package player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import map.Map;
import map.MapCreator;
import map.Tile;
import menu.GameEngine;

/**
 * Class that represents a player in the game
 */
public class Player {

    private Position currentPosition;
    Position startingPosition;
    private String id;
    private boolean winner;
    private List<Tile> visitedTiles;
    private List<Direction> pastMoves;

    public Player(String id) {
        this.id = id;
        this.winner = false;
        this.startingPosition = generateRandomPosition(new Random());
        this.currentPosition = new Position(this.startingPosition);
        this.visitedTiles = new ArrayList<>();

        String mapType = GameEngine.getMapType();
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
        String mapType = GameEngine.getMapType();
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

        String mapType = GameEngine.getMapType();
        Map map = MapCreator.getMapInstance(mapType);

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
                this.currentPosition.setPosition(newRow, newColumn);
            } else if (tileStatus.equals(Tile.Status.WATER)) {
                this.currentPosition = new Position(this.startingPosition);
            } else {
                this.currentPosition.setPosition(newRow, newColumn);
                this.winner = true;
            }
            this.visitedTiles.add(map.getTile(newRow, newColumn));
            this.pastMoves.add(direction);
            return true;
        } else {
            return false;
        }
    }
}
