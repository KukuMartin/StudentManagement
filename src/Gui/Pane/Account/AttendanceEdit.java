package Gui.Pane.Account;

import Gui.Frame.AttendanceAdd;
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

public class AttendanceEdit extends Pane{

    private JTable table;
    private DefaultTableModel model;

    public AttendanceEdit(Dimension frame, Palette palette, Label label, Signal subject) {
        super(frame, palette, label);

        this.setLayout(null);
        this.setSize(frame);

        initUI(frame, palette, subject);
        
        model.addRow(new Object[]{"2024-001", "Present", "Absent", "Late", "Present", "Excused", "Present", "Present", "Late", "Present", "Absent", "Present", "Present"});
        model.addRow(new Object[]{"2024-002", "Present", "Present", "Present", "Late", "Present", "Absent", "Present", "Present", "Excused", "Present", "Present", "Late"});
        model.addRow(new Object[]{"2024-003", "Absent", "Late", "Present", "Present", "Present", "Present", "Absent", "Late", "Present", "Present", "Excused", "Present"});
        model.addRow(new Object[]{"2024-004", "Present", "Present", "Present", "Present", "Present", "Present", "Present", "Present", "Present", "Present", "Present", "Present"});
        model.addRow(new Object[]{"2024-005", "Late", "Present", "Absent", "Present", "Present", "Excused", "Present", "Present", "Late", "Present", "Absent", "Present"});
    }

    private void initUI(Dimension frame, Palette palette, Signal subject) {
        String[] columns = {
            "Student Id",
            "Jan 10",
            "Jan 11",
            "Jan 12",
            "Jan 13",
            "Jan 14",
            "Jan 15",
            "Jan 16",
            "Jan 17",
            "Jan 18",
            "Jan 19",
            "Jan 20",
            "Jan 21"
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

        JButton attendanceBtn = this.getButton("Attendance", 8);
        attendanceBtn.setBackground(palette.getPrimary());
        attendanceBtn.setForeground(palette.getTextLight());

        setComponent(gradesBtn, new Dimension(70, 40), new Point(40, 60));
        setComponent(attendanceBtn, new Dimension(100, 40), new Point(120, 60));

        // hover n clikc
        gradesBtn.addMouseListener(getMouseEvent(gradesBtn));
        attendanceBtn.addMouseListener(getMouseEvent(attendanceBtn));
        gradesBtn.addActionListener(subject.getActionEvent());
    
    
        
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
    
    public Signal getAddSignal(){
        Signal signal = new Signal(){
            @Override
            public void sendSignal(){
                new AttendanceAdd("Add an activity", new Dimension(380, 320), palette, label);
            }
        };
        return signal;
    }
    
    public Signal getRemoveSignal(){
        Signal signal = new Signal(){
            @Override
            public void sendSignal(){
                int confirm = JOptionPane.showConfirmDialog(
                AttendanceEdit.this,
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
