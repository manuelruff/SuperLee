import java.time.LocalDate;
import java.util.*;

public class UI {
    // all the info will come form there
    //the creation will create the beginning data
    private static ReshetInfo info=new ReshetInfo();
    //saves the Manager Password - started as 1234 and he can change it
    private static String ManagerPass="1234";
    //we will call this function from the main and all the user interface will be from here
    public static void StartMe(){

        //start the ui here
        int choice=-1;
        while (choice!=3){
            System.out.println("please choose an option: ");
            System.out.println("1. Worker");
            System.out.println("2. Manager");
            System.out.println("3. Cancellations");
            System.out.println("4. Exit");
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            String input = myObj.nextLine();  // Read user input
            //try to change the input to a string
            try{
                choice=Integer.parseInt(input);
            }
            //if he entered someting not suitable we will repeate
            catch (Exception e){
                choice =-1;
            }
            switch (choice){
                case 1:
                    WorkerLogIN();
                    //after we did what we want we stop
                    choice=4;
                    break;
                case 2:
                    ManagerLogIN();
                    //after we did what we want we stop
                    choice=4;
                    break;
                case 3:
                    //whatever a cash register should do
                    CashWork();
                    choice=4;
                    break;
                case 4:
                    System.out.println("have a good day");
                    //stops the program
                    System.exit(0);
                    break;
                default:
                    System.out.println("please enter a valid option");
                    break;
            }
        }
    }

    public static void CashWork(){
        boolean CheckName=false;
        String Name="";
        while (!CheckName){
            System.out.println("enter the name of the branch: ");
            //in the beginning there will be a few options, he is seposed to know them
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            Name = myObj.nextLine();  // Read user input
            CheckName=info.CheckSuperName(Name);
            if (!CheckName){
                System.out.println("this branch doesnt exists, try again ");
            }
            else {
                break;
            }
        }
        //maybe add an option to watch a specific days Cancellations
        int choice=-1;
        while (choice!=3){
            System.out.println("please choose an option: ");
            System.out.println("1. watch cancellations history of a day");
            System.out.println("2. add a cancellations log");
            System.out.println("3. Back");
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            String input = myObj.nextLine();  // Read user input
            //try to change the input to a string
            try{
                choice=Integer.parseInt(input);
            }
            //if he entered someting not suitable we will repeate
            catch (Exception e){
                choice =-1;
            }
            switch (choice){
                case 1:
                    boolean check=true;
                    int Year=0;
                    int Month=0;
                    int Day=0;
                    while(check){
                        //we need to get the date he want to watch
                        System.out.println("please enter year: ");
                        myObj = new Scanner(System.in);  // Create a Scanner object
                        String year = myObj.nextLine();  // Read user input
                        System.out.println("please enter month: ");
                        String month = myObj.nextLine();  // Read user input
                        System.out.println("please enter day: ");
                        String day = myObj.nextLine();  // Read user input
                        //try to change the input to a string
                        try{
                            Year=Integer.parseInt(year);
                            Month=Integer.parseInt(month);
                            Day=Integer.parseInt(day);
                            //stop the loop
                            check=false;
                        }
                        //if he entered someting not suitable we will repeate
                        catch (Exception e){
                            System.out.println("please enter a valid date ");
                        }
                    }
                    //if we get here we have numbers for the date, well send it and see what we get back
                    info.PrintCancellation(Name,Year,Month,Day);
                    //after we did what we want we stop
                    choice=4;
                    break;
                case 2:
                    // once we now the name of the branch we need to ask what he wanna do with it
                    info.AddCancellations(Name);
                    //after we did what we want we stop
                    choice=4;
                    break;
                case 3:
                    choice=3;
                    break;
                default:
                    System.out.println("please enter a valid option");
                    break;
            }
        }

    }

    public static void WorkerLogIN(){
        boolean flag = true;
        System.out.println("please log in: ");
        while (flag){
            // got from the use the ID and the password
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("ID: ");
            String ID = myObj.nextLine();  // Read user input
            System.out.println("Password: ");
            String passwordInput = myObj.nextLine();  // Read user input
            // check if the worker enter valid inputs
            if(!info.isExistWorker(ID) || !info.IsTruePassword(ID,passwordInput)){
                System.out.println("invalid input - try again!");
            }
            // if the inputs were valid - call to WorkerOption menu
            else {
                WorkerOption(ID);
                flag = false;
                break;
            }
        }
    }

