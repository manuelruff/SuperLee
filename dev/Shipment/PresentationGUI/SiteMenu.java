package Shipment.PresentationGUI;

import HR.Bussiness.ManagerController;
import Shipment.Bussiness.shipmentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SiteMenu extends JFrame implements ActionListener {
    private JPanel SiteMenu;
    private JButton exitButton;
    private JButton startButton;
    private JComboBox<String> comboBox1;
    private shipmentManagement shipmentM;

    public SiteMenu() throws HeadlessException {

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
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox1 = new JComboBox<>();
        comboBox1.addItem("Add Vendor");
        comboBox1.addItem("Print All Sites (vendors and branches)");
        comboBox1.addItem("Update site info");

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
        }
    }
}
