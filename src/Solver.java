import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solver {
    private int minMoves = 0;
    private boolean reachedGoal = false;
    private boolean reachedGoalTwin = false;
    private SearchNode leafNode;
    private List<Board> shortestSolution;
    private int totalMoves = 0;

    public Solver(Board initial) {
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        MinPQ<SearchNode> pqTwin = new MinPQ<SearchNode>();
        shortestSolution = new ArrayList<Board>();
        Board prev = null;

        if (initial == null) {
            throw new IllegalArgumentException("Please input an nxn board that is not null");
        }
        if (initial.isGoal()) {
            reachedGoal = true;
            shortestSolution.add(initial);
        }

        SearchNode initNode = new SearchNode(null, initial, 0);
        pq.insert(initNode);
        SearchNode initNodeTwin = new SearchNode(null, initial.twin(), 0);
        pqTwin.insert(initNodeTwin);

        SearchNode min;
        SearchNode minTwin;
        while (!reachedGoal && !reachedGoalTwin) {
            min = pq.delMin();
            minTwin = pqTwin.delMin();
            minMoves++;

            for (Board neighbor : min.getBoard().neighbors()) {
                if (minMoves!= 1) {
                    prev = min.getParent().getBoard();
                }
                if(neighbor.isGoal()) {
                    leafNode = new SearchNode(min,neighbor,min.getNumMoves() + 1);
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
                    reachedGoalTwin = true;
                    break;
                }
                else if(!neighbor.equals(prev) || minMoves == 1) {
                    SearchNode neighborNode = new SearchNode(minTwin, neighbor, min.getNumMoves() + 1);
                    pqTwin.insert(neighborNode);
                }
            }
        }

        totalMoves = minMoves;

        if(reachedGoal && !initial.isGoal()) {
            minMoves = 0;

            //upwards traversal from leaf
            while(leafNode.getBoard() != initial) {
                shortestSolution.add(leafNode.getBoard());
                leafNode = leafNode.getParent();
                minMoves++;
            }

            Collections.reverse(shortestSolution);
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
            return this.shortestSolution;
        }
        else {
            return null;
        }
    }


    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In("puzzle4x4-48.txt");
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