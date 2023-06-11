package HR.PresentationGUI.HRManagaerWindows;

import HR.PresentationGUI.HRManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DriversSchedule extends JFrame implements ActionListener {
    private JPanel DriversScheduleWin;
    //save the window that opened us to show him after closing
    private HRManager save;
    public DriversSchedule(HRManager save) {
        this.save=save;

    }
    private void createUIComponents() {
        // TODO: place custom component creation code here

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
