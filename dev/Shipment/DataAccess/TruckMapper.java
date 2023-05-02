package Shipment.DataAccess;

import Shipment.Bussiness.Truck;
import resource.Connect;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
public class TruckMapper {
    private static TruckMapper instance = new TruckMapper();
    private static Map<String, Truck> truckMap;
    private static Connection connect;
    private TruckMapper()
    {
        truckMap = new HashMap<>();
        connect = Connect.getConnection();
    }
    public static TruckMapper getInstance()
    {
        return instance;
    }
    public static Truck getTruck(String ID)
    {
        if(truckMap.get(ID) == null){
//            readTruck(ID);
        }
        return truckMap.get(ID);
    }
    private static void readTruck(String ID)
    {

    }
    private static void readWorkingDays(String ID)
    {

    }
    public static void writeAllTrucks()
    {

    }
    private  static void writeWorkingDays(String ID)
    {

    }




}
