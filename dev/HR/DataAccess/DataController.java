package HR.DataAccess;
import HR.Bussiness.*;
import resource.Connect;

//this will be singleton
public class DataController {
    private static DataController instance;
    private WorkerMapper workerMapper;
    private WeeklyMapper weeklyMapper;
    private SuperMapper superMapper;
    private CashRegisterMapper cashRegisterMapper;
    private DataController() {
        cashRegisterMapper=CashRegisterMapper.getInstance();
        superMapper=SuperMapper.getInstance();
        workerMapper=WorkerMapper.getInstance();
        weeklyMapper=WeeklyMapper.getInstance();
    }
    public static DataController getInstance() {
        if (instance == null)
            instance = new DataController();
        return instance;
    }
    /**
     * @param Name a branch name
     * this function will tell the mapper of workers to get all the workers of a super
     */
    public void loadAllWorkersFromSuper(String Name){
        workerMapper.ReadAllWorkersFromSuper(Name);
    }
    /**
     * this function will tell the mapper of workers to get all the workers
     */
    public void loadAllWorkersFrom(){
        workerMapper.ReadAllWorkers();
    }
    /**
     * @param ID id of worker
     * @return the worker asked if he is in the db
     */
    public Worker getWorker(String ID){
        return workerMapper.getWorker(ID);
    }

    public void DeleteConstraint(String ID){
        workerMapper.DeleteConstraints(ID);
    }
    public void DeleteWorkingDays(String ID, Shift shift){
        weeklyMapper.DeleteWorkerFromShift(ID,shift);
        workerMapper.DeleteWorkingDays(ID);
    }



    //added 26.4
    public Super getSuper(String name){
        return superMapper.getsuper(name);
    }
    public void LoadCancellations(String BranchName,int year,int month,int day){
        //load the cancelations
        cashRegisterMapper.ReadCancellations( BranchName, year, month, day);
    }


    /**
     * we will save all the changes when we go out of the system
     *
     */
    public void saveData() {

        //todo add writing for the drivers shifts

        workerMapper.WriteAllWorkers();
        workerMapper.WriteAllDrivers();
        cashRegisterMapper.WriteAllCancellations();
        superMapper.WriteAllSupers();
        //write all weekly to db
        weeklyMapper.WriteAllWeekly();
        //close the connection to database when finished
        Connect.disconnect();
    }


}
