public class Player {

    private static class Position {
        public static int x;
        public static int y;
    }

    Player() {
        // Start from (0,0), can be randomized later on
        Position.x = 0;
        Position.y = 0;
    }

    public int getPositionX() { return Position.x; }

    public int getPositionY() { return Position.y; }

    public boolean setPosition(int newX, int newY) {
        if (newX >= 0 && newY >= 0) {
            Position.x = newX;
            Position.y = newY;
            return true;
        }

        return false;
    }
}
