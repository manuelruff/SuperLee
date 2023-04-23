package Domain;

//this will be singletone
/**
 * the class WorkerController hold all the functions which an employee can do
 */
public class WorkerController {
    private static WorkerController instance;
    private GeneralController generalController = GeneralController.getInstance();
    private WorkerController() {
    }
    public static WorkerController getInstance() {
        if (instance == null) {
            instance = new WorkerController();
        }
        return instance;
    }

    //check if the password is correct by given ID
    public boolean IsTruePassword(String ID, String password) {
        if (!GeneralController.isExistWorker(ID)) {
            return false;
        }
        return GeneralController.Workers.get(ID).CheckPassword(password);
    }
    // add Domain.Constraints to worker by given Id
    public boolean AddConstraints(String ID, int day, double s_hour, double e_hour, String r) {
        return GeneralController.Workers.get(ID).AddCantWork(Days.values()[day - 1], s_hour, e_hour, r);
    }

    // remove Constraintss for worker by id
    public boolean RemoveConstraints(String ID, int day, double s_hour, double e_hour) {
        return GeneralController.Workers.get(ID).RemoveCantWork(Days.values()[day - 1], s_hour, e_hour);
    }
    //changes a worker password
    public void ChangeWorkerPassword(String ID, String newPassword) {
        GeneralController.Workers.get(ID).SetPassword(newPassword);
    }
    //changes a worker name
    public void ChangeWorkerName(String ID, String newName) {
        GeneralController.Workers.get(ID).SetName(newName);
    }
    //changes a worker bank info
    public void ChangeWorkerBank(String ID, int newBank) {
        GeneralController.Workers.get(ID).SetBank(newBank);
    }
    //prints constraints of worker
    public void ShowConstraints(String ID) {
        GeneralController.Workers.get(ID).ShowConstraints();
    }

}
