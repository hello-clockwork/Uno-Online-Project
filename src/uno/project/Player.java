package uno.project;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;
    
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public void drawCards(int numCards, Deck deck) {
        for (int i = 0; i < numCards; i++) {
            Card drawnCard = deck.drawCard();
            if (drawnCard != null) {
                hand.add(drawnCard);
            }
        }
    }
    
    public void drawCard(Card card) {
        if (card != null) {
            hand.add(card);
        }
    }
    
    public boolean hasPlayableCard(String color) {
        for (Card card : hand) {
            if (card.getColor().equals(color) || 
                card instanceof WildCard || 
                (card instanceof ActionCard && ((ActionCard)card).getType().equals(color))) {
                return true;
            }
        }
        return false;
    }
    
    public Card playCard(String color) {
        for (Card card : new ArrayList<>(hand)) {
            if (card.getColor().equals(color) || 
                card instanceof WildCard || 
                (card instanceof ActionCard && ((ActionCard)card).getType().equals(color))) {
                hand.remove(card);
                return card;
            }
        }
        return null;
    }
    
    public boolean hasWon() {
        return hand.isEmpty(); // Check for empty hand
    }
    
    public int getHandSize() {
        return hand.size();
    }
}