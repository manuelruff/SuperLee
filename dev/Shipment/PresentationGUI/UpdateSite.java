package Shipment.PresentationGUI;

import Shipment.Bussiness.shipmentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSite extends JFrame implements ActionListener {

    private JComboBox<String> comboBox;
    private JTextField textField;
    private JButton startButton;
    private JPanel UpdateSite;
    private JButton cancelButton;
    private JTextField textField1;
    private shipmentManagement shipmentM;
    private SiteMenu save;


    public UpdateSite(SiteMenu save) throws HeadlessException {
        // Set the content pane of the JFrame to the JPanel UpdateSite
        this.save = save;
        this.setContentPane(UpdateSite);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,200));
        this.pack();
        this.setVisible(true);
        this.setTitle("Site Menu");
        textField.addActionListener(this);
        startButton.addActionListener(this);
        cancelButton.addActionListener(this);
        comboBox.addActionListener(this);
        textField1.addActionListener(this);
        shipmentM = shipmentManagement.getInstance();



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks
        if (e.getSource() == startButton) {
            shipmentM.addVendor("branch1","beerSheva","0507175128","roee");
            if(shipmentM.checkVendor(textField.getText()))
            {
                textField.setBackground(Color.white);
                if (comboBox.getSelectedItem().equals("Site address")) {
                    String newAddress = textField.getText();
                    shipmentM.updateSite(textField.getText(),textField1.getText(),1);
                    JOptionPane.showMessageDialog(this, "Vendor information changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    save.setVisible(true); // show the main window
                    this.dispose(); // close the current window
                } else if (comboBox.getSelectedItem().equals("Contact name")) {
                    String newAddress = textField.getText();
                    shipmentM.updateSite(textField.getText(),textField1.getText(),2);
                    JOptionPane.showMessageDialog(this, "Vendor information changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    save.setVisible(true); // show the main window
                    this.dispose(); // close the current window
                }
                else {
                    String phoneNumber = textField.getText();
                    // Remove all non-digit characters from the phone number
                    String digitsOnly = phoneNumber.replaceAll("\\D", "");

                    if (digitsOnly.length() != 10) {
                        // Change the color of the phoneNumberLabel to red
                        textField1.setBackground(Color.RED);
                    }
                    else {
                        textField1.setBackground(Color.WHITE);
                        shipmentM.updateSite(textField.getText(), textField1.getText(), 3);
                        JOptionPane.showMessageDialog(this, "Vendor information changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        save.setVisible(true); // show the main window
                        this.dispose(); // close the current window
                    }
                }
            }
            else
            {
                textField.setBackground(Color.RED);
            }
        }
        if (e.getSource() == cancelButton) {
            save.setVisible(true); // show the main window
            this.dispose(); // close the current window
        }
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox = new JComboBox<>();
        comboBox.addItem("Site address");
        comboBox.addItem("Contact name");
        comboBox.addItem("Contact phone number");
    }
}
