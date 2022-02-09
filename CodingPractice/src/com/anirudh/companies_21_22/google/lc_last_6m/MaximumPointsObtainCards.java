package com.anirudh.companies_21_22.google.lc_last_6m;

/**
 * Created by paanir on 9/3/21.
 */
/*
1423. Maximum Points You Can Obtain from Cards
Medium

2079

89

Add to List

Share
There are several cards arranged in a row, and each card has an associated number of points.
The points are given in the integer array cardPoints.

In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.

Your score is the sum of the points of the cards you have taken.

Given the integer array cardPoints and the integer k, return the maximum score you can obtain.



Example 1:

Input: cardPoints = [1,2,3,4,5,6,1], k = 3
Output: 12
Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first
will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
Example 2:

Input: cardPoints = [2,2,2], k = 2
Output: 4
Explanation: Regardless of which two cards you take, your score will always be 4.
Example 3:

Input: cardPoints = [9,7,7,9,7,7,9], k = 7
Output: 55
Explanation: You have to take all the cards. Your score is the sum of points of all cards.
Example 4:

Input: cardPoints = [1,1000,1], k = 1
Output: 1
Explanation: You cannot take the card in the middle. Your best score is 1.
Example 5:

Input: cardPoints = [1,79,80,1,1,1,200,1], k = 3
Output: 202
 */

/*
- we have to maximize sum of k cards from the corners
- which means we have to minimize the rest of the n-k array
- use SlidingWindow approach to get the contiguous window of size (length - k) with the MINIMUM sum of points
- means rest of the array, from either side of size k,  will have maximum sum of points!
 */
public class MaximumPointsObtainCards {

    public int maxScore(int[] cardPoints, int k) {
        int totalSum = 0;
        for (int p : cardPoints) {
            totalSum += p;
        }
        if (cardPoints.length == k)
            return totalSum;

        int windowLen = cardPoints.length - k; //find min in this window

        int slow = 0;
        int fast = 0;
        int currSum = 0;
        while (fast < cardPoints.length && fast + 1 <= windowLen) { //create first window
            currSum += cardPoints[fast];
            fast++;
        }
        int minSum = currSum; //fast is outside window, slow is inside window
        while (fast < cardPoints.length) {
            currSum = currSum + cardPoints[fast] - cardPoints[slow];
            minSum = Math.min(minSum, currSum);
            slow++;
            fast++;
        }
        return totalSum - minSum;
    }
}
