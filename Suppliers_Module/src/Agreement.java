import java.util.LinkedList;
import java.util.Scanner;

public class Agreement {
    private LinkedList<Product> productList;
    private LinkedList<PrecentageDiscount> total_orderDiscount;
    private MethoodSupply methoodSupply;
    private EOM eom;

    private Scanner input= new Scanner(System.in);


    //constructor
    public Agreement() {
        this.productList= new LinkedList<>();
        this.total_orderDiscount= new LinkedList<>();
        this.methoodSupply=null;
        this.eom=null;

    }

    public Agreement(LinkedList<Product> productList, LinkedList<PrecentageDiscount> total_orderDiscount, MethoodSupply methoodSupply, EOM eom) {
        this.productList = productList;
        this.total_orderDiscount = total_orderDiscount;
        this.methoodSupply = methoodSupply;
        this.eom = eom;
    }

    //-------------------Getter and Setter---------------------------------------------------
    public MethoodSupply getMethoodSupply() {
        return methoodSupply;
    }

    public void setMethoodSupply(MethoodSupply methoodSupply) {
        this.methoodSupply = methoodSupply;
    }
    public LinkedList<Product> getProductList() {
        return productList;
    }

    public LinkedList<PrecentageDiscount> getTotal_orderDiscount() {
        return total_orderDiscount;
    }

    public EOM getEom() {
        return eom;
    }

    public void setEom(int x) {
        if(x==1)
        {
            this.eom=EOM.Plus_0;
        }
        else if (x==2)
        {
            this.eom=EOM.Plus_30;

        }
        else
            this.eom=EOM.Plus_60;

    }

    ////////////////////////////////////////////////////////

    public void addProductToAgreement(Product x)
    {
        productList.add(x);
        System.out.println("Product added successfully\n");
    }

    public void addTotal_orderDiscount (PrecentageDiscount x)
    {
        this.total_orderDiscount.add(x);
    }



    public void addProductMenu(Product x)
    {
        if(isExist(x)==false)
            addProductToAgreement(x);
        else
        {
            System.out.println("This product already exist");
        }
    }


    public Product CreateProduct()
    {
        Scanner input= new Scanner(System.in);
        String product_name="";
        int product_id=0;
        boolean check=true;
        while(check==true)
        {
            System.out.println("Please enter the product name");
            product_name= input.nextLine();
            check=isExistName(product_name);
            if(check==true)
                System.out.println("This product name is already exist, please enter again");
        }

        check=true;
        while(check==true)
        {
            System.out.println("Please enter product local id");
            product_id=input.nextInt();
            check=isExistID(product_id);
            if(check==true)
                System.out.println("This product id is already exist, please enter again");
        }


        System.out.println("Please enter product weight");
        double weight =input.nextDouble();

        System.out.println("Please enter product price");
        double price = input.nextDouble();

        System.out.println("Please enter the amount of goods in units you have: ");
        int amount = input.nextInt();

        Product p= new Product(product_name,product_id,weight,price,amount);
        System.out.println("Enter disconts:");
        p.addDiscount();

        addProductToAgreement(p);
        return p;

    }


