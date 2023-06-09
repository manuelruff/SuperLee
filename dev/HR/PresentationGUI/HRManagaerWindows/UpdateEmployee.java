package HR.PresentationGUI.HRManagaerWindows;

import HR.Bussiness.ManagerController;
import HR.PresentationGUI.HRManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class UpdateEmployee extends JFrame implements ActionListener  {

    private JPanel UpdateEmployeeWin;
    private JComboBox comboBox1;
    private JButton backButton;
    private JButton startButton;
    //private JLabel IdLabel;
    //private JTextField IdText;
    private ManagerController managerController;
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
        //IdLabel.setVisible(false);
        //IdText.setVisible(false);
        this.managerController = ManagerController.getInstance();
        this.save = save;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Start"))
        {
            if(comboBox1.getSelectedItem().equals("add new worker"))
            {
                new AddNewWorker(this);
                this.setVisible(false);
            }
            else if(comboBox1.getSelectedItem().equals("add worker to branch")) {
                //*** from here try changes***
//                String[] allBranches = managerController.getAllSuperNames();
//                JList<String> list = new JList<>(allBranches);
//                int result = JOptionPane.showConfirmDialog(null, new JScrollPane(list), "Select a branch to add the employee:", JOptionPane.OK_CANCEL_OPTION);
//                if (result == JOptionPane.OK_OPTION) {
//                    // save the selected option
//                    String selectedOption = list.getSelectedValue();
//                    managerController.AddWorkerToBranch(IdText.getText(), selectedOption);
//                    JOptionPane.showMessageDialog(null, "The worker added successfully!", "Role", JOptionPane.INFORMATION_MESSAGE);
//                }
                // *** until here try changes***


                JTextField textField = new JTextField();
                int result = JOptionPane.showConfirmDialog(null, textField, "Enter worker id:", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String ID = textField.getText();
                    boolean check = managerController.isExistWorker(ID);
                    //if the worker exists
                    if (!check) {
                        JOptionPane.showMessageDialog(null, "worker id not found");
                    } else {
                        String[] allBranches = managerController.getAllSuperNames();
                        JList<String> list = new JList<>(allBranches);
                        result = JOptionPane.showConfirmDialog(null, new JScrollPane(list), "Select a branch to add the employee:", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.OK_OPTION) {
                            // save the selected option
                            String selectedOption = list.getSelectedValue();
                            managerController.AddWorkerToBranch(ID, selectedOption);
                            JOptionPane.showMessageDialog(null, "The worker added successfully!", "Role", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
            else if(comboBox1.getSelectedItem().equals("remove worker"))
            {
                //*** try changes from here***
                //managerController.RemoveWorkerAllBranches(IdText.getText());
                //JOptionPane.showMessageDialog(null, "The worker has been removed successfully!", "Fired!", JOptionPane.INFORMATION_MESSAGE);
                //**changes stop here
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
                //*** changes start from here***
//                String[] options = {"ShiftManager","Cashier","StoreKeeper","GeneralEmp","Guard","Cleaner","Usher"};
//                JList<String> list = new JList<>(options);
//                int result = JOptionPane.showConfirmDialog(null, new JScrollPane(list), "Select a job to add:", JOptionPane.OK_CANCEL_OPTION);
//                if (result == JOptionPane.OK_OPTION) {
//                    // save the selected option
//                    String selectedOption = list.getSelectedValue();
//                    managerController.AddJobToWorker(IdText.getText(),selectedOption);
//                    JOptionPane.showMessageDialog(null, "Role added successfully!", "Role", JOptionPane.INFORMATION_MESSAGE);
                //}
                //*** end of changes***
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
                    if(!managerController.isExistWorker(ID)){
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
                    if(!managerController.isExistWorker(ID)){
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
                    if(!managerController.isExistWorker(ID)){
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
                    if(!managerController.isExistWorker(ID)){
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
        // Create the combo box and add items to it
        comboBox1 = new JComboBox<>();
        comboBox1.addItem("add new worker");
        comboBox1.addItem("add worker to branch");
        comboBox1.addItem("remove worker");
        comboBox1.addItem("add job for worker");
        comboBox1.addItem("change worker wage");
        comboBox1.addItem("change worker contract");
        comboBox1.addItem("add bonus to worker");
        comboBox1.addItem("remove bonus from worker");

        // Add an item listener to the combo box
//        comboBox1.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                // Check if the "add worker to branch" item is selected
//                if (!comboBox1.getSelectedItem().equals("add new worker")) {
//                    // Show the ID label and text field
//                    setIDVisible();
//                    IdText.setInputVerifier(new UpdateEmployee.IdVerifier(IdLabel));
//
//                    // Add a change listener to the IdText field
//                    IdText.getDocument().addDocumentListener(new DocumentListener() {
//                        @Override
//                        public void insertUpdate(DocumentEvent e) {
//                            updateStartButton();
//                        }
//
//                        @Override
//                        public void removeUpdate(DocumentEvent e) {
//                            updateStartButton();
//                        }
//
//                        @Override
//                        public void changedUpdate(DocumentEvent e) {
//                            updateStartButton();
//                        }
//                    });
//
//                    // Disable the Start button initially
//                    startButton.setEnabled(false);
//                }
//                else {
//                    // Enable the Start button
//                    startButton.setEnabled(true);
//                    // Hide the ID label and text field
//                    IdLabel.setVisible(false);
//                    IdText.setVisible(false);
//                }
//            }
//        });
    }


    // Update the Start button based on the validity of the input
//    private void updateStartButton() {
//        boolean isValid = IdText.getInputVerifier().verify(IdText);
//        startButton.setEnabled(isValid);
//    }



    // the verifier for ID
    class IdVerifier extends InputVerifier {
        private JLabel idLabel;

        public IdVerifier(JLabel idLabel) {
            this.idLabel = idLabel;
        }

        @Override
        public boolean verify(JComponent input) {
            // Get the ID field text
            String ID = ((JTextField) input).getText();
            // Check if the worker exists
            boolean check = managerController.isExistWorker(ID);
            if (!check) {
                // Change the color of the IdLabel to red
                idLabel.setForeground(Color.RED);
                return false;
            }
            // Change the color of the IdLabel to black
            idLabel.setForeground(Color.WHITE);
            return true;
        }
    }

    // set the Id to visible
//    private void setIDVisible() {
//        IdLabel.setVisible(true);
//        IdText.setVisible(true);
//    }
}
