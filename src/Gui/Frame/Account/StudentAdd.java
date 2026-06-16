/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui.Frame.Account;
import Gui.Frame.Frame;
import Gui.Frame.MainFrame;
import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import Gui.Panel.Panel;
import Gui.Pane.NavBar;
import Gui.Pane.Pane;
import java.awt.Dimension;
import java.awt.Point;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFrame;
/**
 *
 * @author Netmanet
 */
public class StudentAdd extends Frame{
    
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private String phoneNumber;

    private String houseNumber;
    private String street;
    private String barangay;
    private String city;
    private String province;
    private String zipCode;

    public StudentAdd(String title,
                      Dimension size,
                      Palette palette,
                      Label label) {

        super(title, size, palette, label, JFrame.DISPOSE_ON_CLOSE);

        this.setPanel(new StudentPanel(this));
    }

    public static void main(String[] args) {

        StudentAdd frame = new StudentAdd(
                "Add Student",
                MainFrame.createSize(),
                MainFrame.createPalette(),
                MainFrame.createLabel()
        );

        frame.setVisible(true);
    }

    private static class StudentPanel extends Panel {

        public StudentPanel(Frame frame) {
            super(frame);

            createNavBar();
            createForm();
        }

        private void createNavBar() {

            NavBar navBar = new NavBar(size, palette, label);

            JButton homeBtn = navBar.getSquareButton("Home", 8);
            homeBtn.setBackground(palette.getAccent());
            homeBtn.setForeground(palette.getTextLight());
            homeBtn.setFont(label.getBody());

            navBar.addButton(homeBtn, getSignal(navBar, Layer.TOP));

            JButton exitBtn = navBar.getSquareButton("Exit", 8);
            exitBtn.setBackground(palette.getPrimary());
            exitBtn.setForeground(palette.getTextLight());
            exitBtn.setFont(label.getBody());

            exitBtn.addActionListener(e -> System.exit(0));

            int x = size.width - 120;
            int navBarHeight = 60;
            int y = navBarHeight + 30;

            exitBtn.setSize(100, navBar.getHeight() - 20);
            exitBtn.setLocation(x, y);

            navBar.add(exitBtn);

            this.add(navBar, Layer.TOP.getLayer());
        }

        private void createForm() {
            FormPane formPane = new FormPane(size, palette, label);
            setPane(formPane, Layer.MIDDLE);
        }
    }

    private static class FormPane extends Pane {

        public FormPane(Dimension frame,
                        Palette palette,
                        Label label) {

            super(frame, palette, label);

            this.setSize(frame);
            this.setBackground(palette.getBackground());

            buildForm();
        }

        private void buildForm() {

            int leftXLabel = 40;
            int leftXField = 180;

            int rightXLabel = 460;
            int rightXField = 570;
            int y = 80;

            JLabel heading = new JLabel("Student Registration");
            heading.setFont(label.getHeading());
            heading.setForeground(palette.getTextDark());

            setUpComponent(
                    heading,
                    new Dimension(400, 50),
                    new Point(40, y)
            );

            y += 60;

            y = addField("First Name", leftXLabel, leftXField, y);
            y = addField("Middle Name", leftXLabel, leftXField, y);
            y = addField("Last Name", leftXLabel, leftXField, y);
            y = addField("Gender", leftXLabel, leftXField, y);
            y = addField("Birth Date", leftXLabel, leftXField, y);
            y = addField("Phone Number", leftXLabel, leftXField, y);

            int yRight = 140;

            yRight = addField("House Number", rightXLabel, rightXField, yRight);
            yRight = addField("Street", rightXLabel, rightXField, yRight);
            yRight = addField("Barangay", rightXLabel, rightXField, yRight);
            yRight = addField("City", rightXLabel, rightXField, yRight);
            yRight = addField("Province", rightXLabel, rightXField, yRight);
            yRight = addField("Zip Code", rightXLabel, rightXField, yRight);

            JButton saveButton = getSquareButton("Save Student", 12);

            saveButton.setBackground(palette.getPrimary());
            saveButton.setForeground(palette.getTextLight());
            saveButton.setFont(label.getBody());

            saveButton.addMouseListener(getClickableComponent(saveButton));

            setUpButton(
                    saveButton,
                    new Dimension(200, 40),
                    new Point(250, Math.max(y, yRight) + 40)
            );
        }

        private int addField(String text,
                             int xLabel,
                             int xField,
                             int y) {

            JLabel labelField = new JLabel(text + ":");
            labelField.setFont(label.getBody());
            labelField.setForeground(palette.getTextDark());

            setUpComponent(
                    labelField,
                    new Dimension(150, 30),
                    new Point(xLabel, y)
            );

            int fieldWidth = (int)(frame.width * 0.30);
            int fieldHeight = 35;

            JTextField field = getSquareTextField(12);

            field.setBackground(palette.getLight());
            field.setForeground(palette.getTextDark());
            field.setFont(label.getBody());

            setUpText(
                    field,
                    true,
                    new Dimension(fieldWidth, fieldHeight),
                    new Point(xField, y)
            );

            return y + 45;
        }
    }
}