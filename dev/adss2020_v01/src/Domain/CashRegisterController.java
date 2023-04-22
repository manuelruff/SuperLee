package Domain;

//this will be singlton
public class CashRegisterController {
    private static CashRegisterController instance;
    private CashRegister cashRegister;

    private CashRegisterController() {
        cashRegister = new CashRegister();
    }
    public static CashRegisterController getInstance() {
        if (instance == null) {
            instance = new CashRegisterController();
        }
        return instance;
    }
    


}
