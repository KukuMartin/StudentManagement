package Gui.Panel;

import Gui.Frame.Frame;
import Gui.Pane.Validate.AccountPick;
import Gui.Misc.Tool.Signal;
import Gui.Pane.NavBar;
import Gui.Pane.Pane;
import Gui.Pane.Validate.AccountSignIn;
import School.Model.Account.Type.Admin;
import School.Model.Account.Type.Advisor;
import School.Model.Account.Type.Teacher;
import School.System.Account.Type.AdminSystem;
import School.System.Account.Type.AdvisorSystem;
import School.System.Account.Type.TeacherSystem;
import School.System.SchoolSystem;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class AccountPanel extends Panel {
    SchoolSystem system;
    
    AdminSystem adminSystem;
    TeacherSystem teacherSystem;
    AdvisorSystem advisorSystem;
    
    AdminPanel adminPanel;
    TeacherPanel teacherPanel;
    AdvisorPanel advisorPanel;
    
    AccountSignIn signInTeacher;
    AccountSignIn signInAdvisor;
    AccountSignIn signInAdmin;

    public AccountPanel(Frame frame, SchoolSystem system) {
        super(frame);
        this.system = system;
        teacherSystem = system.getTeacherSystem();
        advisorSystem = system.getAdvisorSystem();
        adminSystem = system.getAdminSystem();

        NavBar navbar = new NavBar(size, palette, label);

        Signal signOut = frame.getSignal(this);
        teacherPanel = new TeacherPanel(frame, signOut);
        advisorPanel = new AdvisorPanel(frame, signOut, advisorSystem);
        adminPanel = new AdminPanel(frame, signOut, teacherSystem, advisorSystem);

        signInTeacher = new AccountSignIn(size, palette, label, getTeacherSignal());
        signInAdvisor = new AccountSignIn(size, palette, label, getAdvisorSignal());    
        signInAdmin = new AccountSignIn(size, palette, label, getAdminSignal());

        Signal teacherSignal = this.getSignal(signInTeacher, Layer.MIDDLE);
        Signal advisorSignal = this.getSignal(signInAdvisor, Layer.MIDDLE);
        Signal adminSignal = this.getSignal(signInAdmin, Layer.MIDDLE);

        Pane pick = new AccountPick(size, palette, label, adminSignal, advisorSignal, teacherSignal);
        
        signInTeacher.addBack(this.getSignal(pick, Layer.MIDDLE));
        signInAdvisor.addBack(this.getSignal(pick, Layer.MIDDLE));
        signInAdmin.addBack(this.getSignal(pick, Layer.MIDDLE));
        
        this.setPane(pick, Layer.MIDDLE);
        this.setPane(navbar, Layer.TOP);
    }

    private Signal getAdminSignal() {
        return new Signal() {
            @Override
            public void sendSignal() {
                Admin account = adminSystem.getAdmin(signInAdmin.getUsername());
                if(account != null && account.getPassword().equals(signInAdmin.getPassword())){
                    
                    frame.setPanel(adminPanel);
                    return;
                }
                JOptionPane.showMessageDialog(AccountPanel.this, "Login Unsuccessful!", "Try Again", JOptionPane.INFORMATION_MESSAGE);
            }
        };
    }
    
    private Signal getTeacherSignal(){
        return new Signal(){
            @Override
            public void sendSignal() {
                Teacher account = teacherSystem.getTeacher(signInTeacher.getUsername());
                if(account != null && account.getPassword().equals(signInTeacher.getPassword())){
                    teacherPanel.teacherName = account.getUsername();
                    teacherPanel.setFields(account.getAccountId(), account.getUsername(), account.getFirstName(), account.getMiddleName(), account.getLastName(), account.getGender(), account.getPhoneNumber(), account.getBirthDate(), account.getAddress());
                    frame.setPanel(teacherPanel);
                    return;
                }
                JOptionPane.showMessageDialog(AccountPanel.this, "Login Unsuccessful!", "Try Again", JOptionPane.INFORMATION_MESSAGE);
            }
        };
    }
    
    private Signal getAdvisorSignal(){
        return new Signal(){
            @Override
            public void sendSignal() {
                Advisor account = advisorSystem.getAdvisor(signInAdvisor.getUsername());
                if(account != null && account.getPassword().equals(signInAdvisor.getPassword())){
                    advisorPanel.start(account.getId());
                    advisorPanel.setFields(account.getAccountId(), account.getUsername(), account.getFirstName(), account.getMiddleName(), account.getLastName(), account.getGender(), account.getPhoneNumber(), account.getBirthDate(), account.getAddress());
                    frame.setPanel(advisorPanel);
                    return;
                }
                JOptionPane.showMessageDialog(AccountPanel.this, "Login Unsuccessful!", "Try Again", JOptionPane.INFORMATION_MESSAGE);
            }
        };
    }
}