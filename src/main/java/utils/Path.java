// see: https://www.geeksforgeeks.org/find-whether-path-two-cells-matrix/

package utils;

import map.Tile;

// Java program to find path between two
// cell in matrix
public class Path {

    // method for finding and printing
    // whether the path exists or not
    public static boolean isPath(Tile[][] matrix, int row, int column, int n) {
        // defining visited array to keep
        // track of already visited indexes
        boolean[][] visited = new boolean[n][n];

        // flag to indicate whether the path exists or not
        boolean flag = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // if matrix[i][j] is source
                // and it is not visited
                if ((i == row && j == column) && !visited[i][j])

                    // starting from i, j and then finding the path
                    if (isPath(matrix, i, j, visited)) {
                        flag = true; // if path exists
                        break;
                    }
            }
        }

        return flag;
    }


    // method for checking boundries
    private static boolean isSafe(int i, int j, Tile[][] matrix) {
        return i >= 0 && i < matrix.length && j >= 0
                && j < matrix[0].length;
    }

    // Returns true if there is a path from a source (a
    // cell with value 1) to a destination (a cell with
    // value 2)
    private static boolean isPath(Tile[][] matrix,
                                  int i, int j, boolean[][] visited) {

        // checking the boundries, walls and
        // whether the cell is unvisited
        if (isSafe(i, j, matrix) && !matrix[i][j].getStatus().equals(Tile.Status.WATER)
                && !visited[i][j]) {
            // make the cell visited
            visited[i][j] = true;

            // if the cell is the required
            // destination then return true
            if (matrix[i][j].getStatus().equals(Tile.Status.TREASURE))
                return true;

            // traverse up
            boolean up = isPath(matrix, i - 1, j, visited);

            // if path is found in up direction return true
            if (up)
                return true;

            // traverse left
            boolean left = isPath(matrix, i, j - 1, visited);

            // if path is found in left direction return true
            if (left)
                return true;

            //traverse down
            boolean down = isPath(matrix, i + 1, j, visited);

            // if path is found in down direction return true
            if (down)
                return true;

            // traverse right

            // if path is found in right direction return true
            return isPath(matrix, i, j + 1, visited);
        }
        return false; // no path has been found
    }
}
