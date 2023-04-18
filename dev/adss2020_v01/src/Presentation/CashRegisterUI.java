package Presentation;

import Domain.Jobs;
import Domain.ReshetInfo;

import java.util.Scanner;

public class CashRegisterUI {
    private static ReshetInfo info=ReshetInfo.getInstance();
    /**
     * option for the cashregistry
     */
    public static void CashWork(){
        String Name=UIGeneralFnctions.AskForBranch();
        //maybe add an option to watch a specific days Domain.Cancellations
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
                    String ID = UIGeneralFnctions.AskForWorkerID();
                    boolean isShiftManager = false;
                    while(!isShiftManager){
                        if(!info.CanDoJob(ID, Jobs.ShiftManager)){
                            System.out.println("access denied - this action is for Domain.Shift Manager only!");
                            continue;
                        }
                        isShiftManager = true;
                    }
                    // if he's a shift manager
                    System.out.println("please enter the name of the item which you want to cancel: ");
                    myObj = new Scanner(System.in);  // Create a Scanner object
                    String item = myObj.nextLine();  // Read user input
                    System.out.println("please enter the amount of the item which you want to cancel: ");
                    double amount = UIGeneralFnctions.AskForDoubleNumber();
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


}
