package Test;

import Gui.Frame.Frame;
import Gui.Frame.MainFrame;
import Gui.Pane.Account.AccountView;
import Gui.Pane.Pane;
import Gui.Panel.AccountPanel;
import Gui.Panel.Panel;
import Gui.Panel.Panel.Layer;
import Tool.Gui.Label;
import Tool.Gui.Palette;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class TestFrame extends Frame{
    private final boolean showNavBar = false;
    public TestFrame(){
        String title = "Testing Pane";
        super(title, MainFrame.createSize(), MainFrame.createPalette(), MainFrame.createLabel());
        
        Panel panel = new Panel(this, size, palette, label);
        
        Pane pane = new AccountView(size, palette, label);
        panel.setPane(pane, Layer.MIDDLE);
        this.setUp(panel);
    }
    
    private void setUp(Panel panel){
        if(showNavBar){
//            NavBar navbar = new NavBar();
        }
        
        this.setPanel(panel);
    }
    
    public static void main(String[] args){
        TestFrame frame = new TestFrame();
        frame.setVisible(true);
    }
}
