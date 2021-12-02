package com.anirudh.interview_prep_2021_2022.two_sigma;

/*
486. Predict the Winner
Medium

2345

126

Add to List

Share
You are given an integer array nums. Two players are playing a game with this array: player 1 and player 2.

Player 1 and player 2 take turns, with player 1 starting first. Both players start the game with a score of 0. At each turn, the player takes one of the numbers from either end of the array (i.e., nums[0] or nums[nums.length - 1]) which reduces the size of the array by 1. The player adds the chosen number to their score. The game ends when there are no more elements in the array.

Return true if Player 1 can win the game. If the scores of both players are equal, then player 1 is still the winner, and you should also return true. You may assume that both players are playing optimally.



Example 1:

Input: nums = [1,5,2]
Output: false
Explanation: Initially, player 1 can choose between 1 and 2.
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2).
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.
Hence, player 1 will never be the winner and you need to return false.
Example 2:

Input: nums = [1,5,233,7]
Output: true
Explanation: Player 1 first chooses 1. Then player 2 has to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
 */

/*
dp[i][j] = Score(player 1) - Score of player(2) when the [i..j] subarray is being considered
Eg. for [3], dp[0][0] = 3, as 3 picked by player 1
return dp[i][j] >= 0

dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j-1])
Start from bottom-right, get s <>e columns in each row

Do in 1D array
 */
public class DP_PredictWinner {
    public boolean PredictTheWinner(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        for (int s = nums.length - 1; s >= 0; --s) {
            dp[s][s] = nums[s];
            for (int e = s + 1; e < nums.length; ++e) {
                int pickFromStart = nums[s] - dp[s + 1][e]; // s picked by player 1, player 2 picks the next
                int pickFromEnd = nums[e] - dp[s][e - 1];
                dp[s][e] = Math.max(pickFromStart, pickFromEnd);
            }
        }
        return dp[0][nums.length - 1] >= 0;
    }

    public boolean PredictTheWinner1D(int[] nums) {
        int[] dp = new int[nums.length];
        for (int s = nums.length - 1; s >= 0; s--) {
            dp[s] = nums[s];
            for (int e = s + 1; e < nums.length; e++) {
                int pickFromStart = nums[s] - dp[e];
                int pickFromEnd = nums[e] - dp[e - 1];
                dp[e] = Math.max(pickFromStart, pickFromEnd);
            }
        }
        return dp[nums.length - 1] >= 0;
    }
}
