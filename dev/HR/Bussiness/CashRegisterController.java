package HR.Bussiness;

import HR.DataAccess.SuperMapper;
import HR.DataAccess.WorkerMapper;

import java.util.Map;

//this will be singlton
public class CashRegisterController  {
    private static CashRegisterController instance=new CashRegisterController();;
    private CashRegister cashRegister;
    private static Map<String, Super> Superim;
    //all the Workers in the company
    private static Map<String, Worker> Workers;
    private CashRegisterController() {
        cashRegister = new CashRegister();
        Superim = SuperMapper.getSuperMap();
        Workers= WorkerMapper.getWorkerMap();
    }
    public static CashRegisterController getInstance() {
        return instance;
    }
    //add a cash cancellations
    public static void AddCancellations(String Name, String item, double amount, String ID) {
        //create the cancallation
        Cancellations cancel = new Cancellations(amount, item, ID, Workers.get(ID).getName());
        //add the cancellation to the super
        Superim.get(Name).get_cash_register().AddCancalation(cancel);
    }
    //print Domain.Cancellations of a specific date in a branch
    public static void PrintCancellation(String Name, int year, int month, int day) {
        Superim.get(Name).get_cash_register().PrintCancellation(Name,year, month, day);
    }

    /**
     * the function checks if worker is a shift manager and can make a cancellations
     * @param ID - the Id of the worker
     * @return true/false
     */
    public static boolean CheckWorkerCanCancel(String ID){return WorkerController.CanDoJob(ID,Jobs.ShiftManager);}
    public static boolean CheckWorkerPassword(String ID, String password){
        return WorkerController.IsTruePassword(ID, password);
    }
}
