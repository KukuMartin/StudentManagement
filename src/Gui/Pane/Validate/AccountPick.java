    package Gui.Pane.Validate;

    import Gui.Pane.Pane;
    import Tool.Gui.Palette;
    import Tool.Gui.Signal;
import java.awt.BasicStroke;
    import java.awt.Color;
    import java.awt.Dimension;
    import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

    public class AccountPick extends Pane{
        private Palette palette;
        private Signal advisorSignal, teacherSignal; 

        public AccountPick(Dimension frame, Palette palette, Signal advisorSignal, Signal teacherSignal) {
            super(frame, palette);
            this.palette = palette;
            setBackground(this.palette.getNeutral());

            JButton advisorBtn, teacherBtn;

            JLabel title = new JLabel("STUDENT SYSTEM", SwingConstants.CENTER);
            int width = 800;
            title.setBounds(getX(width, 0.5), 40, 800, 40);
            title.setFont(new Font("SansSerif", Font.BOLD, 50));
            title.setForeground(palette.getPrimary());
            add(title);
            
            width = 300;
            advisorBtn = new JButton("ADVISOR");
            advisorBtn.setBounds(getX(width, 0.5), 150, 300, 60);
            styleButton(advisorBtn);
            advisorBtn.addActionListener(this.getAction(advisorSignal));
            advisorBtn.addMouseListener(this.getMouse(advisorBtn));
            this.setButton(advisorBtn);

            teacherBtn = new JButton("TEACHER");
            teacherBtn.setBounds(getX(width, 0.5), 250, 300, 60);
            styleButton(teacherBtn);
            teacherBtn.addActionListener(this.getAction(teacherSignal));
            teacherBtn.addMouseListener(this.getMouse(teacherBtn));
            this.setButton(teacherBtn);
        }

        private void styleButton(JButton btn) {
            btn.setBackground(palette.getSecondary());
            btn.setForeground(palette.getTextLight());
            btn.setFont(new Font("SansSerif", Font.BOLD, 18));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setOpaque(true);
        }     
        
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        int width = 500;
        int height = 280;
        int arch = 20;
        
        g2d.setColor(palette.getPrimary());
        g2d.setStroke(new BasicStroke(10));
        g2d.drawRoundRect(this.getX(width, 0.5), this.getY(height, 0.5), width, height, arch, arch);
    }
}
