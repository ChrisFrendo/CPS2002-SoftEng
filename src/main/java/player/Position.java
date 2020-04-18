package player;

class Position {
    private int row;
    private int column;

    Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    Position(Position p) {
        this.row = p.row;
        this.column = p.column;
    }

    int getRow() {
        return row;
    }

    int getColumn() {
        return column;
    }

    void setPosition(int newRow, int newColumn) {
        this.row = newRow;
        this.column = newColumn;
    }
}
