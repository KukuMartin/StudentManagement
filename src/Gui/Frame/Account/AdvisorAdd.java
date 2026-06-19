package Gui.Frame.Account;

import Gui.Frame.Frame;
import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import Gui.Misc.Tool.Signal;
import Gui.Pane.NavBar;
import Gui.Pane.Pane;
import Gui.Panel.Panel;
import School.Model.Account.Type.Advisor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;
import javax.swing.*;

public class AdvisorAdd extends Frame {
    
    private Advisor advisor;
    private String buttonName;
    private Signal saveSignal;

    public AdvisorAdd(String title, Dimension size, Palette palette, Label label, Signal save, Advisor advisor) {
        super(title, size, palette, label, JFrame.DISPOSE_ON_CLOSE);
        this.advisor = advisor;
        this.buttonName = buttonName;
        this.saveSignal = save;
        setTitle("Add an advisor");
    }
    
    public void initUi() {
        Panel panel = new Panel(this);
        int navH = (int)(NavBar.getHeightPercent() * size.height);

        NavBar navBar = new NavBar(getDimension(), getPalette(), getLabel());
        navBar.setLocation(0, 0);
        navBar.addSignOut(new Signal() {
            @Override
            public void sendSignal() {
                AdvisorAdd.this.dispose();
            }
        });
        panel.setPane(navBar, Panel.Layer.TOP);

        Pane content = new Pane(getDimension(), getPalette(), getLabel()) {
            {
                setBackground(palette.getBackground());
                setLocation(0, navH);
                setSize(size.width, size.height - navH);

                JLabel titleHeader = new JLabel("Add Advisor");
                titleHeader.setFont(label.getHeading());
                titleHeader.setForeground(palette.getTextDark());
                setUpComponent(titleHeader, new Dimension(400, 50), new Point(40, 30));

                JLabel lblUsername = new JLabel("Username");
                lblUsername.setFont(label.getBody());
                setUpComponent(lblUsername, new Dimension(150, 20), new Point(40, 100));

                JTextField txtUsername = getSquareTextField(8);
                txtUsername.setText(advisor.getUsername());
                txtUsername.setBackground(palette.getNeutral());
                txtUsername.setForeground(palette.getTextDark());
                setUpText(txtUsername, true, new Dimension(350, 30), new Point(40, 125));

                JLabel lblPassword = new JLabel("Password");
                lblPassword.setFont(label.getBody());
                setUpComponent(lblPassword, new Dimension(150, 20), new Point(420, 100));

                JTextField txtPassword = getSquareTextField(8);
                txtPassword.setText(advisor.getPhoneNumber());
                txtPassword.setBackground(palette.getNeutral());
                txtPassword.setForeground(palette.getTextDark());
                setUpText(txtPassword, true, new Dimension(350, 30), new Point(420, 125));

                JLabel lblFirstName = new JLabel("First Name");
                lblFirstName.setFont(label.getBody());
                setUpComponent(lblFirstName, new Dimension(150, 20), new Point(40, 175));

                JTextField txtFirstName = getSquareTextField(8);
                txtFirstName.setText(advisor.getFirstName());
                txtFirstName.setBackground(palette.getNeutral());
                txtFirstName.setForeground(palette.getTextDark());
                setUpText(txtFirstName, true, new Dimension(220, 30), new Point(40, 200));

                JLabel lblLastName = new JLabel("Last Name");
                lblLastName.setFont(label.getBody());
                setUpComponent(lblLastName, new Dimension(150, 20), new Point(280, 175));

                JTextField txtLastName = getSquareTextField(8);
                txtLastName.setText(advisor.getLastName());
                txtLastName.setBackground(palette.getNeutral());
                txtLastName.setForeground(palette.getTextDark());
                setUpText(txtLastName, true, new Dimension(220, 30), new Point(280, 200));

                JLabel lblMiddleName = new JLabel("Middle Name");
                lblMiddleName.setFont(label.getBody());
                setUpComponent(lblMiddleName, new Dimension(150, 20), new Point(520, 175));

                JTextField txtMiddleName = getSquareTextField(8);
                txtMiddleName.setText(advisor.getMiddleName());
                txtMiddleName.setBackground(palette.getNeutral());
                txtMiddleName.setForeground(palette.getTextDark());
                setUpText(txtMiddleName, true, new Dimension(250, 30), new Point(520, 200));

                JLabel lblSection = new JLabel("Section");
                lblSection.setFont(label.getBody());
                setUpComponent(lblSection, new Dimension(150, 20), new Point(40, 250));

                JTextField txtSection = getSquareTextField(8);
                txtSection.setBackground(palette.getNeutral());
                txtSection.setForeground(palette.getTextDark());
                setUpText(txtSection, true, new Dimension(610, 30), new Point(160, 275));

                JButton btnSectionAction = getSquareButton("Add", 15);
                btnSectionAction.setBackground(palette.getPrimary());
                btnSectionAction.setForeground(palette.getTextLight());
                btnSectionAction.setFont(label.getBody());
                btnSectionAction.addMouseListener(getClickableComponent(btnSectionAction));
                btnSectionAction.addActionListener(e -> {
                    if (btnSectionAction.getText().equals("Add")) {
                        btnSectionAction.setText("Update");
                        txtSection.setEditable(false);
                    } else {
                        btnSectionAction.setText("Add");
                        txtSection.setEditable(true);
                    }
                });
                setUpButton(btnSectionAction, new Dimension(110, 30), new Point(40, 275));

                JLabel lblGender = new JLabel("Gender");
                lblGender.setFont(label.getBody());
                setUpComponent(lblGender, new Dimension(150, 20), new Point(40, 325));

                String[] genders = {"MALE", "FEMALE", "OTHER"};
                JComboBox<String> cmbGender = new JComboBox<>(genders);
                cmbGender.setBackground(palette.getBackground());
                cmbGender.setSelectedItem(advisor.getGender());
                cmbGender.setForeground(palette.getTextDark());
                cmbGender.setBounds(40, 350, 350, 30);
                add(cmbGender);

                JLabel lblBirthdate = new JLabel("Birthdate");
                lblBirthdate.setFont(label.getBody());
                setUpComponent(lblBirthdate, new Dimension(150, 20), new Point(420, 325));

                JSpinner date = new JSpinner(new SpinnerDateModel());
                date.setValue(advisor.getBirthDate());
                JSpinner.DateEditor editor = new JSpinner.DateEditor(date, "MM/dd/yyyy");
                date.setEditor(editor);
                date.setBounds(420, 350, 350, 30);
                add(date);

                JLabel lblAddress = new JLabel("Address");
                lblAddress.setFont(label.getBody());
                setUpComponent(lblAddress, new Dimension(150, 20), new Point(40, 400));

                JTextField txtAddress = getSquareTextField(8);
                txtAddress.setText(advisor.getAddress());
                txtAddress.setBackground(palette.getNeutral());
                txtAddress.setForeground(palette.getTextDark());
                setUpText(txtAddress, true, new Dimension(730, 30), new Point(40, 425));

                JLabel lblPhone = new JLabel("Phone No.");
                lblPhone.setFont(label.getBody());
                setUpComponent(lblPhone, new Dimension(150, 20), new Point(40, 475));

                JTextField txtPhone = getSquareTextField(8);
                txtPhone.setText(advisor.getPhoneNumber());
                txtPhone.setBackground(palette.getNeutral());
                txtPhone.setForeground(palette.getTextDark());
                setUpText(txtPhone, true, new Dimension(350, 30), new Point(40, 500));

                JButton save = getSquareButton(buttonName, 20);
                save.setBackground(palette.getPrimary());
                save.setForeground(palette.getTextLight());
                save.setFont(label.getBody());
                save.addMouseListener(getClickableComponent(save));
                save.addActionListener(e -> {
                    JOptionPane.showMessageDialog(AdvisorAdd.this, "Advisor Information Saved!");
                    saveSignal.sendSignal();
                    AdvisorAdd.this.dispose();
                });
                setUpButton(save, new Dimension(110, 35), new Point(540, 550));

                JButton cancel = getSquareButton("Cancel", 20);
                cancel.setBackground(palette.getSecondary());
                cancel.setForeground(palette.getTextLight());
                cancel.setFont(label.getBody());
                cancel.addMouseListener(getClickableComponent(cancel));
                cancel.addActionListener(e -> {
                    int response = JOptionPane.showConfirmDialog(AdvisorAdd.this, 
                            "Are you sure you want to cancel?", 
                            "Confirm Cancel", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.QUESTION_MESSAGE);

                    if (response == JOptionPane.YES_OPTION) {
                        AdvisorAdd.this.dispose();
                    }
                });
                setUpButton(cancel, new Dimension(110, 35), new Point(660, 550));
            }
        };

        panel.setPane(content, Panel.Layer.BOTTOM);
        this.setPanel(panel);
    }
}