package Presentation;

import Domain.*;

import java.util.Scanner;

public class HRManagerUI {
    //private static ReshetInfo info=ReshetInfo.getInstance();
    private static ManagerController managerController = ManagerController.getInstance();
    //saves the Manager Password - started as 1234 and he can change it
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
            if(!managerController.checkPassword(input)){
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
        while (choice!=7){
            System.out.println("please choose your action:");
            System.out.println("1. work on a branch (snif)");
            System.out.println("2. add new branch");
            System.out.println("3. send weekly shifts to history for all branches");
            System.out.println("4. update employee");
            System.out.println("5. change password");
            System.out.println("6. pay salaries");
            System.out.println("7. Back ");
            //ask for input num
            choice=UIGeneralFnctions.AskForNumber(1,7);
            switch (choice){
                case 1:
                    String Name=UIGeneralFnctions.AskForBranch();
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
                        choice2=UIGeneralFnctions.AskForNumber(1,7);
                        switch (choice2) {
                            case 1:
                                //check if there is not a weekly created already
                                if(managerController.HasWeekly(Name)){
                                    System.out.println("you already created a weekly, now you can just edit it or send all the weekly to history to start again");
                                    break;
                                }
                                //create the weekly plan
                                managerController.CreateWeekly(Name);
                                break;
                            case 2:
                                if(!managerController.HasWeekly(Name)){
                                    System.out.println("no weekly yet, go create one first");
                                    break;
                                }
                                System.out.println("please enter the number of the day which you want to change \n" +
                                        "Sunday-1 , Monday-2, Tuesday-3, Wednesday-4, Thursday-5, Friday-6, Saturday-7 \n"+
                                        "if you want to exit - press 8 ");
                                //get the day he wants to edit
                                int day_choice = UIGeneralFnctions.AskForNumber(1,8);
                                if(day_choice==8)
                                    break;
                                //prints this day's info
                                System.out.println("the shift in " + Days.values()[day_choice-1] + " is:" );
                                //print the shift og the day he chose
                                managerController.PrintDay(Name,day_choice-1);
                                //ask if he wants to add someone, remove or replace
                                System.out.println("please choose the action you want to do: \n" +
                                        "1. remove an employee  \n"+
                                        "2. add an employee \n"+
                                        "3. back");
                                //get input
                                int ActionChoice =UIGeneralFnctions.AskForNumber(1,3);
                                //if i got here i have a good option
                                switch (ActionChoice){
                                    case 1:
                                        String ID = UIGeneralFnctions.AskForWorkerID();
                                        if(!managerController.RemoveFromDay(ID, Name,day_choice-1)){
                                            System.out.println("this worker doesn't works at this shift");
                                        }
                                        break;
                                    case 2:
                                        ID = UIGeneralFnctions.AskForWorkerID();
                                        System.out.println("""
                                                please enter the time of the shift which you want to add the worker:\s
                                                1. morning\s
                                                2. Evening""");
                                        int shift_op = UIGeneralFnctions.AskForNumber(1,2);
                                        managerController.AddToDay(ID,Name,shift_op,day_choice-1);
                                        break;
                                    case 3:
                                        break;
                                }
                                break;
                            case 3:
                                //print the current weekly plan
                                if(!managerController.PrintWeekly(Name)){
                                    System.out.println("no weekly shift yet");
                                }
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
                                    managerController.PrintWeeklyFromHist(Name,Year,Month,Day);
                                    break;
                                }
                                break;
                            case 5:
                                //we need to ask for the ID of the employee he wants to remove from the branch
                                boolean CheckName=false;
                                while (!CheckName){
                                    String ID=UIGeneralFnctions.AskForWorkerID();
                                    CheckName=managerController.IsWorksInSuper(ID,Name);
                                    if (!CheckName){
                                        System.out.println("this branch doesnt have an employee with this ID, try again");
                                    }
                                    else {
                                        managerController.RemoveWorker(ID,Name);
                                        break;
                                    }
                                }
                                break;
                            case 6:
                                int choice4 = -1;
                                while (choice4 != 8) {
                                    System.out.println("please enter the number of the day which you want to change working hoursd in \n" +
                                            "Sunday-1 , Monday-2, Tuesday-3, Wednesday-4, Thursday-5, Friday-6, Saturday-7 ");
                                    int day_choice1 = UIGeneralFnctions.AskForNumber(1,8);
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
                                        // check if the Domain.Constraints number is valid
                                        double Constraints_num_morning_start = Double.parseDouble(input_Constraints);
                                        double Constraints_num_morning_end = Double.parseDouble(input_Constraints_2);
                                        double Constraints_num_evening_start = Double.parseDouble(input_Constraints_3);
                                        double Constraints_num_evening_end = Double.parseDouble(input_Constraints_4);
                                        if(!UIGeneralFnctions.CheckTimeValidate(Constraints_num_morning_start, Constraints_num_morning_end) || !UIGeneralFnctions.CheckTimeValidate(Constraints_num_evening_start, Constraints_num_evening_end)){
                                            System.out.println("invalid time input - hours should be between 10.00-24.59 (note that seconds is .0-.59!");
                                        }
                                        else {
                                            // if the times are valid ill send them to the super for update
                                            managerController.UpdateSuperTimes(Name, Days.values()[day_choice1 - 1], Constraints_num_morning_start, Constraints_num_morning_end, Constraints_num_evening_start, Constraints_num_evening_end);
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
                    managerController.addSuper(new_super);
                    System.out.println(new_super.GetName() +" added successfully");
                    break;
                case 3:
                    //check if he created the weekly for everyone
                    if(!managerController.CheckAllHaveWeekly()){
                        System.out.println("not all the branches has a weekly plan create them first and then you can send");
                        break;
                    }
                    //send all to history
                    managerController.SendConstraintsToHistory();
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
                        choice3=UIGeneralFnctions.AskForNumber(1,9);
                        switch (choice3) {
                            case 1:
                                String BranchName= UIGeneralFnctions.AskForBranch();
                                managerController.AddNewWorker(CreateNewWorker(),BranchName);
                                break;
                            case 2:
                                ID = UIGeneralFnctions.AskForWorkerID();
                                String branch_name= UIGeneralFnctions.AskForBranch();
                                if(!managerController.AddWorkerToBranch(ID,branch_name)){
                                    System.out.println("the worker is already in this branch - please try again");
                                    break;
                                }
                                System.out.println(ID + " added successfully to: " + branch_name);
                                break;
                            case 3:
                                ID = UIGeneralFnctions.AskForWorkerID();
                                managerController.RemoveWorkerAllBranches(ID);
                                break;
                            case 4:
                                ID = UIGeneralFnctions.AskForWorkerID();
                                System.out.println("please enter the new worker's first role: \n" +
                                        "ShiftManager-1 , Cashier-2, StoreKeeper-3, GeneralEmp-4, Guard-5, Cleaner-6, Usher-7");
                                int role_choice = UIGeneralFnctions.AskForNumber(1,7);
                                managerController.AddJobToWorker(ID,role_choice);
                                break;
                            case 5:
                                ID = UIGeneralFnctions.AskForWorkerID();
                                System.out.println("please enter the wage: ");
                                int wage_choice=UIGeneralFnctions.AskForIntNumber();
                                managerController.ChangeWage(ID,wage_choice);
                                break;
                            case 6:
                                ID = UIGeneralFnctions.AskForWorkerID();
                                System.out.println("please enter the contract: ");
                                Scanner myObj_Contract = new Scanner(System.in);  // Create a Scanner object
                                String input_Contract = myObj_Contract.nextLine();  // Read user input
                                managerController.ChangeContract(ID,input_Contract);
                                break;
                            case 7:
                                ID = UIGeneralFnctions.AskForWorkerID();
                                System.out.println("please enter the bonus you want to add: ");
                                double a_bonus = UIGeneralFnctions.AskForDoubleNumber();
                                System.out.println("bonuss added to: "+managerController.addBonusToWorker(ID,a_bonus));
                                break;
                            case 8:
                                ID = UIGeneralFnctions.AskForWorkerID();
                                System.out.println("please enter the amount of bonus that you want to remove: ");
                                double r_bonus = UIGeneralFnctions.AskForDoubleNumber();
                                System.out.println("bonus removed from: " +managerController.removeBonusToWorker(ID,r_bonus));
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
                    managerController.setManagerPassword(pass1);
                    //after we did what we want we stop
                    System.out.println("password has changed");
                    break;
                case 6:
                    System.out.println("are you sure that you want to pay salaries?" +
                            "1. Yes" +
                            "2. No");
                    int payment_choice = UIGeneralFnctions.AskForNumber(1,2);
                    if(payment_choice == 1){
                        managerController.Payment();
                        System.out.println("Payment done!");
                    }
                    break;
                case 7:
                    System.out.println("have a good day");
                    break;
                default:
                    System.out.println("please enter a valid option");
                    break;
            }
        }
    }

    public static Worker CreateNewWorker(){
        boolean new_id_flag = false;
        String input_newID = "";
        // as long as the id is not new - keep asking the manager to add one
        while(!new_id_flag){
            System.out.println("please enter the new worker's ID");
            // get the new id from the manager
            Scanner myObj_newID = new Scanner(System.in);  // Create a Scanner object
            input_newID = myObj_newID.nextLine();  // Read user input
            if (managerController.isExistWorker(input_newID)) {
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
        role_choice = UIGeneralFnctions.AskForNumber(1,7);
        // create a generic password for the new worker
        String generic_Password = "123";
        return new Worker(input_newName,input_newID,bankNum,input_newContract,wage, Jobs.values()[role_choice-1],generic_Password);
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
            if (managerController.CheckBranchExist(input_newSuper)) {
                System.out.println("this super is already exists");
            }
            else {
                new_super_flag = true;
            }
        }
        return new Super(input_newSuper);
    }

}
