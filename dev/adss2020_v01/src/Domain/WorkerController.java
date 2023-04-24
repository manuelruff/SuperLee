package Domain;

//this will be singletone

import DataAccess.WorkerMapper;

/**
 * the class WorkerController hold all the functions which an employee can do
 */
public class WorkerController {
    private static WorkerController instance = new WorkerController();;
    private GeneralController generalController;
    private WorkerController() {
    }
    public static WorkerController getInstance() {
        return instance;
    }

    //check if the password is correct by given ID
    public static boolean IsTruePassword(String ID, String password) {
        if (!GeneralController.isExistWorker(ID)) {
            return false;
        }
        return GeneralController.Workers.get(ID).CheckPassword(password);
    }
    // add Domain.Constraints to worker by given Id
    public static boolean AddConstraints(String ID, int day, double s_hour, double e_hour, String r) {
        return GeneralController.Workers.get(ID).AddCantWork(Days.values()[day - 1], s_hour, e_hour, r);
    }

    // remove Constraintss for worker by id
    public static boolean RemoveConstraints(String ID, int day, double s_hour, double e_hour) {
        return GeneralController.Workers.get(ID).RemoveCantWork(Days.values()[day - 1], s_hour, e_hour);
    }
    //changes a worker password
    public static void ChangeWorkerPassword(String ID, String newPassword) {
        GeneralController.Workers.get(ID).SetPassword(newPassword);
    }
    //changes a worker name
    public static void ChangeWorkerName(String ID, String newName) {
        GeneralController.Workers.get(ID).SetName(newName);
    }
    //changes a worker bank info
    public static void ChangeWorkerBank(String ID, int newBank) {
        GeneralController.Workers.get(ID).SetBank(newBank);
    }
    //prints constraints of worker
    public static void ShowConstraints(String ID) {
        GeneralController.Workers.get(ID).ShowConstraints();
    }

    // new added to connect between controllers functions - manu will check if its good or yell at me :(
    public static Worker GetWorkerByID(String ID) {
        return GeneralController.GetWorkerByID(ID);
    }

    // im not sure this one should be here
    public static boolean isExistWorker(String ID){return GeneralController.isExistWorker(ID);}

//    public static void UpdateWorker(String ID){
//        WorkerMapper.UpdateWorker(ID);
//    }
}
