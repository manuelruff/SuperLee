import java.util.LinkedList;
import java.util.Scanner;

public class Product {
    private String product_name;
    private int local_key;
    private double unit_weight;
    private double unit_price;
    private int amount_available;
    private LinkedList<PrecentageDiscount> discounts;

    public Product(String product_name, int local_key, double unit_weight, double unit_price, int amount_available) {
        this.product_name = product_name;
        this.local_key = local_key;
        this.unit_weight = unit_weight;
        this.unit_price = unit_price;
        this.amount_available = amount_available;
        this.discounts = new LinkedList<>();
    }

    public Product(String product_name, int local_key, double unit_weight, double unit_price, int amount_available, LinkedList<PrecentageDiscount> discounts) {
        this.product_name = product_name;
        this.local_key = local_key;
        this.unit_weight = unit_weight;
        this.unit_price = unit_price;
        this.amount_available = amount_available;
        this.discounts = discounts;
    }

    //----------------------------------Getters and Setter------------------------------------------
    public int getAmount_available() {
        return amount_available;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public void setAmount_available(int amount_available) {
        this.amount_available = amount_available;
    }

    public int getLocal_key() {
        return local_key;
    }


    public double getUnit_weight() {
        return unit_weight;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public LinkedList<PrecentageDiscount> getDiscounts() {
        return discounts;
    }

    ////-------------------------------------------------------------------------------////////////

    public void addDiscount()
    {
        Scanner input = new Scanner(System.in);
        int moreDiscount=1;
        while(moreDiscount==1)
        {
            PrecentageDiscount x=PrecentageDiscount.CreateDiscount();
            this.discounts.add(x); // adding the discount to the list (range and percentage)
            System.out.println("If you want to enter another discount press 1, else press something else");
            moreDiscount=input.nextInt();
        }
    }

    public void printProductInfo()
    {
        System.out.println("Product Name: "+product_name+", Id: "+local_key+", Weight: "+unit_weight+", Price: "+unit_price);
    }

    public void printProductDiscounts()
    {
        for (PrecentageDiscount p:discounts)
        {
            p.printDiscount();
        }
        System.out.println("-----------------------------------------------");
    }


}
