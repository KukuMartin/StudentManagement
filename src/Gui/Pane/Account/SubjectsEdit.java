package Gui.Pane.Account;

import Gui.Frame.SubjectAdd;
import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import Gui.Misc.Tool.Signal;
import Gui.Pane.Pane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class SubjectsEdit extends Pane{

    private JTable table;
    private DefaultTableModel model;
    private JButton attendanceBtn;

    public SubjectsEdit(Dimension frame, Palette palette, Label label) {
        super(frame, palette, label);

        this.setLayout(null);
        this.setSize(frame);

        initUI(frame, palette);
        
        model.addRow(new Object[]{"2024-001", 90, 85, 88, 92, 87, 91, 89, 93, 94, 90, 95, 96});
        model.addRow(new Object[]{"2024-002", 84, 86, 80, 89, 90, 88, 87, 91, 92, 90, 93, 94});
        model.addRow(new Object[]{"2024-003", 78, 82, 85, 80, 84, 86, 88, 89, 87, 90, 91, 92});
        model.addRow(new Object[]{"2024-004", 95, 94, 96, 93, 92, 97, 98, 96, 95, 94, 99, 98});
        model.addRow(new Object[]{"2024-005", 88, 89, 90, 91, 87, 86, 85, 92, 93, 94, 90, 91});
    }

    private void initUI(Dimension frame, Palette palette) {
        String[] columns = {
            "Student Id",
            "WW#1",
            "WW#2",
            "WW#3",
            "WW#4",
            "WW#5",
            "WW#6",
            "WW#7",
            "PT#1",
            "PT#2",
            "PT#3",
            "PT#4",
            "Exam",
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
        JButton gradesBtn = this.getButton("Grade", 8);
        gradesBtn.setBackground(palette.getPrimary());
        gradesBtn.setForeground(palette.getTextLight());

        attendanceBtn = this.getButton("Attendance", 8);
        attendanceBtn.setBackground(palette.getPrimary());
        attendanceBtn.setForeground(palette.getTextLight());

        setComponent(gradesBtn, new Dimension(70, 40), new Point(40, 60));
        setComponent(attendanceBtn, new Dimension(100, 40), new Point(120, 60));

        // hover n clikc
        gradesBtn.addMouseListener(getMouseEvent(gradesBtn));
        attendanceBtn.addMouseListener(getMouseEvent(attendanceBtn));
    
    
        
         //new third=======
        JButton addcBtn = this.getButton("Add Column", 8);
        addcBtn.setBackground(palette.getPrimary());
        addcBtn.setForeground(palette.getTextLight());

        JButton delcBtn = this.getButton("Delete Column", 8);
        delcBtn.setBackground(palette.getPrimary());
        delcBtn.setForeground(palette.getTextLight());

        setComponent(addcBtn, new Dimension(110, 20), new Point(40, 390));
        setComponent(delcBtn, new Dimension(120, 20), new Point(170, 390));

        // hoverr n click
        addcBtn.addMouseListener(getMouseEvent(addcBtn));
        delcBtn.addMouseListener(getMouseEvent(delcBtn));
        addcBtn.addActionListener(this.getAddSignal().getActionEvent());
        delcBtn.addActionListener(this.getRemoveSignal().getActionEvent());

        
        //new first===========
        JScrollPane scrollPane = new JScrollPane(table);
        int topSpace = 90;     // space for Grades / Attendance buttons
        int bottomSpace = 90;  // space for Add / Delete buttons
        int sideMargin = 40;

        scrollPane.setBounds(sideMargin,topSpace,frame.width - (sideMargin * 2),frame.height - topSpace - bottomSpace);
        this.add(scrollPane);
        this.setComponentZOrder(gradesBtn, this.getComponentCount() - 1);
        this.setComponentZOrder(attendanceBtn, this.getComponentCount() - 1);
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
    
    public void addAttendance(Signal attendance){
        attendanceBtn.addActionListener(attendance.getActionEvent());
    }
    
    public Signal getAddSignal(){
        Signal signal = new Signal(){
            @Override
            public void sendSignal(){
                new SubjectAdd("Add an activity", new Dimension(380, 320), palette, label);
            }
        };
        return signal;
        }
    
    public Signal getRemoveSignal(){
        Signal signal = new Signal(){
            @Override
            public void sendSignal(){
                int confirm = JOptionPane.showConfirmDialog(
                SubjectsEdit.this,
                "Are you sure you want to delete this column?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
                );
            }
        };
        return signal;
    }
}
