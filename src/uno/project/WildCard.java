package uno.project;

import java.util.Random;

public class WildCard extends Card {
    private String color;
    
    public WildCard() {
        super("Wild");
        this.color = null;
    }
    
    public String chooseColor() {
        String[] colors = {"Red", "Blue", "Green", "Yellow"};
        return colors[new Random().nextInt(colors.length)];
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    @Override
    public String toString() {
        return color != null ? "Wild Card (" + color + ")" : "Wild Card";
    }
}