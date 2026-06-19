package Gui.Pane.Account;

import Gui.Frame.MainFrame;
import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import Gui.Misc.Tool.Signal;
import Gui.Pane.Pane;
import java.awt.Dimension;
import java.time.LocalDate;
import javax.swing.*;

public class AccountView extends Pane {

    private JLabel windowTitle, accType, fName, lName, mName, gender, phone, birthday, accAddress;

    private JTextField usernameField, phoneField, birthdayField, fNameField, lNameField, mNameField, genderField, addressArea;

    private boolean toggleEdit = false;

    public AccountView(Dimension frame, Palette palette, Label label) {
        super(frame, palette, label);
        this.setSize(frame);
        this.setBackground(palette.getBackground());

        windowTitle = new JLabel("Your Account");
        windowTitle.setFont(label.getHeading());
        this.setUpComponent(windowTitle, getSize(windowTitle, 260), 0.5, 0.17);
        this.add(windowTitle);

        accType = new JLabel("Username:");
        accType.setFont(label.getBody());
        this.setUpComponent(accType, getSize(accType, 200), 0.2, 0.27);
        this.add(accType);

        usernameField = this.getSquareTextField("", 8);
        usernameField.setBackground(palette.getNeutral());
        this.setUpText(usernameField, false, new Dimension(500, 30), 0.55, 0.27);
        this.add(usernameField);
        
        fName = new JLabel("First Name:");
        fName.setFont(label.getBody());
        this.setUpComponent(fName, getSize(fName, 200), 0.2, 0.34);
        this.add(fName);

        fNameField = this.getSquareTextField("", 8);
        fNameField.setBackground(palette.getNeutral());
        this.setUpText(fNameField, false, new Dimension(500, 30), 0.55, 0.34);
        this.add(fNameField);

        lName = new JLabel("Last Name:");
        lName.setFont(label.getBody());
        this.setUpComponent(lName, getSize(lName, 200), 0.2, 0.41);
        this.add(lName);

        lNameField = this.getSquareTextField("", 8);
        lNameField.setBackground(palette.getNeutral());
        this.setUpText(lNameField, false, new Dimension(500, 30), 0.55, 0.41);
        this.add(lNameField);

        mName = new JLabel("Middle Name:");
        mName.setFont(label.getBody());
        this.setUpComponent(mName, getSize(mName, 200), 0.2, 0.48);
        this.add(mName);

        mNameField = this.getSquareTextField("", 8);
        mNameField.setBackground(palette.getNeutral());
        this.setUpText(mNameField, false, new Dimension(500, 30), 0.55, 0.48);
        this.add(mNameField);

        gender = new JLabel("Gender:");
        gender.setFont(label.getBody());
        this.setUpComponent(gender, getSize(gender, 200), 0.2, 0.55);
        this.add(gender);

        genderField = this.getSquareTextField("", 8);
        genderField.setBackground(palette.getNeutral());
        this.setUpText(genderField, false, new Dimension(500, 30), 0.55, 0.55);
        this.add(genderField);

        phone = new JLabel("Phone Number:");
        phone.setFont(label.getBody());
        this.setUpComponent(phone, getSize(phone, 200), 0.2, 0.62);
        this.add(phone);

        phoneField = this.getSquareTextField("", 8);
        phoneField.setBackground(palette.getNeutral());
        this.setUpText(phoneField, false, new Dimension(500, 30), 0.55, 0.62);
        this.add(phoneField);

        birthday = new JLabel("Birth Date:");
        birthday.setFont(label.getBody());
        this.setUpComponent(birthday, getSize(birthday, 200), 0.2, 0.69);
        this.add(birthday);

        birthdayField = this.getSquareTextField("", 8);
        birthdayField.setBackground(palette.getNeutral());
        this.setUpText(birthdayField, false, new Dimension(500, 30), 0.55, 0.69);
        this.add(birthdayField);

        accAddress = new JLabel("Address:");
        accAddress.setFont(label.getBody());
        this.setUpComponent(accAddress, getSize(accAddress, 200), 0.2, 0.76);
        this.add(accAddress);

        addressArea = this.getSquareTextField("", 8);;
        addressArea.setFont(label.getCaption());
        addressArea.setBackground(palette.getNeutral());
        this.setUpText(addressArea, false, new Dimension(500, 30), 0.55, 0.76);
        this.add(addressArea);
    }

    public void setFields(int accountId, String username, String firstName,String middleName, String lastName, 
                        String gender, String phoneNumber, 
                        LocalDate birthDate, String address) {
        usernameField.setText(username);
        fNameField.setText(firstName);
        lNameField.setText(lastName);
        mNameField.setText(middleName != null ? middleName : "");
        genderField.setText(gender);
        phoneField.setText(phoneNumber);
        birthdayField.setText(birthDate != null ? birthDate.toString() : "");
        addressArea.setText(address != null ? address : "");
    }
}