import java.util.Scanner;

public class UI {

    public void shippingMenu()
    {
        char choice ='a';
        shipmentManagement Smanagement = new shipmentManagement();
        while(choice != '0') {
            System.out.println("Shipping Main Menu:");
            System.out.println("1 - Site Menu");
            System.out.println("2 - Truck Menu");
            System.out.println("3 - Drivers Menu");
            System.out.println("4 - Shipping Menu");
            System.out.println(("5 - Order Menu"));
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
                                String driversIDToRemove = null;
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

                    break;
                case '5':

                    break;
                case '6':

                    break;
                default:
                    break;
            }
        }
    }


}
