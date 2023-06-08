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
                // todo: add a new window for adding new worker
            }
            else if(comboBox1.getSelectedItem().equals("add worker to branch"))
            {
                //todo: first cuild the function for list of supers and then do this one
            }
            else if(comboBox1.getSelectedItem().equals("remove worker"))
            {
                JTextField textField = new JTextField();
                int result = JOptionPane.showConfirmDialog(null, textField, "Enter worker id:", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String ID = textField.getText();
                    boolean check = managerController.isExistWorker(ID);
                    //if the worker exists
                    if (!check) {
                        JOptionPane.showMessageDialog(null, "worker id not found");
                    }
                    else{
                        managerController.RemoveWorkerAllBranches(ID);
                        JOptionPane.showMessageDialog(null, "The worker has been removed successfully!", "Fired!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
            else if(comboBox1.getSelectedItem().equals("add job for worker"))
            {
                JTextField textField = new JTextField();
                int result = JOptionPane.showConfirmDialog(null, textField, "Enter worker id:", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String ID = textField.getText();
                    boolean check = managerController.isExistWorker(ID);
                    //if the worker exists
                    if(!check)
                    {
                        JOptionPane.showMessageDialog(null,"worker id not found");
                    }
                    else{
                        String[] options = {"ShiftManager","Cashier","StoreKeeper","GeneralEmp","Guard","Cleaner","Usher"};
                        JList<String> list = new JList<>(options);
                        result = JOptionPane.showConfirmDialog(null, new JScrollPane(list), "Select a job to add:", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.OK_OPTION) {
                            // save the selected option
                            String selectedOption = list.getSelectedValue();
                            managerController.AddJobToWorker(ID,selectedOption);
                            JOptionPane.showMessageDialog(null, "Role added successfully!", "Role", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
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
                JTextField field1 = new JTextField();
                JTextField field2 = new JTextField();
                JPanel panel = new JPanel(new GridLayout(2, 2));
                panel.add(new JLabel("Enter Worker ID:"));
                panel.add(field1);
                panel.add(new JLabel("Enter New Contract:"));
                panel.add(field2);
                int result = JOptionPane.showConfirmDialog(null, panel, "Enter values", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String ID = field1.getText();
                    String value2 = field2.getText();
                    if(!workerController.isExistWorker(ID)){
                        JOptionPane.showMessageDialog(null, "This worker doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    managerController.ChangeContract(ID,value2);
                    JOptionPane.showMessageDialog(null, "Contract changed successfully!", "Contract", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else if(comboBox1.getSelectedItem().equals("add bonus to worker"))
            {
                JTextField field1 = new JTextField();
                JTextField field2 = new JTextField();
                JPanel panel = new JPanel(new GridLayout(2, 2));
                panel.add(new JLabel("Enter Worker ID:"));
                panel.add(field1);
                panel.add(new JLabel("Enter Bonus:"));
                panel.add(field2);
                int result = JOptionPane.showConfirmDialog(null, panel, "Enter values", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String ID = field1.getText();
                    String value2 = field2.getText();
                    int bonus = Integer.parseInt(value2);
                    if(!workerController.isExistWorker(ID)){
                        JOptionPane.showMessageDialog(null, "This worker doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(bonus < 0){
                        JOptionPane.showMessageDialog(null, "bonus can't be negative", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String WorkerName = managerController.addBonusToWorker(ID,bonus);
                    JOptionPane.showMessageDialog(null, "Bonus added successfully to: " + WorkerName, "Bonus", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else if(comboBox1.getSelectedItem().equals("remove bonus from worker"))
            {
                JTextField field1 = new JTextField();
                JTextField field2 = new JTextField();
                JPanel panel = new JPanel(new GridLayout(2, 2));
                panel.add(new JLabel("Enter Worker ID:"));
                panel.add(field1);
                panel.add(new JLabel("Enter Bonus:"));
                panel.add(field2);
                int result = JOptionPane.showConfirmDialog(null, panel, "Enter values", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String ID = field1.getText();
                    String value2 = field2.getText();
                    int bonus = Integer.parseInt(value2);
                    if(!workerController.isExistWorker(ID)){
                        JOptionPane.showMessageDialog(null, "This worker doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(bonus < 0){
                        JOptionPane.showMessageDialog(null, "bonus can't be negative", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String WorkerName = managerController.removeBonusToWorker(ID,bonus);
                    JOptionPane.showMessageDialog(null, "Bonus removed successfully from: " + WorkerName, "Bonus", JOptionPane.INFORMATION_MESSAGE);
                }
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
