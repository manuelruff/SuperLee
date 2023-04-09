import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class SupplyManager {
    private static SupplyManager supply_manager = null;
    private Map<Integer, Supplier> suppliers_list;
    private Map<String, LinkedList<Supplier>>SupplierByProduct ;
    private Scanner input = new Scanner(System.in);


    // constructor
    private SupplyManager()
    {
        suppliers_list = new HashMap<>();
        SupplierByProduct= new HashMap<>();
    }

    // I use here singleton design because i want only one appereance of this instance
    public static SupplyManager getSupply_manager() {
        if (supply_manager == null) {
            supply_manager = new SupplyManager();

        }
        return supply_manager;
    }

    //Getters and Setters
    public Map<Integer, Supplier> getSuppliers_list()
    {
        return suppliers_list;
    }

    public Map<String, LinkedList<Supplier>> getSupplierByProduct()
    {
        return SupplierByProduct;
    }

    public SupplierCard CreateCard ()
    {
        int supplier_number=0;
        int company_number=0;
        int bank_account=0;



        // checking if valid supplier number, and if it is not exisitng already
        boolean validSupplierNum=false;
        String supplierNum;
        //Scanner input = new Scanner(System.in);
        while (validSupplierNum==false)
        {
            System.out.println("Enter supplier number: ");
            supplierNum = input.nextLine();
            validSupplierNum= isNumeric(supplierNum);

            if(validSupplierNum==true) // check if the input is valid number
            {
                supplier_number=Integer.parseInt(supplierNum);
                // check if the input is not already exist in the system
                if(isExist(supplier_number)==true)
                {
                    validSupplierNum=false;
                    System.out.print("This supplier number already exist, enter a new one: ");
                }
            }
        }
        // checking if company number is a vaild number
        boolean validCompanyNum=false;
        String companyNum;
        while (validCompanyNum==false)
        {
            System.out.println("Enter company number: ");
            companyNum=input.nextLine();
            validCompanyNum= isNumeric(companyNum);
            if(validCompanyNum==true)
            {
                company_number=Integer.parseInt(companyNum);
            }
        }
        // checking if the bank account is a vaild number
        boolean validBankAccount=false;
        String bankAccount;
        while (validBankAccount==false)
        {
            System.out.println("Enter bank account: ");
            bankAccount=input.nextLine();
            validBankAccount= isNumeric(bankAccount);
            if(validBankAccount==true)
            {
                bank_account=Integer.parseInt(bankAccount);
            }
        }

        System.out.println("Enter your name: ");
        String n= input.nextLine();
        System.out.println("Enter address");
        String address=input.nextLine();
        // creation of a new suppliercard
        SupplierCard newSupplier= new SupplierCard(n,supplier_number,company_number,bank_account,address);

        //enter payment methood
        boolean validPaymentMethood=false;
        while(validPaymentMethood==false)
        {
            System.out.println("Choose the payment methood you want:\n 1.Cash \n 2.Bit \n 3.Credit card");
            int num = input.nextInt();
            if(num==1 || num==2 || num==3)
            {
                newSupplier.setPayment_method(num);
                validPaymentMethood=true;
            }
            else
            {
                System.out.println("You insert incorrect option!");
            }
        }

        //enter contact members
        System.out.println("Enter contact members detials:");
        int choice=1;
        while(choice==1){
            newSupplier.addContact_members();

            System.out.println("If you want to add another contact member press 1, else press something else");
            choice=input.nextInt();
        }

        //enter products categories
        int choice2=1;
        while(choice2==1){
            Scanner reader=new Scanner(System.in);
            System.out.println("Enter products category:");
            String ans= reader.nextLine();
            newSupplier.addCategory_ToSupplier(ans);
            System.out.println("If you want to add another products category press 1, else press something else");
            choice2=reader.nextInt();
        }

        return newSupplier;
    }

    public Agreement CreateAgreement()
    {
        Agreement a=new Agreement();
        // Scanner input = new Scanner(System.in);
        System.out.println("--Create Agreement Step--");
        int moreItems=1;
        System.out.println("--Adding Products--");
        while(moreItems==1)
        {
            a.CreateProduct();
            System.out.println("For adding another product press 1, else press something else");
            moreItems=input.nextInt();
        }

        System.out.println("--Adding Discount by total amount of products by percentage--");
        int moreD=1;
        while(moreD==1)
        {
            PrecentageDiscount pd= PrecentageDiscount.CreateDiscount();
            a.addTotal_orderDiscount(pd);
            System.out.println("For adding another discount press 1, else press something else");
            moreD=input.nextInt();
        }

        System.out.println("Pick your supply method:");
        a.MethodSupplyChoose();

        int eom=10;
        while (eom!=1&& eom!=2 &&eom!=3)
        {
            System.out.println("Choose EOM you want");
            System.out.println("1. Plus 0");
            System.out.println("2. Plus 30");
            System.out.println("3. Plus 60");
            eom=input.nextInt();
        }

        a.setEom(eom);

        return a;
    }

    // Create new supplier
    public void CreateSupplier()
    {
        SupplierCard card=CreateCard();
        Agreement agreement=CreateAgreement();
        Supplier s=new Supplier(card,agreement);
        //after creating a supplier we want to update the products we can buy from all suppliers
        //and add to the list of the product the supplier that can provide it
        for (int i=0; i<s.agreement.getProductList().size(); i++)
        {
            String temp=s.agreement.getProductList().get(i).getProduct_name();
            if(getSupplierByProduct().containsKey(temp))
            {
                getSupplierByProduct().get(temp).add(s);
            }
            else {
                LinkedList<Supplier> myList = new LinkedList<>();
                myList.add(s);
                getSupplierByProduct().put(temp,myList);
            }
        }

        getSuppliers_list().put(s.getCard().getSupplier_number(),s);
    }


    public void UpdateSupplierByProduct(Supplier s)
    {
        //after creating a supplier we want to update the products we can buy from all suppliers
        //and add to the list of the product the supplier that can provide it
        for (int i=0; i<s.agreement.getProductList().size(); i++)
        {
            String temp=s.agreement.getProductList().get(i).getProduct_name();
            if(getSupplierByProduct().containsKey(temp))
            {
                getSupplierByProduct().get(temp).add(s);
            }
            else {
                LinkedList<Supplier> myList = new LinkedList<>();
                myList.add(s);
                getSupplierByProduct().put(temp,myList);
            }
        }


    }
    public Supplier CreateSupplier(SupplierCard card, Agreement agreement)
    {

        Supplier s=new Supplier(card,agreement);
        UpdateSupplierByProduct(s);
        getSuppliers_list().put(s.getCard().getSupplier_number(),s);
        return s;
    }

    // edit supplier
    public void EditSupplier()
    {
        //Scanner input = new Scanner(System.in);
        System.out.println("Enter the supplier number you want to edit:");
        int num= input.nextInt();
        if(suppliers_list.containsKey(num)==true) // the number is exist
        {
            int choice=9;
            while(choice!=0)
            {
                System.out.println("1. Edit supplier card");
                System.out.println("2. Edit agreement");
                System.out.println("0. Quit");
                choice=input.nextInt();
                if(choice==1)
                {
                    EditCard(suppliers_list.get(num).getCard());
                } else if (choice==2)
                {
                    EditAgreement(suppliers_list.get(num));
                }
            }
        }
        else
            System.out.println("This supplier number is invalid");
    }

    public void EditCard(SupplierCard x)
    {
        Scanner input = new Scanner(System.in);
        int choice=9;
        while(choice!=0)
        {
            System.out.println("1. Change company number.");
            System.out.println("2. Change bank account.");
            System.out.println("3. Change payment methood.");
            System.out.println("4. Change the address");
            System.out.println("5. Edit contact members.");
            System.out.println("6. Edit categories of product");
            System.out.println("0. Back to main menu");
            choice = input.nextInt();
            switch (choice)
            {
                case 1:
                {
                    System.out.println("Enter a new company number");
                    int y= input.nextInt();
                    x.setCompany_number(y);
                    break;
                }
                case 2:
                {
                    System.out.println("Enter a new bank account");
                    int y= input.nextInt();
                    x.setBank_account(y);
                    break;

                }

                case 3: //Change payment methood
                {
                    boolean valid=false;
                    while (valid==false)
                    {
                        System.out.println("Choose a new payment methood:");
                        System.out.println("1. cash");
                        System.out.println("2. bit");
                        System.out.println("3. credit card");
                        int y= input.nextInt();
                        if(y==1 || y==2 || y==3)
                        {
                            valid=true;
                            x.setPayment_method(y);
                        }

                    }
                    break;

                }

                case 4:
                {
                    System.out.println("Enter a new address");
                    x.setAddress(input.nextLine());
                    break;

                }
                case 5:
                {
                    x.EditContactsMembers();
                    break;
                }

                case 6:
                {
                    x.EditCategories();
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

    public void PrintSupplierOrders(){
        //Scanner input = new Scanner(System.in);
        System.out.println("Please enter supplier ID:");
        int id_ = input.nextInt();
        if(!this.getSuppliers_list().containsKey(id_)){
            System.out.println("This is not valid id!");
            return;
        }
        else{
            this.getSuppliers_list().get(id_).PrintOrders();
        }
    }

    public void printAllProducts(Supplier x)
    {
        if(x.getAgreement().getProductList().isEmpty())
        {
            System.out.println("There is no products in the list");
        }
        else
        {
            System.out.println("---Product list---");
            for(Product p:x.getAgreement().getProductList())
            {
                p.printProductInfo();
            }
        }
    }
    public void EditAgreement(Supplier x)
    {
        Scanner input1= new Scanner(System.in);
        int choice=9;
        while(choice!=0) {
            System.out.println("1. Add product");
            System.out.println("2. Remove product");
            System.out.println("3. Edit product");
            System.out.println("4. View total discounts that you offer the client");
            System.out.println("5. Edit total discount");
            System.out.println("0. Back to main menu");
            choice = input1.nextInt();


            switch (choice)
            {
                case 1:
                {
                    Product p=x.getAgreement().CreateProduct();
                    String tempName= p.getProduct_name();
                    if(SupplierByProduct.containsKey(tempName))
                    {
                        SupplierByProduct.get(tempName).add(x);
                    }
                    else
                    {
                        LinkedList<Supplier> myList = new LinkedList<>();
                        myList.add(x);
                        getSupplierByProduct().put(p.getProduct_name(),myList);
                    }

                    printAllProducts(x);
                    break;
                }

                case 2:
                {
                    Scanner input2 = new Scanner(System.in);
                    System.out.println("Please enter the name of the product you want delete");
                    String temp=input2.nextLine();
                    String temp2=x.getAgreement().removeProductFromAgreement(temp);
                    deleteProductFromSupplier(x.getCard().getSupplier_number(),temp2);
                    printAllProducts(x);
                    break;
                }

                case 3:
                {
                    Scanner input2 = new Scanner(System.in);
                    System.out.println("Please enter the name of the product you want edit");
                    String temp=input2.nextLine();// TODO
                    boolean isExist=false;
                    for(Product i:x.getAgreement().getProductList())
                    {
                        if(i.getProduct_name().equals(temp))
                        {
                            x.getAgreement().editProduct(i);
                            isExist=true;
                            break;
                        }
                    }
                    if (isExist==false)
                    {
                        System.out.println("There is no product calls: "+temp);
                    }

                    break;
                }

                case 4:
                {
                    System.out.println("Total discount (about all the purchase)");
                    for (PrecentageDiscount i:x.getAgreement().getTotal_orderDiscount())
                    {
                        i.printDiscount();
                    }

                    break;
                }

                case 5:
                {
                    Scanner input2 = new Scanner(System.in);
                    System.out.println("The current total discount (about all the purchase):");
                    for (PrecentageDiscount i:x.getAgreement().getTotal_orderDiscount())
                    {
                        i.printDiscount();
                    }
                    int choice2=2;
                    while (choice2!=0 && choice2!=1)
                    {
                        System.out.println("0.Keep current discounts ");
                        System.out.println("1.Delete current discounts and enter new discounts ");
                        choice2=input2.nextInt();

                        switch (choice2)
                        {
                            case 0:
                            {
                                System.out.println("No change will be made");
                                break;
                            }
                            case 1:
                            {
                                x.getAgreement().editTotalDiscount();
                                break;
                            }
                            default :
                            {
                                System.out.println("Invalid input");
                                break;
                            }
                        }
                    }

                    break;
                }

                default:
                {
                    if(choice!=0)
                        System.out.println("Invalid input");
                    break;
                }
            }
        }


    }

    public void removeSupplierByUser(){
        //Scanner input = new Scanner(System.in);
        System.out.println("Please enter supplier id:");
        int sup_id = input.nextInt();
        if(!this.suppliers_list.containsKey(sup_id)){
            System.out.println("This supplier is not supply products to Super Lee!");
        }
        else{
            removeSupplier(this.suppliers_list.get(sup_id));
            System.out.println("Supplier "+sup_id+ " deleted successfully");
        }
    }
    //help function to remove supplier
    public void removeSupplier(Supplier s1){
        for(Product p:s1.getAgreement().getProductList())
        {
            this.SupplierByProduct.get(p.getProduct_name()).remove(s1);
            if(this.SupplierByProduct.get(p.getProduct_name()).isEmpty()) // if s1 is the only supplier of the product remove the product
            {
                this.SupplierByProduct.remove(p.getProduct_name());
            }

        }

        this.suppliers_list.remove(s1.getCard().getSupplier_number()); // remove the supplier from supplier list
    }

    //Help function
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try
        {
            int d = Integer.parseInt(strNum);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isExist(int supNum) {
        if(supply_manager.suppliers_list.containsKey(supNum))
            return true;
        return false;
    }

    public void deleteProductFromSupplier(int sup,String pro)
    {
        if(pro == "notExist"){
            //System.out.println("Product: "+pro+" is not existing in Supplier: "+sup);
            return;
        }
        Supplier s1 = this.suppliers_list.get(sup);
        this.SupplierByProduct.get(pro).remove(s1);
        if(this.SupplierByProduct.get(pro).isEmpty()) // if s1 is the only supplier of the product remove the product
        {
            this.SupplierByProduct.remove(pro);
        }
        System.out.println("Product: "+pro+ " was deleted from Supplier: "+sup+ " Successfully!");


    }

    public void loadData()
    {
        //-------------------------------supplier1-------------------------------------------------
        //supplierCard
        //contacts
        ContactMember c1_1=new ContactMember("0521234567","Moshe","Moshe123@gmail.com");
        ContactMember c1_2=new ContactMember("0522255889","Avraham","Avraham23@gmail.com");
        LinkedList<ContactMember> contactList1=new LinkedList<>();
        contactList1.add(c1_1);
        contactList1.add(c1_2);

        //product_categories
        LinkedList<String> categoriesList1=new LinkedList<>();
        categoriesList1.add("Animal Food");
        categoriesList1.add("Meat");

        SupplierCard sCard1=new SupplierCard("Matan",10,100,59833,"Rehovot",Payment_method.cash,contactList1,categoriesList1);

        //Agreement
        LinkedList<Product> productList1=new LinkedList<>();
        LinkedList<PrecentageDiscount> total_orderDiscount1=new LinkedList<>();

        //products
        //p1
        LinkedList<PrecentageDiscount> p1_1_discounts=new LinkedList<>();

        Range range1_1_1=new Range(0,100);
        Range range1_1_2=new Range(101,200);
        Range range1_1_3=new Range(201,300);
        Range range1_1_4=new Range(301,500);

        PrecentageDiscount discount1_1_1=new PrecentageDiscount(range1_1_1,5);
        PrecentageDiscount discount1_1_2=new PrecentageDiscount(range1_1_2,7);
        PrecentageDiscount discount1_1_3=new PrecentageDiscount(range1_1_3,10);
        PrecentageDiscount discount1_1_4=new PrecentageDiscount(range1_1_4,15);

        p1_1_discounts.add(discount1_1_1);
        p1_1_discounts.add(discount1_1_2);
        p1_1_discounts.add(discount1_1_3);
        p1_1_discounts.add(discount1_1_4);

        Product p1=new Product("Dog Food",1000,5,70,500,p1_1_discounts);

        //p2

        LinkedList<PrecentageDiscount> p1_2_discounts=new LinkedList<>();

        Range range1_2_1=new Range(0,300);
        Range range1_2_2=new Range(301,600);
        Range range1_2_3=new Range(601,1000);

        PrecentageDiscount discount1_2_1=new PrecentageDiscount(range1_2_1,0);
        PrecentageDiscount discount1_2_2=new PrecentageDiscount(range1_2_2,10);
        PrecentageDiscount discount1_2_3=new PrecentageDiscount(range1_2_3,15);

        p1_2_discounts.add(discount1_2_1);
        p1_2_discounts.add(discount1_2_2);
        p1_2_discounts.add(discount1_2_3);

        Product p2=new Product("Chicken",1001,1,30,1000,p1_2_discounts);

        productList1.add(p1);
        productList1.add(p2);

        Range rangeTotal1=new Range(500,700);
        Range rangeTotal2=new Range(701,1000);
        Range rangeTotal3=new Range(1001,3000);

        PrecentageDiscount discountTotal1_1=new PrecentageDiscount(rangeTotal1,5);
        PrecentageDiscount discountTotal1_2=new PrecentageDiscount(rangeTotal2,8);
        PrecentageDiscount discountTotal1_3=new PrecentageDiscount(rangeTotal3,12);

        total_orderDiscount1.add(discountTotal1_1);
        total_orderDiscount1.add(discountTotal1_2);
        total_orderDiscount1.add(discountTotal1_3);

        MethoodSupply methoodSupply1= new BySupplyDays("By supply days", 2);

        Agreement agreement1=new Agreement(productList1,total_orderDiscount1,methoodSupply1,EOM.Plus_30);

        Supplier s1=CreateSupplier(sCard1,agreement1); // The supplier will add to the supplier list and to the list of products

        //-------------------------------supplier2-------------------------------------------------
        //supplierCard
        //contacts

        ContactMember c2_1=new ContactMember("0521236357","Idan","Idanosh19@gmail.com");
        LinkedList<ContactMember> contactList2=new LinkedList<>();
        contactList2.add(c2_1);

        //product_categories
        LinkedList<String> categoriesList2=new LinkedList<>();
        categoriesList2.add("Cereal");
        categoriesList2.add("Snacks");

        SupplierCard sCard2=new SupplierCard("Ido",11,105,68323,"Ness-Ziona",Payment_method.bit,contactList2,categoriesList2);

        //Agreement
        LinkedList<Product> productList2=new LinkedList<>();
        LinkedList<PrecentageDiscount> total_orderDiscount2=new LinkedList<>();

        //products
        //p2_1 p(supplier)_(productIndex)
        LinkedList<PrecentageDiscount> p2_1_discounts=new LinkedList<>();

        Range range2_1_1=new Range(0,500);
        Range range2_1_2=new Range(501,1000);
        Range range2_1_3=new Range(1001,1500);

        //discount(supplier)_(product)_(index)
        PrecentageDiscount discount2_1_1=new PrecentageDiscount(range2_1_1,2);
        PrecentageDiscount discount2_1_2=new PrecentageDiscount(range2_1_2,5);
        PrecentageDiscount discount2_1_3=new PrecentageDiscount(range2_1_3,7);


        p2_1_discounts.add(discount2_1_1);
        p2_1_discounts.add(discount2_1_2);
        p2_1_discounts.add(discount2_1_3);

        Product p2_1=new Product("Captain-Crunch",2000,0.75,25,1500,p2_1_discounts);

        //p2_2

        LinkedList<PrecentageDiscount> p2_2_discounts=new LinkedList<>();

        Range range2_2_1=new Range(0,400);
        Range range2_2_2=new Range(401,1000);
        Range range2_2_3=new Range(1001,1600);
        Range range2_2_4=new Range(1601,2000);


        //discount(supplier)_(product)_(index)
        PrecentageDiscount discount2_2_1=new PrecentageDiscount(range2_2_1,2);
        PrecentageDiscount discount2_2_2=new PrecentageDiscount(range2_2_2,5);
        PrecentageDiscount discount2_2_3=new PrecentageDiscount(range2_2_3,7);
        PrecentageDiscount discount2_2_4=new PrecentageDiscount(range2_2_4,7);


        p2_2_discounts.add(discount2_2_1);
        p2_2_discounts.add(discount2_2_2);
        p2_2_discounts.add(discount2_2_3);
        p2_2_discounts.add(discount2_2_4);

        Product p2_2=new Product("Lays-chips",2001,0.5,7,2000,p2_2_discounts);

        productList2.add(p2_1);
        productList2.add(p2_2);

        Range rangeTotal2_1=new Range(800,1200);
        Range rangeTotal2_2=new Range(1201,1600);
        Range rangeTotal2_3=new Range(1601,3500);

        PrecentageDiscount discountTotal2_1=new PrecentageDiscount(rangeTotal2_1,5);
        PrecentageDiscount discountTotal2_2=new PrecentageDiscount(rangeTotal2_2,8);
        PrecentageDiscount discountTotal2_3=new PrecentageDiscount(rangeTotal2_3,12);

        total_orderDiscount2.add(discountTotal2_1);
        total_orderDiscount2.add(discountTotal2_2);
        total_orderDiscount2.add(discountTotal2_3);

        int[] days=new int[]{0,1,0,0,0,1,0};
        MethoodSupply methoodSupply2= new ByFixedDays("By fixed days",days );

        Agreement agreement2=new Agreement(productList2,total_orderDiscount2,methoodSupply2,EOM.Plus_0);

        Supplier s2=CreateSupplier(sCard2,agreement2); // The supplier will add to the supplier list and to the list of products

        //-------------------------------supplier3-------------------------------------------------
        //supplierCard
        //contacts

        ContactMember c3_1=new ContactMember("0521298523","Lior","Lior@gmail.com");
        LinkedList<ContactMember> contactList3=new LinkedList<>();
        contactList3.add(c3_1);

        //product_categories
        LinkedList<String> categoriesList3=new LinkedList<>();
        categoriesList3.add("Cereal");
        categoriesList3.add("Milk");

        SupplierCard sCard3=new SupplierCard("Kim",12,189,37519,"Yiron",Payment_method.bit,contactList3,categoriesList3);

        //Agreement
        LinkedList<Product> productList3=new LinkedList<>();
        LinkedList<PrecentageDiscount> total_orderDiscount3=new LinkedList<>();

        //products
        //p3_1 p(supplier)_(productIndex)
        LinkedList<PrecentageDiscount> p3_1_discounts=new LinkedList<>();

        Range range3_1_1=new Range(0,500);
        Range range3_1_2=new Range(501,700);
        Range range3_1_3=new Range(701,100);

        //discount(supplier)_(product)_(index)
        PrecentageDiscount discount3_1_1=new PrecentageDiscount(range3_1_1,3);
        PrecentageDiscount discount3_1_2=new PrecentageDiscount(range3_1_2,6);
        PrecentageDiscount discount3_1_3=new PrecentageDiscount(range3_1_3,9);


        p3_1_discounts.add(discount3_1_1);
        p3_1_discounts.add(discount3_1_2);
        p3_1_discounts.add(discount3_1_3);

        Product p3_1=new Product("Captain-Crunch",3000,0.75,25,1000,p3_1_discounts);

        //p3_2

        LinkedList<PrecentageDiscount> p3_2_discounts=new LinkedList<>();

        Range range3_2_1=new Range(0,400);
        Range range3_2_2=new Range(401,1000);
        Range range3_2_3=new Range(1001,1600);
        Range range3_2_4=new Range(1601,2000);


        //discount(supplier)_(product)_(index)
        PrecentageDiscount discount3_2_1=new PrecentageDiscount(range3_2_1,2);
        PrecentageDiscount discount3_2_2=new PrecentageDiscount(range3_2_2,3);
        PrecentageDiscount discount3_2_3=new PrecentageDiscount(range3_2_3,6);
        PrecentageDiscount discount3_2_4=new PrecentageDiscount(range3_2_4,8);


        p3_2_discounts.add(discount3_2_1);
        p3_2_discounts.add(discount3_2_2);
        p3_2_discounts.add(discount3_2_3);
        p3_2_discounts.add(discount3_2_4);

        Product p3_2=new Product("Lays-chips",2001,0.5,7,2000,p3_2_discounts);

        productList3.add(p3_1);
        productList3.add(p3_2);

        Range rangeTotal3_1=new Range(800,1200);
        Range rangeTotal3_2=new Range(1201,1600);
        Range rangeTotal3_3=new Range(1601,3500);

        PrecentageDiscount discountTotal3_1=new PrecentageDiscount(rangeTotal3_1,5);
        PrecentageDiscount discountTotal3_2=new PrecentageDiscount(rangeTotal3_2,8);
        PrecentageDiscount discountTotal3_3=new PrecentageDiscount(rangeTotal3_3,12);

        total_orderDiscount3.add(discountTotal3_1);
        total_orderDiscount3.add(discountTotal3_2);
        total_orderDiscount3.add(discountTotal3_3);

        MethoodSupply methoodSupply3= new BySuperLee("By Supper Lee" );

        Agreement agreement3=new Agreement(productList3,total_orderDiscount3,methoodSupply3,EOM.Plus_0);

        Supplier s3=CreateSupplier(sCard3,agreement3); // The supplier will add to the supplier list and to the list of products
/////////////////

        //-------------------------------supplier4-------------------------------------------------
        //supplierCard
        //contacts

        ContactMember c4_1=new ContactMember("0521123456","Lidor","Lidori@gmail.com");
        LinkedList<ContactMember> contactList4=new LinkedList<>();
        contactList3.add(c4_1);

        //product_categories
        LinkedList<String> categoriesList4=new LinkedList<>();
        categoriesList3.add("Cereal");
        categoriesList3.add("Bread");

        SupplierCard sCard4=new SupplierCard("Yaron",13,953,96453,"Beer-Sheva",Payment_method.bit,contactList4,categoriesList4);

        //Agreement
        LinkedList<Product> productList4=new LinkedList<>();
        LinkedList<PrecentageDiscount> total_orderDiscount4=new LinkedList<>();

        //products
        //p4_1 p(supplier)_(productIndex)
        LinkedList<PrecentageDiscount> p4_1_discounts=new LinkedList<>();

        Range range4_1_1=new Range(0,600);
        Range range4_1_2=new Range(601,1000);
        Range range4_1_3=new Range(1001,1200);

        //discount(supplier)_(product)_(index)
        PrecentageDiscount discount4_1_1=new PrecentageDiscount(range4_1_1,2);
        PrecentageDiscount discount4_1_2=new PrecentageDiscount(range4_1_2,8);
        PrecentageDiscount discount4_1_3=new PrecentageDiscount(range4_1_3,13);


        p4_1_discounts.add(discount4_1_1);
        p4_1_discounts.add(discount4_1_2);
        p4_1_discounts.add(discount4_1_3);

        Product p4_1=new Product("Cini-Minis",4000,0.75,25,1200,p4_1_discounts);

        //p4_2

        LinkedList<PrecentageDiscount> p4_2_discounts=new LinkedList<>();

        Range range4_2_1=new Range(0,350);
        Range range4_2_2=new Range(351,780);
        Range range4_2_3=new Range(781,1200);
        Range range4_2_4=new Range(1201,1500);


        //discount(supplier)_(product)_(index)
        PrecentageDiscount discount4_2_1=new PrecentageDiscount(range4_2_1,4);
        PrecentageDiscount discount4_2_2=new PrecentageDiscount(range4_2_2,5);
        PrecentageDiscount discount4_2_3=new PrecentageDiscount(range4_2_3,6);
        PrecentageDiscount discount4_2_4=new PrecentageDiscount(range4_2_4,7);


        p4_2_discounts.add(discount4_2_1);
        p4_2_discounts.add(discount4_2_2);
        p4_2_discounts.add(discount4_2_3);
        p4_2_discounts.add(discount4_2_4);

        Product p4_2=new Product("Captain-Crunch",4001,0.5,7,1500,p4_2_discounts);

        //p4_3 p(supplier)_(productIndex)
        LinkedList<PrecentageDiscount> p4_3_discounts=new LinkedList<>();

        Range range4_3_1=new Range(0,600);
        Range range4_3_2=new Range(601,1000);
        Range range4_3_3=new Range(1001,1200);

        //discount(supplier)_(product)_(index)
        PrecentageDiscount discount4_3_1=new PrecentageDiscount(range4_3_1,2);
        PrecentageDiscount discount4_3_2=new PrecentageDiscount(range4_3_2,8);
        PrecentageDiscount discount4_3_3=new PrecentageDiscount(range4_3_3,13);


        p4_1_discounts.add(discount4_3_1);
        p4_1_discounts.add(discount4_3_2);
        p4_1_discounts.add(discount4_3_3);

        Product p4_3=new Product("Lays-chips",4002,0.5,6.8,1200,p4_3_discounts);
//
        productList4.add(p4_1);
        productList4.add(p4_2);
        productList4.add(p4_3);


        Range rangeTotal4_1=new Range(800,1200);
        Range rangeTotal4_2=new Range(1201,1600);
        Range rangeTotal4_3=new Range(1601,3500);

        PrecentageDiscount discountTotal4_1=new PrecentageDiscount(rangeTotal4_1,4);
        PrecentageDiscount discountTotal4_2=new PrecentageDiscount(rangeTotal4_2,6);
        PrecentageDiscount discountTotal4_3=new PrecentageDiscount(rangeTotal4_3,13);

        total_orderDiscount4.add(discountTotal4_1);
        total_orderDiscount4.add(discountTotal4_2);
        total_orderDiscount4.add(discountTotal4_3);

        MethoodSupply methoodSupply4= new BySuperLee("By Supper Lee" );

        Agreement agreement4=new Agreement(productList4,total_orderDiscount4,methoodSupply4,EOM.Plus_30);

        Supplier s4=CreateSupplier(sCard4,agreement4); // The supplier will add to the supplier list and to the list of products
/////////////////
    }

}


