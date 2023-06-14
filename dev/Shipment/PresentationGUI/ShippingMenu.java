package Shipment.PresentationGUI;

import Shipment.Bussiness.shipmentManagement;
import Shipment.Service.GUIService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class ShippingMenu extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel label;
    private JComboBox<String> comboBox;
    private JButton startButton;
    private JButton backButton;
    private shipmentManagement sManagement;
    private ShipManager save;
    private GUIService guiService;

    public ShippingMenu(ShipManager save) {
        createUIComponents();
        this.save = save;
        setContentPane(panel);
        pack();
        setVisible(true);
        sManagement = shipmentManagement.getInstance();
        guiService = GUIService.getInstance();
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
                if (currentDate.getDayOfWeek().equals(DayOfWeek.FRIDAY) || currentDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                    new AddShipment(this, 1);
                } else {
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
            } else if (Objects.equals(comboBox.getSelectedItem(), "Delete Shipment")) {
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
            } else if (Objects.equals(comboBox.getSelectedItem(), "Print All Shipments")) {

            } else if (Objects.equals(comboBox.getSelectedItem(), "Print All Available Shipments")) {

            } else if (Objects.equals(comboBox.getSelectedItem(), "Execute Nearest Shipment")) {
                if (sManagement.checkAvailableShipment()) {
                    JOptionPane.showMessageDialog(this, "There is No AvailableShipments at the moment", "Failure!", JOptionPane.INFORMATION_MESSAGE);
                } else if (!sManagement.checkIfDriverExist()) {
                    JOptionPane.showMessageDialog(this, "Cant find a suitable driver", "Failure!", JOptionPane.INFORMATION_MESSAGE);
                } else if (!sManagement.checkExecuteNow()) {
                    JOptionPane.showMessageDialog(this, "In one of the branches there is not available storekeeper, so the shipment was canceled", "Failure!", JOptionPane.INFORMATION_MESSAGE);
                    sManagement.deleteShipment(sManagement.getAvailableShipment().get(0).getID());
                } else {
                    String truckWeight;
                    int weight = 0;
                    int firstWeight = Integer.MAX_VALUE;
                    boolean validInput = false;
                    while (true) {
                        while (!validInput) {
                            truckWeight = JOptionPane.showInputDialog(this, "Please enter the weight of the truck:");
                            if (truckWeight != null && truckWeight.matches("\\d+")) {
                                weight = Integer.parseInt(truckWeight);
                                if (weight >= firstWeight) {
                                    JOptionPane.showMessageDialog(this, "The weight is higher then the last weight", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    firstWeight = weight;
                                    validInput = true;
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Invalid input! Please enter a numeric value.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        if (sManagement.checkTruckWeight(weight)) {
                            String[] options = {"Exchange Truck", "Delete Items From Shipment", "Delete Last Site"};
                            JComboBox<String> comboBoxWeight = new JComboBox<>(options);
                            comboBoxWeight.addActionListener(this);

                            Object[] message = {
                                    "Choose an option:",
                                    comboBoxWeight
                            };

                            int result = JOptionPane.showOptionDialog(null, message, "Pop-up Window", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                            if (result == JOptionPane.OK_OPTION) {
                                if (Objects.equals(comboBoxWeight.getSelectedItem(), "Delete Items From Shipment")) {
                                    JComboBox<String> comboBoxSites = new JComboBox<>(guiService.getSitesOfShipmentData());
                                    comboBoxSites.addActionListener(this);
                                    Object[] pop = {
                                            "Choose an option:",
                                            comboBoxSites
                                    };

                                    result = JOptionPane.showOptionDialog(null, pop, "Pop-up Window", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                                    if (result == JOptionPane.OK_OPTION) {
                                        String siteName = Objects.requireNonNull(comboBoxSites.getSelectedItem()).toString();
                                        new ExecuteShipment(this, siteName);
                                        this.setVisible(false);
                                    } else if (Objects.equals(comboBoxWeight.getSelectedItem(), "Exchange Truck")) {
                                        if (sManagement.changeTruck()) {
                                            JOptionPane.showMessageDialog(this, "Truck Exchanged Successfully", "Success!", JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            JOptionPane.showMessageDialog(this, "There is No Available truck at the moment", "Failure!", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                    } else if (Objects.equals(comboBoxWeight.getSelectedItem(), "Delete Last Site")) {
                                        if (sManagement.removeLastSiteFromShipment()) {
                                            JOptionPane.showMessageDialog(this, "Site removed Successfully", "Success!", JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            JOptionPane.showMessageDialog(this, "There is only 1 site in the shipment", "Failure!", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                    }
                                }
                                // TODO maybe remove the cancel button
                                else {
                                    System.out.println("Dialog canceled.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "The shipment has been executed successfully.", "finished", JOptionPane.ERROR_MESSAGE);
                                //todo check this
                                LocalTime time = LocalTime.now();
                                sManagement.updateShipment(time);
                                break;
                            }
                        }

                    }
                }
            }
        }
        else if (e.getActionCommand().equals("Back")) {
            save.setVisible(true);
            this.dispose();

        }
    }
}

