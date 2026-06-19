package Gui.Frame.Subject;

import Gui.Frame.Frame;
import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivityAdd extends Frame {

    private JLabel lblTitle, lblType, lblName, lblScore;
    private JComboBox<String> cboType;
    private JTextField txtName, txtScore;
    private JButton btnSubmit;

    public ActivityAdd(String title, Dimension size, Palette palette, Label label) {
        super(title, size, palette, label, JFrame.DISPOSE_ON_CLOSE);

        lblTitle = new JLabel("ADD ASSESSMENT");
        lblTitle.setBounds(130, 10, 200, 25);
        add(lblTitle);

        lblType = new JLabel("Assessment Type:");
        lblType.setBounds(20, 50, 120, 25);
        lblType.setFont(label.getBody());
        add(lblType);

        cboType = new JComboBox<>(new String[]{
                "Written Task",
                "Performance Task",
                "Midterm",
                "Finals"
        });

        cboType.setBounds(150, 50, 180, 25);
        cboType.setBackground(palette.getNeutral());
        cboType.setForeground(palette.getTextDark());
        cboType.setFont(label.getCaption());
        add(cboType);

        lblName = new JLabel("Student Name:");
        lblName.setBounds(20, 100, 120, 25);
        lblName.setFont(label.getBody());
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(150, 100, 180, 25);
        txtName.setBackground(palette.getNeutral());
        txtName.setForeground(palette.getTextDark());
        txtName.setFont(label.getCaption());
        add(txtName);

        lblScore = new JLabel("Score:");
        lblScore.setBounds(20, 150, 120, 25);
        add(lblScore);

        txtScore = new JTextField();
        txtScore.setBounds(150, 150, 180, 25);
        txtScore.setBackground(palette.getNeutral());
        txtScore.setForeground(palette.getTextDark());
        txtScore.setFont(label.getCaption());
        add(txtScore);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(150, 200, 100, 30);
        btnSubmit.setBackground(palette.getPrimary());
        btnSubmit.setForeground(palette.getTextLight());
        btnSubmit.setFont(label.getCaption());
        add(btnSubmit);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String type =
                        (String) cboType.getSelectedItem();

                String name =
                        txtName.getText().trim();

                String score =
                        txtScore.getText().trim();

                if(name.isEmpty() || score.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Please fill all fields!"
                    );

                    return;
                }

                JOptionPane.showMessageDialog(
                        null,
                        "Assessment Saved!"
                                + "\n\nType: " + type
                                + "\nName: " + name
                                + "\nScore: " + score
                );

                txtName.setText("");
                txtScore.setText("");
            }
        });

        setVisible(true);
    }
}