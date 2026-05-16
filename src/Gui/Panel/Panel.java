package Gui.Panel;

import Gui.Frame.Frame;
import Gui.Frame.MainFrame;
import Gui.Pane.Pane;
import Tool.Gui.Label;
import Tool.Gui.Palette;
import Tool.Gui.Signal;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLayeredPane;

public class Panel extends JLayeredPane{
    public enum Layer{
        BOTTOM(1),
        MIDDLE(2),
        TOP(3);
        
        private final Integer number;
        
        Layer(Integer number){
            this.number = number;
        }
        
        public Integer getLayer(){
            return number;
        }
    }
    
    protected final Frame frame;
    protected final Dimension size;
    protected final Palette palette;
    protected final Label label;
    
    public Panel(Frame frame, Dimension size, Palette palette, Label label){
        this.frame = frame;
        this.size = size;
        this.palette = palette;
        this.label = label;
    }
    
    public void setPane(Pane pane, Layer layer){
        Component[] comps = this.getComponents();
        for(Component comp : comps){
            if(this.getLayer(comp) == layer.getLayer()){
                this.remove(comp);
            }
        }
        
        pane.setSize(size);
        pane.setLocation(0, 0);
        pane.setLayout(null);
        this.add(pane, layer.getLayer());
        this.repaint();
    }
    
    public Signal getSignal(Pane pane, Layer layer){
        Signal signal = new Signal(){
            @Override
            public void sendSignal() {
                Panel.this.setPane(pane, layer);
            }
        };
        return signal;
    }
}
