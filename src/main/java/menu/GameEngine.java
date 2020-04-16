package menu;

import player.*;
import java.util.ArrayList;

public class GameEngine {

    private static GameEngine gameEngine = null;

    private GameEngine() {}

    public static GameEngine getInstance() {
        if (gameEngine == null)
            gameEngine = new GameEngine();

        return gameEngine;
    }

    ArrayList<Player> playerList = new ArrayList<>();

    public boolean incorrectPlayerAmount(int playerAmount) {
        return playerAmount < 2 || playerAmount > 8;
    }

    public boolean incorrectMapSize(int mapSize, int playerAmount) {
        return (playerAmount > 4 || mapSize < 5 || mapSize > 50)
                && (playerAmount < 5 || mapSize < 8 || mapSize > 50);
    }
}
