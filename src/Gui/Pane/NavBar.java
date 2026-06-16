package Gui.Pane;

import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import Gui.Misc.Tool.Signal;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

public class NavBar extends Pane{
    final private List<JButton> buttons = new ArrayList<>();
    final private Dimension size = new Dimension();
    
    final static private double heightPercent = 0.1;
    
    //specifics
    final private int xPadding = 10;
    final private int yPadding = 10;
    final private int space = 5;
    
    final private int buttonWidth = 100;
    final private int buttonHeight;
    
    public NavBar(Dimension frame, Palette palette, Label label){
        super(frame, palette, label);
        this.size.width = frame.width - xPadding;
        this.size.height = (int)(heightPercent * frame.height);
        
        buttonHeight = this.size.height;
        
        this.setBackground(this.palette.getSecondary());
        this.setSize(this.size.width, this.size.height);
        
        
        this.addMouseListener(this.getClickableJPanel(this));
    }
    
    //java documentation testing
    /**
     * adds a button in navBar for both event create an anonymous class
     * @param button the button gonna be used
     * @param actionEvent what happens when clicked
     */
    
    public void addButton(JButton button, Signal signal){
        int index = buttons.size();
        int x, y, padding;
        
        
        x = xPadding + ((space + buttonWidth) * index);
        y = yPadding;
        
        button.addActionListener(signal.getActionEvent());
        button.addMouseListener(this.getClickableComponent(button));
        
        this.setUpButton(button, new Dimension(buttonWidth, buttonHeight), new Point(x, y));
        buttons.add(button);
    }
    
    public void addSignOut(Signal signOut){
        int x, y;
        JButton button = this.getSquareButton("< Exit", 8); //TODO: turn into imageIcon
        
        button.setBackground(palette.getAccent());
        button.setForeground(palette.getTextLight());
        
        button.setFont(label.getBody());
        
        button.addActionListener(signOut.getActionEvent());
        button.addMouseListener(this.getClickableComponent(button));
        
        x = frame.width - buttonWidth - 22;
        y = yPadding;
        
        this.setUpButton(button, new Dimension(buttonWidth, buttonHeight), new Point(x, y));
    }
    
    public static double getHeightPercent(){
        return heightPercent;
    }
}
