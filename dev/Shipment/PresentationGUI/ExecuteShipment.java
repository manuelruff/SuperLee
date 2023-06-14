package Shipment.PresentationGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Shipment.Bussiness.shipmentManagement;

public class ExecuteShipment extends JFrame implements ActionListener {
    private JPanel panel;
    private JComboBox<String> comboBox;
    private JLabel label;
    private JButton doButton;
    private shipmentManagement sManagement;
    private ShippingMenu save;

    public ExecuteShipment(ShippingMenu shippingMenu){
        sManagement = shipmentManagement.getInstance();
        createUIComponents();
        save = shippingMenu;


    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.setTitle("Execute Shipment");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 200));

        // setting the main panel.
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);

        // setting the combo box
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new GridLayout(1,1));
        comboPanel.setBackground(Color.BLACK);
        String[] options = {"Exchange Truck", "Delete Item From Shipment", "Delete Last Site"};
        comboBox = new JComboBox<>(options);
        comboPanel.add(comboBox);
        panel.add(comboPanel);

        // setting the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,1));
        buttonPanel.setBackground(Color.BLACK);
        doButton = new JButton("DO");
        buttonPanel.add(doButton);
        panel.add(buttonPanel);

        // finishing stuff
        setContentPane(panel);
        pack();
        setVisible(true);
        comboBox.addActionListener(this);
        doButton.addActionListener(this);
    }

}
