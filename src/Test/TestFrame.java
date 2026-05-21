//package Test;
//
//import Gui.Frame.Frame;
//import Gui.Frame.MainFrame;
//import Gui.Pane.Account.SubjectsEdit;
//import Gui.Pane.NavBar;
//import Gui.Pane.Pane;
//import Gui.Panel.AccountPanel;
//import Gui.Panel.Panel;
//import Gui.Panel.Panel.Layer;
//import Gui.Misc.Tool.Label;
//import Gui.Misc.Tool.Palette;
//import Gui.Misc.Tool.Signal;
//import java.awt.Dimension;
//
//public class TestFrame extends Frame{
//    private final boolean showNavBar = true;
//    public TestFrame(String title, Dimension size, Palette palette, Label label){
//        super(title, size, palette, label);
//        
//        this.setUp(size, palette, label);
//    }
//    
//    private void setUp(Dimension size, Palette palette, Label label){
//        Panel panel = new Panel(this);
//        
//        if(showNavBar){
//            Signal signal = new Signal(){
//                @Override
//                public void sendSignal() {
//                }
//            };
//            NavBar navbar = new NavBar(size, palette, label);
//            panel.setPane(navbar, Layer.TOP);
//        }
//        
//        Pane pane = new SubjectsEdit(size, palette, label);
//        panel.setPane(pane, Layer.MIDDLE);
//        
//        this.setPanel(panel);
//    }
//    
//    public static void main(String[] args){
//        String title = "Testing Pane";
//        
//        Dimension size = MainFrame.createSize();
//        Palette palette = MainFrame.createPalette();
//        Label label = MainFrame.createLabel();
//        
//        TestFrame frame = new TestFrame(title, size, palette, label);
//        frame.setVisible(true);
//    }
//}
