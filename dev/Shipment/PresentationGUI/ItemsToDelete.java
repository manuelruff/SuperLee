package Shipment.PresentationGUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Shipment.Bussiness.shipmentManagement;
import Shipment.Service.GUIService;

public class ItemsToDelete extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel fieldLabel;
    private JTextField field;
    private JButton doButton;
    private shipmentManagement sManagement;
    private ShippingMenu save;
    private GUIService guiService;


    public ItemsToDelete(ShippingMenu shippingMenu, String siteName){
        save = shippingMenu;
        createUIComponents();
        setContentPane(panel);
        pack();
        setVisible(true);

        // finishing stuff
        doButton.addActionListener(this);
        field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    private void checkBox(){
        setEnabled(!field.getText().isEmpty());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == doButton) {

        }

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

        sManagement = shipmentManagement.getInstance();
        guiService = GUIService.getInstance();

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(1,2));
        fieldPanel.setBackground(Color.BLACK);
        fieldLabel = new JLabel("Quantity:");
        field = new JTextField(10);
        fieldLabel.setVisible(false);
        fieldLabel.setInputVerifier(new digitVerifier(fieldLabel));
        field.setVisible(false);
        fieldPanel.add(fieldLabel);
        fieldPanel.add(field);
        panel.add(fieldPanel);


        // setting the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,1));
        buttonPanel.setBackground(Color.BLACK);
        doButton = new JButton("Enter");
        buttonPanel.add(doButton);
        panel.add(buttonPanel);
    }

    class digitVerifier extends InputVerifier {
        private JLabel fieldLabel;

        public digitVerifier(JLabel fieldLabel) {
            this.fieldLabel = fieldLabel;
        }

        @Override
        public boolean verify(JComponent input) {
            String w = ((JTextField) input).getText();

            if (!w.matches("\\d+")) {
                // Change the color to red
                fieldLabel.setForeground(Color.RED);
                return false;
            }

            // Change the color to white
            fieldLabel.setForeground(Color.WHITE);
            return true;
        }
    }
}
