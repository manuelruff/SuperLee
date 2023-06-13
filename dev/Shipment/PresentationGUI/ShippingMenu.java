package Shipment.PresentationGUI;

import HR.Bussiness.Days;
import Shipment.Bussiness.shipmentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;

public class ShippingMenu extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel label;
    private JComboBox<String> comboBox;
    private JButton startButton;
    private JButton backButton;
    private shipmentManagement sManagement;
    private  ShipManager save;

    public ShippingMenu(ShipManager save) {
        createUIComponents();
        this.save = save;
        setContentPane(panel);
        pack();
        setVisible(true);
        sManagement = shipmentManagement.getInstance();
        comboBox.addActionListener(this);
        startButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.setTitle("Shipping");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 200));

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);

        // Adding the label
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.setBackground(Color.BLACK);
        label = new JLabel("Please choose your action:");
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        labelPanel.add(label);
        panel.add(labelPanel);

        // Adding the combo box
        String[] options = {"Add Shipment", "Delete Shipment", "Print All Shipments", "Print All Available Shipments", "Execute Nearest Shipment"};
        comboBox = new JComboBox<>(options);
        panel.add(comboBox);

        panel.add(Box.createVerticalStrut(50));

        // Adding the start button
        startButton = new JButton("Start");
        panel.add(startButton);

        // Adding the back button
        backButton = new JButton("Back");
        panel.add(backButton);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1)); // Set the grid layout to 2 rows and 1 column
        buttonPanel.setBackground(Color.BLACK); // Set the panel background color to black
        buttonPanel.add(startButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) {
            if (Objects.equals(comboBox.getSelectedItem(), "Add Shipment")) {
                LocalDate currentDate = LocalDate.now();
                if (currentDate.getDayOfWeek().equals(DayOfWeek.FRIDAY) || currentDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
                    new AddShipment(this, 1);
                }
                else{
                    String[] options = {"This Week", "Next Week"};
                    // dialog for this week or next week.
                    int choice = JOptionPane.showOptionDialog(
                            this,
                            "Please choose the shipment schedule:",
                            "Shipment Schedule",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]
                    );
                    new AddShipment(this, choice);
                    setVisible(false);
                }
            }
            else if (Objects.equals(comboBox.getSelectedItem(), "Delete Shipment")) {
                JTextField textField = new JTextField();
                int result = JOptionPane.showConfirmDialog(null, textField, "Enter Shipment id:", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String ID = textField.getText();
                    boolean check = sManagement.checkShipmentID(ID);

                    //if the Shipment exists
                    if (!check) {
                        JOptionPane.showMessageDialog(null, "Shipment id not found");
                    } else {
                        sManagement.deleteShipment(ID);
                        JOptionPane.showMessageDialog(null, "The Shipment has been removed successfully!", "Remove", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
            else if (Objects.equals(comboBox.getSelectedItem(), "Print All Shipments")) {

            }
            else if (Objects.equals(comboBox.getSelectedItem(), "Print All Available Shipments")) {

            }
            else if (Objects.equals(comboBox.getSelectedItem(), "Execute Nearest Shipment")) {

            }
        }
        else if (e.getActionCommand().equals("Back")){
            this.dispose();
            save.setVisible(true);
        }
    }
}

