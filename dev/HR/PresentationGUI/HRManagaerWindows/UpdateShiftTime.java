package HR.PresentationGUI.HRManagaerWindows;

import HR.Bussiness.ManagerController;
import HR.PresentationGUI.HRManager;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateShiftTime extends JFrame implements ActionListener {
    // Declare the components
    private JLabel dayLabel, startMorningLabel, endMorningLabel, startEveningLabel, endEveningLabel;
    private JComboBox<String> dayComboBox;
    private JTextField startMorningTextField, endMorningTextField, startEveningTextField, endEveningTextField;
    private JButton doButton, backButton;
    private JPanel UpdateTimeWin;
    private ManagerController managerController;
    //save the window that opened us to show him after closing
    private WorkOnABranch save;
    //name of this branch we are working on
    private String name;
    // Constructor for the window
    public UpdateShiftTime(WorkOnABranch save,String name) {
        this.save = save;
        this.name = name;
        this.managerController = ManagerController.getInstance();

        // Set the content pane background color to black
        getContentPane().setBackground(Color.BLACK);

        // Create the components
        dayLabel = new JLabel("Choose day:");
        startMorningLabel = new JLabel("Choose start morning:");
        endMorningLabel = new JLabel("Choose end morning:");
        startEveningLabel = new JLabel("Choose start evening:");
        endEveningLabel = new JLabel("Choose end evening:");

        // Set the foreground color of each label to white
        dayLabel.setForeground(Color.WHITE);
        startMorningLabel.setForeground(Color.WHITE);
        endMorningLabel.setForeground(Color.WHITE);
        startEveningLabel.setForeground(Color.WHITE);
        endEveningLabel.setForeground(Color.WHITE);

        String[] days = {"Sunday","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        dayComboBox = new JComboBox<>(days);
        startMorningTextField = new JTextField(10);
        endMorningTextField = new JTextField(10);
        startEveningTextField = new JTextField(10);
        endEveningTextField = new JTextField(10);


// Set the document class for the text fields
        startMorningTextField.setDocument(new DoubleDocument());
        endMorningTextField.setDocument(new DoubleDocument());
        startEveningTextField.setDocument(new DoubleDocument());
        endEveningTextField.setDocument(new DoubleDocument());
        // put initial values in the tsxt boxes


        doButton = new JButton("Do");
        backButton = new JButton("Back");

        // Set the foreground color of each button to black
        doButton.setForeground(Color.BLACK);
        backButton.setForeground(Color.BLACK);

        // Create a panel and set the layout
        UpdateTimeWin = new JPanel();
        UpdateTimeWin.setLayout(new BoxLayout(UpdateTimeWin, BoxLayout.Y_AXIS));

        // Add the components to the panel
        JPanel dayPanel = new JPanel(new GridLayout(1, 2));
        dayPanel.setBackground(Color.BLACK); // Set the panel background color to black
        dayPanel.add(dayLabel);
        dayPanel.add(dayComboBox);
        UpdateTimeWin.add(dayPanel);

        JPanel startMorningPanel = new JPanel(new GridLayout(1, 2));
        startMorningPanel.setBackground(Color.BLACK); // Set the panel background color to black
        startMorningPanel.add(startMorningLabel);
        startMorningPanel.add(startMorningTextField);
        UpdateTimeWin.add(startMorningPanel);

        JPanel endMorningPanel = new JPanel(new GridLayout(1, 2));
        endMorningPanel.setBackground(Color.BLACK); // Set the panel background color to black
        endMorningPanel.add(endMorningLabel);
        endMorningPanel.add(endMorningTextField);
        UpdateTimeWin.add(endMorningPanel);

        JPanel startEveningPanel = new JPanel(new GridLayout(1, 2));
        startEveningPanel.setBackground(Color.BLACK); // Set the panel background color to black
        startEveningPanel.add(startEveningLabel);
        startEveningPanel.add(startEveningTextField);
        UpdateTimeWin.add(startEveningPanel);

        JPanel endEveningPanel = new JPanel(new GridLayout(1, 2));
        endEveningPanel.setBackground(Color.BLACK); // Set the panel background color to black
        endEveningPanel.add(endEveningLabel);
        endEveningPanel.add(endEveningTextField);
        UpdateTimeWin.add(endEveningPanel);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1)); // Set the grid layout to 2 rows and 1 column
        buttonPanel.setBackground(Color.BLACK); // Set the panel background color to black
        buttonPanel.add(doButton);
        buttonPanel.add(backButton);
        UpdateTimeWin.add(buttonPanel);

        // Set the maximum size of the buttons to fill the horizontal space
        Dimension maxButtonSize = new Dimension(Integer.MAX_VALUE, doButton.getPreferredSize().height);
        doButton.setMaximumSize(maxButtonSize);
        backButton.setMaximumSize(maxButtonSize);

        // Set the action listeners for the buttons
        doButton.addActionListener(this);
        backButton.addActionListener(this);

        // Set the content pane and display the window
        setContentPane(UpdateTimeWin);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 200));
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Do"))
        {

        }
        else if(e.getActionCommand().equals("Back"))
        {
            //we show the main window
            save.setVisible(true);
            //close this window
            this.dispose();
        }
    }

    // Create a custom document class that sets the double filter
    class DoubleDocument extends PlainDocument {
        public DoubleDocument() {
            setDocumentFilter(new DoubleFilter());
        }
    }
    // Create a document filter that only allows double values
    class DoubleFilter extends DocumentFilter {
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            String newStr = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
            try {
                Double.parseDouble(newStr);
                super.insertString(fb, offset, string, attr);
            } catch (NumberFormatException e) {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            String newStr = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
            try {
                Double.parseDouble(newStr);
                super.replace(fb, offset, length, text, attrs);
            } catch (NumberFormatException e) {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
}
