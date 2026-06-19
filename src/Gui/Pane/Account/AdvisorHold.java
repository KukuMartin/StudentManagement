package Gui.Pane.Account;

import Gui.Frame.Account.AdvisorAdd; // Assuming you have/will create this window mirroring StudentAdd
import Gui.Frame.Account.AdvisorEdit;
import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import Gui.Misc.Tool.Signal;
import Gui.Pane.Pane;
import School.Model.Account.Account;
import School.Model.Account.Section;
import School.Model.Account.Type.Advisor;
import School.System.Account.AccountSystem;
import School.System.Account.Type.AdvisorSystem;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class AdvisorHold extends Pane {

    public Advisor advisorHolder; // Holds structural cache data during operations
    
    private JList<String> list;
    private DefaultListModel<String> model;
    private JScrollPane scrollPane;

    private AdvisorSystem advisorSystem;
    private List<Advisor> advisors;

    private JButton addBtn, removeBtn, updateBtn;

    public AdvisorHold(Dimension frame, Palette palette, Label label) {
        super(frame, palette, label);

        this.setLayout(null);
        this.setSize(frame);
    }

    private void initUI() {
        model = new DefaultListModel<>();
        list = new JList<>(model);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(label.getBody());
        list.setBackground(palette.getBackground());
        list.setForeground(palette.getTextDark());
        list.setSelectionBackground(Palette.lighten(palette.getPrimary(), .5f));
        list.setSelectionForeground(palette.getTextLight());
        list.setFixedCellHeight(28);

        int sideMargin = 40;
        int topSpace = 125;    
        int bottomSpace = 55;  
        int listWidth = frame.width - (sideMargin * 2) - 130; 
        
        JLabel titleLabel = new JLabel("Advisor Accounts");
        titleLabel.setFont(label.getHeading()); 
        titleLabel.setForeground(palette.getTextDark());
        titleLabel.setBounds(sideMargin, 69, 400, 40); 
        this.add(titleLabel);

        scrollPane = new JScrollPane(list);
        scrollPane.setBounds(sideMargin, topSpace, listWidth, frame.height - topSpace - bottomSpace);
        this.add(scrollPane);

        int buttonX = sideMargin + listWidth + 20;
        int buttonWidth = 110;
        int buttonHeight = 35;
        int buttonGap = 15;

        addBtn = this.getSquareButton("Add", 8);
        addBtn.setBackground(palette.getPrimary());
        addBtn.setForeground(palette.getTextLight());
        addBtn.setFont(label.getBody());
        this.setUpButton(addBtn, new Dimension(buttonWidth, buttonHeight),
                new Point(buttonX, topSpace));
        addBtn.addMouseListener(this.getClickableComponent(addBtn));
        addBtn.addActionListener(getAddListener());

        removeBtn = this.getSquareButton("Remove", 8);
        removeBtn.setBackground(palette.getPrimary());
        removeBtn.setForeground(palette.getTextLight());
        removeBtn.setFont(label.getBody());
        this.setUpButton(removeBtn, new Dimension(buttonWidth, buttonHeight),
                new Point(buttonX, topSpace + buttonHeight + buttonGap));
        removeBtn.addMouseListener(this.getClickableComponent(removeBtn));
        removeBtn.addActionListener(getRemoveListener());

        updateBtn = this.getSquareButton("Update", 8);
        updateBtn.setBackground(palette.getPrimary());
        updateBtn.setForeground(palette.getTextLight());
        updateBtn.setFont(label.getBody());
        this.setUpButton(updateBtn, new Dimension(buttonWidth, buttonHeight),
                new Point(buttonX, topSpace + (buttonHeight + buttonGap) * 2));
        updateBtn.addMouseListener(this.getClickableComponent(updateBtn));
        updateBtn.addActionListener(getUpdateListener());
    }

    public void addAdvisor(AdvisorSystem advisorSystem) {
        this.advisorSystem = advisorSystem;
        initUI();
        refresh();
    }

    private void refresh() {
        model.clear();
        advisors = advisorSystem.getAllAdvisors();
        for (Advisor advisor : advisors) {
            model.addElement(advisor.getUsername());
        }
    }

    private ActionListener getAddListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdvisorHold.this.resetAdvisor();
                
                Signal saveSignal = new Signal() {
                    @Override
                    public void sendSignal() {
                        AccountSystem accountSystem = advisorSystem.getAccountSystem();
                        
                        // 1. Write core properties out to the global Account table matrix
                        int accountId = accountSystem.createAccount(advisorHolder);
                        if (accountId <= 0) {
                            JOptionPane.showMessageDialog(null, "Account creation failed. Verify field inputs.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        
                        // Pass the generated Account key to the advisor container reference
                        advisorHolder.accountId = accountId;
                        
                        // 2. CRITICAL: Make createAdvisor return the auto-increment primary key ID from the DB
                        advisorSystem.createAdvisor(advisorHolder);
                        int advisorId = advisorSystem.getAdvisor(advisorHolder.getUsername()).getId();
                        if (advisorId <= 0) {
                            JOptionPane.showMessageDialog(null, "Advisor profile insertion failed.", "Database Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        
                        // Sync the newborn advisor ID back into the structural object cache tracker
                        advisorHolder.id = advisorId;
                        
                        // 3. Extract the active handled section profile bound to the advisor
                        if (advisorHolder.getSection() != null && !advisorHolder.getSection().isEmpty()) {
                            Section sectionToSave = advisorHolder.getSection().get(0);
                            advisorSystem.getSectionSystem().createSection(sectionToSave, advisorId);
                        }
                        
                        AdvisorHold.this.resetAdvisor();
                        
                        JOptionPane.showMessageDialog(AdvisorHold.this, "Advisor Account and Section Successfully Bound!");
                        AdvisorHold.this.refresh();
                    }
                };
                AdvisorAdd add = new AdvisorAdd("Add an Advisor", frame, palette, label, saveSignal, "Register Advisor", advisorHolder, advisorSystem);
            }
        };
    }

    private ActionListener getRemoveListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();

                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Please select an advisor to remove.");
                    return;
                }

                Advisor advisorToRemove = advisors.get(selectedIndex);

                int response = JOptionPane.showConfirmDialog(
                        AdvisorHold.this,
                        "Permanently delete advisor instance: " + advisorToRemove.getUsername() + "?",
                        "Confirm Remove",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (response != JOptionPane.YES_OPTION) {
                    return;
                }

                AccountSystem accountSystem = advisorSystem.getAccountSystem();
                accountSystem.deleteAccount(advisorToRemove.getAccountId());
                refresh();
            }
        };
    }

    private ActionListener getUpdateListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();

                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Please select an advisor to update.");
                    return;
                }

                Advisor advisorToUpdate = advisors.get(selectedIndex);

                Signal updateSignal = new Signal() {
                    @Override
                    public void sendSignal() {
                        AccountSystem accountSystem = advisorSystem.getAccountSystem();
                        
                        // Decouple updates strictly using focused entity mapping properties
                        Account clearAccountData = new Account(
                            advisorToUpdate.getAccountId(), 
                            advisorToUpdate.getFirstName(),
                            advisorToUpdate.getMiddleName(),
                            advisorToUpdate.getLastName(),
                            advisorToUpdate.getGender(),
                            advisorToUpdate.getBirthDate(),
                            advisorToUpdate.getPhoneNumber(),
                            advisorToUpdate.getAddress()
                        );
                        
                        accountSystem.updateAccount(clearAccountData);
                        advisorSystem.updateAdvisor(advisorToUpdate);
                        
                        JOptionPane.showMessageDialog(AdvisorHold.this, "Advisor Profile Details Updated!");
                        AdvisorHold.this.refresh();
                    }
                };
                
                AdvisorEdit edit = new AdvisorEdit("Add an Advisor", frame, palette, label, updateSignal, "Register Advisor", advisorToUpdate);
            }
        };
    }

    public void resetAdvisor() {
        this.advisorHolder = new Advisor(0, "", "", 0, new ArrayList<Section>(), "", "", "", "", java.time.LocalDate.now(), "", "");
    }

    public JList<String> getList() {
        return list;
    }
}