package Gui.Panel;

import Gui.Frame.Frame;
import Gui.Pane.Account.AccountView;
import Gui.Pane.Account.SectionEdit;
import Gui.Pane.NavBar;
import Gui.Misc.Tool.Signal;
import School.Model.Account.Section;
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
    int advisorId;
    
    public AdvisorPanel(Frame frame, Signal signOut, AdvisorSystem advisorSystem) {
        super(frame);
        
        this.advisorSystem = advisorSystem;
        
        navbar = new NavBar(size, palette, label);
        navbar.addSignOut(signOut);
        
        account = new AccountView(size, palette, label);
        section = new SectionEdit(size, palette, label);
        
        navbar.addButton(this.getAccoutButton(), this.getAccountSignal());
        navbar.addButton(this.getSectionButton(), this.getSectionSignal());
        
        this.setBackground(palette.getNeutral());
        this.setPane(account, Layer.MIDDLE);
        this.setPane(navbar, Layer.TOP);
        
    }
    
    public void start(int advisorId){
        this.advisorId = advisorId;
        
        SectionSystem sectionSystem = advisorSystem.getSectionSystem();
        StudentSystem studentSystem = sectionSystem.getStudentSystem();
        Section section = sectionSystem.getAllSections(advisorId).get(0);
        this.section.addStudent(section, studentSystem);
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
    
    public void setFields(int accountId, String username, String firstName, String middleName, String lastName, String gender, String phoneNumber, LocalDate birthDate, String address) {
        account.setFields(accountId, username, firstName, middleName, lastName, gender, phoneNumber, birthDate, address);
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