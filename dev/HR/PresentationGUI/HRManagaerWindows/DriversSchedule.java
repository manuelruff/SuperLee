package HR.PresentationGUI.HRManagaerWindows;

import HR.PresentationGUI.HRManager;
import HR.Service.GUIService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.List;


public class DriversSchedule extends JFrame implements ActionListener {
    private JPanel DriversScheduleWin;
    private JComboBox<String> driversComboBox;
    private JTable driversTable;
    private JButton backButton;
    //save the window that opened us to show him after closing
    private HRManager save;
    //take the instance of gui service so we get what we want
    private GUIService guiService;
    public DriversSchedule(HRManager save) {
        this.save=save;
        // After everything else
        this.setContentPane(DriversScheduleWin);
        this.setMinimumSize(new Dimension(300,200));
        this.pack();
        this.setVisible(true);
        this.setTitle("Drivers Schedule");
    }

    private void createUIComponents() {
        guiService=GUIService.getInstance();
        // Panel setup
        DriversScheduleWin = new JPanel(new BorderLayout());
        DriversScheduleWin.setBackground(Color.BLACK);  // set panel background to black

        // Table setup
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable
                return false;
            }
        };
        model.addColumn("ID");
        model.addColumn("Weekly Working Days");
        driversTable = new JTable(model);
        driversTable.setForeground(Color.WHITE);  // set table text color to white
        driversTable.setBackground(Color.BLACK);  // set table background to black
        JScrollPane scrollPane = new JScrollPane(driversTable);
        scrollPane.setBackground(Color.BLACK);  // set scroll pane background to black
        scrollPane.getViewport().setBackground(Color.BLACK);  // set viewport background to black

        // ComboBox setup
        driversComboBox = new JComboBox<>();

        List<List<String>> drivers = guiService.getDriversSchedule();
        for (List<String> driver : drivers) {
            driversComboBox.addItem(driver.get(0) + "-" + driver.get(1));
        }
        driversComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                // Get the selected driver
                int selectedIndex = driversComboBox.getSelectedIndex();
                List<String> selectedDriver = drivers.get(selectedIndex);
                // Update the table model
                model.setRowCount(0); // Clear the existing rows
                String driverID = selectedDriver.get(0);
                // Concatenate all values from index 2 and onward, separating them with commas
                String weeklyWorkingDays = String.join(", ", selectedDriver.subList(2, selectedDriver.size()));
                model.addRow(new Object[]{driverID, weeklyWorkingDays});
            }
        });

        // Back button setup
        backButton = new JButton("Back");
        backButton.setForeground(Color.WHITE);  // set text color to white

        backButton.addActionListener(this);
        backButton.setForeground(Color.black);
        // Add the components to the panel
        DriversScheduleWin.add(driversComboBox, BorderLayout.NORTH);
        DriversScheduleWin.add(scrollPane, BorderLayout.CENTER);
        DriversScheduleWin.add(backButton, BorderLayout.SOUTH);

        // JFrame setup
        setContentPane(DriversScheduleWin);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close only this window on close
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Back"))
        {
            //we show the main window
            save.setVisible(true);
            //close this window
            this.dispose();
        }
    }
}
