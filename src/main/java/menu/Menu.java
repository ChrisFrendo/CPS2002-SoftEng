package menu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import map.Tile;
import player.Player;
import utils.Color;
import utils.FileHelperUtils;

public class Menu {
    static int playerAmount = 0;
    private static Tile[][] gameBoard = null;
    private static Scanner scanner = new Scanner(System.in);
    private static GameEngine gameEngine = GameEngine.getInstance();

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
        ArrayList<Player> playerList = gameEngine.getPlayerList();

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

    static void getPlayerMoves(List<Player> playerList) {
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

    static void setupGameValues() {
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
            gameBoard = gameEngine.createMap(mapSize, playerAmount);
        }

        System.out.println("================================================");
    }

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

    static void generateHtml(List<Player> playerList) {
        /* Generating html for each player */
        for (int i = 0; i < playerList.size(); i++) {
            gameEngine.writeHtml(gameBoard, playerList.get(i), i);
        }
    }

    static boolean checkWinners(List<Player> playerList) {
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
