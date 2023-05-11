package Shipment.DataAccess;

import Shipment.Bussiness.Order;
import Shipment.Bussiness.Vendor;
import Shipment.Bussiness.Zone;
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
    public Vendor getVendors(String name) {
        if (!vendors.containsKey(name)){
            readVendor(name);
        }
        return vendors.get(name);
    }

    public void readVendor(String name) {
        String contactName, address, phoneNumber;
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Super WHERE NAME = '" + name + "'");
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
            System.out.println("i have a problem sorry");

        }
    }

}
