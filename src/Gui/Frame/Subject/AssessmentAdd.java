package Gui.Frame.Subject;

import Gui.Frame.Frame;
import Gui.Frame.MainFrame;
import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AssessmentAdd extends Frame implements ActionListener {

    private int idCounter = 1;
    private List<SubjectModel> subjects = new ArrayList<>();

    private JTextField txtName;
    private JTextField txtCode;
    private JTextField txtStart;
    private JTextField txtEnd;
    private JPanel header;
    private JButton btnAdd;
    private JButton btnRemove;
    private JButton btnSave;

    private JTable table;
    private DefaultTableModel model;

    public AssessmentAdd(String title, Dimension size, Palette palette, Label label) {

        super(title, size, palette, label, JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        header = new JPanel();
        header.setBounds(0, 0, getDimension().width, 70);
        header.setBackground(getPalette().getPrimary());
        add(header);

        JLabel lblTitle = new JLabel("ADD ASSESSMENT", SwingConstants.CENTER);
        lblTitle.setBounds(165, 75, 500, 40);
        lblTitle.setFont(getLabel().getHeading());
        lblTitle.setForeground(getPalette().getTextDark());
        add(lblTitle);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(30, 135, 120, 30);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(150, 135, 200, 35);
        add(txtName);

        JLabel lblCode = new JLabel("Subject Code:");
        lblCode.setBounds(30, 195, 120, 30);
        add(lblCode);

        txtCode = new JTextField();
        txtCode.setBounds(150, 195, 200, 35);
        add(txtCode);

        JLabel lblStart = new JLabel("Schedule Start:");
        lblStart.setBounds(30, 260, 120, 30);
        add(lblStart);

        txtStart = new JTextField();
        txtStart.setBounds(150, 260, 200, 35);
        add(txtStart);

        JLabel lblEnd = new JLabel("Schedule End:");
        lblEnd.setBounds(30, 320, 120, 30);
        add(lblEnd);

        txtEnd = new JTextField();
        txtEnd.setBounds(150, 320, 200, 35);
        add(txtEnd);

        
        model = new DefaultTableModel(
                new String[]{"ID", "Name", "Code", "Start", "End"},
                0
        );

        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(370, 120, 450, 250);

        add(scroll);

        // BUTTONS
        btnAdd = new JButton("Add");
        btnAdd.setBounds(570, 375, 120, 40);
        btnAdd.setFont(getLabel().getBody());
        btnAdd.setBackground(getPalette().getPrimary());
        btnAdd.setForeground(getPalette().getTextLight());
        btnAdd.addActionListener(this);
        add(btnAdd);

        btnRemove = new JButton("Remove");
        btnRemove.setBounds(700, 375, 120, 40);
        btnRemove.setFont(getLabel().getBody());
        btnRemove.setBackground(getPalette().getPrimary());
        btnRemove.setForeground(getPalette().getTextLight());
        btnRemove.addActionListener(this);
        add(btnRemove);

        btnSave = new JButton("Save");
        btnSave.setBounds(350, 510, 120, 40);
        btnSave.setFont(getLabel().getBody());
        btnSave.setBackground(getPalette().getPrimary());
        btnSave.setForeground(getPalette().getTextLight());
        btnSave.addActionListener(this);
        add(btnSave);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAdd) {

            try {

                SubjectModel s = new SubjectModel(
                        idCounter++,
                        txtName.getText(),
                        txtCode.getText(),
                        LocalTime.parse(txtStart.getText()),
                        LocalTime.parse(txtEnd.getText())
                );

                subjects.add(s);

                model.addRow(new Object[]{
                        s.id,
                        s.name,
                        s.code,
                        s.scheduleStart,
                        s.scheduleEnd
                });

                txtName.setText("");
                txtCode.setText("");
                txtStart.setText("");
                txtEnd.setText("");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Use HH:mm format"
                );
            }
        }

        if (e.getSource() == btnRemove) {

            int row = table.getSelectedRow();

            if (row == -1 && model.getRowCount() > 0) {
                row = model.getRowCount() - 1;
            }

            if (row >= 0) {

                subjects.remove(row);
                model.removeRow(row);
            }
        }

        if (e.getSource() == btnSave) {

            JOptionPane.showMessageDialog(
                    this,
                    "Subjects saved: " + subjects.size()
            );

            dispose();
        }
    }

    private static class SubjectModel {

        private int id;
        private String name;
        private String code;
        private LocalTime scheduleStart;
        private LocalTime scheduleEnd;

        public SubjectModel(
                int id,
                String name,
                String code,
                LocalTime scheduleStart,
                LocalTime scheduleEnd
        ) {

            this.id = id;
            this.name = name;
            this.code = code;
            this.scheduleStart = scheduleStart;
            this.scheduleEnd = scheduleEnd;
        }
    }
    
    public static void main(String[] args) {

        AssessmentAdd frame = new AssessmentAdd(
                "Subject Schedule",
                MainFrame.createSize(),
                MainFrame.createPalette(),
                MainFrame.createLabel()
        );

        frame.setVisible(true);
    }
}
