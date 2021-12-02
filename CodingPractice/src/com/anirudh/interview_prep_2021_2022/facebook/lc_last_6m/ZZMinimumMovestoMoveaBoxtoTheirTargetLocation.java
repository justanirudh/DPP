package com.anirudh.interview_prep_2021_2022.facebook.lc_last_6m;

/**
 * Created by paanir on 8/29/21.
 */

/**
 * 1263. Minimum Moves to Move a Box to Their Target Location
 Hard

 432

 26

 Add to List

 Share
 A storekeeper is a game in which the player pushes boxes around in a warehouse trying to get them to target locations.

 The game is represented by an m x n grid of characters grid where each element is a wall, floor, or box.

 Your task is to move the box 'B' to the target position 'T' under the following rules:

 The character 'S' represents the player. The player can move up, down, left, right in grid if it is a floor (empty cell).
 The character '.' represents the floor which means a free cell to walk.
 The character '#' represents the wall which means an obstacle (impossible to walk there).
 There is only one box 'B' and one target cell 'T' in the grid.
 The box can be moved to an adjacent free cell by standing next to the box and then moving in the direction of the box. This is a push.
 The player cannot walk through the box.
 Return the minimum number of pushes to move the box to the target. If there is no way to reach the target, return -1.



 Example 1:


 Input: grid = [["#","#","#","#","#","#"],
 ["#","T","#","#","#","#"],
 ["#",".",".","B",".","#"],
 ["#",".","#","#",".","#"],
 ["#",".",".",".","S","#"],
 ["#","#","#","#","#","#"]]
 Output: 3
 Explanation: We return only the number of times the box is pushed.
 Example 2:

 Input: grid = [["#","#","#","#","#","#"],
 ["#","T","#","#","#","#"],
 ["#",".",".","B",".","#"],
 ["#","#","#","#",".","#"],
 ["#",".",".",".","S","#"],
 ["#","#","#","#","#","#"]]
 Output: -1
 Example 3:

 Input: grid = [["#","#","#","#","#","#"],
 ["#","T",".",".","#","#"],
 ["#",".","#","B",".","#"],
 ["#",".",".",".",".","#"],
 ["#",".",".",".","S","#"],
 ["#","#","#","#","#","#"]]
 Output: 5
 Explanation:  push the box down, left, left, up and up.
 Example 4:

 Input: grid = [["#","#","#","#","#","#","#"],
 ["#","S","#",".","B","T","#"],
 ["#","#","#","#","#","#","#"]]
 Output: -1
 */

//TODO: Implement
//https://leetcode.com/problems/minimum-moves-to-move-a-box-to-their-target-location/discuss/709355/Java-use-2-level-BFS-beat-99
public class ZZMinimumMovestoMoveaBoxtoTheirTargetLocation {
    //1 BFS for box to Target
    //1 BFS for Storekeeper to Box
}
