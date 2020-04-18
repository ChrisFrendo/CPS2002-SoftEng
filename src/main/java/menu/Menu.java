package menu;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Scanner scanner = new Scanner(System.in);
        GameEngine gameEngine = GameEngine.getInstance();

        /* Prompts for amount of players */
        int playerAmount = 0;
        System.out.println("Enter number of players:");
        while(!gameEngine.validatePlayers(playerAmount)) {
            System.out.println("Please enter an amount from 2 to 8");
            playerAmount = scanner.nextInt();
        }

        /* Prompts for map size */
        int mapSize = 0;
        System.out.println("Enter map size");
        while(gameEngine.createMap(mapSize, playerAmount) != null) {
            System.out.println("Please enter a maximum of 50");
            System.out.println("and a minimum of 5 for 2-4 player");
            System.out.println("a minimum of 8 for 5-8 players");
            mapSize = scanner.nextInt();
        }

        /* Creates Players */
        gameEngine.createPlayers(playerAmount);
    }
}
