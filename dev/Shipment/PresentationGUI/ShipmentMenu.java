package Shipment.PresentationGUI;

import Shipment.Bussiness.shipmentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ShipmentMenu extends JFrame implements ActionListener {
    private JPanel shipMenu;
    private JComboBox<String> comboBox1;
    private JButton startButton;
    private JButton exitButton;
    private shipmentManagement sManagement;

    public ShipmentMenu() {
        this.setContentPane(shipMenu);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300, 200));
        this.pack();
        this.setVisible(true);
        this.setTitle("Shipping Menu");
        createUIComponents();
        comboBox1.addActionListener(this);
        exitButton.addActionListener(this);
        startButton.addActionListener(this);
        sManagement = shipmentManagement.getInstance();
    }



    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox1 = new JComboBox<String>();
        comboBox1.addItem("Add Shipment");
        comboBox1.addItem("Delete Shipment");
        comboBox1.addItem("Print all Shipments");
        comboBox1.addItem("Print available Shipments");
        comboBox1.addItem("Execute nearest Shipment");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) {
            if (Objects.equals(comboBox1.getSelectedItem(), "Add Shipment")) {

            }
            else if (Objects.equals(comboBox1.getSelectedItem(), "Delete Shipment")) {
                JTextField textField = new JTextField();
                int result = JOptionPane.showConfirmDialog(null, textField, "Enter Shipment id:", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String ID = textField.getText();
                    boolean check = sManagement.checkShipmentID(ID);
                    //if the Shipment exists
                    if (!check) {
                        JOptionPane.showMessageDialog(null, "Shipment id not found");
                    } else {
                        sManagement.deleteShipment(ID);
                        JOptionPane.showMessageDialog(null, "The shipment has been removed successfully!", "Deleted!", JOptionPane.INFORMATION_MESSAGE);
                    }


                }
                else if (Objects.equals(comboBox1.getSelectedItem(), "Print all Shipments")) {

                }
                else if (Objects.equals(comboBox1.getSelectedItem(), "Print available Shipments")) {

                }
                else if (Objects.equals(comboBox1.getSelectedItem(), "Execute nearest Shipment")) {

                }

            }

        }
    }
}
