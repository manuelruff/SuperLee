package HR.PresentationGUI.HRManagaerWindows;

import HR.PresentationGUI.HRManager;
import HR.Service.GUIService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PrintShift  extends JFrame{
    private JPanel PrintShiftWin;
    //take the instance of gui service so we get what we want
    private List<List<String>> getShift;

    public PrintShift(List<List<String>> getShift) {
        // After everything else
        this.getShift=getShift;
        // create JScrollPane
        JScrollPane scrollPane = new JScrollPane(PrintShiftWin,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, // only vertical scrollbar as needed
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // only horizontal scrollbar as needed

        this.setContentPane(scrollPane);
        this.setMinimumSize(new Dimension(300,200));
        this.setLocation(300, 0);
        this.pack();
        this.setVisible(true);
        this.setTitle("Shifts");
    }
    private void createUIComponents() {
        // Panel setup
        PrintShiftWin = new JPanel();
        PrintShiftWin.setLayout(new BoxLayout(PrintShiftWin, BoxLayout.Y_AXIS)); // Vertical layout
        PrintShiftWin.setBackground(Color.BLACK);  // set panel background to black


        for(List<String> shiftInfo : getShift) {
            if(!shiftInfo.isEmpty()){
                JLabel dateLabel = new JLabel("Date: " + shiftInfo.get(0));
                dateLabel.setForeground(Color.WHITE);  // set label text color to white
                PrintShiftWin.add(dateLabel);

                JLabel shiftTimeLabel = new JLabel("Shift Time: " + shiftInfo.get(1));
                shiftTimeLabel.setForeground(Color.WHITE);  // set label text color to white
                PrintShiftWin.add(shiftTimeLabel);

                JLabel dayOfWeekLabel = new JLabel("Day of Week: " + shiftInfo.get(2));
                dayOfWeekLabel.setForeground(Color.WHITE);  // set label text color to white
                PrintShiftWin.add(dayOfWeekLabel);

                // Check if the shift is empty
                if(shiftInfo.get(3).equals("Empty")) {
                    JLabel emptyLabel = new JLabel("This shift is empty.");
                    emptyLabel.setForeground(Color.WHITE);  // set label text color to white
                    PrintShiftWin.add(emptyLabel);
                } else {
                    JLabel startLabel = new JLabel("Start Time: " + shiftInfo.get(3));
                    startLabel.setForeground(Color.WHITE);  // set label text color to white
                    PrintShiftWin.add(startLabel);

                    JLabel endLabel = new JLabel("End Time: " + shiftInfo.get(4));
                    endLabel.setForeground(Color.WHITE);  // set label text color to white
                    PrintShiftWin.add(endLabel);

                    // Assuming the workers start from the 5th index
                    for (int i = 5; i < shiftInfo.size(); i++) {
                        JLabel workerLabel = new JLabel(shiftInfo.get(i));
                        workerLabel.setForeground(Color.WHITE);  // set label text color to white
                        PrintShiftWin.add(workerLabel);
                    }
                }
                PrintShiftWin.add(new JSeparator());  // add separator between shifts
            }
        }
    }

}
