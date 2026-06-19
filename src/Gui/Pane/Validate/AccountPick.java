package Gui.Pane.Validate;

import Gui.Misc.Tool.Label;
import Gui.Pane.Pane;
import Gui.Misc.Tool.Palette;
import Gui.Misc.Tool.Signal;
import School.System.SchoolSystem;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JLabel;

public class AccountPick extends Pane{
    SchoolSystem schoolSystem;
    
    public AccountPick(Dimension frame, Palette palette, Label label, Signal adminSignal, Signal advisorSignal, Signal teacherSignal) {
        super(frame, palette, label);
        
        this.setBackground(palette.getBackground());
        this.setSize(frame);
        
        this.createTitle(.5f, .35f);
        
        JLabel subtitle = new JLabel("Student Record System");
        subtitle.setFont(label.getHeading());
        this.setUpComponent(subtitle, getSize(subtitle, 450), .5, .5);
        
        JLabel selection = new JLabel("Select your SRS version");
        selection.setFont(new Font("SansSerif", Font.PLAIN,15));
        this.setUpComponent(selection, getSize(selection, 180), .5, .75);
              
        JButton teacher = this.getSquareButton("I'm a Teacher", 10);
        teacher.setBackground(palette.getAccent());
        teacher.setForeground(palette.getTextLight());
        teacher.setFont(label.getBody());
        this.setUpButton(teacher, getSize(teacher, 150), .5, .84);
        teacher.addActionListener(teacherSignal.getActionEvent());
        teacher.addMouseListener(this.getClickableComponent(teacher));
        
        JButton admin = this.getSquareButton("I'm an Admin", 10);
        admin.setBackground(palette.getAccent());
        admin.setForeground(palette.getTextLight());
        admin.setFont(label.getBody());
        this.setUpButton(admin, getSize(admin, 150), new Point(teacher.getX() - teacher.getWidth() - 10, teacher.getY()));
        admin.addActionListener(adminSignal.getActionEvent());
        admin.addMouseListener(this.getClickableComponent(admin));
        
        JButton advisor = this.getSquareButton("I'm an Advisor", 10);
        advisor.setBackground(palette.getAccent());
        advisor.setForeground(palette.getTextLight());
        advisor.setFont(label.getBody());
        this.setUpButton(advisor, getSize(advisor, 150), new Point(teacher.getX() + teacher.getWidth() + 10, teacher.getY()));
        advisor.addActionListener(advisorSignal.getActionEvent());
        advisor.addMouseListener(this.getClickableComponent(advisor));
    }
    
    private void createTitle(float xPercent, float yPercentS){
        Dimension size;
        Color textDark = palette.getTextDark();
        Color accent = palette.getAccent();
        JLabel title = new JLabel("<html>" + 
                "<span style='color:rgb(" + textDark.getRed() + ", " + textDark.getGreen() + ", " + textDark.getBlue() + ");'>SR</span>" + 
                "<span style='color:rgb(" + accent.getRed() + ", " + accent.getGreen() + ", " + accent.getBlue() + ");'>S</span>" + 
                "</html>");
        title.setFont(label.getTitle());
        size = title.getPreferredSize();
        this.setUpComponent(title, getSize(title, size.width + 10), xPercent, yPercentS);
    }
}