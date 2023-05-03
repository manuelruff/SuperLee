package HR.Bussiness;

//this will be singletone

import HR.DataAccess.DataController;
import HR.DataAccess.WorkerMapper;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;

/**
 * the class WorkerController hold all the functions which an employee can do
 */
public class WorkerController  {
    private static WorkerController instance ;
    public Map<String, Worker> Workers;
    public Map<String, Driver> Drivers;
    private WorkerMapper workerMapper;
    private DataController dataController;

    private WorkerController() {
        workerMapper=WorkerMapper.getInstance();
        dataController=DataController.getInstance();
        Workers= workerMapper.getWorkerMap();
        Drivers=workerMapper.getDriverMap();
    }
    public static WorkerController getInstance() {
        if(instance == null)
            instance = new WorkerController();
        return instance;
    }
    //check if the password is correct by given ID
    public boolean IsTruePassword(String ID, String password) {
        if (!isExistWorker(ID)) {
            return false;
        }
        return Workers.get(ID).CheckPassword(password);
    }
    // add Domain.Constraints to worker by given Id
    public boolean AddConstraints(String ID, int day, double s_hour, double e_hour, String r) {
        return Workers.get(ID).AddCantWork(Days.values()[day - 1], s_hour, e_hour, r);
    }
    // remove Constraintss for worker by id
    public boolean RemoveConstraints(String ID, int day, double s_hour, double e_hour) {
        //we will tell the db to delete his constraints so it will be re written later
        dataController.DeleteConstraint(ID);
        return Workers.get(ID).RemoveCantWork(Days.values()[day - 1], s_hour, e_hour);
    }
    //changes a worker password
    public void ChangeWorkerPassword(String ID, String newPassword) {
        Workers.get(ID).SetPassword(newPassword);
    }
    //changes a worker name
    public void ChangeWorkerName(String ID, String newName) {
        Workers.get(ID).SetName(newName);
    }
    //changes a worker bank info
    public void ChangeWorkerBank(String ID, int newBank) {
        Workers.get(ID).SetBank(newBank);
    }
    //prints constraints of worker
    public void ShowConstraints(String ID) {
        Workers.get(ID).ShowConstraints();
    }
    // new added to connect between controllers functions - manu will check if its good or yell at me :(
    public Worker GetWorkerByID(String ID) {
        return Workers.get(ID);
    }
    // im not sure this one should be here
    public boolean isExistWorker(String ID){
        //we tell the database to load that id if exists before we check him
        dataController.getWorker(ID);
        //check if the worker is not a worker or a driver
        return Workers.get(ID) != null && Drivers.get(ID) == null;
    }
    public boolean ShowWorkerShifts(String BranchName){
        return ManagerController.getInstance().PrintWeekly(BranchName);
    }
    public boolean ShowDriverShifts(String ID){
        return Drivers.get(ID).PrintDaysShift();
    }
    public boolean CanDoJob(String ID, Jobs job) {
        return Workers.get(ID).CanDoJob(job);
    }
    // check if worker is a driver
    public boolean IsDriver(String ID){
        return Drivers.get(ID)!=null;
    }
    
    
}
