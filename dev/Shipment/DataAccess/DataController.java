package Shipment.DataAccess;

import Shipment.Bussiness.*;
import resource.Connect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// singleton
public class DataController {
    private static DataController instance;
    private OrderMapper orderMapper;
    private ShipmentMapper shipmentMapper;
    private TruckMapper truckMapper;
    private VendorMapper vendorMapper;
    private SManagerPasswordMapper sManagerPasswordMapper;


    private DataController(){
        sManagerPasswordMapper = SManagerPasswordMapper.getInstance();
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

    /**************************************** Order Related Methods ****************************************/

    public Map<String, Order> getOrderMap() {
        return orderMapper.getOrderMap();
    }

    public void loadOrdersByVendor(String vendorName){orderMapper.readOrderWithVendor(vendorName);}



    /**************************************** Vendor Related Methods ****************************************/

    public Vendor getVendor(String name){
        return vendorMapper.getVendor(name);
    }

    public Map<String, Vendor> getVendorMap(){return vendorMapper.getVendorMap();}
    public Map<String, List<Order>> getVendorOrderMap(){return vendorMapper.getVendorsOrderMap();}

    public void loadAllVendors(){vendorMapper.readAllVendors();}




    /**************************************** Shipment Related Methods ****************************************/

    public Map<String, Shipment> getShipmentsMap(){return shipmentMapper.getShipmentsMap();}
    public Map<String,Shipment> getAvailableShipments(){return shipmentMapper.getAvailableShipmentsMap();}
    public void loadAllShipments(){}
    public Shipment getShipment(String shipmentID){return shipmentMapper.getShipment(shipmentID);}

    public List<Shipment> getAvailableShipmentsIntoList() {
        return new ArrayList<>(getAvailableShipments().values());
    }



    /**************************************** Trucks Related Methods ****************************************/

    public Truck getTruck(String truckNumber){
        return truckMapper.getTruck(truckNumber);
    }
    public Map<String, Truck> getTrucksMap(){return truckMapper.getTruckMap();}

    public void deleteTruck(String truckNumber) {
    }
    public void loadAllTrucks(){
        truckMapper.readAllTrucks();
    }


    /**************************************** Manager Passwords ****************************************/
    public String getShipmentManagerPassword() {
        return sManagerPasswordMapper.getSManagerPassword();
    }
    public void setShipmentManagerPassword(String Password) {
        sManagerPasswordMapper.setSManagerPassword(Password);
    }





    public void closeShipmentsDB(){
        vendorMapper.writeAllVendors();
        shipmentMapper.writeAllShipments();
        orderMapper.writeAllOrders();
        truckMapper.writeAllTrucks();
        orderMapper.writeStaticSave();
        shipmentMapper.writeItemDocsCounter();
        Connect.disconnect();
    }

    public void loadSaves() {
        orderMapper.readStaticSave();
        shipmentMapper.getItemDocsCounter();
    }
}
