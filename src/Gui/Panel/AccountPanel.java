package Gui.Panel;

import Gui.Frame.Frame;
import Gui.Pane.Validate.AccountPick;
import Gui.Misc.Tool.Signal;
import Gui.Pane.NavBar;
import Gui.Pane.Pane;
import Gui.Pane.Validate.AccountSignIn;

public class AccountPanel extends Panel{
    AccountSignIn signInTeacher;
    AccountSignIn signInAdvisor;
    public AccountPanel(Frame frame) {
        super(frame);
        
        NavBar navbar = new NavBar(size, palette, label);
        
        Signal signOut = frame.getSignal(this);
        TeacherPanel teacher = new TeacherPanel(frame, signOut);
        AdvisorPanel advisor = new AdvisorPanel(frame, signOut);
        
        Signal teacherSignal = this.getTeacherSignIn(frame.getSignal(teacher));
        Signal advisorSignal = this.getAdvisorSignIn(frame.getSignal(advisor));
        
        
        Pane pick = new AccountPick(size, palette, label, advisorSignal, teacherSignal);
        signInTeacher.addBack(this.getSignal(pick, Layer.MIDDLE));
        signInAdvisor.addBack(this.getSignal(pick, Layer.MIDDLE));
        this.setPane(pick, Layer.MIDDLE);
        this.setPane(navbar, Layer.TOP);
    }
    
    private Signal getTeacherSignIn(Signal account){
        signInTeacher = new AccountSignIn(size, palette, label, account);
        Signal sigInSignal = this.getSignal(signInTeacher, Layer.MIDDLE);
        
        return sigInSignal;
    }
    
    private Signal getAdvisorSignIn(Signal account){
        signInAdvisor = new AccountSignIn(size, palette, label, account);
        Signal sigInSignal = this.getSignal(signInAdvisor, Layer.MIDDLE);
        
        return sigInSignal;
    }
}