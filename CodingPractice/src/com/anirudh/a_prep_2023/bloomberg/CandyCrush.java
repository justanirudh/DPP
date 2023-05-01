package com.anirudh.a_prep_2023.bloomberg;

import java.util.ArrayList;
import java.util.List;

/*
723. Candy Crush
Medium
919
460
company
Bloomberg
company
Capital One
company
Facebook
This question is about implementing a basic elimination algorithm for Candy Crush.

Given an m x n integer array board representing the grid of candy where board[i][j] represents the type of candy. A value of board[i][j] == 0 represents that the cell is empty.

The given board represents the state of the game following the player's move. Now, you need to restore the board to a stable state by crushing candies according to the following rules:

If three or more candies of the same type are adjacent vertically or horizontally, crush them all at the same time - these positions become empty.
After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these candies will drop until they hit a candy or bottom at the same time. No new candies will drop outside the top boundary.
After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
If there does not exist more candies that can be crushed (i.e., the board is stable), then return the current board.
You need to perform the above rules until the board becomes stable, then return the stable board.



Example 1:


Input: board = [[110,5,112,113,114],[210,211,5,213,214],[310,311,3,313,314],[410,411,412,5,414],[5,1,512,3,3],[610,4,1,613,614],[710,1,2,713,714],[810,1,2,1,1],[1,1,2,2,2],[4,1,4,4,1014]]
Output: [[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[110,0,0,0,114],[210,0,0,0,214],[310,0,0,113,314],[410,0,0,213,414],[610,211,112,313,614],[710,311,412,613,714],[810,411,512,713,1014]]
Example 2:

Input: board = [[1,3,5,5,2],[3,4,3,3,1],[3,2,4,5,2],[2,4,4,5,5],[1,4,4,1,1]]
Output: [[1,3,0,0,0],[3,4,0,5,2],[3,2,0,3,1],[2,4,0,5,2],[1,4,3,1,1]]


Constraints:

m == board.length
n == board[i].length
3 <= m, n <= 50
1 <= board[i][j] <= 2000
 */

/*
IMP!
TODO: Only partial test cases pass
For crushing:
use 2 pointer approach:
go row by row, find >=3 consecutive elems, convert to -1; Skip 0s as they maybe from previous round
go col by col, find >=3 consecutive elems, convert to -1; Skip 0s as they maybe from previou round

For dropping
traverse cols bottom-up
use 2-pointer approach
slow = first (-1)
fast = first non (-1)
copy from fast to slow
put 0s in remaining

Keep boolean tracking if board was changed
if it was, do recursion again else return board

Sx: O(1) as in-place
Tx: ((rc)^2): In each round we traverse rc
and how many rounds total: at max rc
so rc * rc

 */
public class CandyCrush {
    public int[][] candyCrush(int[][] board) {
        // crush candies

        int nr = board.length;
        int nc = board[0].length;
        boolean hadChanges;

        //2 pointer approach to mark >=3 conseq elems
        //mark them all first, then remove together
        List<int[]> tbr = new ArrayList<>();

        //mark row by row
        for (int i = 0; i < nr; ++i) {
            int fast = 0;
            while (fast < nc) {
                int first = board[i][fast];
                if (first == 0) {
                    fast++;
                    continue; //so that next recursion of board does not consider 0 for consecutive sequence
                }
                int slow = fast; //resets slow
                int count = 0;
                while (fast < nc && board[i][fast] == first) {
                    count++;
                    fast++;
                }
                if (count >= 3) {
                    while (slow != fast) {
                        tbr.add(new int[]{i, slow});
                        slow++;
                    }
                }
            }
        }

        //mark col by col
        for (int i = 0; i < nc; ++i) {
            int fast = 0;
            while (fast < nr) {
                int first = board[fast][i];
                if (first == 0) {
                    fast++;
                    continue; //so that next recursion of board does not consider 0 for consecutive sequence
                }
                int slow = fast; //resets slow
                int count = 0;
                while (fast < nr && board[fast][i] == first) {
                    count++;
                    fast++;
                }
                if (count >= 3) {
                    while (slow != fast) {
                        tbr.add(new int[]{slow, i});
                        slow++;
                    }
                }
            }
        }
        //sweep
        for (int[] elem : tbr) {
            board[elem[0]][elem[1]] = -1;
        }

        hadChanges = !tbr.isEmpty();
        if (!hadChanges)
            return board;

        // drop candies
        for (int i = 0; i < nc; ++i) {
            int slow = nr - 1;
            for (int fast = nr - 1; fast >= 0; --fast) {
                if (board[fast][i] > 0) { //if negative, dont decrement slow it will be sentinel for neg values
                    board[slow][i] = board[fast][i];
                    slow--;
                }
            }
            while (slow >= 0) {
                board[slow][i] = 0;
                slow--;
            }
        }
        return candyCrush(board);
    }
}
