
public class Product_Order_Detailes {
    private int product_id;
    private String product_name;
    private int amount;
    private double discount;
    private double price_by_supplier;
    private double final_price;

    public Product_Order_Detailes(int product_id, String product_name, int amount, double discount, double price_by_supplier) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.amount = amount;
        this.discount = discount;
        this.price_by_supplier = price_by_supplier;
        this.final_price = amount*price_by_supplier*discount;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public int getAmount() {
        return amount;
    }

    public double getDiscount() {
        return discount;
    }

    public double getPrice_by_supplier() {
        return price_by_supplier;
    }

    public double getFinal_price() {
        return final_price;
    }

    public void printProcuctOrder() {
        System.out.println("Product Number: "+this.getProduct_id()+", Product Name: "+this.getProduct_name()+", Amount:"
                + this.getAmount()+ " Catalog Price:" + this.getPrice_by_supplier() + " Discount:"+ this.getDiscount() +
                " Final Price:" + this.getFinal_price());

    }
}

