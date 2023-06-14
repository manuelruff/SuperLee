package Shipment.PresentationGUI;

import Shipment.Bussiness.Training;
import Shipment.Bussiness.shipmentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddOrder extends JFrame implements ActionListener {
    private JButton backButton;
    private JButton startButton;
    private JComboBox<String> comboBox;
    private JPanel AddOrder;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox<String> comboBox1;
    private shipmentManagement shipmentM;
    private OrderMenu save;
    private String source;

    public AddOrder(OrderMenu save,String source) {
        this.save = save;
        this.source =source;
        this.setContentPane(AddOrder);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,200));
        this.pack();
        this.setVisible(true);
        this.setTitle("Add order");
        startButton.addActionListener(this);
        backButton.addActionListener(this);
        comboBox.addActionListener(this);
        textField1.addActionListener(this);
        textField2.addActionListener(this);
        shipmentM = shipmentManagement.getInstance();
    }

    private void createUIComponents() {
        comboBox = new JComboBox<>();
        comboBox.addItem("Add Item");
        comboBox.addItem("Print Items added so far");
        comboBox1 = new JComboBox<>();
        comboBox1.addItem("Regular");
        comboBox1.addItem("Cooling");
        comboBox1.addItem("Freezer");




    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton)
        {
            if(comboBox.getSelectedItem().equals("Add Item"))
            {
                String itemName = textField1.getText();
                if(!itemName.isEmpty())
                {
                    textField1.setBackground(Color.WHITE);
                    if (checkAmount(textField2.getText())) {
                        textField2.setBackground(Color.WHITE);
                        shipmentM.addItemToOrder(source,textField1.getText(),Integer.parseInt(textField2.getText()), Training.valueOf(comboBox1.getSelectedItem().toString()).ordinal());
                        JOptionPane.showMessageDialog(this, "Item added to order!", "Success", JOptionPane.INFORMATION_MESSAGE);

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
    private boolean checkAmount(String val)
    {
        return val.matches("\\d+");
    }
}
