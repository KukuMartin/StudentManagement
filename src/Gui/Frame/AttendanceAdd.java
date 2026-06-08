/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui.Frame;

import Gui.Misc.Tool.Label;
import Gui.Misc.Tool.Palette;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
/**
 *
 * @author kimberly
 */
public class AttendanceAdd extends Frame implements ActionListener{
    
    private JLabel lblTitle, lblDate;
    private JButton btnSetup;
    private JPanel header;
    private JSpinner date;
    
    public AttendanceAdd(String title, Dimension size, Palette palette, Label label){
        super(title, size, palette, label, JFrame.EXIT_ON_CLOSE);
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        header = new JPanel();
        header.setBounds(0, 0, getDimension().width, 70);
        header.setBackground(getPalette().getPrimary());
        add(header);
        
        btnSetup = new JButton("Setup Attendance");
        btnSetup.setBounds(320, 350, 200, 45);
        btnSetup.setFont(getLabel().getCaption());
        btnSetup.setBackground(getPalette().getPrimary());
        btnSetup.setForeground(getPalette().getTextLight());
        btnSetup.addActionListener(this);
        add(btnSetup);
        
        
        lblTitle = new JLabel("SETUP ATTENDANCE", SwingConstants.CENTER);
        lblTitle.setBounds(180, 120, 500, 40);
        lblTitle.setFont(getLabel().getHeading()); 
        lblTitle.setForeground(getPalette().getTextDark());
        add(lblTitle);
        
        lblDate = new JLabel("Date");
        lblDate.setBounds(120, 220, 100, 30);
        lblDate.setFont(getLabel().getBody());
        lblDate.setForeground(getPalette().getTextDark());
        add(lblDate);
        
        date = new JSpinner(new SpinnerDateModel());
        date.setBounds(200, 215, 550, 40);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(date, "MM/dd/yyyy");
        date.setEditor(editor);
        add(date);

    }
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == btnSetup) {

                String selectedDate = new SimpleDateFormat("MM/dd/yyyy")
                        .format(date.getValue());

                JOptionPane.showMessageDialog(
                        this,
                        "Attendance set for " + selectedDate
                );
            }
        }
}

