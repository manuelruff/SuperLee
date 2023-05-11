package Shipment.DataAccess;

import Shipment.Bussiness.*;

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


    /****************************************** Getter From Mappers ******************************************/
    public Vendor getVendor(String name){
        return vendorMapper.getVendor(name);
    }

    public Truck getTruck(String truckNumber){
        return truckMapper.getTruck(truckNumber);
    }
}
