package Shipment.Presentation;


import Shipment.Bussiness.shipmentManagement;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.time.temporal.TemporalAdjusters;

import java.util.Scanner;


public class UI {
    private static shipmentManagement sManagement = shipmentManagement.getInstance();
    private static Scanner scanner;
    public static void SManagerLogIN(Scanner sc) {
        scanner = sc;
        int choice = -1;
        System.out.println("please log in: ");
        while (choice != 4) {
            System.out.println("Password: ");
            String input = scanner.nextLine();  // Read user input
            if (!sManagement.checkPassword(input)) {
                System.out.println("Wrong password please try again");
                continue;
            } else {
                System.out.println("welcome Shipment Manager!");
            }
            shippingMenu(sc);
            choice = 4;
        }
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
                        System.out.println("1 - Add Vendor");
                        System.out.println("2 - Print All Sites (vendors and branches)");
                        System.out.println("3 - Update site info");
                        System.out.println("4 - Exit");
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

                            case 2:
                                sManagement.printSites();
                                break;
                            case 3:
                                updateSite();
                                break;
                            case 4:
                                chF=false;
                                break;
                            default:
                                System.out.println("please enter a number between 1 - 5");
                        }
                    }
                    break;
                case "2":
                    int ch2=0;
                    while(ch2 != 3) {
                        System.out.println("Truck Menu:");
                        System.out.println("1 - Add Truck");
                        System.out.println("2 - Print All Trucks");
                        System.out.println("3 - Exit");
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
                                sManagement.printTrucks();
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Invalid input");
                        }
                    }
                    break;
                case "3":
                    int ch3=0;
                    while(ch3 != 4) {
                        System.out.println("Drivers Menu:");
                        System.out.println("1 - Print All drivers");
                        System.out.println("2 - Update driver licence");
                        System.out.println("3 - Update driver training");
                        System.out.println("4 - Exit");
                        try{
                            ch3 = Integer.parseInt(scanner.nextLine());
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Please enter a number from the menu");
                        }
                        switch (ch3)
                        {
                            case 1:
                                sManagement.printDrivers();
                                break;
                            case 2:
                                updateDriverLicence();
                                break;
                            case 3:
                                updateDriverTraining();
                                break;
                            case 4:
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
                                sManagement.printOrders();
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
                                sManagement.printShipments();
                                break;
                            case 4:
                                sManagement.printAvailableShipments();
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
                    sManagement.printAllDocs();
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
                    sManagement.updateDriverLicence(driverID,driverLicence);
                }
                else
                    System.out.println("Invalid input");
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
                    sManagement.updateDriverTraining(driverID,training);
                }
                else
                    System.out.println("Invalid input");
            }
        }
        scanner.nextLine();
        }

    /**
     * this function gets input from the user about a new site he wants to add
     * and adds the new site to the system
     */
    public static void addSite() {
        boolean check = true;
        String siteName = null;
        while (check) {
            System.out.println("Please enter Vendor name:");
            siteName = scanner.nextLine();
            check = sManagement.checkVendor(siteName);
            if (check)
                System.out.println("Vendor already exist please enter a new name");
        }
        System.out.println("Please enter Vendor Address:");
        String siteAddress = scanner.nextLine();
        String sitePhoneNumber;
        System.out.println("Please enter site phone number (10 digits - only numbers):");
        sitePhoneNumber = scanner.nextLine();
        while (sitePhoneNumber.length() != 10 || !sitePhoneNumber.matches("[0-9]+")) {
            System.out.println("Please enter site phone number (10 digits - only numbers):");
            sitePhoneNumber = scanner.nextLine();
        }
        System.out.println("Please enter contact name:");
        String contactName = scanner.nextLine();
        sManagement.addVendor(siteName, siteAddress, sitePhoneNumber, contactName);
        System.out.println("Vendor added to the system");


     }
    /**
     * this function gets input from the user about a new truck he wants to add
     * and adds the new truck to the system
     */

    //todo fix it
    public static void updateSite()
    {
        System.out.println("Please enter the name of the site you would like to update:");
        String siteName = scanner.nextLine();
        if(sManagement.checkBranch(siteName) || sManagement.checkVendor(siteName)){
            while (true) {
                System.out.println("""
                        What would you like to change:
                        1 - Site address
                        2 - Contact name
                        3 - Contact phone number""");
                String str = scanner.nextLine();
                try {
                    int num = Integer.parseInt(str);
                    if (num >= 1 && num <= 3) {
                        System.out.println("Enter the new details");
                        String change = scanner.nextLine();
                        sManagement.updateSite(siteName,change,num);
                        return;
                    }
                    else {
                        System.out.println("The string must be a number between 1 and 3.");
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
            check2 = sManagement.checkTruckNumber(truckNumber);
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
        sManagement.addTruck(truckNumber,truckCarryWeight, truckWeight,truckModel,storage);
        System.out.println("Truck added to the system");
        scanner.nextLine();
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
                if (sManagement.checkVendor(sourceSite)) {
                    while (check41) {
                        System.out.println("Please enter Destination site:");
                        destinationSite = scanner.nextLine();
                        if (sManagement.checkBranch(destinationSite)) {
                            sManagement.createOrder(sourceSite, destinationSite);
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
                                        sManagement.addItemToOrder(sourceSite,itemName,amount,training);
                                        System.out.println("Item add to order");
                                        break;
                                    case 2:
                                        sManagement.printLastOrder(sourceSite);
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
        LocalDate currentDate = LocalDate.now();
        LocalDate runningDate = LocalDate.now();
        LocalDate closestSunday = currentDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        LocalDate nextFriday = currentDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        boolean check50 = true, check51 = true;
        int day;
        String shipmentID, vendor;
        String week;
        int startFrom;
        int choice;
        while (true) {
            if (currentDate.isEqual(nextFriday) || currentDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                week = "2";
            } else {
                System.out.println("You want the Shipment to be scheduled for this week or next week?");
                System.out.println("""
                        1. This Week
                        2. Next Week""");
                week = scanner.nextLine();
            }
            if (week.equals("1")) {
                System.out.println("Please choose and pick a date: ");
                while (runningDate.isBefore(nextFriday)) {
                    runningDate = runningDate.plusDays(1);
                    System.out.println((runningDate.getDayOfWeek().ordinal() + 1) + " - " + runningDate.getDayOfWeek() + ": " + runningDate);
                }
                choice = Integer.parseInt(scanner.nextLine());
                if (currentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                    startFrom = 0;
                else{
                    startFrom = currentDate.getDayOfWeek().ordinal();
                }
                while (!(startFrom < choice && choice <= runningDate.getDayOfWeek().ordinal())) {
                    System.out.println("Please choose a valid number: ");
                    choice = Integer.parseInt(scanner.nextLine());
                }
                runningDate = LocalDate.now().plusDays(choice);
                day = runningDate.getDayOfWeek().ordinal() + 1;
                break;
            }
            else if (week.equals("2")) {
                System.out.println("Please choose an pick a date: ");
                LocalDate dateToMove = closestSunday;
                for (int i=0 ; i < 6; i++){
                    System.out.println(i + " - " + dateToMove.getDayOfWeek() + ": " + dateToMove);
                    dateToMove = dateToMove.plusDays(1);
                }
                while (true) {
                    day = Integer.parseInt(scanner.nextLine());
                    if (day < 0 || day > 5) {
                        System.out.println("please enter a number between 1 - 5 only");
                    }
                    else {
                        runningDate = closestSunday.plusDays(day);
                        break;
                    }
                }
                break;
            }
        }
        while (check50)
        {
            System.out.println("Please enter ID for the shipment:");
            shipmentID = scanner.nextLine();
            if(sManagement.checkShipmentID(shipmentID))
                System.out.println("Shipment ID already exist in the system");
            else {
                while(check51)
                {
                    System.out.println("Please enter a vendor name:");
                    vendor = scanner.nextLine();
                    if(sManagement.checkVendor(vendor))
                    {
                        if (sManagement.createShipment(day, runningDate ,shipmentID,vendor))
                            System.out.println("Shipment created");
                        else{System.out.println("cant create shipment");
                        }
                        check50 = false;
                        check51 = false;
                    }
                    else
                        System.out.println("vendor does not exist in the system");
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
            if(sManagement.checkShipmentID(shipmentIDToDelete))
            {
                sManagement.deleteShipment(shipmentIDToDelete);
                check52 = false;
            }
            else {
                System.out.println("Shipment does not exist in the system");
            }
        }
    }

    public static void executeShipment(){
        int save;
        if (sManagement.checkAvailableShipment()){
            System.out.println("There are no available shipments");
            return;
        }
        if(!sManagement.checkExecuteNow()){
            System.out.println("There is no storekeeper assigned to all the branches shifts so you cant execute now");
            return;
        }
        if(!sManagement.checkIfDriverExist())
        {
            System.out.println("There is no driver assigned to this shipment please update the weekly shift");
            return;
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
        while (sManagement.checkTruckWeight(currWeight)){
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
                        itemsToDelete();
                    }
                    case "2" -> {
                        if (!sManagement.changeTruck()){
                            System.out.println("couldn't switch truck for this shipment at this moment");
                        }
                        else{

                        }
                    }
                    case "3" -> {
                        if (sManagement.removeLastSiteFromShipment())
                            break;
                        System.out.println("There is only one site in the shipment");
                    }
                }
            }
            while (true){
                try{
                    save = currWeight;
                    System.out.println("Please enter the weight of the truck with the items (in KG): ");
                    currWeight = Integer.parseInt(scanner.nextLine());
                    if (save < currWeight) {
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
        System.out.println("The Items Were delivered to their destinations");
        sManagement.updateShipment();
    }
    private static void itemsToDelete(){
        int amount;
        System.out.println("Those are the branches of the shipments: ");
        sManagement.printSiteOfShipment();
        System.out.println("Please choose the one you want to delete the item from, enter the name of the site: ");
        String answer = scanner.nextLine();
        while(!sManagement.checkSiteID(answer)){
            System.out.println("This site is not exist, please enter again");
            answer = scanner.nextLine();
        }
        sManagement.printItemsDoc(answer);
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
            if (sManagement.deleteItemFromShipment(itemName, amount,answer))
                break;
            else{
                System.out.println("The item was not found");
            }
        }

    }

}








