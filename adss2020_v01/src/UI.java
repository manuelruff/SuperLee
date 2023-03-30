import javax.lang.model.element.Name;
import java.util.Scanner;

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
            System.out.println("2. add new Iluts ");
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
                    info.AddIluts(ID);
                    //after we did what we want we stop
                    break;
                case 3:
                    info.RemoveIluts(ID);
                    //do remove ilutz here
                    break;
                case 4:
                    info.ShowIluts(ID);
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
                    while (choice2!=3) {
                        System.out.println("please choose your action at the branch: ");
                        System.out.println("1. create weekly shift");
                        System.out.println("2. update day in weekly shift");
                        System.out.println("3. show current shift");
                        System.out.println("4. send shift to history");
                        System.out.println("5. watch history");
                        System.out.println("6. remove worker from this super");
                        System.out.println("7. update super shift times");
                        System.out.println("8. Exit ");
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
                                //create the weekly plan
                                info.CreatWeekly(Name);
                                break;
                            case 2:
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
                                            "2. add an employee \n"+
                                            "3. switch employees");
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
                                        info.AddToDay(Name,day_choice);
                                        break;
                                    case 3:
                                        //first removes someone
                                        info.RemoveFromDay(Name,day_choice);
                                        //and then add somone else
                                        info.AddToDay(Name,day_choice);
                                        break;
                                }

                                break;
                            case 3:
                                //print the current weekly plan
                                info.PrintWeekly(Name);
                                break;
                            case 4:
                                //send the current iluts to history and then you have to create new one
                                info.SendIlutsToHistory(Name);
                                break;
                            case 5:

                                break;
                            case 6:
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
                            case 7:
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
                                        if(!info.CheckTimeValidate(iluts_num_morning_start,iluts_num_morning_end) || !info.CheckTimeValidate(iluts_num_evening_start,iluts_num_evening_end)){
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
                            case 8:
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
                                info.AddNewWorker();
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

// added 29.3
    public static String AskForWorkerID(){
        System.out.println("please enter the worker's ID: ");
        // get the new id from the manager
        Scanner myObj_newID = new Scanner(System.in);  // Create a Scanner object
        return myObj_newID.nextLine();
        }
}