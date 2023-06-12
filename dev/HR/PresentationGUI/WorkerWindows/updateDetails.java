package HR.PresentationGUI.WorkerWindows;
import HR.Bussiness.WorkerController;
import HR.PresentationGUI.WorkerGUI;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class updateDetails extends JFrame implements ActionListener {
    private JPanel updateDetailsWin;
    private JTextField password, bank, name;
    private JButton updateButton;
    private JButton backButton;
    // save the preview window to open in the end
    private WorkerGUI save;
    // take instance for worker controller
    private WorkerController workerController;
    // save the old values to know what to update
    private String Wid;
    private String Wname;
    private String Wpass;
    private String Wbank;

    public updateDetails(WorkerGUI save, List<String> worker) {
        this.save = save;
        this.setContentPane(updateDetailsWin);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300, 200));
        this.pack();
        this.setVisible(true);
        this.setTitle("Worker");

        // Add listeners
        updateButton.addActionListener(this);
        backButton.addActionListener(this);
        this.workerController = WorkerController.getInstance();

        // Put current values and save them
        this.Wid = worker.get(0);
        this.Wname = worker.get(1);
        this.Wpass = worker.get(2);
        this.Wbank = worker.get(3);

        // Apply document filters
        bank.setDocument(new IntegerDocument());

        // Sets the text boxes with first info
        this.name.setText(this.Wname);
        this.password.setText(this.Wpass);
        this.bank.setText(this.Wbank);

        // Add document listeners to text fields to enable/disable the update button
        name.getDocument().addDocumentListener(new UpdateButtonDocumentListener());
        password.getDocument().addDocumentListener(new UpdateButtonDocumentListener());
        bank.getDocument().addDocumentListener(new UpdateButtonDocumentListener());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            // Change password if changed
            if (!(password.getText().equals(this.Wpass))) {
                workerController.ChangeWorkerPassword(this.Wid, password.getText());
            }
            // Change name if changed
            if (!(name.getText().equals(this.Wname))) {
                workerController.ChangeWorkerName(this.Wid, name.getText());
            }
            // Change bank if changed
            if (!(bank.getText().equals(this.Wbank))) {
                workerController.ChangeWorkerBank(this.Wid, Integer.parseInt(bank.getText()));
            }
            // Todo: make sure that bank is only a number and that all fields are filled when the update button is pressed
        } else if (e.getSource() == backButton) {
            this.dispose();
            save.setVisible(true);
        }
    }

    // Inner class for document filter
    class IntegerDocument extends PlainDocument {
        @Override
        public void insertString(int offset, String s, AttributeSet attributeSet) throws BadLocationException {
            if (s == null) {
                return;
            }
            try {
                Integer.parseInt(s);
                super.insertString(offset, s, attributeSet);
            } catch (NumberFormatException e) {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    // Inner class for document listener to enable/disable the update button
    class UpdateButtonDocumentListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateButton.setEnabled(isFieldsFilled());
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateButton.setEnabled(isFieldsFilled());
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateButton.setEnabled(isFieldsFilled());
        }

        private boolean isFieldsFilled() {
            return !name.getText().isEmpty() && !password.getText().isEmpty() && !bank.getText().isEmpty();
        }
    }
}