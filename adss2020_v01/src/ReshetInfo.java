import javax.security.auth.Subject;
import java.time.LocalDate;
import java.util.*;

public class ReshetInfo {
    //all the branches of the company
    private static Map<String ,Super> Superim;
    //all the workers in the company
    private static Map<String ,Worker> Workers;



    //builder for reshetinfo
    public ReshetInfo(){

        Superim=new HashMap<>();
        Workers=new HashMap<>();
        //create supers and workers and insert them to where i need
        StartData();
    }

    /**
     * this function will create database for beginning
     */
    public void StartData(){

        //creating shift managers:
        Worker ShiftManager1 = new Worker("manu" , "1", 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Worker ShiftManager2 = new Worker("david" , "2", 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Worker ShiftManager3 = new Worker("muhamad" , "3", 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Worker ShiftManager4 = new Worker("omri" , "4", 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Worker ShiftManager5 = new Worker("oded" , "5", 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Worker ShiftManager6 = new Worker("oded2" , "6", 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Worker ShiftManager7 = new Worker("peleg" , "7", 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Worker ShiftManager8 = new Worker("mitzi" , "8", 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Worker ShiftManager9 = new Worker("hatul" , "9", 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        //puts them in the worker list
        Workers.put("1",ShiftManager1);
        Workers.put("2",ShiftManager2);
        Workers.put("3",ShiftManager3);
        Workers.put("4",ShiftManager4);
        Workers.put("5",ShiftManager5);
        Workers.put("6",ShiftManager6);
        Workers.put("7",ShiftManager7);
        Workers.put("8",ShiftManager8);
        Workers.put("9",ShiftManager9);

        //create cashiers
        Worker Cashier1 = new Worker("mikel" , "10", 318 , "ata ahla gever",60, Jobs.Cashier ,"123" );
        Worker Cashier2 = new Worker("migul" , "11", 318 , "ata ahla gever",60, Jobs.Cashier ,"123" );
        Worker Cashier3 = new Worker("huan" , "12", 318 , "ata ahla gever",60, Jobs.Cashier ,"123" );
        Worker Cashier4 = new Worker("omri-escopar" , "13", 318 , "ata ahla gever",60, Jobs.Cashier ,"123" );
        //puts them in the worker list
        Workers.put("10",Cashier1);
        Workers.put("11",Cashier2);
        Workers.put("12",Cashier3);
        Workers.put("13",Cashier4);

        //create StoreKeeper
        Worker StoreKeeper1 = new Worker("oded" , "14", 318 , "ata ahla gever",60, Jobs.StoreKeeper ,"123" );
        Worker StoreKeeper2 = new Worker("gal" , "15", 318 , "ata ahla gever",60, Jobs.StoreKeeper ,"123" );
        Worker StoreKeeper3 = new Worker("papi" , "16", 318 , "ata ahla gever",60, Jobs.StoreKeeper ,"123" );
        Worker StoreKeeper4 = new Worker("hatul" , "17", 318 , "ata ahla gever",60, Jobs.StoreKeeper ,"123" );
        //puts them in the worker list
        Workers.put("14",StoreKeeper1);
        Workers.put("15",StoreKeeper2);
        Workers.put("16",StoreKeeper3);
        Workers.put("17",StoreKeeper4);


        //create GeneralEmployee
        Worker GeneralEmp1 = new Worker("odedi" , "18", 318 , "ata ahla gever",30, Jobs.GeneralEmp ,"123" );
        Worker GeneralEmp2 = new Worker("shimun" , "19", 318 , "ata ahla gever",30, Jobs.GeneralEmp ,"123" );
        Worker GeneralEmp3 = new Worker("sara" , "20", 318 , "ata ahla gever",30, Jobs.GeneralEmp ,"123" );
        Worker GeneralEmp4 = new Worker("gebi" , "21", 318 , "ata ahla gever",30, Jobs.GeneralEmp ,"123" );
        //puts them in the worker list
        Workers.put("18",GeneralEmp1);
        Workers.put("19",GeneralEmp2);
        Workers.put("20",GeneralEmp3);
        Workers.put("21",GeneralEmp4);

        //create guards
        Worker Guard1 = new Worker("yuri" , "22", 318 , "ata ahla gever",50, Jobs.Guard ,"123" );
        Worker Guard2 = new Worker("dor" , "23", 318 , "ata ahla gever",50, Jobs.Guard ,"123" );
        Worker Guard3 = new Worker("shalev" , "24", 318 , "ata ahla gever",50, Jobs.Guard ,"123" );
        Worker Guard4 = new Worker("harel" , "25", 318 , "ata ahla gever",50, Jobs.Guard ,"123" );
        //puts them in the worker list
        Workers.put("22",Guard1);
        Workers.put("23",Guard2);
        Workers.put("24",Guard3);
        Workers.put("25",Guard4);

        //create cleaner
        Worker Cleaner1 = new Worker("rohama" , "26", 318 , "ata ahla gever",10, Jobs.Cleaner ,"123" );
        Worker Cleaner2 = new Worker("avraham" , "27", 318 , "ata ahla gever",10, Jobs.Cleaner ,"123" );
        Worker Cleaner3 = new Worker("shoshana" , "28", 318 , "ata ahla gever",10, Jobs.Cleaner ,"123" );
        Worker Cleaner4 = new Worker("alo" , "29", 318 , "ata ahla gever",10, Jobs.Cleaner ,"123" );
        //puts them in the worker list
        Workers.put("26",Cleaner1);
        Workers.put("27",Cleaner2);
        Workers.put("28",Cleaner3);
        Workers.put("29",Cleaner4);

        //create usher
        Worker Usher1 = new Worker("ahrak" , "30", 318 , "ata ahla gever",15, Jobs.Usher ,"123" );
        Worker Usher2 = new Worker("midbar" , "31", 318 , "ata ahla gever",15, Jobs.Usher ,"123" );
        Worker Usher3 = new Worker("sahara" , "32", 318 , "ata ahla gever",15, Jobs.Usher ,"123" );
        Worker Usher4 = new Worker("alosantos" , "33", 318 , "ata ahla gever",15, Jobs.Usher ,"123" );
        //puts them in the worker list
        Workers.put("30",Usher1);
        Workers.put("31",Usher2);
        Workers.put("32",Usher3);
        Workers.put("33",Usher4);

        //create a super
        Super Super1 = new Super("zolretzah");
        Super Super2 = new Super("yakarmeod");
        //add all the employees to the supers
        for (int i=1;i<=33;i++) {
            Super1.AddWorker(Integer.toString(i));
            Super2.AddWorker(Integer.toString(i));
        }
        //add the super to the super list
        Superim.put("zolretzah",Super1);
        Superim.put("yakarmeod",Super2);
    }

    //checks if the name of a super exists
    public boolean CheckSuperName(String Name){
        return Superim.get(Name) != null;
    }

    //creates weeklysheeft for a branch
    public void CreateWeekly(String Name){
        //we get the object of the super we wanna add a weekly to
        Super curr=Superim.get(Name);
        List<String> CanWorkList;
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        //create the weekly
        Weekly week=new Weekly();
        for (Days day:Days.values()){
            ShiftTime time=ShiftTime.Morning;
            for(int i=0;i<2;i++) {
                System.out.println("we are working on: " + day + " at the "+time+ " shift");
                //first we need to choose the manager to start the shift
                System.out.println("first choose the shift manager: ");
                //ill get the list of available managers
                CanWorkList=GetAvailableEmployee(day, Jobs.ShiftManager, time, curr.GetWorkers(),curr.GetName());
                if(CanWorkList.size()==0){
                    System.out.println("it looks like you are out of managers or you dont have enough, go assing some new ones so you can make the shifts.");
                    return;
                }
                //prints the list of workers available - name and id
                for(int j=0;j<CanWorkList.size();j++){
                    System.out.println(j+1 +". "+ Workers.get(CanWorkList.get(j)).GetName() + " with ID: "+ CanWorkList.get(j));
                }
                int num = -1;
                while (num == -1) {
                    String number = myObj.nextLine();  // Read user input
                    //try to change the input to a string
                    try {
                        num = Integer.parseInt(number);
                        //if he puts negative value its not good
                        if (num < 1 || num > CanWorkList.size()) {
                            System.out.println("please choose a valid option");
                            num = -1;
                        }
                    }
                    //if he entered someting not suitable we will repeate
                    catch (Exception e) {
                        System.out.println("please choose a valid option");
                        num = -1;
                    }
                }
                //when we get here we have a good choice for the shift manager
                //get the time of the shift
                double start;
                double end;
                if(time ==ShiftTime.Morning){
                    start= Superim.get(Name).getStart_morning(day);
                    end=Superim.get(Name).getEnd_morning(day);
                }
                else{
                    start= Superim.get(Name).getStart_evening(day);
                    end=Superim.get(Name).getEnd_evening(day);
                }
                //crete the shift and now we need to add to it
                Shift CurrShift=new Shift(week.getStartDate().plusDays(day.ordinal()),time,start,end,CanWorkList.get(num-1),Workers.get(CanWorkList.get(num-1)).GetName());
                //update the menager shift
                Workers.get(CanWorkList.get(num-1)).AddShift(day);
                //then we need to let him see the rest without shift manager
                //then we send it to the curr shift

                //start the list from the beginnning we dont need the menager list
                CanWorkList.clear();

                for (Jobs job : Jobs.values()) {
                    //we dont need to choose shift manager again or more then one
                    if (job==Jobs.ShiftManager){
                        continue;
                    }
                    num = -1;
                    while (num == -1) {
                        System.out.print(" how many workers as " + job + " you want? ");
                        String number = myObj.nextLine();  // Read user input
                        //try to change the input to int
                        try {
                            num = Integer.parseInt(number);
                            //if he puts negative value its not good
                            if (num < 0) {
                                num = -1;
                                continue;
                            }
                            //we will get the available workers
                            CanWorkList = GetAvailableEmployee(day, job, time, curr.GetWorkers(),curr.GetName());
                            //if he wants too many its also not good
                            if (CanWorkList.size() < num) {
                                System.out.println("you dont have enough workers, the maximum is: " + CanWorkList.size() + " please choose again");
                                num = -1;
                            }
                        }
                        //if he entered someting not suitable we will repeate
                        catch (Exception e) {
                            System.out.println("please choose a valid number");
                            num = -1;
                        }
                    }
                    //if i got here i have a good number
                    int k = 0;
                    while (k < num) {
                        System.out.println("the workers that can be in this shift are: ");
                        for (int j = 0; j < CanWorkList.size(); j++) {
                            System.out.println((j+1) +". " +Workers.get(CanWorkList.get(j)).GetName() + " with ID: "+ CanWorkList.get(j));
                        }
                        int choice=-1;
                        while (choice==-1){
                            System.out.println("choose the one you want");
                            String input = myObj.nextLine();  // Read user input
                            //try to change the input to a string
                            try{
                                choice=Integer.parseInt(input);
                                if (choice<1 || choice>CanWorkList.size()){
                                    System.out.println("please choose a valid number.");
                                    choice =-1;
                                }
                            }
                            //if he entered something not suitable we will repeate
                            catch (Exception e){
                                System.out.println("please choose a valid number.");
                                choice =-1;
                            }
                        }
                        //if i got here i have a good index for a worker
                        // we will decrese one so it will be the index from the list
                        choice=choice-1;
                        //add to the shift
                        CurrShift.AddWorker(CanWorkList.get(choice),Workers.get(CanWorkList.get(choice)).GetName());
                        //now i need to update the worker propertyly
                        Workers.get(CanWorkList.get(choice)).AddShift(day);
                        //remove from the available workers
                        CanWorkList.remove(choice);
                        k++;
                    }

                }
                    //send the shift i created to the weekly
                    week.AddShift(CurrShift);
                    //we go to evening shift
                    time=ShiftTime.Evening;
                }
            }
        //add the weekly to the super
        curr.AddWeekly(week);
        }

    //checks if there is a weekly right now
    public boolean HasWeekly(String Name){
        return Superim.get(Name).HasWeekly();
    }

    //prints the shifts of one day for a branch (morning and evening)
    public void PrintDay(String Name,int day){
        //we get the object of the super
        Super curr=Superim.get(Name);
        Weekly week=curr.GetWeekShifts();
        if(week==null){
            System.out.println("nothing in this week yet");
        }
        else{
            //each day has 2 shifts so we show him both of them
            day=day*2;
            week.GetShift(day).PrintMe();
            week.GetShift(day+1).PrintMe();
        }
    }

    // remove worker from a shift of a branch
    public void RemoveFromDay(String ID,String branch,int day){
        if(!IsWorkAtDay(branch,ID,day)){
            System.out.println("this worker doesn't works at this shift");
            return;
        }
        int shiftnum = day*2;
        //whem were here we have a good number for employee so we remove him
        Superim.get(branch).GetWeekShifts().GetShift(shiftnum).RemoveWorker(ID);
        Superim.get(branch).GetWeekShifts().GetShift(shiftnum+1).RemoveWorker(ID);
        Workers.get(ID).RemoveShift(Days.values()[day]);
    }

    //checks if worker works in specific shift in a branch
    public static boolean IsWorkAtDay(String branch,String ID,int day){
        day = day*2;
        return (Superim.get(branch).GetWeekShifts().GetShift(day).IsWorkerAtShift(ID) || Superim.get(branch).GetWeekShifts().GetShift(day+1).IsWorkerAtShift(ID));
    }
    // add worker to a shift int a branch
    public void AddToDay(String ID, String branch,int shift_op,int day){
        double s=0;
        double e=0;
        day=day*2;
        if(shift_op == 1)
        {
            s=Superim.get(branch).getStart_morning(Days.values()[day]);
            e=Superim.get(branch).getEnd_morning(Days.values()[day]);
        }
        else {
            s=Superim.get(branch).getStart_evening(Days.values()[day]);
            e=Superim.get(branch).getEnd_evening(Days.values()[day]);
            day=day+1;
        }
        if(!Workers.get(ID).IsFree(Days.values()[day],s,e)){
            System.out.println("this worker can't work at this shift");
            return;
        }
        else{
            //whem were here we have a good number for employee so we add him
            Superim.get(branch).GetWeekShifts().GetShift(day).AddWorker(ID,Workers.get(ID).GetName());
            // add the shift to the workers shifts
            Workers.get(ID).AddShift(Days.values()[day]);
        }

    }
    //prints current weekly shifts of a branch
    public void PrintWeekly(String Name){
        //we get the object of the weekly
        Weekly week=Superim.get(Name).GetWeekShifts();
        if(week==null){
            System.out.println("no weekly shift yet");
        }
        else{week.PrintMe();}
    }
    //print a shift from history of a branch by its date if exists
    public void PrintWeeklyFromHist(String Name,int year,int month,int day){
        Superim.get(Name).PrintWeekFromHistByDate(year,month,day);
    }
    // check if worker is exists by id
    public boolean isExistWorker(String ID){
        return Workers.get(ID) != null;
    }
    //remove a worker from a branch by id
    public void RemoveWorker(String ID,String Name){
        //we get the object of the super we wanna add a weekly to
        Super curr=Superim.get(Name);
        curr.RemoveWorker(ID);
    }
    //get day job and a list of workers and checks if the worker can do the work in the shift and these conditions
    public List<String> GetAvailableEmployee(Days day, Jobs job,ShiftTime time, List<String> WorkersID,String SuperName){
    List<String> ret=new ArrayList<>();
    //we will save the start and end of the shift
    double start=0;
    double end=0;
    //like that i get the correct hours
    if (time==ShiftTime.Morning){
        start=Superim.get(SuperName).getStart_morning(day);
        end=Superim.get(SuperName).getEnd_morning(day);
    }
    else{
        start=Superim.get(SuperName).getStart_evening(day);
        end=Superim.get(SuperName).getEnd_evening(day);
    }
    //checking each employee
        for(String id:WorkersID){
            Worker curr=Workers.get(id);
            //check if he is qualified for the job
            if(curr.CanDoJob(job)){
                //check if he is free by his Constraints
                if (curr.IsFree(day,start,end)) {
                    ret.add(id);
                }
            }
        }
        return ret;
    }
    //check if the password is correct by given ID
    public boolean IsTruePassword(String ID,String password){
        if(!isExistWorker(ID)){
            return false;
        }
        return Workers.get(ID).CheckPassword(password);
    }
    // add Constraints to worker by given Id
    public void AddConstraints(String ID,int day,double s_hour,double e_hour,String r){
        Workers.get(ID).AddCantWork(Days.values()[day-1],s_hour,e_hour,r);
    }
    // remove Constraintss for worker by id
    public void RemoveConstraints(String ID,int day,double s_hour,double e_hour){
        Workers.get(ID).RemoveCantWork(Days.values()[day-1],s_hour,e_hour);
    }
    //changes a worker password
    public void ChangeWorkerPassword(String ID, String newPassword){Workers.get(ID).SetPassword(newPassword);}
    //changes a worker name
    public void ChangeWorkerName(String ID, String newName){Workers.get(ID).SetName(newName);}
    //changes a worker bank info
    public void ChangeWorkerBank(String ID, int newBank){Workers.get(ID).SetBank(newBank);}
    //prints constraints of worker
    public void ShowConstraints(String ID){Workers.get(ID).ShowConstraints();}
    //sends weekly of all branches to their history
    public void SendConstraintsToHistory(){
        //send all the weekly to history
        for(Super sup:Superim.values()){
            sup.SendConstraintsToHistory();
        }
        //prepare the workers state for next weekly
        for(Worker worker : Workers.values()){
            worker.ResetDaysOfWork();
        }
    }
    //check if all branches has the weekly
    public boolean CheckAllHaveWeekly(){
        for(Super sup:Superim.values()){
            if(!sup.HasWeekly()){
                return false;
            }
        }
        return true;
    }
    //update times of shift for one day in a branch
    public void UpdateSuperTimes(String Name,Days day, double m_s,double m_e, double e_s, double e_e){
        Super curr=Superim.get(Name);
        curr.setStart_morning(day,m_s);
        curr.setEnd_morning(day,m_e);
        curr.setStart_evening(day,e_s);
        curr.setEnd_evening(day,e_e);
    }
    //add a cash cancellations
    public void AddCancellations(String Name,String item,double amount,String ID){
        //create the cancallation
        Cancellations cancel=new Cancellations(amount,item,ID,Workers.get(ID).GetName());
        //add the cancellation to the super
        Superim.get(Name).get_cash_register().AddCancalation(cancel);
    }
    //print Cancellations of a specific date in a branch
    public void PrintCancellation(String Name,int year,int month,int day){
        Superim.get(Name).get_cash_register().PrintCancellation( year, month, day);
    }
    //add a new worker to the company
    public void AddNewWorker(Worker newEmployee,String branchName){
        // create the worker using all the data the manger entered
        //Worker new_worker = new Worker(ID, name, bank, contract, wage, job, password);
        for (Map.Entry<String, Super> entry : Superim.entrySet()) {
            if (entry.getKey().equals(branchName)) {
                Superim.get(entry.getKey()).AddWorker(newEmployee.GetID());
                Workers.put(newEmployee.GetID(),newEmployee);
                System.out.println(newEmployee.GetID() + " added successfully to: " + branchName);
            }
        }
    }
    //add worker by id to branch
    public void AddWorkerToBranch(String ID, String branchName){
        if (Superim.get(branchName).GetWorkers().contains(ID)){
            System.out.println("the worker is already in this branch");
            return;
        }
        // add the worker to the workers map
        Superim.get(branchName).AddWorker(ID);
        System.out.println( ID + " added successfully to: " + branchName);
    }

    //removes a worker from the company
    public void RemoveWorkerAllBranches(String ID){
        // check if the branch name is existed in the superim list
        for (Map.Entry<String, Super> entry : Superim.entrySet()) {
            // remove if from every branch he works at
            if(Superim.get(entry.getKey()).RemoveWorker(ID)){
                System.out.println("worker removed successfully from "+ Superim.get(entry.getKey()).GetName());
            }
        }
        // remove it from the map of all workers
        Workers.remove(ID);
    }
    //add a job for a worker (role) by id
    public void AddJobToWorker(String ID,int job_index){
        Workers.get(ID).AddJob(Jobs.values()[job_index - 1]);
    }
    //changes the wage of a worker by id
    public void ChangeWage(String ID,int wage){
        Workers.get(ID).setWage(wage);
    }
    //changes the contract of a worker by id
    public void ChangeContract(String ID, String input_Contract){
        Workers.get(ID).setContract(input_Contract);
    }
    //check that a given number is in a time structure (hours and minuts)
    public boolean CheckTimeValidate(double start, double end){
        double start_dec = start - Math.floor(start);
        double end_dec = end - Math.floor(end);
        return (! (start < 0 || start > end || end > 24 || start_dec >= 0.60 || end_dec >= 0.60));
    }
    // calculate salary for worker without a bonus
    public double CalculateMonthlySalary(String ID){
        if (!isExistWorker(ID)) {
            System.out.println("this worker is not working at our markets");
            return -1;
        }
        double salary = Workers.get(ID).getShiftWorked()*Workers.get(ID).getWage();
        return salary;
    }
    // with bonus
    public double CalculateMonthlySalary(String ID,double bonus){
        if (!isExistWorker(ID)) {
            System.out.println("this worker is not working at our markets");
            return -1;
        }
        double salary = Workers.get(ID).getShiftWorked()*Workers.get(ID).getWage() + bonus;
        return salary;
    }
    // reset the number of shifts to all workers
    public void ResetWorkDaysWorkers (){
        for(String ID : Workers.keySet() ){
            Workers.get(ID).ResetShiftsAmount();
        }
    }
    //returns worker by id
    public Worker GetWorkerByID(String ID){
        return Workers.get(ID);
    }
    //check by id if worker works in specific super
    public boolean IsWorksInSuper(String ID, String SuperName){
        return Superim.get(SuperName).GetWorkers().contains(ID);

    }
    //check if a worker can to a job\role
    public boolean CanDoJob(String ID, Jobs job){
        return Workers.get(ID).CanDoJob(job);
    }
}
