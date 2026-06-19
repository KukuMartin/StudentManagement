package Gui.Pane.Account;

import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import Gui.Misc.Tool.Signal;
import Gui.Pane.Pane;
import School.Model.Account.Type.Teacher;
import School.System.Account.AccountSystem;
import School.System.Account.Type.TeacherSystem;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class TeacherHold extends Pane {

    private JList<String> list;
    private DefaultListModel<String> model;
    private JScrollPane scrollPane;

    private TeacherSystem teacherSystem;
    private List<Teacher> teachers;

    private JButton addBtn, removeBtn, updateBtn;

    public TeacherHold(Dimension frame, Palette palette, Label label) {
        super(frame, palette, label);

        this.setLayout(null);
        this.setSize(frame);
    }

    private void initUI() {
        model = new DefaultListModel<>();
        list = new JList<>(model);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(label.getBody());
        list.setBackground(palette.getBackground());
        list.setForeground(palette.getTextDark());
        list.setSelectionBackground(Palette.lighten(palette.getPrimary(), .5f));
        list.setSelectionForeground(palette.getTextLight());
        list.setFixedCellHeight(28);

        // --- UPDATED LAYOUT METRICS ---
        int sideMargin = 40;
        int topSpace = 125;      // Changed from 90 to 125 to sit below the title
        int bottomSpace = 55;    // Changed from 90 to 55 to keep the layout proportional
        int listWidth = frame.width - (sideMargin * 2) - 130; 
        
        // Your title stays here safely at y = 69
        JLabel titleLabel = new JLabel("Teacher Accounts");
        titleLabel.setFont(label.getHeading()); 
        titleLabel.setForeground(palette.getTextDark());
        titleLabel.setBounds(sideMargin, 69, 400, 40); 
        this.add(titleLabel);

        // The scrollPane and buttons will automatically shift down using 'topSpace'
        scrollPane = new JScrollPane(list);
        scrollPane.setBounds(sideMargin, topSpace, listWidth, frame.height - topSpace - bottomSpace);
        this.add(scrollPane);

        int buttonX = sideMargin + listWidth + 20;
        int buttonWidth = 110;
        int buttonHeight = 35;
        int buttonGap = 15;

        addBtn = this.getSquareButton("Add", 8);
        addBtn.setBackground(palette.getPrimary());
        addBtn.setForeground(palette.getTextLight());
        addBtn.setFont(label.getBody());
        this.setUpButton(addBtn, new Dimension(buttonWidth, buttonHeight),
                new Point(buttonX, topSpace));
        addBtn.addMouseListener(this.getClickableComponent(addBtn));
        addBtn.addActionListener(getAddListener());

        removeBtn = this.getSquareButton("Remove", 8);
        removeBtn.setBackground(palette.getPrimary());
        removeBtn.setForeground(palette.getTextLight());
        removeBtn.setFont(label.getBody());
        this.setUpButton(removeBtn, new Dimension(buttonWidth, buttonHeight),
                new Point(buttonX, topSpace + buttonHeight + buttonGap));
        removeBtn.addMouseListener(this.getClickableComponent(removeBtn));
        removeBtn.addActionListener(getRemoveListener());

        updateBtn = this.getSquareButton("Update", 8);
        updateBtn.setBackground(palette.getPrimary());
        updateBtn.setForeground(palette.getTextLight());
        updateBtn.setFont(label.getBody());
        this.setUpButton(updateBtn, new Dimension(buttonWidth, buttonHeight),
                new Point(buttonX, topSpace + (buttonHeight + buttonGap) * 2));
        updateBtn.addMouseListener(this.getClickableComponent(updateBtn));
        updateBtn.addActionListener(getUpdateListener());
    }

    public void addTeacher(TeacherSystem teacherSystem) {
        this.teacherSystem = teacherSystem;
        initUI();
        refresh();
    }

    private void refresh() {
        model.clear();
        teachers = teacherSystem.getAllTeachers();
        for (Teacher teacher : teachers) {
            model.addElement(teacher.getUsername());
        }
    }

    private ActionListener getAddListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        };
    }

    private ActionListener getRemoveListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();

                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a teacher to remove.");
                    return;
                }

                Teacher teacher = teachers.get(selectedIndex);

                int response = JOptionPane.showConfirmDialog(
                        TeacherHold.this,
                        "Remove " + teacher.getUsername() + "?",
                        "Confirm Remove",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (response != JOptionPane.YES_OPTION) {
                    return;
                }

                
                AccountSystem accountSystem = teacherSystem.getAccountSystem();
                accountSystem.deleteAccount(teacher.getAccountId());
                refresh();
            }
        };
    }
    
    private ActionListener getUpdateListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();

                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a teacher to update.");
                    return;
                }

                Teacher teacher = teachers.get(selectedIndex);

            }
        };
    }

    public JList<String> getList() {
        return list;
    }
}