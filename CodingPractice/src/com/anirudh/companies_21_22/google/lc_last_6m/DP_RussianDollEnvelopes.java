package com.anirudh.companies_21_22.google.lc_last_6m;

/*
354. Russian Doll Envelopes
Hard

2720

66

Add to List

Share
You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.

One envelope can fit into another if and only if both the width and height of one envelope are greater than the other envelope's width and height.

Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).

Note: You cannot rotate an envelope.



Example 1:

Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
Output: 3
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
Example 2:

Input: envelopes = [[1,1],[1,1],[1,1]]
Output: 1


Constraints:

1 <= envelopes.length <= 5000
envelopes[i].length == 2
1 <= wi, hi <= 104
 */

import java.util.Arrays;
import java.util.Comparator;

/*
sort increasing order by width
if width is equal, sort decreasing order by height so that they dont become part of a valid subsequence
after doing this, find Longest Increasing Subsequence
 */
public class DP_RussianDollEnvelopes {

    class CompareElems implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            if (a[0] != b[0])
                return a[0] - b[0]; //sort by width
            else
                return b[1] - a[1]; //reverse sort by height
        }
    }

    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new CompareElems());
        int[] heights = new int[envelopes.length];
        for (int i = 0; i < heights.length; ++i) {
            heights[i] = envelopes[i][1];
        }
        return new DP_LongestIncreasingSubsequence2().lengthOfLIS(heights);
    }
}
