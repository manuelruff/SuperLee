import HR.PresentationGUI.HRManager;
import HR.PresentationGUI.WorkerGUI;
import Shipment.PresentationGUI.ShipManager;
import javax.swing.JOptionPane;

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
                new HRManager();
            }
            else if (role.equals("ShipManager")){
                new ShipManager();
            }
            else if (role.equals("Worker")){
                new WorkerGUI();
            }
            //shipment manager
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
