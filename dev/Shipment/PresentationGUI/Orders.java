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
    private JTable table1;

    private shipmentManagement shipmentM;
    private ShipManager save;
    private GUIService service;

    public Orders(ShipManager save) {
        createUIComponents();
        this.save = save;
        this.setContentPane(Orders);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,200));
        this.pack();
        this.setVisible(true);
        this.setTitle("Driver Menu");
        shipmentM = shipmentManagement.getInstance();
        service = GUIService.getInstance();
    }

    private void createUIComponents() {
        JFrame frame = new JFrame("JButton in JTable Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the table data
        Object[][] tableData = {
                {"John", createButton("Edit")},
                {"Emily", createButton("Edit")},
                {"David", createButton("Edit")}
        };
        String[] columnNames = {"Name", "Action"};

        // Create the table model
        DefaultTableModel model = new DefaultTableModel(tableData, columnNames);

        // Create the custom renderer and editor for the button column
        TableColumn buttonColumn = table1.getColumnModel().getColumn(1);
        buttonColumn.setCellRenderer(new ButtonRenderer());
        buttonColumn.setCellEditor(new ButtonEditor());

        // Create the JTable component
        JTable table = new JTable(model);

        // Set the preferred size of the table
        table.setPreferredScrollableViewportSize(new Dimension(400, 200));

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the frame
        frame.getContentPane().add(scrollPane);

        frame.pack();
        frame.setVisible(true);
    }
    // Helper method to create a JButton with the given text
    private static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform button action here
                System.out.println("Button clicked");
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

    }
}
