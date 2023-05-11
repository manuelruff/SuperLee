package Shipment.DataAccess;

import HR.Service.ShipmentService;
import Shipment.Bussiness.*;
import resource.Connect;


import java.net.IDN;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ShipmentMapper {
    private static  ShipmentMapper instance = new ShipmentMapper();
    private static ShipmentService shipmentService;
    private static Map<String, Shipment> availableShipmentsMap;
    private static Map<String, Shipment> shipmentsMap;
    private static Connection conn;
    private static VendorMapper vendorMapper;

    private ShipmentMapper()
    {
        availableShipmentsMap = new HashMap<>();
        shipmentsMap = new HashMap<>();
        conn = Connect.getConnection();
        shipmentService = ShipmentService.getInstance();
        vendorMapper = VendorMapper.getInstance();
    }

    private static ShipmentMapper getInstance(){
        if (instance == null)
            instance = new ShipmentMapper();
        return instance;
    }

    private static Driver GetDriver(List<String> driverDetails)
    {
        String name,id,licence,ability,workingDays;
        name = driverDetails.get(0);
        id = driverDetails.get(1);
        licence = driverDetails.get(2);
        ability = driverDetails.get(3);
        Driver driver = new Driver(id,name,licence.charAt(0), Training.valueOf(ability));
        for(int i = 4; i < driverDetails.size(); i++)
            driver.addNewDay(Days.valueOf(driverDetails.get(i)));
        return driver;
    }
    private static void readItems(ItemsDoc itemsDoc)
    {
        String name,storage;
        int amount;
        try {
            java.sql.Statement stat = conn.createStatement();
            java.sql.ResultSet rs = stat.executeQuery("select * from Orders WHERE ID=="+itemsDoc.getID()+"" );
            while (rs.next())
            {
                name = rs.getString("Name");
                storage = rs.getString("Storage");
                amount = rs.findColumn("Amount");
                Item item = new Item(name,amount, Training.valueOf(storage));
                itemsDoc.addItemToDoc(item);
            }
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry");

        }
    }
    private static List<ItemsDoc> getItemDocs(String shipmentID)
    {
        String name,id;
        List<ItemsDoc> itemsDocs=null;
        try
        {
            java.sql.Statement stat = conn.createStatement();
            java.sql.ResultSet rs = stat.executeQuery("SELECT * FROM ItemDocs WHERE ShipmentID == "+shipmentID+" ");
            while (rs.next())
            {
                name = rs.getString("Name");
                id = rs.getString("ID");
                ItemsDoc itemsDoc = new ItemsDoc(name);
                itemsDoc.setID(id);
                itemsDocs.add(itemsDoc);
                readItems(itemsDoc);
            }
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry");
        }
        return itemsDocs;
    }
    private static Site getBranch(String siteName)
    {
        String address,phoneNumber,contactName;
        Zone zone;
        List<String> siteDetails = shipmentService.askForSite(siteName);
        address = siteDetails.get(0);
        phoneNumber = siteDetails.get(1);
        contactName = siteDetails.get(2);
        zone = Zone.valueOf(siteDetails.get(3));
        return new Branch(siteName,address,phoneNumber,contactName,zone);
    }
    private static List<Site> readDestinations(String shipmentID)
    {
        String name;
        List<Site> destinations=null;
        try
        {
            java.sql.Statement stat = conn.createStatement();
            java.sql.ResultSet rs = stat.executeQuery("SELECT * FROM ShipmentBranches WHERE ShipmentID == "+shipmentID+" ");
            while (rs.next())
            {
                name = rs.getString("SiteName");
                Site site = getBranch(name);
                destinations.add(site);
            }
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry");
        }
        return destinations;
    }

    public static List<Shipment> getShipments()
    {
        String id,TruckNumber,DriverID,Source,Time,Status;
        Days day;
        List<Shipment> shipments = null;
        try{
            java.sql.Statement stat = conn.createStatement();
            java.sql.ResultSet rs = stat.executeQuery("SELECT * FROM Shipments");
            while (rs.next())
            {
                id = rs.getString("ID");
                TruckNumber = rs.getString("TruckNumber");
                DriverID = rs.getString("DriverID");
                Source = rs.getString("Source");
                Time = rs.getString("Time");
                day = Days.valueOf(rs.getString("Day"));
                Status = rs.getString("Status");
                if(Status != "available") {
                    Shipment shipment = new Shipment(id, TruckNumber,GetDriver(shipmentService.askForDriver(DriverID)) , day, vendorMapper.getVendor(Source), readDestinations(id),getItemDocs(id));
                    //shipment.setDepartureTime(Time);//todo: set time
                    shipments.add(shipment);
                    shipmentsMap.put(id,shipment);
                }

            }

        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry");
        }
        return shipments;
    }
    public static List<Shipment> getAvailableShipments()
    {
        String id,TruckNumber,DriverID,Source,Time,Status;
        Days day;
        List<Shipment> availableShipments = null;
        try{
            java.sql.Statement stat = conn.createStatement();
            java.sql.ResultSet rs = stat.executeQuery("SELECT * FROM Shipments");
            while (rs.next())
            {
                id = rs.getString("ID");
                TruckNumber = rs.getString("TruckNumber");
                DriverID = rs.getString("DriverID");
                Source = rs.getString("Source");
                Time = rs.getString("Time");
                day = Days.valueOf(rs.getString("Day"));
                Status = rs.getString("Status");

                if(Objects.equals(Status, "available")) {
                    Shipment shipment = new Shipment(id, TruckNumber,GetDriver(shipmentService.askForDriver(DriverID)) , day, vendorMapper.getVendor(Source),readDestinations(id),getItemDocs(id));
                    //shipment.setDepartureTime(Time);//todo: set time
                    availableShipments.add(shipment);
                    availableShipmentsMap.put(id,shipment);
                }

            }

        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry");
        }
        return availableShipments;
    }

    public static void writeAllShipments()
    {
        for(Shipment shipment :shipmentsMap.values())
            writeShipment(shipment);
        for(Shipment shipment : availableShipmentsMap.values())
            writeShipment(shipment);

    }
    public static void writeShipment(Shipment shipment)
    {
        String id,truckNumber,day,driverID,time = null,status,source;
        try{
            java.sql.Statement stat = conn.createStatement();
            id = shipment.getID();
            truckNumber = shipment.getTruckNumber();
            day = shipment.getDayOfTheWeek().toString();
            driverID = shipment.getDriver().getID();
            source = shipment.getSource().toString();
            status = shipment.getShipmentStatus().toString();
            if(!Objects.equals(status, "available") && shipment.getDepartureTime() != null)// check how to enter time
                time = shipment.getDepartureTime().toString();
            java.sql.ResultSet rs = stat.executeQuery("select * from Shipments WHERE ID == "+id+"");
            if (!rs.next())
            {
                stat.executeUpdate("INSERT INTO Shipments(ID, TruckNumber, DriverID,Day,Source,Time,Status) " +
                        "VALUES ('" + id + "', '" + truckNumber + "', '"+driverID+"' ,'" + day + "','" + source +"', '"+time+"', '"+status+"')");
            }
            else
            {
                stat.executeUpdate("UPDATE Shipments SET ID='" + id + "', TruckNumber='" + truckNumber + "', DriverID=" + driverID + ", Sorce= "+source+", Time= "+time+", Status= "+source+"  WHERE ID=" + id);
            }
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry");
        }
        writeItemDocs(shipment);
        writeDestinations(shipment);
    }
    private static void writeDestinations(Shipment shipment)
    {
        try
        {
            java.sql.Statement stat = conn.createStatement();
            for(Site site : shipment.getDestinations())
            {
                stat.executeUpdate("INSERT INTO ShipmentBranches(ShipmentID,SiteName) " +
                        "VALUES (" + shipment.getID() + ", '" + site.getName() + " ')");
            }
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry");
        }
    }
    private static void writeItemDocs(Shipment shipment)
    {
        try
        {
            java.sql.Statement stat = conn.createStatement();
            for(ItemsDoc itemsDoc : shipment.getDocs())
            {
                stat.executeUpdate("INSERT INTO ItemDocs(ShipmentID, DocID, SiteName) " +
                        "VALUES (" + shipment.getID() + ", '" + itemsDoc.getID() + "', '" + itemsDoc.getSiteName() + " ')");
                writeItemsForDocs(itemsDoc);
            }
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry");
        }
    }
    private static void writeItemsForDocs(ItemsDoc itemsDoc)
    {
        try {
            java.sql.Statement stat = conn.createStatement();
            for(Item item : itemsDoc.getItemList())
            {
                stat.executeUpdate("INSERT INTO ItemsForDocs(DocID, Name, Amount,Storage) " +
                        "VALUES (" + itemsDoc.getID() + ", '" + item.getName() + "', '" + item.getQuantity() + " '," + item.getStorageCondition().toString() + ")");
            }
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry");
        }
    }


}
