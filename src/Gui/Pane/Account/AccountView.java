package Gui.Pane.Account;

import Gui.Pane.Pane;
import Tool.Gui.Palette;
import Tool.Gui.Signal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AccountView extends Pane{
    private JLabel windowTitle, accID, accType, accCredentials, accAddress;
    private JButton mgButton;
    
    public AccountView(Dimension frame, Palette palette)
    {
        super(frame,palette);
        this.setLayout(null);
        this.setSize(frame);

        int labelWidth = 200;
        int fieldWidth = 500;
        int fieldHeight = 40;

        Font font = new Font("SansSerif", Font.BOLD, 18);
        
        windowTitle = new JLabel("My Account");
        windowTitle.setBounds(this.getX(labelWidth, 0.535), 60, labelWidth, 30);
        windowTitle.setFont(new Font("SansSerif", Font.BOLD, 28));
        add(windowTitle);
        
        accID = new JLabel("Account ID:");
        accID.setBounds(this.getX(labelWidth, 0.2), 125, labelWidth, 25);
        accID.setFont(font);
        add(accID);

        JTextField idField = new JTextField();
        idField.setEditable(false);
        idField.setBackground(palette.getSecondary());
        idField.setBorder(null);
        idField.setBounds(this.getX(fieldWidth, 0.55), 125, fieldWidth, fieldHeight);
        add(idField);

        accType = new JLabel("Account Type:");
        accType.setBounds(this.getX(labelWidth, 0.2), 185, labelWidth, 25);
        accType.setFont(font);
        add(accType);

        JTextField typeField = new JTextField();
        typeField.setEditable(false);
        typeField.setBackground(palette.getSecondary());
        typeField.setBorder(null);
        typeField.setBounds(this.getX(fieldWidth, 0.55), 185, fieldWidth, fieldHeight);
        add(typeField);

        accCredentials = new JLabel("Credentials:");
        accCredentials.setBounds(this.getX(labelWidth, 0.2), 245, labelWidth, 25);
        accCredentials.setFont(font);
        add(accCredentials);

        JTextField credentialsField = new JTextField();
        credentialsField.setEditable(false);
        credentialsField.setBackground(palette.getSecondary());
        credentialsField.setBorder(null);
        credentialsField.setBounds(this.getX(fieldWidth, 0.55), 245, fieldWidth, fieldHeight);
        add(credentialsField);

        accAddress = new JLabel("Address:");
        accAddress.setBounds(this.getX(labelWidth, 0.2), 305, labelWidth, 25);
        accAddress.setFont(font);
        add(accAddress);

        JTextField addressField = new JTextField();
        addressField.setEditable(false);
        addressField.setBackground(palette.getSecondary());
        addressField.setBorder(null);
        addressField.setBounds(this.getX(fieldWidth, 0.55), 305, fieldWidth, fieldHeight);
        add(addressField);

        mgButton = new JButton("Manage");
        mgButton.setBounds(this.getX(100, 0.5), 360, 100, 30);
        
        mgButton.setBackground(palette.getSecondary());
        mgButton.setForeground(palette.getTextLight());
        
        mgButton.addMouseListener(this.getMouseAll(mgButton));
        this.setButton(mgButton);
      
        add(windowTitle);
        add(accID);
        add(accType);
        add(accCredentials);
        add(accAddress);
        add(mgButton);
    }
}
