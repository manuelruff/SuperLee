package Shipment.DataAccess;
import Shipment.Bussiness.Item;
import Shipment.Bussiness.Order;
import Shipment.Bussiness.Training;
import Shipment.Bussiness.Zone;
import resource.Connect;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapper {
    private static OrderMapper instance = new OrderMapper();
    private static Map<String, Order> orderMap;
    private static Connection conn;

    private OrderMapper() {
        orderMap = new HashMap<>();
        conn = Connect.getConnection();
    }
    public  static  OrderMapper getInstance(){
        return instance;
    }
    public static  Order getOrder(String ID){
        if (orderMap.get(ID)==null){
            readOrder(ID);
        }
        return orderMap.get(ID);
    }
    private static void readOrder(String ID)
    {
        String id,destination,zone,source;
        try{
            java.sql.Statement stat = conn.createStatement();
            java.sql.ResultSet rs = stat.executeQuery("select * from Orders WHERE ID=="+ID+"");
            if(rs.next())
            {
                id = rs.getString("ID");
                destination = rs.getString("destination");
                zone = rs.getString("zone");
                source = rs.getString("Source");
                Order order = new Order(destination,Zone.valueOf(zone),source);
                order.setID(id);// when writing back to the database maybe duplication
                orderMap.put(id,order);
                readItems(id);

            }
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry");

        }
    }
    private static void readItems(String ID)
    {
        String name,storage;
        int amount;
        try {
            java.sql.Statement stat = conn.createStatement();
            java.sql.ResultSet rs = stat.executeQuery("select * from Orders WHERE ID=="+ID+"" );
            while (rs.next())
            {
                name = rs.getString("Name");
                storage = rs.getString("Storage");
                amount = rs.findColumn("Amount");
                Item item = new Item(name,amount, Training.valueOf(storage));
                orderMap.get(ID).addItemToOrder(item);
            }
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry");

        }
    }
    public static void writeAllOrders()
    {
        for(Order order : orderMap.values())
        {
            String id = null,destination,source;
            Zone zone;
            List<Item> itemList;
            try{
                java.sql.Statement stat = conn.createStatement();
                id = order.getID();
                destination = order.getDestination();
                zone = order.getZone();
                source = order.getSource();
                java.sql.ResultSet rs = stat.executeQuery("select * from Order WHERE ID=="+id+"" );
                if(!rs.next())
                {
                    stat.executeUpdate("INSERT INTO Order(ID, Destination, Zone,Source) " +
                            "VALUES ('" + id + ", '" + destination + " ','" + zone.toString() + "','" + source + "')");
                }
                else {
                    stat.executeUpdate("UPDATE Order SET ID='" + id + "', Destination='" + destination + "', Zone=" + zone + ", Sorce= "+source+"  WHERE ID=" + id);
                }
            }
            catch (SQLException e)
            {
                System.out.println("i have a problem sorry");
            }
            writeItems(order);
        }
    }
    private static void writeItems(Order order)
    {
        try {
            java.sql.Statement stat = conn.createStatement();
            for(Item item : order.getItemList())
            {
                stat.executeUpdate("INSERT INTO Items(ID, Name, Amount,Storage) " +
                        "VALUES (" + order.getID() + ", '" + item.getName() + "', '" + item.getQuantity() + " '," + item.getStorageCondition().toString() + ")");
            }
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry");
        }
    }


}
