package Gui.Panel;

import Gui.Frame.Frame;
import Gui.Frame.MainFrame;
import Gui.Pane.Pane;
import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import Gui.Misc.Tool.Signal;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLayeredPane;

public class Panel extends JLayeredPane{
    public enum Layer{
        BOTTOM(10),
        MIDDLE(20),
        TOP(30);
        
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
    
    public Panel(Frame frame){
        this.frame = frame;
        this.size = frame.getDimension();
        this.palette = frame.getPalette();
        this.label = frame.getLabel();
    }
    
    public void setPane(Pane pane, Layer layer){
        Component[] comps = this.getComponents();
        for(Component comp : comps){
            if(this.getLayer(comp) != layer.getLayer()){
                continue;
            }
            
            if(comp instanceof Pane){
                ((Pane)comp).setIsActive(false);
            }
            
            this.remove(comp);
        }
        
        pane.setLocation(0, 0);
        pane.setLayout(null);
        this.add(pane, layer.getLayer());
        pane.setIsActive(true);
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
