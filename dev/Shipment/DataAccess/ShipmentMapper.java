package Shipment.DataAccess;

import Shipment.Bussiness.Shipment;
import resource.Connect;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShipmentMapper {
    private static  ShipmentMapper instance = new ShipmentMapper();
    private static Map<String, Shipment> availableShipmentsMap;
    private static Map<String, Shipment> shipmentsMap;
    private static Connection conn;

    private ShipmentMapper()
    {
        availableShipmentsMap = new HashMap<>();
        shipmentsMap = new HashMap<>();
        conn = Connect.getConnection();
    }

    private static ShipmentMapper getInstance(){return instance;}

    public static List<Shipment> getShipments()
    {
        return null;
    }
    public static List<Shipment> getAvailableShipments()
    {
        return null;
    }

    private static void readShipment(String ID)
    {

    }

    private static void readAvailableShipment(String ID)
    {

    }

    private static void
 }
