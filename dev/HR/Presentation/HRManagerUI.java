package HR.Presentation;

import HR.DataAccess.DataController;
import HR.Bussiness.*;

import static Presentation.GeneralUI.scanner;

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
            System.out.println("Password: ");
            String input = scanner.nextLine();  // Read user input
            if(!ManagerController.checkPassword(input)){
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
                                if(ManagerController.HasWeekly(Name)){
                                    System.out.println("you already created a weekly, now you can just edit it or send all the weekly to history to start again");
                                    break;
                                }
                                //create the weekly plan
                                ManagerController.CreateWeekly(Name);
                                break;
                            case 2:
                                if(!ManagerController.HasWeekly(Name)){
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
                                ManagerController.PrintDay(Name,day_choice-1);
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
                                        if(!ManagerController.RemoveFromDay(ID, Name,day_choice-1)){
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
                                        ManagerController.AddToDay(ID,Name,shift_op,day_choice-1);
                                        break;
                                    case 3:
                                        break;
                                }
                                break;
                            case 3:
                                //print the current weekly plan
                                if(!ManagerController.PrintWeekly(Name)){
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
                                                            String year = scanner.nextLine();  // Read user input
                                    System.out.println("please enter month: ");
                                    String month = scanner.nextLine();  // Read user input
                                    System.out.println("please enter day: ");
                                    String day = scanner.nextLine();  // Read user input
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
                                    ManagerController.PrintWeeklyFromHist(Name,Year,Month,Day);
                                    break;
                                }
                                break;
                            case 5:
                                //we need to ask for the ID of the employee he wants to remove from the branch
                                boolean CheckName=false;
                                while (!CheckName){
                                    String ID=UIGeneralFnctions.AskForWorkerID();
                                    CheckName= ManagerController.IsWorksInSuper(ID,Name);
                                    if (!CheckName){
                                        System.out.println("this branch doesnt have an employee with this ID, try again");
                                    }
                                    else {
                                        ManagerController.RemoveWorker(ID,Name);
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
                                    String input_Constraints = scanner.nextLine();  // Read user input
                                    System.out.println("please enter time to end morning shift (it needs to look like: 22.00 for 22pm): ");
                                    String input_Constraints_2 = scanner.nextLine();  // Read user input
                                    System.out.println("please enter time to start evening shift (it needs to look like: 22.00 for 22pm): ");
                                    String input_Constraints_3 = scanner.nextLine();  // Read user input
                                    System.out.println("please enter time to end evening shift (it needs to look like: 22.00 for 22pm): ");
                                    String input_Constraints_4 = scanner.nextLine();  // Read user input
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
                                            ManagerController.UpdateSuperTimes(Name, Days.values()[day_choice1 - 1], Constraints_num_morning_start, Constraints_num_morning_end, Constraints_num_evening_start, Constraints_num_evening_end);
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
                    CreateNewSuper();
                    System.out.println("Branch added to the system");
                    break;
                case 3:
                    //check if he created the weekly for everyone
                    if(!ManagerController.CheckAllHaveWeekly()){
                        System.out.println("not all the branches has a weekly plan create them first and then you can send");
                        break;
                    }
                    //send all to history
                    ManagerController.SendConstraintsToHistory();
                    break;
                case 4:
                    String LastID="-1";
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
                                ManagerController.AddNewWorker(CreateNewWorker(),BranchName);
                                break;
                            case 2:
                                ID = UIGeneralFnctions.AskForWorkerID();
                                String branch_name= UIGeneralFnctions.AskForBranch();
                                if(!ManagerController.AddWorkerToBranch(ID,branch_name)){
                                    System.out.println("the worker is already in this branch - please try again");
                                    break;
                                }
                                System.out.println(ID + " added successfully to: " + branch_name);
                                break;
                            case 3:
                                ID = UIGeneralFnctions.AskForWorkerID();
                                ManagerController.RemoveWorkerAllBranches(ID);
                                break;
                            case 4:
                                ID = UIGeneralFnctions.AskForWorkerID();
                                System.out.println("please enter the new worker's first role: \n" +
                                        "ShiftManager-1 , Cashier-2, StoreKeeper-3, GeneralEmp-4, Guard-5, Cleaner-6, Usher-7");
                                int role_choice = UIGeneralFnctions.AskForNumber(1,7);
                                ManagerController.AddJobToWorker(ID,role_choice);
                                break;
                            case 5:
                                ID = UIGeneralFnctions.AskForWorkerID();
                                System.out.println("please enter the wage: ");
                                int wage_choice=UIGeneralFnctions.AskForIntNumber();
                                ManagerController.ChangeWage(ID,wage_choice);
                                break;
                            case 6:
                                ID = UIGeneralFnctions.AskForWorkerID();
                                System.out.println("please enter the contract: ");
                                String input_Contract = scanner.nextLine();  // Read user input
                                ManagerController.ChangeContract(ID,input_Contract);
                                break;
                            case 7:
                                ID = UIGeneralFnctions.AskForWorkerID();
                                System.out.println("please enter the bonus you want to add: ");
                                double a_bonus = UIGeneralFnctions.AskForDoubleNumber();
                                System.out.println("bonus added to: "+ ManagerController.addBonusToWorker(ID,a_bonus));
                                break;
                            case 8:
                                ID = UIGeneralFnctions.AskForWorkerID();
                                System.out.println("please enter the amount of bonus that you want to remove: ");
                                double r_bonus = UIGeneralFnctions.AskForDoubleNumber();
                                System.out.println("bonus removed from: " + ManagerController.removeBonusToWorker(ID,r_bonus));
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
                    String pass1 = scanner.nextLine();  // Read user input
                    ManagerController.setManagerPassword(pass1);
                    //after we did what we want we stop
                    System.out.println("password has changed");
                    break;
                case 6:
                    System.out.println("are you sure that you want to pay salaries? " +
                            "1.Yes" +
                            " 2.No");
                    int payment_choice = UIGeneralFnctions.AskForNumber(1,2);
                    if(payment_choice == 1){
                        //we first need to get all the workers from the database to pay them
                        DataController.loadAllWorkersFrom();
                        ManagerController.Payment();
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
            input_newID = scanner.nextLine();  // Read user input
            if (managerController.isExistWorker(input_newID)) {
                System.out.println("this worker is already working at our markets");
            }
            else if(input_newID==""){
                System.out.println("please enter a valid ID");
            }
            else {
                new_id_flag = true;
            }
        }
        boolean new_name_flag = false;
        String input_newName="";
        while(!new_name_flag){
            // if the worker doesn't exist - get the other info from the manager
            System.out.println("please enter the new worker's name");
            // get the new name from the manager
            input_newName = scanner.nextLine();  // Read user input
            if (input_newName=="") {
                System.out.println("please enter a valid name");
            }
            else {
                new_name_flag = true;
            }
        }
        boolean bank_flag = false;
        int bankNum = 0;
        while (!bank_flag) {
            System.out.println("please enter the new worker's bank number");
            String input_newBank = scanner.nextLine();  // Read user input
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
        String input_newContract = scanner.nextLine();  // Read user input
        boolean wage_flag = false;
        int wage = 0;
        while (!wage_flag) {
            System.out.println("please enter the new worker's wage");
            String input_newWage = scanner.nextLine();  // Read user input
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
        return new Worker(input_newID,input_newName,bankNum,input_newContract,wage, Jobs.values()[role_choice-1],generic_Password);
    }

    public static void CreateNewSuper(){
        boolean check = true;
        String siteName = null;
        while(check) {
            System.out.println("Please enter site name:");
            siteName = scanner.nextLine();
            check = ManagerController.CheckBranchExist(siteName);
            if (check)
                System.out.println("Site already exist please enter a new name");
        }
        System.out.println("Please enter Site Address:");
        String siteAddress = scanner.nextLine();
        String sitePhoneNumber;
        System.out.println("Please enter site phone number (10 digits - only numbers):");
        sitePhoneNumber = scanner.nextLine();
        while(sitePhoneNumber.length() != 10 || !sitePhoneNumber.matches("[0-9]+")) {
            System.out.println("Please enter site phone number (10 digits - only numbers):");
            sitePhoneNumber = scanner.nextLine();
        }
        System.out.println("Please enter contact name:");
        String contactName = scanner.nextLine();
        System.out.println("""
                Please enter branch zone:
                0 - North
                1 - Center
                2 - South""");
        int zone = GeneralController.AskForNumber(0,2);
        ManagerController.addSuper(siteName,siteAddress,sitePhoneNumber,contactName,zone);

    }

}
