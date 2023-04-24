package DataAccess;

import junit.framework.Test;

//this will be singleton
public class DataController {
        private static DataController instance;
    private DataController() {
    }

    public static DataController getInstance() {
        if (instance == null) {
            instance = new DataController();
        }
        return instance;
    }

    /**
     * we will save all the changes when we go out of the system
     *
     */
    public void saveData() {
        WorkerMapper.WriteAllWorkers();

        //close the connection to database when finished
        Connect.disconnect();
    }


}
