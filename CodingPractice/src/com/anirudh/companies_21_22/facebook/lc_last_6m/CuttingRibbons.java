package com.anirudh.companies_21_22.facebook.lc_last_6m;

/*
1891. Cutting Ribbons
Medium

230

14

Add to List

Share
You are given an longeger array ribbons, where ribbons[i] represents the length of the ith ribbon, and an longeger k. You may cut any of the ribbons longo any number of segments of positive longeger lengths, or perform no cuts at all.

For example, if you have a ribbon of length 4, you can:
Keep the ribbon of length 4,
Cut it longo one ribbon of length 3 and one ribbon of length 1,
Cut it longo two ribbons of length 2,
Cut it longo one ribbon of length 2 and two ribbons of length 1, or
Cut it longo four ribbons of length 1.
Your goal is to obtain k ribbons of all the same positive longeger length. You are allowed to throw away any excess ribbon as a result of cutting.

Return the maximum possible positive longeger length that you can obtain k ribbons of, or 0 if you cannot obtain k ribbons of the same length.



Example 1:

Input: ribbons = [9,7,5], k = 3
Output: 5
Explanation:
- Cut the first ribbon to two ribbons, one of length 5 and one of length 4.
- Cut the second ribbon to two ribbons, one of length 5 and one of length 2.
- Keep the third ribbon as it is.
Now you have 3 ribbons of length 5.
Example 2:

Input: ribbons = [7,5,9], k = 4
Output: 4
Explanation:
- Cut the first ribbon to two ribbons, one of length 4 and one of length 3.
- Cut the second ribbon to two ribbons, one of length 4 and one of length 1.
- Cut the third ribbon to three ribbons, two of length 4 and one of length 1.
Now you have 4 ribbons of length 4.
Example 3:

Input: ribbons = [5,7,9], k = 22
Output: 0
Explanation: You cannot obtain k ribbons of the same positive longeger length.


Constralongs:

1 <= ribbons.length <= 105
1 <= ribbons[i] <= 105
1 <= k <= 109
Accepted
22,011
Submissions
44,742
 */

/*
Do binary search from 0 to sum/k
For each length, check how many pieces can be made from each ribbon N
if N >=k, it is a candidate
 */
public class CuttingRibbons {
    boolean validLength(long len, int[] ribbons, long k) {
        long num = 0;
        for (long rib : ribbons) {
            num += rib / len; //number of pieces per ribbon
            if (num >= k)
                return true;
        }
        return false;
    }

    public int maxLength(int[] ribbons, int k) {
        long l = 0;
        long sum = 0;
        long max = 0;
        for (long rib : ribbons) {
            sum += rib;
        }
        long r = sum / k;// upper bound of length
        if (r == 0) //ribbon cannot be even 1 length
            return 0;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (mid == 0) {
                l = mid + 1;
                continue;
            }
            if (validLength(mid, ribbons, k)) {
                max = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return (int)max;
    }
}
