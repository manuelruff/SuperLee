package HR.PresentationGUI.WorkerWindows;

import HR.PresentationGUI.WorkerGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class updateDetails extends JFrame implements ActionListener {
    private JPanel updateDetails;
    //save the preview window to open in the end
    private WorkerGUI save;

    public updateDetails(WorkerGUI save) {
        this.save=save;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
