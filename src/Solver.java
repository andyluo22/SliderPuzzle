import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solver {
    private int minMoves;
    private List<Board> shortestSolution;
    private Board goalBoard;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("Please input an nxn board that is not null");
        }
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        shortestSolution = new ArrayList<Board>();
        shortestSolution.add(initial);

        //create goalBoard
        int[][] goalArray = new int[initial.dimension()][initial.dimension()];
        goalArray = createGoalBoard(goalArray);
        goalBoard = new Board(goalArray);

        //create initial searchNode and add it into priority queue
        SearchNode initNode = new SearchNode(null, initial ,minMoves);
        pq.insert(initNode);


    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return false;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return 0;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return this.shortestSolution;
    }

    private int[][] createGoalBoard(int[][] board) {
        int num = 1;
        for (int i; i < board.length; i++) {
            for (int j; j < board.length; j++) {
                board[i][j] = num;
                num++;
            }
        }

        board[board.length][board.length] = 0;

        return board;
    }


    // test client (see below)
    public static void main(String[] args) {

    }

}
