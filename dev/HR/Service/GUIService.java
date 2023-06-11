package HR.Service;

import HR.Bussiness.ServiceController;

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


}
