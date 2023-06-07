package HR.PresentationGUI.HRManagaerWindows;

import HR.Bussiness.ManagerController;
import HR.Bussiness.WorkerController;
import HR.PresentationGUI.HRManager;

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
    private WorkerController workerController;
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
        this.workerController = WorkerController.getInstance();
        this.save = save;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Start"))
        {
            if(comboBox1.getSelectedItem().equals("add new worker"))
            {

            }
            else if(comboBox1.getSelectedItem().equals("add worker to branch"))
            {

            }
            else if(comboBox1.getSelectedItem().equals("remove worker"))
            {

            }
            else if(comboBox1.getSelectedItem().equals("add job for worker"))
            {

            }
            else if(comboBox1.getSelectedItem().equals("change worker wage"))
            {
                JTextField field1 = new JTextField();
                JTextField field2 = new JTextField();
                JPanel panel = new JPanel(new GridLayout(2, 2));
                panel.add(new JLabel("Enter Worker ID:"));
                panel.add(field1);
                panel.add(new JLabel("Enter New Wage:"));
                panel.add(field2);
                int result = JOptionPane.showConfirmDialog(null, panel, "Enter values", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String ID = field1.getText();
                    String value2 = field2.getText();
                    int wage = Integer.parseInt(value2);
                    if(!workerController.isExistWorker(ID)){
                        JOptionPane.showMessageDialog(null, "This worker doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(wage < 0){
                        JOptionPane.showMessageDialog(null, "Wage can't be negative", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    managerController.ChangeWage(ID,wage);
                    JOptionPane.showMessageDialog(null, "Waged changed successfully!", "Wage", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else if(comboBox1.getSelectedItem().equals("change worker contract"))
            {

            }
            else if(comboBox1.getSelectedItem().equals("add bonus to worker"))
            {

            }
            else if(comboBox1.getSelectedItem().equals("remove bonus from worker"))
            {

            }

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
