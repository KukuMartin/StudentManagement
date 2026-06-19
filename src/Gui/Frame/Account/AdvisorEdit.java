package Gui.Frame.Account;

import Gui.Frame.Frame;
import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import Gui.Misc.Tool.Signal;
import School.Model.Account.Account;
import School.Model.Account.Type.Advisor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.*;

public class AdvisorEdit extends Frame {
    
    private Advisor advisor;
    private String purpose;
    private Signal saveSignal;

    JLabel titleHeader, lblUsername, lblPassword, lblFirstName, lblLastName, lblMiddleName,
           lblGender, lblBirthdate, lblAddress, lblPhone;

    JTextField txtUsername, txtPassword, txtFirstName, txtLastName, txtMiddleName, 
               txtAddress, txtPhone;

    JComboBox<String> cmbGender;
    JButton saveButton, cancelButton;
    JSpinner dateSpinner;

    public AdvisorEdit (String title, Dimension size, Palette palette, Label label, Signal save, String purpose, Advisor advisor) {
        super(title, size, palette, label, JFrame.DISPOSE_ON_CLOSE);
        this.advisor = advisor;
        this.purpose = purpose;
        this.saveSignal = save;
        
        setTitle(purpose);
        setSize(830, 580); // Adjusted height since fields were shifted up
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(palette.getBackground());

        // Top bar implementation matching StudentAdd
        JPanel topBar = new JPanel();
        topBar.setBackground(palette.getAccent());
        topBar.setBounds(0, 0, 850, 40);
        add(topBar);

        // Header Title
        titleHeader = new JLabel("Advisor Information");
        titleHeader.setFont(label.getHeading());
        titleHeader.setForeground(palette.getTextDark());
        titleHeader.setBounds(40, 50, 400, 50);
        add(titleHeader);

        // ROW 1: Username & Password
        lblUsername = new JLabel("Username");
        lblUsername.setFont(label.getBody());
        lblUsername.setBounds(40, 115, 150, 20);
        add(lblUsername);

        txtUsername = new JTextField(advisor.getUsername());
        txtUsername.setBounds(40, 140, 350, 30);
        add(txtUsername);

        lblPassword = new JLabel("Password");
        lblPassword.setFont(label.getBody());
        lblPassword.setBounds(420, 115, 150, 20);
        add(lblPassword);

        txtPassword = new JTextField(advisor.getPassword());
        txtPassword.setBounds(420, 140, 350, 30);
        add(txtPassword);

        // ROW 2: First Name, Middle Name, Last Name
        lblFirstName = new JLabel("First Name");
        lblFirstName.setFont(label.getBody());
        lblFirstName.setBounds(40, 185, 150, 20);
        add(lblFirstName);

        txtFirstName = new JTextField(advisor.getFirstName());
        txtFirstName.setBounds(40, 210, 220, 30);
        add(txtFirstName);

        lblMiddleName = new JLabel("Middle Name");
        lblMiddleName.setFont(label.getBody());
        lblMiddleName.setBounds(280, 185, 150, 20);
        add(lblMiddleName);

        txtMiddleName = new JTextField(advisor.getMiddleName());
        txtMiddleName.setBounds(280, 210, 220, 30);
        add(txtMiddleName);

        lblLastName = new JLabel("Last Name");
        lblLastName.setFont(label.getBody());
        lblLastName.setBounds(520, 185, 150, 20);
        add(lblLastName);

        txtLastName = new JTextField(advisor.getLastName());
        txtLastName.setBounds(520, 210, 250, 30);
        add(txtLastName);

        // ROW 3: Gender, Birthdate, Phone No. (Shifted Up from Y:325 -> Y:255)
        lblGender = new JLabel("Gender");
        lblGender.setFont(label.getBody());
        lblGender.setBounds(40, 255, 150, 20);
        add(lblGender);

        String[] genders = {"MALE", "FEMALE", "OTHER"};
        cmbGender = new JComboBox<>(genders);
        cmbGender.setBounds(40, 280, 220, 30);
        cmbGender.setBackground(palette.getBackground());
        cmbGender.setForeground(palette.getTextDark());
        cmbGender.setSelectedItem(advisor.getGender());
        add(cmbGender);

        lblBirthdate = new JLabel("Birthdate");
        lblBirthdate.setFont(label.getBody());
        lblBirthdate.setBounds(280, 255, 150, 20);
        add(lblBirthdate);

        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setBounds(280, 280, 220, 30);
        if (advisor.getBirthDate() != null) {
            dateSpinner.setValue(Date.from(advisor.getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "MM/dd/yyyy");
        dateSpinner.setEditor(editor);
        add(dateSpinner);

        lblPhone = new JLabel("Phone No.");
        lblPhone.setFont(label.getBody());
        lblPhone.setBounds(520, 255, 150, 20);
        add(lblPhone);

        txtPhone = new JTextField(advisor.getPhoneNumber());
        txtPhone.setBounds(520, 280, 250, 30);
        add(txtPhone);

        // ROW 4: Address (Shifted Up from Y:395 -> Y:325)
        lblAddress = new JLabel("Address");
        lblAddress.setFont(label.getBody());
        lblAddress.setBounds(40, 325, 150, 20);
        add(lblAddress);

        txtAddress = new JTextField(advisor.getAddress());
        txtAddress.setBounds(40, 350, 730, 30);
        add(txtAddress);

        // ACTIONS: Submit Button (Shifted Up from Y:540 -> Y:470)
        this.saveButton = new JButton("Submit");
        this.saveButton.setBounds(540, 470, 110, 35);
        this.saveButton.setBackground(palette.getPrimary());
        this.saveButton.setForeground(palette.getTextLight());
        this.saveButton.setFont(label.getBody());
        this.saveButton.setFocusable(false);
        this.saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText().trim();
                String password = txtPassword.getText().trim();
                String firstName = txtFirstName.getText().trim();
                String lastName = txtLastName.getText().trim();
                String middleName = txtMiddleName.getText().trim();
                String gender = (String) cmbGender.getSelectedItem();
                String address = txtAddress.getText().trim();
                String phone = txtPhone.getText().trim();

                Date spinnerDate = (Date) dateSpinner.getValue();
                LocalDate birthDate = spinnerDate.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

                Advisor updatedAdvisor = new Advisor(
                    advisor.getId(),
                    username,
                    password,
                    advisor.getAccountId(),
                    advisor.getSection(),
                    firstName,
                    middleName,
                    lastName,
                    gender,
                    birthDate,
                    phone,
                    address
                );

                advisor.Update(updatedAdvisor);
                advisor.Update((Account) updatedAdvisor);

                saveSignal.sendSignal();
                dispose();
            }
        });
        add(this.saveButton);

        // ACTIONS: Cancel Button (Shifted Up from Y:540 -> Y:470)
        this.cancelButton = new JButton("Cancel");
        this.cancelButton.setBounds(660, 470, 110, 35);
        this.cancelButton.setBackground(palette.getPrimary());
        this.cancelButton.setForeground(palette.getTextLight());
        this.cancelButton.setFont(label.getBody());
        this.cancelButton.setFocusable(false);
        this.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(
                        AdvisorEdit.this,
                        "Are you sure you want to cancel?",
                        "Confirm Cancel",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (response == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
        add(this.cancelButton);

        this.setVisible(true);
    }
}