package Gui.Pane.Account;

import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import Gui.Pane.Pane;
import School.Model.Account.Section;
import School.Model.Account.Type.Student;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class SectionEdit extends Pane{

    private JTable table;
    private DefaultTableModel model;

    public SectionEdit(Dimension frame, Palette palette, Label label) {
        super(frame, palette, label);

        this.setLayout(null);
        this.setSize(frame);

        initUI();
    }

    private void initUI() {
        String[] columns = {     
            "id", "first", "middle", "last",
            "gender", "birthday", "phone",
            "address"
        };
        int[] width = {
            50, 120, 120, 120,
            80, 100, 100, 300
        };
        boolean[] ifCenter = {
            true, false, false, false,
            true, true, true, false
        };
        
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        
        
        
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        for(int index = 0; index < columns.length; index++){
            TableColumn column = table.getColumnModel().getColumn(index);
            column.setPreferredWidth(width[index]);
            if(!ifCenter[index]){
                continue;
            }
            column.setCellRenderer(center);
        }
        
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
        JButton gradesBtn = this.getSquareButton("Section", 8);
        gradesBtn.setBackground(palette.getPrimary());
        gradesBtn.setForeground(palette.getTextLight());
        setUpButton(gradesBtn, new Dimension(110, 40), new Point(40, 60));

        //new first===========
        JScrollPane scrollPane = new JScrollPane(table);
        int topSpace = 90;     // space for Grades / Attendance buttons
        int bottomSpace = 90;  // space for Add / Delete buttons
        int sideMargin = 40;

        scrollPane.setBounds(sideMargin,topSpace,frame.width - (sideMargin * 2),frame.height - topSpace - 65);
        this.add(scrollPane);
        this.setComponentZOrder(gradesBtn, this.getComponentCount() - 1);
    }
    
    public void addStudent(List<Student> students){
        for(Student student : students){
            Object[] row = {
                student.getId(),
                student.getFirstName(),
                student.getMiddleName(),
                student.getLastName(),
                student.getGender(),
                student.getBirthDate(),
                student.getPhoneNumber(),
                student.getAddress()
            };
            
            model.addRow(row);
        }
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