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
    public WorkOnABranch(HRManager save) {
        this.save=save;
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
