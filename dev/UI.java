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
                                while(check) {
                                    System.out.println("Please enter site name:");
                                    String siteName = scanner.nextLine();
                                    check = Smanagement.checkSite(siteName);
                                    if (check)
                                        System.out.println("Site already exist please enter a new name");
                                    else
                                        check = false;
                                }
                                System.out.println("Please enter Site Address:");
                                String siteAddress = scanner.nextLine();
                                String sitePhoneNumber = "000000000";
                                while(sitePhoneNumber.length() != 10 && sitePhoneNumber.matches("[0-9]+")) {
                                    System.out.println("Please enter site phone number (10 digits - only numbers):");
                                    sitePhoneNumber = scanner.nextLine();
                                }
                                System.out.println("Please enter contact name");
                                String contactName = scanner.nextLine();
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
                                ch = 5;
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
                        switch (ch2)
                        {
                            case 1:
                                System.out.println("Please enter the truck number");
                                String truckNumber = scanner.nextLine();
                                System.out.println("Please Enter the truck weight in KG");
                                int truckWeight = scanner.nextInt();
                                System.out.println("Plese enter the truck maximum carry weight in KG");
                                int truckCarryWeight = scanner.nextInt();


                                break;
                        }
                    }
                    break;
                case '3':

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