    public boolean isExistName(String x)
    {
        for (Product p : productList)
        {
            if (p.getProduct_name().equals(x))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isExistID(int x)
    {
        for (Product p : productList)
        {
            if (p.getLocal_key()==x)
            {
                return true;
            }
        }
        return false;
    }

    public boolean isExist (Product x)
    {
        boolean isExist=false;
        for (Product p : productList) {
            if (p.getProduct_name().equals(x.getProduct_name())) {
                isExist=true;
            }
        }
        return isExist;
    }

    public void MethodSupplyChoose()
    {
        Scanner input= new Scanner(System.in);
        int choice=4;
        while (choice!=1&&choice!=2&&choice!=3)
        {
            System.out.println("1. By fixed days");
            System.out.println("2. By Supper Lee");
            System.out.println("3. By supply days");
            choice=input.nextInt();
            switch (choice)
            {
                case 1:
                {
                    Scanner scanner= new Scanner(System.in);
                    int[] supplydays = new int[]{0,0,0,0,0,0,0};
                    int day=1;
                    while (day!=0)
                    {
                        System.out.println("1.Sunday");
                        System.out.println("2.Monday");
                        System.out.println("3.Tuesday");
                        System.out.println("4.Wednesday");
                        System.out.println("5.Thursday");
                        System.out.println("6.Friday");
                        System.out.println("7.Saturday");
                        System.out.println("0.Quit");
                        day=scanner.nextInt();

                        if(day>0 && day<8)
                        {
                            supplydays[day-1]=1;
                        }
                        else if (day!=0)
                        {
                            System.out.println("Invalid input");
                        }
                    }

                    MethoodSupply method= new ByFixedDays("By fixed days",supplydays);
                    this.methoodSupply=method;
                    break;
                }

                case 2: // empty case (its ok)
                {
                    MethoodSupply method= new BySuperLee("By Supper Lee");
                    this.methoodSupply=method;
                    break;
                }

                case 3:
                {
                    Scanner scanner= new Scanner(System.in);
                    System.out.println("Enter how many days is required for you to supply");
                    int x=scanner.nextInt();
                    MethoodSupply method= new BySupplyDays("By supply days",x);
                    this.methoodSupply=method;
                    break;

                }

                default:
                {
                    System.out.println("Enter valid number");
                    break;
                }

            }
        }

    }

    public String removeProductFromAgreement(String name)
    {
        if(isExistName(name)==true)
        {
            for (Product p : productList) {
                if (p.getProduct_name().equals(name))
                {
                    this.productList.remove(p);
                    break;
                }
            }
            return name;
        }
        else
        {
            //System.out.println("There is no product with this name");
            System.out.println(name+" is not exist in the product list");
            return "notExist";
        }
    }

    public void editProduct(Product p)
    {
        int choice=9;
        while (choice!=0)
        {
            System.out.println("1. Edit price");
            System.out.println("2. Edit amount in stock");
            System.out.println("3. Edit discounts");
            System.out.println("0. Quit");
            choice=input.nextInt();

            switch (choice)
            {
                case 1:
                {
                    System.out.println("Enter the new price");
                    int priceNew=input.nextInt();
                    p.setUnit_price(priceNew);
                    p.printProductInfo();
                    break;
                }
                case 2:
                {
                    System.out.println("Enter the new amount");
                    int amountNew=input.nextInt();
                    p.setAmount_available(amountNew);
                    p.printProductInfo();
                    break;
                }
                case 3:
                {
                    System.out.println("This is your current disconts:");
                    for (PrecentageDiscount i:total_orderDiscount)
                    {
                        i.printDiscount();
                    }
                    System.out.println("\n You want to keep this or delete all and create new discounts?");
                    int option=3;
                    while(option!=1 && option!=2)//TODO
                    {
                        Scanner input2= new Scanner(System.in);
                        System.out.println("1. Keep");
                        System.out.println("2. Change");
                        option=input2.nextInt();
                        switch (option)
                        {
                            case 1:
                            {
                                System.out.println("There were no changes");
                            }
                            case 2:
                            {
                                p.getDiscounts().clear(); //delete all the discount
                                p.addDiscount(); //adding new discounts
                                p.printProductInfo();
                            }
                            default :
                                System.out.println("Invalid input");
                        }
                    }
                    break;

                }
                default:
                {
                    System.out.println("Invalid input");
                    break;
                }
            }
        }
    }

    public void editTotalDiscount()
    {
        getTotal_orderDiscount().clear();
        int moreD=1;
        while(moreD==1)
        {
            PrecentageDiscount pd= PrecentageDiscount.CreateDiscount();
            addTotal_orderDiscount(pd);
            System.out.println("For adding another discount press 1, else press something else");
            moreD=input.nextInt();
        }
        printTotalDiscounts();
    }

    public void printTotalDiscounts()
    {
        System.out.println("---Total Discounts---");
        if(total_orderDiscount.isEmpty())
            System.out.println("Total discounts list is empty");
        else
        {
            for(PrecentageDiscount p:total_orderDiscount)
            {
                p.printDiscount();
            }
        }
    }

}