package Gui;

import Gui.Pane.NavBar;
import Tool.Gui.Palette;
import Tool.Gui.Signal;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class MainFrame extends JFrame{
    private final static Dimension size = new Dimension(850, 460);
    
    private final static Palette palette = getPalette();
    
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
    
    
    
    private static Palette getPalette(){
        Color primary = new Color(3, 79, 132);
        Color secondary = new Color(146, 168, 209);
        Color accent = new Color(251, 119, 26);
        Color neutral = new Color(245, 245, 245);

        Color textLight = Color.WHITE;
        Color textDark = new Color(33, 33, 33);
        
        return new Palette(primary, secondary, accent, neutral, textLight, textDark);
    }
    
    public Signal getSignal(JLayeredPane newPanel){
        Signal signal = new Signal(){
            @Override
            public void sendNotif(){
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
}
