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


import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


public class editConstraints extends JFrame implements ActionListener {
    private JPanel editConstraintsWin;
    private JButton addButton;
    private JButton backButton;
    private JButton removeButton;
    private JComboBox<Days> dayCB;
    private JTextField from;
    private JTextField to;
    private JPanel dataWin;
    private JTextField reason;

    private JLabel dayLabel;
    private JLabel fromLabel;
    private JLabel toLabel;
    private JLabel reasonLabel;

    private WorkerGUI save;
    private WorkerController workerController;
    private GUIService guiService;
    private String id;

    public editConstraints(WorkerGUI save, String id) {
        this.save = save;
        this.id = id;
        this.workerController = WorkerController.getInstance();
        createUIComponents();
    }

    private void createUIComponents() {
        editConstraintsWin = new JPanel();
        editConstraintsWin.setBackground(Color.BLACK);
        editConstraintsWin.setLayout(new GridBagLayout());

        this.setContentPane(editConstraintsWin);
        this.setMinimumSize(new Dimension(300, 500));
        this.pack();
        this.setVisible(true);
        this.setTitle("Constraints");

        guiService = GUIService.getInstance();

        dayCB = new JComboBox<>(Days.values());

        from = new JTextField();
        from.setDocument(new DoubleDocument());

        to = new JTextField();
        to.setDocument(new DoubleDocument());

        reason = new JTextField();

        DocumentListener docListener = createDocumentListener();
        from.getDocument().addDocumentListener(docListener);
        to.getDocument().addDocumentListener(docListener);

        addButton = new JButton("Add Constraint");
        removeButton = new JButton("Remove Constraint");
        backButton = new JButton("Back");

        addButton.setEnabled(false);
        removeButton.setEnabled(false);

        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        backButton.addActionListener(this);

        dataWin = new JPanel();
        dataWin.setBackground(Color.BLACK);
        dataWin.setLayout(new BoxLayout(dataWin, BoxLayout.Y_AXIS));

        loadData();

        dayLabel = new JLabel("Day: ");
        dayLabel.setForeground(Color.WHITE);

        fromLabel = new JLabel("From: ");
        fromLabel.setForeground(Color.WHITE);

        toLabel = new JLabel("To: ");
        toLabel.setForeground(Color.WHITE);

        reasonLabel = new JLabel("Reason: ");
        reasonLabel.setForeground(Color.WHITE);

        addComponent(dayLabel, 0, 0, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, new Insets(5, 5, 5, 5));
        addComponent(dayCB, 1, 0, 1, GridBagConstraints.HORIZONTAL, 1.0, 0.0, new Insets(5, 5, 5, 5));

        addComponent(fromLabel, 0, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, new Insets(5, 5, 5, 5));
        addComponent(from, 1, 1, 1, GridBagConstraints.HORIZONTAL, 1.0, 0.0, new Insets(5, 5, 5, 5));

        addComponent(toLabel, 0, 2, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, new Insets(5, 5, 5, 5));
        addComponent(to, 1, 2, 1, GridBagConstraints.HORIZONTAL, 1.0, 0.0, new Insets(5, 5, 5, 5));

        addComponent(reasonLabel, 0, 3, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, new Insets(5, 5, 5, 5));
        addComponent(reason, 1, 3, 1, GridBagConstraints.HORIZONTAL, 1.0, 0.0, new Insets(5, 5, 5, 5));

        JScrollPane scrollPane = new JScrollPane(dataWin);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        addComponent(scrollPane, 0, 4, 2, GridBagConstraints.BOTH, 1.0, 1.0, new Insets(5, 5, 5, 5));

        addComponent(addButton, 0, 5, 2, GridBagConstraints.HORIZONTAL, 1.0, 0.0, new Insets(5, 5, 5, 5));
        addComponent(removeButton, 0, 6, 2, GridBagConstraints.HORIZONTAL, 1.0, 0.0, new Insets(5, 5, 5, 5));
        addComponent(backButton, 0, 7, 2, GridBagConstraints.HORIZONTAL, 1.0, 0.0, new Insets(5, 5, 5, 5));
    }

    private void addComponent(Component component, int gridx, int gridy, int gridwidth, int fill, double weightx,
                              double weighty, Insets insets) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.fill = fill;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.insets = insets;
        editConstraintsWin.add(component, gbc);
    }

    private void loadData() {
        dataWin.removeAll();

        List<String> constraints = guiService.getWorkerCantWorkDays(id);
        String currentDay = "";
        for (String constraint : constraints) {
            if (currentDay.isEmpty()) {
                currentDay = constraint;
                JLabel dayLabel = new JLabel("Day: " + currentDay);
                dayLabel.setForeground(Color.WHITE);
                dataWin.add(dayLabel);
            } else {
                JLabel constraintLabel = new JLabel(constraint);
                constraintLabel.setForeground(Color.WHITE);
                dataWin.add(constraintLabel);
            }
        }

        dataWin.add(Box.createVerticalGlue());
        dataWin.revalidate();
        dataWin.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedDay = dayCB.getSelectedIndex() + 1;
        double fromTime = Double.parseDouble(from.getText());
        double toTime = Double.parseDouble(to.getText());
        if (e.getSource() == addButton) {
            workerController.AddConstraints(id, selectedDay, fromTime, toTime, reason.getText());
            loadData();
        } else if (e.getSource() == removeButton) {
            workerController.RemoveConstraints(id, selectedDay, fromTime, toTime);
            loadData();
        } else if (e.getSource() == backButton) {
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