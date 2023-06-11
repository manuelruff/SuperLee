package Shipment.PresentationGUI;

import Shipment.Bussiness.Order;
import Shipment.Bussiness.shipmentManagement;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ShipManager extends JFrame implements ActionListener{
    private JPanel ShipManager;
    private JButton exitButton;
    private JButton startButton;
    private JComboBox<String> comboBox1;

    private shipmentManagement sManagement;

    public ShipManager()
    {
        this.setContentPane(ShipManager);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,200));
        this.pack();
        this.setVisible(true);
        this.setTitle("Ship Manager");
        comboBox1.addActionListener(this);
        exitButton.addActionListener(this);
        startButton.addActionListener(this);
        sManagement = shipmentManagement.getInstance();


    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox1 = new JComboBox<>();
        comboBox1.addItem("Site Menu");
        comboBox1.addItem("Truck Menu");
        comboBox1.addItem("Drivers Menu");
        comboBox1.addItem("Order Menu");
        comboBox1.addItem("Shipping Menu");
        comboBox1.addItem("Print all shipped item docs");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) {
            if (Objects.equals(comboBox1.getSelectedItem(), "Site Menu")){
                new SiteMenu();
                this.setVisible(false);
            }
            else if(Objects.equals(comboBox1.getSelectedItem(), "Truck Menu")){
                new TruckMenu();
                this.setVisible(false);
            }
            else if (Objects.equals(comboBox1.getSelectedItem(), "Drivers Menu")) {
                new DriverMenu();
                this.setVisible(false);
            }
            else if (Objects.equals(comboBox1.getSelectedItem(), "Order Menu")){
                new OrderMenu();
                this.setVisible(false);
            }
            else if (Objects.equals(comboBox1.getSelectedItem(), "Shipping Menu")){
                new ShipmentMenu();
                this.setVisible(false);
            }
            else if (Objects.equals(comboBox1.getSelectedItem(), "Print all shipped item docs")){
                // TODO print all docs
            }
        }
        else if(e.getActionCommand().equals("Exit"))
        {
            System.exit(0);
        }
    }
}
