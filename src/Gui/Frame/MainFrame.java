package Gui.Frame;

import Gui.Panel.AccountPanel;
import Gui.Panel.Panel;
import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

public class MainFrame extends Frame{
    
    public MainFrame(String title, Dimension size, Palette palette, Label label){
        super(title, size, palette, label);
        
        Panel panel = new AccountPanel(this);
        this.setPanel(panel);
    }
    
    public static void main(String[] args){
        String title = "Testing Pane";
        
        Dimension size = MainFrame.createSize();
        Palette palette = MainFrame.createPalette();
        Label label = MainFrame.createLabel();
        
        MainFrame frame = new MainFrame(title, size, palette, label);
        frame.setVisible(true);
    }
    
    public static Dimension createSize(){
        int width = 850;
        int height = 460;
        
        return new Dimension(width, height);
    }
    
    public static Palette createPalette(){
        Color primary = new Color(0, 101, 128);
        Color secondary = new Color(0, 70, 88);
        Color accent = new Color(0, 141, 179);
        Color light = new Color(148, 190, 202);
        Color neutral = new Color(166, 166, 166);
        Color background = Color.WHITE;

        Color textLight = Color.WHITE;
        Color textDark = new Color(33, 33, 33);
        
        return new Palette(primary, secondary, accent, light, neutral, background, textLight, textDark);
    }
    
    public static Label createLabel() {
        String fontName;
        try{
            String fontPath = "/Gui/Misc/Resource/Helvetica.ttf";
            Font helvetica = Font.createFont(Font.TRUETYPE_FONT, MainFrame.class.getResourceAsStream(fontPath)).deriveFont(30f);
            
            GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphics.registerFont(helvetica);
            
            fontName = helvetica.getName();
        } catch (IOException | FontFormatException e){
            fontName = "Arial";
        }
        
        Font title = new Font(fontName, Font.BOLD | Font.ITALIC, 120);
        Font heading = new Font(fontName, Font.BOLD, 40);
        Font subHeading = new Font(fontName , Font.BOLD, 25);
        Font body = new Font(fontName, Font.PLAIN, 15);
        Font caption = new Font(fontName, Font.PLAIN, 10);
        
        return new Label(title, heading, subHeading, body, caption);
    }
}
