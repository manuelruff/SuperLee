package Shipment.PresentationGUI;
import Shipment.Bussiness.Vendor;
import Shipment.Bussiness.shipmentManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class AddShipment extends JFrame implements ActionListener {
    // need to have date, from current date onward. using the comboBox.
    // need to have ID label.
    private JPanel panel;

    private JTextField IDText, vendorText;
    private ShippingMenu save;
    private JLabel IDLabel, vendorLabel;
    private JComboBox<String> comboBox;
    private JButton doButton;
    private JButton cancelButton;
    private List<LocalDate> daysOfWeek;
    private  shipmentManagement sManagement;
    private  int c;
    public AddShipment(ShippingMenu shippingMenu, int choice){
        c = choice;
        createUIComponents();
        save = shippingMenu;
        setContentPane(panel);
        pack();
        setVisible(true);
        sManagement = shipmentManagement.getInstance();
        doButton.setEnabled(false);
        IDText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {updateAddButtonEnabledState();}

            @Override
            public void removeUpdate(DocumentEvent e) {updateAddButtonEnabledState();}

            @Override
            public void changedUpdate(DocumentEvent e) {updateAddButtonEnabledState();}
        });

        vendorText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {updateAddButtonEnabledState();}

            @Override
            public void removeUpdate(DocumentEvent e) {updateAddButtonEnabledState();}

            @Override
            public void changedUpdate(DocumentEvent e) {updateAddButtonEnabledState();}
        });

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        functionX(c);
        this.setTitle("ADD SHIPMENT");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 200));

        // setting the main panel.
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);

        // setting the comboBox
        String[] option = new String[daysOfWeek.size()];
        for(int i=0; i < daysOfWeek.size(); i++){
            option[i] = daysOfWeek.get(i).toString();
        }

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setBackground(Color.BLACK);
        comboBoxPanel.setLayout(new GridLayout(1,1));

        // Add the combo box to the combo box panel
        comboBox = new JComboBox<>(option);
        comboBoxPanel.add(comboBox);
        panel.add(comboBoxPanel);
        comboBox.addActionListener(this);

        // label 1
        JPanel labelPanel1 = new JPanel();
        labelPanel1.setBackground(Color.BLACK);
        labelPanel1.setLayout(new GridLayout(1,2));
        IDLabel = new JLabel("ID:");
        IDLabel.setForeground(Color.WHITE);
        labelPanel1.add(IDLabel);
        IDText = new JTextField(10);
        labelPanel1.add(IDText);
        panel.add(labelPanel1);
        IDText.setInputVerifier(new NumberVerifier(IDLabel));

        //label2
        JPanel labelPanel2 = new JPanel();
        labelPanel2.setBackground(Color.BLACK);
        labelPanel2.setLayout(new GridLayout(1,2));
        vendorLabel = new JLabel("Vendor:");
        labelPanel2.add(vendorLabel);
        vendorLabel.setForeground(Color.WHITE);
        vendorText = new JTextField(10);
        labelPanel2.add(vendorText);
        vendorText.setInputVerifier(new VendorVerifier(vendorLabel));
        panel.add(labelPanel2);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.BLACK);
        emptyPanel.setLayout(new GridLayout(1,1));
        panel.add(emptyPanel);



        // setting the buttons
        JPanel buttonPanel =  new JPanel(new GridLayout(2,1));
        doButton = new JButton("ADD");
        cancelButton = new JButton("Cancel");
        buttonPanel.setBackground(Color.BLACK); // Set the panel background color to black
        buttonPanel.add(doButton);
        buttonPanel.add(cancelButton);
        panel.add(buttonPanel);
        doButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ADD")){
            int index = comboBox.getSelectedIndex();
            int day = convertFunc(daysOfWeek.get(index).getDayOfWeek().ordinal());
            String ID = IDText.getText();
            String vendor = vendorText.getText();
            if (sManagement.createShipment(day,daysOfWeek.get(index),ID,vendor)){
                JOptionPane.showMessageDialog(this, "Shipment added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(this, "Shipment Can't be created", "Failed", JOptionPane.INFORMATION_MESSAGE);
            }

        }
        else if (e.getActionCommand().equals("Cancel")){
            dispose();
            save.setVisible(true);
        }
    }

    private void functionX(int choice){
        daysOfWeek = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate closestSunday = today.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        LocalDate nextFriday = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        if(choice == 0){
            while(!today.equals(nextFriday)){
                today = today.plusDays(1);
                daysOfWeek.add(today);
            }
        }
        else{
            LocalDate current = closestSunday;
            for(int i=0; i < 6; i++){
                daysOfWeek.add(current);
                current = current.plusDays(1);
            }
        }
    }
    private void updateAddButtonEnabledState() {
        // Enable the add button only if all text fields are non-empty
        boolean allFieldsFilled = !IDText.getText().isEmpty() &&
                !vendorText.getText().isEmpty();
        doButton.setEnabled(allFieldsFilled);
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
            boolean check = sManagement.checkShipmentID(number);
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

    class VendorVerifier extends InputVerifier {
        private JLabel vendorLabel;

        public VendorVerifier(JLabel nameLabel) {
            this.vendorLabel = nameLabel;
        }

        @Override
        public boolean verify(JComponent input) {
            // Get the ID field text
            String name = ((JTextField) input).getText();
            // Check if the Vendor exists
            boolean check = sManagement.checkVendor(name);
            if (!check) {
                // Change the color of the IdLabel to red
                vendorLabel.setForeground(Color.RED);
                doButton.setEnabled(false);
                return false;
            }
            // Change the color of the IdLabel to white
            vendorLabel.setForeground(Color.WHITE);
            return true;
        }
    }

    private int convertFunc(int curr)
    {
        if (curr == 6)
            return 0;
        else{
            return curr + 1;
        }
    }

}