    public static void WorkerOption(String ID){
        //start the worker options here - Omri's Code!
        int choice=-1;
        while (choice!=4){
            System.out.println("hello please choose your option: ");
            System.out.println("1. update personal details ");
            System.out.println("2. create Iluts ");
            System.out.println("3. remove Iluts ");
            System.out.println("4. show Iluts ");
            System.out.println("5. Exit");
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            String input = myObj.nextLine();  // Read user input
            //try to change the input to a string
            try{
                choice=Integer.parseInt(input);
            }
            //if he entered something not suitable we will repeat
            catch (Exception e){
                choice =-1;
            }
            switch (choice){
                case 1:
                    //if the worker want to change details
                    info.updatePersonalInfo(ID);
                    //after we did what we want we stop
                    break;
                case 2:
                    //after we did what we want we stop
                    break;
                case 3:
                    //do remove ilutz here
                    break;
                case 4:
                    //after we did what we want we stop
                    break;
                case 5:
                    System.out.println("have a good day");
                    //stops the program
                    System.exit(0);
                    break;
                default:
                    System.out.println("please enter a valid option");
                    break;
            }
        }

    }

    public static void ManagerLogIN(){
        int choice=-1;
        System.out.println("please log in: ");
        while (choice!=3){
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Password: ");
            String input = myObj.nextLine();  // Read user input
            if(!input.equals(ManagerPass)){
                System.out.println("Wrong password please try again");
            }
            else{
                System.out.println("welcome MS BIG BOSS, what would you like to do today?");
                //we need to call the functionality for manager options
                ManagerOptions();
                //so we leave the while loop
                choice=3;
            }
        }
    }

