package HR.Service;
import HR.Bussiness.ServiceControler;

import java.util.List;

public class ShipmentService {
    private static ServiceControler serviceControler;

    public ShipmentService(){
        serviceControler = ServiceControler.getInstance();
    }

    public List<String> askForDriver(char licence, int training,int day,List<String> branches){
        //check that all the branches has weekly if one doesnt have we return null
        if(!serviceControler.checkHasWeekly(branches)){
            return null;
        }
        //todo check we have employees for yeach super
        if(!serviceControler.checkStoreKeeper(branches,day)){

        }
        //go get a suitable driver if we have one
        return serviceControler.getDriver(licence,training,day);
    }

    public List<String> askForDriver(String ID){
        //todo add the right function to the service controler
    	return serviceControler.getDriver(ID);
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
