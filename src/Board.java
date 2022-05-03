import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {
    private int [][] tiles;
    private String stringOfBoard;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = tiles;
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.tiles.length).append(System.lineSeparator());

        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0;j < tiles.length; j++) {
                sb.append(tiles[i][j]).append(" ");
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString();
        //return Stream.of(this.tiles).map(Arrays::toString).collect(Collectors.joining("\n"));
    }

    // board dimension n
    public int dimension() {

        return 0;
    }

    // number of tiles out of place
    public int hamming() {
        return 0;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return 0;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return false;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] tiles = new int[1][1];
        Board board = new Board(tiles);
        return board;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int n = 2;
        int[][] tiles = new int[n][n];

        tiles[0][0] = 1;
        tiles[0][1] = 0;
        tiles[1][0] = 2;
        tiles[1][1] = 3;

        Board board = new Board(tiles);

        System.out.println(board.toString());
    }

}
