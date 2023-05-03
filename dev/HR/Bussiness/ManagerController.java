package HR.Bussiness;
import HR.DataAccess.*;
import HR.DataAccess.DataController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * the class ManagerController hold all the functions which HR manager can do
 */

//this will be singlton
public class ManagerController{
    private static ManagerController instance;
    private WorkerController workerController;
    private DataController  dataController;
    private ManagerPasswordMapper managerPasswordMapper;
    private WorkerMapper workerMapper;
    private SuperMapper superMapper;
    private Map<String, Super> Superim;
    //all the Workers in the company
    private Map<String, Worker> Workers;
    private Map<String, Driver> Drivers;
    //todo add this to the db, read and write for it
    private Map<String,DriverShift> DriversShifts;
    private String ManagerPassword;
    //to chose workers for the shift randomly
    private static Random rand;
    private ManagerController(){
        managerPasswordMapper=ManagerPasswordMapper.getInstance();
        ManagerPassword= managerPasswordMapper.getManagerPassword();
        workerMapper=WorkerMapper.getInstance();
        superMapper=SuperMapper.getInstance();
        Superim = superMapper.getSuperMap();
        Workers= workerMapper.getWorkerMap();
        Drivers=workerMapper.getDriverMap();
        //todo add reading for the workers shfits

        rand = new Random();
        dataController=DataController.getInstance();
        workerController=WorkerController.getInstance();

    }
    public static ManagerController getInstance(){
        if (instance == null) {
            instance = new ManagerController();
        }
        return instance;
    }
    /**
     * @param password input password
     * @return true if password is correct
     */
   public boolean checkPassword(String password){
        return password.equals(ManagerPassword);
   }
    /**
     * change the password in the database and the instance
     * @param password new password
     */
    public void setManagerPassword(String password) {
        ManagerPassword = password;
        managerPasswordMapper.setManagerPassword(password);
    }
    /**
    * the function allows the HR manager to add new branch of markert
     */
    public void addSuper(String siteName,String siteAddress,String sitePhoneNumber,
                                String contactName,int zoneSuper) {
        Super add=new Super(siteName,siteAddress,sitePhoneNumber,contactName, Zone.values()[zoneSuper]);
        Superim.put(add.getName(), add);
    }

