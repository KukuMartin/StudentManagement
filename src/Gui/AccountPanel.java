package Gui;

import Gui.Pane.Validate.AccountPick;
import Gui.Pane.Validate.SignIn;
import Tool.Gui.Palette;
import Tool.Gui.Signal;
import java.awt.Dimension;
import javax.swing.JLayeredPane;

public class AccountPanel extends Panel{
    private AccountPick accountPick;
    private SignIn signIn;
    
    public AccountPanel(MainFrame frame, Dimension size, Palette palette) {
        super(frame, size, palette);
        Signal teacher = this.getTeacherSignal();
        Signal advisor = this.getAdvisorSignal();
        Signal exit = this.getBackSignal();
        
        accountPick = new AccountPick(size, palette, advisor, teacher);
        
        this.setBackground(palette.getNeutral());
        this.setMiddle(null, accountPick);
    }
    
    private Signal getAdvisorSignal(){
        AdvisorPanel advisor = new AdvisorPanel(frame, size, palette, this.getExitSignal());
        Signal signIn = frame.getSignal(advisor);
        Signal exit = this.getBackSignal();
        Signal signal = new Signal(){
            @Override
            public void sendNotif(){
                AccountPanel.this.signIn = new SignIn(size, palette, exit, signIn);
                AccountPanel.this.setMiddle(accountPick, AccountPanel.this.signIn);
            }
        };
        return signal;
    }
    
    private Signal getTeacherSignal(){
        TeacherPanel teacher = new TeacherPanel(frame, size, palette, this.getExitSignal());
        Signal signIn = frame.getSignal(teacher);
        Signal exit = this.getBackSignal();
        Signal signal = new Signal(){
            @Override
            public void sendNotif(){
                AccountPanel.this.signIn = new SignIn(size, palette, exit, signIn);
                AccountPanel.this.setMiddle(accountPick, AccountPanel.this.signIn);
            }
        };
        return signal;
    }
    
    private Signal getExitSignal(){
        Signal signal = new Signal(){
            @Override
            public void sendNotif(){
                frame.setPanel(AccountPanel.this);
            }
        };
        return signal;
    }
    
    private Signal getBackSignal(){
        Signal signal = new Signal(){
            @Override
            public void sendNotif(){
                AccountPanel.this.setMiddle(signIn, accountPick);
            }
        };
        return signal;
    }
}
