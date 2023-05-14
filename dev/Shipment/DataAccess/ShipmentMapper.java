package Shipment.DataAccess;

import HR.Service.ShipmentService;
import Shipment.Bussiness.*;
import resource.Connect;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
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

    private void readShipment(String shipmentID) {
        String id, truckNumber, driverID, source, time, status;
        Days day;
        LocalDate date;
        try {
            java.sql.Statement stat = conn.createStatement();
            java.sql.ResultSet rs = stat.executeQuery("SELECT * FROM Shipments WHERE ID=='"+shipmentID+"'" );
            if (rs.next()) {
                id = rs.getString("ID");
                truckNumber = rs.getString("TruckNumber");
                driverID = rs.getString("DriverID");
                source = rs.getString("Source");
                time = rs.getString("Time");
                day = Days.valueOf(rs.getString("Day"));
                date = LocalDate.parse(rs.getString("Date"));
                status = rs.getString("Status");
                Shipment shipment = new Shipment(id, truckNumber,GetDriver(shipmentService.askForDriver(driverID)), day, vendorMapper.getVendor(source), readDestinations(id),getItemDocs(id),date);
                shipment.setDepartureTime(LocalTime.parse(time));
                shipment.setShipmentStatus(Status.valueOf(status));
                shipmentsMap.put(id,shipment);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Driver GetDriver(List<String> driverDetails)
    {
        String name,id,licence,ability;
        name = driverDetails.get(1);
        id = driverDetails.get(0);
        licence = driverDetails.get(2);
        ability = driverDetails.get(3);
        Driver driver = new Driver(id,name,licence.charAt(0), Training.valueOf(ability));
        for(int i = 4; i < driverDetails.size(); i++)
            driver.addNewDay(Days.valueOf(driverDetails.get(i)));
        return driver;
    }

    private void readItems(ItemsDoc itemsDoc)
    {
        String name,storage;
        int amount;
        try {
            java.sql.Statement stat = conn.createStatement();
            java.sql.ResultSet rs = stat.executeQuery("SELECT * FROM ItemsForDocs WHERE DocID='"+itemsDoc.getID()+"'" );
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
            System.out.println("i have a problem sorry1");

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
            System.out.println("i have a problem in setting ItemDocs counter sorry2");

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
            System.out.println("i have a problem in writing ItemDocs counter sorry3");
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
                name = rs.getString("SiteName");
                id = rs.getString("DocID");
                ItemsDoc itemsDoc = new ItemsDoc(name);
                itemsDoc.fixCounter();
                itemsDoc.setID(id);
                itemsDocs.add(itemsDoc);
                readItems(itemsDoc);
            }
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry4");
        }
        return itemsDocs;
    }
    private Site getBranch(String siteName)
    {
        String address,phoneNumber,contactName;
        Zone zone;
        List<String> siteDetails = shipmentService.askForSite(siteName);
        siteName = siteDetails.get(0);
        address = siteDetails.get(1);
        phoneNumber = siteDetails.get(2);
        contactName = siteDetails.get(3);
        zone = Zone.valueOf(siteDetails.get(4));
        return new Branch(siteName,address,phoneNumber,contactName,zone);
    }
    private List<Site> readDestinations(String shipmentID)
    {
        String name;
        List<Site> destinations= new ArrayList<>();
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
            System.out.println("i have a problem sorry5");
        }
        return destinations;
    }

    public void getShipments()
    {
        String id,TruckNumber,DriverID,Source,Time,status;
        Days day;
        LocalDate date;
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
                status = rs.getString("Status");
                if(!Objects.equals(status, "Available")) {
                    Shipment shipment = new Shipment(id, TruckNumber,GetDriver(shipmentService.askForDriver(DriverID)), day, vendorMapper.getVendor(Source), readDestinations(id),getItemDocs(id),date);
                    shipment.setDepartureTime(LocalTime.parse(Time));
                    shipment.setShipmentStatus(Status.valueOf(status));
                    shipmentsMap.put(id,shipment);
                }

            }

        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry6");
        }
    }
    public void getAvailableShipments()
    {
        String id,TruckNumber,DriverID,Source,Time,status;
        Days day;
        LocalDate date;

        try{
            java.sql.Statement stat = conn.createStatement();
            java.sql.ResultSet rs = stat.executeQuery("SELECT * FROM Shipments");
            while (rs.next())
            {
                Shipment shipment;
                id = rs.getString("ID");
                TruckNumber = rs.getString("TruckNumber");
                DriverID = rs.getString("DriverID");
                Source = rs.getString("Source");
                Time = rs.getString("Time");
                day = Days.valueOf(rs.getString("Day"));
                status = rs.getString("Status");
                date = LocalDate.parse(rs.getString("Date"));
                if(Objects.equals(status, "Available")) {
                    if (Objects.equals(DriverID, "null"))
                         shipment = new Shipment(id, TruckNumber , day, vendorMapper.getVendor(Source),readDestinations(id),getItemDocs(id),date);
                    else{shipment = new Shipment(id, TruckNumber,GetDriver(shipmentService.askForDriver(DriverID)) , day, vendorMapper.getVendor(Source),readDestinations(id),getItemDocs(id),date);}
                    if (!Objects.equals(Time, "null"))
                        shipment.setDepartureTime(LocalTime.parse(Time));
                    shipment.setShipmentStatus(Status.valueOf(status));
                    availableShipmentsMap.put(id,shipment);
                    shipmentsMap.put(id, shipment);
                }

            }

        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry7");
        }
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
            if (shipment.getDriver() != null)
                driverID = shipment.getDriver().getID();
            else{driverID = null;}
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
            System.out.println("i have a problem sorry8");
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
            System.out.println("i have a problem sorry9");
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
            System.out.println("i have a problem sorry10");
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
            System.out.println("i have a problem sorry11");
        }
    }
    public void deleteShipment(String ID)
    {
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate("DELETE FROM Shipments WHERE ID == '"+ID+"'");
            stat.executeUpdate("DELETE FROM ShipmentBranches WHERE ShipmentID == '"+ID+"'");
        }
        catch (SQLException e)
        {
            System.out.println("i have a problem sorry12");
        }

    }

}
