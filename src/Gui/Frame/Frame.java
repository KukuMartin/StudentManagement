package Gui.Frame;

import Gui.Panel.Panel;
import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import Gui.Misc.Tool.Signal;
import java.awt.Dimension;
import javax.swing.JFrame;

public abstract class Frame extends JFrame{
    private final Dimension size;
    private final Palette palette;
    private final Label label;
    
    public Frame(String title, Dimension size, Palette palette, Label label, int operation){
        this.size = size;
        this.palette = palette;
        this.label = label;
        
        this.setTitle(title);
        this.setSize(size);
        
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(operation);
        setLocationRelativeTo(null);
    }
    
    public void setPanel(Panel panel){
        panel.setSize(size);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        
        this.setContentPane(panel);
        this.repaint();
    }
    
    public Signal getSignal(Panel panel){
        Signal signal = new Signal(){
            @Override
            public void sendSignal() {
                Frame.this.setPanel(panel);
            }
        };
        return signal;
    }
    
    public Dimension getDimension(){
        return size;
    }
    
    public Palette getPalette(){
        return palette;
    }
    
    public Label getLabel(){
        return label;
    }
}
