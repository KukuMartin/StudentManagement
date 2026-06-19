package Gui.Panel;

import Gui.Frame.Frame;
import Gui.Pane.Account.AccountView;
import Gui.Pane.Account.SubjectsEdit;
import Gui.Pane.NavBar;
import Gui.Misc.Tool.Signal;
import Gui.Pane.Account.AttendanceEdit;
import School.System.Account.Type.TeacherSystem;
import java.time.LocalDate;
import javax.swing.JButton;

public class TeacherPanel extends Panel{
    private NavBar navbar;
    private AccountView account;
    private SubjectsEdit subject;
    
    String teacherName;
    
    public TeacherPanel(Frame frame, Signal signOut) {
        super(frame);
        
        navbar = new NavBar(size, palette, label);
        navbar.addSignOut(signOut);
        account = new AccountView(size, palette, label);
        subject = new SubjectsEdit(size, palette, label);
        subject.addAttendance(this.getAttendanceSignal());
        
        navbar.addButton(this.getAccountButton(), this.getAccountSignal());
        navbar.addButton(this.getSubjectButton(), this.getSubjectSignal());
        
        this.setBackground(palette.getNeutral());
        this.setPane(account, Layer.MIDDLE);
        this.setPane(navbar, Layer.TOP);
    }
    
    private JButton getAccountButton(){
        JButton button = account.getSquareButton("Account", 8);
        
        button.setBackground(palette.getPrimary());
        button.setForeground(palette.getTextLight());
        
        button.setFont(label.getBody());
        button.addMouseListener(navbar.getClickableComponent(button));
        return button;
    }
    
    private Signal getAccountSignal(){
        Signal signal = new Signal(){
            @Override
            public void sendSignal() {
                if(!account.getIsActive()){
                    TeacherPanel.this.setPane(account, Layer.MIDDLE);
                }            }
        };
        return signal;
    }
    
    private JButton getSubjectButton(){
        JButton button = account.getSquareButton("Subject", 8);
        
        button.setBackground(palette.getPrimary());
        button.setForeground(palette.getTextLight());
        
        button.setFont(label.getBody());
        
        button.addMouseListener(navbar.getClickableComponent(button));
        return button;
    }
    
    private Signal getSubjectSignal(){
        Signal signal = new Signal(){
            @Override
            public void sendSignal() {
                if(!subject.getIsActive()){
                    TeacherPanel.this.setPane(subject, Layer.MIDDLE);
                }
            }
        };
        return signal;
    }
    
    public void setFields(int accountId, String username, String firstName, String middleName, String lastName, String gender, String phoneNumber, LocalDate birthDate, String address) {
        account.setFields(accountId, username, firstName, middleName, lastName, gender, phoneNumber, birthDate, address);
    }
    
    private Signal getAttendanceSignal(){
        AttendanceEdit attendance = new AttendanceEdit(size, palette, label, this.getSignal(subject, Layer.MIDDLE));
        return this.getSignal(attendance, Layer.MIDDLE);
    }
}