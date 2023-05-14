package Shipment.DataAccess;

import HR.Service.ShipmentService;
import Shipment.Bussiness.*;
import resource.Connect;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;

public class ShipmentMapper {
    private static ShipmentMapper instance = new ShipmentMapper();
    private ShipmentService shipmentService;
    private Map<String, Shipment> availableShipmentsMap;
    private Map<String, Shipment> shipmentsMap;
    private Connection conn;
    private VendorMapper vendorMapper;

    private ShipmentMapper()
    {
        availableShipmentsMap = new HashMap<>();
        shipmentsMap = new HashMap<>();
        conn = Connect.getConnection();
        shipmentService = ShipmentService.getInstance();
        vendorMapper = VendorMapper.getInstance();
    }

    static ShipmentMapper getInstance(){
        if (instance == null)
            instance = new ShipmentMapper();
        return instance;
    }


    public Map<String, Shipment> getShipmentsMap() {
        return shipmentsMap;
    }

    public Map<String, Shipment> getAvailableShipmentsMap() {
        return availableShipmentsMap;
    }
    public void addShipmentToAvailable(Shipment shipment)
    {
        availableShipmentsMap.putIfAbsent(shipment.getID(),shipment);
    }


    public Shipment getShipment(String shipmentID){
        if(!shipmentsMap.containsKey(shipmentID))
            readShipment(shipmentID);
        return shipmentsMap.get(shipmentID);

    }

    private Driver GetDriver(List<String> driverDetails)
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

