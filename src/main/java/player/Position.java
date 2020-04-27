package player;

/**
 * Class for storing the position of a player. This works by
 * storing the row and column the player is located in.
 */
public class Position {
    private int row;
    private int column;

    /**
     * Constructor for Position that takes a row and a column
     *
     * @param row the position in the row
     * @param column the position in the column
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Constructor for Position that takes a position
     *
     * @param p a position instance indicating the required position
     */
    Position(Position p) {
        this.row = p.row;
        this.column = p.column;
    }

    /**
     * Getter for the row of the position
     *
     * @return the row the position is located in
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter for the column of the position
     *
     * @return the column the position is located in
     */
    public int getColumn() {
        return column;
    }

    /**
     * Setter for the position
     *
     * @param newRow the row the new position is to be located in
     * @param newColumn the column the new position is to be located in
     */
    void setPosition(int newRow, int newColumn) {
        this.row = newRow;
        this.column = newColumn;
    }
}
