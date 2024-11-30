package uno.project;

public class UnoProject {
    public static void main(String[] args) {
        Game game = new Game();
        game.addPlayer("Player 1");
        game.addPlayer("Player 2");
        game.addPlayer("Player 3");
        game.addPlayer("Player 4");
        game.startGame();
        
        boolean gameWon = false;
        while (!gameWon) {
            game.playTurn();
            
            // Check if any player has won
            for (Player player : game.getPlayers()) {
                if (player.hasWon()) {
                    System.out.println(player.getName() + " has won the game!");
                    gameWon = true;
                    break;
                }
            }
            
            // Prevent infinite loop
            if (game.getTotalTurns() > 100) {
                System.out.println("Game ended in a draw.");
                break;
            }
        }
    }
}