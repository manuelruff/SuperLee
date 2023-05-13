package Shipment.DataAccess;
import Shipment.Bussiness.Item;
import Shipment.Bussiness.Order;
import Shipment.Bussiness.Training;
import Shipment.Bussiness.Zone;
import resource.Connect;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapper {
    private static OrderMapper instance = new OrderMapper();
    private Map<String, Order> orderMap;

    private Connection conn;

    private OrderMapper() {
        orderMap = new HashMap<>();
        conn = Connect.getConnection();
    }
    public static OrderMapper getInstance(){
        if (instance == null)
            instance = new OrderMapper();
        return instance;
    }

    public Map<String, Order> getOrderMap() {
        return orderMap;
    }

    public Order getOrder(String ID){
        if (orderMap.get(ID)==null){
            readOrder(ID);
        }
        return orderMap.get(ID);
    }
    public void addOrderToMap(Order order)
    {
        orderMap.putIfAbsent(order.getID(), order);
    }
    private void readOrder(String ID)
    {
        String id,destination,zone,source;
        try{
            java.sql.Statement stat = conn.createStatement();
            java.sql.ResultSet rs = stat.executeQuery("select * from Orders WHERE ID=='"+ID+"'");
            if(rs.next())
            {
                id = rs.getString("ID");
                destination = rs.getString("Destination");
                zone = rs.getString("Zone");
                source = rs.getString("Source");
                Order order = new Order(destination,Zone.valueOf(zone),source);
                order.setID(id);
                orderMap.put(id,order);
                readItems(id,order);

            }
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry1");

        }
    }
    public void readOrderWithVendor(String vendor)
    {
        String id,destination,zone,source;
        Map<String,List<Order>> vendorOrderMap = VendorMapper.getInstance().getVendorsOrderMap();
        List<Order> orders = new ArrayList<>();
        try{
            java.sql.Statement stat = conn.createStatement();
            java.sql.ResultSet rs = stat.executeQuery("select * from Orders WHERE Source=='"+vendor+"'");
            while(rs.next())
            {
                id = rs.getString("ID");
                destination = rs.getString("Destination");
                zone = rs.getString("Zone");
                source = rs.getString("Source");
                Order order = new Order(destination,Zone.valueOf(zone),source);
                order.minusCount();
                order.setID(id);
                orderMap.put(id,order);
                readItems(id,order);
                orders.add(order);
                deleteItems(id);
            }
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry1");
        }
        try {
            java.sql.Statement stat = conn.createStatement();
            stat.executeUpdate("DELETE FROM Orders WHERE Source ==  '" + vendor + "' ");
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry1");
        }
        vendorOrderMap.put(vendor,orders);
    }


    private void readItems(String ID, Order order)
    {
        String name,storage;
        int amount;
        try {
            java.sql.Statement stat = conn.createStatement();
            java.sql.ResultSet rs = stat.executeQuery("select * from OrderItems WHERE ID=='"+ID+"'" );
            while (rs.next())
            {
                name = rs.getString("Name");
                storage = rs.getString("Storage");
                amount = rs.findColumn("Amount");
                Item item = new Item(name,amount, Training.valueOf(storage));
                order.addItemToOrder(item);
            }
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry");

        }
    }
    private  void deleteItems(String id)
    {
        try {
            java.sql.Statement stat = conn.createStatement();
            java.sql.ResultSet rs = stat.executeQuery("select * from OrderItems WHERE ID=='" + id + "'");
            while (rs.next()) {
                stat.executeUpdate("DELETE FROM OrderItems WHERE ID = " + id);
            }
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem in deleting items sorry");
        }
    }
    public void writeAllOrders()
    {
        for(Order order : orderMap.values())
        {
            String id ,destination,source;
            Zone zone;
            try{
                java.sql.Statement stat = conn.createStatement();
                id = order.getID();
                destination = order.getDestination();
                zone = order.getZone();
                source = order.getSource();
                stat.executeUpdate("INSERT OR IGNORE INTO Orders(ID, Destination, Zone,Source) " +
                        "VALUES (" + id + ",'" + destination + "','" + zone.toString() + "','" + source + "')");
            }
            catch (SQLException e)
            {
                System.out.println("i have a problem sorry2");
            }
            writeItems(order);
        }
    }
    private void writeItems(Order order)
    {
        try {
            java.sql.Statement stat = conn.createStatement();
            for(Item item : order.getItemList())
            {
                stat.executeUpdate("INSERT OR IGNORE INTO OrderItems(ID, Name, Amount,Storage) " +
                        "VALUES ('" + order.getID() + "', '" + item.getName() + "', '" + item.getQuantity() + " ','" + item.getStorageCondition().toString() + "')");
            }
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry3");
        }
    }

    public void readStaticSave(){
        int rs;
        try{
            java.sql.Statement stat = conn.createStatement();
            rs = stat.executeUpdate("select * from StaticSaves WHERE Object=='" + "Order" + "'");
            Order.setCount(rs);
        }
        catch (SQLException e) {
            System.out.println("can't read");
        }
    }

    public void writeStaticSave(){
        int count = Order.getCount();
        try {
            java.sql.Statement stat = conn.createStatement();
            stat.executeUpdate("UPDATE StaticSaves SET LastID = '"+count+"' WHERE object == 'Order'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
