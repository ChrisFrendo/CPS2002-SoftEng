package menu;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Scanner scanner = new Scanner(System.in);

        /* Prompts for amount of players */
        int playerAmount = 0;
        System.out.println("Enter number of players:");
        while(playerAmount < 2 || playerAmount > 8) {
            System.out.println("Please enter an amount from 2 to 8");
            playerAmount = scanner.nextInt();
        }

        /* Prompts for map size */
        int mapSize = 0;
        System.out.println("Enter map size");
        while( (playerAmount > 4 || mapSize < 5 || mapSize > 50)
                && (playerAmount < 5 || mapSize < 8 || mapSize > 50) ) {
            System.out.println("Please enter a maximum of 50");
            System.out.println("and a minimum of 5 for 2-4 player");
            System.out.println("a minimum of 8 for 5-8 players");
            mapSize = scanner.nextInt();
        }
    }
}
