import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SupplierCard {
    private String supplier_name;
    private String address;
    private int supplier_number;
    private int company_number;
    private int bank_account;
    private Payment_method payment_method;

    //private List<ContactMember> contact_members;
    private LinkedList<ContactMember> contact_members;
    //private List<String> product_categories;
    private LinkedList<String> product_categories;


    Scanner input = new Scanner(System.in);

    public SupplierCard(String name, int supplier_number, int company_number, int bank_account, String address) {
        this.supplier_number = supplier_number;
        this.company_number = company_number;
        this.bank_account = bank_account;
        this.address = address;
        this.payment_method = null;
        this.contact_members = new LinkedList<>();
        this.product_categories = new LinkedList<>();

    }

    public SupplierCard(String supplier_name, int supplier_number, int company_number, int bank_account, String address,Payment_method payment_method, LinkedList<ContactMember> contact_members, LinkedList<String> product_categories) {
        this.supplier_name = supplier_name;
        this.address = address;
        this.supplier_number = supplier_number;
        this.company_number = company_number;
        this.bank_account = bank_account;
        this.payment_method = payment_method;
        this.contact_members = contact_members;
        this.product_categories = product_categories;
    }

//    public SupplierCard(String name, int supplier_number, int company_number, int bank_account, String address, Payment_method payment_method, List<ContactMember> contact_members, List<String> product_categories) {
//        this.supplier_number = supplier_number;
//        this.supplier_name = name;
//        this.company_number = company_number;
//        this.bank_account = bank_account;
//        this.payment_method = payment_method;
//        this.contact_members = contact_members;
//        this.product_categories = new LinkedList<>();
//        this.address = address;
//
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSupplier_number() {
        return supplier_number;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public int getCompany_number() {

        return company_number;
    }

    public void setCompany_number(int company_number) {
        this.company_number = company_number;
    }

    public int getBank_account() {
        return bank_account;
    }

    public void setBank_account(int bank_account) {
        this.bank_account = bank_account;
    }

    public Payment_method getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(int x) {
        if (x == 1)
            this.payment_method = Payment_method.cash;
        else if (x == 2) {
            this.payment_method = Payment_method.bit;
        } else
            this.payment_method = Payment_method.credit_card;
    }

    public List<ContactMember> getContact_members() {
        return contact_members;
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    public void addContact_members() {
        System.out.println("Enter the name:");
        String name = input.nextLine();
        System.out.println("Enter the email:");
        String email = input.nextLine();
        System.out.println("Enter the phone number:");
        String phone = input.nextLine();
        for (int i = 0; i < this.contact_members.size(); i++) {
            if (phone.equals(this.contact_members.get(i).getPhone_number())) {
                System.out.println("This phone number is invalid!");
                return;
            }

        }
        ContactMember x = new ContactMember(phone, name, email);
        this.contact_members.add(x);
    }

    public void removeContact_members() {
        boolean exist = false;
        System.out.println("Enter the phone number of the member");
        String pNum = input.nextLine();
        for (ContactMember member : contact_members) {
            if (member.getPhone_number().equals(pNum)) {
                System.out.println("Remove Successfully");
                exist = true;
                contact_members.remove(member);
                break;
            }
        }
        if (exist == false)
            System.out.println("There is not contact member with this phone number");

    }

    public void editContact_members() {
        Scanner input = new Scanner(System.in);
        boolean exist = false;
        System.out.println("Enter the phone number of the member");
        String pNum = input.nextLine();
        for (ContactMember member : contact_members) {
            if (member.getPhone_number().equals(pNum)) {
                exist=true;
                int choice = 9;
                while (choice != 0) {
                    System.out.println("1. Edit name");
                    System.out.println("2. Edit phone number");
                    System.out.println("3. Edit mail address");
                    System.out.println("0. Quit");
                    choice=input.nextInt();

                    switch (choice) {
                        case 1: {
                            Scanner input2 = new Scanner(System.in);
                            System.out.println("Enter the new name");
                            String name=input2.nextLine();
                            member.setName(name);
                            break;
                        }
                        case 2: {
                            Scanner input2 = new Scanner(System.in);
                            System.out.println("Enter the new phone number");
                            String num=input2.nextLine();
                            member.setPhone_number(num);
                            break;
                        }
                        case 3: {
                            Scanner input2 = new Scanner(System.in);
                            System.out.println("Enter the new email");
                            String m=input2.nextLine();
                            member.setEmail(m);
                            break;
                        }
                        default:
                        {
                            if(choice!=0)
                                System.out.println("invalid choice, choose again");
                            break;
                        }

                    }
                }


            }
        }
        if (exist == false)
            System.out.println("There is not contact member with this phone number");

    }

    public void printContacts()
    {
        if(contact_members.isEmpty())
            System.out.println("Contact member list is empty");
        else
        {
            System.out.println("Contact member INFO:");
        }
        for(ContactMember c: contact_members)
        {
            c.printContactMember();
        }
    }
    public void EditContactsMembers() {
        Scanner input = new Scanner(System.in);
        int choice = 9;
        while (choice != 0) {
            System.out.println("1. Add new contact member");
            System.out.println("2. Remove a contact member");
            System.out.println("3. Edit an exist contact member");
            System.out.println("0. Quit");
            choice = input.nextInt();
            switch (choice) {
                case 1: {
                    addContact_members();
                    printContacts();
                    break;
                }
                case 2: {
                    removeContact_members();
                    printContacts();
                    break;
                }
                case 3:
                {
                    editContact_members();
                    printContacts();
                    break;
                }

                default:
                {
                    if(choice!=0)
                        System.out.println("invalid choice, choose again");
                    break;
                }

            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    public void printCategories()
    {
        int i=1;
        if(product_categories.isEmpty()==false)
            System.out.println("---Categories of supplier: "+supplier_number+" ---");
        else {
            System.out.println("Category list is empty!");
        }
        for (String s:product_categories)
        {
            System.out.println(i+". "+s);
            i++;
        }
        System.out.println();
    }
    ///////////////////////////////////////////////////////////////////////////
    public void EditCategories() {
        Scanner input = new Scanner(System.in);
        int choice = 9;
        while (choice != 0) {
            System.out.println("1. Add category");
            System.out.println("2. Remove category");
            System.out.println("0. Quit");
            choice = input.nextInt();
            switch (choice) {
                case 1: {
                    Scanner input2 = new Scanner(System.in);
                    System.out.println("Enter the new category");
                    String x = input2.nextLine();
                    addCategory_ToSupplier(x);
                    printCategories();
                    break;
                }

                case 2: {
                    Scanner input2 = new Scanner(System.in);
                    System.out.println("Enter the category you want to remove");
                    String x = input2.nextLine();
                    removeCategory_ToSupplier(x);
                    printCategories();
                    break;
                }

                default:
                {
                    if(choice!=0)
                        System.out.println("invalid choice, choose again");
                    break;
                }
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////
    public List<String> getProduct_categories() {
        return product_categories;
    }

    public void addCategory_ToSupplier(String category) {
        if (this.product_categories.contains(category)) {
            System.out.println("This category already exist!");
        }
        else
        {
            this.product_categories.add(category);
            System.out.println("Category add successfully\n");

        }
    }


    public void removeCategory_ToSupplier(String Category) {
        if (!this.product_categories.contains(Category)) {
            System.out.println("This category is not exist!");
        } else {
            this.product_categories.remove(Category);
            System.out.println("Category removed successfully\n");

        }
    }

}
