package player;

import map.Map;

class Position {
    private int x;
    private int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

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
