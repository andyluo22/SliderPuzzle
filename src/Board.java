import com.sun.jmx.snmp.SnmpUnknownMsgProcModelException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {
    private int[][] tiles;
    private int len;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = tiles;
        len = tiles.length;
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(len).append(System.lineSeparator());

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                sb.append(tiles[i][j]).append(" ");
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString();
        //return Stream.of(this.tiles).map(Arrays::toString).collect(Collectors.joining("\n"));
        //this would print out the array with square brackets so not as flexible in terms of printing strings
    }

    // board dimension n
    public int dimension() {
        return this.tiles.length;
    }

    // number of tiles out of place
    public int hamming() {
        int countOutPlace = 0;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (tiles[i][j] != (i * len + j + 1) && tiles[i][j] != 0) {
                    countOutPlace++;
                }
            }
        }

        return countOutPlace; // 0 doesn't count
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int distance = 0;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int num = tiles[i][j];

                if (num != 0) { // 0 doesn't count
                    int rowExpected;
                    int colExpected;

                    if (num % len == 0) {
                        rowExpected = num / len - 1;
                        colExpected = len - 1;

                    } else {
                        rowExpected = num / len;
                        colExpected = num % len - 1;

                    }
                    distance += Math.abs(rowExpected - i) + Math.abs(colExpected - j);
                }
            }
        }
        return distance;
    }

    // is this board the goal board?
    public boolean isGoal() {
        int countOutPlace = 0;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (tiles[i][j] != (i * len + j + 1) && tiles[i][j] != 0) {
                    countOutPlace++;
                }
            }
        }

        return countOutPlace == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (!(y instanceof Board)) {
            return false;
        }

        Board cast = (Board) y;

        if (cast.len != this.len) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (cast.tiles[i][j] != this.tiles[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    // all neighboring boards we are not returning an iterator we are returning ITERABLE
    public Iterable<Board> neighbors() {
        ArrayList<Board> list = new ArrayList<>();
        ArrayList<Board> listNeighbors = new ArrayList<>();

        listNeighbors = neighbourBoards(list, this.tiles);

        return listNeighbors;
    }

    private ArrayList neighbourBoards(ArrayList storeBoards, int[][] boardOriginal) {
        boolean condition = false;
        int countLeft = 0, countRight = 0, countUp = 0, countDown = 0;
        int row = 0;
        int col = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (boardOriginal[i][j] == 0) {
                    row = i;
                    col = j;
                }
            }
        }

        while (!condition) {
            int[][] boardStore = new int[len][len];
            //Avoid deep copying and mutability
            for (int i = 0; i < len; i++) {
                boardStore[i] = Arrays.copyOf(boardOriginal[i], boardOriginal[i].length);
            }

            if (col != 0 && countLeft == 0) {
                boardStore[row][col] = boardStore[row][col - 1];
                boardStore[row][col - 1] = 0;
                countLeft++;
                Board boardLeft = new Board(boardStore);
                storeBoards.add(boardLeft);
            } else if (col != (len - 1) && countRight == 0) {
                boardStore[row][col] = boardStore[row][col + 1];
                boardStore[row][col + 1] = 0;
                countRight++;
                Board boardRight = new Board(boardStore);
                storeBoards.add(boardRight);
            } else if (row != 0 && countUp == 0) {
                boardStore[row][col] = boardStore[row - 1][col];
                boardStore[row - 1][col] = 0;
                countUp++;
                Board boardUp = new Board(boardStore);
                storeBoards.add(boardUp);
            } else if (row != (len - 1) && countDown == 0) {
                boardStore[row][col] = boardStore[row + 1][col];
                boardStore[row + 1][col] = 0;
                countDown++;
                Board boardDown = new Board(boardStore);
                storeBoards.add(boardDown);
            } else {
                condition = true;
            }
        }

        return storeBoards;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] boardTwin = new int[len][len];
        //Avoid deep copying and mutability
        for (int i = 0; i < len; i++) {
            boardTwin[i] = Arrays.copyOf(this.tiles[i], this.tiles[i].length);
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (boardTwin[i][j] != 0 && j + 1 < len) {
                    if(boardTwin[i][j + 1] != 0) {
                        int store = boardTwin[i][j];

                        boardTwin[i][j] = boardTwin[i][j + 1];
                        boardTwin[i][j + 1] = store;

                        Board twin = new Board(boardTwin);
                        return twin;
                    }
                }
            }

        }

        return null;
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
        System.out.println(board.hamming());
        System.out.println(board.manhattan());
        System.out.println(board.isGoal());
        System.out.println();

        int n2 = 3;
        int[][] tiles2 = new int[n2][n2];

        tiles2[0][0] = 2;
        tiles2[0][1] = 0;
        tiles2[0][2] = 1;
        tiles2[1][0] = 3;
        tiles2[1][1] = 5;
        tiles2[1][2] = 4;
        tiles2[2][0] = 8;
        tiles2[2][1] = 7;
        tiles2[2][2] = 6;

        Board board2 = new Board(tiles2);

        System.out.println(board2.toString());
        System.out.println(board2.hamming());
        System.out.println(board2.manhattan());
        System.out.println(board2.isGoal());
        System.out.println();

        int n3 = 3;
        int[][] tiles3 = new int[n2][n2];

        tiles3[0][0] = 0;
        tiles3[0][1] = 1;
        tiles3[0][2] = 2;
        tiles3[1][0] = 3;
        tiles3[1][1] = 4;
        tiles3[1][2] = 5;
        tiles3[2][0] = 6;
        tiles3[2][1] = 7;
        tiles3[2][2] = 8;

        Board board3 = new Board(tiles3);

        System.out.println(board3.toString());
        System.out.println("Twin board 3");
        System.out.println(board3.twin().toString());
        System.out.println(board3.hamming());
        System.out.println(board3.manhattan());
        System.out.println(board3.isGoal());
        System.out.println();

        int n4 = 3;
        int[][] tiles4 = new int[n4][n4];

        tiles4[0][0] = 1;
        tiles4[0][1] = 2;
        tiles4[0][2] = 3;
        tiles4[1][0] = 4;
        tiles4[1][1] = 5;
        tiles4[1][2] = 6;
        tiles4[2][0] = 7;
        tiles4[2][1] = 8;
        tiles4[2][2] = 0;

        Board board4 = new Board(tiles4);

        System.out.println(board4.toString());
        System.out.println("Twin board 4");
        System.out.println(board4.twin().toString());
        System.out.println(board4.hamming());
        System.out.println(board4.manhattan());
        System.out.println(board4.isGoal());
        System.out.println();

        System.out.println(board4.equals(board));;

        board.neighbors();
        board2.neighbors();

        for (Board neighbors : board2.neighbors()) {
            System.out.println(neighbors);
        }


    }

}
