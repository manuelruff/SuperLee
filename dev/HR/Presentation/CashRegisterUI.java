package HR.Presentation;


import HR.Bussiness.CashRegisterController;

import java.util.Scanner;


public class CashRegisterUI {
    private static Scanner scanner;

    private static CashRegisterController cashRegisterController = CashRegisterController.getInstance();
    /**
     * option for the cashregistry
     */
    public static void CashWork(Scanner sc){
        scanner=sc;
        UIGeneralFnctions.setScanner(sc);
        String Name= UIGeneralFnctions.AskForBranch();
        int choice=-1;
        while (choice!=3){
            System.out.println("please choose an option: ");
            System.out.println("1. watch cancellations history of a day");
            System.out.println("2. add a cancellations log");
            System.out.println("3. Back");
            String input = scanner.nextLine();  // Read user input
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
                    }
                    //if we get here we have numbers for the date, well send it and see what we get back
                    cashRegisterController.PrintCancellation(Name,Year,Month,Day);
                    //after we did what we want we stop
                    choice=4;
                    break;
                case 2:
                    String ID = UIGeneralFnctions.AskForWorkerID();
                    boolean isShiftManager = false;
                    while(!isShiftManager){
                        if(!cashRegisterController.CheckWorkerCanCancel(ID)){
                            System.out.println("access denied - this action is for Domain.Shift Manager only!");
                            continue;
                        }
                        isShiftManager = true;
                    }
                    System.out.println("please enter your password: ");
                    String password = scanner.nextLine();  // Read user input
                    if(!cashRegisterController.CheckWorkerPassword(ID,password)){
                        System.out.println("password is incorrect");
                        choice = 4;
                        break;
                    }
                    // if he's a shift manager
                    System.out.println("please enter the name of the item which you want to cancel: ");
                      // Create a Scanner object
                    String item = scanner.nextLine();  // Read user input
                    System.out.println("please enter the amount of the item which you want to cancel: ");
                    double amount = UIGeneralFnctions.AskForDoubleNumber();
                    cashRegisterController.AddCancellations(Name,item,amount,ID);
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


}
