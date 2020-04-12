package player;

import map.Map;

public class Player {

    public class Position {
        private int x;
        private int y;

        public int getPositionX() { return x; }
        public int getPositionY() { return y; }
        public boolean setPosition(int newX, int newY) {
            if (Map.getInstance().tileExists(newX, newY)) {
                this.x = newX;
                this.y = newY;

                return true;
            }

            return false;
        }
    }

    Position position = new Position();

    Player() {
        // Start from (0,0), can be randomized later on
        this.position.setPosition(0, 0);
    }
}
