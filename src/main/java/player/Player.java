package player;

import java.util.Random;

import map.Map;
import map.Tile;

public class Player {

    Position currentPosition;
    Position startingPosition;
    private String id;
    private boolean winner;

    public Player(String id) {
        this.id = id;
        this.winner = false;
        this.startingPosition = generateRandomPosition(new Random());
        this.currentPosition = new Position(this.startingPosition);
    }

    public String getId() {
        return id;
    }

    boolean isWinner() {
        return winner;
    }

    private static Position generateRandomPosition(Random r) {
        int size = Map.getInstance().getSize();

        int x = r.nextInt(size);
        int y = r.nextInt(size);

        if (Map.getInstance().isValidStartingPosition(x, y)) {
            return new Position(x, y);
        } else {
            return generateRandomPosition(r);
        }
    }

    boolean move(Direction direction) {
        int newX;
        int newY;

        switch (direction) {
            case up:
                newX = this.currentPosition.getPositionX();
                newY = this.currentPosition.getPositionY() - 1;
                break;
            case down:
                newX = this.currentPosition.getPositionX();
                newY = this.currentPosition.getPositionY() + 1;
                break;
            case left:
                newX = this.currentPosition.getPositionX() - 1;
                newY = this.currentPosition.getPositionY();
                break;
            default:
                newX = this.currentPosition.getPositionX() + 1;
                newY = this.currentPosition.getPositionY();
        }

        if (Map.getInstance().tileExists(newX, newY)) {
            Tile.Status tileStatus = Map.getInstance().getTileStatus(newX, newY);
            if (tileStatus.equals(Tile.Status.GRASS)) {
                this.currentPosition.setPosition(newX, newY);
            } else if (tileStatus.equals(Tile.Status.WATER)) {
                this.currentPosition = new Position(this.startingPosition);
            } else {
                this.currentPosition.setPosition(newX, newY);
                this.winner = true;
            }
            return true;
        } else {
            return false;
        }

    }

}
