package Tool.Gui;

import java.awt.Font;

public class Label {
    final private Font title;
    final private Font heading;
    final private Font subHeading;
    final private Font body;
    final private Font caption;
    
    public Label(Font title, Font heading, Font subHeading, Font body, Font caption){
        this.title = title;
        this.heading = heading;
        this.subHeading = subHeading;
        this.body = body;
        this.caption = caption;
    }
    
    public Font getTitle() {
        return title;
    }
    
    public Font getHeading() {
        return heading;
    }
    
    public Font getSubHeading() {
        return subHeading;
    }
    
    public Font getBody() {
        return body;
    }
    
    public Font getCaption() {
        return caption;
    }
}
