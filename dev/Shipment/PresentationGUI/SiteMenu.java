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
    private JComboBox comboBox1;
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
        this.shipmentM = shipmentManagement.getInstance();
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox1 = new JComboBox<String>();
        comboBox1.addItem("Add Vendor");
        comboBox1.addItem("Print All Sites (vendors and branches)");
        comboBox1.addItem("Update site info");

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SiteMenu());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
