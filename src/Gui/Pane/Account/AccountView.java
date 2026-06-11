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

    private JLabel windowTitle, accID, accType, phone, birthday, accAddress;

    private JTextField idField, typeField, phoneField, birthdayField;
    private JTextArea addressArea;

    private JButton mgButton;
    private boolean toggleEdit = false;

    public AccountView(Dimension frame, Palette palette, Label label) {
        super(frame, palette, label);
        this.save = save;
        this.setSize(frame);
        this.setBackground(palette.getBackground());

        windowTitle = new JLabel("Your Account");
        windowTitle.setFont(label.getHeading());
        this.setComponent(windowTitle, getSize(windowTitle, 260), 0.5, 0.17);

        accID = new JLabel("Account ID:");
        accID.setFont(label.getBody());
        this.setComponent(accID, getSize(accID, 200), 0.2, 0.27);

        idField = this.getTextField("", 8);
        idField.setBackground(palette.getNeutral());
        this.setComponent(idField, false, new Dimension(500, 30), 0.55, 0.27);

        accType = new JLabel("Username:");
        accType.setFont(label.getBody());
        this.setComponent(accType, getSize(accType, 200), 0.2, 0.37);

        typeField = this.getTextField("", 8);
        typeField.setBackground(palette.getNeutral());
        this.setComponent(typeField, false, new Dimension(500, 30), 0.55, 0.37);

        phone = new JLabel("Phone Number:");
        phone.setFont(label.getBody());
        this.setComponent(phone, getSize(phone, 200), 0.2, 0.47);

        phoneField = this.getTextField("", 8);
        phoneField.setBackground(palette.getNeutral());
        this.setComponent(phoneField, false, new Dimension(500, 30), 0.55, 0.47);

        birthday = new JLabel("Birth Date:");
        birthday.setFont(label.getBody());
        this.setComponent(birthday, getSize(birthday, 200), 0.2, 0.57);

        birthdayField = this.getTextField("", 8);
        birthdayField.setBackground(palette.getNeutral());
        this.setComponent(birthdayField, false, new Dimension(500, 30), 0.55, 0.57);

        mgButton = this.getButton("Manage", 8);
        mgButton.setBackground(palette.getSecondary());
        mgButton.setForeground(palette.getTextLight());
        mgButton.setFont(label.getBody());
        this.setComponent(mgButton, getSize(mgButton, 150), 0.5, 0.85);

        mgButton.addMouseListener(this.getMouseEvent(mgButton));
        mgButton.addActionListener(this.getManageSignal().getActionEvent());
    }

    public void setFields(int accountId, String username, String phoneNumber, LocalDate birthDate) {

        idField.setText(String.valueOf(accountId));
        typeField.setText(username);
        phoneField.setText(phoneNumber);
        birthdayField.setText(birthDate != null ? birthDate.toString() : "");
    }

    private Signal getManageSignal() {
        return new Signal() {
            @Override
            public void sendSignal() {

                toggleEdit = !toggleEdit;
                
                mgButton.setText(toggleEdit ? "Done?" : "Manage");

                idField.setEditable(toggleEdit);
                typeField.setEditable(toggleEdit);
                phoneField.setEditable(toggleEdit);
                birthdayField.setEditable(toggleEdit);
                addressArea.setEditable(toggleEdit);
            }
        };
    }
}