import HR.PresentationGUI.HRManager;
import HR.PresentationGUI.StoreManager;
import HR.PresentationGUI.WorkerGUI;
import Shipment.PresentationGUI.ShipManager;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {

        if( args.length!=2){
            System.out.println("Wrong parameters");
        }
        //its just the main one so we will open the store manager option
        if(args.length==1){
            //we get the args that we want to start by:
            String mode = args[0];
            if(mode.equals("SuperLiMainGUI ")){
                new StoreManager();
            }
            else if(mode.equals("SuperLiMainCLI ")){
                GeneralUI.StartMe("StoreManager");
            }
            else{
                JOptionPane.showMessageDialog(null,"Wrong parameters");
                System.exit(0);
            }
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
            if (role.equals("HRManager")){
                new HRManager();
            }
            else if (role.equals("ShipManager")){
                new ShipManager();
            }
            else if (role.equals("Worker")){
                new WorkerGUI();
            }
            else if(role.equals("StoreManager")){
                new StoreManager();
            }
            else{
                JOptionPane.showMessageDialog(null,"Wrong role");
                System.exit(0);
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Wrong parameters");
            System.exit(0);
        }

    }
}
