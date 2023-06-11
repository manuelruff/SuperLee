package HR.PresentationGUI.WorkerWindows;

import HR.PresentationGUI.WorkerGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editConstraints extends JFrame implements ActionListener {
    private JPanel editConstraints;

    //save the preview window to open in the end
    private WorkerGUI save;
    public editConstraints(WorkerGUI save) {
        this.save=save;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
