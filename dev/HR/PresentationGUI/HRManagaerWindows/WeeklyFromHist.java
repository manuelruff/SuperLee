package HR.PresentationGUI.HRManagaerWindows;

import HR.PresentationGUI.HRManager;
import HR.Service.GUIService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeeklyFromHist  extends JFrame implements ActionListener {
    private JPanel WeeklyFromHistWin;
    //take the instance of gui service so we get what we want
    private GUIService guiService;
    //save the window that opened us to show him after closing
    private WorkOnABranch save;
    //name of this branch we are working on
    private String name;
    public WeeklyFromHist(WorkOnABranch save, String name) {
        this.save=save;
        this.name=name;
    }
    private void createUIComponents() {
        guiService=GUIService.getInstance();
        // TODO: place custom component creation code here
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
