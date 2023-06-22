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


    public PrintShipment(ShippingMenu save) {
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
            message = service.getAShipmentString(selectedItem);

            JTextArea textArea = new JTextArea(message.toString());
            textArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(500, 500));

            JOptionPane.showMessageDialog(null, scrollPane, "Shipment", JOptionPane.INFORMATION_MESSAGE);
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
        String[] d = service.getShipmentsIDs();
        for (String s : d) {
            comboBox.addItem(s);
        }
    }
}
