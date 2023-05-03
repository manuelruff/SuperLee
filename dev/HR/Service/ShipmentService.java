package HR.Service;
import HR.Bussiness.ServiceControler;

import java.util.List;

public class ShipmentService {
    private static ServiceControler serviceControler;

    public ShipmentService(){
    	serviceControler = ServiceControler.getInstance();
    }

    public List<String> askForDriver(char licence, int training,int day){

        return null;
    }
    public List<String> askForSite(String branchName){
        return null;
    }
    public void askRemoveDayForDriver(String ID,int day){
    }
    public void getUpdateForDriver(char licence, int training){
    }

    //if a site wont get something in the day and we want to update hr manager so he can remove storekeeper
    public void getUpdateForSite(String branchName, int day){
    }

}
