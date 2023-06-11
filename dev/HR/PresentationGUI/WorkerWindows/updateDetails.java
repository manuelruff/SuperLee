package HR.PresentationGUI.WorkerWindows;
import HR.Bussiness.WorkerController;
import HR.PresentationGUI.WorkerGUI;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class updateDetails extends JFrame implements ActionListener {
    private JPanel updateDetailsWin;
    private JTextField password,bank,name;
    private JButton updateButton;
    private JButton backButton;
    //save the preview window to open in the end
    private WorkerGUI save;
    //take instance for worker controller
    private WorkerController workerController;
    //save the old values to know what to update
    private String Wid;
    private String Wname;
    private String Wpass;
    private String Wbank;
    public updateDetails(WorkerGUI save,List<String> worker) {
        this.save=save;
        this.setContentPane(updateDetailsWin);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,200));
        this.pack();
        this.setVisible(true);
        this.setTitle("Worker");
        //add listeners
        updateButton.addActionListener(this);
        backButton.addActionListener(this);
        this.workerController = WorkerController.getInstance();
        //put current values and save them
        this.Wid=worker.get(0);
        this.Wname=worker.get(1);
        this.Wpass=worker.get(2);
        this.Wbank=worker.get(3);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==updateButton){
            //change password if changed
            if(!(password.getText().equals(this.Wpass)))
            {
                workerController.ChangeWorkerPassword(this.Wid,password.getText());
            }
            //change name if changed
            if(!(name.getText().equals(this.Wname)))
            {
                workerController.ChangeWorkerName(this.Wid,name.getText());// change name
            }
            //changed bank if changed
            if(!(bank.getText().equals(this.Wbank)))
            {
                workerController.ChangeWorkerBank(this.Wid,Integer.parseInt(bank.getText()));
            }
        }
        else if(e.getSource()==backButton)
        {
            this.dispose();
            save.setVisible(true);
        }
    }
}
