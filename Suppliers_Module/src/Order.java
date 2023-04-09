
import java.util.LinkedList;

public class Order {
    private String supplier_name;
    private int supplier_id;
    private String address;
    private String order_date;
    private int order_id;
    private String contact_phone;
    private LinkedList<Product_Order_Detailes> order_products;


    public Order(String name, int sup_id, String add, String date, int or_id, String contact_phone, LinkedList<Product_Order_Detailes> or_pro) {
        this.supplier_name = name;
        this.supplier_id = sup_id;
        this.address = add;
        this.order_date = date;
        this.order_id = or_id;
        this.contact_phone = contact_phone;
        this.order_products = or_pro;

    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public String getAddress() {
        return address;
    }

    public String getOrder_date() {
        return order_date;
    }

    public int getOrder_id() {
        return order_id;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public LinkedList<Product_Order_Detailes> getOrder_products() {
        return order_products;
    }

    public void addProductToOrder(Product_Order_Detailes p1) {
        this.order_products.add(p1);
    }

    public void PrintOrder() {
        System.out.println("--------ORDER-------");
        System.out.println("Supplier name: " + this.supplier_name + ", Address: " + this.address + ", Order id: " + this.order_id);
        System.out.println("Supplier id: " + this.supplier_id + ", Order date: " + this.order_date + ", Phone number to Contact: " + this.contact_phone);
        for (Product_Order_Detailes orderProduct : this.order_products) {
            orderProduct.printProcuctOrder();
        }
        System.out.println("");
    }
}
