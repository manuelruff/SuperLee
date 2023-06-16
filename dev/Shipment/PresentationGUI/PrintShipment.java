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


    public PrintShipment(ShippingMenu save,int ch1) {
        shipmentM = shipmentManagement.getInstance();
        this.ch=ch1;
        createUIComponents();
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


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == printButton) {
            String selectedItem = Objects.requireNonNull(comboBox.getSelectedItem()).toString();
            StringBuilder message;
            if (ch == 1 ) {
                String[] d = service.getShipmentsIDs();
                comboBox.removeAllItems();
                for (String s : d) {
                    comboBox.addItem(s);
                }
                message = service.getShipmentString(selectedItem);
            } else {
                String[] d = service.getAShipmentsIDs();
                comboBox.removeAllItems();
                for (String s : d) {
                    comboBox.addItem(s);
                }
                message = service.getAShipmentString(selectedItem);
            }
            JOptionPane.showMessageDialog(null, message.toString(), "Shipment", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == backButton) {
            save.setVisible(true);
            this.dispose();
        }
    }

    private void createUIComponents() {
        shipmentM = shipmentManagement.getInstance();
        comboBox = new JComboBox<>();
        service = GUIService.getInstance();
        if (ch == 1 ) {
            String[] d = service.getShipmentsIDs();

            for (String s : d) {
                comboBox.addItem(s);
            }
        } else {
            String[] d = service.getAShipmentsIDs();
            for (String s : d) {
                comboBox.addItem(s);
            }
        }
        comboBox.setForeground(Color.BLACK);
//        if(ch==1)
//        {
//            String[] d = service.getShipmentsIDs();
//            comboBox = new JComboBox<>(d);
//        }
//        else {
//            String[] d = service.getAShipmentsIDs();
//            comboBox = new JComboBox<>(d);
//        }
    }
}
