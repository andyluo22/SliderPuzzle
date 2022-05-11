# SliderPuzzle 🧩
## The Problem
The slider puzzle is a game that is played on either a 3-by-3 grid or 4-by-4 grid, with 8 square tiles labelled 1 through 8 or 15 square tiles labelled 1 through 15 respectively.  The **objective** of the game is to rearrange the initial set of tiles so that they are in row-major order - using as few moves as possible.  With some rules, the player can only move the tiles either **horizontally** or **vertically** into the blank square.  

![image](https://user-images.githubusercontent.com/68613171/167739024-e9274d26-ee81-4298-a36e-293ecbe33c63.png)

**Figure 1:** _Given an initial starting board on the left we want to achieve the goal board in row-major order on the right_

## Significance 
To solve the slider puzzle in an elegant way, an implementation of the **A* algorithm** - which is a general artifical intelligence methodology - is used to find the shortest path to the goal board. This implementation utilizes a priority queue and a minimum heap tree to keep track of which nodes in the tree lead to the shortest path to the goal board.  Lastly, the key reason why the **A* algorithm** is used instead of other graph traversal algorithms is that the tree of game boards that are processed aren't pre-processed before hand.  One major drawback of using the A* algorithm is the space complexity.  However, before we approach memory bounded inputs of these puzzles, **A*** is the best solution in many cases based on time complexity and efficiency.    

#### Heuristic Function A* Search

A **heuristic** algorithm is a shortcut to solve problems that contain multiple paths. In mathematics, this is often denoted as the functions: _f(n) = g(n) + h(n)_ where _f(n)_ is the **heuristic**, _g(n)_ is the **cost** or amount of moves that we've taken to reach the board we are analyzing from the inital board, and _h(n)_ which is another defined cost that depends on the node itself.  In this case, the **heuristic cost** is computed as the **manhattan function** which is the total sum of how many steps it takes for each position in the grid to move to its correct positions.  Since the tree traversal done to find the goal board looks at each possible way the board being analyzed can move (up, down, left, right), a heuristic function is introduced to greatly reduce the chances the algorithm considers a path that does not lead to the shortest one.


#### Detecting Unsolvable Boards
In order to detect unsolvable boards, we consider the equivalence classes of a board.  An equivalence class in mathematical terms describes a specific group or subset of the board and by encompassing all equivalence classes we span all the possibilities of whether or not the board is solvable or not.  In this case, the equivalence classes of a board in terms of solvability can be grouped into 

_1._ boards that can be solved in row-major order and,

_2._ boards that cannot be solved in row-major order.  

By introducing more rigorous proofs on solving these boards, we find a very important fact that can be applied to detecting these unsolvable boards.  The proof is that if a board is unsolvable then its twin board is solvable and if a board is solvable then its twin board cannot be solved.  The twin board is simply just any board that is created from the initial board by swapping any pairs of tiles that aren't the empty tile.  This is a very important step in writing the _**A* algorithm**__ in a timely and spacely manner.

#### Optimization of Space Complecity and Time Complexity 

Since the **A* Algorithm** and pathfinding algorithms inevitably must consider different paths, this takes up a lot of time and heap storage space.  To improve the efficiency of the algorithm, two critical optimizations were considered:

_1._ Never consider search nodes that are the same as the parent nodes parent, or simply the grandparent of the current node as this leads to redudancy in path computation

_2._ Cache the Manhattan priorites of each search node when constructing that search node - simply instantiate and pre-compute the Manhattan priorty value before hand so that the priority queue does not need to compute these values from scratch.

## The A* Algorithm Implementation


![image](https://user-images.githubusercontent.com/68613171/167746754-a13bae2e-4312-4830-a712-853cc950d382.png)


#### Appendix of Terms: 
