package HR.PresentationGUI.WorkerWindows;

import HR.Bussiness.WorkerController;
import HR.PresentationGUI.WorkerGUI;
import HR.Service.GUIService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//todo continue here!
public class editConstraints extends JFrame implements ActionListener {
    private JPanel editConstraintsWin;
    private JButton addButton;
    private JButton backButton;
    private JButton removeButton;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;

    //save the preview window to open in the end
    private WorkerGUI save;

    // take instance for worker controller
    private WorkerController workerController;
    //take the instance of gui service so we get what we want
    private GUIService guiService;
    public editConstraints(WorkerGUI save) {
        this.save=save;
        this.workerController = WorkerController.getInstance();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void createUIComponents() {
        //get the instance when we update the panel
        guiService=GUIService.getInstance();
        // TODO: place custom component creation code here
    }
}
