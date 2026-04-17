package Gui.Pane.Account;

import Gui.Pane.Pane;
import Tool.Gui.Palette;
import Tool.Gui.Signal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class SectionEdit extends Pane{
    private JPanel mainPanel;
    private JPanel tablePanel;

    private JTable table;
    private DefaultTableModel model;

    public SectionEdit(Dimension frame, Palette palette) {
        super(frame, palette);

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

        table.setRowHeight(24);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setOpaque(true);
        header.setBackground(palette.getPrimary());
        header.setForeground(palette.getTextLight());

        table.setBackground(new Color(245, 245, 245));
        table.setForeground(Color.BLACK);
        table.setSelectionBackground(new Color(100, 149, 237));
        table.setSelectionForeground(Color.WHITE);
        table.setGridColor(new Color(200, 200, 200));

        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane scrollPane = new JScrollPane(table);

        int width = 50;
        int height = 100;

        scrollPane.setBounds(
                this.getX(frame.width - width, 0.5),
                this.getY(frame.height - height, 0.5),
                frame.width - width,
                frame.height - height
        );

        this.add(scrollPane);
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
