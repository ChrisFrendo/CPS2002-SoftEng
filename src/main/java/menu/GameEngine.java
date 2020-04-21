package menu;

import html.*;
import map.*;
import player.*;
import utils.FileHelperUtils;

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

    private ArrayList<Player> playerList = new ArrayList<>();

    ArrayList<Player> getPlayerList() {
        return playerList;
    }

    boolean validatePlayers(int playerAmount) {
        return playerAmount >= 2 && playerAmount <= 8;
    }

    void createPlayers(int playerAmount) {
        for(int i = 0; i < playerAmount; ++i) {
            Player player = new Player("Player " + i);
            playerList.add(player);
        }
    }

    Tile[][] createMap(int mapSize, int playerAmount) {
        if(Map.getInstance().setMapSize(mapSize, playerAmount)) {
            return Map.getInstance().generateMap(new Random());
        }
        return null;
    }

    boolean handleInput(char input, int playerNumber) {
        if (Direction.get(input) != null) {
            return playerList.get(playerNumber).move(Direction.get(input));
        }
        return false;
    }

    void writeHtml(Tile[][] gameBoard, Player player, int playerNumber) {
        String GENERATED_HTML_DIR = "generatedHtml";

        PageBuilder builder = new MapPageBuilder();
        Director director = new Director(builder);

        try {
            director.construct(gameBoard, player);
        } catch (Exception e) {
            System.out.println("Error constructing html file. Game exiting");
            System.exit(-1);
        }

        Page myPage = builder.getPage();

        GenerateHtmlFiles.getInstance().generateHtmlFile(myPage.getHtml(), "map_player_" + (playerNumber) + ".html", GENERATED_HTML_DIR);
    }
}
