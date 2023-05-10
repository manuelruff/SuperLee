package HR.Service;
import HR.Bussiness.ServiceController;

import java.util.List;

public class ShipmentService {
    private ServiceController serviceController;
    private static ShipmentService instance;

    private ShipmentService(){
        serviceController = ServiceController.getInstance();
    }
    public static ShipmentService getInstance(){
        if(instance==null) {
            instance = new ShipmentService();
        }
        return instance;
    }

    public List<String> askForDriver(char licence, int training,int day,List<String> branches){
        //check that all the branches has weekly if one doesnt have we return null
        if(!serviceController.checkHasWeekly(branches)){
            return null;
        }
        //todo check we have employees for each super
        // check if all the branches has storekeeper for the day if one doesnt have we cant have the shipment
        if(!serviceController.checkStoreKeeper(branches,day)){
            return null;
        }
        //go get a suitable driver if we have one
        return serviceController.getDriver(licence,training,day);
    }

    public List<String> askForDriver(String ID){
        //todo add the right function to the service controler
    	return serviceController.getDriver(ID);
    }
    public List<String> askForSite(String branchName){
        //todo gtSite in service controler
        return null;
    }
    public boolean checkASite(String branchName){
        //todo checkASite in service controler
        return false;
    }
    public void askRemoveDayForDriver(String ID,int day){
    }
    public void getUpdateForDriver(char licence, int training){
    }

    //if a site wont get something in the day and we want to update hr manager so he can remove storekeeper
    public void getUpdateForSite(String branchName, int day){
    }

}
