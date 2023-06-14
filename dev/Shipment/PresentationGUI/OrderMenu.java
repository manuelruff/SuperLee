package Shipment.PresentationGUI;

import Shipment.Bussiness.shipmentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderMenu extends JFrame implements ActionListener {
    private JButton backButton;
    private JPanel OrderMenu;
    private JButton startButton;
    private JComboBox<String> comboBox;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel label2;
    private JLabel label1;
    private shipmentManagement shipmentM;
    private ShipManager save;

    public OrderMenu(ShipManager save) {
        this.save = save;
        this.setContentPane(OrderMenu);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,200));
        this.pack();
        this.setVisible(true);
        this.setTitle("Order Menu");
        startButton.addActionListener(this);
        backButton.addActionListener(this);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check the selected item in comboBox1
                String selectedOption = (String) comboBox.getSelectedItem();

                // Update the visibility of comboBox2 based on the selected item in comboBox1
                if (selectedOption.equals("Add Order")) {
                    label1.setVisible(true);
                    label2.setVisible(true);
                    textField1.setVisible(true);
                    textField2.setVisible(true);
                } else {
                    label1.setVisible(false);
                    label2.setVisible(false);
                    textField1.setVisible(false);
                    textField2.setVisible(false);
                }
            }
        });
        shipmentM = shipmentManagement.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton)
        {
            if(comboBox.getSelectedItem().equals("Add Order"))
            {
                if(shipmentM.checkVendor(textField1.getText()))
                {
                    textField1.setBackground(Color.WHITE);
                    if(shipmentM.checkBranch(textField2.getText()))
                    {
                        textField2.setBackground(Color.WHITE);
                        shipmentM.createOrder(textField1.getText(),textField2.getText());
                        new AddOrder(this,textField1.getText());
                        this.setVisible(false);
                    }
                    else
                        textField2.setBackground(Color.RED);
                }
                else
                    textField1.setBackground(Color.RED);
            }

        }
        if(e.getSource() == backButton)
        {
            save.setVisible(true);
            this.dispose();
        }

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox = new JComboBox<>();
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();
        textField2 = new JTextField();
        comboBox.addItem("Add Order");
        comboBox.addItem("Print all orders");
        label1.setVisible(false);
        label2.setVisible(false);
        textField1.setVisible(false);
        textField2.setVisible(false);
    }
}
