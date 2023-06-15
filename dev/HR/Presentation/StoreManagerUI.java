package HR.Presentation;

import HR.Bussiness.ManagerController;
import Shipment.Bussiness.shipmentManagement;
import Shipment.Presentation.UI;

import java.util.Scanner;

public class StoreManagerUI {
    private static Scanner scanner;
    public static shipmentManagement sManagement;

    private static ManagerController managerController = ManagerController.getInstance();
    public static void Work(Scanner sc){
        sManagement= shipmentManagement.getInstance();
        scanner=sc;
        UIGeneralFnctions.setScanner(sc);
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
                    UI.SManagerLogIN(scanner);
                    break;
                case 4:
                    //whatever a cash register should do
                    CashRegisterUI.CashWork(scanner);
                    break;
                case 5:
                    System.out.println("have a good day");
                    //do what we need in the database when closed
                    sManagement.closeDB();
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
