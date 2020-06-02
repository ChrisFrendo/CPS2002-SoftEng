package menu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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
     * Boolean used to determine if console outputs should have ansi colours or not
     */
    private static boolean withColours = false;

    /**
     * Main method
     *
     * @param args any arguments passed to the program
     *             One arg is used, to determine if game should use ANSI colours or not
     *             if "colours" is passed then game uses colours, else no colours are used
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("colours")) {
            withColours = true;
        }

        printWithColour(Color.CYAN_BOLD_BRIGHT, "Welcome to Water tiles!!");

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
            printWithColour(Color.MAGENTA, playerList.get(i).getId());
            System.out.println("Enter move [u, d, l, r]:");
            input = scanner.next().charAt(0);

            if (!gameEngine.handleInput(input, i)) {
                printWithColour(Color.RED, "Cannot move in that direction. Enter a different move");
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
        while (!gameEngine.validatePlayers(playerAmount)) {
            System.out.println("Enter number of players:");
            System.out.println("Please enter an amount from 2 to 8");
            playerAmount = getIntInput();
            scanner.nextLine();
        }

        /* Prompts for map size */
        while (gameBoard == null) {
            System.out.println("Enter map size");
            System.out.println("Please enter a maximum of 50");
            System.out.println("and a minimum of 5 for 2-4 player");
            System.out.println("a minimum of 8 for 5-8 players");
            int mapSize = getIntInput();
            scanner.nextLine();
            gameBoard = gameEngine.createMap(mapSize, playerAmount);
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
            printWithColour(Color.RED, "Invalid Input");
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
        for (Player player : playerList) {
            /* Check if treasure is found */
            if (player.isWinner()) {
                treasureFound = true;
                printWithColour(Color.YELLOW_BOLD, player.getId() + " found the treasure");
            }
        }
        return treasureFound;
    }

    /**
     * Helper function used to print using ansi colours depending on withColours global variable
     *
     * @param color   The color to use
     * @param message The message to print
     */
    private static void printWithColour(Color color, String message) {
        if (withColours) {
            System.out.print(color);
            System.out.println(message);
            System.out.print(Color.RESET);
        } else {
            System.out.println(message);
        }
    }
}
