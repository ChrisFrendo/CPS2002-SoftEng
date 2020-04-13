package player;

import map.Map;

public class Player {

    public static class Position {
        private int x;
        private int y;

        int getPositionX() {
            return x;
        }

        int getPositionY() {
            return y;
        }

        boolean setPosition(int newX, int newY) {
            if (Map.getInstance().tileExists(newX, newY)) {
                this.x = newX;
                this.y = newY;

                return true;
            }

            return false;
        }
    }

    Position position = new Position();
    private String id;

    public Player(String id) {
        this.id = id;
        // Start from (0,0), can be randomized later on
        this.position.setPosition(0, 0);
    }

    boolean move(Direction direction) {
        switch (direction) {
            case up:
                return this.position.setPosition(this.position.getPositionX(), this.position.getPositionY() + 1);
            case down:
                return this.position.setPosition(this.position.getPositionX(), this.position.getPositionY() - 1);
            case left:
                return this.position.setPosition(this.position.getPositionX() - 1, this.position.getPositionY());
            default:
                return this.position.setPosition(this.position.getPositionX() + 1, this.position.getPositionY());
        }
    }

    public String getId() {
        return id;
    }
}
