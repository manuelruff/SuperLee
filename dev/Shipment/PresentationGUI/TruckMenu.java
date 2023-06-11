package Shipment.PresentationGUI;

import Shipment.Bussiness.shipmentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TruckMenu  extends JFrame implements ActionListener {
    private JButton backButton;
    private JButton startButton;
    private JComboBox<String> comboBox1;
    private JPanel TruckMenu;
    private shipmentManagement shipmentM;
    public TruckMenu()
    {
        this.setContentPane(TruckMenu);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,200));
        this.pack();
        this.setVisible(true);
        this.setTitle("Truck Menu");
        startButton.addActionListener(this);
        backButton.addActionListener(this);
        comboBox1.addActionListener(this);
        shipmentM = shipmentManagement.getInstance();
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox1 = new JComboBox<>();
        comboBox1.addItem("Add Truck");
        comboBox1.addItem("Print All Trucks");

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
