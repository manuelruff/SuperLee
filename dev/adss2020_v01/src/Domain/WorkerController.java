package Domain;

//this will be singletone
public class WorkerController {
    private static WorkerController instance;
    private WorkerController() {
    }
    public static WorkerController getInstance() {
        if (instance == null) {
            instance = new WorkerController();
        }
        return instance;
    }



}
