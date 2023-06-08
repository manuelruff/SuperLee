package HR.PresentationGUI.HRManagaerWindows;

import HR.Bussiness.ManagerController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddNewWorker extends JFrame implements ActionListener {
    private JPanel AddNewWorkerWin;
    private ManagerController managerController;
    //save the window that opened us to show him after closing
    private UpdateEmployee save;
    private JLabel idLabel, fullNameLabel, bankNumLabel, contractLabel, wageLabel, roleLabel;
    private JTextField idField, fullNameField, bankNumField, contractField, wageField, roleField;
    private JButton addButton, cancelButton;

    public AddNewWorker(UpdateEmployee save) {
        this.save=save;
        this.managerController = ManagerController.getInstance();
        addButton.setEnabled(false);
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
    }

    private void updateAddButtonEnabledState() {
        // Enable the add button only if all text fields are non-empty
        boolean allFieldsFilled = !idField.getText().isEmpty() &&
                !fullNameField.getText().isEmpty() &&
                !bankNumField.getText().isEmpty() &&
                !contractField.getText().isEmpty() &&
                !wageField.getText().isEmpty() &&
                !roleField.getText().isEmpty();
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

        // Set the foreground color of each label to white
        idLabel.setForeground(Color.WHITE);
        fullNameLabel.setForeground(Color.WHITE);
        bankNumLabel.setForeground(Color.WHITE);
        contractLabel.setForeground(Color.WHITE);
        wageLabel.setForeground(Color.WHITE);
        roleLabel.setForeground(Color.WHITE);

        idField = new JTextField(10);
        fullNameField = new JTextField(10);
        bankNumField = new JTextField(10);
        contractField = new JTextField(10);
        wageField = new JTextField(10);
        roleField = new JTextField(10);

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
        RolePanel.add(roleField);
        AddNewWorkerWin.add(RolePanel);

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

        }
        else if (e.getSource() == cancelButton) {
            //we show the main window
            save.setVisible(true);
            //close this window
            this.dispose();

        }
    }
}
