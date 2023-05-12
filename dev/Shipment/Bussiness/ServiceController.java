package Shipment.Bussiness;

import Shipment.DataAccess.DataController;

import java.util.Map;

// todo will be singleton in the end
public class ServiceController {
    private static  ServiceController instance;
    private DataController dataController;
    private Map<String,Shipment> shipments;


    public static ServiceController getInstance() {
        if (instance == null)
            instance = new ServiceController();
        return instance;
    }
    private ServiceController(){
        dataController = DataController.getInstance();
        shipments = dataController.getShipmentsMap();
    }

}
