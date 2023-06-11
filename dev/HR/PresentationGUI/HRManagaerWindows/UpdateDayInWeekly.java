package HR.PresentationGUI.HRManagaerWindows;
import HR.Bussiness.Days;
import HR.Bussiness.ManagerController;
import HR.Bussiness.ShiftTime;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateDayInWeekly extends JFrame implements ActionListener {

    private JPanel UpdateDayWin;
    private JComboBox<String> DayComboBox;
    private JComboBox<String> ShiftComboBox;
    private JComboBox<String> ActionComboBox;
    private JButton doButton;
    private JButton backButton;
    private JButton watchShiftButton;
    private ManagerController managerController;

    //save the window that opened us to show him after closing
    private WorkOnABranch save;
    //name of this branch we are working on
    private String name;
    public UpdateDayInWeekly(WorkOnABranch save,String name) {
        this.save=save;
        this.name=name;
        this.setContentPane(UpdateDayWin);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,200));
        this.pack();
        this.setVisible(true);
        this.setTitle("Update day in weekly");
        doButton.addActionListener(this);
        watchShiftButton.addActionListener(this);
        backButton.addActionListener(this);
        DayComboBox.addActionListener(this);
        ShiftComboBox.addActionListener(this);
        ActionComboBox.addActionListener(this);
        this.managerController = ManagerController.getInstance();
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        DayComboBox = new JComboBox<String>();
        DayComboBox.addItem("Sunday");
        DayComboBox.addItem("Monday");
        DayComboBox.addItem("Tuesday");
        DayComboBox.addItem("Wednesday");
        DayComboBox.addItem("Thursday");
        DayComboBox.addItem("Friday");
        DayComboBox.addItem("Saturday");
        ActionComboBox = new JComboBox<String>();
        ActionComboBox.addItem("Add worker");
        ActionComboBox.addItem("Remove worker");
        ShiftComboBox = new JComboBox<String>();
        ShiftComboBox.addItem("Morning");
        ShiftComboBox.addItem("Evening");
        doButton = new JButton("Do");
        watchShiftButton = new JButton("Watch Shift");
        backButton = new JButton("Back");
        doButton.setForeground(Color.black);
        watchShiftButton.setForeground(Color.black);
        backButton.setForeground(Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==doButton)
        {
            if(ActionComboBox.getSelectedItem().equals("Add worker")) {
                //show a window to ask type of worker
                Object[] options = {"ShiftManager", "Cashier", "GeneralEmp", "Guard", "Guard", "Cleaner","Usher"};
                int selection = JOptionPane.showOptionDialog(null, "Choose a role", "Roles", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                int shift_op =ShiftTime.valueOf(ShiftComboBox.getSelectedItem().toString()).ordinal();
                int day_choice = Days.valueOf(DayComboBox.getSelectedItem().toString()).ordinal();
                String workerID = managerController.AddToDay(name,shift_op,day_choice,selection);
                JOptionPane.showMessageDialog(null, "Worker added: "+workerID, "added", JOptionPane.INFORMATION_MESSAGE);
                //after i add it ill press back so he can go preform another action
                backButton.doClick();
            }
            else if(ActionComboBox.getSelectedItem().equals("Remove worker")) {
                int day_choice = Days.valueOf(DayComboBox.getSelectedItem().toString()).ordinal();
                JTextField textField = new JTextField();
                int result = JOptionPane.showConfirmDialog(null, textField, "Enter worker id:", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String id = textField.getText();
                    if(!managerController.IsWorkAtDay(name, id,day_choice)){
                        JOptionPane.showMessageDialog(null, "this worker doesn't works at this day", "error", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if(managerController.IsOnlyStoreKeeperWithShipmentOrShiftManager(name,id,day_choice)){
                        //todo comtinue this

                        Object[] options = {"cancel the shipment and remove the worker", "go back and add other worker before",};
                        int selection = JOptionPane.showOptionDialog(null, "choose an action", "error", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        if(selection==0){
                            // delete the shipment
                            managerController.DeleteShipment(name,day_choice);
                            //remove the worker
                            managerController.RemoveFromDay(id, name,day_choice);
                            //after i add it ill press back so he can go preform another action
                            backButton.doClick();                        }
                        else{
                            //after i add it ill press back so he can go preform another action
                            backButton.doClick();                        }
                    }
                    if(!managerController.RemoveFromDay(id,name,day_choice)){
                        // I think we dont need this - but check in tests
                        JOptionPane.showMessageDialog(null, "sorry, we have a problem - please try again!", "error", JOptionPane.INFORMATION_MESSAGE);
                        //after i add it ill press back so he can go preform another action
                        backButton.doClick();
                    }
                }
                //after i add it ill press back so he can go preform another action
                backButton.doClick();
            }
        }
        if(e.getActionCommand().equals("Watch Shift"))
        {
            //we will ask what day he wants to see
            Object[] options = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday","Saturday"};
            int selection = JOptionPane.showOptionDialog(null, "Choose a day", "Shifts", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            //we will open the window of the shifts for this day
            new PrintShift(name,selection);
        }
        else if(e.getSource()==ActionComboBox)
        {
            if(ActionComboBox.getSelectedItem().equals("Remove worker")) {
                //in this action he is not choosing in
                ShiftComboBox.setVisible(false);
            }
            if(ActionComboBox.getSelectedItem().equals("Add worker")) {
                //in this action he needs to choose a shift
                ShiftComboBox.setVisible(true);
            }
        }
        else if(e.getSource()==backButton)
        {
            //we show the main window
            save.setVisible(true);
            //close this window
            this.dispose();
        }
    }



}
