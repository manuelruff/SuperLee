package Shipment.PresentationGUI;

import Shipment.Bussiness.shipmentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DriverMenu extends JFrame implements ActionListener {
    private JButton backButton;
    private JButton startButton;
    private JComboBox<String> comboBox1;
    private JPanel DriverMenu;
    private shipmentManagement shipmentM;
    private ShipManager save;

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
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox1 = new JComboBox<>();
        comboBox1.addItem("Print All drivers");
        comboBox1.addItem("Update driver information");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if(comboBox1.getSelectedItem().equals("Update driver information"))
            {
                new UpdateDriver(this);
                this.setVisible(false);
            }
        }
        if(e.getSource() == backButton)
        {

        }
    }
}
