package player;

import java.util.Random;

import map.Map;

public class Player {

    Position currentPosition;
    private Position startingPosition;
    private String id;

    public Player(String id) {
        this.id = id;

        this.startingPosition = generateRandomPosition(new Random());
        this.currentPosition = this.startingPosition;
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
        switch (direction) {
            case up:
                return this.currentPosition.setPosition(this.currentPosition.getPositionX(), this.currentPosition.getPositionY() - 1);
            case down:
                return this.currentPosition.setPosition(this.currentPosition.getPositionX(), this.currentPosition.getPositionY() + 1);
            case left:
                return this.currentPosition.setPosition(this.currentPosition.getPositionX() - 1, this.currentPosition.getPositionY());
            default:
                return this.currentPosition.setPosition(this.currentPosition.getPositionX() + 1, this.currentPosition.getPositionY());
        }
    }

    public String getId() {
        return id;
    }
}
