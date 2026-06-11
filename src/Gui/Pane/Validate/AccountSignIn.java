package Gui.Pane.Validate;

import Gui.Pane.Pane;
import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import Gui.Misc.Tool.Signal;
import java.awt.Dimension;
import javax.swing.*;

public class AccountSignIn extends Pane {
    JTextField AccF;
    JPasswordField PassyF;
    
    public AccountSignIn(Dimension size, Palette palette, Label label, Signal account) {
        super(size, palette, label);

        this.setBackground(palette.getBackground());
        this.setSize(size);

        JLabel Logintitle = new JLabel("Log in to your SMS");
        Logintitle.setFont(label.getHeading());
        this.setComponent(Logintitle, getSize(Logintitle, 380), 0.5, 0.24);

        JLabel accId = new JLabel("Username:");
        accId.setFont(label.getBody());
        this.setComponent(accId, getSize(accId, 150), .3, 0.35);

        AccF = this.getTextField(8);
        AccF.setFont(label.getCaption());
        AccF.setBackground(palette.getNeutral());
        this.setComponent(AccF, true, new Dimension(300, 30), 0.5, 0.35);
        
        JLabel passy = new JLabel("Password:");
        passy.setFont(label.getBody());
        this.setComponent(passy, getSize(passy, 150), 0.3, 0.45);

        PassyF = this.getPasswordField(8);
        PassyF.setFont(label.getCaption());
        PassyF.setBackground(palette.getNeutral());
        this.setComponent(PassyF, true, new Dimension(300, 30), 0.5, 0.45);

        JLabel undertext = new JLabel("If you have not set any Two-Factor Authentication (2FA), simply press Log In to continue.");
        undertext.setFont(label.getBody());
        this.setComponent(undertext, new Dimension(650, undertext.getPreferredSize().height), 0.5, 0.74);

        JButton login = this.getButton("Log in", 10);
        login.setBackground(palette.getAccent());
        login.setForeground(palette.getTextLight());
        login.setFont(label.getBody());
        this.setComponent(login, new Dimension(150, 30), 0.5, 0.82);
        login.addActionListener(account.getActionEvent());
        login.addMouseListener(this.getMouseEvent(login));
    }
    
    public void addBack(Signal pick){
        JButton back = this.getButton("< Back", 10);
        back.setBackground(palette.getAccent());
        back.setForeground(palette.getTextLight());
        back.setFont(label.getBody());
        
        this.setComponent(back, this.getSize(back, 90), .062, .15);
        back.addActionListener(pick.getActionEvent());
        back.addMouseListener(this.getMouseEvent(back));
    }
    
    public String getUsername(){
        return AccF.getText();
    }
    
    public String getPassword(){
        return new String(PassyF.getPassword());
    }
}