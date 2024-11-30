package uno.project;

public class NumberCard extends Card {
    private int number;
    public NumberCard(String color, int number) {
        super(color);
        this.number = number;
    }
    public int getNumber() {
        return number;
    }
    @Override
    public String toString() {
        return getColor() + " " + number;
    }
}