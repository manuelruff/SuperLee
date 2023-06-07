package HR.PresentationGUI;

import HR.Bussiness.ManagerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateEmployee extends JFrame implements ActionListener  {

    private JPanel UpdateEmployeeWin;
    private JComboBox comboBox1;
    private JButton exitButton;
    private JButton startButton;
    private ManagerController managerController;

    public UpdateEmployee() {
        setContentPane(UpdateEmployeeWin);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.setMaximumSize(new Dimension(300,200));
        this.pack();
        this.setTitle("Update Employee");
        exitButton.addActionListener(this);
        startButton.addActionListener(this);
        comboBox1.addActionListener(this);
        this.managerController = ManagerController.getInstance();
    }
    @Override
    public void actionPerformed(ActionEvent e) {

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
