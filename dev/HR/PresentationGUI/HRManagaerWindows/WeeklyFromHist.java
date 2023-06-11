package HR.PresentationGUI.HRManagaerWindows;

import HR.Bussiness.Days;
import HR.PresentationGUI.HRManager;
import HR.Service.GUIService;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WeeklyFromHist  extends JFrame implements ActionListener {
    // Class components
    private JTextField yearField, monthField, dayField;
    private JButton doButton, backButton;
    private JLabel yearLabel, monthLabel, dayLabel,justtext ,errorlable;
    private JPanel WeeklyFromHistWin;
    private JComboBox<Days> daysComboBox;

    // take the instance of gui service so we get what we want
    private GUIService guiService;
    // save the window that opened us to show him after closing
    private WorkOnABranch save;
    // name of this branch we are working on
    private String name;

    private PrintShift prwin;

    public WeeklyFromHist(WorkOnABranch save, String name) {
        this.save=save;
        this.name=name;
        createUIComponents();

        // Set the content pane and display the window
        setContentPane(WeeklyFromHistWin);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 200));
        setLocation(300, 0);
        pack();
        setVisible(true);
        setTitle("Shifts");
    }

    private void createUIComponents() {
        guiService=GUIService.getInstance();

        // Set the content pane background color to black
        getContentPane().setBackground(Color.BLACK);

        yearLabel = new JLabel("Year:");
        monthLabel = new JLabel("Month:");
        dayLabel = new JLabel("Day:");
        errorlable=new JLabel("no weekly there");

        // Set the foreground color of each label to white
        yearLabel.setForeground(Color.WHITE);
        monthLabel.setForeground(Color.WHITE);
        dayLabel.setForeground(Color.WHITE);
        errorlable.setForeground(Color.RED);
        errorlable.setVisible(false);
        // Create a JComboBox with the week days
        daysComboBox = new JComboBox<>(Days.values());

        // Fields with PlainDocument subclass to restrict inputs to integers only
        yearField = new JTextField(10);
        yearField.setDocument(new IntDocument());

        monthField = new JTextField(10);
        monthField.setDocument(new IntDocument());

        dayField = new JTextField(10);
        dayField.setDocument(new IntDocument());

        // Buttons
        doButton = new JButton("Do");
        backButton = new JButton("Back");

        // Set the foreground color of each button to black
        doButton.setForeground(Color.BLACK);
        backButton.setForeground(Color.BLACK);

        // Initially set the "Do" button to be disabled
        doButton.setEnabled(false);

        // Panel
        WeeklyFromHistWin = new JPanel();
        WeeklyFromHistWin.setLayout(new BoxLayout(WeeklyFromHistWin, BoxLayout.Y_AXIS));
        WeeklyFromHistWin.setBackground(Color.BLACK); // Set the panel background color to black

        //first we tell him what we want
        justtext=new JLabel("please enter the date of sunday of that week:");
        justtext.setForeground(Color.white);
        WeeklyFromHistWin.add(justtext);

        // Add the components to the panel
        JPanel yearPanel = new JPanel(new GridLayout(1, 2));
        yearPanel.setBackground(Color.BLACK); // Set the panel background color to black
        yearPanel.add(yearLabel);
        yearPanel.add(yearField);
        WeeklyFromHistWin.add(yearPanel);

        JPanel monthPanel = new JPanel(new GridLayout(1, 2));
        monthPanel.setBackground(Color.BLACK); // Set the panel background color to black
        monthPanel.add(monthLabel);
        monthPanel.add(monthField);
        WeeklyFromHistWin.add(monthPanel);

        JPanel dayPanel = new JPanel(new GridLayout(1, 2));
        dayPanel.setBackground(Color.BLACK); // Set the panel background color to black
        dayPanel.add(dayLabel);
        dayPanel.add(dayField);
        WeeklyFromHistWin.add(dayPanel);

        // Add the JComboBox to the panel
        JPanel daySelectionPanel = new JPanel(new GridLayout(1, 2));
        daySelectionPanel.setBackground(Color.BLACK); // Set the panel background color to black
        JLabel daysLabel = new JLabel("Day of the week:");
        daysLabel.setForeground(Color.WHITE); // Set the label foreground color to white
        daySelectionPanel.add(daysLabel);
        daySelectionPanel.add(daysComboBox);
        WeeklyFromHistWin.add(daySelectionPanel);


        //add error lable
        WeeklyFromHistWin.add(errorlable);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1)); // Set the grid layout to 2 rows and 1 column
        buttonPanel.setBackground(Color.BLACK); // Set the panel background color to black
        buttonPanel.add(doButton);
        buttonPanel.add(backButton);
        WeeklyFromHistWin.add(buttonPanel);

        // Set the action listeners for the buttons
        doButton.addActionListener(this);
        backButton.addActionListener(this);

        // Add the document listeners for the text fields and action listener for the JComboBox
        yearField.getDocument().addDocumentListener(new ButtonStateController());
        monthField.getDocument().addDocumentListener(new ButtonStateController());
        dayField.getDocument().addDocumentListener(new ButtonStateController());
        daysComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doButton.setEnabled(checkAllFields());
            }
        });

        //some window properties
        this.setContentPane(WeeklyFromHistWin);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,300));
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Handle action events for buttons here
        if(e.getSource() == doButton) {
            //we will check if there is a shift there:
            //if we find a shift well open a window that shoes it
            List<List<String>> getShift= guiService.getWeeklyFromHist(this.name,Integer.parseInt(yearField.getText()),Integer.parseInt(monthField.getText()),Integer.parseInt(dayField.getText()),daysComboBox.getSelectedIndex());
            if(getShift==null){
                errorlable.setVisible(true);
            }
            else{
                this.prwin=new PrintShift(getShift);
            }
        }
        else if(e.getSource() == backButton) {
            //we show the main window
            save.setVisible(true);
            if(this.prwin!=null) {
                //close extra window
                this.prwin.dispose();
            }
            //close this window
            this.dispose();
        }
    }

    // Check whether all fields are filled
    private boolean checkAllFields() {
        return yearField.getText().trim().length() > 0
                && monthField.getText().trim().length() > 0
                && dayField.getText().trim().length() > 0
                && daysComboBox.getSelectedItem() != null;
    }

    // Inner classes for DocumentFilter
    class IntFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (string != null && string.matches("\\d*")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text != null && text.matches("\\d*")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

    class IntDocument extends PlainDocument {
        public IntDocument() {
            setDocumentFilter(new IntFilter());
        }
    }

    // Inner class for DocumentListener
    class ButtonStateController implements DocumentListener {
        private void updateButtonState() {
            doButton.setEnabled(checkAllFields());
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            updateButtonState();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateButtonState();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateButtonState();
        }
    }
}
