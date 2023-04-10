import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class User {

    private static User user = null;

    //singleton
    LinkedList<Order> all_orders;
    SupplyManager sup_manager;
    Order_Manager order_manager;
    static int counter = 0;
    Scanner input = new Scanner(System.in);

//    public User(LinkedList<Order> all_orders, SupplyManager sup_manager, Order_Manager order_manager) {
//        this.all_orders = all_orders;
//        this.sup_manager = SupplyManager.getSupply_manager();
//        this.order_manager = order_manager;
//    }


    private User() {
        this.sup_manager = SupplyManager.getSupply_manager();
        this.order_manager = Order_Manager.getOrder_Manager();
    }

    public static User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public void begin(){
        int choice1 = 0;
        while (choice1!=3) {
            System.out.println("Welcome to Suppliers system! \nDo you want to upload existing data, edit current data,create new data or start over? \n1.Load data \n2.Edit existing data  \n3.Exit");
            choice1 = input.nextInt();
            switch (choice1) {
                case 1:
                {
                    user.sup_manager.loadData();
                    System.out.println("Data load successfully!");
                    break;
                }

                case 2:
                    System.out.println("Please select one of the following options:");
                    Menu();
                    break;
                default:
                    System.out.println("This is not valid option!");
            }
        }
    }

    public void Menu(){
        int choice = 9;
        while (choice != 0)
        {
            System.out.println("1. Add new supplier.");
            System.out.println("2. Edit existing supplier details.");
            System.out.println("3. Delete an existing supplier.");
            System.out.println("4. Create new order.");
            System.out.println("5. View order history of supplier");
            System.out.println("0. Back");
            choice = input.nextInt();

            switch (choice)
            {
                case 1:
                {
                    this.sup_manager.CreateSupplier();
                    break;
                }

                case 2:
                {
                    this.sup_manager.EditSupplier();
                    break;
                }

                case 3:
                {
                    this.sup_manager.removeSupplierByUser();
                    break;
                }
                case 4: {
                    this.addOrder();
                    break;
                }
                case 5:
                {
                    this.sup_manager.PrintSupplierOrders();
                    break;
                }

                default:
                {
                    System.out.println("This option is not valid!");
                    break;
                }

            }
        }


    }



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //take the products that can supply by one supplier
    //take all the suppliers can supply products from this order and save their relevant products
    public Map<Supplier,Map<String,Integer>> fullProductslist(Map<String,Integer> ans1) {
        //save all the products provide by one supplier and their suppliers
        Map<Supplier,Map<String,Integer>> goodSup = new HashMap<>();
        //check for each product from the map
        for (Map.Entry<String, Integer> iter : ans1.entrySet()) {
            //find this product in our data
            for (Map.Entry<String, LinkedList<Supplier>> iter2 : this.sup_manager.getSupplierByProduct().entrySet()) {
                //find match in our data
                if (iter.getKey().compareTo(iter2.getKey()) == 0) {
                    //check if there is supplier with enough amount
                    for (int k = 0; k < iter2.getValue().size(); k++) {
                        //run all the supplier products
                        for (int p = 0; p < iter2.getValue().get(k).agreement.getProductList().size(); p++) {
                            //the correct product and enough amount
                            if ((iter2.getValue().get(k).agreement.getProductList().get(p).getProduct_name().compareTo(iter.getKey()) == 0) && (iter2.getValue().get(k).agreement.getProductList().get(p).getAmount_available() >= iter.getValue())) {
                                //if its good supplier and product and the supplier already in the map
                                if (goodSup.containsKey(iter2.getValue().get(k))) {
                                    goodSup.get(iter2.getValue().get(k)).put(iter.getKey(), iter.getValue());
                                } else {
                                    Map<String, Integer> temp = new HashMap<>();
                                    temp.put(iter.getKey(), iter.getValue());
                                    goodSup.put(iter2.getValue().get(k), temp);
                                }
                            }
                        }
                    }
                }
            }
        }
        return goodSup;
    }

    /////////////////////////select the best supplier right now////////////////////////////////
    public Supplier bestSupplier(Map<Supplier,Map<String,Integer>> ans){
        //reset to the first arguments in the map
        int num_ofProduct =  ans.get(ans.keySet().toArray()[0]).size();
        Supplier s1 = (Supplier) ans.keySet().toArray()[0];
        double best = fullPricreOrder(s1, (Map<String, Integer>) ans.values().toArray()[0]);
        for(Map.Entry<Supplier,Map<String,Integer>> iter : ans.entrySet()) {
            //if there is more optional products and if it cheaper
            if (iter.getValue().size() > num_ofProduct) {
                num_ofProduct = iter.getValue().size();
                s1 = iter.getKey();
                best = fullPricreOrder(iter.getKey(), iter.getValue());
                //if they have the same amount of optional products
            } else if (iter.getValue().size() == num_ofProduct) {
                if (fullPricreOrder(iter.getKey(), iter.getValue()) < best) {
                    s1 = iter.getKey();
                    best = fullPricreOrder(iter.getKey(), iter.getValue());
                }
            }
        }
        return s1;
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    //get the final price of product by supplier by given supplier, product and amount
    public double productPriceBySupplier(Supplier supplier, String product_name, int amount){
        Product p1 = null;
        for(int i =0; i <supplier.agreement.getProductList().size(); i++) {
            if (supplier.agreement.getProductList().get(i).getProduct_name().compareTo(product_name) == 0) {
                p1 = supplier.agreement.getProductList().get(i);
                break;
            }
        }
        double discount = 0;
        for(int j =0; j < p1.getDiscounts().size(); j++){
            if(p1.getDiscounts().get(j).getAmountRange().getMax()>amount && p1.getDiscounts().get(j).getAmountRange().getMin()< amount){
                discount = p1.getDiscounts().get(j).getPercentage();
                break;
            }
        }
        double unitPrice = p1.getUnit_price();
        return amount*unitPrice*(1-(discount*0.01));

    }
    //calculate and return total price for suppliers and map of products and amount dor each of them
    public double fullPricreOrder(Supplier supplier, Map<String,Integer> ans1){
        double total =0;
        int amount =0;
        for (Map.Entry<String, Integer> iter : ans1.entrySet()) {
            total += productPriceBySupplier(supplier, iter.getKey(), iter.getValue());
            amount+=iter.getValue();
        }
        double total_discount = 0;
        for(int i = 0; i<supplier.agreement.getTotal_orderDiscount().size(); i++){
            if(supplier.agreement.getTotal_orderDiscount().get(i).getAmountRange().getMin() > amount && supplier.agreement.getTotal_orderDiscount().get(i).getAmountRange().getMax() < amount){
                total_discount = supplier.agreement.getTotal_orderDiscount().get(i).getPercentage();
                break;
            }
        }
        return total*(1-(total_discount*0.01));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //return list of product ready to order
    public LinkedList<Product_Order_Detailes> fromProductsToOrder(Supplier supplier, Map<String,Integer> ans1){
        LinkedList<Product_Order_Detailes> ans = new LinkedList<>();
        for(Map.Entry<String, Integer> iter : ans1.entrySet()){
            ans.add(fromProductToProductOrder(supplier, iter.getKey(), iter.getValue()));
        }
        return ans;
    }
    //return product order detail from product and supplier
    public Product_Order_Detailes fromProductToProductOrder(Supplier supplier, String name,int amount){
        Product p2 = null;
        double dis =0;
        for(int i = 0; i< supplier.agreement.getProductList().size(); i++){
            if(supplier.agreement.getProductList().get(i).getProduct_name().compareTo(name)==0){
                p2 = supplier.agreement.getProductList().get(i);
                for(int j=0; j< p2.getDiscounts().size(); j++){
                    if(p2.getDiscounts().get(j).getAmountRange().getMax()>amount && p2.getDiscounts().get(j).getAmountRange().getMin() <amount){
                        dis = p2.getDiscounts().get(j).getPercentage();
                        break;
                    }
                }

            }
        }
        return new Product_Order_Detailes(p2.getLocal_key(), p2.getProduct_name(), amount, dis, p2.getUnit_price());
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Map<Supplier,LinkedList<Product_Order_Detailes>> allFullProducts(Map<String,Integer> ans) {
        Map<Supplier, LinkedList<Product_Order_Detailes>> allFullProducts = new HashMap<>();
        while (!ans.isEmpty()) {
            //save all the suppliers and the current products
            Map<Supplier, Map<String, Integer>> temp1 = fullProductslist(ans);
            //chose the supplier with most products. if there is more than one, chose the cheapest.
            Supplier s1 = bestSupplier(temp1);
            //get his list of product order details
            LinkedList<Product_Order_Detailes> pods1 = fromProductsToOrder(s1, temp1.get(s1));
            //add the supplier and his list to the map
            allFullProducts.put(s1, pods1);
            //remove the product from ans - run all the product the chosen supplier and remove them
            for (Map.Entry<String, Integer> iter : temp1.get(s1).entrySet()) {
                ans.remove(iter.getKey());
            }
        }
        return allFullProducts;
    }


    //function that get list of products cant provided by one supplier and try to create the best combination for them
    public Map<Supplier,LinkedList<Product_Order_Detailes>> allHalfProduct(Map<String,Integer> products, Map<Supplier,LinkedList<Product_Order_Detailes>> ans){

        for (Map.Entry<String, Integer> iter : products.entrySet()){
            if(halfProduct(iter.getKey(), iter.getValue(), ans)==null){
                System.out.println("The order canceled! the product "+ iter.getKey() + "cant provide with the amount of "+ iter.getValue() + "!.");
                return null;
            }
        }
        return ans;

    }
    //function that get product cant provided by one supplier and try to create the best combination for it
    public Map<Supplier,LinkedList<Product_Order_Detailes>> halfProduct(String name, int amount, Map<Supplier,LinkedList<Product_Order_Detailes>> ans) {
        if (!isEnough(name, amount)) {
            return null;
        } else {
            while (amount != 0) {

                LinkedList<Supplier> suppliers = this.sup_manager.getSupplierByProduct().get(name);
                Supplier s1 = suppliers.getFirst();
                int max_amount = 0;
                for (int i = 0; i < suppliers.get(0).agreement.getProductList().size(); i++) {
                    if (suppliers.get(0).agreement.getProductList().get(i).getProduct_name().compareTo(name) == 0) {
                        max_amount = suppliers.get(0).agreement.getProductList().get(i).getAmount_available();
                    }
                }
                //find the current best supplier that can provide most of the product
                for (Supplier supplier : suppliers) {
                    for (int i = 0; i < supplier.agreement.getProductList().size(); i++) {
                        if (supplier.agreement.getProductList().get(i).getProduct_name().compareTo(name) == 0) {
                            if (supplier.agreement.getProductList().get(i).getAmount_available() > max_amount) {
                                s1 = supplier;
                                max_amount = supplier.agreement.getProductList().get(i).getAmount_available();
                            }
                            //if both can provide the same amount check who is cheaper
                            else if (supplier.agreement.getProductList().get(i).getAmount_available() == max_amount) {
                                if (productPriceBySupplier(supplier, name, amount) < productPriceBySupplier(s1, name, amount)) {
                                    s1 = supplier;
                                    max_amount = supplier.agreement.getProductList().get(i).getAmount_available();
                                }

                            }
                        }
                    }
                }
                //now we have the supplier can provide most ot the product in this time
                //check if we sub from the amount to much - for not provide more than requested
                if(max_amount > amount){
                    max_amount = amount;
                }
                //create product order detail from the product
                Product_Order_Detailes pod1 = fromProductToProductOrder(s1, name, amount);
                //check if the supplier already in the list if yes just add the product to his list
                if(ans.containsKey(s1)){
                    ans.get(s1).add(pod1);
                }
                //if the supplier is not exist in the list
                else{
                    LinkedList<Product_Order_Detailes> temp = new LinkedList<>();
                    temp.add(pod1);
                    ans.put(s1,temp);
                }
                amount = amount-max_amount;
                suppliers.remove(s1);
            }
            return ans;
        }
    }


    //check for product if it can be provided by suppliers
    public boolean isEnough(String name, int amount){
        LinkedList<Supplier> suppliers = this.sup_manager.getSupplierByProduct().get(name);
        boolean success = false;
        int count =0;
        //for each supplier check the amount he can provide from this product
        for (Supplier s1 : suppliers) {
            for (int j = 0; j < s1.agreement.getProductList().size(); j++) {
                Product p1 = s1.agreement.getProductList().get(j);
                if (p1.getProduct_name().compareTo(name) == 0) {
                    count += p1.getAmount_available();
                }
            }
        }
        if(count >= amount){
            success =true;
        }
        return success;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //check if product can be provided by one supplier
    public boolean oneSupplier(String name,int amount){
        LinkedList<Supplier> suppliers = this.sup_manager.getSupplierByProduct().get(name);
        boolean success = false;
        //for each supplier check the amount he can provide from this product
        for (Supplier s1 : suppliers) {//TODO : if sucess true break
            for (int j = 0; j < s1.agreement.getProductList().size(); j++) {
                Product p1 = s1.agreement.getProductList().get(j);
                if ((p1.getProduct_name().compareTo(name) == 0) && (p1.getAmount_available() >= amount)) {
                    success = true;
                    break;
                }
            }
        }
        return success;
    }

    //main function use all the help function - split all the products to 2 lists - one supplier or some and work on it
    public Map<Supplier,LinkedList<Product_Order_Detailes>> allProductsOrder(Map<String,Integer> ans){
        if(ans.isEmpty()){
            return null;
        }
        //save all the products provide by one supplier
        Map<String,Integer> oneSup = new HashMap<>();
        //save all the products provide by more than one supplier
        Map<String,Integer> moreThanOne = new HashMap<>();
        //check for each product from the map
        for (Map.Entry<String, Integer> iter : ans.entrySet()) {
            if(oneSupplier(iter.getKey(), iter.getValue())){
                oneSup.put(iter.getKey(),iter.getValue());
            }
            else {
                moreThanOne.put(iter.getKey(),iter.getValue());
            }
        }
        Map<Supplier,LinkedList<Product_Order_Detailes>> ret = allFullProducts(oneSup);
        Map<Supplier,LinkedList<Product_Order_Detailes>> ret12 =  allHalfProduct(moreThanOne, ret);
        return ret12;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public LinkedList<Order> current_orders( Map<Supplier,LinkedList<Product_Order_Detailes>> ToOrder){
        LinkedList<Order> current = new LinkedList<>();
        for (Map.Entry<Supplier, LinkedList<Product_Order_Detailes>> iter : ToOrder.entrySet()) {
            Supplier s1 =iter.getKey();
            String name_ = s1.getCard().getSupplier_name();
            int id_ = s1.card.getSupplier_number();
            String address = s1.getCard().getAddress();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String date = dtf.format(now);
            int or_id = counter;
            List<ContactMember> mem = s1.card.getContact_members();
            String phone = mem.get(0).getPhone_number();
            LinkedList<Product_Order_Detailes> pro_or = iter.getValue();
            Order or1 = new Order(name_, id_, address, date, or_id, phone, pro_or);
            current.add(or1);
            s1.addOrderToHistory(or1);

        }
        return current;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public LinkedList<Order> OrdersByRequest(Map<String,Integer> ans){
        Map<Supplier,LinkedList<Product_Order_Detailes>> ToOrder = allProductsOrder(ans);
        LinkedList<Order> orders = current_orders(ToOrder);
        return orders;
    }

    public void addOrder(){
        int moreItems=1;

        System.out.println("--Adding Products To Order--");
        Map<String, Integer> currentOrder = new HashMap<>();
        while(moreItems==1) {
            System.out.println("Please enter the name of the product you want to order");
            Scanner input1 = new Scanner(System.in);
            String name = input1.next();
            boolean flag = this.sup_manager.getSupplierByProduct().containsKey(name);
            if (!flag) {
                System.out.println("This Product is not available");
            } else {
                System.out.println("Please enter the amount of " + name + " you want to order");
                Scanner input2 = new Scanner(System.in);
                int amount = input2.nextInt();
                currentOrder.put(name,amount);
            }
            System.out.println("For adding another product press 1, else press another number");
            moreItems = input.nextInt();
        }
        LinkedList<Order> orders = OrdersByRequest(currentOrder);
        for (int i =0 ; i < orders.size(); i++){
            orders.get(i).PrintOrder();
        }
    }

}

