package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import html.Director;
import html.GenerateHtmlFiles;
import html.MapPageBuilder;
import html.Page;
import html.PageBuilder;
import map.Map;
import map.Tile;
import player.Direction;
import player.Player;
import map.MapCreator;

/**
 * This singleton class is used to drive the game
 */
public class GameEngine {
    /**
     * Map instance global
     */
    private static Map map = null;

    private static String mapType = "";

    public static String getMapType() {
        return mapType;
    }

    /**
     * Singleton instance of GameEngine
     */
    private static GameEngine gameEngine = null;

    /**
     * List of type player containing all players currently playing the game
     */
    private List<Player> playerList = new ArrayList<>();


    private GameEngine() {
    }

    /**
     * Getter for the singleton GameEngine instance
     *
     * @return the single GameEngine instance
     */
    public static GameEngine getInstance() {
        if (gameEngine == null)
            gameEngine = new GameEngine();

        return gameEngine;
    }

    /**
     * This method resets the single GameEngine instance
     *
     * @return the empty single GameEngine instance
     */
    GameEngine resetGameEngine() {
        gameEngine = null;
        return gameEngine;
    }


    /**
     * Getter for playerList
     *
     * @return A list of type Player
     */
    List<Player> getPlayerList() {
        return playerList;
    }

    /**
     * This method checks if the player amount is as specified
     *
     * @param playerAmount the amount of players the game will have
     * @return true if the player amount is valid, false otherwise
     */
    boolean validatePlayers(int playerAmount) {
        return playerAmount >= 2 && playerAmount <= 8;
    }

    /**
     * This method creates the players and stores them in an arraylist
     *
     * @param playerAmount the amount of players tp be created
     */
    void createPlayers(int playerAmount) {
        for (int i = 0; i < playerAmount; ++i) {
            Player player = new Player("Player " + i);
            playerList.add(player);
        }
    }

    /**
     * This method takes care of creating and generating the map and tiles
     *
     * @param mapSize      the desired map size
     * @param playerAmount the amount of players that will be populated on the map
     * @return the board that was just created, null if the creation failed
     */
    Tile[][] createMap(int mapSize, int playerAmount) {
        map = MapCreator.getMapInstance("Safe", mapSize, playerAmount);
        if (map != null) {
            return map.generateMap(new Random());
        }
        return null;
    }

    /**
     * This method takes care of the input and moves the indicated player as demanded
     *
     * @param input        the character indicating the direction the player is to be moved
     * @param playerNumber the number of the player to be moved
     * @return true if the player was moved successfully, false otherwise
     */
    boolean handleInput(char input, int playerNumber) {
        if (Direction.get(input) != null) {
            return playerList.get(playerNumber).move(Direction.get(input));
        }
        return false;
    }

    /**
     * This method generates html that displays the position of the player
     *
     * @param gameBoard    the board that the game is being played on
     * @param player       the player object that the html is being generated for
     * @param playerNumber the player number of the player that the html is being generated for
     */
    void writeHtml(Tile[][] gameBoard, Player player, int playerNumber) {
        String GENERATED_HTML_DIR = "generatedHtml";

        PageBuilder builder = new MapPageBuilder();
        Director director = new Director(builder);

        try {
            director.construct(gameBoard, player);
        } catch (Exception e) {
            System.out.println("Error constructing html file. Game exiting");
            throw new RuntimeException("Error constructing HTML");
        }

        Page myPage = builder.getPage();

        GenerateHtmlFiles.getInstance().generateHtmlFile(myPage.getHtml(), "map_player_" + (playerNumber) + ".html", GENERATED_HTML_DIR);
    }
}
