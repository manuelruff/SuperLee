import java.time.LocalDate;
import java.util.*;

public class ReshetInfo {
    //all the branches of the company
    private static Map<String ,Super> Superim;
    //all the workers in the company
    private static Map<String ,Worker> Workers;
    //every week the managers will fill their desires for workers and it will be saved here
    private static Map<String,List<WantShift>> wnt_shifts;
    //builder for reshetinfo
    public ReshetInfo(){

        Superim=new HashMap<>();
        Workers=new HashMap<>();
        wnt_shifts=new HashMap<>();
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

    //function that checks if the name of a super exists
    public boolean CheckSuperName(String Name){
        return Superim.get(Name) != null;
    }

    //function that creates weeklysheet for a super
    public void CreateWeekly(String Name){
        //we get the object of the super we wanna add a weekly to
        Super curr=Superim.get(Name);
        List<String> CanWorkList;
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        //create the weekly
        Weekly week=new Weekly();
        for (Days day:Days.values()){
            CanWork time=CanWork.Morning;
            for(int i=0;i<2;i++) {
                System.out.println("we are working on: " + day + " at the "+time+ " shift");
                //first we need to choose the manager to start the shift
                System.out.println("first choose the shift manager: ");
                //ill get the list of available managers
                CanWorkList=GetAvailableEmployee(day, Jobs.ShiftManager, time, curr.GetWorkers(),curr.GetName());
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
                // ineed to figure our how to work with date later on..
                //crete the shift and now we need to add to it
                Shift CurrShift=new Shift(LocalDate.now().plusDays(day.ordinal()),time,CanWorkList.get(num-1),Workers.get(CanWorkList.get(num-1)).GetName());
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
                    time=CanWork.Evening;
                }
            }
        //add the weekly to the super
        curr.AddWeekly(week);
        }

    public void PrintDay(String Name,int day){
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
    public void AddToDay(String Name,int day){
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
        Superim.get(Name).GetWeekShifts().GetShift(day).AddWorker(input_ID,Workers.get(input_ID).GetName());
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
    public List<String> GetAvailableEmployee(Days day, Jobs job,CanWork time, List<String> WorkersID,String SuperName){
    List<String> ret=new ArrayList<>();
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
                    ret.add(id);
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

    //print Cancellations of a specific date
    public void PrintCancellation(String Name,int year,int month,int day){
        Superim.get(Name).get_cash_register().PrintCancellation( year, month, day);
    }

    // the function add a new worker
    public void AddNewWorker(){
        boolean is_exist_branch = false;
        System.out.println("please enter the new worker's ID");
        // get the new id from the manager
        Scanner myObj_newID = new Scanner(System.in);  // Create a Scanner object
        String input_newID = myObj_newID.nextLine();  // Read user input
        if (isExistWorker(input_newID)) {
            System.out.println("this worker is already working at our markets");
            return;
        }
        // if the worker doesn't exist - get the other info from the manager
        System.out.println("please enter the new worker's name");
        Scanner myObj_newName = new Scanner(System.in);  // Create a Scanner object
        String input_newName = myObj_newName.nextLine();  // Read user input
        boolean bank_flag = false;
        int bankNum = 0;
        while (!bank_flag) {
            System.out.println("please enter the new worker's bank number");
            Scanner myObj_newBank = new Scanner(System.in);  // Create a Scanner object
            String input_newBank = myObj_newBank.nextLine();  // Read user input
            try {
                bankNum = Integer.parseInt(input_newBank);
                bank_flag = true;
            }
            //if he entered something not suitable we will repeat
            catch (Exception e) {
                System.out.println("you entered wrong bank number (should be only numbers) - please try again!");
            }
        }
        System.out.println("please enter the new worker's contract");
        Scanner myObj_newContract = new Scanner(System.in);  // Create a Scanner object
        String input_newContract = myObj_newContract.nextLine();  // Read user input
        boolean wage_flag = false;
        int wage = 0;
        while (!wage_flag) {
            System.out.println("please enter the new worker's wage");
            Scanner myObj_newWage = new Scanner(System.in);  // Create a Scanner object
            String input_newWage = myObj_newWage.nextLine();  // Read user input
            try {
                wage = Integer.parseInt(input_newWage);
                wage_flag = true;
            }
            //if he entered something not suitable we will repeat
            catch (Exception e) {
                System.out.println("you entered wrong wage - please try again!");
            }
        }
        boolean role_flag = false;
        int role_choice = 0;
        while (!role_flag) {
            System.out.println("please enter the new worker's first role: \n" +
                    "ShiftManager-1 , Cashier-2, StoreKeeper-3, GeneralEmp-4, Guard-5, Cleaner-6, Usher-7");
            Scanner myObj_Role = new Scanner(System.in);  // Create a Scanner object
            String input_role = myObj_Role.nextLine();  // Read user input
            try {
                role_choice = Integer.parseInt(input_role);
                if (role_choice < 0 || role_choice > 8) {
                    System.out.println("not valid, please try again");
                    continue;
                }
                role_flag = true;
            }
            //if he entered something not suitable we will repeat
            catch (Exception e) {
                System.out.println("you entered wrong role option - please try again!");
            }
        }
        String generic_Password = "1111";
        // create the worker using all the data the manger entered
        Worker new_worker = new Worker(input_newName, input_newID, bankNum, input_newContract, wage, Jobs.values()[role_choice - 1], generic_Password);
        // add the new worker to the needed branches
        System.out.println("please enter the branch you want to add the new worker: \n" +
                "this are our branches - please enter the branch name exactly as written");
        int j = 0;
        for (Map.Entry<String, Super> entry : Superim.entrySet()) {
            System.out.println((j + 1) + ". " + entry.getKey());
            j++;
        }
        int branch_choice = -1;
        // the flag checks if the input of the manager is valid branch name
        // the flag checks if the worker is already working in the given branch
        boolean worker_in_branch = false;
        while (branch_choice == -1) {
            Scanner myObj_Branch = new Scanner(System.in);  // Create a Scanner object
            String inputBranchName = myObj_Branch.nextLine();  // Read user input
            // check if the branch name is existed in the superim list
            for (Map.Entry<String, Super> entry : Superim.entrySet()) {
                if (entry.getKey().equals(inputBranchName)) {
                    is_exist_branch = true;
                    branch_choice = 999;
                    // add the new worker to the selected branch
                    Superim.get(entry.getKey()).AddWorker(input_newID);
                    Workers.put(input_newID,new_worker);
                    break;
                }
            }
            if(!is_exist_branch){
                System.out.println("please enter valid super name");
            }
        }
        if(is_exist_branch){
            System.out.println(input_newID + " added successfully to the superim");
        }

    }

    // the function add worker by id to branch
    public void AddWorkerToBranch(String ID){
        // flag to check if the branch is existed
        boolean is_exist_branch = false;
        if (!isExistWorker(ID)) {
            System.out.println("this id is not working at our markets");
            return;
        }
        System.out.println("please enter the branch you want to add the worker: \n" +
                "this are our branches - please enter the branch name exactly as written");
        int j = 0;
        for (Map.Entry<String, Super> entry : Superim.entrySet()) {
            System.out.println((j + 1) + ". " + entry.getKey());
            j++;
        }
        Scanner myObj_Branch = new Scanner(System.in);  // Create a Scanner object
        String inputBranchName = myObj_Branch.nextLine();  // Read user input
        // check if the branch name is existed in the superim list
        for (Map.Entry<String, Super> entry : Superim.entrySet()) {
            if (entry.getKey().equals(inputBranchName)) {
                is_exist_branch = true;
                // check if the worker is already working in the branch
                for (int i = 0; i < Superim.get(entry.getKey()).GetWorkers().size(); i++) {
                    if (ID.equals(Superim.get(entry.getKey()).GetWorkers().get(i))) {
                        System.out.println("the worker is already in this branch");
                        return;
                    }
                    // add the worker to the workers map
                    Superim.get(entry.getKey()).AddWorker(ID);
                    System.out.println("worker added successfully");
                }
            }
        }
        // if the branch doesn't exist
        if (!is_exist_branch) {
            System.out.println("this branch doesn't exist - please enter the accurate name!");
        }
    }

    // the function removes a worker from all
    public void RemoveWorkerAllBranches(String ID){
        // first check if the worker is existed
        if (!isExistWorker(ID)) {
            System.out.println("this worker is not working at our markets");
            return;
        }
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

    public void AddJobToWorker(String ID){
        int role_choice;
        // do the loop as long as the flag is false - it means the input was invalid
        boolean validInput = false;
        if (!isExistWorker(ID)) {
            System.out.println("this worker is not working at our markets");
            return;
        }
        while(!validInput){
            System.out.println("please enter the new worker's first role: \n" +
                    "ShiftManager-1 , Cashier-2, StoreKeeper-3, GeneralEmp-4, Guard-5, Cleaner-6, Usher-7");
            Scanner myObj_Role = new Scanner(System.in);  // Create a Scanner object
            String input_role = myObj_Role.nextLine();  // Read user input
            try {
                // check the valdity of the role input
                role_choice = Integer.parseInt(input_role);
                if (role_choice < 0 || role_choice > 8) {
                    System.out.println("not valid, please try again");
                    continue;
                }
                // if the input is valid - add the job to all of his optional jobs
                Workers.get(ID).AddJob(Jobs.values()[role_choice - 1]);
                validInput = true;
            }
            //if he entered something not suitable we will repeat
            catch (Exception e) {
                System.out.println("you entered wrong role option - please try again!");
            }
        }
    }

    public void ChangeWage(String ID){
        if (!isExistWorker(ID)) {
            System.out.println("this worker is not working at our markets");
            return;
        }
        boolean validInput = false;
        while(!validInput){
            System.out.println("please enter the wage: ");
            Scanner myObj_Wage = new Scanner(System.in);  // Create a Scanner object
            String input_Wage = myObj_Wage.nextLine();  // Read user input
            // check if the wage is number
            try {
                int wage_choice = Integer.parseInt(input_Wage);
                Workers.get(ID).setWage(wage_choice);
                validInput = true;
            }
            //if he entered something not suitable we will repeat
            catch (Exception e) {
                System.out.println("you entered wrong wage - please try again!");
            }
        }
    }

    public void ChangeContract(String ID){
        if (!isExistWorker(ID)) {
            System.out.println("this worker is not working at our markets");
            return;
        }
        System.out.println("please enter the contract: ");
        Scanner myObj_Contract = new Scanner(System.in);  // Create a Scanner object
        String input_Contract = myObj_Contract.nextLine();  // Read user input
        Workers.get(ID).setContract(input_Contract);
    }

    public boolean CheckTimeValidate(double start, double end){
        double start_dec = start - Math.floor(start);
        double end_dec = end - Math.floor(end);
        return start < 0 || start > end || end > 24 || start_dec >= 0.60 || end_dec >= 0.60;
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
            Workers.get(ID).ReSetShiftsAmount();
        }
    }
}
