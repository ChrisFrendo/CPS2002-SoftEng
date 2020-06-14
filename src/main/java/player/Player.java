package player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import map.Map;
import map.MapCreator;
import map.Tile;
import team.Observer;
import team.Team;
import menu.GameEngine;

/**
 * Class that represents a player in the game
 */
public class Player extends Observer<Team> {

    private Position currentPosition;
    Position startingPosition;
    private String id;
    private boolean winner = false;
    private List<Tile> visitedTiles;
    private List<Direction> pastMoves;

    public Player(String id) {
        this.id = id;
        this.startingPosition = generateRandomPosition(new Random());
        this.currentPosition = new Position(this.startingPosition);
        this.visitedTiles = new ArrayList<>();

        MapCreator.MapType mapType = GameEngine.getMapType();
        Map map = MapCreator.getMapInstance(mapType);

        this.visitedTiles.add(map.getTile(this.startingPosition.getRow(), this.startingPosition.getColumn()));
        this.pastMoves = new ArrayList<>();
    }

    /**
     * Getter
     *
     * @return player ID
     */
    public String getId() {
        return id;
    }

    /**
     * Getter
     *
     * @return list containing the tiles visited by a player
     */
    public List<Tile> getVisitedTiles() {
        return visitedTiles;
    }

    /**
     * Getter
     *
     * @return list of directions which the player has taken
     */
    public List<Direction> getPastMoves() {
        return pastMoves;
    }

    /**
     * This method checks whether the player is a winner
     *
     * @return true if the player is a winner, false otherwise
     */
    public boolean isWinner() {
        return winner;
    }

    /**
     * Getter
     *
     * @return the current position of the player
     */
    public Position getCurrentPosition() {
        return currentPosition;
    }

    /**
     * This method generates a random position on the map. It then checks if the position
     * is valid and returns it if it is. If it is not, it generating until a valid position
     * is found.
     *
     * @param r an instance of random used to generate the random numbers
     * @return a valid position on the already created map
     */
    private static Position generateRandomPosition(Random r) {
        MapCreator.MapType mapType = GameEngine.getMapType();
        Map map = MapCreator.getMapInstance(mapType);

        int size = map.getSize();

        int row = r.nextInt(size);
        int column = r.nextInt(size);

        if (map.isValidStartingPosition(row, column)) {
            return new Position(row, column);
        } else {
            return generateRandomPosition(r);
        }
    }

    /**
     * This method tries to move the player in the desired direction.
     * It also validates if the move was successful i.e. if the
     * new position is a tile that exists on the map.
     *
     * @param direction one of the four possible directions
     * @return true if the move was successful, false otherwise
     */
    public boolean move(Direction direction) {
        int newRow;
        int newColumn;

        MapCreator.MapType mapType = GameEngine.getMapType();
        Map map = MapCreator.getMapInstance(mapType);

        // computing the values of newRow and newColumn based on the director
        switch (direction) {
            case up:
                newRow = this.currentPosition.getRow() - 1;
                newColumn = this.currentPosition.getColumn();
                break;
            case down:
                newRow = this.currentPosition.getRow() + 1;
                newColumn = this.currentPosition.getColumn();
                break;
            case left:
                newRow = this.currentPosition.getRow();
                newColumn = this.currentPosition.getColumn() - 1;
                break;
            default:
                newRow = this.currentPosition.getRow();
                newColumn = this.currentPosition.getColumn() + 1;
        }

        if (map.tileExists(newRow, newColumn)) {
            Tile.Status tileStatus = map.getTileStatus(newRow, newColumn);
            if (tileStatus.equals(Tile.Status.GRASS)) {
                // player can go on grass
                this.currentPosition.setPosition(newRow, newColumn);
            } else if (tileStatus.equals(Tile.Status.WATER)) {

                // if there is a subject, set its state to the water tile's position
                if (this.subject != null)
                    this.subject.setState(new Position(newRow, newColumn));

                // reset to starting position
                this.currentPosition = new Position(this.startingPosition);
            } else {
                // updating current position and setting player as winner
                this.currentPosition.setPosition(newRow, newColumn);
                this.winner = true;
            }

            // adding tile to visitedTiles
            this.visitedTiles.add(map.getTile(newRow, newColumn));

            // adding direction to pastMoves
            this.pastMoves.add(direction);

            // if there is a subject, set its state the the current position to update map visibility
            if (this.subject != null)
                this.subject.setState(this.currentPosition);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Overridden update method from Observer class
     * <p>
     * Adds the position from the subject's state to the list of visitedTiles if it is not already there
     * This allows players attached to a subject (part of the same team) to view each other's visible tiles
     */
    @Override
    public void update() {
        MapCreator.MapType mapType = GameEngine.getMapType();
        Map map = MapCreator.getMapInstance(mapType);

        Position position = this.subject.getState();
        Tile newTile = map.getTile(position.getRow(), position.getColumn());
        if (!this.visitedTiles.contains(newTile))
            this.visitedTiles.add(newTile);
    }

    /**
     * Attaches a given team as the instance's subject
     *
     * @param team The Team which the player will belong to
     */
    public void setTeam(Team team) {
        this.subject = team;
        this.subject.attach(this);
    }

    /**
     * Getter
     *
     * @return returns the subject of the player, which is the player's team
     */
    public Team getTeam() {
        return this.subject;
    }

    /**
     * Sets the state of the Team subject with the player's startingPosition
     * This ensures all team members can see each other's starting positions
     */
    public void attachStartPosition() {
        this.subject.setState(this.startingPosition);
    }
}
