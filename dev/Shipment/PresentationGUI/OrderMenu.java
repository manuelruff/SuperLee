package Shipment.PresentationGUI;

import Shipment.Bussiness.shipmentManagement;
import Shipment.Service.GUIService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
    private GUIService service;

    public OrderMenu(ShipManager save) {
        this.save = save;
        this.setContentPane(OrderMenu);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(400,300));
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
        service = GUIService.getInstance();
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
            else if(comboBox.getSelectedItem().equals("Print all orders(without items)"))
            {
                JScrollPane scrollPane = new JScrollPane(createOrdersTable());
                JOptionPane.showMessageDialog(null, scrollPane, "Order Details", JOptionPane.INFORMATION_MESSAGE);
            }
            else if(comboBox.getSelectedItem().equals("Print all items in orders"))
            {
                new PrintItems(this);
                this.setVisible(false);
            }

        }
        if(e.getSource() == backButton)
        {
            save.setVisible(true);
            this.dispose();
        }

    }
    public JTable createOrdersTable() {
        // Create the table data
        List<String[]> data = service.getOrdersData();
        Object[][] tableData = new Object[data.size()][4];
        int row = 0;
        for (String[] order : data) {
            tableData[row][0] = order[0];
            tableData[row][1] = order[1];
            tableData[row][2] = order[2];
            tableData[row][3] = order[3];
            row++;
        }
        // Create the table column names
        String[] columnNames = {"Order ID", "Source", "Destination", "Storage"};

        // Create the JTable component

        return new JTable(tableData, columnNames);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox = new JComboBox<>();
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();
        textField2 = new JTextField();
        comboBox.addItem("Print all orders(without items)");
        comboBox.addItem("Print all items in orders");
        comboBox.addItem("Add Order");
        label1.setVisible(false);
        label2.setVisible(false);
        textField1.setVisible(false);
        textField2.setVisible(false);
    }
}
