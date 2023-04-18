package Presentation;

import Domain.ReshetInfo;

import java.util.Scanner;

public class WorkerUI {

    private static ReshetInfo info=ReshetInfo.getInstance();

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
     *  will open the options for Workers
     * @param ID make the changes on the worker with given id
     */
    public static void WorkerOption(String ID){
        //start the worker options here - Omri's Code!
        int choice=-1;
        while (choice!=5){
            System.out.println("hello please choose your option: ");
            System.out.println("1. update personal details ");
            System.out.println("2. add new constraints ");
            System.out.println("3. remove constraints ");
            System.out.println("4. show constraints ");
            System.out.println("5. Exit");
            //ask for input
            choice=UIGeneralFnctions.AskForNumber(1,5);
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
                        op1_choice = UIGeneralFnctions.AskForNumber(1,6);
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
                                int newBank = UIGeneralFnctions.AskForIntNumber();
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
                        day_choice = UIGeneralFnctions.AskForNumber(1,8);
                        switch (day_choice){
                            case 1: case 2: case 3: case 4: case 5: case 6: case 7:
                                System.out.println("please enter the start of the time that you cant work at (it needs to look like: 10.00 for 10): ");
                                double Constraints_num_start=UIGeneralFnctions.AskForDoubleNumber();
                                System.out.println("please enter the end of the time that you cant work at (it needs to look like: 10.00 for 10): ");
                                double Constraints_num_end=UIGeneralFnctions.AskForDoubleNumber();
                                if (!info.CheckTimeValidate(Constraints_num_start, Constraints_num_end)){
                                    System.out.println("not valid, please try again");
                                    break;
                                }
                                Scanner myConstraints=new Scanner(System.in);// create scanner
                                System.out.println("please enter reason:");
                                String reason=myConstraints.nextLine();  // Read user input
                                // if the Domain.Constraints is valid - add the Domain.Constraints at the day the user gave
                                if(info.AddConstraints(ID,day_choice,Constraints_num_start,Constraints_num_end,reason)){
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
                                if (!info.CheckTimeValidate(Constraints_num_start, Constraints_num_end)){
                                    System.out.println("not valid, please try again");
                                    break;
                                }
                                // if the Domain.Constraints is valid - remove the Domain.Constraints at the day the user gave if exists
                                if(info.RemoveConstraints(ID,day_choice,Constraints_num_start,Constraints_num_end)){
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
}
