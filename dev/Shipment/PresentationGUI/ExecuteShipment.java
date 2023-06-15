package Shipment.PresentationGUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Shipment.Bussiness.shipmentManagement;
import Shipment.Service.GUIService;

public class ExecuteShipment extends JFrame implements ActionListener {
    private JPanel panel;
    private JComboBox<String> comboBox;
    private JLabel label, fieldLabel;
    private JTextField field;
    private JButton doButton;
    private shipmentManagement sManagement;
    private ShippingMenu save;
    private GUIService guiService;
    private String siteName;
    private List<String> itemNames;
    private List<Integer> itemQuantities;


    public ExecuteShipment(ShippingMenu shippingMenu, String siteName){
        this.siteName = siteName;
        save = shippingMenu;
        createUIComponents();


        // finishing stuff
        comboBox.addActionListener(this);
        doButton.addActionListener(this);
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {checkBox();}

            @Override
            public void removeUpdate(DocumentEvent e) {checkBox();}

            @Override
            public void changedUpdate(DocumentEvent e) {checkBox();}
        });
    }
    private void checkBox(){
        setEnabled(!field.getText().isEmpty());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("delete")){
            int index = comboBox.getSelectedIndex();
            if (sManagement.deleteItemFromShipment(itemNames.get(index),itemQuantities.get(index),siteName)){
                JOptionPane.showMessageDialog(null, "The item was deleted", "Remove", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                save.setVisible(true);
            };
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
        itemNames = new ArrayList<>();
        itemQuantities = new ArrayList<>();


        // setting the combo box
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new GridLayout(1,1));
        comboPanel.setBackground(Color.BLACK);
        splittingList();
        String[] options = itemNames.toArray(new String[itemNames.size()]);
        comboBox = new JComboBox<>(options);
        comboPanel.add(comboBox);
        panel.add(comboPanel);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("this item quantity is " + itemQuantities.get(comboBox.getSelectedIndex()));
                label.setVisible(true);
            }
        });



        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(1,1));
        labelPanel.setBackground(Color.BLACK);
        label = new JLabel();
        label.setForeground(Color.white);
        label.setVisible(false);
        labelPanel.add(label);
        panel.add(labelPanel);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(1,2));
        fieldPanel.setBackground(Color.BLACK);
        fieldLabel = new JLabel("Quantity:");
        field = new JTextField();
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
        doButton = new JButton("Delete");
        buttonPanel.add(doButton);
        panel.add(buttonPanel);
    }

    private void splittingList(){
        List<String> temp = guiService.getItemsFromDoc(siteName);
        for(int i=0; i< temp.size(); i++){
            if (i % 2 == 0){
                itemNames.add(temp.get(i));
            }
            else{
                itemQuantities.add(Integer.parseInt(temp.get(i)));
            }
        }
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
