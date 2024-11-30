package uno.project;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Deck deck;
    private List<Player> players;
    private int currentPlayerIndex;
    private String currentColor;
    private int totalTurns;

    public Game() {
        deck = new Deck();
        players = new ArrayList<>();
        currentPlayerIndex = 0;
        totalTurns = 0;
    }

    public void addPlayer(String playerName) {
        players.add(new Player(playerName));
    }

    public void startGame() {
        // Each player draws 7 cards
        for (Player player : players) {
            player.drawCards(7, deck);
        }
        
        // Choose initial color from first card
        Card firstCard = deck.drawCard();
        if (firstCard instanceof NumberCard) {
            currentColor = firstCard.getColor();
        } else {
            currentColor = "Red"; // Default starting color
        }
        deck.addToDiscardPile(firstCard);
    }

    public void playTurn() {
        if (players.isEmpty()) {
            System.out.println("No players in the game.");
            return;
        }
        
        Player currentPlayer = players.get(currentPlayerIndex);
        System.out.println(currentPlayer.getName() + "'s turn:");
        
        // If no playable card, draw a card
        if (!currentPlayer.hasPlayableCard(currentColor)) {
            Card drawnCard = deck.drawCard();
            if (drawnCard != null) {
                currentPlayer.drawCard(drawnCard);
                System.out.println(currentPlayer.getName() + " draws a card.");
            }
        }
        
        // Try to play a card
        Card playedCard = currentPlayer.playCard(currentColor);
        if (playedCard != null) {
            System.out.println(currentPlayer.getName() + " plays " + playedCard);
            deck.addToDiscardPile(playedCard);
            
            // Handle action cards
            if (playedCard instanceof ActionCard) {
                ActionCard actionCard = (ActionCard) playedCard;
                handleActionCard(actionCard);
            }
            
            // Handle wild cards
            if (playedCard instanceof WildCard) {
                WildCard wildCard = (WildCard) playedCard;
                currentColor = wildCard.chooseColor();
                System.out.println("New color chosen: " + currentColor);
            }
        } else {
            System.out.println(currentPlayer.getName() + " cannot play a card.");
        }
        
        // Move to next player
        updatePlayerIndex();
        totalTurns++;
    }

    private void handleActionCard(ActionCard actionCard) {
        switch (actionCard.getType()) {
            case "Skip":
                updatePlayerIndex();
                break;
            case "Reverse":
                // For simplicity, in a 2-player game, Reverse acts like Skip
                updatePlayerIndex();
                break;
            case "Draw Two":
                Player nextPlayer = players.get((currentPlayerIndex + 1) % players.size());
                nextPlayer.drawCards(2, deck);
                updatePlayerIndex();
                break;
        }
    }

    private void updatePlayerIndex() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getTotalTurns() {
        return totalTurns;
    }
}