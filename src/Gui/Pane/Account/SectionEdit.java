package Gui.Pane.Account;

import Gui.Frame.Account.StudentAdd;
import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import Gui.Misc.Tool.Signal;
import Gui.Pane.Pane;
import School.Model.Account.Account;
import School.Model.Account.Section;
import School.Model.Account.Type.Student;
import School.System.Account.AccountSystem;
import School.System.Account.Type.StudentSystem;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class SectionEdit extends Pane{
    public Student studentHolder;
    public Section section;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    List<Student> students;
    
    public SectionEdit(Dimension frame, Palette palette, Label label) {
        super(frame, palette, label);

        this.setLayout(null);
        this.setSize(frame);
    }

    private void initUI(Section section, StudentSystem studentSystem) {
        AccountSystem accountSystem = studentSystem.getAccountSystem();
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
        
        this.section = section;
        
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        
        
        
        model = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
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

        table.setBackground(palette.getBackground());
        table.setForeground(palette.getTextDark());
        table.setSelectionBackground(Palette.lighten(table.getBackground(), .5f));
        table.setSelectionForeground(Palette.lighten(table.getForeground(), .5f));
        table.setGridColor(new Color(200, 200, 200));
        
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(true);
        
        JButton gradesBtn = this.getSquareButton("Section", 8);
        gradesBtn.setBackground(palette.getPrimary());
        gradesBtn.setForeground(palette.getTextLight());
        setUpButton(gradesBtn, new Dimension(110, 40), new Point(40, 60));
        
        JButton addBtn = this.getSquareButton("Add", 8);
        addBtn.setBackground(palette.getPrimary());
        addBtn.setForeground(palette.getTextLight());
        setUpButton(addBtn, new Dimension(110, 30),
                new Point(40, frame.height - 80));
        addBtn.addMouseListener(this.getClickableComponent(addBtn));
        
        Signal save = new Signal() {
            @Override
            public void sendSignal() {
                AccountSystem accountSystem = studentSystem.getAccountSystem();
                int accountId = accountSystem.createAccount(studentHolder);
                if (accountId == -1) {
                    JOptionPane.showMessageDialog(null, "Account registration failed. Please ensure all fields are filled properly.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                studentHolder.sectionId = section.getId();
                studentHolder.accountId = accountId;
                studentSystem.createStudent(studentHolder);
                SectionEdit.this.resetStudent();
                
                JOptionPane.showMessageDialog(SectionEdit.this, "Student Information Saved!");
                SectionEdit.this.reloadTable(studentSystem);
            }
        };
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SectionEdit.this.resetStudent();
                new StudentAdd("Add a student", frame, palette, label, save, "Add a student", studentHolder);
            }
        });

        JButton removeBtn = this.getSquareButton("Remove", 8);
        removeBtn.setBackground(palette.getPrimary());
        removeBtn.setForeground(palette.getTextLight());
        setUpButton(removeBtn, new Dimension(110, 30),
                new Point(40 + 110 + 20, frame.height - 80));
        removeBtn.addMouseListener(this.getClickableComponent(removeBtn));
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete.");
                    return;
                }

                int modelRow = table.convertRowIndexToModel(selectedRow);
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                
                int studentId = (int) model.getValueAt(modelRow, 0);
                int accountId = studentSystem.getStudent(studentId).getAccountId();
                
                accountSystem.deleteAccount(accountId);
                model.removeRow(modelRow);
            }
        });

        JButton updateBtn = this.getSquareButton("Update", 8);
        updateBtn.setBackground(palette.getPrimary());
        updateBtn.setForeground(palette.getTextLight());
        setUpButton(updateBtn, new Dimension(110, 30),
                new Point(40 + (110 + 20) * 2, frame.height - 80));
        updateBtn.addMouseListener(this.getClickableComponent(updateBtn));
        updateBtn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(SectionEdit.this, "Select a row first.");
            return;
        }

        int modelRow = table.convertRowIndexToModel(selectedRow);
        
        // 1. Get the STUDENT ID from the table row (e.g., 2)
        int studentId = (int) table.getModel().getValueAt(modelRow, 0);

        // 2. Locate the student object
        Student studentToUpdate = SectionEdit.this.search(studentId);
        if (studentToUpdate == null) return;

        Signal updateSignal = new Signal() {
    @Override
    public void sendSignal() {
        AccountSystem accountSystem = studentSystem.getAccountSystem();
        
        // Build a distinct Account data object mapped strictly to its real account identity
        Account clearAccountData = new Account(
            studentToUpdate.getAccountId(), // Explicitly targets the account table key
            studentToUpdate.getFirstName(),
            studentToUpdate.getMiddleName(),
            studentToUpdate.getLastName(),
            studentToUpdate.getGender(),
            studentToUpdate.getBirthDate(),
            studentToUpdate.getPhoneNumber(),
            studentToUpdate.getAddress()
        );
        
        // Save both records completely decoupled from one another
        accountSystem.updateAccount(clearAccountData); 
        studentSystem.updateStudent(studentToUpdate); 
        
        JOptionPane.showMessageDialog(SectionEdit.this, "Student Information Successfully Updated!");
        SectionEdit.this.reloadTable(studentSystem);
    }
};

        new StudentAdd("Update Profile", frame, palette, label, updateSignal, "Update Details", studentToUpdate);
    }
});

        scrollPane = new JScrollPane(table);
        int topSpace = 90;     
        int bottomSpace = 90; 
        int sideMargin = 40;

        scrollPane.setBounds(sideMargin,topSpace,frame.width - (sideMargin * 2),frame.height - topSpace - bottomSpace);
        this.add(scrollPane);
        this.setComponentZOrder(gradesBtn, this.getComponentCount() - 1);
    }
    
    public void addStudent(Section section, StudentSystem studentSystem){
        initUI( section, studentSystem);
        this.students = studentSystem.getStudents(section.getId());
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
    
    public void reloadTable(StudentSystem studentSystem) {
    clearTable();
    
    this.students = studentSystem.getStudents(this.section.getId());
    
    for (Student student : students) {
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
    
    public void resetStudent(){
        studentHolder = new Student(0, "", "", 0, section.getId(), new java.util.ArrayList<>(), "", "", "", "", java.time.LocalDate.now(), "", "");
    }
    
    private Student search(int id){
        for(Student student : students){
            if(student.getId() == id){
                return student;
            }
        }
        return null;
    }
}