package Gui.Pane;

import Tool.Gui.Palette;
import Tool.Gui.Signal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NavBar extends Pane{
    final private List<JButton> buttons = new ArrayList<>();
    final private Dimension frame = new Dimension();
    final private Signal exit;
    
    //specifics
    final private int xPadding = 10;
    final private int yPadding = 10;
    final private int space = 5;
    
    final private int buttonWidth = 100;
    final private int buttonHeight;
    
    public NavBar(Dimension frame, Palette palette, Signal exit){
        super(frame, palette);
        double heightPercent = 0.10;
        
        this.frame.width = frame.width - xPadding;
        this.frame.height = (int)(heightPercent * frame.height);
        
        buttonHeight = this.frame.height - (yPadding);
        
        this.palette = palette;
        this.exit = exit;
        this.setBackground(this.palette.getPrimary());
        
        this.setSize(this.frame.width, this.frame.height);
        this.setLayout(null);
        
        
        this.addExit();
        this.something();
    }
    
    private void something(){
        JButton button = new JButton("Hello");
        button.setLocation(this.getX(button, 0.5), this.getY(button, 0.75));
        button.setSize(100, 50);
        
        button.setBackground(Color.blue);
        
        this.add(button);
    }
    
    //java documentation testing
    /**
     * adds a button in navBar for both event create an anonymous class
     * @param button the button gonna be used
     * @param actionEvent what happens when clicked
     */
    public void addButton(JButton button, ActionListener actionEvent){
        int index = buttons.size();
        int x, y, padding;
        
        button.setBackground(palette.getSecondary());
        button.setForeground(palette.getTextLight());
        
        button.addActionListener(actionEvent);
        button.addMouseListener(this.getMouse(button));
        
        x = xPadding + ((space + buttonWidth) * index);
        y = yPadding;
        
        this.setButton(button, new Point(x, y), new Dimension(buttonWidth, buttonHeight));
        buttons.add(button);
    }
    
    private void addExit(){
        int x, y;
        JButton button = new JButton("Exit"); //TODO: turn into imageIcon
        
        button.setBackground(palette.getAccent());
        button.setForeground(palette.getTextLight());
        
        button.addActionListener(this.getAction(exit));
        button.addMouseListener(this.getMouse(button));
        
        x = frame.width - buttonWidth - xPadding;
        System.out.println(x);
        System.out.println(this.getWidth());
        y = yPadding;
        
        this.setButton(button, new Point(x, y), new Dimension(buttonWidth, buttonHeight));
    }
    
    
}
