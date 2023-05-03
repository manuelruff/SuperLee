import HR.Bussiness.ManagerController;
import HR.Presentation.CashRegisterUI;
import HR.Presentation.HRManagerUI;
import HR.Presentation.WorkerUI;
import Shipment.Presentation.UI;

import java.util.Scanner;

public class GeneralUI {
    public static Scanner scanner;
    public static ManagerController managerController;
    // all the info will come form there
    //the creation will create the beginning data
    /**
     * entering screen, the user will choose his role or if he want to add cancellation to cash
     */
    //we will call this function from the main and all the user interface will be from here
    public static void StartMe(){
        managerController=ManagerController.getInstance();
        scanner =new Scanner(System.in);
        //start the ui here
        int choice=-1;
        while (choice!=5){
            System.out.println("please choose an option: ");
            System.out.println("1. Worker");
            System.out.println("2. HR Manager");
            System.out.println("3. Ship Manager");
            System.out.println("4. Cancellations");
            System.out.println("5. Exit");
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
                    WorkerUI.WorkerLogIN(scanner);
                    //after we did what we want we stop
                    break;
                case 2:
                    HRManagerUI.ManagerLogIN(scanner);
                    //after we did what we want we stop
                    break;
                case 3:
                    UI.shippingMenu(scanner);
                    break;
                case 4:
                    //whatever a cash register should do
                    CashRegisterUI.CashWork(scanner);
                    break;
                case 5:
                    System.out.println("have a good day");
                    //do what we need in the database when closed
                    managerController.closeDB();
                    //close the scanner
                    scanner.close();
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