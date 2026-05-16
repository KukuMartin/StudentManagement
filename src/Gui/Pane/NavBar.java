package Gui.Pane;

import Tool.Gui.Label;
import Tool.Gui.Palette;
import Tool.Gui.Signal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
    
    final static private double heightPercent = 0.1;
    
    //specifics
    final private int xPadding = 10;
    final private int yPadding = 10;
    final private int space = 5;
    
    final private int buttonWidth = 100;
    final private int buttonHeight;
    
    public NavBar(Dimension frame, Palette palette, Label label, Signal exit){
        super(frame, palette, label);
        
        this.frame.width = frame.width - xPadding;
        this.frame.height = (int)(heightPercent * frame.height);
        
        buttonHeight = this.frame.height - (yPadding);
        
        this.setBackground(this.palette.getPrimary());
        this.setSize(this.frame.width, this.frame.height);
        this.setLayout(null);
        
        
        this.addExit(exit);
    }
    
    //java documentation testing
    /**
     * adds a button in navBar for both event create an anonymous class
     * @param button the button gonna be used
     * @param actionEvent what happens when clicked
     */
    private void createButton(JButton button, Signal signal){
        JPanel newButton = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
            }
        };
        
        button.setBackground(palette.getSecondary());
        button.setForeground(palette.getTextLight());
        
        button.addActionListener(signal.getActionEvent());
        button.addMouseListener(this.getMouseEvent(button));
    }
    
    public void addButton(JButton button, ActionListener actionEvent){
        int index = buttons.size();
        int x, y, padding;
        
        
        x = xPadding + ((space + buttonWidth) * index);
        y = yPadding;
        
        this.setComponent(button, new Dimension(buttonWidth, buttonHeight), new Point(x, y));
        buttons.add(button);
    }
    
    private void addExit(Signal signal){
        int x, y;
        JButton button = new JButton("Exit"); //TODO: turn into imageIcon
        
        button.setBackground(palette.getAccent());
        button.setForeground(palette.getTextLight());
        
        button.addActionListener(signal.getActionEvent());
        button.addMouseListener(this.getMouseEvent(button));
        
        x = frame.width - buttonWidth - xPadding;
        System.out.println(x);
        System.out.println(this.getWidth());
        y = yPadding;
        
        this.setComponent(button, new Dimension(buttonWidth, buttonHeight), new Point(x, y));
    }
    
    public static double getHeightPercent(){
        return heightPercent;
    }
}
