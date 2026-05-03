package Test;

import Gui.Frame.MainFrame;
import Gui.Panel.Panel;
import Tool.Gui.Palette;
import java.awt.Dimension;

public class TestGUI extends Panel{
    
    
    public TestGUI(MainFrame frame, Dimension size, Palette palette) {
        super(frame, size, palette);
    }
    
    public static void main(String[] args){
        TestGUI gui = new TestGUI();
    }
}
