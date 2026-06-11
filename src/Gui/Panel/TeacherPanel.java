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
    boolean activeAccount = true;
    
    String teacherName;
    
    public TeacherPanel(Frame frame, Signal signOut) {
        super(frame);
        
        navbar = new NavBar(size, palette, label);
        navbar.addSignOut(signOut);
        account = new AccountView(size, palette, label);
        subject = new SubjectsEdit(size, palette, label);
        subject.addAttendance(this.getAttendanceSignal());
        
        navbar.addButton(this.getAccountButton(), this.getAccountSignal());
        navbar.addButton(this.getSectionButton(), this.getSectionSignal());
        
        this.setBackground(palette.getNeutral());
        this.setPane(account, Layer.MIDDLE);
        this.setPane(navbar, Layer.TOP);
    }
    
    private JButton getAccountButton(){
        JButton button = account.getButton("Account", 8);
        
        button.setBackground(palette.getPrimary());
        button.setForeground(palette.getTextLight());
        
        button.setFont(label.getBody());
        button.addMouseListener(navbar.getMouseEvent(button));
        return button;
    }
    
    private Signal getAccountSignal(){
        Signal signal = new Signal(){
            @Override
            public void sendSignal() {
                if(!activeAccount){
                    TeacherPanel.this.setPane(account, Layer.MIDDLE);
                    activeAccount = true;
                }            }
        };
        return signal;
    }
    
    private JButton getSectionButton(){
        JButton button = account.getButton("Subject", 8);
        
        button.setBackground(palette.getPrimary());
        button.setForeground(palette.getTextLight());
        
        button.setFont(label.getBody());
        
        button.addMouseListener(navbar.getMouseEvent(button));
        return button;
    }
    
    private Signal getSectionSignal(){
        Signal signal = new Signal(){
            @Override
            public void sendSignal() {
                if(activeAccount){
                    TeacherPanel.this.setPane(subject, Layer.MIDDLE);
                    activeAccount = false;
                }
            }
        };
        return signal;
    }
    public void setFields(int accountId, String username, String phoneNumber, LocalDate birthDate) {
        account.setFields(accountId, username, phoneNumber, birthDate);
    }
    
    private Signal getAttendanceSignal(){
        AttendanceEdit attendance = new AttendanceEdit(size, palette, label, this.getSignal(subject, Layer.MIDDLE));
        return this.getSignal(attendance, Layer.MIDDLE);
    }
}