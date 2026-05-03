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

public class MainFrame extends JFrame{
    private final static Dimension size = new Dimension(850, 460);
    
    private final static Palette palette = createPalette();
    private final static Label label = createLabel();
    
    public MainFrame(){
        this.setTitle("Student Management");
        this.setSize(size);
        
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Panel panel = new AccountPanel(this, size, palette);
        this.setPanel(panel);
    }
    
    public static void main(String[] args){
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
    
    
    
    private static Palette createPalette(){
        Color primary = new Color(3, 79, 132);
        Color secondary = new Color(146, 168, 209);
        Color accent = new Color(251, 119, 26);
        Color neutral = new Color(245, 245, 245);

        Color textLight = Color.WHITE;
        Color textDark = new Color(33, 33, 33);
        
        return new Palette(primary, secondary, accent, neutral, textLight, textDark);
    }
    
    private static Label createLabel(){
        Font title = new Font("Arial", Font.BOLD | Font.ITALIC, 40);
        Font heading = new Font("Arial", Font.BOLD, 30);
        Font subHeading = new Font("Arial", Font.BOLD, 20);
        Font body = new Font("Arial", Font.PLAIN, 10);
        Font caption = new Font("Arial", Font.PLAIN, 5);
        
        return new Label(title, heading, subHeading, body, caption);
    }
    
    public ActionListener getActionEvent(JLayeredPane newPanel){
        ActionListener signal = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                MainFrame.this.setPanel(newPanel);
            }
        };
        return signal;
    }
    
    public void setPanel(JLayeredPane newPanel){
        newPanel.setSize(size);
        newPanel.setLocation(0, 0);
        newPanel.setLayout(null);
        this.setContentPane(newPanel);
        this.repaint();
    }
    
    public Palette getPalette(){
        return null;
    }
}
