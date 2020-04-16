package menu;

import map.*;
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

    public boolean createPlayers(int playerAmount) {
        if (playerAmount < 2 || playerAmount > 8)
            return false;

        int test = 10;
        return true;
    }

    public boolean setMapSize(int mapSize, int playerAmount) {
        return Map.getInstance().setMapSize(mapSize, playerAmount);
    }

    ArrayList<Player> playerList = new ArrayList<>();
}
