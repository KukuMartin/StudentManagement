package Gui.Frame.Account;

import Gui.Frame.Frame;
import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import Gui.Misc.Tool.Signal;
import School.Model.Account.Type.Advisor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AdvisorEdit extends Frame {
    
    JLabel titleHeader, lblUsername, lblFirstName, lblLastName, lblMiddleName, lblGender, lblBirthdate, lblAddress, lblPhone, lblPassword;
    JTextField txtUsername, txtFirstName, txtLastName, txtMiddleName, txtAddress, txtPhone, txtPassword;
    JComboBox<String> cmbGender;
    JButton save, cancel;
    JSpinner date;

    public AdvisorEdit(String title, Dimension size, Palette palette, Label label, int operation, Signal save, String purpose, String buttonName, Advisor advisor) {
        super(title, size, palette, label, operation);

        setTitle(purpose);
        setSize(830, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(palette.getNeutral());

        JPanel topBar = new JPanel();
        topBar.setBackground(palette.getAccent());
        topBar.setBounds(0, 0, 850, 40);
        add(topBar);

        titleHeader = new JLabel("Add Teacher");
        titleHeader.setFont(label.getHeading());
        titleHeader.setForeground(palette.getTextDark());
        titleHeader.setBounds(40, 50, 400, 50);
        add(titleHeader);

        lblUsername = new JLabel("Username");
        lblUsername.setFont(label.getBody());
        lblUsername.setBounds(40, 120, 150, 20);
        add(lblUsername);

        txtUsername = new JTextField(advisor.getUsername());
        txtUsername.setBounds(40, 145, 350, 30);
        add(txtUsername);

        lblFirstName = new JLabel("First Name");
        lblFirstName.setFont(label.getBody());
        lblFirstName.setBounds(40, 195, 150, 20);
        add(lblFirstName);

        txtFirstName = new JTextField(advisor.getFirstName());
        txtFirstName.setBounds(40, 220, 220, 30);
        add(txtFirstName);

        lblLastName = new JLabel("Last Name");
        lblLastName.setFont(label.getBody());
        lblLastName.setBounds(280, 195, 150, 20);
        add(lblLastName);

        txtLastName = new JTextField(advisor.getLastName());
        txtLastName.setBounds(280, 220, 220, 30);
        add(txtLastName);

        lblMiddleName = new JLabel("Middle Name");
        lblMiddleName.setFont(label.getBody());
        lblMiddleName.setBounds(520, 195, 150, 20);
        add(lblMiddleName);

        txtMiddleName = new JTextField(advisor.getMiddleName());
        txtMiddleName.setBounds(520, 220, 250, 30);
        add(txtMiddleName);

        // --- Shifted Gender components left into the old Section columns ---
        lblGender = new JLabel("Gender");
        lblGender.setFont(label.getBody());
        lblGender.setBounds(40, 270, 150, 20);
        add(lblGender);

        String[] genders = {"MALE", "FEMALE", "OTHER"};
        cmbGender = new JComboBox<>(genders);
        cmbGender.setBounds(40, 295, 220, 30);
        cmbGender.setBackground(palette.getBackground());
        cmbGender.setSelectedItem(advisor.getGender());
        cmbGender.setForeground(palette.getTextDark());
        add(cmbGender);

        // --- Shifted Birthdate components left into the old Gender columns ---
        lblBirthdate = new JLabel("Birthdate");
        lblBirthdate.setFont(label.getBody());
        lblBirthdate.setBounds(280, 270, 150, 20);
        add(lblBirthdate);

        date = new JSpinner(new SpinnerDateModel());
        date.setValue(advisor.getBirthDate());
        date.setBounds(280, 295, 220, 30);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(date, "MM/dd/yyyy");
        date.setEditor(editor);
        add(date);

        lblAddress = new JLabel("Address");
        lblAddress.setFont(label.getBody());
        lblAddress.setBounds(40, 345, 150, 20);
        add(lblAddress);

        txtAddress = new JTextField(advisor.getAddress());
        txtAddress.setBounds(40, 370, 730, 30);
        add(txtAddress);

        lblPhone = new JLabel("Phone No.");
        lblPhone.setFont(label.getBody());
        lblPhone.setBounds(40, 420, 150, 20);
        add(lblPhone);

        txtPhone = new JTextField(advisor.getPhoneNumber());
        txtPhone.setBounds(40, 445, 350, 30);
        add(txtPhone);

        lblPassword = new JLabel("Password");
        lblPassword.setFont(label.getBody());
        lblPassword.setBounds(420, 120, 150, 20);
        add(lblPassword);

        txtPassword = new JTextField(advisor.getPhoneNumber());
        txtPassword.setBounds(420, 145, 350, 30);
        add(txtPassword);

        this.save = new JButton(buttonName);
        this.save.setBounds(540, 500, 110, 35);
        this.save.setBackground(palette.getPrimary());
        this.save.setForeground(palette.getTextLight());
        this.save.setFont(label.getBody());
        this.save.setFocusable(false);
        this.save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdvisorEdit.this, "Teacher Information Saved!");
                save.sendSignal();
                dispose();
            }
        });
        add(this.save);

        cancel = new JButton("Cancel");
        cancel.setBounds(660, 500, 110, 35);
        cancel.setBackground(palette.getSecondary());
        cancel.setForeground(palette.getTextLight());
        cancel.setFont(label.getBody());
        cancel.setFocusable(false);
        cancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(AdvisorEdit.this, 
                        "Are you sure you want to cancel?", 
                        "Confirm Cancel", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
        add(cancel);
    }
}