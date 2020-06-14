package menu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import map.Tile;
import player.Player;
import team.Observer;
import team.Team;
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

        /* calling helper function to get number of players and map size */
        while (true) {
            try {
                setupGameValues();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                continue;
            }
            break;
        }

        /* Creates Players */
        List<Player> playerList = gameEngine.createPlayers(playerAmount);

        FileHelperUtils.deleteDirectory("generatedHTML");

        playGame(playerList);
    }

    private static void playGame(List<Player> playerList) {
        System.out.print(Color.CYAN);
        System.out.println("Select Game Mode: ");
        System.out.println("1: Solo Play");
        System.out.println("2: Team Play");
        System.out.print(Color.RESET);

        boolean valid;

        // decide if team or normal mode
        do {
            valid = true;
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    soloPlay(playerList);
                    break;
                case "2":
                    teamPlay(playerList);
                    break;
                default:
                    System.out.print(Color.RED);
                    System.out.println("Invalid Input: 1 = Solo, 2 = Team");
                    System.out.print(Color.RESET);
                    valid = false;
            }
        } while (!valid);
    }

    /**
     * Method used to play a game in team mode
     *
     * @param playerList The list of players that are playing
     */
    private static void teamPlay(List<Player> playerList) {
        int numTeams = getNumTeams(playerList.size());

        List<Team> teams = gameEngine.createTeams(numTeams);

        System.out.println("==============================================");

        printTeams(teams);

        // main game loop
        boolean treasureFound;

        do {
            generateHtml(playerList);
            System.out.println("================================================");

            // get a single player to play for each team
            for (Team team : teams) {
                getPlayerMoves(team.getCurrentPlayer());
                team.incrementCurrentPlayerCount();
            }

            treasureFound = checkWinnersTeam(playerList);
        } while (!treasureFound);

        // generate html with treasure tile
        generateHtml(playerList);
    }

    /**
     * Helper function used to get the number of teams from console
     * Checks if the number of teams is less than numPlayers
     *
     * @param numPlayers The number of players playing the game
     * @return the number of teams inputted by the used
     */
    private static int getNumTeams(int numPlayers) {
        int numTeams;
        // getting number of teams
        do {
            System.out.println("Enter number of teams:");
            numTeams = getIntInput();
            scanner.nextLine();

            // cannot have more teams than players
            if (numTeams > numPlayers) {
                System.out.print(Color.RED);
                System.out.println("Cannot have more teams than players");
                System.out.print(Color.RESET);
            } else {
                break;
            }
        } while (true);

        return numTeams;
    }

    /**
     * Prints each team's players
     *
     * @param teams the list of teams to print
     */
    private static void printTeams(List<Team> teams) {
        // printing team lists
        for (Team team : teams) {
            System.out.print(Color.CYAN);
            System.out.println(team.getTeamId() + " players: ");
            System.out.print(Color.RESET);
            for (Observer o : team.getObservers()) {
                Player p = (Player) o;
                System.out.println(p.getId());
            }
            System.out.println();
        }
    }

    /**
     * Method used to play the game in solo mode
     *
     * @param playerList The list of players currently playing
     */
    private static void soloPlay(List<Player> playerList) {
        System.out.println("Starting Game");

        generateHtml(playerList);

        /* Moves players */
        boolean treasureFound = false;

        while (!treasureFound) {
            System.out.println("================================================");
            System.out.println("Generated Updated Maps");

            /* Getting moves for all players */
            for (Player player : playerList) {
                getPlayerMoves(player);
            }

            generateHtml(playerList);

            treasureFound = checkWinners(playerList);
        }
    }

    /**
     * Helper function used to get the moves for all the players
     *
     * @param player the player currently moving
     */
    private static void getPlayerMoves(Player player) {
        char input;

        /* Getting moves for all players */
        do {
            System.out.print(Color.MAGENTA_UNDERLINED);
            System.out.println(player.getId());
            System.out.print(Color.RESET);
            System.out.println("Enter move [u, d, l, r]:");
            input = scanner.next().charAt(0);

            if (gameEngine.handleInput(input, player)) {
                break;
            } else {
                System.out.print(Color.RED);
                System.out.println("Cannot move in that direction. Enter a different move");
                System.out.print(Color.RESET);
            }

        } while (true);
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
        for (Player player : playerList) {
            gameEngine.writeHtml(gameBoard, player);
        }
    }

    private static boolean checkWinnersTeam(List<Player> playerList) {
        boolean treasureFound = false;

        /* Checking if there are any winners */
        for (Player player : playerList) {
            /* Check if treasure is found */
            if (player.isWinner()) {
                treasureFound = true;
                System.out.print(Color.YELLOW_BOLD);

                Team team = player.getTeam();
                System.out.println(team.getTeamId() + " found the treasure. Winners are: ");
                for (Observer x : team.getObservers()) {
                    Player p = (Player) x;
                    System.out.println(p.getId());
                }
                System.out.print(Color.RESET);
            }
        }
        return treasureFound;
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
                System.out.print(Color.YELLOW_BOLD);
                System.out.println(player.getId() + " found the treasure");
                System.out.print(Color.RESET);
            }
        }
        return treasureFound;
    }
}
