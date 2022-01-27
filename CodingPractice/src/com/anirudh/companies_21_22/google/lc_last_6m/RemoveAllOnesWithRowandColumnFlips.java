package com.anirudh.companies_21_22.google.lc_last_6m;

import java.util.HashSet;
import java.util.Set;

/*
2128. Remove All Ones With Row and Column Flips
Medium

39

8

Add to List

Share
You are given an m x n binary matrix grid.

In one operation, you can choose any row or column and flip each value in that row or column (i.e., changing all 0's to 1's, and all 1's to 0's).

Return true if it is possible to remove all 1's from grid using any number of operations or false otherwise.



Example 1:


Input: grid = [[0,1,0],[1,0,1],[0,1,0]]
Output: true
Explanation: One possible way to remove all 1's from grid is to:
- Flip the middle row
- Flip the middle column
Example 2:


Input: grid = [[1,1,0],[0,0,0],[0,0,0]]
Output: false
Explanation: It is impossible to remove all 1's from grid.
Example 3:


Input: grid = [[0]]
Output: true
Explanation: There are no 1's in grid.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is either 0 or 1.
Accepted
1,129
Submissions
1,424
 */
/*
All rows/columns should either be the same pattern string OR anti-pattern string
    000 and 000 are same patt
    101 and 010 are same patt
At the end there should only be max 2 patterns in the set
 */
public class RemoveAllOnesWithRowandColumnFlips {
    public boolean removeOnes(int[][] grid) {
        Set<String> patterns = new HashSet<>();
        //go through each row
        for (int[] ints : grid) {
            StringBuilder sb = new StringBuilder();
            StringBuilder antiSb = new StringBuilder();
            for (int j = 0; j < grid[0].length; ++j) {
                int elem = ints[j];
                sb.append(elem);
                antiSb.append(elem == 0 ? 1 : 0);
            }
            patterns.add(sb.toString());
            patterns.add(antiSb.toString());
            if(patterns.size() > 2)
                return false;
        }
        return true;
    }
}
