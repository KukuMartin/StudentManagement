package Gui.Misc.Tool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class Signal {
    public abstract void sendSignal();
    
    public final ActionListener getActionEvent(){
        ActionListener event = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                sendSignal();
            }
        };
        return event;
    }
    
    public final MouseAdapter getMouseEvent(){
        MouseAdapter event = new MouseAdapter(){
            @Override 
            public void mouseClicked(MouseEvent e){
                
                sendSignal();
            }
        };
        return event;
    }
}