    //creating weekly functions
    public void StartWeekly(String BranchName) {
        //load all the workers we need from the db
        dataController.loadAllWorkersFromSuper(BranchName);
        //creat a weekly and put him in the super
        Weekly week = new Weekly();
        Superim.get(BranchName).setWeekly(week);
    }
    public String AddShift(String BranchName,int day_int,int shiftTime_int,int [] workersNum){
        //we get the object of the super we wanna add a weekly to
        Super curr = Superim.get(BranchName);
        //get the day of the shift
        Days day = Days.values()[day_int];
        //get the time of the shift
        ShiftTime time = ShiftTime.values()[shiftTime_int];
        //get times of start and end for shift
        double start;
        double end;
        if (time == ShiftTime.Morning) {
            start = Superim.get(BranchName).getStart_morning(day);
            end = Superim.get(BranchName).getEnd_morning(day);
        } else {
            start = Superim.get(BranchName).getStart_evening(day);
            end = Superim.get(BranchName).getEnd_evening(day);
        }
        //list of availabale shift managers for now
        List<String> CanWorkList= GetAvailableEmployee(day, Jobs.ShiftManager, time, curr.GetWorkersIDS(), curr.getName());
        if (CanWorkList.size() == 0) {
            return "it looks like you are out of managers or you dont have enough, go assing some new ones so you can make the shifts.";
        }
        //we will choose one of the shift managers to the shift
        int chosen=rand.nextInt(CanWorkList.size());
        //create the shift with the shift manager
        Shift CurrShift = new Shift(curr.GetWeekShifts().getStartDate().plusDays(day.ordinal()), time, start, end, Workers.get(CanWorkList.get(chosen)));
        //update the menager shift
        Workers.get(CanWorkList.get(chosen)).AddShift(day);
        Workers.get(CanWorkList.get(chosen)).AddShiftWorked();
        //if we got here we can go on with choosing the rest of the workers
        //start the list from the beginning we don't need the manager list
        CanWorkList.clear();
        int indx_job=0;
        //for each job we chose the workers
        for (Jobs job : Jobs.values()) {
            //we dont need to choose shift manager again or more than one
            if (job == Jobs.ShiftManager) {
                continue;
            }
            //we will get the available Workers
            CanWorkList = GetAvailableEmployee(day, job, time, curr.GetWorkersIDS(), curr.getName());
            if(CanWorkList.size()<workersNum[indx_job]){
                return "it looks like you are out of " + job + " or you dont have enough, go assing some new ones so you can make the shifts.";
            }
            //we will choose the workers
            for (int i = 0; i < workersNum[indx_job]; i++) {
                //get random worker
                chosen = rand.nextInt(CanWorkList.size());
                //add to the shift
                CurrShift.AddWorker(job, Workers.get(CanWorkList.get(chosen)));
                //now i need to update the worker propertyly
                Workers.get(CanWorkList.get(chosen)).AddShift(day);
                Workers.get(CanWorkList.get(chosen)).AddShiftWorked();
                //remove from the available Workers
                CanWorkList.remove(chosen);
            }
            //we finished adding all workers of a job type
        }
        //we add the shift to the weekly
        curr.GetWeekShifts().AddShift(CurrShift);
        return "success";
    }
    //give back string representing day and shift for the ui by numbers
    public String getDayAndShift(int day_int,int shiftTime_int){
        //get the day of the shift
        Days day = Days.values()[day_int];
        //get the time of the shift
        ShiftTime time = ShiftTime.values()[shiftTime_int];
        return ("you are working on: " + day + " at the " + time);
    }
    public void emptyShift(int day_int,int shiftTime_int,String BranchName){
        //we get the object of the super we wanna add a weekly to
        Super curr = Superim.get(BranchName);
        //get the day of the shift
        Days day = Days.values()[day_int];
        //get the time of the shift
        ShiftTime time = ShiftTime.values()[shiftTime_int];
        //get times of start and end for shift
        double start;
        double end;
        if (time == ShiftTime.Morning) {
            start = Superim.get(BranchName).getStart_morning(day);
            end = Superim.get(BranchName).getEnd_morning(day);
        } else {
            start = Superim.get(BranchName).getStart_evening(day);
            end = Superim.get(BranchName).getEnd_evening(day);
        }
        Shift CurrShift = new Shift(curr.GetWeekShifts().getStartDate().plusDays(day.ordinal()), time, start, end);
        curr.GetWeekShifts().AddShift(CurrShift);
    }
    //tells the super that the weekly got canceled
    public void CancelWeekly(String BranchName) {
        //todo  update the workers in the shift that they dont have it now
        Weekly week=Superim.get(BranchName).GetWeekShifts();
        //for each shift we tell it to empty and update thw workers inside
        for(Shift shift: week.getShiftList()){
            shift.clearWorkers();
        }
        //we return it to null
        Superim.get(BranchName).setWeekly(null);
    }
    /**
     * the function allows the HR manager to change the shift time of specific branch
     * @param Name - the name of the branch
     * @param day - the day we want to change the time at
     * @param m_s - the start hour at morning
     * @param m_e - the end hour at morning
     * @param e_s - the start hour at evening
     * @param e_e - the end hour at mevening
     */
    public void UpdateSuperTimes(String Name, Days day, double m_s, double m_e, double e_s, double e_e) {
        Super curr = Superim.get(Name);
        curr.setStart_morning(day, m_s);
        curr.setEnd_morning(day, m_e);
        curr.setStart_evening(day, e_s);
        curr.setEnd_evening(day, e_e);
    }
    /**
     * the function pay the salaries to the workers
     */
    public void Payment() {
        //we need to load all the workers from db to do that

        for (Worker worker : Workers.values()) {
            worker.CalculateSalary();
            worker.resetBonus();
            ResetWorkDaysWorkers();
        }
    }
    /**
     * the function send the constraints of all the workers to history
     */
    public void SendConstraintsToHistory() {
        //send all the weekly to history
        for (Super sup : Superim.values()) {
            sup.SendConstraintsToHistory();
        }
        //prepare the Workers state for next weekly
        for (Worker worker : Workers.values()) {
            worker.ResetDaysOfWork();
        }
    }
    /**
     * the function checks if all the branches have weekly
     * @return true if indeed
     */
    public boolean CheckAllHaveWeekly() {
        for (Super sup : Superim.values()) {
            if (!sup.HasWeekly()) {
                return false;
            }
        }
        return true;
    }
    /**
     * the function add new employee to branch
     * @param branchName - the branch we want to add the worker to
     */
    public void AddNewWorker(String ID, String Name, int Bank,
                                    String Contract, double Wage, Jobs FirstJob, String Password,
                                    String branchName) {
        // create the worker using all the data the manger entered
        Worker newEmployee = new Worker(ID, Name, Bank, Contract, Wage, FirstJob, Password);
        //Domain.Worker new_worker = new Domain.Worker(ID, name, bank, contract, wage, job, password);
        for (Map.Entry<String, Super> entry : Superim.entrySet()) {
            if (entry.getKey().equals(branchName)) {
                Superim.get(entry.getKey()).AddWorker(newEmployee);
            }
        }
        Workers.put(newEmployee.getID(), newEmployee);
    }
    public void AddNewWorker(String ID,String Name,  int Bank,String Contract,
                                    double Wage , String Password,
                                    char license, Training ability) {
        // create the worker using all the data the manger entered
        Driver newEmployee = new Driver(ID , Name, Bank, Contract, Wage, Password, license, ability);
        //Domain.Worker new_worker = new Domain.Worker(ID, name, bank, contract, wage, job, password);
        Drivers.put(newEmployee.getID(), newEmployee);
    }
    //removes a worker from the company
    public void RemoveWorkerAllBranches(String ID) {
        // check if the branch name is existed in the superim list
        for (Map.Entry<String, Super> entry : Superim.entrySet()) {
            // remove if from every branch he works at
            Superim.get(entry.getKey()).RemoveWorker(ID);
//            if (Superim.get(entry.getKey()).RemoveWorker(ID)) {
//                System.out.println("worker removed successfully from " + Superim.get(entry.getKey()).getName());
//            }
        }
        // remove it from the map of all Workers
        Workers.remove(ID);
    }
    //add a job for a worker (role) by id
    public void AddJobToWorker(String ID, int job_index) {
        Workers.get(ID).AddJob(Jobs.values()[job_index - 1]);
    }
    //changes the wage of a worker by id
    public void ChangeWage(String ID, int wage) {
        Workers.get(ID).setWage(wage);
    }
    //changes the contract of a worker by id
    public void ChangeContract(String ID, String input_Contract) {
        Workers.get(ID).setContract(input_Contract);
    }
    //add worker by id to branch
    public boolean AddWorkerToBranch(String ID, String branchName) {
        if (Superim.get(branchName).GetWorkersIDS().contains(ID)) {
            return false;
        }
        // add the worker to the Workers map
        Superim.get(branchName).AddWorker(Workers.get(ID));
        return true;
    }
    // add bonus to worker by given ID
    public String addBonusToWorker(String ID, double bonus) {
        Workers.get(ID).addBonus(bonus);
        return Workers.get(ID).getName();
    }
    public String removeBonusToWorker(String ID, double bonus) {
        Workers.get(ID).removeBonus(bonus);
        return Workers.get(ID).getName();
    }
    public boolean HasWeekly(String Name) {
        return Superim.get(Name).HasWeekly();
    }
    //prints the shifts of one day for a branch (morning and evening)
    public void PrintDay(String Name, int day) {
        //we get the object of the super
        Super curr = Superim.get(Name);
        Weekly week = curr.GetWeekShifts();
        if (week != null) {
            //each day has 2 shifts so we show him both of them
            day = day * 2;
            week.GetShift(day).PrintMe();
            week.GetShift(day + 1).PrintMe();
        }
    }
    // remove worker from a shift of a branch
    public boolean RemoveFromDay(String ID, String branch, int day) {

        //we first need to load all the workers for this super from the db
        dataController.loadAllWorkersFromSuper(branch);
        //we check if the worker is working in this day
        if (!IsWorkAtDay(branch, ID, day)) {
            return false;
        }
        //if he works in this day
        // we will delete this workers working days so it will be re written in the db later with the updates
        dataController.DeleteWorkingDays(ID,Superim.get(branch).GetWeekShifts().GetShift(day));
        int shiftnum = day * 2;
        //when were here we have a good number for employee so we remove him
        Superim.get(branch).GetWeekShifts().GetShift(shiftnum).RemoveWorker(ID);
        Superim.get(branch).GetWeekShifts().GetShift(shiftnum + 1).RemoveWorker(ID);
        Workers.get(ID).RemoveShift(Days.values()[day]);
        return true;
    }
    //checks if worker works in specific shift in a branch
    public boolean IsWorkAtDay(String branch, String ID, int day) {
        day = day * 2;
        return (Superim.get(branch).GetWeekShifts().GetShift(day).IsWorkerAtShift(ID) || Superim.get(branch).GetWeekShifts().GetShift(day + 1).IsWorkerAtShift(ID));
    }
    // add worker to a shift int a branch
    public String AddToDay2(String branch, int shift_op, int day,int jobChoice){
        //we first need to load all the workers for this super from the db
        dataController.loadAllWorkersFromSuper(branch);
        double s = 0;
        double e = 0;
        Jobs job = Jobs.values()[jobChoice];
        //savres it to use in day
        int days_day=day;
        day = day * 2;
        ShiftTime st = ShiftTime.values()[shift_op];
        if (st == ShiftTime.Morning) {
            s = Superim.get(branch).getStart_morning(Days.values()[days_day]);
            e = Superim.get(branch).getEnd_morning(Days.values()[days_day]);
        } else {
            s = Superim.get(branch).getStart_evening(Days.values()[days_day]);
            e = Superim.get(branch).getEnd_evening(Days.values()[days_day]);
            day = day + 1;
        }
        // get all the available employees from the asked branch
        List<String> availableEmployees= GetAvailableEmployee(Days.values()[days_day],job,st, Superim.get(branch).GetWorkersIDS(),branch);
        // add a worker to the shift
        Random rand = new Random();
        String randomWorker = availableEmployees.get(rand.nextInt(availableEmployees.size()));
        Superim.get(branch).GetWeekShifts().GetShift(day).AddWorker(job, Workers.get(randomWorker));
        // add the shift to the Workers shifts
        Workers.get(randomWorker).AddShift(Days.values()[days_day]);
        Workers.get(randomWorker).AddShiftWorked();
        return Workers.get(randomWorker).getName();
    }
    //prints current weekly shifts of a branch
    public boolean PrintWeekly(String Name) {
        //we get the object of the weekly
        Weekly week = Superim.get(Name).GetWeekShifts();
        if (week == null) {
            return false;
        } else {
            week.PrintMe();
            return true;
        }
    }
    // prints all the drivers shifts
    public void PrintDriversSchedule() {
        for(Driver driver: Drivers.values()){
            driver.PrintForShifts();
        }
    }
    //print a shift from history of a branch by its date if exists
    public void PrintWeeklyFromHist(String Name, int year, int month, int day) {
        Superim.get(Name).PrintWeekFromHistByDate(year, month, day);
    }
    //remove a worker from a branch by id
    public void RemoveWorker(String ID, String Name) {
        Super curr = Superim.get(Name);
        curr.RemoveWorker(ID);
    }
    // new added to connect between controllers functions - manu will check if its good or yell at me :(
    public boolean IsWorksInSuper(String ID, String SuperName){
        //we first need to load all the workers for this super from the db
        dataController.loadAllWorkersFromSuper(SuperName);
        return Superim.get(SuperName).GetWorkersIDS().contains(ID);
    }
    public boolean isExistWorker(String ID){return workerController.isExistWorker(ID);}
    public boolean CheckBranchExist(String branchName){ //we tell the database to load that id if exists before we check him
        dataController.getSuper(branchName);
        return Superim.get(branchName) != null;
    }
    //get day job and a list of Workers and checks if the worker can do the work in the shift and these conditions
    public List<String> GetAvailableEmployee(Days day, Jobs job, ShiftTime time, List<String> WorkersID, String SuperName) {
        List<String> ret = new ArrayList<>();
        //we will save the start and end of the shift
        double start = 0;
        double end = 0;
        //like that i get the correct hours
        if (time == ShiftTime.Morning) {
            start = Superim.get(SuperName).getStart_morning(day);
            end = Superim.get(SuperName).getEnd_morning(day);
        } else {
            start = Superim.get(SuperName).getStart_evening(day);
            end = Superim.get(SuperName).getEnd_evening(day);
        }
        //checking each employee
        for (String id : WorkersID) {
            Worker curr = Workers.get(id);
            //check if he is qualified for the job
            if (curr.CanDoJob(job)) {
                //check if he is free by his Domain.Constraints
                if (curr.IsFree(day, start, end)) {
                    ret.add(id);
                }
            }
        }
        return ret;
    }
    // reset the number of shifts to all Workers - im not sure if this one should be here
    public void ResetWorkDaysWorkers() {
        for (String ID : Workers.keySet()) {
            Workers.get(ID).ResetShiftsAmount();
        }
    }
    // the function checks if speific shift is empty
    public boolean IsShiftEmpty(String branch, int shift_op ,int day) {
        //savres it to use in day
        day = day * 2;
        if (shift_op == 2)
            day = day+1;
        return Superim.get(branch).GetWeekShifts().GetShift(day).IsEmptyShift();
    }

