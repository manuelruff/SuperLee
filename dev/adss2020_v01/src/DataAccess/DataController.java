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


    public static void loadAllWorkersFromSuper(String Name){
        WorkerMapper.ReadAllWorkersFromSuper(Name);
    }

    public static Worker getWorker(String ID){
        return WorkerMapper.getWorker(ID);
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
