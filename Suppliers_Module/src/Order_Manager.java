
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Order_Manager {
    private static Order_Manager order_manager = null;

    private LinkedList<Order> orders;

    public static Order_Manager getOrder_Manager() {
        if (order_manager == null) {
            order_manager = new Order_Manager();
        }
        return order_manager;
    }

//    public Order_Manager(LinkedList<Order> orders) {
//        this.orders = orders;
//    }

    public LinkedList<Order> getOrders() {
        return orders;
    }

    //create product to order from yuvi and freshi order
    public Product_Order_Detailes createProductOrder(Product p1, int amountOfUnits) {
        double dis = 0;
        for (int i = 0; i < p1.getDiscounts().size(); i++) {
            if (p1.getDiscounts().get(i).getAmountRange().getMax() > amountOfUnits && p1.getDiscounts().get(i).getAmountRange().getMin() < amountOfUnits) {
                dis = p1.getDiscounts().get(i).getPercentage();
                break;
            }

        }
        Product_Order_Detailes pod1 = new Product_Order_Detailes(p1.getLocal_key(), p1.getProduct_name(), amountOfUnits, dis, p1.getUnit_price());
        return pod1;
    }

    public Map  FromProductToOrder() {
        Map<String, Integer> Product_toOrder = new HashMap<String, Integer>();
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (choice != 5) {
            System.out.println("Please select an option:");
            System.out.println("1. Add product to Order");
            System.out.println("2. Remove product from the current order");
            System.out.println("3. Edit product from the current order");
            System.out.println("4. View of the current order");
            System.out.println("5. Send a request for Order");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Plaese enter the product name:");
                    String name = input.nextLine();
                    System.out.println("Plaese enter the amount of" + name);
                    int amount = input.nextInt();
                    Product_toOrder.put(name, amount);
                    break;
                case 2:
                    System.out.println("Plaese enter the product name:");
                    String name2 = input.nextLine();
                    boolean exist = Product_toOrder.containsKey(name2);
                    if (exist) {
                        Product_toOrder.remove(name2);
                    } else {
                        System.out.println("This product is not include in the current order.");
                    }
                    break;
                case 3:
                    System.out.println("Plaese enter the product name:");
                    String name3 = input.nextLine();
                    boolean exist3 = Product_toOrder.containsKey(name3);
                    if (exist3) {
                        int amount3 = Product_toOrder.get(name3);
                        System.out.println("The current amount of " + name3 + " is " + amount3 + ", what is the correct quantity requested?");
                        int new_amount = input.nextInt();
                        Product_toOrder.put(name3, new_amount);
                    } else {
                        System.out.println("This product is not include in the current order.");
                    }
                    break;
                case 4:
                    System.out.println("The current order is displayed in front of you:");
                    for (Map.Entry<String, Integer> iter : Product_toOrder.entrySet()) {
                        System.out.println("Product: " + iter.getKey() + ", Amount: " + iter.getValue());
                    }
                    break;
                case 5:
                    System.out.println("The Order is on it way!");
                    return Product_toOrder;
                //////////////////////////to create the order from the current map//////////////////////
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
        return Product_toOrder;
    }
}




