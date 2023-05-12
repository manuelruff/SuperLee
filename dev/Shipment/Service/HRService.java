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

    //print all the shipments in a specific day that will get to siteName
    public void askForShipments(int day, String siteName) {

    }
    //delete all shipments that are going to sitename in a specific day
    public void askForDeleteShipment(int day, String siteName){

    }
    //return true if there is a shipment for the day and siteName
    public boolean isThereAShipment(int day, String siteName){
        return false;
    }

    //print all the shipments in the system for hr manager
    public void askForShipments() {
    }

    // this function will go over the available shipments and find drivers.
    public void findDriversForShipments(){

    }
    //update of a site removal from HR module
    public void removeSite(String name){

    }
    //update that a weekly was created, now you need to check who created it and try to get drivers if possible
    public void weeklyCreated(){

    }
}
