package HR.Service;
import HR.Bussiness.ServiceController;

import java.time.LocalDate;
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
        //check we have employees for each super
        // check if all the branches has storekeeper for the day if one doesnt have we cant have the shipment
        if(!serviceController.checkStoreKeeper(branches,day)){
            return null;
        }
        //go get a suitable driver if we have one
        return serviceController.getDriver(licence,training,day);
    }

    public List<String> askForDriver(String ID){return serviceController.getDriver(ID);}

    public boolean checkWeekly(List<String> branches, LocalDate date){return serviceController.checkHasWeekly(branches,date);}
    //return a site by list of strings [String name, String address, String phoneNumber, String contactName, Zone zone]
    public List<String> askForSite(String branchName){
        return serviceController.getSite(branchName);
    }
    //check if a site name exists
    public boolean checkASite(String branchName){return serviceController.checkSite(branchName);}
    //we get a driver and a day we remove from his shifts
    public void askRemoveDayForDriver(String ID,int day){serviceController.RemoveShiftFromDriver(ID,day);}
    //we get updates for a driver, load if from db and write these updates
    public void getUpdateForDriver(String ID,char licence, int training){serviceController.UpdateDriver(ID,licence,training);}
    //we need to load all the drivers from the database and print them by printme of driver
    public void printAllDrivers(){serviceController.printDrivers();}
    //if a site wont get something in the day and we want to update hr manager so he can remove storekeeper
    public void getUpdateForSite(String branchName, int day){serviceController.UpdateSiteMessage(branchName,day);}
    //update site info when we get it from shipment
    public void getUpdateForSite(String branchName, String contactName, String contactNumber){serviceController.UpdateSiteContact(branchName,contactName,contactNumber);}
}
