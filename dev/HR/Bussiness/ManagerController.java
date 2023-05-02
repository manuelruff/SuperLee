package HR.Bussiness;
import HR.DataAccess.*;
import HR.DataAccess.DataController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * the class ManagerController hold all the functions which HR manager can do
 */

//this will be singlton
public class ManagerController{
    private static ManagerController instance=new ManagerController();
    private static Map<String, Super> Superim;
    //all the Workers in the company
    private static Map<String, Worker> Workers;
    private static Map<String, Driver> Drivers;
    private static String ManagerPassword;
    protected static Scanner scanner=new Scanner(System.in);
    private ManagerController(){
        ManagerPasswordMapper.getInstance();
        ManagerPassword= ManagerPasswordMapper.getManagerPassword();
        Superim = SuperMapper.getSuperMap();
        Workers= WorkerMapper.getWorkerMap();
        Drivers=WorkerMapper.getDriverMap();
    }
    public static ManagerController getInstance(){
        return instance;
    }
    /**
     * @param password input password
     * @return true if password is correct
     */
   public static boolean checkPassword(String password){
        return password.equals(ManagerPassword);
   }
    /**
     * change the password in the database and the instance
     * @param password new password
     */
    public static void setManagerPassword(String password) {
        ManagerPassword = password;
        ManagerPasswordMapper.setManagerPassword(password);
    }
    /**
    * the function allows the HR manager to add new branch of markert
     */
    public static void addSuper(String siteName,String siteAddress,String sitePhoneNumber,
                                String contactName,int zoneSuper) {
        Super add=new Super(siteName,siteAddress,sitePhoneNumber,contactName, Zone.values()[zoneSuper]);
        Superim.put(add.getName(), add);
    }
    /**
     * the function allows the HR manager to create new weekly
     * @param Name - the name of the branch he wants to create a new weekly
     */
    public static void CreateWeekly(String Name) {
        //we first need to load all the workers for this super from the db
        DataController.loadAllWorkersFromSuper(Name);
        //we get the object of the super we wanna add a weekly to
        Super curr = Superim.get(Name);
        List<String> CanWorkList;
        //create the weekly
        Weekly week = new Weekly();
        for (Days day : Days.values()) {
            ShiftTime time = ShiftTime.Morning;
            for (int i = 0; i < 2; i++) {
                //get the time of the shift
                double start;
                double end;
                if (time == ShiftTime.Morning) {
                    start = Superim.get(Name).getStart_morning(day);
                    end = Superim.get(Name).getEnd_morning(day);
                } else {
                    start = Superim.get(Name).getStart_evening(day);
                    end = Superim.get(Name).getEnd_evening(day);
                }
                System.out.println("we are working on: " + day + " at the " + time + " shift");
                //first we need to choose the manager to start the shift
                System.out.println("first choose the shift manager: ");
                //ill get the list of available managers
                CanWorkList = GetAvailableEmployee(day, Jobs.ShiftManager, time, curr.GetWorkersIDS(), curr.getName());
                if (CanWorkList.size() == 0) {
                    System.out.println("it looks like you are out of managers or you dont have enough, go assing some new ones so you can make the shifts.");
                    return;
                }
                //prints the list of Workers available - name and id
                for (int j = 0; j < CanWorkList.size(); j++) {
                    System.out.println(j + 1 + ". " + Workers.get(CanWorkList.get(j)).getName() + " with ID: " + CanWorkList.get(j));
                }
                System.out.println("if you dont want this shift to happen enter 0.");
                int num =AskForNumber(0,CanWorkList.size());
                //if he dont want anyone in the shift we create an empty shift
                if (num==0){
                    Shift CurrShift = new Shift(week.getStartDate().plusDays(day.ordinal()), time, start, end);

                    week.AddShift(CurrShift);
                    //we go to evening shift
                    time = ShiftTime.Evening;
                    //go to next iteration
                    continue;
                }
                //when we get here we have a good choice for the shift manager
                //Workers the shift and now we need to add to it
                Shift CurrShift = new Shift(week.getStartDate().plusDays(day.ordinal()), time, start, end, Workers.get(CanWorkList.get(num - 1)));
                //update the menager shift
                Workers.get(CanWorkList.get(num - 1)).AddShift(day);
                Workers.get(CanWorkList.get(num - 1)).AddShiftWorked();
                //then we need to let him see the rest without shift manager
                //then we send it to the curr shift

                //start the list from the beginning we don't need the manager list
                CanWorkList.clear();

                for (Jobs job : Jobs.values()) {
                    //we dont need to choose shift manager again or more than one
                    if (job == Jobs.ShiftManager) {
                        continue;
                    }
                    //we will get the available Workers
                    CanWorkList = GetAvailableEmployee(day, job, time, curr.GetWorkersIDS(), curr.getName());
                    System.out.print(" how many Workers as " + job + " you want? ");
                    num =AskForNumber(0,CanWorkList.size());

                    //if i got here i have a good number
                    int k = 0;
                    while (k < num) {
                        System.out.println("the Workers that can be in this shift are: ");
                        for (int j = 0; j < CanWorkList.size(); j++) {
                            System.out.println((j + 1) + ". " + Workers.get(CanWorkList.get(j)).getName() + " with ID: " + CanWorkList.get(j));
                        }
                        int choice = AskForNumber(1,CanWorkList.size());
                        //if i got here i have a good index for a worker
                        // we will decrese one so it will be the index from the list
                        choice = choice - 1;
                        //add to the shift
                        CurrShift.AddWorker(job, Workers.get(CanWorkList.get(choice)));
                        //now i need to update the worker propertyly
                        Workers.get(CanWorkList.get(choice)).AddShift(day);
                        Workers.get(CanWorkList.get(choice)).AddShiftWorked();
                        //remove from the available Workers
                        CanWorkList.remove(choice);
                        k++;
                    }
                }
                //send the shift i created to the weekly
                week.AddShift(CurrShift);
                //we go to evening shift
                time = ShiftTime.Evening;
            }
        }
        //add the weekly to the super
        curr.AddWeekly(week);
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
    public static void UpdateSuperTimes(String Name, Days day, double m_s, double m_e, double e_s, double e_e) {
        Super curr = Superim.get(Name);
        curr.setStart_morning(day, m_s);
        curr.setEnd_morning(day, m_e);
        curr.setStart_evening(day, e_s);
        curr.setEnd_evening(day, e_e);
    }
    /**
     * the function pay the salaries to the workers
     */
    public static void Payment() {
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
    public static void SendConstraintsToHistory() {
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
    public static boolean CheckAllHaveWeekly() {
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
    public static void AddNewWorker(String ID, String Name, int Bank,
                                    String Contract, double Wage, Jobs FirstJob, String Password,
                                    String branchName) {
        // create the worker using all the data the manger entered
        Worker newEmployee = new Worker(ID, Name, Bank, Contract, Wage, FirstJob, Password);
        //Domain.Worker new_worker = new Domain.Worker(ID, name, bank, contract, wage, job, password);
        for (Map.Entry<String, Super> entry : Superim.entrySet()) {
            if (entry.getKey().equals(branchName)) {
                Superim.get(entry.getKey()).AddWorker(newEmployee);
                System.out.println(newEmployee.getID() + " added successfully to: " + branchName);
            }
        }
        Workers.put(newEmployee.getID(), newEmployee);
    }
    //todo check of its working
    public static void AddNewWorker(String ID,String Name,  int Bank,String Contract,
                                    double Wage , String Password,
                                    char license, Training ability) {
        // create the worker using all the data the manger entered
        Driver newEmployee = new Driver(ID , Name, Bank, Contract, Wage, Password, license, ability);
        //Domain.Worker new_worker = new Domain.Worker(ID, name, bank, contract, wage, job, password);
        Drivers.put(newEmployee.getID(), newEmployee);
    }
    //removes a worker from the company
    public static void RemoveWorkerAllBranches(String ID) {
        // check if the branch name is existed in the superim list
        for (Map.Entry<String, Super> entry : Superim.entrySet()) {
            // remove if from every branch he works at
            if (Superim.get(entry.getKey()).RemoveWorker(ID)) {
                System.out.println("worker removed successfully from " + Superim.get(entry.getKey()).getName());
            }
        }
        // remove it from the map of all Workers
        Workers.remove(ID);
    }
    //add a job for a worker (role) by id
    public static void AddJobToWorker(String ID, int job_index) {
        Workers.get(ID).AddJob(Jobs.values()[job_index - 1]);
    }
    //changes the wage of a worker by id
    public static void ChangeWage(String ID, int wage) {
        Workers.get(ID).setWage(wage);
    }
    //changes the contract of a worker by id
    public static void ChangeContract(String ID, String input_Contract) {
        Workers.get(ID).setContract(input_Contract);
    }
    //add worker by id to branch
    public static boolean AddWorkerToBranch(String ID, String branchName) {
        if (Superim.get(branchName).GetWorkersIDS().contains(ID)) {
            return false;
        }
        // add the worker to the Workers map
        Superim.get(branchName).AddWorker(Workers.get(ID));
        return true;
    }
    // add bonus to worker by given ID
    public static String addBonusToWorker(String ID, double bonus) {
        Workers.get(ID).addBonus(bonus);
        return Workers.get(ID).getName();
    }
    public static String removeBonusToWorker(String ID, double bonus) {
        Workers.get(ID).removeBonus(bonus);
        return Workers.get(ID).getName();
    }
    public static boolean HasWeekly(String Name) {
        return Superim.get(Name).HasWeekly();
    }
    //prints the shifts of one day for a branch (morning and evening)
    public static void PrintDay(String Name, int day) {
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
    public static boolean RemoveFromDay(String ID, String branch, int day) {

        //we first need to load all the workers for this super from the db
        DataController.loadAllWorkersFromSuper(branch);
        //we check if the worker is working in this day
        if (!IsWorkAtDay(branch, ID, day)) {
            return false;
        }
        //if he works in this day
        // we will delete this workers working days so it will be re written in the db later with the updates
        DataController.DeleteWorkingDays(ID,Superim.get(branch).GetWeekShifts().GetShift(day));
        int shiftnum = day * 2;
        //when were here we have a good number for employee so we remove him
        Superim.get(branch).GetWeekShifts().GetShift(shiftnum).RemoveWorker(ID);
        Superim.get(branch).GetWeekShifts().GetShift(shiftnum + 1).RemoveWorker(ID);
        Workers.get(ID).RemoveShift(Days.values()[day]);
        return true;
    }
    //checks if worker works in specific shift in a branch
    public static boolean IsWorkAtDay(String branch, String ID, int day) {
        day = day * 2;
        return (Superim.get(branch).GetWeekShifts().GetShift(day).IsWorkerAtShift(ID) || Superim.get(branch).GetWeekShifts().GetShift(day + 1).IsWorkerAtShift(ID));
    }

    // add worker to a shift int a branch
    public static void AddToDay(String ID, String branch, int shift_op, int day) {
        //we first need to load all the workers for this super from the db
        DataController.loadAllWorkersFromSuper(branch);
        double s = 0;
        double e = 0;
        //savres it to use in day
        int days_day=day;
        day = day * 2;
        if (shift_op == 1) {
            s = Superim.get(branch).getStart_morning(Days.values()[days_day]);
            e = Superim.get(branch).getEnd_morning(Days.values()[days_day]);
        } else {
            s = Superim.get(branch).getStart_evening(Days.values()[days_day]);
            e = Superim.get(branch).getEnd_evening(Days.values()[days_day]);
            day = day + 1;
        }
        if (!Workers.get(ID).IsFree(Days.values()[days_day], s, e)) {
            System.out.println("this worker can't work at this shift");
            return;
        } else {
            //check if the shift  is empty
            if (Superim.get(branch).GetWeekShifts().GetShift(day).IsEmptyShift()){
                if(!Workers.get(ID).CanDoJob(Jobs.ShiftManager)){
                    System.out.println("this shift is empty you need to add a shift manager first");
                }
                else{
                    //if we are here he added a manager to an empty shift
                    //whem were here we have a good number for employee so we add him
                    Superim.get(branch).GetWeekShifts().GetShift(day).AddWorker(Jobs.ShiftManager, Workers.get(ID));
                    // add the shift to the Workers shifts
                    Workers.get(ID).AddShift(Days.values()[days_day]);
                    Workers.get(ID).AddShiftWorked();
                }
            }
            else {
                //print what role this worker can do and he decides what he wants him to do
                //whem were here we have a good number for employee so we add him

                //add all the jobs he can do
                List<Jobs> job_list=new ArrayList<>();
                for(Jobs job:Jobs.values()){
                    if(Workers.get(ID).CanDoJob(job)){
                        job_list.add(job);
                    }
                }
                //show the manager what he can do so he will choose
                System.out.println("please choose the role you want to put him in");
                int i=1;
                for(Jobs job:job_list){
                    System.out.println(i+". "+job);
                    i++;
                }
                int num=AskForNumber(1,job_list.size());
                Superim.get(branch).GetWeekShifts().GetShift(day).AddWorker(job_list.get(num-1), Workers.get(ID));
                // add the shift to the Workers shifts
                Workers.get(ID).AddShift(Days.values()[days_day]);
                Workers.get(ID).AddShiftWorked();
            }
        }
    }
    //prints current weekly shifts of a branch
    public static boolean PrintWeekly(String Name) {
        //we get the object of the weekly
        Weekly week = Superim.get(Name).GetWeekShifts();
        if (week == null) {
            return false;
        } else {
            week.PrintMe();
            return true;
        }
    }
    //print a shift from history of a branch by its date if exists
    public static void PrintWeeklyFromHist(String Name, int year, int month, int day) {
        Superim.get(Name).PrintWeekFromHistByDate(year, month, day);
    }
    //remove a worker from a branch by id
    public static void RemoveWorker(String ID, String Name) {
        Super curr = Superim.get(Name);
        curr.RemoveWorker(ID);
    }
    // new added to connect between controllers functions - manu will check if its good or yell at me :(
    public static boolean IsWorksInSuper(String ID, String SuperName){
        //we first need to load all the workers for this super from the db
        DataController.loadAllWorkersFromSuper(SuperName);
        return Superim.get(SuperName).GetWorkersIDS().contains(ID);
    }
    public static boolean isExistWorker(String ID){return WorkerController.isExistWorker(ID);}
    public static boolean CheckBranchExist(String branchName){ //we tell the database to load that id if exists before we check him
        DataController.getSuper(branchName);
        return Superim.get(branchName) != null;
    }
    public static int AskForNumber(int s, int e) {
        int num = 0;
        while (true) {
            String input = scanner.nextLine();
            try {
                num = Integer.parseInt(input);
                if (num <= e && num >= s) {
                    return num;
                } else {
                    System.out.println("your input is not in range of: " + s + " - " + e);
                }
            }
            //if he entered something not suitable we will repeat
            catch (Exception e1) {
                System.out.println("invalid input, please try again");
            }
        }
    }
    //get day job and a list of Workers and checks if the worker can do the work in the shift and these conditions
    public static List<String> GetAvailableEmployee(Days day, Jobs job, ShiftTime time, List<String> WorkersID, String SuperName) {
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
    public static void ResetWorkDaysWorkers() {
        for (String ID : Workers.keySet()) {
            Workers.get(ID).ResetShiftsAmount();
        }
    }
    /**
     * it will save the data in the database and close the connection
     */
    public static void closeDB(){
        DataController.saveData();
    }
}
