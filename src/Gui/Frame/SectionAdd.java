/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui.Frame;
import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import Gui.Misc.Tool.Signal;
import Gui.Pane.NavBar;
import Gui.Pane.Pane;
import Gui.Panel.Panel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 *
 * @author Netmanet
 */
public class SectionAdd extends Frame{

    public SectionAdd(String title, Dimension size, Palette palette, Label label) {
        super(title, size, palette, label, JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void initUi(){
        Panel panel = new Panel(this);

        int navH = (int)(NavBar.getHeightPercent() * size.height);

        NavBar navBar = new NavBar(getDimension(), getPalette(), getLabel());
        navBar.setLocation(0, 0);
        navBar.addSignOut(new Signal() {
            @Override
            public void sendSignal() {
                SectionAdd.this.dispose();
            }
        });
        panel.setPane(navBar, Panel.Layer.TOP);
        Pane content = new Pane(getDimension(), getPalette(), getLabel()) {
            {
                setBackground(palette.getBackground());
                setLocation(0, navH);
                setSize(size.width, size.height - navH);

               
                JLabel lblTitle = new JLabel("Add New Section");
                lblTitle.setFont(label.getHeading());
                lblTitle.setForeground(palette.getTextDark());
                setUpComponent(lblTitle, new Dimension(400, 100), new Point(230, 30));

               
                JLabel lblName = new JLabel("Section Name");
                lblName.setFont(label.getSubHeading());
                lblName.setForeground(palette.getTextDark());
                setUpComponent(lblName, new Dimension(180, 40), new Point(70, 140));
                
                
                JTextField txtSectionName = getSquareTextField(8);
                txtSectionName.setBackground(palette.getNeutral());
                txtSectionName.setForeground(palette.getTextDark());
                setUpText(txtSectionName, true, new Dimension(550, 45), new Point(250, 140));

                
                JLabel lblCode = new JLabel("Section Code");
                lblCode.setFont(label.getSubHeading());
                lblCode.setForeground(palette.getTextDark());
                setUpComponent(lblCode, new Dimension(180, 40), new Point(70, 210));

                
                JTextField txtSectionCode = getSquareTextField(8);
                txtSectionCode.setBackground(palette.getNeutral());
                txtSectionCode.setForeground(palette.getTextDark());
                setUpText(txtSectionCode, true, new Dimension(550, 45), new Point(250, 210));

                
                JButton btnAdd = getSquareButton("Add Section", 30);
                btnAdd.setBackground(new Color(80, 200, 40));
                btnAdd.setForeground(palette.getTextLight());
                btnAdd.setFont(label.getSubHeading());
                btnAdd.addMouseListener(getClickableComponent(btnAdd));
                btnAdd.addActionListener(e -> {
                    System.out.println(txtSectionName.getText());
                    System.out.println(txtSectionCode.getText());
                });
                setUpButton(btnAdd, new Dimension(200, 50), new Point(325, 330));
            }
        };

        panel.setPane(content, Panel.Layer.BOTTOM);
        this.setPanel(panel);
    }
}