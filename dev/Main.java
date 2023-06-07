import HR.Presentation.CashRegisterUI;
import HR.Presentation.HRManagerUI;
import HR.Presentation.StoreManagerUI;
import HR.Presentation.WorkerUI;
import HR.PresentationGUI.HRManager;
import Shipment.Presentation.UI;

public class Main {
    public static void main(String[] args) {

        if( args.length!=2){
            System.out.println("Wrong parameters");
        }
        //we get the args that we want to start by:
        String mode = args[0];
        //we get the role that is coming in
        String role = args[1];

        if(mode.equals("CLI"))
        {
            //    calls the GeneralUI object starting function
            GeneralUI.StartMe(role);
        }
        else if(mode.equals("GUI"))
        {
            // todo   calls the GUI object starting function
            if (role.equals("HRManager")){
                HRManager a=new HRManager();
            }

        }
        else{
            System.out.println("Wrong mode");
            System.exit(0);
        }

    }
}
