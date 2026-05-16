package Gui.Frame;

import Gui.Panel.Panel;
import Tool.Gui.Label;
import Tool.Gui.Palette;
import Tool.Gui.Signal;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public abstract class Frame extends JFrame{
    public final Dimension size;
    public final Palette palette;
    public final Label label;
    
    public Frame(String title, Dimension size, Palette palette, Label label){
        this.size = size;
        this.palette = palette;
        this.label = label;
        
        this.setTitle(title);
        this.setSize(size);
        
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
