package Shipment.PresentationGUI;

import Shipment.Bussiness.shipmentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PrintItemDocs extends JFrame implements ActionListener {
    private JPanel PrintItemDocs;
    private JButton backButton;
    private JButton printButton;
    private JComboBox<String> comboBox;
    private ShippingMenu save;
    private shipmentManagement shipmentM;

    public PrintItemDocs(ShippingMenu save) {
        shipmentM = shipmentManagement.getInstance();
        this.save = save;
        this.setContentPane(PrintItemDocs);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,300));
        this.pack();
        this.setVisible(true);
        this.setTitle("Item Docs");
        printButton.addActionListener(this);
        backButton.addActionListener(this);
        comboBox.addActionListener(this);


    }

    private void createUIComponents() {
        shipmentM = shipmentManagement.getInstance();
        String[] ids = shipmentM.getItemDocsIDs();
        comboBox = new JComboBox<>(ids);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==printButton)
        {
            StringBuilder message = shipmentM.getItemDocString(Objects.requireNonNull(comboBox.getSelectedItem()).toString());
            JOptionPane.showMessageDialog(null, message.toString(), "Items", JOptionPane.INFORMATION_MESSAGE);

        }
        if(e.getSource() == backButton)
        {
            save.setVisible(true);
            this.dispose();
        }


    }
}
