package HR.PresentationGUI;

import HR.Bussiness.WorkerController;
import HR.PresentationGUI.WorkerWindows.editConstraints;
import HR.PresentationGUI.WorkerWindows.updateDetails;
import HR.Service.GUIService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WorkerGUI extends JFrame implements ActionListener {
    private JPanel WorkerGUIWin;
    private JComboBox comboBox1;
    private JButton startButton;
    private JButton exitButton;
    private JTextField ID;
    private JPasswordField Password;
    //take instance for worker controller
    private WorkerController workerController;
    //take the instance of gui service so we get what we want
    private GUIService guiService;

    public WorkerGUI() {
        this.setContentPane(WorkerGUIWin);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,200));
        this.pack();
        this.setVisible(true);
        this.setTitle("Worker");
        guiService=GUIService.getInstance();
        startButton.addActionListener(this);
        exitButton.addActionListener(this);
        comboBox1.addActionListener(this);
        this.workerController = WorkerController.getInstance();
    }
    private void createUIComponents() {
        comboBox1 = new JComboBox<String>();
        comboBox1.addItem("update personal details");
        comboBox1.addItem("edit constraints");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==startButton){
            //check that id and password exists
            if(!workerController.isExistWorker(ID.getText()) || !workerController.IsTruePassword(ID.getText(),Password.getText())){
                JOptionPane.showMessageDialog(null, "invalid input - try again!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                //open the function he wants
                if (comboBox1.getSelectedItem().equals("update personal details")) {
                    this.setVisible(false);
                    List<String> worker=guiService.getWorkerInfo(ID.getText());
                    new updateDetails(this,worker);
                } else {
                    this.setVisible(false);
                    new editConstraints(this,ID.getText());
                }
            }
        }
        else if(e.getSource()==exitButton)
        {
            System.exit(0);
        }
    }
}
