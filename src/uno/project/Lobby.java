package uno.project;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    private List<Game> games;
    
    public Lobby() {
        games = new ArrayList<>();
    }
    
    public void createGame() {
        Game game = new Game();
        games.add(game);
        System.out.println("New game created.");
    }
    
    public List<Game> getGames() {
        return games;
    }
}