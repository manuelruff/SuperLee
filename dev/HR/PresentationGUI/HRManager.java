package HR.PresentationGUI;
import HR.Bussiness.ManagerController;
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
                JTextField textField = new JTextField();
                int result = JOptionPane.showConfirmDialog(null, textField, "Enter branch name:", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String name = textField.getText();
                    boolean check =managerController.CheckBranchExist(name);
                    //if the branch exists
                    if(check)
                    {
                        new WorkOnABranch(this,name);
                        this.setVisible(false);
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"no such branch");
                    }
                }

            }
            else if(comboBox1.getSelectedItem().equals("add new branch"))
            {

            }
            else if(comboBox1.getSelectedItem().equals("send weekly shifts to history for all branches"))
            {

            }
            else if(comboBox1.getSelectedItem().equals("update employee"))
            {

            }
            else if(comboBox1.getSelectedItem().equals("Show Drivers Schedule"))
            {

            }
            else if(comboBox1.getSelectedItem().equals("Show all shipments"))
            {

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
