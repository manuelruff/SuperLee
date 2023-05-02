package HR.Bussiness;

//this will be singletone

import HR.DataAccess.DataController;
import HR.DataAccess.WorkerMapper;

import java.util.Map;

/**
 * the class WorkerController hold all the functions which an employee can do
 */
public class WorkerController  {
    private static WorkerController instance = new WorkerController();;

    public static Map<String, Worker> Workers;
    public static Map<String, Driver> Drivers;

    private WorkerController() {
        Workers= WorkerMapper.getWorkerMap();
        Drivers=WorkerMapper.getDriverMap();

    }
    public static WorkerController getInstance() {
        return instance;
    }
    //check if the password is correct by given ID
    public static boolean IsTruePassword(String ID, String password) {
        if (!isExistWorker(ID)) {
            return false;
        }
        return Workers.get(ID).CheckPassword(password);
    }
    // add Domain.Constraints to worker by given Id
    public static boolean AddConstraints(String ID, int day, double s_hour, double e_hour, String r) {
        return Workers.get(ID).AddCantWork(Days.values()[day - 1], s_hour, e_hour, r);
    }
    // remove Constraintss for worker by id
    public static boolean RemoveConstraints(String ID, int day, double s_hour, double e_hour) {
        //we will tell the db to delete his constraints so it will be re written later
        DataController.DeleteConstraint(ID);
        return Workers.get(ID).RemoveCantWork(Days.values()[day - 1], s_hour, e_hour);
    }
    //changes a worker password
    public static void ChangeWorkerPassword(String ID, String newPassword) {
        Workers.get(ID).SetPassword(newPassword);
    }
    //changes a worker name
    public static void ChangeWorkerName(String ID, String newName) {
        Workers.get(ID).SetName(newName);
    }
    //changes a worker bank info
    public static void ChangeWorkerBank(String ID, int newBank) {
        Workers.get(ID).SetBank(newBank);
    }
    //prints constraints of worker
    public static void ShowConstraints(String ID) {
        Workers.get(ID).ShowConstraints();
    }
    // new added to connect between controllers functions - manu will check if its good or yell at me :(
    public static Worker GetWorkerByID(String ID) {
        return Workers.get(ID);
    }
    // im not sure this one should be here
    public static boolean isExistWorker(String ID){
        //we tell the database to load that id if exists before we check him
        DataController.getWorker(ID);
        //check if the worker is not a worker or a driver
        return Workers.get(ID) != null && Drivers.get(ID) == null;
    }
    public static boolean ShowShiftFromBranch(String BranchName){
        return ManagerController.PrintWeekly(BranchName);
    }
    public static boolean CanDoJob(String ID, Jobs job) {
        return Workers.get(ID).CanDoJob(job);
    }
    
    
}
