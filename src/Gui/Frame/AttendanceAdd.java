package Gui.Frame;

import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import java.awt.Dimension;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Date;

public class AttendanceAdd extends Frame {

    private JLabel lblTitle, lblName, lblDate;
    private JTextField txtName;
    private JSpinner dateSpinner;
    private JButton btnSubmit;

    public AttendanceAdd(String title, Dimension size, Palette palette, Label label) {
        super(title, size, palette, label);
        lblTitle = new JLabel("ATTENDANCE RECORD");
        lblTitle.setBounds(120, 10, 200, 25);
        add(lblTitle);

        lblName = new JLabel("Student Name:");
        lblName.setBounds(20, 70, 120, 25);
        lblName.setFont(label.getBody());
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(150, 70, 180, 25);
        txtName.setBackground(palette.getNeutral());
        txtName.setForeground(palette.getTextDark());
        txtName.setFont(label.getCaption());
        add(txtName);

        lblDate = new JLabel("Date Present:");
        lblDate.setFont(label.getBody());
        lblDate.setBounds(20, 120, 120, 25);
        add(lblDate);

        dateSpinner = new JSpinner(
                new SpinnerDateModel()
        );

        JSpinner.DateEditor editor =
                new JSpinner.DateEditor(
                        dateSpinner,
                        "MMMM dd, yyyy"
                );

        dateSpinner.setEditor(editor);

        dateSpinner.setBounds(150, 120, 180, 25);
        dateSpinner.setBackground(palette.getNeutral());
        dateSpinner.setForeground(palette.getTextDark());
        dateSpinner.setFont(label.getCaption());

        add(dateSpinner);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(150, 180, 100, 30);
        btnSubmit.setBackground(palette.getPrimary());
        btnSubmit.setForeground(palette.getTextLight());
        btnSubmit.setFont(label.getCaption());
        add(btnSubmit);
        
      

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name =
                        txtName.getText().trim();

                Date selectedDate =
                        (Date) dateSpinner.getValue();

                if(name.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter student name!"
                    );

                    return;
                }

                JOptionPane.showMessageDialog(
                        null,
                        "Attendance Saved!"
                                + "\n\nStudent: " + name
                                + "\nDate Present: " + selectedDate
                );

                txtName.setText("");
            }
        });

        setVisible(true);
    }
}
