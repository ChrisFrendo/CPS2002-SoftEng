package menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import html.Director;
import html.GenerateHtmlFiles;
import html.MapPageBuilder;
import html.Page;
import html.PageBuilder;
import map.Map;
import map.MapCreator;
import map.Tile;
import player.Direction;
import player.Player;
import team.Team;

/**
 * This singleton class is used to drive the game
 */
public class GameEngine {

    /**
     * Stores the MapType, by default set to SAFE
     */
    private static MapCreator.MapType mapType = MapCreator.MapType.SAFE;

    public static MapCreator.MapType getMapType() {
        return mapType;
    }

    /**
     * Singleton instance of GameEngine
     */
    private static GameEngine gameEngine = null;

    /**
     * List of type player containing all players currently playing the game
     */
    List<Player> playerList = new ArrayList<>();

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
    List<Player> createPlayers(int playerAmount) {
        for (int i = 0; i < playerAmount; ++i) {
            Player player = new Player("Player " + i);
            playerList.add(player);
        }
        return playerList;
    }

    /**
     * This method takes care of creating and generating the map and tiles
     *
     * @param mapSize      the desired map size
     * @param playerAmount the amount of players that will be populated on the map
     * @return the board that was just created, null if the creation failed
     */
    Tile[][] createMap(int mapSize, int playerAmount, MapCreator.MapType mapType) {
        Map map = MapCreator.getMapInstance(mapType, mapSize, playerAmount);
        if (map != null) {
            GameEngine.mapType = mapType;
            return map.generateMap(new Random());
        }
        return null;
    }

    /**
     * This method takes care of the input and moves the indicated player as demanded
     *
     * @param input  the character indicating the direction the player is to be moved
     * @param player the player to be moved
     * @return true if the player was moved successfully, false otherwise
     */
    boolean handleInput(char input, Player player) {
        if (Direction.get(input) != null) {
            return player.move(Direction.get(input));
        }
        return false;
    }

    /**
     * This method generates html that displays the position of the player
     *
     * @param gameBoard the board that the game is being played on
     * @param player    the player object that the html is being generated for
     */
    void writeHtml(Tile[][] gameBoard, Player player) {
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

        GenerateHtmlFiles.getInstance().generateHtmlFile(myPage.getHtml(), "map_" + player.getId() + ".html", GENERATED_HTML_DIR);
    }


    /**
     * Method used to create a number of Teams and populate them with players from playerList
     *
     * @param numTeams The number of teams to create
     * @return A list of Teams where each team contains a number of players
     */
    List<Team> createTeams(int numTeams) {
        Collections.shuffle(playerList);

        List<Team> teams = new ArrayList<>();

        // creating empty teams
        for (int i = 0; i < numTeams; i++) {
            teams.add(new Team("Team " + i));
        }

        // adding players to teams
        int j = 0;
        for (Player player : playerList) {

            // attaching team to player
            player.setTeam(teams.get(j));
            j = (j + 1) % teams.size();
        }

        // ensuring players can see each other's positions
        for (Player player : playerList) {
            player.attachStartPosition();
        }

        return teams;
    }
}
