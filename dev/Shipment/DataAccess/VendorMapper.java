package Shipment.DataAccess;


import Shipment.Bussiness.Order;
import Shipment.Bussiness.Vendor;

import resource.Connect;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendorMapper {

    private static VendorMapper instance;
    private Map<String, Vendor> vendors;
    private Map<String, List<Order>> vendorsOrderMap;
    private Connection conn;

    private VendorMapper() {
        conn = Connect.getConnection();
        vendors = new HashMap<>();
        vendorsOrderMap = new HashMap<>();
    }

    public static VendorMapper getInstance() {
        if (instance == null) {
            instance = new VendorMapper();
        }
        return instance;
    }

    /**
     * This function getting a vendor from the map identity or the dataBase.
     *
     * @param name String, name of the vendor.
     * @return the Vendor.
     */
    public Vendor getVendor(String name) {
        if (!vendors.containsKey(name)) {
            readVendor(name);
        }
        return vendors.get(name);
    }


    public Map<String, Vendor> getVendorMap() {
        return vendors;
    }

    public Map<String, List<Order>> getVendorsOrderMap(){return vendorsOrderMap;}

    /**
     * This function reads a vendor from the database according to the unique name it has and creating a vendor object
     * out of it. the function access the Vendors table.
     *
     * @param name String, name of the Vendor to read.
     */
    public void readVendor(String name) {
        String contactName, address, phoneNumber;
        List<Order> orderList = new ArrayList<>();
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Vendors WHERE NAME = '" + name + "'");
            if (rs.next()) {
                name = rs.getString("Name");
                phoneNumber = rs.getString("PhoneNumber");
                contactName = rs.getString("ContactName");
                address = rs.getString("Address");
                Vendor vendor = new Vendor(name, address, phoneNumber, contactName);
                vendors.put(name, vendor);
                vendorsOrderMap.put(name, orderList);
            }
        } catch (SQLException e) {
            System.out.println("Can't read this vendor: " + name);
        }
    }

    /**
     * This function writing every Vendor back to the dataBase.
     */
    public void writeAllVendors() {
        for (Vendor vendor : vendors.values()) {
            String name, address, phoneNumber, contactName;
            try (Statement stmt = conn.createStatement()) {
                name = vendor.getName();
                address = vendor.getAddress();
                phoneNumber = vendor.getPhoneNumber();
                contactName = vendor.getContactName();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Vendors WHERE Name ='" + name + "'");
                if (!rs.next()) {
                    stmt.executeUpdate("INSERT OR IGNORE INTO Vendors (name, Address, PhoneNumber, ContactName) VALUES ('" + name + "', '" + address + "', '" + phoneNumber + "', '" + contactName + "')");
                } else {
                    stmt.executeUpdate("UPDATE Vendors SET ContactName = '" + contactName + "', PhoneNumber = '" + phoneNumber + "', Address = '" + address + "' WHERE Name = '" + name + "'");
                }
            } catch (SQLException e) {
                System.out.println("Can't write this Vendor");
            }
        }
    }


    public void readAllVendors() {
        String vendorName, contactName, address, phoneNumber;
        List<Order> orderList;
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Vendors");
            while (rs.next()) {
                vendorName = rs.getString("Name");
                phoneNumber = rs.getString("PhoneNumber");
                contactName = rs.getString("ContactName");
                address = rs.getString("Address");
                if (vendors.containsKey(vendorName))
                    continue;
                orderList = new ArrayList<>();
                Vendor vendor = new Vendor(vendorName, address, phoneNumber, contactName);
                vendors.put(vendorName, vendor);
                vendorsOrderMap.put(vendorName, orderList);
            }
        } catch (SQLException e) {
            System.out.println("Can't read this vendors");
        }
    }
}