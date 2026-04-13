package Gui;

import Gui.Pane.NavBar;
import Tool.Gui.Palette;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class MainFrame extends JFrame{
    private final static Dimension size = new Dimension(700, 400);
    
    private final static Palette palette = getPalette();
    
    public MainFrame(){
        this.setTitle("Student Management");
        this.setSize(size);
        
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //everything below here is jsut for testing
        JLayeredPane pane = new JLayeredPane();
        pane.setLayout(null);
        NavBar bar = new NavBar(size, palette, null);
        bar.addButton(new JButton("Heyy"), null);
        bar.addButton(new JButton("whatt"), null);
        pane.add(bar, 1);
        this.setPanel(pane);
    }
    
    public static void main(String[] args){
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
    
    
    
    private static Palette getPalette(){
        Color primary = Color.RED;
        Color secondary = Color.ORANGE;
        Color accent = Color.GREEN;
        Color neutral = Color.WHITE;

        Color textLight = Color.WHITE;
        Color textDark = Color.BLACK;
        
        return new Palette(primary, secondary, accent, neutral, textLight, textDark);
    }
    
    public void setPanel(JLayeredPane newPanel){
        this.setContentPane(newPanel);
        this.repaint();
    }
}
