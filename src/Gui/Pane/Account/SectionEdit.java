package Gui.Pane.Account;

import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import Gui.Pane.Pane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class SectionEdit extends Pane{

    private JTable table;
    private DefaultTableModel model;

    public SectionEdit(Dimension frame, Palette palette, Label label) {
        super(frame, palette, label);

        this.setLayout(null);
        this.setSize(frame);

        initUI(frame, palette);
        
        model.addRow(new Object[]{"2024-001", "Student", "Juan", "Santos", "Dela Cruz", "Male", "2006-03-15", "09171234567", "12", "Rizal St", "San Isidro", "Calamba", "Laguna", "4027", "Philippines"});
        model.addRow(new Object[]{"2024-002", "Student", "Maria", "Lopez", "Garcia", "Female", "2005-11-22", "09181234568", "45", "Mabini St", "San Jose", "Los Baños", "Laguna", "4030", "Philippines"});
        model.addRow(new Object[]{"2024-003", "Student", "Carlos", "Reyes", "Torres", "Male", "2006-07-09", "09191234569", "78", "Bonifacio Ave", "Mayapa", "Calamba", "Laguna", "4027", "Philippines"});
        model.addRow(new Object[]{"2024-004", "Student", "Angela", "Cruz", "Bautista", "Female", "2005-05-30", "09201234570", "23", "Del Pilar St", "Poblacion", "Santa Rosa", "Laguna", "4026", "Philippines"});
        model.addRow(new Object[]{"2024-005", "Student", "Mark", "Fernandez", "Ramos", "Male", "2006-01-18", "09211234571", "56", "Quezon Ave", "Balibago", "Santa Rosa", "Laguna", "4026", "Philippines"});
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
        table.setFont(label.getCaption());

        JTableHeader header = table.getTableHeader();
        header.setFont(label.getBody());
        header.setOpaque(true);
        header.setResizingAllowed(false);
        header.setBackground(palette.getPrimary());
        header.setForeground(palette.getTextLight());

        table.setBackground(new Color(245, 245, 245));
        table.setForeground(Color.BLACK);
        table.setSelectionBackground(new Color(100, 149, 237));
        table.setSelectionForeground(Color.WHITE);
        table.setGridColor(new Color(200, 200, 200));

        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        //new second========
        JButton gradesBtn = this.getButton("Section", 8);
        gradesBtn.setBackground(palette.getPrimary());
        gradesBtn.setForeground(palette.getTextLight());
        setComponent(gradesBtn, new Dimension(110, 40), new Point(40, 60));

        //new first===========
        JScrollPane scrollPane = new JScrollPane(table);
        int topSpace = 90;     // space for Grades / Attendance buttons
        int bottomSpace = 90;  // space for Add / Delete buttons
        int sideMargin = 40;

        scrollPane.setBounds(sideMargin,topSpace,frame.width - (sideMargin * 2),frame.height - topSpace - 65);
        this.add(scrollPane);
        this.setComponentZOrder(gradesBtn, this.getComponentCount() - 1);
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