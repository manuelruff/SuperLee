package HR.PresentationGUI.HRManagaerWindows;

import HR.Bussiness.Days;
import HR.Bussiness.ManagerController;
import HR.Presentation.UIGeneralFnctions;
import HR.PresentationGUI.HRManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreateWeekly  extends JFrame implements ActionListener {
    private JPanel CreateWeeklyWin;
    private ManagerController managerController;

    //save the window that opened us to show him after closing
    private WorkOnABranch save;
    //name of this branch we are working on
    private String name;
    private JLabel dayLabel, CashiersLabel, StoreKeepersLabel, GeneralEmployesLabel, GuardsLabel,  CleanerLabel, UsherLabel , errorLabel, shiftLabel;
    private JTextField CashiersLabelField, StoreKeepersLabelField, GeneralEmployesLabelField, GuardsLabelField, CleanerLabelField, UsherLabelField;
    private JButton nextButton, cancelButton;
    private JCheckBox empty;

    //values saving for creating the shifts
    private String day="Sunday";
    private String shift="Morning";
    private int daynum=0;
    private int shiftnum=0;

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
        errorLabel= new JLabel("");
        shiftLabel=new JLabel(this.shift);

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
        errorLabel.setForeground(Color.RED);
        errorLabel.setBackground(Color.BLACK);
        shiftLabel.setForeground(Color.WHITE);


        //create the text boxes and start them with 0
        CashiersLabelField=new JTextField(10);
        CashiersLabelField.setText("0");
        StoreKeepersLabelField=new JTextField(10);
        StoreKeepersLabelField.setText("0");
        GeneralEmployesLabelField=new JTextField(10);
        GeneralEmployesLabelField.setText("0");
        GuardsLabelField=new JTextField(10);
        GuardsLabelField.setText("0");
        CleanerLabelField=new JTextField(10);
        CleanerLabelField.setText("0");
        UsherLabelField=new JTextField(10);
        UsherLabelField.setText("0");

        //make the button available only when all textboxes are full
        CashiersLabelField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = CashiersLabelField.getText();
                checkAllTextBoxes();
                // perform necessary actions based on the text retrieved from the text field
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = CashiersLabelField.getText();
                checkAllTextBoxes();
                //if he removes somethig we dont need the message anymore
                errorLabel.setText("");
                // perform necessary actions based on the text retrieved from the text field
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkAllTextBoxes();
                // this method is not called for changes to the text content of the JTextField
            }
        });
        StoreKeepersLabelField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = StoreKeepersLabelField.getText();
                checkAllTextBoxes();
                // perform necessary actions based on the text retrieved from the text field
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = StoreKeepersLabelField.getText();
                checkAllTextBoxes();
                //if he removes somethig we dont need the message anymore
                errorLabel.setText("");
                // perform necessary actions based on the text retrieved from the text field
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkAllTextBoxes();
                // this method is not called for changes to the text content of the JTextField
            }
        });
        GeneralEmployesLabelField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = GeneralEmployesLabelField.getText();
                checkAllTextBoxes();
                // perform necessary actions based on the text retrieved from the text field
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = GeneralEmployesLabelField.getText();
                checkAllTextBoxes();
                //if he removes somethig we dont need the message anymore
                errorLabel.setText("");
                // perform necessary actions based on the text retrieved from the text field
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkAllTextBoxes();
                // this method is not called for changes to the text content of the JTextField
            }
        });
        GuardsLabelField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = GuardsLabelField.getText();
                checkAllTextBoxes();
                // perform necessary actions based on the text retrieved from the text field
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = GuardsLabelField.getText();
                checkAllTextBoxes();
                //if he removes somethig we dont need the message anymore
                errorLabel.setText("");
                // perform necessary actions based on the text retrieved from the text field
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkAllTextBoxes();
                // this method is not called for changes to the text content of the JTextField
            }
        });
        CleanerLabelField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = CleanerLabelField.getText();
                checkAllTextBoxes();
                // perform necessary actions based on the text retrieved from the text field
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = CleanerLabelField.getText();
                checkAllTextBoxes();
                //if he removes somethig we dont need the message anymore
                errorLabel.setText("");
                // perform necessary actions based on the text retrieved from the text field
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkAllTextBoxes();
                // this method is not called for changes to the text content of the JTextField
            }
        });
        UsherLabelField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = UsherLabelField.getText();
                checkAllTextBoxes();
                // perform necessary actions based on the text retrieved from the text field
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = UsherLabelField.getText();
                checkAllTextBoxes();
                //if he removes somethig we dont need the message anymore
                errorLabel.setText("");
                // perform necessary actions based on the text retrieved from the text field
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkAllTextBoxes();
                // this method is not called for changes to the text content of the JTextField
            }
        });

        //make the text fields only get integers
        CashiersLabelField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c < '0' || c > '9') {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });
        StoreKeepersLabelField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c < '0' || c > '9') {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });
        GeneralEmployesLabelField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c < '0' || c > '9') {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });
        GuardsLabelField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c < '0' || c > '9') {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });
        CleanerLabelField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c < '0' || c > '9') {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });
        UsherLabelField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c < '0' || c > '9') {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });

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
        JPanel dayPanel = new JPanel(new GridLayout(1, 3));
        dayPanel.setBackground(Color.BLACK); // Set the panel background color to black
        dayPanel.add(dayLabel);
        dayPanel.add(shiftLabel);
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

        //add the error lable
        CreateWeeklyWin.add(errorLabel);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1)); // Set the grid layout to 2 rows and 1 column
        buttonPanel.setBackground(Color.BLACK); // Set the panel background color to black
        buttonPanel.add(nextButton);
        buttonPanel.add(cancelButton);
        CreateWeeklyWin.add(buttonPanel);



        // Set the maximum size of the buttons to fill the horizontal space
        Dimension maxButtonSize = new Dimension(Integer.MAX_VALUE, nextButton.getPreferredSize().height);
        nextButton.setMaximumSize(maxButtonSize);
        cancelButton.setMaximumSize(maxButtonSize);

        // Set the action listeners for the buttons
        nextButton.addActionListener(this);
        cancelButton.addActionListener(this);


        // Set the content pane and display the window
        setContentPane(CreateWeeklyWin);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 400));
        pack();
        setVisible(true);
    }


    private void checkAllTextBoxes() {
        if (CashiersLabelField.getText().isEmpty() || StoreKeepersLabelField.getText().isEmpty() ||
                GeneralEmployesLabelField.getText().isEmpty() || GuardsLabelField.getText().isEmpty() ||
                CleanerLabelField.getText().isEmpty() || UsherLabelField.getText().isEmpty()) {
            nextButton.setEnabled(false);
        }
        else {
            nextButton.setEnabled(true);
        }
    }

    //Sunday, Monday,Tuesday,Wednesday,Thursday,Friday,Saturday
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            boolean isCreated=false;
            //we need to create an empty shift
            if(empty.isSelected()) {
                managerController.emptyShift(this.daynum,this.shiftnum,name);
                isCreated=true;
                //we want to unselect this
                empty.setSelected(false);
            }
            else {
                //get all the info abount the numbers
                int[] jobs = new int[6];
                jobs[0] = Integer.parseInt(CashiersLabelField.getText());
                jobs[1] = Integer.parseInt(StoreKeepersLabelField.getText());
                jobs[2] = Integer.parseInt(GeneralEmployesLabelField.getText());
                jobs[3] = Integer.parseInt(GuardsLabelField.getText());
                jobs[4] = Integer.parseInt(CleanerLabelField.getText());
                jobs[5] = Integer.parseInt(UsherLabelField.getText());
                String error = managerController.AddShift(name, this.daynum, this.shiftnum, jobs);
                //if we get error message we print it
                if (!error.equals("success")) {
                    isCreated=false;
                    errorLabel.setText(error);
                }
                else{
                    isCreated=true;
                }
            }
            //if we created
            if(isCreated) {
                isCreated=false;
                //if no error we can go on
                //check if we are have finished
                if (nextButton.getText().equals("Done")) {
                    JOptionPane.showMessageDialog(null, "weekly created successfully!", "weekly", JOptionPane.INFORMATION_MESSAGE);
                    this.save.setVisible(true);
                    this.dispose();
                    //go back to prev window
                }
                //reset all the textboxes
                CashiersLabelField.setText("0");
                StoreKeepersLabelField.setText("0");
                GeneralEmployesLabelField.setText("0");
                GuardsLabelField.setText("0");
                CleanerLabelField.setText("0");
                UsherLabelField.setText("0");

                //if we only need to change the shift time and not day thats it:
                if (shiftnum == 0) {
                    shiftnum++;
                    shiftLabel.setText("Evening");
                }
                //else we nned to change the day and shift
                else {
                    shiftnum = 0;
                    shiftLabel.setText("Morning");
                    daynum++;
                    //next day
                    day = Days.values()[daynum].toString();
                    dayLabel.setText(day);
                }
                //if we are in the last day
                if (this.day.equals("Saturday") && shiftnum == 1) {
                    nextButton.setText("Done");
                }
            }
        }
        if (e.getSource() == cancelButton) {
            //delete what we have done and return to last window
            managerController.CancelWeekly(name);
            this.save.setVisible(true);
            this.dispose();
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

