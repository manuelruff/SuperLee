package HR.PresentationGUI.WorkerWindows;
import HR.Bussiness.Days;
import HR.Bussiness.WorkerController;
import HR.PresentationGUI.WorkerGUI;
import HR.Service.GUIService;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editConstraints extends JFrame implements ActionListener {
    private JPanel editConstraintsWin;
    private JButton addButton;
    private JButton backButton;
    private JButton removeButton;
    private JComboBox<Days> dayCB;
    private JTextField from;
    private JTextField to;
    private JPanel data;

    //save the preview window to open in the end
    private WorkerGUI save;

    // take instance for worker controller
    private WorkerController workerController;
    //take the instance of gui service so we get what we want
    private GUIService guiService;
    //save his id for extracting info and updating
    private String id;

    //todo work with constraints reasons, i need to add that to this class


    public editConstraints(WorkerGUI save, String id) {
        this.save = save;
        this.id = id;
        this.workerController = WorkerController.getInstance();
    }

    private void createUIComponents() {
        editConstraintsWin=new JPanel();
        this.setContentPane(editConstraintsWin);
        this.setMinimumSize(new Dimension(300, 300));
        this.pack();
        this.setVisible(true);
        this.setTitle("Constraints");
        //get the instance when we update the panel
        guiService = GUIService.getInstance();

        // TODO: place custom component creation code here
        //combo box with days
        dayCB = new JComboBox<>(Days.values());
        //load the data about the workers constraints
        loadData();

        // Add the data panel to a scroll pane
        JScrollPane scrollPane = new JScrollPane(data);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add the scroll pane to the content pane
        editConstraintsWin.setLayout(new BorderLayout());
        editConstraintsWin.add(scrollPane, BorderLayout.CENTER);

        from = new JTextField();
        from.setDocument(new DoubleDocument());

        to = new JTextField();
        to.setDocument(new DoubleDocument());

        DocumentListener docListener = createDocumentListener();
        from.getDocument().addDocumentListener(docListener);
        to.getDocument().addDocumentListener(docListener);

        addButton = new JButton("Add Constraint");
        removeButton = new JButton("Remove Constraint");
        backButton = new JButton("Back");

        // Initially disable buttons until text fields are filled
        addButton.setEnabled(false);
        removeButton.setEnabled(false);

        //set listeneres for buttons
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        backButton.addActionListener(this);
    }
//load his constraints
    private void loadData(){
        data = new JPanel();
        data.setLayout(new BoxLayout(data, BoxLayout.Y_AXIS));
        List<String> constraints = guiService.getWorkerCantWorkDays(id);
        String currentDay = "";  // Variable to keep track of the current day

        for (String constraint : constraints) {
            if (currentDay.isEmpty()) {
                // A new day is encountered
                currentDay = constraint;
                JLabel dayLabel = new JLabel("Day: " + currentDay);
                dayLabel.setForeground(Color.WHITE);
                data.add(dayLabel);
            } else {
                // Constraints for the current day
                JLabel constraintLabel = new JLabel(constraint);
                constraintLabel.setForeground(Color.WHITE);
                data.add(constraintLabel);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            // Handle add constraint action
            Days selectedDay = (Days) dayCB.getSelectedItem();
            String fromTime = from.getText();
            String toTime = to.getText();

            // TODO: Implement add constraint logic here
            System.out.println("Adding constraint for " + selectedDay + " from " + fromTime + " to " + toTime);

        } else if (e.getSource() == removeButton) {
            // Handle remove constraint action

            // TODO: Implement remove constraint logic here
            System.out.println("Removing constraint");

        } else if (e.getSource() == backButton) {
            // Handle back button action
            this.dispose();
            save.setVisible(true);
        }
    }

    private DocumentListener createDocumentListener() {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkFields();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkFields();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkFields();
            }

            public void checkFields() {
                if (from.getText().trim().isEmpty() || to.getText().trim().isEmpty()) {
                    addButton.setEnabled(false);
                    removeButton.setEnabled(false);
                } else {
                    addButton.setEnabled(true);
                    removeButton.setEnabled(true);
                }
            }
        };
    }

    class HourFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            StringBuilder builder = new StringBuilder();
            builder.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
            builder.insert(offset, string);
            if (isValid(builder.toString())) {
                super.insertString(fb, offset, string, attr);
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            StringBuilder builder = new StringBuilder();
            builder.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
            builder.replace(offset, offset + length, text);
            if (isValid(builder.toString())) {
                super.replace(fb, offset, length, text, attrs);
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        private boolean isValid(String text) {
            if (text.isEmpty()) {
                return true;
            }
            try {
                double value = Double.parseDouble(text);
                int hours = (int) value;
                int minutes = (int) ((value - hours) * 100);
                return hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    class DoubleDocument extends PlainDocument {
        public DoubleDocument() {
            setDocumentFilter(new HourFilter());
        }
    }


}