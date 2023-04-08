import java.awt.*;
import java.util.Scanner;

public class UI {

    public void shippingMenu()
    {
        char choice ='a';
        shipmentManagement Smanagement = new shipmentManagement();
        while(choice != '6') {
            System.out.println("Shipping Main Menu:");
            System.out.println("1 - Site Menu");
            System.out.println("2 - Truck Menu");
            System.out.println("3 - Drivers Menu");
            System.out.println("4 - Order Menu");
            System.out.println("5 - Shipping Menu");
            System.out.println("6 - Exit");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.next().charAt(0);
            switch (choice)
            {
                case '1':
                    int ch=0;
                    while(ch != 5) {
                        System.out.println("Site Menu:");
                        System.out.println("1 - Add Site");
                        System.out.println("2 - Remove Site");
                        System.out.println("3 - Print All Sites ");
                        System.out.println("4 - Update site info");
                        System.out.println("5 - Exit");
                        ch = scanner.nextInt();
                        switch (ch) {
                            case 1:
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
                                String sitePhoneNumber = "000000000";
                                while(sitePhoneNumber.length() != 10 && !sitePhoneNumber.matches("[0-9]+")) {
                                    System.out.println("Please enter site phone number (10 digits - only numbers):");
                                    sitePhoneNumber = scanner.nextLine();
                                }
                                System.out.println("Please enter contact name");
                                String contactName = scanner.nextLine();
                                check = true;
                                while (check)
                                {
                                    System.out.println("Please enter site type:\n" +
                                            "1 - Vendor\n2 - Branch");
                                    int siteType = scanner.nextInt();
                                    if(siteType == 1) {
                                        Smanagement.addVendor(siteName, siteAddress, sitePhoneNumber, contactName);
                                        check = false;
                                    }
                                    else if (siteType == 2) {
                                        int zone = 4;
                                        while(zone < 0 || zone > 2) {
                                            System.out.println("Please enter branch zone:\n" +
                                                    "0 - North\n1 - Center\n2 - South");
                                            zone = scanner.nextInt();
                                            if(zone < 0 || zone > 2)
                                                System.out.println("Please enter a number between 0 - 2");
                                        }
                                        Smanagement.addBranch(siteName,siteAddress,sitePhoneNumber,contactName,zone);
                                        check = false;
                                    }
                                }
                                break;
                            case 2:
                                boolean check1 = true;
                                while (check1)
                                {
                                    System.out.println("Please enter site name you want to remove");
                                    String siteToDelete = scanner.nextLine();
                                    if(Smanagement.checkSite(siteToDelete)) {
                                        Smanagement.deleteSite(siteToDelete);
                                        System.out.println("site deleted");
                                        check1 = false;
                                    }
                                    else {
                                        System.out.println("Site does not exist in the system");
                                        System.out.println("do you still want to delete a site?");
                                        System.out.println("1 - yes\n2 - no");
                                        int ch1 = scanner.nextInt();
                                        if(ch1 == 2)
                                            check1 = false;
                                    }
                                }
                                break;
                            case 3:
                                // todo :prints all sites
                                break;
                            case 4:
                                // todo: update site
                                break;
                            case 5:
                                ch= 5;
                                break;
                            default:
                                System.out.println("please enter a number between 1 - 5");
                                break;
                        }
                    }
                    break;
                case '2':
                    int ch2=0;
                    while(ch2 != 4) {
                        System.out.println("Truck Menu:");
                        System.out.println("1 - Add Truck");
                        System.out.println("2 - Delete Site");
                        System.out.println("3 - Print All Trucks");
                        System.out.println("4 - Exit");
                        ch2 = scanner.nextInt();
                        switch (ch2) {
                            case 1:
                                boolean check2 = true;
                                String truckNumber = null;
                                while (check2) {
                                    System.out.println("Please enter the truck number");
                                    truckNumber = scanner.nextLine();
                                    check2 = Smanagement.checkTruckNumber(truckNumber);
                                    if (check2)
                                        System.out.println("Truck already exist in the system");
                                }
                                System.out.println("Please enter the truck model");
                                String truckModel = scanner.nextLine();
                                System.out.println("Please Enter the truck weight in KG");
                                int truckWeight = scanner.nextInt();
                                System.out.println("Please enter the truck maximum carry weight in KG");
                                int truckCarryWeight = scanner.nextInt();
                                int storage = 4;
                                while(storage < 0 || storage > 2) {
                                    System.out.println("Please enter truck storage capabilities:\n" +
                                            "0 - Regular\n1 - Cooling\n2 - Freezer");
                                    storage = scanner.nextInt();
                                    if(storage < 0 || storage > 2)
                                        System.out.println("Please enter a number between 0 - 2");
                                }
                                Smanagement.addTruck(truckNumber,truckWeight,truckCarryWeight,truckModel,storage);
                                break;
                            case 2:
                                check2 = true;
                                while (check2)
                                {
                                    System.out.println("Please enter the truck number");
                                    truckNumber = scanner.nextLine();
                                    if(Smanagement.checkTruckNumber(truckNumber)) {
                                        Smanagement.removeTruck(truckNumber);
                                        check2 = false;
                                        System.out.println("truck removed from system");
                                    }
                                    else {
                                        System.out.println("truck number does not exist in the system");
                                        System.out.println("do you still want to delete a truck?");
                                        System.out.println("1 - yes\n2 - no");
                                        int ch1 = scanner.nextInt();
                                        if(ch1 == 2)
                                            check2 = false;
                                    }
                                }
                                break;
                            case 3:
                                Smanagement.printTrucks();
                                break;
                            case 4:
                                ch2 = 4;
                                break;
                            default:
                                System.out.println("Invalid input");
                        }
                    }
                    break;
                case '3':
                    int ch3=0;
                    while(ch3 != 5) {
                        System.out.println("Drivers Menu:");
                        System.out.println("1 - Add driver");
                        System.out.println("2 - remove driver");
                        System.out.println("3 - Print All drivers");
                        System.out.println("4 - Update driver licence/training");
                        System.out.println("5 - Exist");
                        ch3 = scanner.nextInt();
                        switch (ch3)
                        {
                            case 1 :
                                boolean check3 = true;
                                String driverID = null;
                                while (check3) {
                                    System.out.println("Please enter drivers ID");
                                    driverID = scanner.nextLine();
                                    check3 = Smanagement.checkID(driverID);
                                    if (check3)
                                        System.out.println("Driver already exist in the system");
                                }
                                System.out.println("Please enter driver's name:");
                                String driverName = scanner.nextLine();
                                check3 = true;
                                char driverLicence = 0;
                                while(check3)
                                {
                                    System.out.println("Please enter licence type :\n" +
                                            "c - for trucks under 12 ton\nd - for above 12 ton");
                                    driverLicence = scanner.next().charAt(0);
                                    if(driverLicence == 'c' || driverLicence == 'd')
                                        check3 = false;
                                    else
                                        System.out.println("Invalid input");
                                }
                                int training = 4;
                                while(training < 0 || training > 2) {
                                    System.out.println("Please enter driver storage capabilities training:\n" +
                                            "0 - Regular\n1 - Cooling\n2 - Freezer");
                                    training = scanner.nextInt();
                                    if(training < 0 || training > 2)
                                        System.out.println("Please enter a number between 0 - 2");
                                }
                                Smanagement.addDriver(driverName,driverID,driverLicence,training);
                                break;
                            case 2:
                                boolean check32 = true;
                                String driversIDToRemove;
                                while (check32) {
                                    System.out.println("Please enter drivers ID");
                                    driversIDToRemove = scanner.nextLine();
                                    check32 = Smanagement.checkID(driversIDToRemove);
                                    if (!check32) {
                                        System.out.println("Driver does not exist in the system");
                                        System.out.println("do you still want to remove a driver?");
                                        System.out.println("1 - yes\n2 - no");//not checking input
                                        int ch1 = scanner.nextInt();
                                        if(ch1 == 1)
                                            check32 = true;
                                    }
                                    else {
                                        check32 = false;
                                        Smanagement.removeDriver(driversIDToRemove);
                                        System.out.println("driver removed from system");
                                    }
                                }
                                break;
                            case 3:
                                Smanagement.printDrivers();
                                break;
                            case 4:
                                //todo: update driver details
                                break;
                            case 5:
                                ch3 = 5;
                                break;
                            default:
                                System.out.println("Invalid input");
                                break;
                        }
                    }
                    break;
                case '4':
                    int ch4=0;
                    while(ch4 != 5) {
                        System.out.println("Order Menu:");
                        System.out.println("1 - Add order");
                        System.out.println("2 - Delete order ");
                        System.out.println("3 - Print All orders");
                        System.out.println("4 - Add item to order");
                        System.out.println("5 - Exist");
                        ch4 = scanner.nextInt();
                        switch (ch4){
                            case 1:
                                boolean check4 =true,check41=true,check40=true;
                                String sourceSite;
                                String destinationSite;
                                String OrderID;
                                while (check40) {
                                    System.out.println("Please enter order ID");
                                    OrderID = scanner.nextLine();
                                    if(!Smanagement.checkOrderID(OrderID)) {
                                        while (check4) {
                                            System.out.println("Please enter a Vendor as a source");
                                            sourceSite = scanner.nextLine();
                                            if (Smanagement.checkVendor(sourceSite)) {
                                                while (check41) {
                                                    System.out.println("Please enter Destination site");
                                                    destinationSite = scanner.nextLine();
                                                    if (Smanagement.checkBranch(destinationSite)) {
                                                        Smanagement.createOrder(sourceSite, destinationSite);
                                                        System.out.println("Order was added to the system");
                                                        check4 = false;
                                                        check41 = false;
                                                        check40 = false;
                                                    } else
                                                        System.out.println("Destination does not exist in the system");
                                                }
                                            } else
                                                System.out.println("Source does not exist in the system");

                                        }
                                    }
                                    else
                                        System.out.println("Order ID does not exist in the system");

                                }
                                break;
                            case 2:
                                boolean check42 = true, check420 = true;
                                String orderIDToRemove;
                                String source;
                                while (check420) {
                                    System.out.println("Please enter a vendor name to delete an order from");
                                    source = scanner.nextLine();
                                    if(Smanagement.checkVendor(source)) {
                                        while (check42) {
                                            System.out.println("Please enter order ID");
                                            orderIDToRemove = scanner.nextLine();
                                            if (!Smanagement.checkOrderID(orderIDToRemove)){
                                                System.out.println("Order does not exist in the system");
                                                System.out.println("do you still want to remove an order?");
                                                System.out.println("1 - yes\n2 - no");//not checking input
                                                int ch42 = scanner.nextInt();
                                                if (ch42 == 2)
                                                    check42 = false;
                                            }
                                            else
                                            {
                                                Smanagement.removeOrder(orderIDToRemove);
                                                System.out.println("Order removed from system");
                                                check420 = false;
                                            }
                                        }
                                    }
                                    else
                                        System.out.println("Vendor does not exist in the system");
                                }
                                break;
                            case 3:
                                //todo:print all orders
                                break;
                            case 4:
                                boolean check44 = true, check441=true;
                                String source1;
                                String itemName;
                                int amount=0;
                                while(check44)
                                {
                                    System.out.println("Please enter the vendor name you want to add an item from");
                                    source1 = scanner.nextLine();
                                    if(Smanagement.checkVendor(source1))
                                    {
                                        System.out.println("Please enter an item name");
                                        itemName = scanner.nextLine();
                                        while(check441) {
                                            System.out.println("Please enter the amount of the item you want to order");
                                            amount = scanner.nextInt();
                                            if(amount <= 0)
                                                System.out.println("please enter only a positive number");
                                            else
                                                check441 = false;
                                        }
                                        int training = 4;
                                        while(training < 0 || training > 2) {
                                            System.out.println("Please enter storage condition for the item:\n" +
                                                    "0 - Regular\n1 - Cooling\n2 - Freezer");
                                            training = scanner.nextInt();
                                            if(training < 0 || training > 2)
                                                System.out.println("Please enter a number between 0 - 2");
                                        }
                                        Smanagement.addItemToOrder(source1,itemName,amount,training);
                                        System.out.println("Item add to order");
                                        check44 =false;
                                    }
                                    else
                                        System.out.println("Vendor does not exist in the system");
                                }
                                break;
                            default:
                                System.out.println("Invalid input");
                                break;
                        }
                    }
                    break;

                case '5':
                    int ch5=0;
                    while(ch5 != 6) {
                        System.out.println("Shipment  Menu:");
                        System.out.println("1 - Add shipment");
                        System.out.println("2 - Delete shipment");
                        System.out.println("3 - Print All shipment");
                        System.out.println("4 - execute nearest shipment");
                        System.out.println("5 - execute shipment by ID");
                        System.out.println("6 - Exit");
                        ch5 = scanner.nextInt();
                        switch (ch5) {
                            case 1:
                                boolean check5 = true,check50 = true, check51 = true;
                                int day;
                                String shipmentID,vendor;
                                while(check5)
                                {
                                    System.out.println("Please enter a day for the shipment:" +
                                            "1 - Sunday\n" +
                                            "2 - Monday\n" +
                                            "3 - Tuesday\n" +
                                            "4 - Wednesday\n" +
                                            "5 - Thursday\n" +
                                            "6 - Friday");
                                    day = scanner.nextInt();
                                    if(day < 1 || day > 6)
                                        System.out.println("please enter a number between 1 - 6 only");
                                    else {
                                        while (check50)
                                        {
                                            System.out.println("Please enter ID for the shipment");
                                            shipmentID = scanner.nextLine();
                                            if(Smanagement.checkShipmentID(shipmentID))
                                                System.out.println("Shipment ID already exist in the system");
                                            else {
                                                while(check51)
                                                {
                                                    System.out.println("Please enter a vendor name");
                                                    vendor = scanner.nextLine();
                                                    if(Smanagement.checkVendor(vendor))
                                                    {
                                                        Smanagement.createShipment(day,shipmentID,vendor);
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
                                break;
                            case 2:
                                boolean check52=true, check53=true;
                                while (check52)
                                {
                                    System.out.println("Please enter shipment ID to delete");
                                    String shipmentIDToDelete = scanner.nextLine();
                                    if(Smanagement.checkShipmentID(shipmentIDToDelete))
                                    {
                                        Smanagement.deleteShipment(shipmentIDToDelete);
                                        System.out.println("Shipment deleted");
                                        check52 = false;
                                    }
                                    else {
                                        System.out.println("Shipment does not exist in the system");
                                        System.out.println("do you still want to delete a shipment?");
                                        System.out.println("1 - yes\n2 - no");//not checking input
                                        int ch1 = scanner.nextInt();
                                        if(ch1 == 2)
                                            check52 = false;
                                    }
                                }
                                break;
                            case 3:
                                Smanagement.printShipments();
                                break;
                            case 4:
                                //todo: function or inputs from the user
                                break;
                            case 5:
                                boolean check55 = true;
                                String shipmentIDToExecute;
                                while (check55)
                                {
                                    System.out.println("Please enter  shipment ID to execute");
                                    shipmentIDToExecute = scanner.nextLine();
                                    if(Smanagement.checkShipmentID(shipmentIDToExecute)) {
                                        Smanagement.executeShipment(shipmentIDToExecute);
                                        check55  =false;
                                    }
                                    else
                                        System.out.println("Shipment ID does not exist in the system");
                                }
                                break;
                            case 6:
                                ch5 = 6;
                                break;
                            default:
                                System.out.println("Invalid input");
                                break;
                        }
                    }
                    break;
                case '6':
                    choice = '6';
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }


}
