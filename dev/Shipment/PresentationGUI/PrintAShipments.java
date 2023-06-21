package Shipment.PresentationGUI;

import Shipment.Service.GUIService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PrintAShipments extends JFrame implements ActionListener {
    private JPanel PrintAShipments;
    private JButton backButton;
    private JButton printButton;
    private JComboBox<String> comboBox;
    private GUIService service;
    private ShippingMenu save;



    public PrintAShipments(ShippingMenu save) {
        createUIComponents();
        this.save = save;
        this.setContentPane(PrintAShipments);
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
        comboBox = new JComboBox<>();
        service = GUIService.getInstance();
        String[] d = service.getAShipmentsIDs();
        for (String s : d) {
            comboBox.addItem(s);
        }
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
            if(save!=null){
                save.setVisible(true);
            }
            this.dispose();
        }
    }
}
