package HR.PresentationGUI.HRManagaerWindows;

import HR.Bussiness.ManagerController;
import HR.PresentationGUI.HRManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateWeekly  extends JFrame implements ActionListener {
    private JPanel CreateWeeklyWin;
    private ManagerController managerController;

    //save the window that opened us to show him after closing
    private WorkOnABranch save;
    //name of this branch we are working on
    private String name;
    private JLabel dayLabel, CashiersLabel, StoreKeepersLabel, GeneralEmployesLabel, GuardsLabel,  CleanerLabel, UsherLabel;
    private JTextField CashiersLabelField, StoreKeepersLabelField, GeneralEmployesLabelField, GuardsLabelField, CleanerLabelField, UsherLabelField;
    private JButton nextButton, cancelButton;
    private JCheckBox empty;

    private String day="Sunday";
    public CreateWeekly(WorkOnABranch save,String name) {
        this.save=save;
        this.name=name;
        this.managerController = ManagerController.getInstance();
        this.day="Sunday";
    }
    private void createUIComponents() {
        this.setTitle("Create weekly");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the content pane background color to black
        getContentPane().setBackground(Color.BLACK);
        // Create the components
        dayLabel=new JLabel(this.day);
        CashiersLabel = new JLabel("Cashiers:");
        StoreKeepersLabel = new JLabel("Store Keepers");
        GeneralEmployesLabel = new JLabel("General Employes:");
        GuardsLabel = new JLabel("Guards:");
        CleanerLabel = new JLabel("Cleaner:");
        UsherLabel = new JLabel("Usher:");

        // Set the foreground color of each label to white
        dayLabel.setForeground(Color.WHITE);
        CashiersLabel.setForeground(Color.WHITE);
        StoreKeepersLabel.setForeground(Color.WHITE);
        GeneralEmployesLabel.setForeground(Color.WHITE);
        GuardsLabel.setForeground(Color.WHITE);
        CleanerLabel.setForeground(Color.WHITE);
        UsherLabel.setForeground(Color.WHITE);
        // Set the foreground color of each label to white
        dayLabel.setForeground(Color.WHITE);


        CashiersLabelField=new JTextField(10);
        StoreKeepersLabelField=new JTextField(10);
        GeneralEmployesLabelField=new JTextField(10);
        GuardsLabelField=new JTextField(10);
        CleanerLabelField=new JTextField(10);
        UsherLabelField=new JTextField(10);


        nextButton = new JButton("Next");
        cancelButton = new JButton("Cancel");

        // Set the foreground color of each button to black
        nextButton.setForeground(Color.BLACK);
        cancelButton.setForeground(Color.BLACK);

        // Create a panel and set the layout
        CreateWeeklyWin = new JPanel();
        CreateWeeklyWin.setLayout(new BoxLayout(CreateWeeklyWin, BoxLayout.Y_AXIS));

        //create the checkbox
        empty= new JCheckBox();
        empty.setBackground(Color.BLACK);
        empty.setForeground(Color.WHITE);
        empty.setText("empty shift");
        empty.addActionListener(this);


        // Add the components to the panel
        JPanel dayPanel = new JPanel(new GridLayout(1, 2));
        dayPanel.setBackground(Color.BLACK); // Set the panel background color to black
        dayPanel.add(dayLabel);
        dayPanel.add(empty);
        CreateWeeklyWin.add(dayPanel);

        JPanel CashiersLabelPanel = new JPanel(new GridLayout(1, 2));
        CashiersLabelPanel.setBackground(Color.BLACK); // Set the panel background color to black
        CashiersLabelPanel.add(CashiersLabel);
        CashiersLabelPanel.add(CashiersLabelField);
        CreateWeeklyWin.add(CashiersLabelPanel);

        JPanel StoreKeepersLabelPanel = new JPanel(new GridLayout(1, 2));
        StoreKeepersLabelPanel.setBackground(Color.BLACK); // Set the panel background color to black
        StoreKeepersLabelPanel.add(StoreKeepersLabel);
        StoreKeepersLabelPanel.add(StoreKeepersLabelField);
        CreateWeeklyWin.add(StoreKeepersLabelPanel);

        JPanel GeneralEmployesLabelPanel = new JPanel(new GridLayout(1, 2));
        GeneralEmployesLabelPanel.setBackground(Color.BLACK); // Set the panel background color to black
        GeneralEmployesLabelPanel.add(GeneralEmployesLabel);
        GeneralEmployesLabelPanel.add(GeneralEmployesLabelField);
        CreateWeeklyWin.add(GeneralEmployesLabelPanel);

        JPanel GuardsLabelPanel = new JPanel(new GridLayout(1, 2));
        GuardsLabelPanel.setBackground(Color.BLACK); // Set the panel background color to black
        GuardsLabelPanel.add(GuardsLabel);
        GuardsLabelPanel.add(GuardsLabelField);
        CreateWeeklyWin.add(GuardsLabelPanel);

        JPanel CleanerLabelPanel = new JPanel(new GridLayout(1, 2));
        CleanerLabelPanel.setBackground(Color.BLACK); // Set the panel background color to black
        CleanerLabelPanel.add(CleanerLabel);
        CleanerLabelPanel.add(CleanerLabelField);
        CreateWeeklyWin.add(CleanerLabelPanel);

        JPanel UsherLabelPanel = new JPanel(new GridLayout(1, 2));
        UsherLabelPanel.setBackground(Color.BLACK); // Set the panel background color to black
        UsherLabelPanel.add(UsherLabel);
        UsherLabelPanel.add(UsherLabelField);
        CreateWeeklyWin.add(UsherLabelPanel);


        JPanel buttonPanel = new JPanel(new GridLayout(2, 1)); // Set the grid layout to 2 rows and 1 column
        buttonPanel.setBackground(Color.BLACK); // Set the panel background color to black
        buttonPanel.add(nextButton);
        buttonPanel.add(cancelButton);
        CreateWeeklyWin.add(buttonPanel);

        // Set the maximum size of the buttons to fill the horizontal space
        Dimension maxButtonSize = new Dimension(Integer.MAX_VALUE, nextButton.getPreferredSize().height);
        nextButton.setMaximumSize(maxButtonSize);
        cancelButton.setMaximumSize(maxButtonSize);
        nextButton.setEnabled(false);

        // Set the action listeners for the buttons
        nextButton.addActionListener(this);
        cancelButton.addActionListener(this);


        // Set the content pane and display the window
        setContentPane(CreateWeeklyWin);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 200));
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {

        }
        if (e.getSource() == cancelButton) {
            
        }
        if (e.getSource() == empty) {
            if(empty.isSelected()) {
                //set all to unavailable
                CashiersLabelField.setEnabled(false);
                StoreKeepersLabelField.setEnabled(false);
                GeneralEmployesLabelField.setEnabled(false);
                GuardsLabelField.setEnabled(false);
                CleanerLabelField.setEnabled(false);
                UsherLabelField.setEnabled(false);
                nextButton.setEnabled(true);
            }
            else {
                //set all to available
                CashiersLabelField.setEnabled(true);
                StoreKeepersLabelField.setEnabled(true);
                GeneralEmployesLabelField.setEnabled(true);
                GuardsLabelField.setEnabled(true);
                CleanerLabelField.setEnabled(true);
                UsherLabelField.setEnabled(true);
                nextButton.setEnabled(false);
            }
        }
    }
}

