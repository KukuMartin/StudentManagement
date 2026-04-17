package Gui.Pane;

import Tool.Gui.Palette;
import Tool.Gui.Signal;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class SectionEdit extends Pane{
    private JPanel mainPanel;
    private JPanel tablePanel;

    private JTable table;
    private DefaultTableModel model;

    public SectionEdit(Dimension frame, Palette palette, Signal signal) {
        super(frame, palette, signal);

        this.setLayout(null);
        this.setSize(frame);

        initUI(frame, palette);
    }

    private void initUI(Dimension frame, Palette palette) {

        String[] columns = {
                "AccountID", "Type",

                "FirstName", "MiddleName", "LastName",
                "Gender", "Birthdate", "PhoneNumber",

                "HouseNumber", "Street", "Barangay",
                "City", "Province", "ZipCode", "Country"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setOpaque(true);
        header.setBackground(new Color(30, 30, 30));
        header.setForeground(Color.WHITE);

        table.setBackground(new Color(245, 245, 245));
        table.setForeground(Color.BLACK);
        table.setSelectionBackground(new Color(100, 149, 237));
        table.setSelectionForeground(Color.WHITE);
        table.setGridColor(new Color(200, 200, 200));

        //scroll panel
        JScrollPane scrollPane = new JScrollPane(table);

        tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        //main panel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBounds(20, 60, frame.width - 40, frame.height - 80);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        this.add(mainPanel);
    }

    public void addRow(Object[] rowData) {
        model.addRow(rowData);
    }

    public void clearTable() {
        model.setRowCount(0);
    }

    public JTable getTable() {
        return table;
    }
}
