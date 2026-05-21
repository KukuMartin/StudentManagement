package Gui.Misc.Tool;

import java.awt.Color;

public class Palette {
    final private Color primary;
    final private Color secondary;
    final private Color accent;
    final private Color light;
    final private Color neutral;
    final private Color background;
    
    final private Color textLight;
    final private Color textDark;

    public Palette(Color primary, Color secondary, Color accent, Color light, Color neutral, Color background, Color textLight, Color textDark) {
        this.primary = primary;
        this.secondary = secondary;
        this.accent = accent;
        this.light = light;
        this.neutral = neutral;
        this.background = background;
        
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
    
    public Color getLight() {
        return light;
    }

    public Color getNeutral() {
        return neutral;
    }
    
    public Color getBackground() {
        return background;
    }
    
    public Color getTextLight() {
        return textLight;
    }
    public Color getTextDark() {
        return textDark;
    }
    
    public static Color darken(Color color, float intensity){
        float hueIncrease = .1f * intensity;
        float satIncrease = .1f * intensity;
        float valDecrease = .6f * intensity;
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        
        float hue = Math.min(1f, hsb[0] + hueIncrease);
        float saturation = Math.max(0f, hsb[1] - satIncrease);
        float value = Math.max(0f, hsb[2] - valDecrease);
        
        return (new Color(Color.HSBtoRGB(hue, saturation, value)));
    }
}
