package menu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import map.MapCreator;
import map.Tile;
import player.Player;
import utils.Color;
import utils.FileHelperUtils;

/**
 * Main class used to run the game
 */
public class Menu {

    /**
     * static field used to keep track of the number of players in the game
     */
    private static int playerAmount = 0;


    /**
     * static field used to store the map currently being played
     */
    private static Tile[][] gameBoard = null;


    /**
     * static instance of a scanner used to get input from the console
     */
    private static Scanner scanner = new Scanner(System.in);


    /**
     * static instance of GameEngine whose methods are used to drive the game
     */
    private static GameEngine gameEngine = GameEngine.getInstance();


    /**
     * Main method
     *
     * @param args any arguments passed to the program, none are used in the game
     */
    public static void main(String[] args) {
        System.out.print(Color.CYAN_BOLD_BRIGHT);
        System.out.println("Welcome to Water tiles!!");
        System.out.print(Color.RESET);

        /* calling helper function to get number of players and map size*/
        while (true) {
            try {
                setupGameValues();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                continue;
            }
            break;
        }

        System.out.println("Starting Game");

        /* Creates Players */
        gameEngine.createPlayers(playerAmount);
        List<Player> playerList = gameEngine.getPlayerList();

        FileHelperUtils.deleteDirectory("generatedHTML");

        generateHtml(playerList);

        /* Moves players */
        boolean treasureFound = false;


        while (!treasureFound) {
            System.out.println("================================================");
            System.out.println("Generated Updated Maps");

            getPlayerMoves(playerList);

            generateHtml(playerList);

            treasureFound = checkWinners(playerList);
        }
    }

    /**
     * Helper function used to get the moves for all the players
     *
     * @param playerList The list of currently playing players
     */
    private static void getPlayerMoves(List<Player> playerList) {
        char input;

        /* Getting moves for all players */
        for (int i = 0; i < playerList.size(); ++i) {
            System.out.print(Color.MAGENTA_UNDERLINED);
            System.out.println(playerList.get(i).getId());
            System.out.print(Color.RESET);
            System.out.println("Enter move [u, d, l, r]:");
            input = scanner.next().charAt(0);

            if (!gameEngine.handleInput(input, i)) {
                System.out.print(Color.RED);
                System.out.println("Cannot move in that direction. Enter a different move");
                System.out.print(Color.RESET);
                --i;
            }

        }
    }

    /**
     * Helper function used to get the number of players and size of the map from the console
     */
    private static void setupGameValues() {
        System.out.println("================================================");
        /* Prompts for amount of players */
        System.out.println("Enter number of players:");
        while (!gameEngine.validatePlayers(playerAmount)) {
            System.out.println("Please enter an amount from 2 to 8");
            playerAmount = getIntInput();
            scanner.nextLine();
        }

        /* Prompts for map size */
        System.out.println("Enter map size");
        while (gameBoard == null) {
            System.out.println("Please enter a maximum of 50");
            System.out.println("and a minimum of 5 for 2-4 player");
            System.out.println("a minimum of 8 for 5-8 players");
            int mapSize = getIntInput();
            scanner.nextLine();

            System.out.println("Enter map type (enter corresponding number)");
            System.out.println("1) Safe");
            System.out.println("2) Hazardous");
            int mapTypeInt = getIntInput();
            MapCreator.MapType mapType;
            scanner.nextLine();

            if (mapTypeInt == 1) {
                mapType = MapCreator.MapType.SAFE;
            } else {
                mapType = MapCreator.MapType.HAZARDOUS;
            }

            gameBoard = gameEngine.createMap(mapSize, playerAmount, mapType);
        }

        System.out.println("================================================");
    }

    /**
     * Helper function used to get an integer input from the console
     *
     * @return The inputted integer to the console
     */
    private static int getIntInput() {
        int value;
        try {
            value = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.print(Color.RED);
            System.out.println("Invalid Input");
            System.out.print(Color.RESET);
            throw e;
        }
        return value;
    }

    /**
     * Helper function used to generate the html file for each player
     *
     * @param playerList List of currently playing players
     */
    private static void generateHtml(List<Player> playerList) {
        /* Generating html for each player */
        for (int i = 0; i < playerList.size(); i++) {
            gameEngine.writeHtml(gameBoard, playerList.get(i), i);
        }
    }

    /**
     * Helper function used to check if there are any winners. Prints out any winners
     *
     * @param playerList List of players currently playing the game
     * @return true if there are winners, false otherwise
     */
    private static boolean checkWinners(List<Player> playerList) {
        boolean treasureFound = false;
        /* Checking if there are any winners */
        for (int i = 0; i < playerList.size(); i++) {
            /* Check if treasure is found */
            if (playerList.get(i).isWinner()) {
                treasureFound = true;
                System.out.print(Color.YELLOW_BOLD);
                System.out.println("Player " + i + " found the treasure");
                System.out.print(Color.RESET);
            }
        }
        return treasureFound;
    }
}
