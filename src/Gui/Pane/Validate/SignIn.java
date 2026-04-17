package Gui.Pane.Validate;

import Gui.Pane.Pane;
import School.Model.Account.Account;
import Tool.Gui.Palette;
import Tool.Gui.Signal;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SignIn extends Pane{
    private Signal backSignal;
    private JTextField userField;
    private JPasswordField passField;

    public SignIn(Dimension frame, Palette palette, Signal backSignal, Signal loginSignal) {
        super(frame, palette);
        
        JButton loginBtn, backBtn;
        JLabel title = new JLabel("SIGN IN", SwingConstants.CENTER);
        int width = 800;
        title.setBounds(getX(width, 0.5), 40, 800, 40);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(palette.getPrimary());
        add(title);

        JLabel userLabel = new JLabel("Username:");
        width = 100;
        userLabel.setBounds(getX(width, 0.4), 120, 100, 20);
        userLabel.setForeground(palette.getTextLight());
        add(userLabel);

        userField = new JTextField();
        width = 300;
        userField.setBounds(getX(width, 0.5), 145, 300, 40);
        userField.setBorder(null);
        userField.setBackground(palette.getSecondary());
        userField.setForeground(palette.getTextLight());
        add(userField);

        JLabel passLabel = new JLabel("Password: ");
        width = 100;
        passLabel.setBounds(getX(width, 0.4), 200, 100, 20);
        passLabel.setForeground(palette.getTextLight());
        add(passLabel);

        passField = new JPasswordField();
        width = 300; 
        passField.setBounds(getX(width, 0.5), 225,300, 40);
        passField.setBorder(null);
        passField.setBackground(palette.getSecondary());
        passField.setForeground(palette.getTextLight());
        add(passField);

        loginBtn = new JButton("LOGIN");
        width = 300;
        loginBtn.setBounds(getX(width, 0.5), 310, 300, 50);
        loginBtn.setBackground(palette.getSecondary());
        loginBtn.setForeground(palette.getTextLight());
        loginBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        loginBtn.addActionListener(this.getActionClick(loginSignal));
        loginBtn.addMouseListener(this.getMouseAll(loginBtn));
        this.setButton(loginBtn);

        backBtn = new JButton("< BACK");
        backBtn.setBounds(20, 20, 80, 30);
        backBtn.setBackground(palette.getAccent());
        backBtn.setForeground(palette.getTextLight());
        backBtn.addActionListener(this.getActionClick(backSignal));
        backBtn.addMouseListener(this.getMouseAll(backBtn));
        this.setButton(backBtn);
    }

    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        int width = 400;
        int height = 300;
        int arch = 20;
        
        g2d.setColor(palette.getPrimary());
        g2d.fillRoundRect(this.getX(width, 0.5), this.getY(height, 0.5), width, height, arch, arch);
    }
}
