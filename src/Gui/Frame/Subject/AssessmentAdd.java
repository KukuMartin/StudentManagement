package Gui.Frame.Subject;

import Gui.Frame.Frame;
import Gui.Frame.MainFrame;
import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AssessmentAdd extends Frame implements ActionListener {

    private JTextField txtName;
    private JTextField txtPercent;

    private JButton btnSubmit;
    private JButton btnCancel;

    public AssessmentAdd(String title, Dimension size, Palette palette, Label label) {

        super(title, size, palette, label, JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        int frameWidth = getDimension().width;

        JPanel header = new JPanel();
        header.setBounds(0, 0, frameWidth, 70);
        header.setBackground(getPalette().getPrimary());
        add(header);

        int titleWidth = 500;
        int titleX = (frameWidth - titleWidth) / 2;
        JLabel lblTitle = new JLabel("ADD ASSESSMENT", SwingConstants.CENTER);
        lblTitle.setBounds(titleX, 85, titleWidth, 40);
        lblTitle.setFont(getLabel().getHeading());
        lblTitle.setForeground(getPalette().getTextDark());
        add(lblTitle);

        int fieldWidth = 320;
        int fieldX = (frameWidth - fieldWidth) / 2;

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(fieldX, 140, fieldWidth, 25);
        lblName.setFont(getLabel().getBody());
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(fieldX, 170, fieldWidth, 35);
        txtName.setBackground(getPalette().getNeutral());
        txtName.setForeground(getPalette().getTextDark());
        txtName.setFont(getLabel().getCaption());
        add(txtName);

        JLabel lblPercent = new JLabel("Percent:");
        lblPercent.setBounds(fieldX, 220, fieldWidth, 25);
        lblPercent.setFont(getLabel().getBody());
        add(lblPercent);

        txtPercent = new JTextField();
        txtPercent.setBounds(fieldX, 250, fieldWidth, 35);
        txtPercent.setBackground(getPalette().getNeutral());
        txtPercent.setForeground(getPalette().getTextDark());
        txtPercent.setFont(getLabel().getCaption());
        add(txtPercent);

        int btnWidth = 140;
        int btnGap = 20;
        int totalBtnWidth = (btnWidth * 2) + btnGap;
        int btnX = (frameWidth - totalBtnWidth) / 2;

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(btnX, 315, btnWidth, 40);
        btnSubmit.setFont(getLabel().getBody());
        btnSubmit.setBackground(getPalette().getPrimary());
        btnSubmit.setForeground(getPalette().getTextLight());
        btnSubmit.addActionListener(this);
        add(btnSubmit);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(btnX + btnWidth + btnGap, 315, btnWidth, 40);
        btnCancel.setFont(getLabel().getBody());
        btnCancel.setBackground(getPalette().getPrimary());
        btnCancel.setForeground(getPalette().getTextLight());
        btnCancel.addActionListener(this);
        add(btnCancel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSubmit) {

            String name = txtName.getText().trim();
            String percentText = txtPercent.getText().trim();

            if (name.isEmpty() || percentText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all fields!"
                );
                return;
            }

            double percent;

            try {

                percent = Double.parseDouble(percentText);

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Percent must be a number!"
                );
                return;
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Assessment Saved!"
                            + "\n\nName: " + name
                            + "\nPercent: " + percent
            );

            txtName.setText("");
            txtPercent.setText("");
        }

        if (e.getSource() == btnCancel) {

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to cancel?",
                    "Confirm Cancel",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                dispose();
            }
        }
    }

    public static void main(String[] args) {

        AssessmentAdd frame = new AssessmentAdd(
                "Add Assessment",
                MainFrame.createSize(),
                MainFrame.createPalette(),
                MainFrame.createLabel()
        );

        frame.setVisible(true);
    }
}