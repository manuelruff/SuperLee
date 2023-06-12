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

        dataWin = new JPanel(new GridBagLayout());
        dataWin.setBackground(Color.BLACK);

        loadData();

        //add scrolbars to dataWin
        JScrollPane scrollPane = new JScrollPane(dataWin);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        dayLabel = new JLabel("Day: ");
        dayLabel.setForeground(Color.WHITE);

        fromLabel = new JLabel("From: ");
        fromLabel.setForeground(Color.WHITE);

        toLabel = new JLabel("To: ");
        toLabel.setForeground(Color.WHITE);

        reasonLabel = new JLabel("Reason: ");
        reasonLabel.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        editConstraintsWin.add(dayLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editConstraintsWin.add(dayCB, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        editConstraintsWin.add(fromLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editConstraintsWin.add(from, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        editConstraintsWin.add(toLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editConstraintsWin.add(to, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        editConstraintsWin.add(reasonLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editConstraintsWin.add(reason, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        editConstraintsWin.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editConstraintsWin.add(addButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editConstraintsWin.add(removeButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editConstraintsWin.add(backButton, gbc);
    }

    private void loadData() {
        dataWin.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        List<String> constraints = guiService.getWorkerCantWorkDays(id);
        String currentDay = "";
        for (String constraint : constraints) {
            if (currentDay.isEmpty()) {
                currentDay = constraint;
                JLabel dayLabel = new JLabel("Day: " + currentDay);
                dayLabel.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = GridBagConstraints.RELATIVE;
                dataWin.add(dayLabel, gbc);
            } else {
                JLabel constraintLabel = new JLabel(constraint);
                constraintLabel.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = GridBagConstraints.RELATIVE;
                dataWin.add(constraintLabel, gbc);
            }
        }

        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        dataWin.add(Box.createVerticalGlue(), gbc);

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

