package Shipment.DataAccess;


import Shipment.Bussiness.Vendor;

import resource.Connect;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class VendorMapper {

    private static VendorMapper instance;
    private Map<String, Vendor> vendors;
    private Connection conn;
    private VendorMapper() {
        conn = Connect.getConnection();
        vendors = new HashMap<>();
    }

    public static VendorMapper getInstance() {
        if (instance == null) {
            instance = new VendorMapper();
        }
        return instance;
    }

    /**
     * This function getting a vendor from the map identity or the dataBase.
     * @param name String, name of the vendor.
     * @return the Vendor.
     */
    public Vendor getVendor(String name) {
        if (!vendors.containsKey(name)){
            readVendor(name);
        }
        return vendors.get(name);
    }


    /**
     * This function reads a vendor from the database according to the unique name it has and creating a vendor object
     * out of it. the function access the Vendors table.
     * @param name String, name of the Vendor to read.
     */
    public void readVendor(String name) {
        String contactName, address, phoneNumber;
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
            }
        }
        catch (SQLException e)
        {
            System.out.println("Can't read this vendor: " + name);

        }
    }

    /**
     * This function writing every Vendor back to the dataBase.
     */
    public void writeAllVendors(){
        for(Vendor vendor : vendors.values()){
            String name, address, phoneNumber, contactName;
            try{
                java.sql.Statement stmt = conn.createStatement();
                name = vendor.getName();
                address = vendor.getAddress();
                phoneNumber = vendor.getPhoneNumber();
                contactName = vendor.getContactName();
                stmt.executeUpdate("INSERT OR IGNORE INTO Vendors (name, Address, PhoneNumber, ContactName) VALUES ('" + name + "', '" + address + "', '" + phoneNumber + "', '" + contactName + "')");
            } catch (SQLException e) {
                System.out.println("can't write this Vendor");
            }
        }
    }

    public Map<String, Vendor> getVendors() {
        return vendors;
    }
}

