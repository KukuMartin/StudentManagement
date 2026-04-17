package Tool.Gui;

import java.awt.Color;

public class Palette {
    final private Color primary;
    final private Color secondary;
    final private Color accent;
    final private Color neutral;
    
    final private Color textLight;
    final private Color textDark;

    public Palette(Color primary, Color secondary, Color accent, Color neutral, Color textLight, Color textDark) {
        this.primary = primary;
        this.secondary = secondary;
        this.accent = accent;
        this.neutral = neutral;
        
        this.textLight = textLight;
        this.textDark = textDark;
    }
    
    public Color getPrimary() {
        return primary;
    }

    public Color getSecondary() {
        return secondary;
    }

    public Color getAccent() {
        return accent;
    }

    public Color getNeutral() {
        return neutral;
    }
    
    public Color getTextLight() {
        return textLight;
    }
    public Color getTextDark() {
        return textDark;
    }
    
    public static Color darken(Color color, float intensity){
        float hueIncrease = .1f * intensity;
        float satIncrease = .6f * intensity;
        float valDecrease = .9f * intensity;
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        
        float hue = Math.min(1f, hsb[0] + hueIncrease);
        float saturation = Math.min(1.0f, hsb[1] + satIncrease);
        float value = Math.max(0f, hsb[2] - valDecrease);
        
        return (new Color(Color.HSBtoRGB(hue, saturation, value)));
    }
}
