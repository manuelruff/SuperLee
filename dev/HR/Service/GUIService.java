package HR.Service;
import HR.Bussiness.ServiceController;
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
        return serviceController.getShift(name,day);
    }
    //get info on shifts from weekly in history
    public List<List<String>> getWeeklyFromHist(String name, int year, int month, int day, int dayInWeek) {
        return serviceController.getWeeklyFromHist(name,year,month,day,dayInWeek);
    }
    //get info on worker for him to update
    public List<String>getWorkerInfo(String id) {
        return serviceController.getWorkerInfo(id);
    }
    //get info on worker cant work days
    public List<String> getWorkerCantWorkDays(String id) {
        return serviceController.getWorkerCantWorkDays(id);
    }
}
