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
    public List<List<String>>getDriversSchedule(){
        List<List<String>>driversSchedule = serviceController.getDriversSchedule();
        return driversSchedule;
    }

}
