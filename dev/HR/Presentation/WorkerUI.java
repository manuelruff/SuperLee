package HR.Presentation;

import HR.Bussiness.WorkerController;
import Shipment.Service.HRService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

public class WorkerUI {
    private static Scanner scanner;
    private static WorkerController workerController=WorkerController.getInstance();
    /**
     * worker needs to log in and it will be checked here
     */
    public static void WorkerLogIN(Scanner sc){
        scanner=sc;
        UIGeneralFnctions.setScanner(sc);
        boolean flag = true;
        System.out.println("please log in: ");
        while (flag){
            // got from the use the ID and the password
            System.out.println("ID: ");
            String ID = scanner.nextLine();  // Read user input
            System.out.println("Password: ");
            String passwordInput = scanner.nextLine();  // Read user input
            // check if the worker enter valid inputs
            if(!workerController.isExistWorker(ID) || !workerController.IsTruePassword(ID,passwordInput)){
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
     *  will open the options for Workers
     * @param ID make the changes on the worker with given id
     */
    public static void WorkerOption(String ID){
        //start the worker options here - Omri's Code!
        int choice=-1;
        while (choice!=7){
            System.out.println("hello please choose your option: ");
            System.out.println("1. update personal details ");
            System.out.println("2. add new constraints ");
            System.out.println("3. remove constraints ");
            System.out.println("4. show constraints ");
            System.out.println("5. show shifts");
            System.out.println("6. show today's shipment");
            System.out.println("7. Back");
            //ask for input
            choice=UIGeneralFnctions.AskForNumber(1,7);
            switch (choice){
                case 1:
                    int op1_choice=-1;
                    while (op1_choice!=4) {
                        System.out.println("hello " + workerController.GetWorkerByID(ID).getName() + " please choose the detail you want to change");
                        System.out.println("1. change password ");
                        System.out.println("2. change name");
                        System.out.println("3. change bank account");
                        System.out.println("4. Back");
                        op1_choice = UIGeneralFnctions.AskForNumber(1,4);
                        switch (op1_choice){
                            case 1:
                                System.out.println("please enter new password:");
                                // get the new password from the worker
                                String input_changepass = scanner.nextLine();  // Read user input
                                // change it to function in reshet info
                                workerController.ChangeWorkerPassword(ID,input_changepass); // change the password
                                //after we did what we want we stop
                                op1_choice=4;
                                break;
                            case 2:
                                System.out.println("please enter new name:");
                                // get the new password from the worker
                                String input_changename = scanner.nextLine();  // Read user input
                                workerController.ChangeWorkerName(ID,input_changename);// change name
                                //after we did what we want we stop
                                op1_choice=4;
                                break;
                            case 3:
                                System.out.println("please enter new bank details:");
                                int newBank = UIGeneralFnctions.AskForIntNumber();
                                workerController.ChangeWorkerBank(ID,newBank );
                                //after we did what we want we stop
                                op1_choice=4;
                                break;
                            case 4:
                                //stop the loop and go back to previous window
                                op1_choice=4;
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
                        day_choice = UIGeneralFnctions.AskForNumber(1,8);
                        switch (day_choice){
                            case 1: case 2: case 3: case 4: case 5: case 6: case 7:
                                System.out.println("please enter the start of the time that you cant work at (it needs to look like: 10.00 for 10): ");
                                double Constraints_num_start=UIGeneralFnctions.AskForDoubleNumber();
                                System.out.println("please enter the end of the time that you cant work at (it needs to look like: 10.00 for 10): ");
                                double Constraints_num_end=UIGeneralFnctions.AskForDoubleNumber();
                                if (!UIGeneralFnctions.CheckTimeValidate(Constraints_num_start, Constraints_num_end)){
                                    System.out.println("not valid, please try again");
                                    break;
                                }
                                System.out.println("please enter reason:");
                                String reason=scanner.nextLine();  // Read user input
                                // if the Domain.Constraints is valid - add the Domain.Constraints at the day the user gave
                                if(workerController.AddConstraints(ID,day_choice,Constraints_num_start,Constraints_num_end,reason)){
                                    System.out.println("your constraint added");
                                }
                                else{
                                    System.out.println("you already have constraint in identical time");
                                }
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
                        System.out.println("please enter the number of the day which you want to remove constraints from \n" +
                                "Sunday-1 , Monday-2, Tuesday-3, Wednesday-4, Thursday-5, Friday-6, Saturday-7 \n"+
                                "if you want to exit - press 8 ");
                        day_choice = UIGeneralFnctions.AskForNumber(1,8);
                        switch (day_choice){
                            case 1: case 2: case 3: case 4: case 5: case 6: case 7:
                                System.out.println("please enter the start of the time that you cant work at (it needs to look like: 10.00 for 10am): ");
                                double Constraints_num_start=UIGeneralFnctions.AskForDoubleNumber();
                                System.out.println("please enter the end of the time that you cant work at (it needs to look like: 22.00 for 22pm): ");
                                double Constraints_num_end=UIGeneralFnctions.AskForDoubleNumber();
                                // check if the Domain.Constraints number is valid
                                if (!UIGeneralFnctions.CheckTimeValidate(Constraints_num_start, Constraints_num_end)){
                                    System.out.println("not valid, please try again");
                                    break;
                                }
                                // if the Domain.Constraints is valid - remove the Domain.Constraints at the day the user gave if exists
                                if(workerController.RemoveConstraints(ID,day_choice,Constraints_num_start,Constraints_num_end)){
                                    System.out.println("the constraint removed");
                                }
                                else{
                                    System.out.println("the constraint is invalid - please look at your constrains ant insert the exact day and time");
                                }
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
                    workerController.ShowConstraints(ID);
                    //after we did what we want we stop
                    break;
                case 5:
                    // prints the shifts of asked branch
                    // first check if the worker is driver or not cause drivers has different shifts
                    if(!workerController.IsDriver(ID)){
                        // if it's not a driver - ask for branch to show the worker shifts
                        String BranchName = UIGeneralFnctions.AskForBranch();
                        boolean printed=workerController.ShowWorkerShifts(BranchName);
                        if(!printed){
                            System.out.println("there is no shifts in this branch");
                        }
                        break;
                    }
                    // if it's a driver - show the driver shifts
                    boolean printed=workerController.ShowDriverShifts(ID);
                    if(!printed){
                        System.out.println("there are no shifts for you in this week");
                    }
                    break;
                case 6:
                    // ask the worker for his branch
                    String branchName = UIGeneralFnctions.AskForBranch();
                    // first check todays shift
                    LocalDate today = LocalDate.now();
                    String dayName = today.getDayOfWeek().toString().charAt(0)+today.getDayOfWeek().toString().substring(1).toLowerCase();
                    //String dayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault());
                    System.out.println(dayName);
                    // check if the worker works today in the branch
                    boolean works = workerController.IsWorksTodayAsShiftManagerOrStoreKeeper(ID,dayName,branchName);
                    if(works) {
                        workerController.printShipments(dayName, branchName);
                        System.out.println("need to do");
                    }
                    else{
                        System.out.println("you are not valid to see this content!");
                    }
                    break;
                case 7:
                    choice=7;
                    System.out.println("have a good day");
                    break;
                default:
                    System.out.println("please enter a valid option");
                    break;
            }
        }

    }
}
