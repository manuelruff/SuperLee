package Shipment.Service;

import HR.Bussiness.ServiceController;

public class HRService {

    private ServiceController serviceController;
    private static HRService instance;

    private HRService(){
        serviceController = ServiceController.getInstance();
    }
    public static HRService getInstance(){
        if(instance==null) {
            instance = new HRService();
        }
        return instance;
    }

    public void askForShipments(int day) {
    }

    public void askForShipments(int day, String siteName) {

    }

    public void askForShipments(String siteName) {

    }
    public void askForDeleteShipment(int day, String siteName){

    }

    public void askForShipments() {
    }
}
