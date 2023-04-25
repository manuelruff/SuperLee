package DataAccess;

import Domain.Worker;
import junit.framework.Test;

//this will be singleton
public class DataController {
    private static DataController instance=new DataController();;
    private DataController() {
    }
    public static DataController getInstance() {
        return instance;
    }
    /**
     * @param Name a branch name
     * this function will tell the mapper of workers to get all the workers of a super
     */
    public static void loadAllWorkersFromSuper(String Name){
        WorkerMapper.ReadAllWorkersFromSuper(Name);
    }
    /**
     * this function will tell the mapper of workers to get all the workers
     */
    public static void loadAllWorkersFrom(){
        WorkerMapper.ReadAllWorkers();
    }
    /**
     * @param ID id of worker
     * @return the worker asked if he is in the db
     */
    public static Worker getWorker(String ID){
        return WorkerMapper.getWorker(ID);
    }
    public static void DeleteConstraint(String ID){
        WorkerMapper.DeleteConstraints(ID);
    }
    public static void DeleteWorkingDays(String ID){
        WorkerMapper.DeleteWorkingDays(ID);
    }






    /**
     * we will save all the changes when we go out of the system
     *
     */
    public static void saveData() {
        WorkerMapper.WriteAllWorkers();
        //close the connection to database when finished
        Connect.disconnect();
    }


}
