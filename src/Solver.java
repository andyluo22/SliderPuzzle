import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solver {
    private int minMoves = 0;
    private List<Board> shortestSolution;
    private Board goalBoard;
    private boolean reachedGoal = false;
    private boolean reachedGoalTwin = false;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("Please input an nxn board that is not null");
        }
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        MinPQ<SearchNode> pqTwin = new MinPQ<SearchNode>();
        shortestSolution = new ArrayList<Board>();
        shortestSolution.add(initial);

        //create goalBoard
        int[][] goalArray = new int[initial.dimension()][initial.dimension()];
        goalArray = createGoalBoard(goalArray);
        goalBoard = new Board(goalArray);

        //create initial searchNode tree and add it into priority queue
        //Note:  The priority queue keeps track of smallest priority so we remove these and add the neighbours of the node into the pq
        SearchNode initNode = new SearchNode(null, initial ,minMoves);
        pq.insert(initNode);
        SearchNode initNodeTwin = new SearchNode(null,initial.twin(),minMoves);
        pqTwin.insert(initNodeTwin);

        while(reachedGoal || reachedGoalTwin) {

        }


    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        if(reachedGoal) {
            return true;
        }
        else {
            return false;
        }
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if(reachedGoal) {
            return minMoves;
        }
        else {
            return -1;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return this.shortestSolution;
    }

    private int[][] createGoalBoard(int[][] board) {
        int num = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
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
