package Gui.Pane;

import Tool.Gui.Label;
import Tool.Gui.Palette;
import Tool.Gui.Signal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public abstract class Pane extends JPanel{
    protected Dimension frame;
    protected Palette palette;
    protected Label label;
    
    //misc
    private final static float clickIntensity = 0.30f;
    private final static float hoverIntensity = 0.10f;
    
    public Pane(Dimension frame, Palette palette){
        this.frame = frame;
        this.palette = palette;
    }
    
    public MouseAdapter getActionMouse(JButton button){
        MouseAdapter mouseEvent = new MouseAdapter(){
            private final Color originalColor = button.getBackground();
            private final Color originalText = button.getForeground();
            
            boolean entered = false;
            
            @Override
            public void mousePressed(MouseEvent e){
                Color darkerColor = Palette.darken(originalColor, clickIntensity);
                Color darkerText = Palette.darken(originalText, clickIntensity);
                
                Pane.this.newColor(button, darkerColor, darkerText);
            };
            
            @Override
            public void mouseEntered(MouseEvent e){
                Color darkerColor = Palette.darken(originalColor, hoverIntensity);
                Color darkerText = Palette.darken(originalText, hoverIntensity);
                
                entered = true;
                
                Pane.this.newColor(button, darkerColor, darkerText);
            };
            
            @Override
            public void mouseReleased(MouseEvent e){
                if(entered){
                    this.mouseEntered(e);
                    return;
                }
                
                Pane.this.oldColor(button, originalColor, originalText);
            };
            
            @Override
            public void mouseExited(MouseEvent e){
                entered = false;
                
                Pane.this.oldColor(button, originalColor, originalText);
            };
        };
        return mouseEvent;
    }
    
    private void newColor(JButton button, Color color, Color text){
        button.setBackground(color);
        button.setForeground(text);
        this.repaint();
    }

    private void oldColor(JButton button, Color color, Color text){
        button.setBackground(color);
        button.setForeground(text);
        this.repaint();
    }
    
    protected int getX(int width, double xPercent){
        int x = (int)(frame.width * xPercent) - (int)(width / 2.0);
        return x;
    }
    
    protected int getY(int height, double yPercent){
        int x = (int)(frame.height * yPercent) - (height / 2);
        return x;
    }
    
    protected void setComponent(JComponent component, Dimension size, double xPercent, double yPercent){
        component.setLocation(this.getX(size.width, xPercent), this.getY(size.height, yPercent));
        component.setSize(size.width, size.height);
        
        this.add(component);
    }
    
    protected void setComponent(JButton button, Dimension size, double xPercent, double yPercent){
        this.setComponent((JComponent)button, size, xPercent, yPercent);
        
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }
    
    protected void setComponent(JTextComponent text, Dimension size, double xPercent, double yPercent){
        this.setComponent((JComponent)text, size, xPercent, yPercent); //TODO: work on this
        
        text.setBorder(null);
        text.setOpaque(true); 
    }
}
