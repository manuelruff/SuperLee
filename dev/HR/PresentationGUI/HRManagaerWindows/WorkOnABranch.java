package HR.PresentationGUI.HRManagaerWindows;
import HR.Bussiness.ManagerController;
import HR.PresentationGUI.HRManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkOnABranch extends JFrame implements ActionListener {
    private JPanel WorkOnABranchWin;
    private  JComboBox<String>  comboBox1;
    private JButton backButton;
    private JButton startButton;
    private ManagerController managerController;

    //save the window that opened us to show him after closing
    private HRManager save;
    //name of this branch we are working on
    private String name;
    public WorkOnABranch(HRManager save,String name) {
        this.save=save;
        this.name=name;
        this.setContentPane(WorkOnABranchWin);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,200));
        this.pack();
        this.setVisible(true);
        this.setTitle("work on a branch (snif)");
        startButton.addActionListener(this);
        backButton.addActionListener(this);
        comboBox1.addActionListener(this);
        this.managerController = ManagerController.getInstance();
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox1 = new JComboBox<String>();
        comboBox1.addItem("create weekly shift");
        comboBox1.addItem("update day in weekly shift");
        comboBox1.addItem("watch week from history");
        comboBox1.addItem("remove worker from this super");
        comboBox1.addItem("update super shift times");
        comboBox1.addItem("delete this branch");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Start"))
        {
            if(comboBox1.getSelectedItem().equals("create weekly shift")) {

            }
            else if(comboBox1.getSelectedItem().equals("update day in weekly shift")) {
                //check if this branch has weekly
                if(!managerController.HasWeekly(name)){
                    JOptionPane.showMessageDialog(null, "no weekly yet, go create one first", "weekly", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    new UpdateDayInWeekly(this,name);
                    this.setVisible(false);
                }
            }
            else if(comboBox1.getSelectedItem().equals("watch week from history")) {

            }
            else if(comboBox1.getSelectedItem().equals("remove worker from this super")) {
                JTextField textField = new JTextField();
                int result = JOptionPane.showConfirmDialog(null, textField, "Enter worker id:", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String id = textField.getText();
                    boolean check =managerController.IsWorksInSuper(id,name);
                    //if the branch exists
                    if(check)
                    {
                        managerController.RemoveWorker(id,name);
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"worker id not found");
                    }
                }
            }
            else if(comboBox1.getSelectedItem().equals("update super shift times")) {

            }
            else if(comboBox1.getSelectedItem().equals("delete this branch")) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this branch?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    // user clicked "Yes"
                    managerController.deleteBranch(name);
                    JOptionPane.showMessageDialog(null, "delete Done!", "delete", JOptionPane.INFORMATION_MESSAGE);
                    // if we delete we go out
                    //we show the main window
                    save.setVisible(true);
                    //close this window
                    this.dispose();
                }
                else {
                    // user clicked "No" or closed the dialog
                    JOptionPane.showMessageDialog(null, "I didnt thought so!", "delete", JOptionPane.INFORMATION_MESSAGE);
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
}
