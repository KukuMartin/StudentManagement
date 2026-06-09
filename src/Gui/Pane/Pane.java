package Gui.Pane;

import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public abstract class Pane extends JPanel{
    protected final Dimension frame;
    protected final Palette palette;
    protected final Label label;
    
    //misc
    private final static float clickIntensity = 0.3f;
    private final static float hoverIntensity = 0.1f;
    
    public Pane(Dimension frame, Palette palette, Label label){
        this.frame = frame;
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
                
                Pane.this.setColor(comp, darkerColor, darkerText);
            };
            
            @Override
            public void mouseEntered(MouseEvent e){
                Color darkerColor = Palette.darken(originalColor, hoverIntensity);
                Color darkerText = Palette.darken(originalText, hoverIntensity);
                
                entered = true;
                
                Pane.this.setColor(comp, darkerColor, darkerText);
            };
            
            @Override
            public void mouseReleased(MouseEvent e){
                if(entered){
                    this.mouseEntered(e);
                    return;
                }
                
                Pane.this.setColor(comp, originalColor, originalText);
            };
            
            @Override
            public void mouseExited(MouseEvent e){
                entered = false;
                
                Pane.this.setColor(comp, originalColor, originalText);
            };
        };
        return mouseEvent;
    }
    
    public MouseAdapter getMouseEvent(JPanel panel){
        MouseAdapter mouseEvent = new MouseAdapter(){
            boolean entered = false;
            
            private List<Color> originalColor;
            private List<Color> originalText;
            
            private List<Component> comps;
            
            {
                this.updateComponent();
            }
            
            @Override
            public void mousePressed(MouseEvent e){
                for(int index = 0; index < comps.size(); index++){
                    Color darkerColor = Palette.darken(originalColor.get(index), clickIntensity);
                    Color darkerText = Palette.darken(originalText.get(index), clickIntensity);
                
                    Pane.this.setColor(comps.get(index), darkerColor, darkerText);
                }
            };
            
            @Override
            public void mouseEntered(MouseEvent e){
                for(int index = 0; index < comps.size(); index++){
                    Color darkerColor = Palette.darken(originalColor.get(index), hoverIntensity);
                    Color darkerText = Palette.darken(originalText.get(index), hoverIntensity);
                    
                    Pane.this.setColor(comps.get(index), darkerColor, darkerText);
                }
                entered = true;
            };
            
            @Override
            public void mouseReleased(MouseEvent e){
                if(entered){
                    this.mouseEntered(e);
                    return;
                }
                
                for(int index = 0; index < comps.size(); index++){
                    Pane.this.setColor(comps.get(index), originalColor.get(index), originalText.get(index));
                }
                this.updateComponent();
            };
            
            @Override
            public void mouseExited(MouseEvent e){
                entered = false;
                
                for(int index = 0; index < comps.size(); index++){
                    Pane.this.setColor(comps.get(index), originalColor.get(index), originalText.get(index));
                }
                this.updateComponent();
            };
            
            private void updateComponent(){
                comps = new ArrayList<>();
                
                comps.add(panel);
                comps.addAll(Arrays.asList(panel.getComponents()));
                
                originalColor = new ArrayList<>();
                originalText = new ArrayList<>();
                for(Component comp : comps){
                    originalColor.add(comp.getBackground());
                    originalText.add(comp.getForeground());
                }
            }
        };
        return mouseEvent;
    }
    
    private void setColor(Component comp, Color color, Color text){
        comp.setBackground(color);
        comp.setForeground(text);
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
    
    public Dimension getSize(JComponent comp, int width){
        Dimension size = new Dimension(width, comp.getPreferredSize().height);
        return size;
    }
    
    public JPanel getPanel(int arc){
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                g.setColor(this.getBackground());
                g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), arc, arc);
                super.paintComponent(g);
            }
        };
        panel.setOpaque(false);
        return panel;
    }
    
    public JButton getButton(String text, int arc){
        JButton button = new JButton(text){
            @Override
            protected void paintComponent(Graphics g){
                g.setColor(this.getBackground());
                g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), arc, arc);
                super.paintComponent(g);
            }
        };
        button.setOpaque(false);
        return button;
    }
    
    public JTextField getTextField(String name, int arc){
        JTextField text = new JTextField(name){
            @Override
            protected void paintComponent(Graphics g){
                g.setColor(this.getBackground());
                g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), arc, arc);
                super.paintComponent(g);
            }
        };
        text.setOpaque(false);
        return text;
    }
    
    public JTextField getTextField(int arc){
        JTextField text = this.getTextField("", arc);
        return text;
    }
    
    public JPasswordField getPasswordField(int arc){
        JPasswordField text = new JPasswordField(){
            @Override
            protected void paintComponent(Graphics g){
                g.setColor(this.getBackground());
                g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), arc, arc);
                super.paintComponent(g);
            }
        };
        text.setOpaque(false);
        return text;
    }
    
    public JTextArea getTextArea(String name, int arc){
        JTextArea text = new JTextArea(name){
            @Override
            protected void paintComponent(Graphics g){
                g.setColor(this.getBackground());
                g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), arc, arc);
                super.paintComponent(g);
            }
        };
        text.setOpaque(false);
        return text;
    }
    
    public JTextArea getTextArea(int arc){
        JTextArea text = this.getTextArea("", arc);
        return text;
    }
    
    //JCOMPONENTS
    protected void setComponent(JComponent component, Dimension size, Point point){
        component.setLocation(point.x, point.y);
        component.setSize(size.width, size.height);
        
        this.add(component);
    }
    
    protected void setComponent(JComponent comp, Dimension size, double xPercent, double yPercent){
        Point point = new Point(this.getX(size.width, xPercent), this.getY(size.height, yPercent));
        this.setComponent(comp, size, point);
    }
    
    //JButton
    protected void setComponent(JButton button, Dimension size, Point point){
        this.setComponent((JComponent)button, size, point);
        
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }
    
    protected void setComponent(JButton button, Dimension size, double xPercent, double yPercent){
        Point point = new Point(this.getX(size.width, xPercent), this.getY(size.height, yPercent));
        this.setComponent(button, size, point);
    }
    
    //TEXTCOMPONENT
    protected void setComponent(JTextComponent text, boolean editable, Dimension size, Point point){
        this.setComponent(text, size, point);
        
        text.setEditable(editable);
        text.setBorder(null);
    }
    
    protected void setComponent(JTextComponent text, boolean editable, Dimension size, double xPercent, double yPercent){
        Point point = new Point(this.getX(size.width, xPercent), this.getY(size.height, yPercent));
        this.setComponent(text, editable, size, point);
    }
}
