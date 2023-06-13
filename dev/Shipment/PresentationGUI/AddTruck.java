package Shipment.PresentationGUI;

import javax.swing.*;
import Shipment.Bussiness.shipmentManagement;


import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddTruck extends JFrame implements ActionListener{
    private JPanel AddTruck;
    private TruckMenu save;
    private shipmentManagement shipmentM;
    private JTextField numberField, modelField, weightField, totalWeightField;
    private JLabel numberLabel, modelLabel, weightLabel, totalWeightLabel,storageLabel;
    private JComboBox<String> training;
    private JButton addButton, cancelButton;

    public AddTruck(TruckMenu save)
    {
        createUIComponents();
        this.save = save;
        this.shipmentM = shipmentManagement.getInstance();
        training.addActionListener(this);
        addButton.setEnabled(false);
        numberField.getDocument().addDocumentListener(new DocumentListener() {
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
        modelField.getDocument().addDocumentListener(new DocumentListener() {
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
        weightField.getDocument().addDocumentListener(new DocumentListener() {
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
        totalWeightField.getDocument().addDocumentListener(new DocumentListener() {
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
        boolean allFieldsFilled = !numberField.getText().isEmpty() &&
                !modelField.getText().isEmpty() &&
                !weightField.getText().isEmpty() &&
                !totalWeightField.getText().isEmpty();
        addButton.setEnabled(allFieldsFilled);
    }
    private void createUIComponents() {
        this.setTitle("Add New Truck");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the content pane background color to black
        getContentPane().setBackground(Color.BLACK);

        // Create the components
        numberLabel = new JLabel("Truck Number:");
        modelLabel = new JLabel("Model:");
        weightLabel = new JLabel("Truck Weight:");
        totalWeightLabel = new JLabel("Truck total carry weight:");
        storageLabel = new JLabel("Choose storage capability's: ");


        // Set the foreground color of each label to white
        numberLabel.setForeground(Color.WHITE);
        modelLabel.setForeground(Color.WHITE);
        weightLabel.setForeground(Color.WHITE);
        totalWeightLabel.setForeground(Color.WHITE);
        storageLabel.setForeground(Color.WHITE);


        numberField = new JTextField(10);
        modelField = new JTextField(10);
        weightField = new JTextField(10);
        totalWeightField = new JTextField(10);
        training = new JComboBox<>();
        training.addItem("Regular");
        training.addItem("Cooling");
        training.addItem("Freezer");

        // check name validate
        // Set the input verifier for the ID field
        numberField.setInputVerifier(new AddTruck.NumberVerifier(numberLabel));

        // check the phone number validate
        weightField.setInputVerifier(new AddTruck.weightVerifier(weightLabel));
        totalWeightField.setInputVerifier(new AddTruck.totalWeightVerifier(totalWeightLabel));

        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");
        addButton.setForeground(Color.BLACK);
        cancelButton.setForeground(Color.BLACK);

        // Create a panel and set the layout
        AddTruck = new JPanel();
        AddTruck.setLayout(new BoxLayout(AddTruck, BoxLayout.Y_AXIS));

        // Add the components to the panel
        JPanel NamePAnel = new JPanel(new GridLayout(1, 2));
        NamePAnel.setBackground(Color.BLACK); // Set the panel background color to black
        NamePAnel.add(numberLabel);
        NamePAnel.add(numberField);
        AddTruck.add(NamePAnel);

        JPanel AddressPAnel = new JPanel(new GridLayout(1, 2));
        AddressPAnel.setBackground(Color.BLACK); // Set the panel background color to black
        AddressPAnel.add(modelLabel);
        AddressPAnel.add(modelField);
        AddTruck.add(AddressPAnel);

        JPanel PhonePAnel = new JPanel(new GridLayout(1, 2));
        PhonePAnel.setBackground(Color.BLACK); // Set the panel background color to black
        PhonePAnel.add(weightLabel);
        PhonePAnel.add(weightField);
        AddTruck.add(PhonePAnel);


        JPanel ContactNamePAnel = new JPanel(new GridLayout(1, 2));
        ContactNamePAnel.setBackground(Color.BLACK); // Set the panel background color to black
        ContactNamePAnel.add(totalWeightLabel);
        ContactNamePAnel.add(totalWeightField);
        AddTruck.add(ContactNamePAnel);


        JPanel buttonPanel = new JPanel(new GridLayout(2, 1)); // Set the grid layout to 2 rows and 1 column
        buttonPanel.setBackground(Color.BLACK); // Set the panel background color to black
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        AddTruck.add(buttonPanel);

        // Set the maximum size of the buttons to fill the horizontal space
        Dimension maxButtonSize = new Dimension(Integer.MAX_VALUE, addButton.getPreferredSize().height);
        addButton.setMaximumSize(maxButtonSize);
        cancelButton.setMaximumSize(maxButtonSize);
        //addButton.setEnabled(false);

        // Set the action listeners for the buttons
        addButton.addActionListener(this);
        cancelButton.addActionListener(this);

        weightField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        // Set the content pane and display the window
        setContentPane(AddTruck);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 200));
        pack();
        setVisible(true);
    }
    class NumberVerifier extends InputVerifier {
        private JLabel numberLabel;

        public NumberVerifier(JLabel nameLabel) {
            this.numberLabel = nameLabel;
        }

        @Override
        public boolean verify(JComponent input) {
            // Get the ID field text
            String number = ((JTextField) input).getText();
            // Check if the Vendor exists
            boolean check = shipmentM.checkTruckNumber(number);
            if (check) {
                // Change the color of the IdLabel to red
                numberLabel.setForeground(Color.RED);
                return false;
            }
            // Change the color of the IdLabel to white
            numberLabel.setForeground(Color.WHITE);
            return true;
        }
    }
    class weightVerifier extends InputVerifier {
        private JLabel weight;

        public weightVerifier(JLabel phoneNumberLabel) {
            this.weight = weightLabel;
        }

        @Override
        public boolean verify(JComponent input) {
            String w = ((JTextField) input).getText();

            // Remove all non-digit characters from the phone number
            String digitsOnly = w.replaceAll("\\D", "");

            if (Integer.parseInt(digitsOnly) < 0) {
                // Change the color of the phoneNumberLabel to red
                weight.setForeground(Color.RED);
                return false;
            }

            // Change the color of the phoneNumberLabel to white
            weight.setForeground(Color.WHITE);
            return true;
        }
    }
    class totalWeightVerifier extends InputVerifier {
        private JLabel totalWeight;

        public totalWeightVerifier(JLabel phoneNumberLabel) {
            this.totalWeight = totalWeightLabel;
        }

        @Override
        public boolean verify(JComponent input) {
            String w = ((JTextField) input).getText();

            // Remove all non-digit characters from the phone number
            String digitsOnly = w.replaceAll("\\D", "");

            if (Integer.parseInt(digitsOnly) < 0) {
                // Change the color of the phoneNumberLabel to red
                totalWeight.setForeground(Color.RED);
                return false;
            }

            // Change the color of the phoneNumberLabel to white
            totalWeight.setForeground(Color.WHITE);
            return true;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String number = numberField.getText();
            String model = modelField.getText();
            String weight = weightField.getText();
            String totalWeight = totalWeightField.getText();
            shipmentM.addTruck(number,Integer.parseInt(totalWeight),Integer.parseInt(weight),model,1);
            // Display a success message and return to the previous window
            JOptionPane.showMessageDialog(this, "Truck added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
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