    //todo roee do it
    public Shipment readShipment(String shipmentID){
        return null;
    }
    private void readItems(ItemsDoc itemsDoc)
    {
        String name,storage;
        int amount;
        try {
            java.sql.Statement stat = conn.createStatement();
            java.sql.ResultSet rs = stat.executeQuery("select * from Orders WHERE ID=='"+itemsDoc.getID()+"'" );
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
    public void getItemDocsCounter()
    {
        try
        {
            java.sql.Statement stat = conn.createStatement();
            java.sql.ResultSet rs = stat.executeQuery("SELECT * FROM StaticSaves WHERE Object == '"+"ItemDoc"+"' ");
            ItemsDoc.setCount(rs.getInt("LastID"));
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem in setting ItemDocs counter sorry");

        }
    }

    public void writeItemDocsCounter()
    {
        try {
            java.sql.Statement stat = conn.createStatement();
            stat.executeUpdate("UPDATE StaticSaves SET LastID=" + ItemsDoc.getCount() + " WHERE Object == '"+"ItemDoc"+"' ");
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem in writing ItemDocs counter sorry");
        }
    }
    private List<ItemsDoc> getItemDocs(String shipmentID)
    {
        String name,id;
        List<ItemsDoc> itemsDocs= new ArrayList<>();
        try
        {
            java.sql.Statement stat = conn.createStatement();
            java.sql.ResultSet rs = stat.executeQuery("SELECT * FROM ItemDocs WHERE ShipmentID == '"+shipmentID+"' ");
            while (rs.next())
            {
                name = rs.getString("Name");
                id = rs.getString("ID");
                ItemsDoc itemsDoc = new ItemsDoc(name);
                itemsDoc.fixCounter();
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
    private Site getBranch(String siteName)
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
    private List<Site> readDestinations(String shipmentID)
    {
        String name;
        List<Site> destinations=null;
        try
        {
            java.sql.Statement stat = conn.createStatement();
            java.sql.ResultSet rs = stat.executeQuery("SELECT * FROM ShipmentBranches WHERE ShipmentID == '"+shipmentID+"' ");
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

    public void getShipments()
    {
        String id,TruckNumber,DriverID,Source,Time,Status;
        Days day;
        LocalDate date;
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
                date = LocalDate.parse(rs.getString("Date"));
                Status = rs.getString("Status");
                if(Status != "Available") {
                    Shipment shipment = new Shipment(id, TruckNumber,GetDriver(shipmentService.askForDriver(DriverID)) , day, vendorMapper.getVendor(Source), readDestinations(id),getItemDocs(id),date);
                    //shipment.setDepartureTime(Time);//todo: set time
                    shipmentsMap.put(id,shipment);
                }

            }

        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry");
        }
    }
    public List<Shipment> getAvailableShipments()
    {
        String id,TruckNumber,DriverID,Source,Time,Status;
        Days day;
        LocalDate date;
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
                date = LocalDate.parse(rs.getString("Date"));
                if(Objects.equals(Status, "Available")) {
                    Shipment shipment = new Shipment(id, TruckNumber,GetDriver(shipmentService.askForDriver(DriverID)) , day, vendorMapper.getVendor(Source),readDestinations(id),getItemDocs(id),date);
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

    public void writeAllShipments()
    {
        for(Shipment shipment :shipmentsMap.values())
            writeShipment(shipment);
        for(Shipment shipment : availableShipmentsMap.values())
            writeShipment(shipment);

    }
    public void writeShipment(Shipment shipment)
    {
        String id,truckNumber,day,driverID,time = null,status,source,date;

        try{
            java.sql.Statement stat = conn.createStatement();
            id = shipment.getID();
            truckNumber = shipment.getTruckNumber();
            day = shipment.getDayOfTheWeek().toString();
            driverID = shipment.getDriver().getID();
            source = shipment.getSource().getName();
            status = shipment.getShipmentStatus().toString();
            date = shipment.getDate().toString();
            if(!Objects.equals(status, "available") && shipment.getDepartureTime() != null)// check how to enter time
                time = shipment.getDepartureTime().toString();
            java.sql.ResultSet rs = stat.executeQuery("select * from Shipments WHERE ID == '"+id+"'");
            if (!rs.next())
            {
                stat.executeUpdate("INSERT INTO Shipments(ID, TruckNumber, DriverID,Day,Source,Time,Status,Date) " +
                        "VALUES ('" + id + "', '" + truckNumber + "', '"+driverID+"' ,'" + day + "','" + source +"', '"+time+"', '"+status+"', '"+date+"')");
            }
            else
            {
                stat.executeUpdate("UPDATE Shipments SET ID='" + id + "', TruckNumber='" + truckNumber + "', DriverID='" + driverID + "', Source= '"+source+"', Time= '"+time+"', Status= '"+status+"',Date='" +date+ "'  WHERE ID=" + id);
            }
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry1");
        }
        writeItemDocs(shipment);
        writeDestinations(shipment);
    }
    private void writeDestinations(Shipment shipment)
    {
        try
        {
            java.sql.Statement stat = conn.createStatement();
            for(Site site : shipment.getDestinations())
            {
                stat.executeUpdate("INSERT OR IGNORE INTO ShipmentBranches(ShipmentID,SiteName) " +
                        "VALUES ('" + shipment.getID() + "', '" + site.getName() + "')");
            }
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry2");
        }
    }
    private void writeItemDocs(Shipment shipment)
    {
        try
        {
            java.sql.Statement stat = conn.createStatement();
            for(ItemsDoc itemsDoc : shipment.getDocs())
            {
                stat.executeUpdate("INSERT OR IGNORE INTO ItemDocs(ShipmentID, DocID, SiteName) " +
                        "VALUES ('" + shipment.getID() + "', '" + itemsDoc.getID() + "', '" + itemsDoc.getSiteName() + "')");
                writeItemsForDocs(itemsDoc);
            }
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry3");
        }
    }
    private void writeItemsForDocs(ItemsDoc itemsDoc)
    {
        try {
            java.sql.Statement stat = conn.createStatement();
            for(Item item : itemsDoc.getItemList())
            {
                stat.executeUpdate("INSERT OR IGNORE INTO ItemsForDocs(DocID, Name, Amount,Storage) " +
                        "VALUES ('" + itemsDoc.getID() + "', '" + item.getName() + "', " + item.getQuantity() + " ,'" + item.getStorageCondition().toString() + "')");
            }
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry4");
        }
    }
    public void deleteShipments()
    {
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate("DELETE FROM Shipments");
            stat.executeUpdate("DELETE FROM ShipmentBranches");
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry4");
        }

    }

}
