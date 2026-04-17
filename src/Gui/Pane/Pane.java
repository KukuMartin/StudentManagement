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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public abstract class Pane extends JPanel{
    protected Dimension frame;
    protected Palette palette;
    
    //misc
    private final static float clickIntensity = 0.30f;
    private final static float hoverIntensity = 0.10f;
    
    public Pane(Dimension frame, Palette palette){
        this.frame = frame;
        this.palette = palette;
    }
    
    public ActionListener getActionClick(Signal signal){
        ActionListener actionEvent = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                signal.sendNotif();
            }
        };
        return actionEvent;
    }
    
    public MouseAdapter getMouseClick(JButton button){
        MouseAdapter mouseEvent = new MouseAdapter(){
            private final Color originalColor = button.getBackground();
            private final Color originalText = button.getForeground();
            
            @Override
            public void mousePressed(MouseEvent e){
                Color darkerColor = Palette.darken(originalColor, clickIntensity);
                Color darkerText = Palette.darken(originalText, clickIntensity);
                
                Pane.this.newColor(button, darkerColor, darkerText);
            };
            
            @Override
            public void mouseReleased(MouseEvent e){
                Pane.this.oldColor(button, originalColor, originalText);
            };
        };
        return mouseEvent;
    }
    
    public MouseAdapter getMouseHover(JButton button){
        MouseAdapter mouseEvent = new MouseAdapter(){
            private final Color originalColor = button.getBackground();
            private final Color originalText = button.getForeground();
            
            @Override
            public void mouseEntered(MouseEvent e){
                Color darkerColor = Palette.darken(originalColor, hoverIntensity);
                Color darkerText = Palette.darken(originalText, hoverIntensity);
                
                Pane.this.newColor(button, darkerColor, darkerText);
            };
            
            @Override
            public void mouseExited(MouseEvent e){
                Pane.this.oldColor(button, originalColor, originalText);
            };
        };
        return mouseEvent;
    }
    
    public MouseAdapter getMouseAll(JButton button){
        MouseAdapter mouseEvent = new MouseAdapter(){
            private final Color originalColor = button.getBackground();
            private final Color originalText = button.getForeground();
            
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
                
                Pane.this.newColor(button, darkerColor, darkerText);
            };
            
            @Override
            public void mouseReleased(MouseEvent e){
                this.mouseEntered(e);
            };
            
            @Override
            public void mouseExited(MouseEvent e){
                Pane.this.oldColor(button, originalColor, originalText);
            };
        };
        return mouseEvent;
    }
    
    protected void newColor(JButton button, Color color, Color text){
        button.setBackground(color);
        button.setForeground(text);
        this.repaint();
    }

    protected void oldColor(JButton button, Color color, Color text){
        button.setBackground(color);
        button.setForeground(text);
        this.repaint();
    }
    
    protected int getX(JComponent comp, double xPercent){
        int x = (int)(frame.width * xPercent) - (int)(comp.getWidth() / 2.0);
        return x;
    }
    
    protected int getX(int width, double xPercent){
        int x = (int)(frame.width * xPercent) - (int)(width / 2.0);
        return x;
    }
    
    protected int getY(JComponent comp, double xPercent){
        int x = (int)(frame.height * xPercent) - (comp.getWidth() / 2);
        return x;
    }
    
    protected int getY(int height, double xPercent){
        int x = (int)(frame.height * xPercent) - (height / 2);
        return x;
    }
    
    protected void setButton(JButton button, Point point, Dimension siZe){
        button.setLocation(point.x, point.y);
        button.setSize(siZe.width, siZe.height);
        
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        
        this.add(button);
    }
    
    protected void setButton(JButton button){
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        
        this.add(button);
    }
}
