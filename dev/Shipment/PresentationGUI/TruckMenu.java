package Shipment.PresentationGUI;

import Shipment.Bussiness.ServiceController;
import Shipment.Bussiness.Truck;
import Shipment.Bussiness.shipmentManagement;
import Shipment.Service.GUIService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class TruckMenu  extends JFrame implements ActionListener {
    private JButton backButton;
    private JButton startButton;
    private JComboBox<String> comboBox1;
    private JPanel TruckMenu;
    private shipmentManagement shipmentM;
    private GUIService service;
    private ShipManager save;
    StringBuilder matrix;
    public TruckMenu(ShipManager save)
    {
        this.save = save;
        this.setContentPane(TruckMenu);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,200));
        this.pack();
        this.setVisible(true);
        this.setTitle("Truck Menu");
        startButton.addActionListener(this);
        backButton.addActionListener(this);
        comboBox1.addActionListener(this);
        shipmentM = shipmentManagement.getInstance();
        service = GUIService.getInstance();
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox1 = new JComboBox<>();
        comboBox1.addItem("Add Truck");
        comboBox1.addItem("Print All Trucks");
        matrix = new StringBuilder();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if(Objects.equals(comboBox1.getSelectedItem(), "Add Truck")) {
                new AddTruck(this);
                this.setVisible(false);
            }
            else if (Objects.equals(comboBox1.getSelectedItem(), "Print All Trucks"))
            {
                JScrollPane scrollPane = new JScrollPane(createTruckTable());
                JOptionPane.showMessageDialog(null, scrollPane, "Truck Details", JOptionPane.INFORMATION_MESSAGE);            }

        }
        if (e.getSource() == backButton) {
            save.setVisible(true); // show the main window
            this.dispose(); // close the current window
        }
    }
    public JTable createTruckTable() {
        // Create the table data
        List<String[]> data = service.getTrucksData();
        Object[][] tableData = new Object[data.size()][5];
        int row = 0;
        for (String[] truck : data) {
            tableData[row][0] = truck[0];
            tableData[row][1] = truck[1];
            tableData[row][2] = truck[2];
            tableData[row][3] = truck[3];
            tableData[row][4] = truck[4];
            row++;
        }

        // Create the table column names
        String[] columnNames = {"Truck Number", "Model", "Weight", "Total Weight", "Storage"};

        // Create the JTable component
        JTable truckTable = new JTable(tableData, columnNames);

        return truckTable;
    }


}
