package HR.PresentationGUI;

import HR.Bussiness.ManagerController;
import Shipment.Service.GUIService;
import Shipment.Service.HRService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreManager  extends JFrame implements ActionListener {
    private JButton HRManagerButton;
    private JButton workerButton;
    private JButton shipmentManagerButton;
    private JPanel StoreManagerWin;

    private HRService hrService;

    public StoreManager() {
        this.setContentPane(StoreManagerWin);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,150));
        this.pack();
        this.setVisible(true);
        this.setTitle("Store manager");
        this.hrService=HRService.getInstance();
        HRManagerButton.addActionListener(this);
        shipmentManagerButton.addActionListener(this);
        workerButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==HRManagerButton)
        {
            new HRManager();
        }
        else if(e.getSource()==workerButton)
        {
            new WorkerGUI();
        }
        else{
            this.hrService.openMainShipManagerWin();
        }
        this.dispose();

    }
}
