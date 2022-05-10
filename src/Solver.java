import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solver {
    private int minMoves = 0;
    private List<Board> shortestSolution;
    private Board goalBoard;
    private boolean reachedGoal = false;
    private boolean reachedGoalTwin = false;
    private SearchNode min;
    private SearchNode minTwin;
    private SearchNode leafLast;
    private List<Board> ssReversed;
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        MinPQ<SearchNode> pqTwin = new MinPQ<SearchNode>();
        shortestSolution = new ArrayList<Board>();
        List shortestSolutionTwin = new ArrayList<Board>();
        Board prev = null;

        //create goalBoard
        int[][] goalArray = new int[initial.dimension()][initial.dimension()];
        goalArray = createGoalBoard(goalArray);
        goalBoard = new Board(goalArray);

        if (initial == null) {
            throw new IllegalArgumentException("Please input an nxn board that is not null");
        }
        if (initial.equals(goalBoard)) {
            reachedGoal = true;
        }
        //create initial searchNode tree and add it into priority queue
        //Note:  The priority queue keeps track of smallest priority so we remove these and add the neighbours of the node into the pq
        SearchNode initNode = new SearchNode(null, initial, 0);
        pq.insert(initNode);
        SearchNode initNodeTwin = new SearchNode(null, initial.twin(), 0);
        pqTwin.insert(initNodeTwin);

        while (!reachedGoal && !reachedGoalTwin) {
            min = pq.delMin();
            minTwin = pqTwin.delMin();
            minMoves++;
            shortestSolution.add(min.getBoard());
            shortestSolutionTwin.add(minTwin.getBoard());

            for (Board neighbor : min.getBoard().neighbors()) {
                if (minMoves!= 1) {
                    prev = min.getParent().getBoard();
                }
                if(neighbor.isGoal()) {
                    shortestSolution.add(neighbor);
                    leafLast = new SearchNode(min,neighbor,min.getNumMoves() + 1);
                    reachedGoal = true;
                    break;
                }
                else if(!neighbor.equals(prev) || minMoves == 1) {
                    SearchNode neighborNode = new SearchNode(min, neighbor, min.getNumMoves() + 1);
                    pq.insert(neighborNode);
                }
            }

            for (Board neighbor : minTwin.getBoard().neighbors()) {
                if (reachedGoal) {
                    break;
                }
                else if(minMoves!= 1) {
                    prev = minTwin.getParent().getBoard();
                }

                if(neighbor.isGoal()) {
                    shortestSolutionTwin.add(neighbor);
                    reachedGoalTwin = true;
                    break;
                }
                else if(!neighbor.equals(prev) || minMoves == 1) {
                    SearchNode neighborNode = new SearchNode(minTwin, neighbor, min.getNumMoves() + 1);
                    pqTwin.insert(neighborNode);
                }
            }
        }

        if(reachedGoal && !initial.isGoal()) {
            minMoves = 0;
            ssReversed = new ArrayList<Board>();

            //upwards traversal
            while(leafLast.getBoard() != initial) {
                ssReversed.add(leafLast.getBoard());
                leafLast = leafLast.getParent();
                minMoves++;
            }

            Collections.reverse(ssReversed);
        }

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        if (reachedGoal) {
            return true;
        } else {
            return false;
        }
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (reachedGoal) {
            return minMoves;
        } else {
            return -1;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (reachedGoal) {
            return this.ssReversed;
        }
        else {
            return null;
        }
    }

    private int[][] createGoalBoard(int[][] board) {
        int num = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = num;
                num++;
            }
        }

        board[board.length - 1][board.length - 1] = 0;

        return board;
    }


    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In("puzzle3x3-solved.txt");
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }


}
