package menu;

import html.Page;
import player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Scanner scanner = new Scanner(System.in);
        GameEngine gameEngine = GameEngine.getInstance();

        /* Prompts for amount of players */
        int playerAmount = 0;
        System.out.println("Enter number of players:");
        while (!gameEngine.validatePlayers(playerAmount)) {
            System.out.println("Please enter an amount from 2 to 8");
            playerAmount = scanner.nextInt();
        }

        /* Prompts for map size */
        int mapSize;
        do {
            System.out.println("Enter map size");
            System.out.println("Please enter a maximum of 50");
            System.out.println("and a minimum of 5 for 2-4 player");
            System.out.println("a minimum of 8 for 5-8 players");
            mapSize = scanner.nextInt();
        } while (gameEngine.createMap(mapSize, playerAmount) == null);


        /* Creates Players */
        gameEngine.createPlayers(playerAmount);


        /* Moves players */
        ArrayList<Player> playerList = gameEngine.getPlayerList();
        char input;
        int currentPlayerNumber = 0;
        while(!playerList.get(currentPlayerNumber).isWinner()) {
            for (currentPlayerNumber = 0; currentPlayerNumber < playerList.size(); ++currentPlayerNumber) {
                System.out.print("Enter move (u, d, l, r) for player ");
                System.out.print(currentPlayerNumber);
                System.out.println(":");
                input = scanner.next().charAt(0);

                if (!gameEngine.handleInput(input, currentPlayerNumber)) {
                    System.out.println("Error: invalid input");
                    --currentPlayerNumber;
                }
            }

            currentPlayerNumber = 0;
        }
    }
}