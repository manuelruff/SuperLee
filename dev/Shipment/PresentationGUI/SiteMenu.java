package Shipment.PresentationGUI;

import HR.Bussiness.ManagerController;
import Shipment.Bussiness.shipmentManagement;
import Shipment.Service.GUIService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class SiteMenu extends JFrame implements ActionListener {
    private JPanel SiteMenu;
    private JButton exitButton;
    private JButton startButton;
    private JComboBox<String> comboBox1;
    private shipmentManagement shipmentM;
    private ShipManager save;
    private GUIService service;


    public SiteMenu(ShipManager save)  {
        this.save = save;
        this.setContentPane(SiteMenu);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,200));
        this.pack();
        this.setVisible(true);
        this.setTitle("Site Menu");
        startButton.addActionListener(this);
        exitButton.addActionListener(this);
        comboBox1.addActionListener(this);
        shipmentM = shipmentManagement.getInstance();
        service = GUIService.getInstance();

    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox1 = new JComboBox<>();
        comboBox1.addItem("Add Vendor");
        comboBox1.addItem("Print All Sites (vendors and branches)");
        comboBox1.addItem("Update site info");

    }


    public JTable createSiteTable() {
        // Create the table data
        List<String[]> data = service.getSiteData();
        boolean ch = true;
        Object[][] tableData = new Object[data.size()+1][5];
        int row = 0;
        for (String[] site : data) {
            if(site[4] != null && ch)
            {
                tableData[row][0] = "";
                tableData[row][1] = "";
                tableData[row][2] = "";
                tableData[row][3] = "";
                tableData[row][4] = "";
                ch = false;
                row++;
            }
            tableData[row][0] = site[0];
            tableData[row][1] = site[1];
            tableData[row][2] = site[2];
            tableData[row][3] = site[3];
            tableData[row][4] = site[4];
            row++;

        }

        // Create the table column names
        String[] columnNames = {"Site Name", "Address", "Phone Number", "Contact Name", "Zone"};

        // Create the JTable component
        JTable siteTable = new JTable(tableData, columnNames);

        return siteTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Start"))
        {
            if(comboBox1.getSelectedItem().equals("Add Vendor"))
            {
                new AddVendor(this);
                this.setVisible(false);
            }
            else if (comboBox1.getSelectedItem().equals("Update site info")) {
                new UpdateSite(this);
                this.setVisible(false);
            } else if (comboBox1.getSelectedItem().equals("Print All Sites (vendors and branches)")) {
                JScrollPane scrollPane = new JScrollPane(createSiteTable());
                JOptionPane.showMessageDialog(null, scrollPane, "Site Details", JOptionPane.INFORMATION_MESSAGE);
            }

        }
        if(e.getSource()== exitButton)
        {
            this.save.setVisible(true);
            this.dispose();
        }
    }
}
