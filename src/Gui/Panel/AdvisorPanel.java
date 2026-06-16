package Gui.Panel;

import Gui.Frame.Frame;
import Gui.Pane.Account.AccountView;
import Gui.Pane.Account.SectionEdit;
import Gui.Pane.NavBar;
import Gui.Misc.Tool.Signal;
import School.System.Account.SectionSystem;
import School.System.Account.Type.AdvisorSystem;
import School.System.Account.Type.StudentSystem;
import java.time.LocalDate;
import javax.swing.JButton;

public class AdvisorPanel extends Panel{
    private NavBar navbar;
    private AccountView account;
    private SectionEdit section;
    
    private AdvisorSystem advisorSystem;
    String advisorName;
    
    public AdvisorPanel(Frame frame, Signal signOut, AdvisorSystem advisorSystem) {
        super(frame);
        
        advisorSystem = advisorSystem;
        
        navbar = new NavBar(size, palette, label);
        navbar.addSignOut(signOut);
        
        account = new AccountView(size, palette, label);
        section = new SectionEdit(size, palette, label);
        
        navbar.addButton(this.getAccoutButton(), this.getAccountSignal());
        navbar.addButton(this.getSectionButton(), this.getSectionSignal());
        
        this.setBackground(palette.getNeutral());
        this.setPane(account, Layer.MIDDLE);
        this.setPane(navbar, Layer.TOP);
        
        StudentSystem studentSystem = advisorSystem.getSectionSystem().getStudentSystem();
        section.addStudent(studentSystem.getStudents(1));
    }
    
    private JButton getAccoutButton(){
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
                    AdvisorPanel.this.setPane(account, Layer.MIDDLE);
                }   
            }
        };
        return signal;
    }
    
    private JButton getSectionButton(){
        JButton button = account.getSquareButton("Section", 8);
        
        button.setBackground(palette.getPrimary());
        button.setForeground(palette.getTextLight());
        
        button.setFont(label.getBody());
        
        button.addMouseListener(navbar.getClickableComponent(button));
        return button;
    }
    
    public void setFields(int accountId, String username, String phoneNumber, LocalDate birthDate) {
        account.setFields(accountId, username, phoneNumber, birthDate);
    }
    
    private Signal getSectionSignal(){
        Signal signal = new Signal(){
            @Override
            public void sendSignal() {
                if(!section.getIsActive()){
                    AdvisorPanel.this.setPane(section, Layer.MIDDLE);
                }           
            }
        };
        return signal;
    }
}