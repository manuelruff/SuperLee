package HR.Bussiness;

import HR.DataAccess.SuperMapper;
import HR.DataAccess.WorkerMapper;

import java.util.Map;

//this will be singlton
public class CashRegisterController  {
    private static CashRegisterController instance;
    private Map<String, Super> Superim;
    //all the Workers in the company
    private Map<String, Worker> Workers;
    private WorkerController workerController;
    private SuperMapper superMapper;
    private WorkerMapper workerMapper;
    private CashRegisterController() {
        superMapper=SuperMapper.getInstance();
        workerMapper=WorkerMapper.getInstance();
        Superim = superMapper.getSuperMap();
        Workers= workerMapper.getWorkerMap();
        workerController=WorkerController.getInstance();
    }
    public static CashRegisterController getInstance() {
        if(instance==null){
            instance=new CashRegisterController();
        }
        return instance;
    }
    //add a cash cancellations
    public void AddCancellations(String Name, String item, double amount, String ID) {
        //create the cancallation
        Cancellations cancel = new Cancellations(amount, item, ID, Workers.get(ID).getName());
        //add the cancellation to the super
        Superim.get(Name).get_cash_register().AddCancalation(cancel);
    }
    //print Domain.Cancellations of a specific date in a branch
    public void PrintCancellation(String Name, int year, int month, int day) {
        Superim.get(Name).get_cash_register().PrintCancellation(Name,year, month, day);
    }

    /**
     * the function checks if worker is a shift manager and can make a cancellations
     * @param ID - the Id of the worker
     * @return true/false
     */
    public boolean CheckWorkerCanCancel(String ID){return workerController.CanDoJob(ID,Jobs.ShiftManager);}
    public boolean CheckWorkerPassword(String ID, String password){
        return workerController.IsTruePassword(ID, password);
    }
}
