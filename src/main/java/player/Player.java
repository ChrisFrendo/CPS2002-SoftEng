package player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import map.Map;
import map.Tile;

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
        this.visitedTiles.add(Map.getInstance().getTile(this.startingPosition.getRow(), this.startingPosition.getColumn()));
        this.pastMoves = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Tile> getVisitedTiles() {
        return visitedTiles;
    }

    public List<Direction> getPastMoves() {
        return pastMoves;
    }

    boolean isWinner() {
        return winner;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    private static Position generateRandomPosition(Random r) {
        int size = Map.getInstance().getSize();

        int row = r.nextInt(size);
        int column = r.nextInt(size);

        if (Map.getInstance().isValidStartingPosition(row, column)) {
            return new Position(row, column);
        } else {
            return generateRandomPosition(r);
        }
    }

    public boolean move(Direction direction) {
        int newRow;
        int newColumn;

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

        if (Map.getInstance().tileExists(newRow, newColumn)) {
            Tile.Status tileStatus = Map.getInstance().getTileStatus(newRow, newColumn);
            if (tileStatus.equals(Tile.Status.GRASS)) {
                this.currentPosition.setPosition(newRow, newColumn);
            } else if (tileStatus.equals(Tile.Status.WATER)) {
                this.currentPosition = new Position(this.startingPosition);
            } else {
                this.currentPosition.setPosition(newRow, newColumn);
                this.winner = true;
            }
            this.visitedTiles.add(Map.getInstance().getTile(newRow, newColumn));
            this.pastMoves.add(direction);
            return true;
        } else {
            return false;
        }

    }

}
