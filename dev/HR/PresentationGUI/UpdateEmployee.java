package HR.PresentationGUI;

import HR.Bussiness.ManagerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateEmployee extends JFrame implements ActionListener  {

    private JPanel UpdateEmployeeWin;
    private JComboBox comboBox1;
    private JButton backButton;
    private JButton startButton;
    private ManagerController managerController;
    private HRManager save;

    public UpdateEmployee(HRManager save) {
        setContentPane(UpdateEmployeeWin);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.setMaximumSize(new Dimension(300,200));
        this.pack();
        this.setTitle("Update Employee");
        backButton.addActionListener(this);
        startButton.addActionListener(this);
        comboBox1.addActionListener(this);
        this.managerController = ManagerController.getInstance();
        this.save = save;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Start"))
        {

        }
        else if(e.getActionCommand().equals("Back"))
        {
            //we show the main window
            save.setVisible(true);
            //close this window
            this.dispose();
        }

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox1 = new JComboBox<String>();
        comboBox1.addItem("add new worker");
        comboBox1.addItem("add worker to branch");
        comboBox1.addItem("remove worker");
        comboBox1.addItem("add job for worker");
        comboBox1.addItem("change worker wage");
        comboBox1.addItem("change worker contract");
        comboBox1.addItem("add bonus to worker");
        comboBox1.addItem("remove bonus from worker");
    }
}
