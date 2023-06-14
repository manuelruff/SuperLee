package Shipment.PresentationGUI;

import HR.Service.ShipmentService;
import Shipment.Bussiness.Training;
import Shipment.Bussiness.shipmentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UpdateDriver extends JFrame implements ActionListener {
    private JPanel UpdateDriver;
    private JButton cancelButton;
    private JButton startButton;
    private JComboBox<String> comboBox;
    private JTextField textField;
    private JComboBox<String> comboBox1;
    private shipmentManagement shipmentM;
    private ShipmentService service;
    private DriverMenu save;

    public UpdateDriver(DriverMenu save) throws HeadlessException {
        // Set the content pane of the JFrame to the JPanel UpdateSite
        this.save = save;
        this.setContentPane(UpdateDriver);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,200));
        this.pack();
        this.setVisible(true);
        this.setTitle("Update Driver info");
        textField.addActionListener(this);
        startButton.addActionListener(this);
        cancelButton.addActionListener(this);
        // Add ActionListener to comboBox1
        comboBox.addActionListener(new ActionListener() {
                                       @Override
                                       public void actionPerformed(ActionEvent e) {
                                           // Check the selected item in comboBox1
                                           String selectedOption = (String) comboBox.getSelectedItem();

                                           // Update the visibility of comboBox2 based on the selected item in comboBox1
                                           if (selectedOption.equals("Update driver training")) {
                                               comboBox1.setVisible(true);
                                           } else {
                                               comboBox1.setVisible(false);
                                           }
                                       }
                                   });
        comboBox1.addActionListener(this);
        shipmentM = shipmentManagement.getInstance();
        service = ShipmentService.getInstance();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startButton) {
            String ID = textField.getText();
            List<String> info = service.askForDriver(ID);

            if(info.size() != 0)
            {
                textField.setBackground(Color.white);
                if (comboBox.getSelectedItem().equals("Update driver licence")) {
                    Training training = Training.valueOf(info.get(3));
                    service.getUpdateForDriver(ID, 'D',training.ordinal());
                    JOptionPane.showMessageDialog(this, "Driver information changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    save.setVisible(true); // show the main window
                    this.dispose(); // close the current window
                }
                else if (comboBox.getSelectedItem().equals("Update driver training")) {
                    String c = comboBox1.getSelectedItem().toString();
                    char licence = info.get(2).charAt(0);
                    service.getUpdateForDriver(textField.getText(),licence,Training.valueOf(c).ordinal());
                    JOptionPane.showMessageDialog(this, "Driver information changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    save.setVisible(true); // show the main window
                    this.dispose(); // close the current window
                }

            }
            else
            {
                textField.setBackground(Color.RED);
            }
        }
        if (e.getSource() == cancelButton) {
            save.setVisible(true); // show the main window
            this.dispose(); // close the current window
        }
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox = new JComboBox<>();
        comboBox.addItem("Update driver licence");
        comboBox.addItem("Update driver training");
        comboBox1 = new JComboBox<>();
        comboBox1.addItem("Regular");
        comboBox1.addItem("Cooling");
        comboBox1.addItem("Freezer");

    }
}
