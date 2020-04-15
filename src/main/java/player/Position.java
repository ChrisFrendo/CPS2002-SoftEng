package player;

class Position {
    private int x;
    private int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Position(Position p) {
        this.x = p.x;
        this.y = p.y;
    }

    int getPositionX() {
        return x;
    }

    int getPositionY() {
        return y;
    }

    void setPosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
}
