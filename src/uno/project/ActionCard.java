package uno.project;

public class ActionCard extends Card {
    private String type;
    public ActionCard(String color, String type) {
        super(color);
        this.type = type;
    }
    public String getType() {
        return type;
    }
    @Override
    public String toString() {
        return getColor() + " " + type;
    }
}