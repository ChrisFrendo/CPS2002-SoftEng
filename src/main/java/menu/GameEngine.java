package menu;

import map.*;
import player.*;
import java.util.ArrayList;
import java.util.Random;

public class GameEngine {

    private static GameEngine gameEngine = null;

    private GameEngine() {}

    public static GameEngine getInstance() {
        if (gameEngine == null)
            gameEngine = new GameEngine();

        return gameEngine;
    }

    ArrayList<Player> playerList = new ArrayList<>();

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public boolean validatePlayers(int playerAmount) {
        return playerAmount >= 2 && playerAmount <= 8;
    }

    public void createPlayers(int playerAmount) {
        for(int i = 0; i < playerAmount; ++i) {
            Player player = new Player(String.valueOf(i));
            playerList.add(player);
        }
    }

    public Tile[][] createMap(int mapSize, int playerAmount) {
        if(Map.getInstance().setMapSize(mapSize, playerAmount)) {
            return Map.getInstance().generateMap(new Random());
        }
        return null;
    }

    public boolean handleInput(char input, int playerNumber) {
        switch(input) {
            case 'u':
                playerList.get(playerNumber).move(Direction.up);
                return true;
            case 'd':
                playerList.get(playerNumber).move(Direction.down);
                return true;
            case 'l':
                playerList.get(playerNumber).move(Direction.left);
                return true;
            case 'r':
                playerList.get(playerNumber).move(Direction.right);
                return true;
            default:
                return false;
        }
    }
}
