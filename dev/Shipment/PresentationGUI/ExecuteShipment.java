package Shipment.PresentationGUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Objects;
import java.util.concurrent.Flow;

import Shipment.Bussiness.shipmentManagement;
import Shipment.Service.GUIService;

public class ExecuteShipment extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel fieldLabel;
    private JTextField field;
    private JButton doButton;
    private shipmentManagement sManagement;
    private ShippingMenu save;
    private GUIService guiService;
    private int firstWeight;

    public ExecuteShipment(ShippingMenu shippingMenu) {
        save = shippingMenu;
        firstWeight = Integer.MAX_VALUE;
        createUIComponents();
        setContentPane(panel);
        pack();
        setVisible(true);

        // finishing stuff
        doButton.addActionListener(this);
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkBox();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkBox();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkBox();
            }
        });
        field.addActionListener(this);
        doButton.setEnabled(false);
    }

    private void checkBox() {
        doButton.setEnabled(!field.getText().isEmpty());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == doButton) {
            int weight;
            try {
                weight = Integer.parseInt(field.getText());
            }
            catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(this, "invalid Number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (weight > firstWeight){
                JOptionPane.showMessageDialog(this, "The weight is higher then the last weight", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
            else{
                firstWeight = weight;
                if (sManagement.checkTruckWeight(weight)) {
                    String[] options = {"Exchange Truck", "Delete Items From Shipment", "Delete Last Site"};
                    JComboBox<String> comboBoxWeight = new JComboBox<>(options);
                    comboBoxWeight.addActionListener(this);

                    Object[] message = {
                            "Choose an option:",
                            comboBoxWeight
                    };

                    int result = JOptionPane.showOptionDialog(null, message, "Pop-up Window", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                    if (result == JOptionPane.OK_OPTION) {
                        if (Objects.equals(comboBoxWeight.getSelectedItem(), "Delete Items From Shipment")) {
                            JComboBox<String> comboBoxSites = new JComboBox<>(guiService.getSitesOfShipmentData());
                            comboBoxSites.addActionListener(this);
                            Object[] pop = {
                                    "Choose an option:",
                                    comboBoxSites
                            };

                            result = JOptionPane.showOptionDialog(null, pop, "Items to Delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                            if (result == JOptionPane.OK_OPTION) {
                                String siteName = Objects.requireNonNull(comboBoxSites.getSelectedItem()).toString();
                                new ItemsToDelete(this, siteName);
                                this.setVisible(false);
                            }
                        }
                        else if (Objects.equals(comboBoxWeight.getSelectedItem(), "Exchange Truck")) {
                            if (sManagement.changeTruck()) {
                                JOptionPane.showMessageDialog(this, "Truck Exchanged Successfully", "Success!", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else {
                                JOptionPane.showMessageDialog(this, "There is No Available truck at the moment", "Failure!", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        else if (Objects.equals(comboBoxWeight.getSelectedItem(), "Delete Last Site")) {
                            if (sManagement.removeLastSiteFromShipment()) {
                                JOptionPane.showMessageDialog(this, "Site removed Successfully", "Success!", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else {
                                JOptionPane.showMessageDialog(this, "There is only 1 site in the shipment", "Failure!", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }

                        }
                    }
                else {
                    JOptionPane.showMessageDialog(this, "The shipment has been executed successfully.", "finished", JOptionPane.INFORMATION_MESSAGE);
                    //todo check this
                    LocalTime time = LocalTime.now();
                    sManagement.updateShipment();
                    save.setVisible(true);
                    this.dispose();
                }
            }
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.setTitle("Execute Shipment");
        Dimension dimension = new Dimension(1, 1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 200));

        // setting the main panel.
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);

        sManagement = shipmentManagement.getInstance();
        guiService = GUIService.getInstance();

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        fieldPanel.setBackground(Color.BLACK);
        fieldLabel = new JLabel("Truck Weight:");
        fieldLabel.setForeground(Color.white);
        field = new JTextField();
        field.setPreferredSize(new Dimension(300, field.getPreferredSize().height)); // Set a narrower width for the text field
        field.setInputVerifier(new digitVerifier(field));
        fieldPanel.add(fieldLabel);
        fieldPanel.add(field);
        panel.add(fieldPanel);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setLayout(new GridLayout(0, 1));
        emptyPanel.setBackground(Color.BLACK);
        panel.add(emptyPanel);


        // setting the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 1));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setPreferredSize(dimension);
        doButton = new JButton("Enter");
        doButton.setPreferredSize(dimension);
        buttonPanel.add(doButton);
        panel.add(buttonPanel);
    }
    class digitVerifier extends InputVerifier {
        private JTextField fieldLabel;

        public digitVerifier(JTextField fieldLabel) {
            this.fieldLabel = fieldLabel;
        }

        @Override
        public boolean verify(JComponent input) {
            String w = ((JTextField) input).getText();
            fieldLabel.setForeground(Color.BLACK);
            if (!w.matches("\\d+")) {
                // Change the color to red
                return false;
            }

            // Change the color to white
            fieldLabel.setForeground(Color.BLACK);
            return true;
        }
    }
}