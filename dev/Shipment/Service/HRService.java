package Shipment.Service;

import Shipment.Bussiness.Days;
import Shipment.Bussiness.ServiceController;

public class HRService {

    private Shipment.Bussiness.ServiceController serviceController;
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
        serviceController.printShipmentsDS(Days.values()[day],siteName);}


    //delete all shipments that are going to sitename in a specific day
    public void askForDeleteShipment(int day, String siteName){
        serviceController.deleteShipmentsDS(Days.values()[day],siteName);}


    //return true if there is a shipment for the day and siteName
    public boolean isThereAShipment(int day, String siteName){
        return serviceController.checkShipment(Days.values()[day],siteName);
    }

    //print all the shipments in the system for hr manager
    public void askForShipments() {
        serviceController.printAllShipments();
    }



    //update of a site removal from HR module
    public void removeSite(String name){
        serviceController.deleteOrdersBySite(name);
    }
    // this function will go over the available shipments and find drivers.
    public void weeklyCreated(){
        serviceController.findDriversForShipment();
    }
}
