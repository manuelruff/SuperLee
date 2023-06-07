package HR.PresentationGUI;

import javax.swing.*;

public class HRManager {
    private JPanel HRManagerWin;
    private JComboBox comboBox1;
    private JButton startButton;
    private JButton exitButton;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox1 = new JComboBox();
        comboBox1.addItem("work on a branch (snif)");
        comboBox1.addItem("add new branch");
        comboBox1.addItem("send weekly shifts to history for all branches");
        comboBox1.addItem("update employee");
        comboBox1.addItem("Show Drivers Schedule");
        comboBox1.addItem("Show all shipments");
        comboBox1.addItem("change password");
        comboBox1.addItem("pay salaries");

    }
}
