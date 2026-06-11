package Gui.Panel;

import Gui.Frame.Frame;
import Gui.Pane.Account.AccountView;
import Gui.Pane.Account.SectionEdit;
import Gui.Pane.NavBar;
import Gui.Misc.Tool.Signal;
import javax.swing.JButton;

public class AdvisorPanel extends Panel{
    private NavBar navbar;
    private AccountView account;
    private SectionEdit section;
    boolean activeAccount = true;
    
    String advisorName;
    
    public AdvisorPanel(Frame frame, Signal signOut) {
        super(frame);
        
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
    
    private JButton getAccoutButton(){
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
                if(activeAccount){
                    AdvisorPanel.this.setPane(section, Layer.MIDDLE);
                    activeAccount = false;
                }  
            }
        };
        return signal;
    }
    
    private JButton getSectionButton(){
        JButton button = account.getButton("Section", 8);
        
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
                    AdvisorPanel.this.setPane(section, Layer.MIDDLE);
                    activeAccount = false;
                }           }
        };
        return signal;
    }
}