package Test;

import Gui.Frame.Frame;
import Gui.Frame.MainFrame;
import Gui.Pane.Account.AccountView;
import Gui.Pane.NavBar;
import Gui.Pane.Pane;
import Gui.Panel.AccountPanel;
import Gui.Panel.Panel;
import Gui.Panel.Panel.Layer;
import Tool.Gui.Label;
import Tool.Gui.Palette;
import Tool.Gui.Signal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class TestFrame extends Frame{
    private final boolean showNavBar = true;
    public TestFrame(String title, Dimension size, Palette palette, Label label){
        super(title, size, palette, label);
        
        this.setUp(size, palette, label);
    }
    
    private void setUp(Dimension size, Palette palette, Label label){
        Panel panel = new Panel(this, size, palette, label);
        
        if(showNavBar){
            Signal signal = new Signal(){
                @Override
                public void sendSignal() {
                }
            };
            NavBar navbar = new NavBar(size, palette, label, signal);
            panel.setPane(navbar, Layer.TOP);
        }
        
        Pane pane = new AccountView(size, palette, label);
        panel.setPane(pane, Layer.MIDDLE);
        
        this.setPanel(panel);
    }
    
    public static void main(String[] args){
        String title = "Testing Pane";
        
        Dimension size = MainFrame.createSize();
        Palette palette = MainFrame.createPalette();
        Label label = MainFrame.createLabel();
        
        TestFrame frame = new TestFrame(title, size, palette, label);
        frame.setVisible(true);
    }
}
