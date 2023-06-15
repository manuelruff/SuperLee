package Shipment.PresentationGUI;

import Shipment.Bussiness.shipmentManagement;
import Shipment.Service.GUIService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Objects;

public class ExecuteShipment extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel label;
    private JTextField weightField;
    private JButton doButton;
    private int firstWeight;

    private shipmentManagement sManagement;
    private ShippingMenu shippingMenu;

    private GUIService guiService;
    ExecuteShipment(ShippingMenu save){
        createUIComponents();
        this.shippingMenu = save;
        setContentPane(panel);
        pack();
        setVisible(true);
        sManagement = shipmentManagement.getInstance();
        guiService = GUIService.getInstance();
        doButton.addActionListener(this);
        doButton.setEnabled(false);
        weightField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = weightField.getText();
                weightField.setBackground(Color.WHITE);
                int weight;
                try{
                    weight = Integer.parseInt(input);
                }
                catch (NumberFormatException ignored){
                    weightField.setBackground(Color.RED);
                    return;
                }
                if(weight > firstWeight){

                }
            }
        });

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        panel = new JPanel();
        this.setTitle("Execute Shipment");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 200));

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);

        // initialize the enters field
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(2, 1));
        fieldPanel.setBackground(Color.BLACK);
        label = new JLabel("Truck Weight:");
        weightField = new JTextField(10);
        label.setForeground(Color.white);
        fieldPanel.add(label);
        fieldPanel.add(weightField);
        panel.add(fieldPanel);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setLayout(new GridLayout(1,1));
        emptyPanel.setBackground(Color.BLACK);
        panel.add(emptyPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 1));
        doButton = new JButton("Enter");
        buttonPanel.add(doButton);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == doButton){
            int weight = Integer.parseInt(weightField.getText());
            if (sManagement.checkTruckWeight(weight)) {
                String[] options = {"Exchange Truck", "Delete Items From Shipment", "Delete Last Site"};
                JComboBox<String> comboBoxWeight = new JComboBox<>(options);
                comboBoxWeight.addActionListener(this);

                Object[] message = {
                        "Choose an option:",
                        comboBoxWeight
                };
                int result = JOptionPane.showOptionDialog(null, message, "Options to Follow", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (result == JOptionPane.OK_OPTION) {
                    if (Objects.equals(comboBoxWeight.getSelectedItem(), "Delete Items From Shipment")) {
                        JComboBox<String> comboBoxSites = new JComboBox<>(guiService.getSitesOfShipmentData());
                        comboBoxSites.addActionListener(this);
                        Object[] pop = {
                                "Choose an option:",
                                comboBoxSites
                        };

                        result = JOptionPane.showOptionDialog(null, pop, "Items to Delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                        if (result == JOptionPane.OK_OPTION) {
                            String siteName = Objects.requireNonNull(comboBoxSites.getSelectedItem()).toString();
                            new ItemsToDelete(shippingMenu, siteName);
                            this.setVisible(false);
                        }
                    }
                    else if (Objects.equals(comboBoxWeight.getSelectedItem(), "Exchange Truck")) {
                        if (sManagement.changeTruck()) {
                            JOptionPane.showMessageDialog(this, "Truck Exchanged Successfully", "Success!", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(this, "There is No Available truck at the moment", "Failure!", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else if (Objects.equals(comboBoxWeight.getSelectedItem(), "Delete Last Site")) {
                        if (sManagement.removeLastSiteFromShipment()) {
                            JOptionPane.showMessageDialog(this, "Site removed Successfully", "Success!", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(this, "There is only 1 site in the shipment", "Failure!", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                }

            }
            else {
                JOptionPane.showMessageDialog(this, "The shipment has been executed successfully.", "finished", JOptionPane.INFORMATION_MESSAGE);
                //todo check this
                LocalTime time = LocalTime.now();
                sManagement.updateShipment(time);
                shippingMenu.setVisible(true);
                this.dispose();
            }
        }
    }
}
