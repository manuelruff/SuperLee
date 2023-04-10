import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class UI {
    // all the info will come form there
    //the creation will create the beginning data
    private static ReshetInfo info=new ReshetInfo();
    //saves the Manager Password - started as 1234 and he can change it
    private static String ManagerPass="1234";

    /**
     * entering screen, the user will choose his role or if he want to add cancellation to cash
     */
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
            //if he entered something not suitable we will repeat
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

    /**
     * option for the cashregistry
     */
    public static void CashWork(){
        String Name=AskForBranch();
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
            //if he entered something not suitable we will repeat
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
                        //if he entered something not suitable we will repeat
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
                    String ID = AskForWorkerID();
                    boolean isShiftManager = false;
                    while(!isShiftManager){
                        if(!info.CanDoJob(ID,Jobs.ShiftManager)){
                            System.out.println("access denied - this action is for Shift Manager only!");
                            continue;
                        }
                        isShiftManager = true;
                    }
                    // if he's a shift manager
                    System.out.println("please enter the name of the item which you want to cancel: ");
                    myObj = new Scanner(System.in);  // Create a Scanner object
                    String item = myObj.nextLine();  // Read user input
                    System.out.println("please enter the amount of the item which you want to cancel: ");
                    double amount = AskForDoubleNumber();
                    info.AddCancellations(Name,item,amount,ID);
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

    /**
     * worker needs to log in and it will be checked here
     */
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

    /**
     *  will open the options for workers
     * @param ID make the changes on the worker with given id
     */
    public static void WorkerOption(String ID){
        //start the worker options here - Omri's Code!
        int choice=-1;
        while (choice!=4){
            System.out.println("hello please choose your option: ");
            System.out.println("1. update personal details ");
            System.out.println("2. add new constraints ");
            System.out.println("3. remove constraints ");
            System.out.println("4. show constraints ");
            System.out.println("5. Exit");
            //ask for input
            choice=AskForNumber(1,5);
            switch (choice){
                case 1:
                    int op1_choice=-1;
                    while (op1_choice!=6) {
                        System.out.println("hello " + info.GetWorkerByID(ID).GetName() + " please choose the detail you want to change");
                        System.out.println("1. change password ");
                        System.out.println("2. change name");
                        System.out.println("3. change bank account");
                        System.out.println("4. add new personal information");
                        System.out.println("5. remove personal information");
                        System.out.println("6. Back");
                        op1_choice = AskForNumber(1,6);
                        switch (op1_choice){
                            case 1:
                                System.out.println("please enter new password:");
                                // get the new password from the worker
                                Scanner myObj_changepass = new Scanner(System.in);  // Create a Scanner object
                                String input_changepass = myObj_changepass.nextLine();  // Read user input
                                // change it to function in reshet info
                                info.ChangeWorkerPassword(ID,input_changepass); // change the password
                                //after we did what we want we stop
                                op1_choice=6;
                                break;
                            case 2:
                                System.out.println("please enter new name:");
                                // get the new password from the worker
                                Scanner myObj_changename = new Scanner(System.in);  // Create a Scanner object
                                String input_changename = myObj_changename.nextLine();  // Read user input
                                info.ChangeWorkerName(ID,input_changename);// change name
                                //after we did what we want we stop
                                op1_choice=6;
                                break;
                            case 3:
                                System.out.println("please enter new bank details:");
                                int newBank = AskForIntNumber();
                                info.ChangeWorkerBank(ID,newBank );
                                //after we did what we want we stop
                                op1_choice=6;
                                break;
                            case 4:
                                System.out.println("please enter the reason about your information:");
                                Scanner myObj = new Scanner(System.in);  // Create a Scanner object
                                String reason = myObj.nextLine();  // Read user input
                                System.out.println("please enter more details about the new information:");
                                String information = myObj.nextLine();  // Read user input
                                info.addNewInfo(ID,reason,information);
                                //after we did what we want we stop
                                op1_choice=6;
                                break;
                            case 5:
                                System.out.println("please enter the reason about your information:");
                                myObj = new Scanner(System.in);  // Create a Scanner object
                                String remove_reason = myObj.nextLine();  // Read user input
                                info.removeInfo(ID,remove_reason);
                                //after we did what we want we stop
                                op1_choice=6;
                                break;
                            case 6:
                                //stop the loop and go back to previous window
                                op1_choice=6;
                                break;
                            default:
                                System.out.println("please enter a valid option");
                                break;
                        }

                    }
                    //after we did what we want we stop
                    break;
                case 2:
                    int day_choice = -1;
                    while (day_choice != 8) {
                        System.out.println("please enter the number of the day which you want to add constraints in \n" +
                                "Sunday-1 , Monday-2, Tuesday-3, Wednesday-4, Thursday-5, Friday-6, Saturday-7 \n"+
                                "if you want to exit - press 8 ");
                        day_choice = AskForNumber(1,8);
                        switch (day_choice){
                            case 1: case 2: case 3: case 4: case 5: case 6: case 7:
                                System.out.println("please enter the start of the time that you cant work at (it needs to look like: 10.00 for 10): ");
                                double Constraints_num_start=AskForDoubleNumber();
                                System.out.println("please enter the end of the time that you cant work at (it needs to look like: 10.00 for 10): ");
                                double Constraints_num_end=AskForDoubleNumber();
                                if (info.CheckTimeValidate(Constraints_num_start, Constraints_num_end)){
                                    System.out.println("not valid, please try again");
                                    break;
                                }
                                Scanner myConstraints=new Scanner(System.in);// create scanner
                                System.out.println("please enter reason:");
                                String reason=myConstraints.nextLine();  // Read user input
                                // if the Constraints is valid - add the Constraints at the day the user gave
                                info.AddConstraints(ID,day_choice,Constraints_num_start,Constraints_num_end,reason);
                                //stop the loop
                                day_choice =8;
                                break;
                                // exit option
                            case 8:
                                System.out.println("thank you for updating your Constraintss");
                                return;
                            // chosen wrong number of day
                            default:
                                System.out.println("invalid input, please try again");
                                break;
                        }
                    }
                    //after we did what we want we stop
                    break;
                case 3:
                    day_choice = -1;
                    while (day_choice != 8) {
                        System.out.println("please enter the number of the day which you want to add constraints in \n" +
                                "Sunday-1 , Monday-2, Tuesday-3, Wednesday-4, Thursday-5, Friday-6, Saturday-7 \n"+
                                "if you want to exit - press 8 ");
                        day_choice = AskForNumber(1,8);
                        switch (day_choice){
                            case 1: case 2: case 3: case 4: case 5: case 6: case 7:
                                System.out.println("please enter the start of the time that you cant work at (it needs to look like: 10.00 for 10am): ");
                                double Constraints_num_start=AskForDoubleNumber();
                                System.out.println("please enter the end of the time that you cant work at (it needs to look like: 22.00 for 22pm): ");
                                double Constraints_num_end=AskForDoubleNumber();
                                // check if the Constraints number is valid
                                if (info.CheckTimeValidate(Constraints_num_start, Constraints_num_end)){
                                    System.out.println("not valid, please try again");
                                    break;
                                }
                                // if the Constraints is valid - remove the Constraints at the day the user gave if exists
                                info.RemoveConstraints(ID,day_choice,Constraints_num_start,Constraints_num_end);
                                day_choice =8;
                                break;
                                // exit option
                            case 8:
                                System.out.println("thank you for updating your constraints");
                                return;
                            // chosen wrong number of day
                            default:
                                System.out.println("invalid input, please try again");
                                break;
                        }
                    }
                    break;
                case 4:
                    info.ShowConstraints(ID);
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

    /**
     * manager (HR manager) needs to log in and it will be checked here
     */
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

    /**
     * will open the options for manager
     */
    //window with everything the manager can do
    public static void ManagerOptions(){
        int choice=-1;
        while (choice!=6){
            System.out.println("please choose your action:");
            System.out.println("1. work on a branch (snif)");
            System.out.println("2. add new branch");
            System.out.println("3. send weekly shifts to history for all branches");
            System.out.println("4. update employee");
            System.out.println("5. change password");
            System.out.println("6. pay salaries");
            System.out.println("7. Exit ");
            //ask for input num
            choice=AskForNumber(1,7);
            switch (choice){
                case 1:
                    String Name=AskForBranch();
                    // once we now the name of the branch we need to ask what he wanna do with it
                    int choice2=-1;
                    while (choice2!=7) {
                        System.out.println("please choose your action at the branch: ");
                        System.out.println("1. create weekly shift");
                        System.out.println("2. update day in weekly shift");
                        System.out.println("3. show current shifts");
                        System.out.println("4. watch week from history");
                        System.out.println("5. remove worker from this super");
                        System.out.println("6. update super shift times");
                        System.out.println("7. Exit ");
                        choice2=AskForNumber(1,7);
                        switch (choice2) {
                            case 1:
                                //check if there is not a weekly created already
                                if(info.HasWeekly(Name)){
                                    System.out.println("you already created a weekly, now you can just edit it or send all the weekly to history to start again");
                                    break;
                                }
                                //create the weekly plan
                                info.CreateWeekly(Name);
                                break;
                            case 2:
                                if(!info.HasWeekly(Name)){
                                    System.out.println("no weekly yet, go create one first");
                                    break;
                                }
                                System.out.println("please enter the number of the day which you want to change \n" +
                                        "Sunday-1 , Monday-2, Tuesday-3, Wednesday-4, Thursday-5, Friday-6, Saturday-7 \n"+
                                        "if you want to exit - press 8 ");
                                //get the day he wants to edit
                                int day_choice = AskForNumber(1,8);
                                //prints this day's info
                                System.out.println("the shift in " + Days.values()[day_choice-1] + " is:" );
                                //print the shift og the day he chose
                                info.PrintDay(Name,day_choice-1);
                                //ask if he wants to add someone, remove or replace
                                System.out.println("please choose the action you want to do: \n" +
                                        "1. remove an employee  \n"+
                                        "2. add an employee \n"+
                                        "3. back");
                                //get input
                                int ActionChoice =AskForNumber(1,3);
                                //if i got here i have a good option
                                switch (ActionChoice){
                                    case 1:
                                        String ID = AskForWorkerID();
                                        info.RemoveFromDay(ID, Name,day_choice-1);
                                        break;
                                    case 2:
                                        ID = AskForWorkerID();
                                        System.out.println("""
                                                please enter the time of the shift which you want to add the worker:\s
                                                1. morning\s
                                                2. Evening""");
                                        int shift_op = AskForNumber(1,2);
                                        info.AddToDay(ID,Name,shift_op,day_choice-1);
                                        break;
                                    case 3:
                                        break;
                                }
                                break;
                            case 3:
                                //print the current weekly plan
                                info.PrintWeekly(Name);
                                break;
                            case 4:
                                //we need to get the date of sunday of that week
                                boolean check=true;
                                int Year=0;
                                int Month=0;
                                int Day=0;
                                while(check){
                                    //we need to get the date he want to watch
                                    System.out.println("we need the date of the sunday of that week");
                                    System.out.println("please enter year: ");
                                    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
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
                                    //if he entered something not suitable we will repeat
                                    catch (Exception e){
                                        System.out.println("please enter a valid date ");
                                    }
                                    info.PrintWeeklyFromHist(Name,Year,Month,Day);
                                    break;
                                }
                                break;
                            case 5:
                                //we need to ask for the ID of the employee he wants to remove from the branch
                                boolean CheckName=false;
                                while (!CheckName){
                                    String ID=AskForWorkerID();
                                    CheckName=info.IsWorksInSuper(ID,Name);
                                    if (!CheckName){
                                        System.out.println("this branch doesnt have an employee with this ID, try again");
                                    }
                                    else {
                                        info.RemoveWorker(ID,Name);
                                        break;
                                    }
                                }
                                break;
                            case 6:
                                int choice4 = -1;
                                while (choice4 != 8) {
                                    System.out.println("please enter the number of the day which you want to change working hoursd in \n" +
                                            "Sunday-1 , Monday-2, Tuesday-3, Wednesday-4, Thursday-5, Friday-6, Saturday-7 ");
                                    int day_choice1 = AskForNumber(1,8);
                                    //change shift hours
                                    //he will change both morning and evening so they wont overlap
                                    System.out.println("please enter time to start morning shift (it needs to look like: 10.00 for 10am): ");
                                    Scanner time_input = new Scanner(System.in);  // Create a Scanner object
                                    String input_Constraints = time_input.nextLine();  // Read user input
                                    System.out.println("please enter time to end morning shift (it needs to look like: 22.00 for 22pm): ");
                                    time_input = new Scanner(System.in);  // Create a Scanner object
                                    String input_Constraints_2 = time_input.nextLine();  // Read user input
                                    System.out.println("please enter time to start evening shift (it needs to look like: 22.00 for 22pm): ");
                                    time_input = new Scanner(System.in);  // Create a Scanner object
                                    String input_Constraints_3 = time_input.nextLine();  // Read user input
                                    System.out.println("please enter time to end evening shift (it needs to look like: 22.00 for 22pm): ");
                                    time_input = new Scanner(System.in);  // Create a Scanner object
                                    String input_Constraints_4 = time_input.nextLine();  // Read user input
                                    try {
                                        // check if the Constraints number is valid
                                        double Constraints_num_morning_start = Double.parseDouble(input_Constraints);
                                        double Constraints_num_morning_end = Double.parseDouble(input_Constraints_2);
                                        double Constraints_num_evening_start = Double.parseDouble(input_Constraints_3);
                                        double Constraints_num_evening_end = Double.parseDouble(input_Constraints_4);
                                        if(info.CheckTimeValidate(Constraints_num_morning_start, Constraints_num_morning_end) || info.CheckTimeValidate(Constraints_num_evening_start, Constraints_num_evening_end)){
                                            System.out.println("invalid time input - hours should be between 10.00-24.59 (note that seconds is .0-.59!");
                                        }
                                        else {
                                            // if the times are valid ill send them to the super for update
                                            info.UpdateSuperTimes(Name, Days.values()[day_choice1 - 1], Constraints_num_morning_start, Constraints_num_morning_end, Constraints_num_evening_start, Constraints_num_evening_end);
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
                            case 7:
                                choice2=7;
                                break;
                            default:
                                System.out.println("please enter a valid option");
                                break;
                            }
                        }
                    break;
                case 2:
                    Super new_super = CreateNewSuper();
                    info.addSuper(new_super);
                    System.out.println(new_super.GetName() +" added successfully");
                    break;
                case 3:
                    //check if he created the weekly for everyone
                    if(!info.CheckAllHaveWeekly()){
                        System.out.println("not all the branches has a weekly plan create them first and then you can send");
                        break;
                    }
                    //send all to history
                    info.SendConstraintsToHistory();
                    break;
                case 4:
                    int choice3 = -1;
                    while (choice3 != 9) {
                        String ID;
                        System.out.println("hello manager, please choose what you want to do: ");
                        System.out.println("1. add new worker");
                        System.out.println("2. add worker to branch");
                        System.out.println("3. remove worker");
                        System.out.println("4. add job for worker");
                        System.out.println("5. change worker wage");
                        System.out.println("6. change worker contract");
                        System.out.println("7. add bonus to worker");
                        System.out.println("8. remove bonus from worker");
                        System.out.println("9. Back");
                        //ask for input
                        choice3=AskForNumber(1,9);
                        switch (choice3) {
                            case 1:
                                String BranchName= AskForBranch();
                                info.AddNewWorker(CreateNewWorker(),BranchName);
                                break;
                            case 2:
                                ID = AskForWorkerID();
                                String branch_name= AskForBranch();
                                info.AddWorkerToBranch(ID,branch_name);
                                break;
                            case 3:
                                ID = AskForWorkerID();
                                info.RemoveWorkerAllBranches(ID);
                                break;
                            case 4:
                                ID = AskForWorkerID();
                                System.out.println("please enter the new worker's first role: \n" +
                                        "ShiftManager-1 , Cashier-2, StoreKeeper-3, GeneralEmp-4, Guard-5, Cleaner-6, Usher-7");
                                int role_choice = AskForNumber(1,7);
                                info.AddJobToWorker(ID,role_choice);
                                break;
                            case 5:
                                ID = AskForWorkerID();
                                System.out.println("please enter the wage: ");
                                int wage_choice=AskForIntNumber();
                                info.ChangeWage(ID,wage_choice);
                                break;
                            case 6:
                                ID = AskForWorkerID();
                                System.out.println("please enter the contract: ");
                                Scanner myObj_Contract = new Scanner(System.in);  // Create a Scanner object
                                String input_Contract = myObj_Contract.nextLine();  // Read user input
                                info.ChangeContract(ID,input_Contract);
                                break;
                            case 7:
                                ID = AskForWorkerID();
                                System.out.println("please enter the bonus you want to add: ");
                                double a_bonus = AskForDoubleNumber();
                                info.addBonusToWorker(ID,a_bonus);
                                break;
                            case 8:
                                ID = AskForWorkerID();
                                System.out.println("please enter the amount of bonus that you want to remove: ");
                                double r_bonus = AskForDoubleNumber();
                                info.removeBonusToWorker(ID,r_bonus);
                                break;
                            case 9:
                                choice3 = 9;
                                break;
                            default:
                                System.out.println("please enter a valid option");
                                break;
                        }
                    }
                    break;
                case 5:
                    System.out.println("enter new password: ");
                    Scanner scanner1 = new Scanner(System.in);  // Create a Scanner object
                    String pass1 = scanner1.nextLine();  // Read user input
                    ManagerPass=pass1;
                    //after we did what we want we stop
                    System.out.println("password has changed");
                    break;
                case 6:
                    System.out.println("are you sure that you want to pay salaries?" +
                            "1. Yes" +
                            "2. No");
                    int payment_choice = AskForNumber(1,2);
                    if(payment_choice == 1){
                        info.Payment();
                    }
                    break;
                case 7:
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

    /**
     * a function that asks for an id until its a valid one
     * @return a valid ID of a worker
     */
    // function to ask the user for an ID input
    public static String AskForWorkerID(){
        boolean IdCheck = false;
        String ID="";
        while(!IdCheck){
            System.out.println("please enter the worker's ID: ");
            // get the new id from the manager
            Scanner myObj_newID = new Scanner(System.in);  // Create a Scanner object
            ID = myObj_newID.nextLine();
            if(!info.isExistWorker(ID)){
                System.out.println("this worker is not working at our markets! try again");
                continue;
            }
            IdCheck = true;
        }
        return ID;
    }

    /**
     * a function that asks for a branch until its a valid one
     * @return a valid name \ id of a branch
     */
    // function to ask the user for a branch name input
    public static String AskForBranch(){
        boolean BranchCheck=false;
        String BranchName="";
        while (!BranchCheck){
            System.out.println("please enter the branch you want to work on: ");
            //in the beginning there will be a few options, he is supposed to know them
            Scanner myObj_BranchName = new Scanner(System.in);  // Create a Scanner object
            BranchName = myObj_BranchName.nextLine();
            BranchCheck=info.CheckSuperName(BranchName);
            if (!BranchCheck){
                System.out.println("this branch doesn't exists, try again!");
                continue;
            }
            BranchCheck = true;
        }
        return BranchName;
    }

    /**
     * a function that asks for a number until its int the given range
     * @param s start of range
     * @param e end of range
     * @return a number in the range
     */
    // function to ask the user for a number in given range input
    public static int AskForNumber(int s,int e){
        int num=0;
        while(true){
            Scanner myObj_input = new Scanner(System.in);  // Create a Scanner object
            String input= myObj_input.nextLine();
            try {
                num = Integer.parseInt(input);
                if (num<=e && num>=s){
                    return num;
                }
                else{
                    System.out.println("your input is not in range of: " + s + " - " + e);
                }
            }
            //if he entered something not suitable we will repeat
            catch (Exception e1) {
                System.out.println("invalid input, please try again");
            }
        }
    }

    // the function creates Worker by given inputs from the user
    public static Worker CreateNewWorker(){
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
        int role_choice = 0;
        System.out.println("please enter the new worker's first role: \n" +
                "ShiftManager-1 , Cashier-2, StoreKeeper-3, GeneralEmp-4, Guard-5, Cleaner-6, Usher-7");
        role_choice = AskForNumber(1,7);
        // create a generic password for the new worker
        String generic_Password = "123";
        return new Worker(input_newName,input_newID,bankNum,input_newContract,wage,Jobs.values()[role_choice-1],generic_Password);
    }

    public static Super CreateNewSuper(){
        boolean new_super_flag = false;
        String input_newSuper = "";
        // as long as the id is not new - keep asking the manager to add one
        while(!new_super_flag){
            System.out.println("please enter the name of the new super");
            // get the new name from the manager
            Scanner myObj_newSuper = new Scanner(System.in);  // Create a Scanner object
            input_newSuper = myObj_newSuper.nextLine();  // Read user input
            if (info.CheckSuperName(input_newSuper)) {
                System.out.println("this super is already exists");
            }
            else {
                new_super_flag = true;
            }
        }
        return new Super(input_newSuper);
    }

    /**
     *  a function that asks for a number (int) until we get one
     * @return a number
     */
    // the function ask the user for a number
    public static int AskForIntNumber(){
        boolean flag = true;
        int num=-999;
        while (flag){
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            String input = myObj.nextLine();  // Read user input
            //try to change the input to a string
            try{
                num=Integer.parseInt(input);
                flag=false;
                return num;
            }
            //if he entered something not suitable we will repeat
            catch (Exception e){
                System.out.println("invalid input - try again!");
                continue;
            }
        }
        return num;
    }

    /**
     * a function that asks for a number (double) until we get one
     * @return a number
     */
    // the function ask the user for a number
    public static double AskForDoubleNumber(){
        boolean flag = true;
        double num=-999;
        while (flag){
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            String input = myObj.nextLine();  // Read user input
            //try to change the input to a string
            try{
                num=Double.parseDouble(input);
                flag=false;
                return num;
            }
            //if he entered something not suitable we will repeat
            catch (Exception e){
                System.out.println("invalid input - try again!");
                continue;
            }
        }
        return num;
    }
}