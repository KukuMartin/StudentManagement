package Gui.Pane;

import Tool.Gui.Palette;
import Tool.Gui.Signal;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JButton;

public class AccountView extends Pane
{
    private JLabel windowTitle, accID, accType, accCredentials, accAddress;
    private JButton mgButton;
    
    public AccountView(Dimension frame, Palette palette, Signal signal)
    {
        super(frame,palette,signal);
        this.setLayout(null);
        this.setSize(frame);

        windowTitle = new JLabel("My Account");
        windowTitle.setBounds(this.getX(windowTitle,0.5),50,150,30);

        accID = new JLabel("Account ID: ");//adding +values later
        accID.setBounds(this.getX(accID,0.2), 100, 200, 25);//xcoor ycoor objwidth objheight

        accType = new JLabel("Account Type: ");
        accType.setBounds(this.getX(accType,0.2), 130, 200, 25);

        accCredentials = new JLabel("Credentials: ");
        accCredentials.setBounds(this.getX(accCredentials,0.2), 160, 200, 25);

        accAddress = new JLabel("Address: ");
        accAddress.setBounds(this.getX(accAddress,0.2), 190, 200, 25);

        mgButton = new JButton("Manage");
        mgButton.setBounds(this.getX(windowTitle,0.5),250,100,30);
        
        //color manage button (direct to next frame)
        mgButton.setBackground(palette.getSecondary());
        mgButton.setForeground(palette.getTextLight());
        mgButton.setOpaque(true);
        mgButton.setContentAreaFilled(true);
        mgButton.setBorderPainted(false);
      
        add(windowTitle);
        add(accID);
        add(accType);
        add(accCredentials);
        add(accAddress);
        add(mgButton);
    }
}
