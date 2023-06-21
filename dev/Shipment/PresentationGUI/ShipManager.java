package Shipment.PresentationGUI;

import Shipment.Bussiness.shipmentManagement;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
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
                new SiteMenu(this);
                this.setVisible(false);
            }
            else if(Objects.equals(comboBox1.getSelectedItem(), "Truck Menu")){
                new TruckMenu(this);
                this.setVisible(false);
            }
            else if (Objects.equals(comboBox1.getSelectedItem(), "Drivers Menu")) {
                new DriverMenu(this);
                this.setVisible(false);
            }
            if (Objects.equals(comboBox1.getSelectedItem(), "Order Menu")){
                new OrderMenu(this);
                this.setVisible(false);
            }
            else if (Objects.equals(comboBox1.getSelectedItem(), "Shipping Menu")){
                new ShippingMenu(this);
                this.setVisible(false);
            }
            else if (Objects.equals(comboBox1.getSelectedItem(), "Print all shipped item docs")){
                // TODO print all docs
                new PrintItemDocs(this);
                this.setVisible(false);
            }
        }
        else if(e.getActionCommand().equals("Exit"))
        {
            //todo connect to DB
            //sManagement.closeDB();
            System.exit(0);
        }
    }
}
