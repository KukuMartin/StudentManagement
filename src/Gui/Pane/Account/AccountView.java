package Gui.Pane.Account;

import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import Gui.Misc.Tool.Signal;
import Gui.Pane.Pane;
import java.awt.Dimension;
import javax.swing.*;

public class AccountView extends Pane{
    private JLabel windowTitle, accID, accType, phone, birthday, accAddress;
    private JTextField idField, typeField, phoneField, birthdayField;
    private JTextArea addressArea;
    private JButton mgButton;
    private boolean toggleEdit = false;
    
    public AccountView(Dimension frame, Palette palette, Label label)
    {
        super(frame,palette, label);
        this.setSize(frame);
        this.setBackground(palette.getBackground());

        windowTitle = new JLabel("Your Account");
        windowTitle.setFont(label.getHeading());
        this.setComponent(windowTitle, getSize(windowTitle, 260), 0.5, 0.17);

        accID = new JLabel("Account ID:");
        accID.setFont(label.getBody());
        this.setComponent(accID, getSize(accID, 200), 0.2, 0.27);

        idField = this.getTextField("2022-00560-MT-5", 8);
        idField.setBackground(palette.getNeutral());
        this.setComponent(idField, false, new Dimension(500, 30), 0.55, 0.27);

        accType = new JLabel("Account Type:");
        accType.setFont(label.getBody());
        this.setComponent(accType, getSize(accType, 200), 0.2, 0.37);

        typeField = this.getTextField("User", 8);
        typeField.setBackground(palette.getNeutral());
        this.setComponent(typeField, false, new Dimension(500, 30), 0.55, 0.37);

        phone = new JLabel("Phone Number:");
        phone.setFont(label.getBody());
        this.setComponent(phone, getSize(phone, 200), 0.2, 0.47);

        phoneField = this.getTextField("0935-456-6784", 8);
        phoneField.setBackground(palette.getNeutral());
        this.setComponent(phoneField, false, new Dimension(500, 30), 0.55, 0.47);

        birthday = new JLabel("Birth Date:");
        birthday.setFont(label.getBody());
        this.setComponent(birthday, getSize(birthday, 200), 0.2, 0.57);

        birthdayField = this.getTextField("October 5 2026", 8);
        birthdayField.setBackground(palette.getNeutral());
        birthdayField.setFont(label.getCaption());
        this.setComponent(birthdayField, false, new Dimension(500, 30), 0.55, 0.57);
        
        accAddress = new JLabel("Address:");
        accAddress.setFont(label.getBody());
        this.setComponent(accAddress, getSize(accAddress, 200), 0.2, 0.67);

        addressArea = this.getTextArea("Unit 5B, Sampaguita Residences\n" +
                "1234 Mabini Street, Barangay San Isidro\n" +
                "Santa Rosa City, Laguna 4026\n" +
                "Philippines", 8);
        addressArea.setEditable(false);
        addressArea.setBackground(palette.getNeutral());
        addressArea.setFont(label.getCaption());

        JScrollPane addressScrollPane = new JScrollPane(addressArea);
        addressScrollPane.setBorder(null);
        addressScrollPane.setBackground(palette.getSecondary());
        addressScrollPane.setViewportView(addressArea);
        addressScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        addressScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setComponent(addressScrollPane, new Dimension(500, 60), 0.55, 0.705);

        mgButton = this.getButton("Manage", 8);
        mgButton.setBackground(palette.getSecondary());
        mgButton.setForeground(palette.getTextLight());
        mgButton.setFont(label.getBody());
        this.setComponent(mgButton, getSize(mgButton, 150), 0.5, 0.82);
        mgButton.addMouseListener(this.getMouseEvent(mgButton));
        mgButton.addActionListener(this.getManageSignal().getActionEvent());
    }
    
    private Signal getManageSignal(){
        Signal signal = new Signal(){
            @Override
            public void sendSignal(){
                toggleEdit = !toggleEdit;
                if(toggleEdit){
                    mgButton.setText("Done?");
                } else{
                    mgButton.setText("Manage");
                }

                idField.setEditable(toggleEdit);
                typeField.setEditable(toggleEdit);
                phoneField.setEditable(toggleEdit);
                birthdayField.setEditable(toggleEdit);
                addressArea.setEditable(toggleEdit);
            }
        };
        return signal;
    }
}