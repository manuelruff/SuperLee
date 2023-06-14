package Shipment.PresentationGUI;

import Shipment.Bussiness.shipmentManagement;
import Shipment.Service.GUIService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DriverMenu extends JFrame implements ActionListener {
    private JButton backButton;
    private JButton startButton;
    private JComboBox<String> comboBox1;
    private JPanel DriverMenu;
    private shipmentManagement shipmentM;
    private ShipManager save;
    private GUIService service;

    public DriverMenu(ShipManager save) {
        this.save = save;
        this.setContentPane(DriverMenu);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,200));
        this.pack();
        this.setVisible(true);
        this.setTitle("Driver Menu");
        startButton.addActionListener(this);
        backButton.addActionListener(this);
        comboBox1.addActionListener(this);
        shipmentM = shipmentManagement.getInstance();
        service = GUIService.getInstance();
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox1 = new JComboBox<>();
        comboBox1.addItem("Print All drivers");
        comboBox1.addItem("Update driver information");
    }

    public JTable createDriverTable() {
        // Create the table data
        List<List<String>> data = service.getDriversData();
        Object[][] tableData = new Object[data.size()][4];
        int row = 0;
        for (List<String> driver : data) {
            tableData[row][0] = driver.get(0);
            tableData[row][1] = driver.get(1);
            tableData[row][2] = driver.get(2);
            tableData[row][3] = driver.get(3);
            row++;
        }
        // Create the table column names
        String[] columnNames = {"Driver ID", "Driver Name", "Licence", "Training"};

        // Create the JTable component
        JTable driversTable = new JTable(tableData, columnNames);

        return driversTable;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if(comboBox1.getSelectedItem().equals("Update driver information"))
            {
                new UpdateDriver(this);
                this.setVisible(false);
            }
            else if(comboBox1.getSelectedItem().equals("Print All drivers"))
            {
                JScrollPane scrollPane = new JScrollPane(createDriverTable());
                JOptionPane.showMessageDialog(null, scrollPane, "Drivers Details", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if(e.getSource() == backButton)
        {
            save.setVisible(true);
            this.dispose();
        }
    }
}
