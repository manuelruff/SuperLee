package Shipment.PresentationGUI;

import Shipment.Bussiness.shipmentManagement;
import Shipment.Service.GUIService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PrintShipment extends JFrame implements ActionListener {
    private JButton backButton;
    private JPanel PrintShipment;
    private JButton printButton;
    private JComboBox<String> comboBox;
    private shipmentManagement shipmentM;
    private GUIService service;
    private ShippingMenu save;
    private int ch;


    public PrintShipment(ShippingMenu save,int ch) {
        shipmentM = shipmentManagement.getInstance();
        this.ch=ch;
        this.save = save;
        this.setContentPane(PrintShipment);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(400,300));
        this.pack();
        this.setVisible(true);
        this.setTitle("Shipments");
        printButton.addActionListener(this);
        backButton.addActionListener(this);
        comboBox.addActionListener(this);


    }

    private void createUIComponents() {
        shipmentM = shipmentManagement.getInstance();
        service = GUIService.getInstance();
        if(ch==1)
        {
            String[] d = service.getShipmentsIDs();
            comboBox = new JComboBox<>(d);
        }
        else {
            String[] d = service.getAShipmentsIDs();
            comboBox = new JComboBox<>(d);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==printButton)
        {
            if(ch ==1) {
                StringBuilder message = service.getShipmentString(Objects.requireNonNull(comboBox.getSelectedItem()).toString());
                JOptionPane.showMessageDialog(null, message.toString(), "Shipment", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                StringBuilder message = service.getAShipmentString(Objects.requireNonNull(comboBox.getSelectedItem()).toString());
                JOptionPane.showMessageDialog(null, message.toString(), "Shipment", JOptionPane.INFORMATION_MESSAGE);
            }

        }
        if(e.getSource() == backButton)
        {
            save.setVisible(true);
            this.dispose();
        }
    }
}
