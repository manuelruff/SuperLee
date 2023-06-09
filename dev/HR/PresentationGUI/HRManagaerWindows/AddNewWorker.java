package HR.PresentationGUI.HRManagaerWindows;
import HR.Bussiness.Jobs;
import HR.Bussiness.ManagerController;
import HR.Bussiness.Training;
import Shipment.Bussiness.Branch;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;


public class AddNewWorker extends JFrame implements ActionListener {
    private JPanel AddNewWorkerWin;
    private ManagerController managerController;
    //save the window that opened us to show him after closing
    private UpdateEmployee save;
    private JLabel idLabel, fullNameLabel, bankNumLabel, contractLabel, wageLabel, roleLabel,branchLabel,licenceLabel,trainingLabel;
    private JTextField idField, fullNameField, bankNumField, contractField, wageField, roleField,branchField,licenceField,trainingField;
    private JComboBox<String> jobComboRole,branchCombo,licenceCombo,trainingCombo;
    private JButton addButton, cancelButton;

    public AddNewWorker(UpdateEmployee save) {
        this.save=save;
        this.managerController = ManagerController.getInstance();
        // todo: check why it must be here and yet say I have problem
        this.branchCombo = new JComboBox<>(managerController.getAllSuperNames());
        addButton.setEnabled(false);
        // Set the initial visibility of the fields
        trainingLabel.setVisible(false);
        trainingField.setVisible(false);
        licenceLabel.setVisible(false);
        licenceField.setVisible(false);
        branchLabel.setVisible(false);
        branchField.setVisible(false);
        branchCombo.setVisible(false);
        licenceCombo.setVisible(false);
        trainingCombo.setVisible(false);


        // Add document listeners to each text field
        idField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }
        });

        fullNameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }
        });

        bankNumField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }
        });

        contractField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }
        });

        wageField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }
        });

        roleField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }
        });

        branchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }
        });
        licenceField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }
        });
        trainingField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateAddButtonEnabledState();
            }
        });
    }

    private void updateAddButtonEnabledState() {
        // Enable the add button only if all text fields are non-empty
        boolean allFieldsFilled = !idField.getText().isEmpty() &&
                !fullNameField.getText().isEmpty() &&
                !bankNumField.getText().isEmpty() &&
                !contractField.getText().isEmpty() &&
                !jobComboRole.getSelectedItem().equals("")&&
                !wageField.getText().isEmpty();
        addButton.setEnabled(allFieldsFilled);
    }

    private void createUIComponents() {
        this.setTitle("Add New Worker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the content pane background color to black
        getContentPane().setBackground(Color.BLACK);

        // Create the components
        idLabel = new JLabel("ID:");
        fullNameLabel = new JLabel("Full Name:");
        bankNumLabel = new JLabel("Bank Number:");
        contractLabel = new JLabel("Contract:");
        wageLabel = new JLabel("Wage:");
        roleLabel = new JLabel("Role:");
        branchLabel = new JLabel("Branch:");
        licenceLabel = new JLabel("Licence:");
        trainingLabel = new JLabel("Training:");

        // Set the foreground color of each label to white
        idLabel.setForeground(Color.WHITE);
        fullNameLabel.setForeground(Color.WHITE);
        bankNumLabel.setForeground(Color.WHITE);
        contractLabel.setForeground(Color.WHITE);
        wageLabel.setForeground(Color.WHITE);
        roleLabel.setForeground(Color.WHITE);
        branchLabel.setForeground(Color.WHITE);
        licenceLabel.setForeground(Color.WHITE);
        trainingLabel.setForeground(Color.WHITE);

        idField = new JTextField(10);
        fullNameField = new JTextField(10);
        bankNumField = new JTextField(10);
        contractField = new JTextField(10);
        wageField = new JTextField(10);
        roleField = new JTextField(10);
        branchField = new JTextField(10);
        licenceField = new JTextField(10);
        trainingField = new JTextField(10);

        // check Id validate
        // Set the input verifier for the ID field
        idField.setInputVerifier(new IdVerifier(idLabel));

        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");
        addButton.setForeground(Color.BLACK);
        cancelButton.setForeground(Color.BLACK);

        // Create a panel and set the layout
        AddNewWorkerWin = new JPanel();
        AddNewWorkerWin.setLayout(new BoxLayout(AddNewWorkerWin, BoxLayout.Y_AXIS));

        // Add the components to the panel
        JPanel IdPanel = new JPanel(new GridLayout(1, 2));
        IdPanel.setBackground(Color.BLACK); // Set the panel background color to black
        IdPanel.add(idLabel);
        IdPanel.add(idField);
        AddNewWorkerWin.add(IdPanel);

        JPanel NamePanel = new JPanel(new GridLayout(1, 2));
        NamePanel.setBackground(Color.BLACK); // Set the panel background color to black
        NamePanel.add(fullNameLabel);
        NamePanel.add(fullNameField);
        AddNewWorkerWin.add(NamePanel);


        JPanel BankNumPanel = new JPanel(new GridLayout(1, 2));
        BankNumPanel.setBackground(Color.BLACK); // Set the panel background color to black
        BankNumPanel.add(bankNumLabel);
        BankNumPanel.add(bankNumField);
        AddNewWorkerWin.add(BankNumPanel);


        JPanel ContractPanel = new JPanel(new GridLayout(1, 2));
        ContractPanel.setBackground(Color.BLACK); // Set the panel background color to black
        ContractPanel.add(contractLabel);
        ContractPanel.add(contractField);
        AddNewWorkerWin.add(ContractPanel);


        JPanel WagePanel = new JPanel(new GridLayout(1, 2));
        WagePanel.setBackground(Color.BLACK); // Set the panel background color to black
        WagePanel.add(wageLabel);
        WagePanel.add(wageField);
        AddNewWorkerWin.add(WagePanel);


        JPanel RolePanel = new JPanel(new GridLayout(1, 2));
        RolePanel.setBackground(Color.BLACK); // Set the panel background color to black
        RolePanel.add(roleLabel);
        jobComboRole = new JComboBox<>(new String[] {"","Driver", "ShiftManager", "Cashier", "StoreKeeper", "GeneralEmp", "Guard", "Cleaner", "Usher"});
        jobComboRole.setPreferredSize(roleField.getPreferredSize());
        RolePanel.add(roleLabel);
        RolePanel.add(jobComboRole);
        AddNewWorkerWin.add(RolePanel);

        jobComboRole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) jobComboRole.getSelectedItem();
                toggleFieldsVisibility(selectedOption);
            }
        });

        JPanel BranchPanel = new JPanel(new GridLayout(1, 2));
        BranchPanel.setBackground(Color.BLACK); // Set the panel background color to black
        BranchPanel.add(branchLabel);
        // todo: need to understand why the branches arent load
        AddNewWorkerWin.add(BranchPanel);

        JPanel LicencePanel = new JPanel(new GridLayout(1, 2));
        LicencePanel.setBackground(Color.BLACK); // Set the panel background color to black
        LicencePanel.add(licenceLabel);
        licenceCombo= new JComboBox<>(new String[] {"C", "D"});
        licenceCombo.setPreferredSize(licenceField.getPreferredSize());
        LicencePanel.add(licenceCombo);
        AddNewWorkerWin.add(LicencePanel);

        JPanel TrainingPanel = new JPanel(new GridLayout(1, 2));
        TrainingPanel.setBackground(Color.BLACK); // Set the panel background color to black
        TrainingPanel.add(trainingLabel);
        trainingCombo= new JComboBox<>(new String[] {"Regular","Cooling","Freezer"});
        trainingCombo.setPreferredSize(trainingField.getPreferredSize());
        TrainingPanel.add(trainingCombo);
        AddNewWorkerWin.add(TrainingPanel);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1)); // Set the grid layout to 2 rows and 1 column
        buttonPanel.setBackground(Color.BLACK); // Set the panel background color to black
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        AddNewWorkerWin.add(buttonPanel);

        // Set the maximum size of the buttons to fill the horizontal space
        Dimension maxButtonSize = new Dimension(Integer.MAX_VALUE, addButton.getPreferredSize().height);
        addButton.setMaximumSize(maxButtonSize);
        cancelButton.setMaximumSize(maxButtonSize);
        //addButton.setEnabled(false);

        // Set the action listeners for the buttons
        addButton.addActionListener(this);
        cancelButton.addActionListener(this);

        wageField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });
        bankNumField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        // Set the content pane and display the window
        setContentPane(AddNewWorkerWin);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 200));
        pack();
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String Id = idField.getText();
            String fullName = fullNameField.getText();
            int bankNum = Integer.parseInt(bankNumField.getText());
            String contract = contractField.getText();
            int wage = Integer.parseInt(wageField.getText());
            String role = roleField.getText();
            String branch = branchField.getText();
            String generic_Password = "123";

            if(role.equals("Driver")){
                managerController.AddNewWorker(Id,fullName, bankNum,contract,wage,generic_Password,licenceField.getText().charAt(0), Training.valueOf(trainingField.getText()));
            }
            else{
                managerController.AddNewWorker(Id,fullName,bankNum,contract,wage, Jobs.valueOf(role),generic_Password,branch);
            }
            // Display a success message and return to the previous window
            JOptionPane.showMessageDialog(this, "Worker added successfully to branch " + branchField.getText() + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
            save.setVisible(true); // show the main window
            this.dispose(); // close the current window

        }
        else if (e.getSource() == cancelButton) {
            //we show the main window
            save.setVisible(true);
            //close this window
            this.dispose();

        }
    }


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
            if (check) {
                // Change the color of the IdLabel to red
                idLabel.setForeground(Color.RED);
                return false;
            }
            // Change the color of the IdLabel to black
            idLabel.setForeground(Color.WHITE);
            return true;
        }
    }


    private void toggleFieldsVisibility(String job) {
        if (job.equals("Driver")) {
            trainingLabel.setVisible(true);
            trainingField.setVisible(true);
            trainingCombo.setVisible(true);
            licenceLabel.setVisible(true);
            licenceField.setVisible(true);
            licenceCombo.setVisible(true);
            branchLabel.setVisible(false);
            branchField.setVisible(false);
            branchCombo.setVisible(false);
        } else {
            trainingLabel.setVisible(false);
            trainingField.setVisible(false);
            trainingCombo.setVisible(false);
            licenceLabel.setVisible(false);
            licenceField.setVisible(false);
            licenceCombo.setVisible(false);
            branchLabel.setVisible(true);
            branchField.setVisible(true);
            branchCombo.setVisible(true);

            JPanel BranchPanel = new JPanel(new GridLayout(1, 2));
            BranchPanel.setBackground(Color.BLACK); // Set the panel background color to black
            BranchPanel.add(branchLabel);
            // todo: need to understand why the branches arent load
            branchCombo.setPreferredSize(branchField.getPreferredSize());
            BranchPanel.add(branchCombo);
            AddNewWorkerWin.add(BranchPanel,6);

        }
    }

}
