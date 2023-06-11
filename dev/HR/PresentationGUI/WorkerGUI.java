package HR.PresentationGUI;

import HR.Bussiness.ManagerController;
import HR.Bussiness.WorkerController;
import HR.PresentationGUI.WorkerWindows.editConstraints;
import HR.PresentationGUI.WorkerWindows.updateDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkerGUI extends JFrame implements ActionListener {
    private JPanel WorkerGUIWin;
    private JComboBox comboBox1;
    private JButton startButton;
    private JButton exitButton;
    //take instance for worker controller
    private WorkerController workerController;

    public WorkerGUI() {
        this.setContentPane(WorkerGUIWin);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,200));
        this.pack();
        this.setVisible(true);
        this.setTitle("Worker");
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
            //open the function he wants
            if(comboBox1.getSelectedItem().equals("update personal details")){
                new updateDetails(this);
            }
            else {
                new editConstraints(this);
            }
        }
        else if(e.getSource()==exitButton)
        {
            System.exit(0);
        }
    }
}
