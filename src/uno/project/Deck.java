package uno.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;
    private List<Card> discardPile;
    
    public Deck() {
        cards = new ArrayList<>();
        discardPile = new ArrayList<>();
        initializeDeck();
    }
    
    private void initializeDeck() {
        String[] colors = {"Red", "Blue", "Green", "Yellow"};
        
        // Add number cards
        for (String color : colors) {
            for (int number = 0; number <= 9; number++) {
                // Add two of each number card for each color (except 0, which has only one)
                cards.add(new NumberCard(color, number));
                if (number > 0) {
                    cards.add(new NumberCard(color, number));
                }
            }
        }
        
        // Add action cards
        String[] actionTypes = {"Skip", "Reverse", "Draw Two"};
        for (String color : colors) {
            for (String type : actionTypes) {
                // Two of each action card for each color
                cards.add(new ActionCard(color, type));
                cards.add(new ActionCard(color, type));
            }
        }
        
        // Add wild cards
        for (int i = 0; i < 4; i++) {
            cards.add(new WildCard());
            cards.add(new WildCard());
        }
        
        // Shuffle the deck
        Collections.shuffle(cards);
    }
    
    public Card drawCard() {
        if (cards.isEmpty()) {
            // Reshuffle discard pile if no cards left
            if (!discardPile.isEmpty()) {
                cards.addAll(discardPile);
                discardPile.clear();
                Collections.shuffle(cards);
            } else {
                return null; // No cards left
            }
        }
        return cards.remove(0);
    }
    
    public void addToDiscardPile(Card card) {
        discardPile.add(card);
    }
}