    //todo fix problems here
    public void addDriverShift(Days day, Driver driver, String branch){
        DriverShift dr= new DriverShift(day,driver,branch);
        this.DriversShifts.put(dr.getBranch(),dr);
    }
    //todo check that this works!
    //check if we have a delivery tomorrow to a branch and no store keeper there
    //if we return false its not good and we need to assign a stoorekeeper or cancel the shipment
    public boolean checkTomorrowOK(String branch){
        //ill load each super that has a delivery tomorrow
        //the day of tomorrow
        Days day= Days.valueOf(LocalDate.now().plusDays(1).getDayOfWeek().toString());
        for(DriverShift dr: DriversShifts.values()){
            //if the shift is tomorrow
            if(dr.getDay()==day){
                if(!checkTomorrowStoreKeeperOK(dr.getBranch(),day)){
                    return false;
                }
            }
        }
        return true;
    }
    //todo check that this works!
    //check for a branch of tomorrow he has store keeper when he has a driver
    public boolean checkTomorrowStoreKeeperOK(String branch, Days day){
        //ill take the info of the super
        Super curr=superMapper.getsuper(branch);
        for(Shift shift : curr.GetWeekShifts().getShiftList()){
            if(Days.valueOf(shift.getDate().getDayOfWeek().toString())==day){
                if(shift.getWorkerList().get(Jobs.StoreKeeper).size()==0){
                    return false;
                }
            }
        }
        return true;
    }
//todo when shift manager sign in w will use this function to decide if
// we need him to put a store keeper for tomorrow, if we do we will open him a new ui or function ther
// that will ask if he wants to cancel the shipment or assign a store keeper to each shift
// we need a function that will check and assign one for each shift
// if we add one will we tell about it to the hr manager?


    /**
     * it will save the data in the database and close the connection
     */
    public void closeDB(){
        dataController.saveData();
    }

}
