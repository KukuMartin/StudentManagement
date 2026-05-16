package Gui.Pane;

import Tool.Gui.Label;
import Tool.Gui.Palette;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

public abstract class Pane extends JPanel{
    protected final Dimension size;
    protected final Palette palette;
    protected final Label label;
    
    //misc
    private final static float clickIntensity = 0.30f;
    private final static float hoverIntensity = 0.10f;
    
    public Pane(Dimension size, Palette palette, Label label){
        this.size = size;
        this.palette = palette;
        this.label = label;
    }
    
    public MouseAdapter getMouseEvent(JComponent comp){
        MouseAdapter mouseEvent = new MouseAdapter(){
            private final Color originalColor = comp.getBackground();
            private final Color originalText = comp.getForeground();
            
            boolean entered = false;
            
            @Override
            public void mousePressed(MouseEvent e){
                Color darkerColor = Palette.darken(originalColor, clickIntensity);
                Color darkerText = Palette.darken(originalText, clickIntensity);
                
                Pane.this.newColor(comp, darkerColor, darkerText);
            };
            
            @Override
            public void mouseEntered(MouseEvent e){
                Color darkerColor = Palette.darken(originalColor, hoverIntensity);
                Color darkerText = Palette.darken(originalText, hoverIntensity);
                
                entered = true;
                
                Pane.this.newColor(comp, darkerColor, darkerText);
            };
            
            @Override
            public void mouseReleased(MouseEvent e){
                if(entered){
                    this.mouseEntered(e);
                    return;
                }
                
                Pane.this.oldColor(comp, originalColor, originalText);
            };
            
            @Override
            public void mouseExited(MouseEvent e){
                entered = false;
                
                Pane.this.oldColor(comp, originalColor, originalText);
            };
        };
        return mouseEvent;
    }
    
    private void newColor(JComponent comp, Color color, Color text){
        comp.setBackground(color);
        comp.setForeground(text);
        this.repaint();
    }

    private void oldColor(JComponent comp, Color color, Color text){
        comp.setBackground(color);
        comp.setForeground(text);
        this.repaint();
    }
    
    protected int getX(int width, double xPercent){
        int x = (int)(size.width * xPercent) - (int)(width / 2.0);
        return x;
    }
    
    protected int getY(int height, double yPercent){
        int x = (int)(size.height * yPercent) - (height / 2);
        return x;
    }
    
    protected void setComponent(JComponent component, Dimension size, Point point){
        component.setLocation(point.x, point.y);
        component.setSize(size.width, size.height);
        
        this.add(component);
    }
    
    protected void setComponent(JButton button, Dimension size, Point point){
        this.setComponent((JComponent)button, size, point);
        
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }
    
    
    protected void setComponent(JTextComponent text, Dimension size, Point point){
        this.setComponent((JComponent)text, size, point); //TODO: work on this
        
        text.setBorder(null);
        text.setOpaque(true); 
    }
    
    protected void setComponent(JButton button, Dimension size, double xPercent, double yPercent){
        Point point = new Point(this.getX(button.getWidth(), xPercent), this.getY(button.getHeight(), yPercent));
        this.setComponent(button, size, point);
    }
    
    protected void setComponent(JTextComponent text, Dimension size, double xPercent, double yPercent){
        Point point = new Point(this.getX(text.getWidth(), xPercent), this.getY(text.getHeight(), yPercent));
        this.setComponent(text, size, point);
    }
}
