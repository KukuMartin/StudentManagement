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

public class SectionPick extends Pane {

    private JPanel scrollContentPanel;
    private JScrollPane scrollPane;
    private int cardYOffset = 15; 
    private int targetCardWidth;
    private int sideMargin = 50;  

    public SectionPick(Dimension frame, Palette palette, Label label) {
        super(frame, palette, label);
        this.setLayout(null);
        this.setSize(frame);
        this.setBackground(Color.WHITE);

        initScrollLayout(frame);
        
        addSectionCard(31, "Section Name", "Section Code");
        addSectionCard(67, "Section Name", "Section Code");

        addActionButton();
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
        int scrollHeight = frame.height - topOffset - 90; 

        this.targetCardWidth = scrollWidth - 12;
        
        this.setComponent((JComponent) scrollPane, new Dimension(scrollWidth, scrollHeight), new Point(sideMargin, topOffset));
    }

    public void addSectionCard(int studentCount, String name, String code) {
        int cardWidth = this.targetCardWidth; 
        int cardHeight = 76; 

        JPanel card = this.getPanel(20); 
        card.setLayout(null);
        card.setBackground(palette.getPrimary()); 
        card.setForeground(palette.getTextLight());

        JPanel badge = this.getPanel(15); 
        badge.setLayout(null);
        badge.setBackground(palette.getPrimary().brighter()); 
        badge.setBounds(14, 10, 65, 56);

        JLabel countLbl = new JLabel(String.valueOf(studentCount), JLabel.CENTER);
        countLbl.setFont(label.getHeading().deriveFont(24.0f));
        countLbl.setForeground(card.getForeground());
        countLbl.setBounds(0, 4, 65, 28);
        badge.add(countLbl);

        JLabel studentsTextLbl = new JLabel("Students", JLabel.CENTER);
        studentsTextLbl.setFont(label.getCaption().deriveFont(9.0f));
        studentsTextLbl.setForeground(card.getForeground());
        studentsTextLbl.setBounds(0, 32, 65, 16);
        badge.add(studentsTextLbl);
        
        card.add(badge);

        int textX = 95; 
        int textWidth = cardWidth - textX - 22;

        JLabel nameLbl = new JLabel(name);
        nameLbl.setFont(label.getHeading().deriveFont(20.0f)); 
        nameLbl.setForeground(card.getForeground());
        nameLbl.setBounds(textX, 12, textWidth, 26);
        card.add(nameLbl);

        JLabel codeLbl = new JLabel(code);
        codeLbl.setFont(label.getCaption().deriveFont(12.0f));
        codeLbl.setForeground(card.getForeground());
        codeLbl.setBounds(textX, 46, textWidth, 20); 
        card.add(codeLbl);

        card.addMouseListener(this.getMouseEvent(card));

        card.setBounds(0, cardYOffset, cardWidth, cardHeight);
        scrollContentPanel.add(card);
        
        cardYOffset += cardHeight + 14;
        scrollContentPanel.setPreferredSize(new Dimension(cardWidth, cardYOffset));
        
        scrollContentPanel.revalidate();
        scrollContentPanel.repaint();
    }

    private void addActionButton() {
        JPanel btn = this.getPanel(20); 
        btn.setLayout(null);
        btn.setBackground(new Color(110, 215, 0));
        btn.setForeground(Color.WHITE);

        JLabel btnLbl = new JLabel("+ Add Section", JLabel.CENTER);
        btnLbl.setFont(label.getHeading().deriveFont(java.awt.Font.BOLD, 14.0f));
        btnLbl.setForeground(btn.getForeground());
        btnLbl.setBounds(0, 0, 160, 36); 
        btn.add(btnLbl);

        btn.addMouseListener(this.getMouseEvent(btn));
        
        btn.setBounds(sideMargin, this.getHeight() - 85, 160, 36);
        this.add(btn);

        this.revalidate();
        this.repaint();
    }
}