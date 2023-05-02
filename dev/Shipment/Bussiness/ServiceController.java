package Shipment.Bussiness;

// todo will be singleton in the end
public class ServiceController {
    private static  ServiceController instance;

    public static ServiceController getInstance() {
        if (instance == null)
            instance = new ServiceController();
        return instance;
    }
    private ServiceController(){
        return;
    }
}
