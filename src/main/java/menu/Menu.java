package menu;

import html.*;
import map.Tile;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import player.Player;
import utils.FileHelperUtils;

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
        Tile[][] gameBoard = null;

        while (gameBoard == null) {
            System.out.println("Enter map size");
            System.out.println("Please enter a maximum of 50");
            System.out.println("and a minimum of 5 for 2-4 player");
            System.out.println("a minimum of 8 for 5-8 players");
            mapSize = scanner.nextInt();
            gameBoard = gameEngine.createMap(mapSize, playerAmount);
        }

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


        while(!treasureFound) {
            for (currentPlayerNumber = 0; currentPlayerNumber < playerList.size(); ++currentPlayerNumber) {
                System.out.println(currentPlayerNumber);

                System.out.print("Enter move (u, d, l, r) for player ");
                System.out.print(currentPlayerNumber);
                System.out.println(":");
                input = scanner.next().charAt(0);

                if (!gameEngine.handleInput(input, currentPlayerNumber)) {
                    System.out.println("Error: invalid input");
                    --currentPlayerNumber;
                }

                gameEngine.writeHtml(gameBoard, playerList.get(currentPlayerNumber), currentPlayerNumber);
                /* Check if treasure is found */
                if (playerList.get(currentPlayerNumber).isWinner()) {
                    treasureFound = true;
                    System.out.print("Player ");
                    System.out.print(currentPlayerNumber);
                    System.out.println(" found the treasure");
                    break;
                }
            }
        }
    }
}
