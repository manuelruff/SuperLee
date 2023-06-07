package HR.Presentation;

import HR.Bussiness.ManagerController;

import java.util.Scanner;

public class StoreManagerUI {
    private static Scanner scanner;
    private static ManagerController managerController = ManagerController.getInstance();
    public static void Work(Scanner sc){
        scanner=sc;
        UIGeneralFnctions.setScanner(sc);
        
    }
}
