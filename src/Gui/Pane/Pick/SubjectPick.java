/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui.Pane.Pick;

import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import Gui.Pane.Pane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class SubjectPick extends Pane {

    private JPanel scrollContentPanel;
    private JScrollPane scrollPane;
    private JPanel selectedCard = null;
    private int cardYOffset = 15; 
    private int targetCardWidth;
    private int sideMargin = 50;  

    public SubjectPick(Dimension frame, Palette palette, Label label) {
        super(frame, palette, label);
        this.setLayout(null);
        this.setSize(frame);
        this.setBackground(Color.WHITE);

        initScrollLayout(frame);
        
        addSubjectCard("Subject Name", "Subj. Code", "W/W - 13:30-15:30/15:30-17:00", "SECTION");
        addSubjectCard("Subject Name", "Subj. Code", "W/W - 13:30-15:30/15:30-17:00", "SECTION");
        addSubjectCard("Subject Name", "Subj. Code", "W/W - 13:30-15:30/15:30-17:00", "SECTION");
        addSubjectCard("Subject Name", "Subj. Code", "W/W - 13:30-15:30/15:30-17:00", "SECTION");
    }

    private void initScrollLayout(Dimension frame) {
        scrollContentPanel = new JPanel();
        scrollContentPanel.setLayout(null);
        scrollContentPanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(scrollContentPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null); 
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        int topOffset = 55;
        int scrollWidth = frame.width - (sideMargin * 2);
        int scrollHeight = frame.height - topOffset - 40; 

        this.targetCardWidth = scrollWidth - 12;
        this.setComponent((JComponent) scrollPane, new Dimension(scrollWidth, scrollHeight), new Point(sideMargin, topOffset));
    }

    public void addSubjectCard(String name, String code, String schedule, String section) {
        int cardWidth = this.targetCardWidth; 
        int cardHeight = 76; 

        JPanel card = this.getPanel(20); 
        card.setLayout(null);
        card.setBackground(palette.getPrimary()); 
        card.setForeground(palette.getTextLight());

        int paddingX = 22;
        int leftWidth = (int) (cardWidth * 0.40);   
        int rightWidth = (int) (cardWidth * 0.52);  
        int rightX = cardWidth - rightWidth - paddingX; 
        
        JLabel nameLbl = new JLabel(name);
        nameLbl.setFont(label.getHeading().deriveFont(20.0f)); 
        nameLbl.setForeground(card.getForeground());
        nameLbl.setBounds(paddingX, 12, leftWidth, 26);
        card.add(nameLbl);

        JLabel codeLbl = new JLabel(code);
        codeLbl.setFont(label.getCaption().deriveFont(12.0f));
        codeLbl.setForeground(card.getForeground());
        codeLbl.setBounds(paddingX, 46, leftWidth, 20); 
        card.add(codeLbl);

        JLabel schedLbl = new JLabel(schedule, JLabel.RIGHT);
        schedLbl.setFont(label.getHeading().deriveFont(java.awt.Font.PLAIN, 20.0f)); 
        schedLbl.setForeground(card.getForeground());
        schedLbl.setBounds(rightX, 12, rightWidth, 26); 
        card.add(schedLbl);

        JLabel secLbl = new JLabel(section.toUpperCase(), JLabel.RIGHT);
        secLbl.setFont(label.getCaption().deriveFont(12.0f));
        secLbl.setForeground(card.getForeground());
        secLbl.setBounds(rightX, 46, rightWidth, 20); 
        card.add(secLbl);

        card.addMouseListener(this.getMouseEvent(card));
        
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedCard = card;
            }
        });

        card.setBounds(0, cardYOffset, cardWidth, cardHeight);
        scrollContentPanel.add(card);
        
        cardYOffset += cardHeight + 14;
        scrollContentPanel.setPreferredSize(new Dimension(cardWidth, cardYOffset));
        
        scrollContentPanel.revalidate();
        scrollContentPanel.repaint();
    }

    public JPanel getSelectedCard() {
        return selectedCard;
    }
}