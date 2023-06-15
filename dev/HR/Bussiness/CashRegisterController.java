package HR.Bussiness;
import HR.DataAccess.DataController;
import java.util.Map;

//this will be singlton
public class CashRegisterController  {
    private static CashRegisterController instance;
    private Map<String, Super> Superim;
    //all the Workers in the company
    private Map<String, Worker> Workers;
    private DataController dataController;
    private CashRegisterController() {
        dataController=DataController.getInstance();
        Superim = dataController.getSuperMap();
        Workers= dataController.getWorkerMap();
    }
    public static CashRegisterController getInstance() {
        if(instance==null){
            instance=new CashRegisterController();
        }
        return instance;
    }
    //add a cash cancellations
    public void AddCancellations(String BranchName, String item, double amount, String ID) {
        //create the cancallation
        Cancellations cancel = new Cancellations(amount, item, ID, Workers.get(ID).getName());
        //add the cancellation to the super
        Superim.get(BranchName).get_cash_register().AddCancalation(cancel);
    }
    //print Domain.Cancellations of a specific date in a branch
    public void PrintCancellation(String Name, int year, int month, int day) {
        dataController.ReadCancellations(Name,year,month,day);
        Superim.get(Name).get_cash_register().PrintCancellation(Name,year, month, day);
    }

    /**
     * the function checks if worker is a shift manager and can make a cancellations
     * @param ID - the Id of the worker
     * @return true/false
     */
    public boolean CheckWorkerCanCancel(String ID){
        if(Workers.get(ID)!=null){
        return Workers.get(ID).CanDoJob(Jobs.ShiftManager);
        }
        return false;
    }
    public boolean CheckWorkerPassword(String ID, String password){
        return Workers.get(ID).CheckPassword(password);
    }
}
