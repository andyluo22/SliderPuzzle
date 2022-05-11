# SliderPuzzle 🧩
## The Problem
The slider puzzle is a game that is played on either a 3-by-3 grid or 4-by-4 grid, with 8 square tiles labelled 1 through 8 or 15 square tiles labelled 1 through 15 respectively.  The **objective** of the game is to rearrange the initial set of tiles so that they are in row-major order - using as few moves as possible.  With some rules, the player can only move the tiles either **horizontally** or **vertically** into the blank square.  

![image](https://user-images.githubusercontent.com/68613171/167739024-e9274d26-ee81-4298-a36e-293ecbe33c63.png)

**Figure 1:** _Given an initial starting board on the left we want to achieve the goal board in row-major order on the right_

## Significance 
To solve the slider puzzle in an elegant way, an implementation of the **A* algorithm** - which is a general artifical intelligence methodology - is used to find the shortest path to the goal board. This implementation utilizes a priority queue and a minimum heap tree to keep track of which nodes in the tree lead to the shortest path to the goal board.  Lastly, the key reason why the **A* algorithm** is used instead of other graph traversal algorithms is that the tree of game boards that are processed aren't pre-processed before hand.  One major drawback of using the A* algorithm is the space complexity.  However, before we approach memory bounded inputs of these puzzles, **A*** is the best solution in many cases based on time complexity and efficiency.    

#### Heuristic Function A* Search

A **heuristic** algorithm is a shortcut to solve problems that contain multiple paths. In mathematics, this is often denoted as the functions: _f(n) = g(n) + h(n)_ where _f(n)_ is the **heuristic**, _g(n)_ is the **cost** or amount of moves that we've taken to reach the board we are analyzing from the inital board, and _h(n)_ which is another defined cost that depends on the node itself.  In this case, the **heuristic cost** is computed as the **manhattan function** which is the total sum of how many steps it takes for each position in the grid to move to its correct positions.  Since the tree traversal done to find the goal board looks at each possible way the board being analyzed can move (up, down, left, right), a heuristic function is introduced to greatly reduce the chances the algorithm considers a path that does not lead to the shortest path.


#### Detecting Unsolvable Boards


#### Optimization of Space Complecity and Time Complexity 


#### Appendix of Terms: 
