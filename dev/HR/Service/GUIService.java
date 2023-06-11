package HR.Service;

import HR.Bussiness.ServiceController;
import HR.Bussiness.Weekly;

import java.time.LocalDate;
import java.util.List;

//this will be singletone
public class GUIService {
    private static GUIService instance;
    private ServiceController serviceController;
    private GUIService() {
        serviceController = ServiceController.getInstance();
    }

    public static GUIService getInstance() {
        if (instance == null) {
            instance = new GUIService();
        }
        return instance;
    }
    //get info on drivers and their shifts
    public List<List<String>>getDriversSchedule(){
        List<List<String>>ret = serviceController.getDriversSchedule();
        return ret;
    }
    //get info on shifts from this weekly
    public List<List<String>> getShift(String name,int day){
        List<List<String>>ret = serviceController.getShift(name,day);
        return ret;
    }
    //get info on shifts from weekly in history
    public boolean getWeeklyFromHist(String Name, int year, int month, int day) {

//        LocalDate date=LocalDate.of(year,month,day);
//        Weekly week=dataController.getWeekly(Name,date.toString());
//        if(week!=null){
//            week.PrintMe();
//            return true;
//        }
//        return false;
        //Superim.get(Name).PrintWeekFromHistByDate(year, month, day);

        return false;
    }

}
