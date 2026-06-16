package Gui.Frame.Subject;

import Gui.Frame.Frame;
import Gui.Frame.MainFrame;

import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import Gui.Misc.Tool.Signal;
import Gui.Pane.NavBar;
import Gui.Pane.Pane;
import Gui.Panel.Panel;
import School.Model.Subject.Activity;
import School.Model.Subject.Assessment;
import School.Model.Subject.Period;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AssessmentAdd extends Frame {

    private Assessment savedAssessment = null;
    private List<Activity> activities = new ArrayList<>();
    private final int[] nextActivityId = {1};

    public AssessmentAdd(String title, Dimension size, Palette palette, Label label) {
        super(title, size, palette, label, JFrame.DISPOSE_ON_CLOSE);

        Panel panel = new Panel(this);

        int navH = (int)(NavBar.getHeightPercent() * size.height);

        NavBar navBar = new NavBar(getDimension(), getPalette(), getLabel());
        navBar.setLocation(0, 0);
        navBar.addSignOut(new Signal() {
            @Override
            public void sendSignal() {
                AssessmentAdd.this.dispose();
            }
        });
        panel.setPane(navBar, Panel.Layer.TOP);

        String[] cols = { "#", "Activity Name", "Score", "Max", "Grade %" };
        DefaultTableModel tableModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };

        Pane content = new Pane(getDimension(), getPalette(), getLabel()) {
            {
                setBackground(palette.getBackground());
                setLocation(0, navH);
                setSize(size.width, size.height - navH);

                JLabel lblTitle = new JLabel("Add Assessment");
                lblTitle.setFont(label.getHeading());
                lblTitle.setForeground(palette.getTextDark());
                setUpComponent(lblTitle, new Dimension(400, 40), new Point(230, 20));

                JLabel lblName = new JLabel("Assessment Name");
                lblName.setFont(label.getSubHeading());
                lblName.setForeground(palette.getTextDark());
                setUpComponent(lblName, new Dimension(180, 40), new Point(70, 80));

                JTextField txtName = getSquareTextField(8);
                txtName.setBackground(palette.getNeutral());
                txtName.setForeground(palette.getTextDark());
                setUpText(txtName, true, new Dimension(550, 45), new Point(250, 80));

                JLabel lblWeight = new JLabel("Weight (0.0-1.0)");
                lblWeight.setFont(label.getSubHeading());
                lblWeight.setForeground(palette.getTextDark());
                setUpComponent(lblWeight, new Dimension(180, 40), new Point(70, 145));

                JTextField txtWeight = getSquareTextField(8);
                txtWeight.setBackground(palette.getNeutral());
                txtWeight.setForeground(palette.getTextDark());
                setUpText(txtWeight, true, new Dimension(200, 45), new Point(250, 145));

                JLabel lblRecordId = new JLabel("Record ID");
                lblRecordId.setFont(label.getSubHeading());
                lblRecordId.setForeground(palette.getTextDark());
                setUpComponent(lblRecordId, new Dimension(120, 40), new Point(480, 145));

                JTextField txtRecordId = getSquareTextField(8);
                txtRecordId.setBackground(palette.getNeutral());
                txtRecordId.setForeground(palette.getTextDark());
                setUpText(txtRecordId, true, new Dimension(170, 45), new Point(600, 145));

                JLabel lblPeriod = new JLabel("Period");
                lblPeriod.setFont(label.getSubHeading());
                lblPeriod.setForeground(palette.getTextDark());
                setUpComponent(lblPeriod, new Dimension(120, 40), new Point(70, 210));

                JComboBox<Period> cbPeriod = new JComboBox<>(Period.values());
                cbPeriod.setBackground(palette.getNeutral());
                cbPeriod.setForeground(palette.getTextDark());
                cbPeriod.setFont(label.getSubHeading());
                setUpComponent(cbPeriod, new Dimension(200, 45), new Point(250, 210));

                JLabel lblActivities = new JLabel("Activities");
                lblActivities.setFont(label.getSubHeading());
                lblActivities.setForeground(palette.getTextDark());
                setUpComponent(lblActivities, new Dimension(150, 30), new Point(70, 280));

                JTextField txtActName = getSquareTextField(8);
                txtActName.setBackground(palette.getNeutral());
                txtActName.setForeground(palette.getTextDark());
                txtActName.setToolTipText("Activity Name");
                setUpText(txtActName, true, new Dimension(250, 40), new Point(70, 320));

                JTextField txtScore = getSquareTextField(8);
                txtScore.setBackground(palette.getNeutral());
                txtScore.setForeground(palette.getTextDark());
                txtScore.setToolTipText("Score");
                setUpText(txtScore, true, new Dimension(120, 40), new Point(330, 320));

                JTextField txtMax = getSquareTextField(8);
                txtMax.setBackground(palette.getNeutral());
                txtMax.setForeground(palette.getTextDark());
                txtMax.setToolTipText("Max Score");
                setUpText(txtMax, true, new Dimension(120, 40), new Point(460, 320));

                JButton btnAddActivity = getSquareButton("+ Add", 20);
                btnAddActivity.setBackground(new Color(60, 160, 240));
                btnAddActivity.setForeground(palette.getTextLight());
                btnAddActivity.setFont(label.getSubHeading());
                btnAddActivity.addMouseListener(getClickableComponent(btnAddActivity));
                btnAddActivity.addActionListener(e -> {
                    String actName  = txtActName.getText().trim();
                    String scoreStr = txtScore.getText().trim();
                    String maxStr   = txtMax.getText().trim();

                    if (actName.isEmpty() || scoreStr.isEmpty() || maxStr.isEmpty()) {
                        JOptionPane.showMessageDialog(AssessmentAdd.this,
                            "Please fill in Activity Name, Score, and Max Score.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    double score, max;
                    try {
                        score = Double.parseDouble(scoreStr);
                        max   = Double.parseDouble(maxStr);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(AssessmentAdd.this,
                            "Score and Max Score must be numbers.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (max <= 0) {
                        JOptionPane.showMessageDialog(AssessmentAdd.this,
                            "Max Score must be greater than 0.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (score < 0 || score > max) {
                        JOptionPane.showMessageDialog(AssessmentAdd.this,
                            "Score must be between 0 and Max Score.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Activity act = new Activity(nextActivityId[0]++, actName, score, max);
                    activities.add(act);
                    tableModel.addRow(new Object[]{
                        activities.size(),
                        act.getName(),
                        act.getScore(),
                        act.getMaxScore(),
                        String.format("%.1f%%", act.getPercent() * 100)
                    });

                    txtActName.setText("");
                    txtScore.setText("");
                    txtMax.setText("");
                    txtActName.requestFocus();
                });
                setUpButton(btnAddActivity, new Dimension(110, 40), new Point(590, 320));

                JTable table = new JTable(tableModel);
                table.setFont(label.getSubHeading());
                table.setForeground(palette.getTextDark());
                table.setBackground(palette.getNeutral());
                table.setRowHeight(28);
                table.setSelectionBackground(new Color(180, 210, 255));
                table.getTableHeader().setFont(label.getSubHeading());

                JScrollPane scroll = new JScrollPane(table);
                setUpComponent(scroll, new Dimension(730, 180), new Point(70, 375));

                JButton btnRemove = getSquareButton("Remove Selected", 20);
                btnRemove.setBackground(new Color(220, 53, 69));
                btnRemove.setForeground(palette.getTextLight());
                btnRemove.setFont(label.getSubHeading());
                btnRemove.addMouseListener(getClickableComponent(btnRemove));
                btnRemove.addActionListener(e -> {
                    int row = table.getSelectedRow();
                    if (row < 0) {
                        JOptionPane.showMessageDialog(AssessmentAdd.this,
                            "Select an activity to remove.",
                            "No Selection", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    int confirm = JOptionPane.showConfirmDialog(AssessmentAdd.this,
                        "Remove \"" + activities.get(row).getName() + "\"?",
                        "Confirm Remove", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (confirm != JOptionPane.YES_OPTION) return;

                    activities.remove(row);
                    tableModel.removeRow(row);

                    for (int i = 0; i < tableModel.getRowCount(); i++)
                        tableModel.setValueAt(i + 1, i, 0);
                });
                setUpButton(btnRemove, new Dimension(200, 40), new Point(600, 565));

                JButton btnSave = getSquareButton("Save Assessment", 30);
                btnSave.setBackground(new Color(80, 200, 40));
                btnSave.setForeground(palette.getTextLight());
                btnSave.setFont(label.getSubHeading());
                btnSave.addMouseListener(getClickableComponent(btnSave));
                btnSave.addActionListener(e -> {
                    String name   = txtName.getText().trim();
                    String pctStr = txtWeight.getText().trim();
                    String recStr = txtRecordId.getText().trim();

                    if (name.isEmpty() || pctStr.isEmpty() || recStr.isEmpty()) {
                        JOptionPane.showMessageDialog(AssessmentAdd.this,
                            "Name, Weight, and Record ID are required.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    double percent;
                    int recordId;
                    try {
                        percent = Double.parseDouble(pctStr);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(AssessmentAdd.this,
                            "Weight must be a number (e.g. 0.30).",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {
                        recordId = Integer.parseInt(recStr);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(AssessmentAdd.this,
                            "Record ID must be an integer.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (percent < 0 || percent > 1) {
                        JOptionPane.showMessageDialog(AssessmentAdd.this,
                            "Weight must be between 0.00 and 1.00.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Period period = (Period) cbPeriod.getSelectedItem();
                    int newId = (int)(System.currentTimeMillis() % 100000);

                    savedAssessment = new Assessment(
                        newId, name, percent, recordId, period, new ArrayList<>(activities)
                    );

                    JOptionPane.showMessageDialog(AssessmentAdd.this,
                        "<html><b>" + name + "</b> saved!<br>"
                        + "Activities: " + activities.size() + "<br>"
                        + "Computed grade: "
                        + String.format("%.2f%%", savedAssessment.getGrade() * 100) + "</html>",
                        "Saved", JOptionPane.INFORMATION_MESSAGE);

                    AssessmentAdd.this.dispose();
                });
                setUpButton(btnSave, new Dimension(200, 50), new Point(290, 630));
            }
        };

        panel.setPane(content, Panel.Layer.BOTTOM);
        this.setPanel(panel);
    }

    public Assessment getSavedAssessment() {
        return savedAssessment;
    }

    public static void main(String[] args) {

        AssessmentAdd frame = new AssessmentAdd(
                "Add Assessment",
                MainFrame.createSize(),
                MainFrame.createPalette(),
                MainFrame.createLabel()
        );

        frame.setVisible(true);
    }
    }