    //window with everything the manager can do
    public static void ManagerOptions(){
        int choice=-1;
        while (choice!=3){
            System.out.println("please choose your action: ");
            System.out.println("1. work on a branch (snif)");
            System.out.println("2. update employee ");
            System.out.println("3. change password ");
            System.out.println("4. Exit ");
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            String input = myObj.nextLine();  // Read user input
            //try to change the input to a string
            try{
                choice=Integer.parseInt(input);
            }
            //if he entered someting not suitable we will repeate
            catch (Exception e){
                choice =-1;
            }
            switch (choice){
                case 1:
                    boolean CheckName=false;
                    String Name="";
                    while (!CheckName){
                        System.out.println("enter the name of the branch: ");
                        //in the beginning there will be a few options, he is seposed to know them
                        Name = myObj.nextLine();  // Read user input
                        CheckName=info.CheckSuperName(Name);
                        if (!CheckName){
                            System.out.println("this branch doesnt exists, try again ");
                        }
                        else {
                            break;
                        }
                    }
                    // once we now the name of the branch we need to ask what he wanna do with it
                    int choice2=-1;
                    while (choice2!=3) {//there probably is a problem here with the loop check that later
                        System.out.println("please choose your action at the branch: ");
                        System.out.println("1. plan weekly shift");
                        System.out.println("2. create weekly shift");
                        System.out.println("3. update day in weekly shift");
                        System.out.println("4. show current shift");
                        System.out.println("5. send shift to history");
                        System.out.println("6. watch history");
                        System.out.println("7. remove worker from this super");
                        System.out.println("8. update super shift times");
                        System.out.println("9. Exit ");
                        myObj = new Scanner(System.in);  // Create a Scanner object
                        input = myObj.nextLine();  // Read user input
                        //try to change the input to a string
                        try {
                            choice2 = Integer.parseInt(input);
                        }
                        //if he entered someting not suitable we will repeate
                        catch (Exception e) {
                            choice2 = -1;
                        }
                        switch (choice2) {
                            case 1:
                                //create list of the what he wants
                                List<WantShift> ls=new ArrayList<>();
                                //create the weekly plan
                                for (Days day:Days.values()) {
                                    CanWork time = CanWork.Morning;
                                    for (int j=0;j<2;j++) {
                                        WantShift curr = new WantShift(day, time);
                                        //create a list of jobs so we can present it to the manager
                                        List<Jobs> job_list = new ArrayList<>();
                                        for (Jobs job : Jobs.values()) {
                                            //there will be only one shift manager so we wont save it
                                            if (job!=Jobs.ShiftManager) {
                                                job_list.add(job);
                                            }
                                        }
                                        int job_chose = -1;
                                        while (job_chose == -1) {
                                            System.out.println("please choose your the job you want to add to the shift: ");
                                            //prints options
                                            for (int i = 0; i < job_list.size(); i++) {
                                                System.out.println(i + "." + job_list.get(i));
                                            }
                                            String job_s = myObj.nextLine();  // Read user input
                                            System.out.println("please enter the amount you want from that job: ");
                                            String num_s = myObj.nextLine();  // Read user input
                                            //try to change the input to a string
                                            try {
                                                job_chose = Integer.parseInt(job_s);
                                                int num_choose = Integer.parseInt(num_s);
                                                if (job_chose > job_list.size()) {
                                                    job_chose = -1;
                                                    System.out.println("not valid input, please try again");
                                                } else {
                                                    curr.add_m_wants(job_list.get(job_chose), num_choose);
                                                }
                                            }
                                            //if he entered someting not suitable we will repeate
                                            catch (Exception e) {
                                                job_chose = -1;
                                                System.out.println("not valid input, please try again");
                                            }
                                        }
                                        //add the WantShift to the list of shifts
                                        ls.add(curr);
                                        //switch between morning and evening
                                        time = CanWork.Evening;
                                    }
                                }
                                //sends the info to reshet info
                                info.SetWeeklyPlan(Name,ls);
                                break;
                            case 2:
                                //create the weekly plan
                                Weekly week=new Weekly();
                                //get the options for the shift
                                List<WantShift> ls2=info.getWant_shifts_byName(Name);
                                //we will save here a list of workers that can work by certain parametesrs
                                Map<String,String>free_workers;
                                //go threw all of them and create real shifts
                                for (WantShift want: ls2) {
                                    //create the current shift
                                    Shift curr=new Shift(LocalDate.now().plusDays(want.getDay().ordinal()),want.getMorning_evening());
                                    //start choosing workers
                                    System.out.println("first you need to chose the manager for the shift: ");
                                    //update the list of available managers for this shift
                                    free_workers=info.GetAvailableEmployee(want.getDay(),Jobs.ShiftManager,want.getMorning_evening(),want.getW_wants(),Name);
                                    
                                }

                                //create the weekly shifts
                                //info.CreateWeekly(Name);
                                break;
                            case 3:
                                //get the day he wants to edit
                                int day_choice = -1;
                                while (day_choice != 8) {
                                    System.out.println("please enter the number of the day which you want to change \n" +
                                            "Sunday-1 , Monday-2, Tuesday-3, Wednesday-4, Thursday-5, Friday-6, Saturday-7 \n"+
                                            "if you want to exit - press 8 ");
                                    myObj = new Scanner(System.in);  // Create a Scanner object
                                    String input_day = myObj.nextLine();  // Read user input
                                    try{
                                        day_choice=Integer.parseInt(input_day);
                                        if(day_choice<0 || day_choice>7){
                                            System.out.println("please enter a valid number ");
                                        }
                                        else{
                                            day_choice=8;
                                        }
                                    }
                                    //if he entered something not suitable we will repeat
                                    catch (Exception e){
                                        System.out.println("please enter a valid number ");
                                        day_choice =-1;
                                    }
                                }
                                //prints this days info
                                System.out.println("the shift in this day looks like: ");
                                //print the shift og the day he chose
                                info.PrintDay(Name,day_choice);
                                //ask if he wants to add someone, remove or replace
                                int ActionChoice = -1;
                                while (ActionChoice != 8) {
                                    System.out.println("please choose the action you want to do: \n" +
                                            "1. remove an employee  \n"+
                                            "2. add an employee");
                                    myObj = new Scanner(System.in);  // Create a Scanner object
                                    String input_action = myObj.nextLine();  // Read user input
                                    try{
                                        ActionChoice=Integer.parseInt(input_action);
                                        if(ActionChoice<0 || ActionChoice>3){
                                            System.out.println("please enter a valid number ");
                                        }
                                        else{
                                            ActionChoice=8;
                                        }
                                    }
                                    //if he entered something not suitable we will repeat
                                    catch (Exception e){
                                        System.out.println("please enter a valid number ");
                                        ActionChoice =-1;
                                    }
                                }
                                //if i got here i have a good option
                                switch (ActionChoice){
                                    case 1:
                                        info.RemoveFromDay(Name,day_choice);
                                        break;
                                    case 2:
                                        //now i need to have a job tytpe so i can add someone so i need to think how i do it
                                        //or just ask for it
                                        //info.AddToDay(Name,day_choice);
                                        break;
                                }

                                break;
                            case 4:
                                //print the current weekly plan
                                info.PrintWeekly(Name);
                                break;
                            case 5:
                                //send the current iluts to history and then you have to create new one
                                info.SendIlutsToHistory(Name);
                                break;
                            case 6:

                                break;
                            case 7:
                                //we need to ask for the ID of the employee he wants to remove from the branch
                                CheckName=false;
                                String ID="";
                                while (!CheckName){
                                    System.out.println("enter the ID of the employee: ");
                                    ID = myObj.nextLine();  // Read user input
                                    //check if the ID is in the super / branch
                                    CheckName=info.isExistWorkerInSuper(ID,Name);
                                    if (!CheckName){
                                        System.out.println("this branch doesnt have an employee with this ID, try again ");
                                    }
                                    else {
                                        info.RemoveWorker(ID,Name);
                                        break;
                                    }
                                }
                                break;
                            case 8:
                                int day_choice1=0;
                                int choice4 = -1;
                                while (choice4 != 8) {
                                    boolean day_flag=true;
                                    //loop to get the day to change
                                    while(day_flag){
                                        System.out.println("please enter the number of the day which you want to change working hoursd in \n" +
                                                "Sunday-1 , Monday-2, Tuesday-3, Wednesday-4, Thursday-5, Friday-6, Saturday-7 ");
                                        Scanner day_input = new Scanner(System.in); // Create a Scanner object
                                        String input_day = day_input.nextLine();  // Read user input
                                        try{
                                            day_choice1=Integer.parseInt(input_day);
                                            if (day_choice1<1 || day_choice1>7){
                                                System.out.println("not valid, please try again");
                                            }
                                            else {
                                                day_flag=false;
                                            }
                                        }
                                        //if he entered something not suitable we will repeat
                                        catch (Exception e){
                                            System.out.println("not valid, please try again");
                                        }
                                    }
                                    //change shift hours
                                    //he will change both morning and evening so they wont overlap
                                    System.out.println("please enter time to start morning shift (it needs to look like: 10.00 for 10am): ");
                                    Scanner time_input = new Scanner(System.in);  // Create a Scanner object
                                    String input_iluts = time_input.nextLine();  // Read user input
                                    System.out.println("please enter time to end morning shift (it needs to look like: 22.00 for 22pm): ");
                                    time_input = new Scanner(System.in);  // Create a Scanner object
                                    String input_iluts_2 = time_input.nextLine();  // Read user input
                                    System.out.println("please enter time to start evening shift (it needs to look like: 22.00 for 22pm): ");
                                    time_input = new Scanner(System.in);  // Create a Scanner object
                                    String input_iluts_3 = time_input.nextLine();  // Read user input
                                    System.out.println("please enter time to end evening shift (it needs to look like: 22.00 for 22pm): ");
                                    time_input = new Scanner(System.in);  // Create a Scanner object
                                    String input_iluts_4 = time_input.nextLine();  // Read user input
                                    try {
                                        // check if the iluts number is valid
                                        double iluts_num_morning_start = Double.parseDouble(input_iluts);
                                        double iluts_num_morning_end = Double.parseDouble(input_iluts_2);
                                        double iluts_num_evening_start = Double.parseDouble(input_iluts_3);
                                        double iluts_num_evening_end = Double.parseDouble(input_iluts_4);
                                        if(info.CheckTimeValidate(iluts_num_morning_start, iluts_num_morning_end) || info.CheckTimeValidate(iluts_num_evening_start, iluts_num_evening_end)){
                                            System.out.println("invalid time input - hours should be between 10.00-24.59 (note that seconds is .0-.59!");
                                        }
                                        else {
                                            // if the times are valid ill send them to the super for update
                                            info.UpdateSuperTimes(Name, Days.values()[day_choice1 - 1], iluts_num_morning_start, iluts_num_morning_end, iluts_num_evening_start, iluts_num_evening_end);
                                            //we can stop the loop
                                            choice4 = 8;
                                            break;
                                        }
                                    }
                                    //if he entered something not suitable we will repeat
                                    catch (Exception e) {
                                        System.out.println("invalid input, please try again");
                                    }
                                }
                                break;
                            case 9:
                                choice2=3;
                                break;
                            default:
                                System.out.println("please enter a valid option");
                                break;
                            }
                        }
                    //after we did what we want we stop
                    choice=4;
                    break;
                case 2:
                    choice2 = -1;
                    while (choice2 != 7) {
                        String ID;
                        System.out.println("hello manager, please choose what you want to do: ");
                        System.out.println("1. add new worker");
                        System.out.println("2. add worker to branch");
                        System.out.println("3. remove worker");
                        System.out.println("4. add job for worker");
                        System.out.println("5. change worker wage");
                        System.out.println("6. change worker contract");
                        System.out.println("7. Back");
                        Scanner myObj_worker1 = new Scanner(System.in);  // Create a Scanner object
                        String input_worker1 = myObj_worker1.nextLine();  // Read user input
                        //try to change the input to a string
                        try {
                            choice2 = Integer.parseInt(input_worker1);
                        }
                        //if he entered something not suitable we will repeat
                        catch (Exception e) {
                            choice = -1;
                        }
                        switch (choice2) {
                            case 1:
                                boolean new_id_flag = false;
                                String input_newID = "";
                                // as long as the id is not new - keep asking the manager to add one
                                while(!new_id_flag){
                                    System.out.println("please enter the new worker's ID");
                                    // get the new id from the manager
                                    Scanner myObj_newID = new Scanner(System.in);  // Create a Scanner object
                                    input_newID = myObj_newID.nextLine();  // Read user input
                                    if (info.isExistWorker(input_newID)) {
                                        System.out.println("this worker is already working at our markets");
                                    }
                                    else {
                                        new_id_flag = true;
                                    }
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
                                // create a generic password for the new worker
                                String generic_Password = "1111";
                                boolean BranchCheck=false;
                                String BranchName="";
                                while (!BranchCheck){
                                    System.out.println("please enter the branch you want to add the new worker: ");
                                    //in the beginning there will be a few options, he is supposed to know them
                                    BranchName = myObj.nextLine();  // Read user input
                                    BranchCheck=info.CheckSuperName(BranchName);
                                    if (!BranchCheck){
                                        System.out.println("this branch doesn't exists, try again ");
                                    }
                                    else {
                                        break;
                                    }
                                }
                                // create a new worker and add him to the asked branch
                                info.AddNewWorker(input_newID,input_newName,bankNum,input_newContract,wage,Jobs.values()[role_choice-1],generic_Password,BranchName);
                                break;
                            case 2:
                                ID = AskForWorkerID();
                                info.AddWorkerToBranch(ID);
                                break;
                            case 3:
                                ID = AskForWorkerID();
                                info.RemoveWorkerAllBranches(ID);
                                break;
                            case 4:
                                ID = AskForWorkerID();
                                info.AddJobToWorker(ID);
                                break;
                            case 5:
                                ID = AskForWorkerID();
                                info.ChangeWage(ID);
                                break;
                            case 6:
                                ID = AskForWorkerID();
                                info.ChangeContract(ID);
                                break;
                            case 7:
                                choice = 3;
                                break;
                            default:
                                System.out.println("please enter a valid option");
                                break;
                        }
                    }
                    //after we did what we want we stop
                    choice=4;
                    break;
                case 3:
                    System.out.println("enter new password: ");
                    Scanner scanner1 = new Scanner(System.in);  // Create a Scanner object
                    String pass1 = scanner1.nextLine();  // Read user input
                    ManagerPass=pass1;
                    //after we did what we want we stop
                    System.out.println("password changed. ");
                    break;
                case 4:
                    System.out.println("have a good day");
                    //stops the program
                    System.exit(0);
                    break;
                default:
                    System.out.println("please enter a valid option");
                    break;
            }
        }
    }

    public static String AskForWorkerID(){
        System.out.println("please enter the worker's ID: ");
        // get the new id from the manager
        Scanner myObj_newID = new Scanner(System.in);  // Create a Scanner object
        return myObj_newID.nextLine();
        }
}