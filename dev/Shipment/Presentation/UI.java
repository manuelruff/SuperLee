package Shipment.Presentation;

import Shipment.Bussiness.Status;
import Shipment.Bussiness.shipmentManagement;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;


public class UI {
    private static shipmentManagement Smanagement=shipmentManagement.getInstance();
    private static Scanner scanner;

    public UI(Scanner sc){
    }

    public static void shippingMenu(Scanner sc)
    {
        scanner=sc;
        String choice;
        while(true) {
            System.out.println("Shipping Main Menu:");
            System.out.println("1 - Site Menu");
            System.out.println("2 - Truck Menu");
            System.out.println("3 - Drivers Menu");
            System.out.println("4 - Order Menu");
            System.out.println("5 - Shipping Menu");
            System.out.println("6 - Print all shipped item docs");
            System.out.println("7 - Exit");
            choice = scanner.nextLine();
            switch (choice)
            {
                case "1":
                    boolean chF=true;
                    while(chF) {
                        System.out.println("Site Menu:");
                        System.out.println("1 - Add Site");
                        System.out.println("2 - Remove Site");
                        System.out.println("3 - Print All Sites ");
                        System.out.println("4 - Update site info");
                        System.out.println("5 - Exit");
                        int ch = 0;
                        try{
                            ch = Integer.parseInt(scanner.nextLine());
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Please enter a number from the menu");
                        }
                        switch (ch) {
                            case 1:
                                addSite();
                                break;
                                //asd

                            case 2:
                                deleteSite();
                                break;
                            case 3:
                                Smanagement.printSites();
                                break;
                            case 4:
                                updateSite();
                                break;
                            case 5:
                                chF=false;
                                break;
                            default:
                                System.out.println("please enter a number between 1 - 5");
                        }
                    }
                    break;
                case "2":
                    int ch2=0;
                    while(ch2 != 4) {
                        System.out.println("Truck Menu:");
                        System.out.println("1 - Add Truck");
                        System.out.println("2 - Delete Truck");
                        System.out.println("3 - Print All Trucks");
                        System.out.println("4 - Exit");
                        try{
                            ch2 = Integer.parseInt(scanner.nextLine());
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Please enter a number from the menu");
                        }
                        switch (ch2) {
                            case 1:
                                addTruck();
                                break;
                            case 2:
                                deleteTruck();
                                break;
                            case 3:
                                Smanagement.printTrucks();
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println("Invalid input");
                        }
                    }
                    break;
                case "3":
                    int ch3=0;
                    while(ch3 != 6) {
                        System.out.println("Drivers Menu:");
                        System.out.println("1 - Add driver");
                        System.out.println("2 - remove driver");
                        System.out.println("3 - Print All drivers");
                        System.out.println("4 - Update driver licence");
                        System.out.println("5 - Update driver training");
                        System.out.println("6 - Exit");
                        try{
                            ch3 = Integer.parseInt(scanner.nextLine());
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Please enter a number from the menu");
                        }
                        switch (ch3)
                        {
                            case 1 :
                                //addDriver();
                                break;
                            case 2:
                                deleteDriver();
                                break;
                            case 3:
                                Smanagement.printDrivers();
                                break;
                            case 4:
                                updateDriverLicence();
                                break;
                            case 5:
                                updateDriverTraining();
                                break;
                            case 6:
                                break;
                            default:
                                System.out.println("Invalid input");
                                break;
                        }
                    }
                    break;
                case "4":
                    int ch4=0;
                    while(ch4 != 3) {
                        System.out.println("Order Menu:");
                        System.out.println("1 - Add order");
                        System.out.println("2 - Print All orders");
                        System.out.println("3 - Exit");
                        try{
                            ch4 = Integer.parseInt(scanner.nextLine());
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Please enter a number from the menu");
                        }
                        switch (ch4){
                            case 1:
                                addOrder();
                                break;
                            case 2:
                                Smanagement.printOrders();
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Invalid input");
                                break;
                        }
                    }
                    break;

                case "5":
                    int ch5 = 0;
                    while(ch5 != 6) {
                        System.out.println("Shipment  Menu:");
                        System.out.println("1 - Add shipment");
                        System.out.println("2 - Delete shipment");
                        System.out.println("3 - Print All shipment");
                        System.out.println("4 - Print all available shipments");
                        System.out.println("5 - execute nearest shipment");
                        System.out.println("6 - Exit");
                        try{
                            ch5 = Integer.parseInt(scanner.nextLine());
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Please enter a number from the menu");
                        }
                        switch (ch5) {
                            case 1:
                                addShipment();
                                break;
                            case 2:
                                deleteShipment();
                                break;
                            case 3:
                                Smanagement.printShipments();
                                break;
                            case 4:
                                Smanagement.printAvailableShipments();
                                break;
                            case 5:
                                executeShipment();
                                break;
                            case 6:
                                break;
                            default:
                                System.out.println("Invalid input");
                                break;
                        }
                    }
                    break;
                case "6":
                    Smanagement.printAllDocs();
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    /********************************************** HELPER FUNCTIONS **********************************************/

    public static void updateDriverLicence()
    {
        boolean check3 = true, check31 =true;
        String driverID=null;
        while (check3) {
            while (check31)

            {
                System.out.println("Please enter drivers ID:");
                driverID = scanner.nextLine();
                if(driverID.matches("[0-9]+"))
                    check31 = false;
                else
                    System.out.println("Please enter numbers only");
            }
            check3 = Smanagement.checkID(driverID);
            if (!check3)
                System.out.println("Driver does not exist in the system");
            else
            {
                char driverLicence = '0';
                while(check3)
                {
                    System.out.println("""
                    Please enter licence type :
                    C - for trucks under 12 ton
                    D - for above 12 ton""");
                    driverLicence = scanner.nextLine().charAt(0);
                    if(driverLicence == 'C' || driverLicence == 'c')
                        System.out.println("All drivers have at least C licence(cannot upgrade licence to C type) ");
                    else if( driverLicence == 'd' || driverLicence == 'D') {
                        check3 = false;
                        driverLicence = Character.toUpperCase(driverLicence);
                        Smanagement.updateDriverLicence(driverID,driverLicence);
                    }
                    else
                        System.out.println("Invalid input");
                }
            }
        }
    }

    public static void updateDriverTraining()
    {
        boolean check3 = true, check31 =true;
        String driverID=null;
        int training;
        while (check3) {
            while (check31)
            {
                System.out.println("Please enter drivers ID:");
                driverID = scanner.nextLine();
                if(driverID.matches("[0-9]+"))
                    check31 = false;
                else
                    System.out.println("Please enter numbers only");
            }
            check3 = Smanagement.checkID(driverID);
            if (!check3)
                System.out.println("Driver does not exist in the system");
            else
            {
                char driverLicence = '0';
                while(check3)
                {
                    System.out.println("""
                        Please enter driver storage capabilities training:
                        0 - Regular
                        1 - Cooling
                        2 - Freezer""");
                    training = scanner.nextInt();
                    if(training == 0)
                        System.out.println("All drivers have at least regular training(cannot upgrade training to regular) ");
                    else if( training == 1 || training == 2) {
                        check3 = false;
                        driverLicence = Character.toUpperCase(driverLicence);
                        Smanagement.updateDriverTraining(driverID,training);
                    }
                    else
                        System.out.println("Invalid input");
                }
            }
        }
        scanner.nextLine();
    }
    /**
     * this function gets input from the user about a new site he wants to add
     * and adds the new site to the system
     */
    public static void addSite()
    {
        boolean check = true;
        String siteName = null;
        while(check) {
            System.out.println("Please enter site name:");
            siteName = scanner.nextLine();
            check = Smanagement.checkSite(siteName);
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
        check = true;
        int siteType;
        while (check)
        {
            while (true) {
                System.out.println("""
                        Please enter Site type:
                        1 - Vendor
                        2 - Branch""");
                if (scanner.hasNextInt()) {
                    siteType = scanner.nextInt();
                    break;
                } else {
                    scanner.nextLine(); // consume the invalid input
                    System.out.println("Invalid input. Please enter an integer next");
                }
            }

            if(siteType == 1) {
                Smanagement.addVendor(siteName, siteAddress, sitePhoneNumber, contactName);
                System.out.println("Vendor added to the system");
                check = false;
            }
            else if (siteType == 2) {
                int zone = 4;
                while(zone < 0 || zone > 2) {
                    System.out.println("""
                            Please enter branch zone:
                            0 - North
                            1 - Center
                            2 - South""");
                    zone = scanner.nextInt();
                    if(zone < 0 || zone > 2)
                        System.out.println("Please enter a number between 0 - 2");
                }
                Smanagement.addBranch(siteName,siteAddress,sitePhoneNumber,contactName,zone);
                System.out.println("Branch added to the system");
                check = false;
            }
        }
    }
    /**
     * this function gets input from the user about a site to delete
     * and delete it from the system
     */
    public static void deleteSite()
    {
        boolean check1 = true;
        String siteToDelete;
        while (check1)
        {
            System.out.println("Please enter site name you want to remove:");
            siteToDelete = scanner.nextLine();
            if(Smanagement.checkSite(siteToDelete)) {
                Smanagement.deleteSite(siteToDelete);
                System.out.println("site deleted");
                check1 = false;
            }
            else {
                System.out.println("Site does not exist in the system");
            }
        }
    }
    /**
     * this function gets input from the user about a new truck he wants to add
     * and adds the new truck to the system
     */

    public static void updateSite()
    {
        System.out.println("Please enter the name of the site you would like to update:");
        String siteName = scanner.nextLine();
        if(Smanagement.checkSite(siteName)){
            while (true) {
                System.out.println("""
                        What would you like to change:
                        1 - Site name
                        2 - Site address
                        3 - Contact name
                        4 - Contact phone number""");
                String str = scanner.nextLine();
                try {
                    int num = Integer.parseInt(str);
                    if (num >= 1 && num <= 4) {
                        System.out.println("Enter the new details");
                        String change = scanner.nextLine();
                        Smanagement.updateSite(siteName,change,num);
                        return;
                    }
                    else {
                        System.out.println("The string must be a number between 1 and 4.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("The string does not contain a valid integer.");
                }
            }
        }
        else
            System.out.println("Site does not exist in the system");
    }
    public static void addTruck()
    {
        boolean check2 = true;
        String truckNumber = null;
        int truckWeight=2,truckCarryWeight=1;
        while (check2) {
            System.out.println("Please enter the truck number:");
            truckNumber = scanner.nextLine();
            check2 = Smanagement.checkTruckNumber(truckNumber);
            if (check2)
                System.out.println("Truck already exist in the system");
        }

        System.out.println("Please enter the truck model:");
        String truckModel = scanner.nextLine();
        while (truckWeight > truckCarryWeight) {
            System.out.println("Please Enter the truck weight in KG:");
            if(scanner.hasNextInt())
            {
                truckWeight = scanner.nextInt();
                while (true) {
                    System.out.println("Please enter the truck maximum carry weight in KG:");
                    if (scanner.hasNextInt()) {
                        truckCarryWeight = scanner.nextInt();
                        break;
                    } else {
                        scanner.nextLine(); // consume the invalid input
                        System.out.println("Invalid input. Please enter an integer next");
                    }
                }
            }
            else
                System.out.println("Please enter numbers only");
            if(truckWeight > truckCarryWeight)
                System.out.println("maximum carry weight must be bigger then the truck weight");
        }
        int storage = 4;
        while(storage < 0 || storage > 2) {
            System.out.println("""
                        Please enter truck storage capabilities:
                        0 - Regular
                        1 - Cooling
                        2 - Freezer""");
            storage = scanner.nextInt();
            if(storage < 0 || storage > 2)
                System.out.println("Please enter a number between 0 - 2");
        }
        Smanagement.addTruck(truckNumber,truckCarryWeight, truckWeight,truckModel,storage);
        System.out.println("Truck added to the system");
        scanner.nextLine();
    }
    /**
     * this function gets input from the user about a truck to delete
     * and delete the truck from the system
     */
    public static void deleteTruck()
    {
        boolean check2 = true;
        String truckNumber;
        while (check2)
        {
            System.out.println("Please enter the truck number:");
            truckNumber = scanner.nextLine();
            if(Smanagement.checkTruckNumber(truckNumber)) {
                Smanagement.removeTruck(truckNumber);
                check2 = false;
                System.out.println("truck has been removed from the system");
            }
            else {
                System.out.println("truck number does not exist in the system");
            }
        }
    }
    /**
     * this function gets input from the user about a new Driver he wants to add
     * and adds the new driver to the system
     */
//    public static void addDriver()
//    {
//        boolean check3 = true, check31 =true;
//        String driverID=null;
//        while (check3) {
//            while (check31)
//            {
//                System.out.println("Please enter drivers ID:");
//                driverID = scanner.nextLine();
//                if(driverID.matches("[0-9]+"))
//                    check31 = false;
//                else
//                    System.out.println("Please enter numbers only");
//            }
//            check3 = Smanagement.checkID(driverID);
//            if (check3)
//                System.out.println("Driver already exist in the system");
//        }
//        System.out.println("Please enter driver's name:");
//        String driverName = scanner.nextLine();
//        check3 = true;
//        boolean bank_flag = false;
//        int bankNum = 0;
//        while (!bank_flag) {
//            System.out.println("please enter the new worker's bank number");
//            String input_newBank = scanner.nextLine();  // Read user input
//            try {
//                bankNum = Integer.parseInt(input_newBank);
//                bank_flag = true;
//            }
//            //if he entered something not suitable we will repeat
//            catch (Exception e) {
//                System.out.println("you entered wrong bank number (should be only numbers) - please try again!");
//            }
//        }
//        System.out.println("please enter the new worker's contract");
//        String input_newContract = scanner.nextLine();  // Read user input
//        System.out.println("please enter the new worker's wage");
//        int wage = AskForIntNumber();
//        char driverLicence = '0';
//        while(check3)
//        {
//            System.out.println("""
//                    Please enter licence type :
//                    C - for trucks under 12 ton
//                    D - for above 12 ton""");
//            driverLicence = scanner.next().charAt(0);
//            if(driverLicence == 'C' || driverLicence == 'D' || driverLicence == 'c' || driverLicence == 'd')
//                check3 = false;
//            else
//                System.out.println("Invalid input");
//        }
//        int training = 4;
//        while(training < 0 || training > 2) {
//            System.out.println("""
//                        Please enter driver storage capabilities training:
//                        0 - Regular
//                        1 - Cooling
//                        2 - Freezer""");
//            training = scanner.nextInt();
//            if(training < 0 || training > 2)
//                System.out.println("Please enter a number between 0 - 2");
//        }
//        driverLicence = Character.toUpperCase(driverLicence);
//        String genericPassword = "123";
//        Smanagement.addDriver(driverName,driverID,bankNum,input_newContract,wage,genericPassword ,driverLicence,training);
//        System.out.println("Driver added to the system");
//        scanner.nextLine();
//    }
    /**
     * this function gets input from the user about a driver to delete
     * and delete the driver from the system
     */
    public static void deleteDriver()
    {
        boolean check32 = true;
        String driversIDToRemove;
        while (check32) {
            System.out.println("Please enter drivers ID:");
            driversIDToRemove = scanner.nextLine();
            check32 = Smanagement.checkID(driversIDToRemove);
            if (!check32) {
                System.out.println("Driver does not exist in the system");
                return;
            }
            else {
                check32 = false;
                Smanagement.removeDriver(driversIDToRemove);
                System.out.println("driver has been removed from system");
            }
        }
    }
    /**
     * this function gets input from the user about a new order he wants to add
     * and adds the new order to the system
     */
    public static void addOrder()
    {
        boolean check4 =true,check41=true,check40=true, check401;
        String sourceSite;
        String destinationSite;
        int amount = 0,cho = 0;
        while (check40) {
            while (check4) {
                System.out.println("Please enter a Vendor as a source:");
                sourceSite = scanner.nextLine();
                if (Smanagement.checkVendor(sourceSite)) {
                    while (check41) {
                        System.out.println("Please enter Destination site:");
                        destinationSite = scanner.nextLine();
                        if (Smanagement.checkBranch(destinationSite)) {
                            Smanagement.createOrder(sourceSite, destinationSite);
                            while(cho != 3)
                            {
                                System.out.println("Item menu:");
                                System.out.println("1 - Add item");
                                System.out.println("2 - Print items added so far");
                                System.out.println("3 - Exit");
                                cho = scanner.nextInt();
                                switch (cho)
                                {
                                    case 1:
                                        System.out.println("Please enter item name:");
                                        String itemName;
                                        scanner.nextLine();
                                        itemName = scanner.nextLine();
                                        check401 = true;
                                        while(check401) {
                                            while (true) {
                                                System.out.println("Please enter the amount of the item you want to order:");
                                                if (scanner.hasNextInt()) {
                                                    amount = scanner.nextInt();
                                                    break;
                                                } else {
                                                    scanner.nextLine(); // consume the invalid input
                                                    System.out.println("Invalid input. Please enter an integer next");
                                                }
                                            }
                                            if(amount <= 0)
                                                System.out.println("please enter only a positive number");
                                            else
                                                check401 = false;
                                        }
                                        int training = 4;
                                        while(training < 0 || training > 2) {
                                            System.out.println("""
                                                    Please enter storage condition for the item:
                                                    0 - Regular
                                                    1 - Cooling
                                                    2 - Freezer""");
                                            training = scanner.nextInt();
                                            if(training < 0 || training > 2)
                                                System.out.println("Please enter a number between 0 - 2");
                                        }
                                        Smanagement.addItemToOrder(sourceSite,itemName,amount,training);
                                        System.out.println("Item add to order");
                                        break;
                                    case 2:
                                        Smanagement.printLastOrder(sourceSite);
                                        break;
                                    case 3:
                                        break;
                                    default:
                                        System.out.println("Invalid details");
                                }
                            }
                            System.out.println("Order was added to the system");
                            check4 = false;
                            check41 = false;
                            check40 = false;
                        }
                        else{
                            System.out.println("Destination does not exist in the system");}
                    }
                }
                else {
                    System.out.println("Source does not exist in the system");
                }

            }

        }
        scanner.nextLine();
    }

    /**
     * this function gets input from the user about a new shipment he wants to add
     * and adds the new shipment to the system
     */
    public static void addShipment()
    {
        LocalDate date = null;
        boolean check5 = true,check50 = true, check51 = true;
        int day;
        String shipmentID,vendor;
        while(check5)
        {
            while (true) {
                System.out.println("""
                    Please enter a day for the shipment:
                    1 - Sunday
                    2 - Monday
                    3 - Tuesday
                    4 - Wednesday
                    5 - Thursday
                    6 - Friday""");
                if (scanner.hasNextInt()) {
                    day = scanner.nextInt();
                    break;
                } else {
                    scanner.nextLine(); // consume the invalid input
                    System.out.println("Invalid input. Please enter an integer next");
                }
            }
            if(day < 1 || day > 6)
                System.out.println("please enter a number between 1 - 6 only");
            else {
                scanner.nextLine();
                while (check50)
                {
                    System.out.println("Please enter ID for the shipment:");
                    shipmentID = scanner.nextLine();
                    if(Smanagement.checkShipmentID(shipmentID))
                        System.out.println("Shipment ID already exist in the system");
                    else {
                        while(check51)
                        {
                            System.out.println("Please enter a vendor name:");
                            vendor = scanner.nextLine();
                            if(Smanagement.checkVendor(vendor))
                            {
                                Smanagement.createShipment(day, date ,shipmentID,vendor);
                                System.out.println("Shipment created");
                                check5 = false;
                                check50 = false;
                                check51 = false;
                            }
                            else
                                System.out.println("vendor does not exist in the system");
                        }

                    }

                }
            }
        }
    }
    public static void deleteShipment()
    {
        boolean check52=true;
        while (check52)
        {
            System.out.println("Please enter shipment ID to delete");
            String shipmentIDToDelete = scanner.nextLine();
            if(Smanagement.checkShipmentID(shipmentIDToDelete))
            {
                Smanagement.deleteShipment(shipmentIDToDelete);
                check52 = false;
            }
            else {
                System.out.println("Shipment does not exist in the system");
            }
        }
    }

    public static void executeShipment(){
        if (!Smanagement.checkAvailableShipment()){
            System.out.println("There are no available shipments");
            return;
        }
        //time formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time = null;
        while (time == null) {
            System.out.println("Enter the time (in HH:MM format): ");
            String timeStr = scanner.nextLine();

            try {
                time = LocalTime.parse(timeStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please enter time in HH:MM format.");
            }
        }
        //reading the truck weight from the user and checking if it is valid
        int currWeight;
        System.out.println("you have arrived at your destination: ");
        while (true){
            try{
                System.out.println("Please enter the weight of the truck with the items (in KG): ");
                currWeight = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please enter an actual number");
            }
        }
        while (Smanagement.checkTruckWeight(currWeight)){
            String input = "0";
            while (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
                System.out.println("The truck exceeded the max carry weight, in order to proceed with the shipment\n" +
                        "please choose one of the options: ");
                System.out.println("1. Take out some of the items");
                System.out.println("2. Switch to a bigger truck");
                System.out.println("3. remove the last site from this shipment");
                input = scanner.nextLine();
                switch (input) {
                    case "1" -> {
                        //todo maybe check if the shipment was cancelled
                        itemsToDelete();
                        //shipment.setShipmentStatus(Status.ItemsChange);
                    }
                    case "2" -> {
                        Smanagement.changeTruck();
//                        if (!Objects.equals(currTruck.getTruckNumber(), shipment.getTruckNumber()))
//                            shipment.setShipmentStatus(Status.TruckExchange);
//                        else {
//                            System.out.println("There isn't a bigger truck available at the moment");
//                        }
//                        currTruck = searchTruckByID(shipment.getTruckNumber());
                    }
                    case "3" -> {
                        if (Smanagement.removeLastSiteFromShipment())
                            break;
                        System.out.println("There is only one site in the shipment");
                    }
                }
            }
            while (true){
                try{
                    int save = currWeight;
                    System.out.println("Please enter the weight of the truck with the items (in KG): ");
                    currWeight = Integer.parseInt(scanner.nextLine());
                    if (save > currWeight) {
                        System.out.println("Please input the lower number then the last weight");
                        currWeight = save;
                        continue;
                    }
                    break;
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Please enter an actual number");
                }
            }
        }
    }
    private static void itemsToDelete(){
        int amount;
        System.out.println("Those are the branches of the shipments: ");
        Smanagement.printSiteOfShipment();
        System.out.println("Please choose the one you want to delete the item from, enter the name of the site: ");
        String answer = scanner.nextLine();
        while(!Smanagement.checkSiteID(answer)){
            System.out.println("This site is not exist, please enter again");
            answer = scanner.nextLine();
        }
        Smanagement.printItemsDoc(answer);
        while(true){
            System.out.println("Please choose an item you want to delete:");
            String itemName = scanner.nextLine();
            while(true) {
                try {
                    System.out.println("Please enter the amount: ");
                    amount = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter an actual number");
                }
            }
            if (Smanagement.deleteItemFromShipment(itemName, amount,answer))
                break;
            else{
                System.out.println("The item was not found");
            }
        }

    }

}








