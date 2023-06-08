package HR.PresentationGUI;
import HR.Bussiness.ManagerController;
import HR.PresentationGUI.HRManagaerWindows.UpdateEmployee;
import HR.PresentationGUI.HRManagaerWindows.WorkOnABranch;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HRManager extends JFrame implements ActionListener{
    private JPanel HRManagerWin;
    private JComboBox<String> comboBox1;
    private JButton startButton;
    private JButton exitButton;
    private ManagerController managerController;

    public HRManager() {
        this.setContentPane(HRManagerWin);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,200));
        this.pack();
        this.setVisible(true);
        this.setTitle("HR Manager");
        startButton.addActionListener(this);
        exitButton.addActionListener(this);
        comboBox1.addActionListener(this);
        this.managerController = ManagerController.getInstance();
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox1 = new JComboBox<String>();
        comboBox1.addItem("work on a branch (snif)");
        comboBox1.addItem("add new branch");
        comboBox1.addItem("send weekly shifts to history for all branches");
        comboBox1.addItem("update employee");
        comboBox1.addItem("Show Drivers Schedule");
        comboBox1.addItem("Show all shipments");
        comboBox1.addItem("pay salaries");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Start"))
        {
            if(comboBox1.getSelectedItem().equals("work on a branch (snif)"))
            {
                String[] allBranches = managerController.getAllSuperNames();

//                namesBox.setModel(new DefaultComboBoxModel<>(allBranches));
//                HRManagerWin.add(namesBox);

                JList<String> list = new JList<>(allBranches);
                int result = JOptionPane.showConfirmDialog(null, new JScrollPane(list), "Select a branch to add the employee:", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    // save the selected option
                    String name = list.getSelectedValue();
                    new WorkOnABranch(this,name);
                    this.setVisible(false);
                }
                //JTextField textField = new JTextField();
                //int result = JOptionPane.showConfirmDialog(null, textField, "Enter branch name:", JOptionPane.OK_CANCEL_OPTION);
//                if (result == JOptionPane.OK_OPTION) {
//                    String name = textField.getText();
//                    boolean check =managerController.CheckBranchExist(name);
//                    //if the branch exists
//                    if(check)
//                    {
//                        new WorkOnABranch(this,name);
//                        this.setVisible(false);
//                    }
//                    else {
//                        JOptionPane.showMessageDialog(null,"no such branch");
//                    }
//                }
            }
            else if(comboBox1.getSelectedItem().equals("add new branch"))
            {

            }
            else if(comboBox1.getSelectedItem().equals("send weekly shifts to history for all branches"))
            {
                //check if he created the weekly for everyone
                if(!managerController.CheckAllHaveWeekly()){
                    JOptionPane.showMessageDialog(null, "not all the branches has a weekly plan create them first and then you can send", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to send weekly shifts to history for all branches?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    // user clicked "Yes"
                    //send all to history
                    managerController.SendConstraintsToHistory();
                    JOptionPane.showMessageDialog(null, "Sending Done!", "Send To History", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    // user clicked "No" or closed the dialog
                    JOptionPane.showMessageDialog(null, "Sending Canceled!", "Send To History", JOptionPane.INFORMATION_MESSAGE);
                }

            }
            else if(comboBox1.getSelectedItem().equals("update employee"))
            {
                new UpdateEmployee(this);
                this.setVisible(false);
            }
            else if(comboBox1.getSelectedItem().equals("Show Drivers Schedule"))
            {
                //todo: add the drivers schedule print
            }
            else if(comboBox1.getSelectedItem().equals("Show all shipments"))
            {
                //todo: add the shipments print
            }
            else if(comboBox1.getSelectedItem().equals("pay salaries"))
            {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to pay salaries?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    // user clicked "Yes"
                    managerController.loadAllWorkersFrom();
                    managerController.Payment();
                    JOptionPane.showMessageDialog(null, "Payment Done!", "Payment", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    // user clicked "No" or closed the dialog
                    JOptionPane.showMessageDialog(null, "Payment Canceled!", "Payment", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }
        else if(e.getActionCommand().equals("Exit"))
        {
            System.exit(0);
        }
    }
}
