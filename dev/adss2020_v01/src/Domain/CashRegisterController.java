package Domain;

//this will be singlton
public class CashRegisterController {
    private static CashRegisterController instance;
    private CashRegister cashRegister;
    private GeneralController generalController = GeneralController.getInstance();

    private CashRegisterController() {
        cashRegister = new CashRegister();
    }
    public static CashRegisterController getInstance() {
        if (instance == null) {
            instance = new CashRegisterController();
        }
        return instance;
    }
    //add a cash cancellations
    public void AddCancellations(String Name, String item, double amount, String ID) {
        //create the cancallation
        Cancellations cancel = new Cancellations(amount, item, ID, GeneralController.Workers.get(ID).GetName());
        //add the cancellation to the super
        GeneralController.Superim.get(Name).get_cash_register().AddCancalation(cancel);
    }
    //print Domain.Cancellations of a specific date in a branch
    public void PrintCancellation(String Name, int year, int month, int day) {
        GeneralController.Superim.get(Name).get_cash_register().PrintCancellation(year, month, day);
    }

    /**
     * the function checks if worker is a shift manager and can make a cancellations
     * @param ID - the Id of the worker
     * @return true/false
     */
    public boolean CheckWorkerCanCancel(String ID){return GeneralController.CanDoJob(ID,Jobs.ShiftManager);}

}
