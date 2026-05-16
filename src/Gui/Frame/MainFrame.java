package Gui.Frame;

import Gui.Panel.AccountPanel;
import Gui.Panel.Panel;
import Tool.Gui.Label;
import Tool.Gui.Palette;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class MainFrame extends Frame{
    
    public MainFrame(){
        String title = "Student Management";
        
        super(title, MainFrame.createSize(), MainFrame.createPalette(), MainFrame.createLabel());
        
        Panel panel = new AccountPanel(this, size, palette);
        this.setPanel(panel);
    }
    
    public static void main(String[] args){
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
    
    public static Dimension createSize(){
        int width = 850;
        int height = 460;
        
        return new Dimension(width, height);
    }
    
    public static Palette createPalette(){
        Color primary = new Color(3, 79, 132);
        Color secondary = new Color(146, 168, 209);
        Color accent = new Color(251, 119, 26);
        Color neutral = new Color(245, 245, 245);
        Color background = new Color(255, 255, 255);

        Color textLight = Color.WHITE;
        Color textDark = new Color(33, 33, 33);
        
        return new Palette(primary, secondary, accent, neutral, background, textLight, textDark);
    }
    
    public static Label createLabel(){
        Font title = new Font("Arial", Font.BOLD | Font.ITALIC, 40);
        Font heading = new Font("Arial", Font.BOLD, 30);
        Font subHeading = new Font("Arial", Font.BOLD, 20);
        Font body = new Font("Arial", Font.PLAIN, 10);
        Font caption = new Font("Arial", Font.PLAIN, 5);
        
        return new Label(title, heading, subHeading, body, caption);
    }
}
