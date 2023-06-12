package Shipment.PresentationGUI;

import Shipment.Bussiness.shipmentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class TruckMenu  extends JFrame implements ActionListener {
    private JButton backButton;
    private JButton startButton;
    private JComboBox<String> comboBox1;
    private JPanel TruckMenu;
    private shipmentManagement shipmentM;
    private ShipManager save;
    public TruckMenu(ShipManager save)
    {
        this.save = save;
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
        if (e.getSource() == startButton) {
            if(Objects.equals(comboBox1.getSelectedItem(), "Add Truck")) {
                new AddTruck(this);
                this.setVisible(false);
            }

        }
        if (e.getSource() == backButton) {
            save.setVisible(true); // show the main window
            this.dispose(); // close the current window
        }
    }

}
