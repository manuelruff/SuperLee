package Presentation;

import Domain.ReshetInfo;

import java.util.Scanner;

public class GeneralUI {
    // all the info will come form there
    //the creation will create the beginning data
    private static ReshetInfo info=ReshetInfo.getInstance();

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
                    WorkerUI.WorkerLogIN();
                    //after we did what we want we stop
                    choice=4;
                    break;
                case 2:
                    HRManagerUI.ManagerLogIN();
                    //after we did what we want we stop
                    choice=4;
                    break;
                case 3:
                    //whatever a cash register should do
                    CashRegisterUI.CashWork();
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

}