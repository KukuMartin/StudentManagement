package Gui.Panel;

import Gui.Frame.Frame;
import Gui.Pane.Account.AdvisorHold;
import Gui.Pane.Account.TeacherHold;
import Gui.Pane.NavBar;
import Gui.Misc.Tool.Signal;
import School.System.Account.Type.AdvisorSystem;
import School.System.Account.Type.TeacherSystem;
import javax.swing.JButton;

public class AdminPanel extends Panel {
    private NavBar navbar;
    private TeacherHold teacherHold;
    private AdvisorHold advisorHold;

    private TeacherSystem teacherSystem;
    private AdvisorSystem advisorSystem;

    public AdminPanel(Frame frame, Signal signOut, TeacherSystem teacherSystem, AdvisorSystem advisorSystem) {
        super(frame);

        this.teacherSystem = teacherSystem;
        this.advisorSystem = advisorSystem;

        navbar = new NavBar(size, palette, label);
        navbar.addSignOut(signOut);

        teacherHold = new TeacherHold(size, palette, label);
        advisorHold = new AdvisorHold(size, palette, label);

        teacherHold.addTeacher(teacherSystem);
        advisorHold.addAdvisor(advisorSystem);

        navbar.addButton(this.getTeacherButton(), this.getTeacherSignal());
        navbar.addButton(this.getAdvisorButton(), this.getAdvisorSignal());

        this.setBackground(palette.getNeutral());
        this.setPane(teacherHold, Layer.MIDDLE);
        this.setPane(navbar, Layer.TOP);
    }

    private JButton getTeacherButton() {
        JButton button = teacherHold.getSquareButton("Teachers", 8);
        button.setBackground(palette.getPrimary());
        button.setForeground(palette.getTextLight());

        button.setFont(label.getBody());

        button.addMouseListener(navbar.getClickableComponent(button));
        return button;
    }

    private Signal getTeacherSignal() {
        Signal signal = new Signal() {
            @Override
            public void sendSignal() {
                if (!teacherHold.getIsActive()) {
                    AdminPanel.this.setPane(teacherHold, Layer.MIDDLE);
                }
            }
        };
        return signal;
    }

    private JButton getAdvisorButton() {
        JButton button = advisorHold.getSquareButton("Advisors", 8);

        button.setBackground(palette.getPrimary());
        button.setForeground(palette.getTextLight());

        button.setFont(label.getBody());

        button.addMouseListener(navbar.getClickableComponent(button));
        return button;
    }

    private Signal getAdvisorSignal() {
        Signal signal = new Signal() {
            @Override
            public void sendSignal() {
                if (!advisorHold.getIsActive()) {
                    AdminPanel.this.setPane(advisorHold, Layer.MIDDLE);
                }
            }
        };
        return signal;
    }
}