package Shipment.PresentationGUI;

import Shipment.Bussiness.shipmentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PrintItems extends JFrame implements ActionListener {
    private JPanel PrintItems;
    private JButton backButton;
    private JButton printButton;
    private JComboBox<String> comboBox;
    private shipmentManagement shipmentM;
    private OrderMenu save;


    public PrintItems(OrderMenu save) {
        shipmentM = shipmentManagement.getInstance();
        this.save = save;
        this.setContentPane(PrintItems);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(400,300));
        this.pack();
        this.setVisible(true);
        this.setTitle("order");
        printButton.addActionListener(this);
        backButton.addActionListener(this);
        comboBox.addActionListener(this);


    }

    private void createUIComponents() {
        shipmentM = shipmentManagement.getInstance();
        String[] d = shipmentM.getOrdersIDs();
        comboBox = new JComboBox<>(d);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==printButton)
        {
            StringBuilder message = shipmentM.getItems(Objects.requireNonNull(comboBox.getSelectedItem()).toString());
            JOptionPane.showMessageDialog(null, message.toString(), "Items", JOptionPane.INFORMATION_MESSAGE);

        }
        if(e.getSource() == backButton)
        {
            save.setVisible(true);
            this.dispose();
        }
    }
}
