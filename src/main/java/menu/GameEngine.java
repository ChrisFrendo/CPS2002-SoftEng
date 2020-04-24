package menu;

import java.util.ArrayList;
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

public class GameEngine {

    private static GameEngine gameEngine = null;

    private GameEngine() {
    }

    public static GameEngine getInstance() {
        if (gameEngine == null)
            gameEngine = new GameEngine();

        return gameEngine;
    }

    GameEngine resetGameEngine() {
        gameEngine = null;
        return gameEngine;
    }

    private ArrayList<Player> playerList = new ArrayList<>();

    ArrayList<Player> getPlayerList() {
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

    /** This method creates the players and stores them in an arraylist
     *
     * @param playerAmount the amount of players tp be created
     */
    void createPlayers(int playerAmount) {
        for (int i = 0; i < playerAmount; ++i) {
            Player player = new Player("Player " + i);
            playerList.add(player);
        }
    }

    /** This method takes care of creating and generating the map and tiles
     *
     * @param mapSize the desired map size
     * @param playerAmount the amount of players that will be populated on the map
     * @return the board that was just created, null if the creation failed
     */
    Tile[][] createMap(int mapSize, int playerAmount) {
        if (Map.getInstance().setMapSize(mapSize, playerAmount)) {
            return Map.getInstance().generateMap(new Random());
        }
        return null;
    }

    /**
     * This method takes care of the input and moves the indicated player as demanded
     *
     * @param input the character indicating the direction the player is to be moved
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
     * @param gameBoard the board that the game is being played on
     * @param player the player object that the html is being generated for
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
