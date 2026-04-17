package Gui;

import Gui.Pane.Account.AccountView;
import Gui.Pane.Account.SectionEdit;
import Gui.Pane.NavBar;
import Tool.Gui.Palette;
import Tool.Gui.Signal;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class AdvisorPanel extends Panel{
    private NavBar navbar;
    private AccountView account;
    private SectionEdit section;
    boolean activeAccount = true;
    
    public AdvisorPanel(MainFrame frame, Dimension size, Palette palette, Signal exit) {
        super(frame, size, palette);
        
        navbar = new NavBar(size, palette, exit);
        account = new AccountView(size, palette);
        section = new SectionEdit(size, palette);
        
        navbar.addButton(this.getAccoutButton(), this.getAccountAction());
        navbar.addButton(this.getSectionButton(), this.getSectionAction());
        
        this.setBackground(palette.getNeutral());
        this.setMiddle(null, account);
        this.setNavBar(navbar);
    }
    
    private JButton getAccoutButton(){
        JButton button = new JButton("Account");
        button.setBackground(palette.getSecondary());
        button.setForeground(palette.getTextDark());
        
        button.addMouseListener(navbar.getMouseAll(button));
        return button;
    }
    
    private ActionListener getAccountAction(){
        ActionListener event = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!activeAccount){
                    AdvisorPanel.this.setMiddle(section, account);
                    activeAccount = true;
                }            }
        };
        return event;
    }
    
    private JButton getSectionButton(){
        JButton button = new JButton("Section");
        button.setBackground(palette.getSecondary());
        button.setForeground(palette.getTextDark());
        
        button.addMouseListener(navbar.getMouseAll(button));
        return button;
    }
    
    private ActionListener getSectionAction(){
        ActionListener event = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(activeAccount){
                    AdvisorPanel.this.setMiddle(account, section);
                    activeAccount = false;
                }
            }
        };
        return event;
    }
}
