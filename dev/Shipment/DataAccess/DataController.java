package Shipment.DataAccess;

import Shipment.Bussiness.*;

import java.util.Map;

// singleton
public class DataController {
    private static DataController instance;
    private OrderMapper orderMapper;
    private ShipmentMapper shipmentMapper;
    private TruckMapper truckMapper;
    private VendorMapper vendorMapper;


    private DataController(){
        orderMapper = OrderMapper.getInstance();
        shipmentMapper = ShipmentMapper.getInstance();
        truckMapper = TruckMapper.getInstance();
        vendorMapper = VendorMapper.getInstance();
    }

    public static DataController getInstance(){
        if (instance == null){
            instance = new DataController();
        }
        return instance;
    }

    public Map<String, Order> getOrderMap() {
        return orderMapper.getOrderMap();
    }

    public Vendor getVendor(String name){
        return vendorMapper.getVendor(name);
    }

    /**************************************** Shipment Related Methods ****************************************/

    public Map<String, Shipment> getShipmentsMap(){return shipmentMapper.getShipmentsMap();}
    public Map<String,Shipment> getAvailableShipments(){return shipmentMapper.getAvailableShipmentsMap();}
    public void loadAllShipments(){}
    public Shipment getShipment(String shipmentID){return shipmentMapper.getShipment(shipmentID);}





    /**************************************** Trucks Related Methods ****************************************/

    public Truck getTruck(String truckNumber){
        return truckMapper.getTruck(truckNumber);
    }
    public Map<String, Truck> getTrucksMap(){return truckMapper.getTruckMap();}

    public void deleteTruck(String truckNumber) {
    }
    public void loadAllTrucks(){

    }
}
