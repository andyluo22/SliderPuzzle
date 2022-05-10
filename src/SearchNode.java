public class SearchNode implements Comparable<SearchNode> {
    private SearchNode parent;
    private Board board;
    private int numMoves;
    private int manhattan;

    public SearchNode(SearchNode parent, Board board, int numMoves) {
        this.board = board;
        this.parent = parent;
        this.numMoves = numMoves;
        this.manhattan = board.manhattan();
    }

    public SearchNode getParent() {
        return parent;
    }

    public int getManhattan() {
        return manhattan;
    }

    public void setManhattan(int manhattan) {
        this.manhattan = manhattan;
    }

    public void setParent(SearchNode parent) {
        this.parent = parent;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getNumMoves() {
        return numMoves;
    }

    public void setNumMoves(int numMoves) {
        this.numMoves = numMoves;
    }

    @Override
    public int compareTo(SearchNode o) {
        int priority = this.manhattan - o.manhattan + this.numMoves - o.numMoves;

        if(priority == 0) {
            return this.manhattan - o.manhattan;
        }
        else {
            return priority;
        }
    }


}
