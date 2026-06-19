package Gui.Frame.Account;

import Gui.Frame.Frame;
import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import Gui.Misc.Tool.Signal;
import School.Model.Account.Account;
import School.Model.Account.Type.Student;
import School.System.Account.AccountSystem;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StudentAdd extends Frame {

    JLabel titleHeader, lblStudentID, lblCourse, lblFirstName, lblLastName, lblMiddleName,
           lblGender, lblBirthdate, lblAddress, lblPhone;

    JTextField txtStudentID, txtCourse, txtFirstName, txtLastName, txtMiddleName,
               txtAddress, txtPhone;

    JComboBox<String> cmbGender;
    JButton save, cancel;
    JSpinner date;

    public StudentAdd(String title, Dimension size, Palette palette, Label label,
                      Signal save, String purpose, Student student) {

        super(title, size, palette, label, JFrame.DISPOSE_ON_CLOSE);

        setTitle(purpose);
        setSize(830, 650); // Slightly increased window height for extra fields
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(palette.getBackground());

        JPanel topBar = new JPanel();
        topBar.setBackground(palette.getAccent());
        topBar.setBounds(0, 0, 850, 40);
        add(topBar);

        titleHeader = new JLabel("Student Information");
        titleHeader.setFont(label.getHeading());
        titleHeader.setForeground(palette.getTextDark());
        titleHeader.setBounds(40, 50, 400, 50);
        add(titleHeader);

        // --- ROW 1: STUDENT ID & COURSE ---
        lblStudentID = new JLabel("Student ID");
        lblStudentID.setFont(label.getBody());
        lblStudentID.setBounds(40, 115, 150, 20);
        add(lblStudentID);

        txtStudentID = new JTextField(student.getStudentId());
        txtStudentID.setBounds(40, 140, 350, 30);
        add(txtStudentID);

        lblCourse = new JLabel("Course");
        lblCourse.setFont(label.getBody());
        lblCourse.setBounds(420, 115, 150, 20);
        add(lblCourse);

        txtCourse = new JTextField(student.getCourse());
        txtCourse.setBounds(420, 140, 350, 30);
        add(txtCourse);

        lblFirstName = new JLabel("First Name");
        lblFirstName.setFont(label.getBody());
        lblFirstName.setBounds(40, 185, 150, 20);
        add(lblFirstName);

        txtFirstName = new JTextField(student.getFirstName());
        txtFirstName.setBounds(40, 210, 220, 30);
        add(txtFirstName);

        lblLastName = new JLabel("Last Name");
        lblLastName.setFont(label.getBody());
        lblLastName.setBounds(520, 185, 150, 20);
        add(lblLastName);

        txtLastName = new JTextField(student.getLastName());
        txtLastName.setBounds(520, 210, 250, 30);
        add(txtLastName);

        lblMiddleName = new JLabel("Middle Name");
        lblMiddleName.setFont(label.getBody());
        lblMiddleName.setBounds(280, 185, 150, 20);
        add(lblMiddleName);

        txtMiddleName = new JTextField(student.getMiddleName());
        txtMiddleName.setBounds(280, 210, 220, 30);
        add(txtMiddleName);

        lblGender = new JLabel("Gender");
        lblGender.setFont(label.getBody());
        lblGender.setBounds(40, 255, 150, 20);
        add(lblGender);

        String[] genders = {"MALE", "FEMALE", "OTHER"};
        cmbGender = new JComboBox<>(genders);
        cmbGender.setBounds(40, 280, 220, 30);
        cmbGender.setBackground(palette.getBackground());
        cmbGender.setForeground(palette.getTextDark());
        cmbGender.setSelectedItem(student.getGender());
        add(cmbGender);

        lblBirthdate = new JLabel("Birthdate");
        lblBirthdate.setFont(label.getBody());
        lblBirthdate.setBounds(280, 255, 150, 20);
        add(lblBirthdate);

        date = new JSpinner(new SpinnerDateModel());
        date.setBounds(280, 280, 220, 30);
        if (student.getBirthDate() != null) {
            date.setValue(java.sql.Date.valueOf(student.getBirthDate()));
        }
        JSpinner.DateEditor editor = new JSpinner.DateEditor(date, "MM/dd/yyyy");
        date.setEditor(editor);
        add(date);

        lblPhone = new JLabel("Phone No.");
        lblPhone.setFont(label.getBody());
        lblPhone.setBounds(520, 255, 150, 20);
        add(lblPhone);

        txtPhone = new JTextField(student.getPhoneNumber());
        txtPhone.setBounds(520, 280, 250, 30);
        add(txtPhone);

        lblAddress = new JLabel("Address");
        lblAddress.setFont(label.getBody());
        lblAddress.setBounds(40, 325, 150, 20);
        add(lblAddress);

        txtAddress = new JTextField(student.getAddress());
        txtAddress.setBounds(40, 350, 730, 30);
        add(txtAddress);

        this.save = new JButton("Submit");
        this.save.setBounds(540, 540, 110, 35);
        this.save.setBackground(palette.getPrimary());
        this.save.setForeground(palette.getTextLight());
        this.save.setFont(label.getBody());
        this.save.setFocusable(false);

        this.save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentID = txtStudentID.getText().trim();
                String course = txtCourse.getText().trim();
                String firstName = txtFirstName.getText().trim();
                String lastName = txtLastName.getText().trim();
                String middleName = txtMiddleName.getText().trim();
                String gender = (String) cmbGender.getSelectedItem();
                String address = txtAddress.getText().trim();
                String phone = txtPhone.getText().trim();

                java.util.Date spinnerDate = (java.util.Date) date.getValue();
                java.time.LocalDate birthDate = spinnerDate.toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDate();

                Student updatedStudent = new Student(
                    student.getId(),       
                    studentID,   
                    course,      
                    student.getAccountId(),    
                    student.getSectionId(),     
                    null,
                    firstName,                 
                    middleName,                
                    lastName,                
                    gender,                      
                    birthDate,                 
                    phone,
                    address                 
                );

                student.Update(updatedStudent);
                student.Update((Account)updatedStudent);

                save.sendSignal();
                dispose();
            }
        });
        add(this.save);

        cancel = new JButton("Cancel");
        cancel.setBounds(660, 540, 110, 35);
        cancel.setBackground(palette.getPrimary());
        cancel.setForeground(palette.getTextLight());
        cancel.setFont(label.getBody());
        cancel.setFocusable(false);

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(
                        StudentAdd.this,
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
        add(cancel);
        
        this.setVisible(true);
    }
}