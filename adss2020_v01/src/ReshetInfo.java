import java.util.*;

public class ReshetInfo {
    //all the branches of the company
    private static Map<String ,Super> Superim;
    //all the workers in the company
    private static Map<String ,Worker> Workers;
    //every week the managers will fill their desires for workers and it will be saved here
    private static Map<String,List<WantShift>> want_shifts;
    //if shift wasnt planned the workers cant put themselves
    private static Map<String,Boolean> is_shift_planed;
    //if the shift was created the workers cant make changes
    private static Map<String,Boolean> is_shift_created;
    //if the woirkers didnt add themeselves to the shifts the shift cant be created
    private static Map<String,Boolean> is_workers_in_shift;

    //builder for reshetinfo
    public ReshetInfo(){

        Superim=new HashMap<>();
        Workers=new HashMap<>();
        want_shifts=new HashMap<>();
        //create supers and workers and insert them to where i need
        StartData();
    }
    public void StartData(){

        //creating shift managers:
        Worker ShiftManager1 = new Worker("manu" , " 1", 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Worker ShiftManager2 = new Worker("david" , " 2", 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
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
        Worker Cashier1 = new Worker("mikel" , " 10", 318 , "ata ahla gever",60, Jobs.Cashier ,"123" );
        Worker Cashier2 = new Worker("migul" , " 11", 318 , "ata ahla gever",60, Jobs.Cashier ,"123" );
        Worker Cashier3 = new Worker("huan" , "12", 318 , "ata ahla gever",60, Jobs.Cashier ,"123" );
        Worker Cashier4 = new Worker("omri-escopar" , "13", 318 , "ata ahla gever",60, Jobs.Cashier ,"123" );
        //puts them in the worker list
        Workers.put("10",Cashier1);
        Workers.put("11",Cashier2);
        Workers.put("12",Cashier3);
        Workers.put("13",Cashier4);

        //create StoreKeeper
        Worker StoreKeeper1 = new Worker("oded" , " 14", 318 , "ata ahla gever",60, Jobs.StoreKeeper ,"123" );
        Worker StoreKeeper2 = new Worker("gal" , " 15", 318 , "ata ahla gever",60, Jobs.StoreKeeper ,"123" );
        Worker StoreKeeper3 = new Worker("papi" , "16", 318 , "ata ahla gever",60, Jobs.StoreKeeper ,"123" );
        Worker StoreKeeper4 = new Worker("hatul" , "17", 318 , "ata ahla gever",60, Jobs.StoreKeeper ,"123" );
        //puts them in the worker list
        Workers.put("14",StoreKeeper1);
        Workers.put("15",StoreKeeper2);
        Workers.put("16",StoreKeeper3);
        Workers.put("17",StoreKeeper4);


        //create GeneralEmployee
        Worker GeneralEmp1 = new Worker("odedi" , " 18", 318 , "ata ahla gever",30, Jobs.GeneralEmp ,"123" );
        Worker GeneralEmp2 = new Worker("shimun" , " 19", 318 , "ata ahla gever",30, Jobs.GeneralEmp ,"123" );
        Worker GeneralEmp3 = new Worker("sara" , "20", 318 , "ata ahla gever",30, Jobs.GeneralEmp ,"123" );
        Worker GeneralEmp4 = new Worker("gebi" , "21", 318 , "ata ahla gever",30, Jobs.GeneralEmp ,"123" );
        //puts them in the worker list
        Workers.put("18",GeneralEmp1);
        Workers.put("19",GeneralEmp2);
        Workers.put("20",GeneralEmp3);
        Workers.put("21",GeneralEmp4);

        //create guards
        Worker Guard1 = new Worker("yuri" , " 22", 318 , "ata ahla gever",50, Jobs.Guard ,"123" );
        Worker Guard2 = new Worker("dor" , " 23", 318 , "ata ahla gever",50, Jobs.Guard ,"123" );
        Worker Guard3 = new Worker("shalev" , "24", 318 , "ata ahla gever",50, Jobs.Guard ,"123" );
        Worker Guard4 = new Worker("harel" , "25", 318 , "ata ahla gever",50, Jobs.Guard ,"123" );
        //puts them in the worker list
        Workers.put("22",Guard1);
        Workers.put("23",Guard2);
        Workers.put("24",Guard3);
        Workers.put("25",Guard4);

        //create cleaner
        Worker Cleaner1 = new Worker("rohama" , " 26", 318 , "ata ahla gever",10, Jobs.Cleaner ,"123" );
        Worker Cleaner2 = new Worker("avraham" , " 27", 318 , "ata ahla gever",10, Jobs.Cleaner ,"123" );
        Worker Cleaner3 = new Worker("shoshana" , "28", 318 , "ata ahla gever",10, Jobs.Cleaner ,"123" );
        Worker Cleaner4 = new Worker("alo" , "29", 318 , "ata ahla gever",10, Jobs.Cleaner ,"123" );
        //puts them in the worker list
        Workers.put("26",Cleaner1);
        Workers.put("27",Cleaner2);
        Workers.put("28",Cleaner3);
        Workers.put("29",Cleaner4);

        //create usher
        Worker Usher1 = new Worker("ahrak" , " 30", 318 , "ata ahla gever",15, Jobs.Usher ,"123" );
        Worker Usher2 = new Worker("midbar" , " 31", 318 , "ata ahla gever",15, Jobs.Usher ,"123" );
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


    public static void setIs_shift_planed_byName(String Name,Boolean is_shift_planed) {
        ReshetInfo.is_shift_planed.remove(Name);
        ReshetInfo.is_shift_planed.put(Name,is_shift_planed);
    }

    public static void setIs_shift_created_byName(String Name,Boolean is_shift_created) {
        ReshetInfo.is_shift_created.remove(Name);
        ReshetInfo.is_shift_created.put(Name,is_shift_created);
    }

    public static Boolean getIs_shift_planed_byName(String Name) {
        return is_shift_planed.get(Name);
    }

    public static Boolean getIs_shift_created_byName(String Name) {
        return is_shift_created.get(Name);
    }

    public static Boolean getIs_workers_in_shiftbyName(String Name) {
        return is_workers_in_shift.get(Name);
    }

    public static void setIs_workers_in_shiftbyName(String Name,Boolean is_workers_in_shift) {
        ReshetInfo.is_workers_in_shift.remove(Name);
        ReshetInfo.is_workers_in_shift.put(Name,is_workers_in_shift);
    }

    //function that checks if the name of a super exists
    public boolean CheckSuperName(String Name){
        return Superim.get(Name) != null;
    }

    public void SetWeeklyPlan(String Name,List<WantShift> ls){
        want_shifts.remove(Name);
        want_shifts.put(Name,ls);
    }

    public static List<WantShift> getWant_shifts_byName(String Name) {
        return want_shifts.get(Name);
    }

    public void PrintDay(String Name, int day){
        //we get the object of the super
        Super curr=Superim.get(Name);
        Weekly week=curr.GetWeekShifts();
        if(week==null){
            System.out.println("nothing in this day yet shift yet");
        }
        else{week.GetShift(day).PrintMe();}
    }

    public void RemoveFromDay(String Name,int day){
        //well get the ID of the one he wants to delete
        System.out.println("please write the ID of the employee you want to remove: ");
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        int loop=-1;
        String input_ID="";
        while (loop==-1){
            input_ID = myObj.nextLine();  // Read user input
            if(!Superim.get(Name).GetWeekShifts().GetShift(day).IsWorkerAtShift(input_ID)){
                System.out.println("this worker doesnt work at this shift - please try again");
            }
            else{
                loop=0;
            }
        }
        //whem were here we have a good number for employee so we remove him
        Superim.get(Name).GetWeekShifts().GetShift(day).RemoveWorker(input_ID);
        Workers.get(input_ID).RemoveShift(Days.values()[day-1]);
    }
    public void AddToDay(String Name,int day,Jobs job){
        //well get the ID oof the one he wants to delete
        System.out.println("please write the ID of the employee you want to add: ");
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        int loop=-1;
        String input_ID="";
        while (loop==-1){
            input_ID = myObj.nextLine();  // Read user input
            // do not forget to change this!!!
            if(Superim.get(Name).GetWeekShifts().GetShift(day).IsWorkerAtShift(input_ID)){
                System.out.println("this worker is already wotks at this shift");
            }
            else{
                loop=0;
            }
        }
        //whem were here we have a good number for employee so we add him
        Superim.get(Name).GetWeekShifts().GetShift(day).AddWorker(job,Workers.get(input_ID));
        // add the shift to the workers shifts - added 31.3
        Workers.get(input_ID).AddShift(Days.values()[day-1]);
    }

    //function that prints current weekly shifts
    public void PrintWeekly(String Name){
        //we get the object of the super
        Super curr=Superim.get(Name);
        Weekly week=curr.GetWeekShifts();
        if(week==null){
            System.out.println("no weekly shift yet");
        }
        else{week.PrintMe();}
    }

    // check if worker is exist by id
    public boolean isExistWorker(String ID){
        return Workers.get(ID) != null;
    }
    public boolean isExistWorkerInSuper(String ID,String Name){
        //we get the object of the super we wanna add a weekly to
        Super curr=Superim.get(Name);
        return curr.GetWorkers().contains(ID);
    }

    //remove a worker from a branch by id
    public void RemoveWorker(String ID,String Name){
        //we get the object of the super we wanna add a weekly to
        Super curr=Superim.get(Name);
        curr.RemoveWorker(ID);
    }

    //function that get day job and a list of workers and checks if the worker can do the work in these conditions
    public Map<String,String> GetAvailableEmployee( Days day, Jobs job,CanWork time, List<String> WorkersID,String SuperName){
    Map<String,String> ret=new HashMap<>();
    //we will save the start and end of the shift
    double start=0;
    double end=0;
    //like that i get the correct hours
    if (time==CanWork.Morning){
        start=Superim.get(SuperName).getStart_morning(day);
        end=Superim.get(SuperName).getEnd_morning(day);
    }
    else{
        start=Superim.get(SuperName).getStart_evening(day);
        end=Superim.get(SuperName).getEnd_evening(day);
    }
    //checking each employrr
        for(String id:WorkersID){
            Worker curr=Workers.get(id);
            //check if he is qualified for the job
            if(curr.CanDoJob(job)){
                //check if he is free by his iluts
                if (curr.IsFree(day,start,end)) {
                    ret.put(id,Workers.get(id).GetName());
                }
            }
        }
        return ret;
    }

    // check if the password is correct by given ID
    public boolean IsTruePassword(String ID,String password){
        if(!isExistWorker(ID)){
            return false;
        }
        return Workers.get(ID).CheckPassword(password);
    }


    public void updatePersonalInfo(String ID){
        if(!isExistWorker(ID)){
            return;
        }
        int op1_choice=-1;
        while (op1_choice!=3){
            System.out.println("hello " +Workers.get(ID).GetName()  + " please choose the detail you want to change");
            System.out.println("1. change password ");
            System.out.println("2. change name");
            System.out.println("3. change bank account");
            System.out.println("4. Back");
            Scanner myObj_worker1 = new Scanner(System.in);  // Create a Scanner object
            String input_worker1 = myObj_worker1.nextLine();  // Read user input
            //try to change the input to a string
            try{
                op1_choice=Integer.parseInt(input_worker1);
            }
            //if he entered something not suitable we will repeat
            catch (Exception e){
                op1_choice =-1;
            }
            switch (op1_choice){
                case 1:
                    System.out.println("please enter new password: ");
                    // get the new password from the worker
                    Scanner myObj_changepass = new Scanner(System.in);  // Create a Scanner object
                    String input_changepass = myObj_changepass.nextLine();  // Read user input
                    Workers.get(ID).SetPassword(input_changepass); // change the password
//                    info.changePassword(ID,input_changepass); // no needed
                    //after we did what we want we stop
                    op1_choice=4;
                    break;
                case 2:
                    System.out.println("please enter new name: ");
                    // get the new password from the worker
                    Scanner myObj_changename = new Scanner(System.in);  // Create a Scanner object
                    String input_changename = myObj_changename.nextLine();  // Read user input
                    Workers.get(ID).SetName(input_changename); // change name
//                    info.changeName(ID,input_changename); // change the name
                    //after we did what we want we stop
                    op1_choice=4;
                    break;
                case 3:
                    System.out.println("please enter new bank details: ");
                    // get the new password from the worker
                    Scanner myObj_changebank = new Scanner(System.in);  // Create a Scanner object
                    String input_changebank = myObj_changebank.nextLine();
                    try
                    {
                        int bankNum=Integer.parseInt(input_changebank);
                        Workers.get(ID).SetBank(bankNum);
//                        info.changeBank(ID,bankNum); // change the bank details
                    }
                    //if he entered something not suitable we will repeat
                    catch (Exception e){
                        op1_choice =-1;
                    }// Read user input
                    //after we did what we want we stop
                    op1_choice=4;
                    break;
                case 4:
                    //stop the loop and go back to previous window
                    op1_choice=3;
                    break;
                default:
                    System.out.println("please enter a valid option");
                    break;
            }
        }
    }


    public void SendIlutsToHistory(String Name){
        //we get the object of the super we wanna add a weekly to
        Super curr=Superim.get(Name);
        curr.SendIlutsToHistory();

    }

    public void UpdateSuperTimes(String Name,Days day, double m_s,double m_e, double e_s, double e_e){
        Super curr=Superim.get(Name);
        curr.setStart_morning(day,m_s);
        curr.setEnd_morning(day,m_e);
        curr.setStart_evening(day,m_s);
        curr.setEnd_evening(day,m_s);
    }

    //cash cancallations
    public void AddCancellations(String Name){
        //we need to verify that the shift manager is the one who is trying to cancel
        boolean flag = true;
        System.out.println("please log in to make sure you are the manager: ");
        String ID="";
        while (flag){
            // got from the use the ID and the password
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("ID: ");
            ID = myObj.nextLine();  // Read user input
            System.out.println("Password: ");
            String passwordInput = myObj.nextLine();  // Read user input
            // check if the worker enter valid inputs
            if(Workers.get(ID)==null || !Workers.get(ID).CanDoJob(Jobs.ShiftManager)){
                System.out.println("invalid input - try again!");
            }
            // if the inputs were valid - call to WorkerOption menu
            else {
                System.out.println("ok fill in the next information please:");
                break;
            }
        }
        flag = true;
        int amount=0;
        System.out.println("what is the amount of the item you want to cancel?");
        while (flag){
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            String input = myObj.nextLine();  // Read user input
            //try to change the input to a string
            try{
                amount=Integer.parseInt(input);
                flag=false;
            }
            //if he entered someting not suitable we will repeate
            catch (Exception e){
                System.out.println("invalid input - try again!");
                amount =0;
            }
        }
        //we got a good amount
        System.out.println("what is the name of the item you want to cancel?");
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String input = myObj.nextLine();  // Read user input
        //create the cancallation
        Cancellations cancel=new Cancellations(amount,input,ID,Workers.get(ID).GetName());
        //add the cancellation to the super
        Superim.get(Name).get_cash_register().AddCancalation(cancel);
    }

    public void AddWeeklyToSuper(String Name,Weekly week){
        Superim.get(Name).AddWeekly(week);
    }

    //print Cancellations of a specific date
    public void PrintCancellation(String Name,int year,int month,int day){
        Superim.get(Name).get_cash_register().PrintCancellation( year, month, day);
    }

    // the function add a new worker
    public void AddNewWorker(String ID, String name, int bank, String contract, int wage, Jobs job, String password,String branchName){
        // create the worker using all the data the manger entered
        Worker new_worker = new Worker(ID, name, bank, contract, wage, job, password);
        for (Map.Entry<String, Super> entry : Superim.entrySet()) {
            if (entry.getKey().equals(branchName)) {
                Superim.get(entry.getKey()).AddWorker(ID);
                Workers.put(ID,new_worker);
                System.out.println(ID + " added successfully to: " + branchName);
            }
        }
    }

    // the function add worker by id to branch
    public void AddWorkerToBranch(String ID,String branch){
        Superim.get(branch).AddWorker(ID);
    }

    // the function removes a worker from all
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

    public void AddJobToWorker(String ID, int job_index){
        Workers.get(ID).AddJob(Jobs.values()[job_index - 1]);
    }

    public void ChangeWage(String ID,int wage){
        Workers.get(ID).setWage(wage);
    }

    public void ChangeContract(String ID, String input_Contract){
        Workers.get(ID).setContract(input_Contract);
    }

    public boolean CheckTimeValidate(double start, double end){
        double start_dec = start - Math.floor(start);
        double end_dec = end - Math.floor(end);
        return start < 0 || start > end || end > 24 || start_dec >= 0.60 || end_dec >= 0.60;
    }

    // calculate salary for worker without a bonus
    public double CalculateMonthlySalary(String ID){
        return Workers.get(ID).getShiftWorked()*Workers.get(ID).getWage();
    }

    // with bonus
    public double CalculateMonthlySalary(String ID,double bonus){
        return Workers.get(ID).getShiftWorked()*Workers.get(ID).getWage() + bonus;
    }

    // reset the number of shifts to all workers
    public void ResetWorkDaysWorkers (){
        for(String ID : Workers.keySet() ){
            Workers.get(ID).ReSetShiftsAmount();
        }
    }


    public void ShowWantsShift(String ID) {
        List<WantShift> relevant_shifts_by_branch = new ArrayList<>();
        // save all the jobs that worker can do
        List<Jobs> worker_jobs = new ArrayList<>();
        worker_jobs = Workers.get(ID).getRoles();
        // iterate over all the supers to show only the one the worker works in
        for(String superName : Superim.keySet() ){
            if(IsWorksInSuper(ID,superName)){
                relevant_shifts_by_branch = want_shifts.get(superName);
                // show only the shifts in the relevant branch
                for(WantShift ws : relevant_shifts_by_branch){
                    for(Jobs job : worker_jobs){
                        // check if the job is in the jobs that the manager needs
                        if(ws.JobInShift(job)){
                            ws.ShowShift();
                        }
                    }
                }
            }
        }
    }
    public Worker GetWorkerByID(String ID){
        return Workers.get(ID);
    }

    // check by id if worker works in specific super
    public boolean IsWorksInSuper(String ID, String SuperName){
        return Superim.get(SuperName).GetWorkers().contains(ID);

    }
}
