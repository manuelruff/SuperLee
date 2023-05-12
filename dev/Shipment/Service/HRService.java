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
    public boolean isThereAShipment(int day, String siteName){
        return false;
    }
    public void askForShipments() {
    }

    // this fucntion will go over the available shipments and find drivers.
    public void findDriversForShipments(){

    }
    //update of a site removal
    public void removeSite(String name){

    }
    public void weeklyCreated(){

    }
}
