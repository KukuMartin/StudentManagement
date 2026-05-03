package Gui.Panel;

import Gui.Frame.MainFrame;
import Gui.Pane.NavBar;
import Gui.Pane.Pane;
import Tool.Gui.Palette;
import java.awt.Dimension;
import javax.swing.JLayeredPane;

public class Panel extends JLayeredPane{
    protected enum Layer{
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
    
    protected MainFrame frame;
    protected Dimension size;
    protected Palette palette;
    
    public Panel(MainFrame frame, Dimension size, Palette palette){
        this.frame = frame;
        this.size = size;
        this.palette = palette;
    }
    
    protected void setMiddle(Pane oldPane, Pane newPane){
        if(oldPane != null){
            if(this.isAncestorOf(oldPane)){
                this.remove(oldPane);
            }
        }
        setLayer(newPane, Layer.MIDDLE);
    }
    
    protected void setNavBar(Pane pane){
        Layer layer = Layer.TOP;
        setLayer(pane, layer.getLayer());
        this.add(pane, layer.getLayer());
        this.repaint();
    }
    
    private void setLayer(Pane pane, Layer layer){
        pane.setSize(size);
        pane.setLocation(0, 0);
        pane.setLayout(null);
        this.add(pane, layer.getLayer());
        this.repaint();
    }
}
