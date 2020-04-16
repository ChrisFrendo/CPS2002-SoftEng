package player;

public class Position {
    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    Position(Position p) {
        this.row = p.row;
        this.column = p.column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    void setPosition(int newRow, int newColumn) {
        this.row = newRow;
        this.column = newColumn;
    }
}
