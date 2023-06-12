package Shipment.PresentationGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShippingMenu extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel label;
    private JComboBox<String> comboBox;
    private JButton startButton;
    private JButton backButton;

    public ShippingMenu(){
        createUIComponents();
        display();

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.setTitle("Shipping");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

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
        String[] options = {"Option 1", "Option 2", "Option 3", "Option 4", "Option 5"};
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
    private void display() {
        SwingUtilities.invokeLater(() -> {
            setContentPane(panel);
            pack();
            setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

