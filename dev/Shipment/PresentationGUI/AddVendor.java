    package Shipment.PresentationGUI;

    import HR.Bussiness.ManagerController;
    import HR.Bussiness.Zone;
    import HR.PresentationGUI.HRManagaerWindows.AddNewSuper;
    import HR.PresentationGUI.HRManager;
    import Shipment.Bussiness.shipmentManagement;

    import javax.swing.*;
    import javax.swing.event.DocumentEvent;
    import javax.swing.event.DocumentListener;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.KeyAdapter;
    import java.awt.event.KeyEvent;

    public class AddVendor extends JFrame implements ActionListener {
    private JPanel AddVendor ;
    private SiteMenu save;
    private shipmentManagement shipmentM;
    private JTextField nameField,addressField,phoneField,contactNameField;
    private JLabel nameLabel,addressLabel,phoneLabel,contactNameLabel;
    private JButton addButton, cancelButton;

    public AddVendor(SiteMenu save)
    {
        createUIComponents();
        this.save = save;
        this.shipmentM = shipmentManagement.getInstance();
        addButton.setEnabled(false);
        nameField.getDocument().addDocumentListener(new DocumentListener() {
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
        addressField.getDocument().addDocumentListener(new DocumentListener() {
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
        phoneField.getDocument().addDocumentListener(new DocumentListener() {
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
        contactNameField.getDocument().addDocumentListener(new DocumentListener() {
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
        this.setVisible(true);


    }
    private void updateAddButtonEnabledState() {
        // Enable the add button only if all text fields are non-empty
        boolean allFieldsFilled = !nameField.getText().isEmpty() &&
                !addressField.getText().isEmpty() &&
                !phoneField.getText().isEmpty() &&
                !contactNameField.getText().isEmpty();
        addButton.setEnabled(allFieldsFilled);
    }
    private void createUIComponents() {
        this.setTitle("Add New Vendor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the content pane background color to black
        getContentPane().setBackground(Color.BLACK);

        // Create the components
        nameLabel = new JLabel("Name:");
        addressLabel = new JLabel("Address:");
        phoneLabel = new JLabel("Phone:");
        contactNameLabel = new JLabel("Contact Name:");

        // Set the foreground color of each label to white
        nameLabel.setForeground(Color.WHITE);
        addressLabel.setForeground(Color.WHITE);
        phoneLabel.setForeground(Color.WHITE);
        contactNameLabel.setForeground(Color.WHITE);


        nameField = new JTextField(10);
        addressField = new JTextField(10);
        phoneField = new JTextField(10);
        contactNameField = new JTextField(10);

        // check name validate
        // Set the input verifier for the ID field
        nameField.setInputVerifier(new AddVendor.NameVerifier(nameLabel));

        // check the phone number validate
        phoneField.setInputVerifier(new AddVendor.PhoneNumberVerifier(phoneLabel));

        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");
        addButton.setForeground(Color.BLACK);
        cancelButton.setForeground(Color.BLACK);

        // Create a panel and set the layout
        AddVendor = new JPanel();
        AddVendor.setLayout(new BoxLayout(AddVendor, BoxLayout.Y_AXIS));

        // Add the components to the panel
        JPanel NamePAnel = new JPanel(new GridLayout(1, 2));
        NamePAnel.setBackground(Color.BLACK); // Set the panel background color to black
        NamePAnel.add(nameLabel);
        NamePAnel.add(nameField);
        AddVendor.add(NamePAnel);

        JPanel AddressPAnel = new JPanel(new GridLayout(1, 2));
        AddressPAnel.setBackground(Color.BLACK); // Set the panel background color to black
        AddressPAnel.add(addressLabel);
        AddressPAnel.add(addressField);
        AddVendor.add(AddressPAnel);

        JPanel PhonePAnel = new JPanel(new GridLayout(1, 2));
        PhonePAnel.setBackground(Color.BLACK); // Set the panel background color to black
        PhonePAnel.add(phoneLabel);
        PhonePAnel.add(phoneField);
        AddVendor.add(PhonePAnel);

        JPanel ContactNamePAnel = new JPanel(new GridLayout(1, 2));
        ContactNamePAnel.setBackground(Color.BLACK); // Set the panel background color to black
        ContactNamePAnel.add(contactNameLabel);
        ContactNamePAnel.add(contactNameField);
        AddVendor.add(ContactNamePAnel);


        JPanel buttonPanel = new JPanel(new GridLayout(2, 1)); // Set the grid layout to 2 rows and 1 column
        buttonPanel.setBackground(Color.BLACK); // Set the panel background color to black
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        AddVendor.add(buttonPanel);

        // Set the maximum size of the buttons to fill the horizontal space
        Dimension maxButtonSize = new Dimension(Integer.MAX_VALUE, addButton.getPreferredSize().height);
        addButton.setMaximumSize(maxButtonSize);
        cancelButton.setMaximumSize(maxButtonSize);
        //addButton.setEnabled(false);

        // Set the action listeners for the buttons
        addButton.addActionListener(this);
        cancelButton.addActionListener(this);

        phoneField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        // Set the content pane and display the window
        setContentPane(AddVendor);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 200));
        pack();
        setVisible(true);


    }
    class NameVerifier extends InputVerifier {
        private JLabel nameLabel;

        public NameVerifier(JLabel nameLabel) {
            this.nameLabel = nameLabel;
        }

        @Override
        public boolean verify(JComponent input) {
            // Get the ID field text
            String name = ((JTextField) input).getText();
            // Check if the Vendor exists
            boolean check = shipmentM.checkVendor(name);
            if (check) {
                // Change the color of the IdLabel to red
                nameLabel.setForeground(Color.RED);
                return false;
            }
            // Change the color of the IdLabel to white
            nameLabel.setForeground(Color.WHITE);
            return true;
        }
    }
        class PhoneNumberVerifier extends InputVerifier {
            private JLabel phoneNumberLabel;

            public PhoneNumberVerifier(JLabel phoneNumberLabel) {
                this.phoneNumberLabel = phoneNumberLabel;
            }

            @Override
            public boolean verify(JComponent input) {
                String phoneNumber = ((JTextField) input).getText();

                // Remove all non-digit characters from the phone number
                String digitsOnly = phoneNumber.replaceAll("\\D", "");

                if (digitsOnly.length() != 10) {
                    // Change the color of the phoneNumberLabel to red
                    phoneNumberLabel.setForeground(Color.RED);
                    return false;
                }

                // Change the color of the phoneNumberLabel to white
                phoneNumberLabel.setForeground(Color.WHITE);
                return true;
            }
        }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String siteName = nameField.getText();
            String siteAddress = addressField.getText();
            String sitePhoneNumber = phoneField.getText();
            String contactName = contactNameField.getText();
            shipmentM.addVendor(siteName,siteAddress,sitePhoneNumber,contactName);
            // Display a success message and return to the previous window
            JOptionPane.showMessageDialog(this, "Vendor added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
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
}
