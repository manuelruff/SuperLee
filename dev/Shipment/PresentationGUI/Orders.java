package Shipment.PresentationGUI;

import Shipment.Bussiness.shipmentManagement;
import Shipment.Service.GUIService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Orders extends JFrame implements ActionListener {
    private JPanel Orders;

    private shipmentManagement shipmentM;
    private OrderMenu save;
    private GUIService service;

    private JFrame frame; // Declare frame as an instance variable

    public Orders(OrderMenu save) {
        createUIComponents();
        this.save = save;

        shipmentM = shipmentManagement.getInstance();
        service = GUIService.getInstance();

        frame.pack(); // Pack the frame
        frame.setVisible(true); // Make the frame visible
    }

    private void createUIComponents() {
        frame = new JFrame("JButton in JTable Example"); // Use the instance variable frame

        // Create the table data
        Object[][] tableData = {
                {"John", createButton("Edit")},
                {"Emily", createButton("Edit")},
                {"David", createButton("Edit")}
        };
        String[] columnNames = {"Name", "Action"};

        // Create the table model
        DefaultTableModel model = new DefaultTableModel(tableData, columnNames);

        // Create the JTable component
        JTable table = new JTable(model);

        // Create the custom renderer and editor for the button column
        TableColumn buttonColumn = table.getColumnModel().getColumn(1);
        buttonColumn.setCellRenderer(new ButtonRenderer());
        buttonColumn.setCellEditor(new ButtonEditor());

        // Set the preferred size of the table
        table.setPreferredScrollableViewportSize(new Dimension(400, 200));

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the frame
        frame.getContentPane().add(scrollPane);
    }

    // Helper method to create a JButton with the given text
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform button action here
                JOptionPane.showMessageDialog(null, "Button clicked");
            }
        });
        return button;
    }


    static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {

            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {

                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Custom cell editor for the button column
    static class ButtonEditor extends DefaultCellEditor {
        protected JButton button;

        public ButtonEditor() {
            super(new JTextField());
            setClickCountToStart(1);

            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            button.setText((value == null) ? "" : value.toString());
            return button;
        }

        public Object getCellEditorValue() {
            return button.getText();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button click events here
    }
}



