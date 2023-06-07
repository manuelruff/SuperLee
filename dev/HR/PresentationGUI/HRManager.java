package HR.PresentationGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HRManager extends JFrame implements ActionListener{
    private JPanel HRManagerWin;
    private JComboBox<String> comboBox1;
    private JButton startButton;
    private JButton exitButton;

    public HRManager() {
        this.setContentPane(HRManagerWin);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setTitle("HR Manager");
        startButton.addActionListener(this);
        exitButton.addActionListener(this);
        comboBox1.addActionListener(this);
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
        comboBox1.addItem("change password");
        comboBox1.addItem("pay salaries");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Start"))
        {
            if(comboBox1.getSelectedItem().equals("work on a branch (snif)"))
            {

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
            else if(comboBox1.getSelectedItem().equals("change password")) {

            }
            else if(comboBox1.getSelectedItem().equals("pay salaries"))
            {

            }

        }
        else if(e.getActionCommand().equals("Exit"))
        {
            System.exit(0);
        }
    }
}
