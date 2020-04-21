package menu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import map.Tile;
import player.Player;
import utils.FileHelperUtils;

public class Menu {
    private static int playerAmount = 0;
    private static Tile[][] gameBoard = null;
    private static Scanner scanner = new Scanner(System.in);
    private static GameEngine gameEngine = GameEngine.getInstance();

    public static void main(String[] args) {

        /* calling helper function to get number of players and map size*/
        setupGameValues();

        System.out.println("Starting Game");

        /* Creates Players */
        gameEngine.createPlayers(playerAmount);
        ArrayList<Player> playerList = gameEngine.getPlayerList();

        FileHelperUtils.deleteDirectory("generatedHTML");

        /* Generate initial positions in html */
        for (int i = 0; i < playerList.size(); ++i) {
            gameEngine.writeHtml(gameBoard, playerList.get(i), i);
        }

        /* Moves players */
        char input;
        int currentPlayerNumber;
        boolean treasureFound = false;


        while (!treasureFound) {
            System.out.println("================================================");
            System.out.println("Generated Updated Maps");
            /* Getting moves for all players */
            for (currentPlayerNumber = 0; currentPlayerNumber < playerList.size(); ++currentPlayerNumber) {
                System.out.println(playerList.get(currentPlayerNumber).getId() + "'s turn");
                System.out.println("Enter move [u, d, l, r]:");
                input = scanner.next().charAt(0);

                if (!gameEngine.handleInput(input, currentPlayerNumber)) {
                    System.out.print("\033[0;31m");
                    System.out.println("Cannot move in that direction. Enter a different move");
                    System.out.print("\033[0m");
                    --currentPlayerNumber;
                }

            }

            /* Generating html for each player */
            for (currentPlayerNumber = 0; currentPlayerNumber < playerList.size(); currentPlayerNumber++) {
                gameEngine.writeHtml(gameBoard, playerList.get(currentPlayerNumber), currentPlayerNumber);
            }

            /* Checking if there are any winners */
            for (currentPlayerNumber = 0; currentPlayerNumber < playerList.size(); currentPlayerNumber++) {
                /* Check if treasure is found */
                if (playerList.get(currentPlayerNumber).isWinner()) {
                    treasureFound = true;
                    System.out.print("Player " + currentPlayerNumber + " found the treasure");
                }
            }
        }
    }

    private static void setupGameValues() {
        System.out.println("================================================");
        /* Prompts for amount of players */
        System.out.println("Enter number of players:");
        while (!gameEngine.validatePlayers(playerAmount)) {
            System.out.println("Please enter an amount from 2 to 8");
            try {
                playerAmount = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
            } finally {
                scanner.nextLine();
            }
        }

        /* Prompts for map size */
        int mapSize;

        System.out.println("Enter map size");
        while (gameBoard == null) {
            System.out.println("Please enter a maximum of 50");
            System.out.println("and a minimum of 5 for 2-4 player");
            System.out.println("a minimum of 8 for 5-8 players");
            try {
                mapSize = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                gameBoard = null;
                scanner.nextLine();
                continue;
            }
            gameBoard = gameEngine.createMap(mapSize, playerAmount);
        }
        scanner.nextLine();
        System.out.println("================================================");
    }
}
