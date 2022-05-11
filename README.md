# SliderPuzzle 🧩
## The Problem
The slider puzzle is a game that is played on either a 3-by-3 grid or 4-by-4 grid, with 8 square tiles labelled 1 through 8 or 15 square tiles labelled 1 through 15 respectively.  The **objective** of the gain is to rearrange the initial set of tiles so that they are in row-major order - using as few moves as possible.  With some rules, the player can only move the tiles either **horizontally** or **vertically** into the blank square.  

![image](https://user-images.githubusercontent.com/68613171/167739024-e9274d26-ee81-4298-a36e-293ecbe33c63.png)

**Figure 1:** _Given an initial starting board on the left we want to achieve the goal board in row-major order on the right_

## Significance 
To solve the slider puzzle in an elegant way, an implementation of the **A* algorithm** - which is a general artifical intelligence methodology - is used to find the shortest path to the goal board. This implementation utilizes a priority queue and a minimum heap tree to keep track of which nodes in the tree lead to the shortest path to the goal board.  Lastly, the key reason why the **A* algorithm** is used instead of other graph traversal algorithms is that the tree of game boards that are processed aren't pre-processed before hand.  One major drawback of using the A* algorithm is the space complexity.  However, before we approach memory bounded inputs of these puzzles, **A*** is the best solution in many cases in terms of time complexity and efficiency.    

#### Heuristic Function A* Search



#### Detecting Unsolvable Boards


#### Optimization of Space Complecity and Time Complexity 
