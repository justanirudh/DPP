package com.anirudh.companies_21_22.facebook.lc_last_6m;

/*
1539. Kth Missing Positive Number
Easy

2022

145

Add to List

Share
Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.

Find the kth positive integer that is missing from this array.



Example 1:

Input: arr = [2,3,4,7,11], k = 5
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
Example 2:

Input: arr = [1,2,3,4], k = 2
Output: 6
Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.


Constraints:

1 <= arr.length <= 1000
1 <= arr[i] <= 1000
1 <= k <= 1000
arr[i] < arr[j] for 1 <= i < j <= arr.length
Accepted
139,481
Submissions
252,724
 */
/*
Option 1:
    Eg.: [2, 3, 4, 7, 11]
    Idx: [0, 1, 2, 3, 4 ]
    At an index i, num_elems_missing_before_i is arr[i] - i - 1
    Eg: before 7, 7-3-1 = 3 elems missing: 1, 5, 6

    Do binary search: O(log n)


Option 2:
Compare each pair of elems and change k based on whether the diff is <=> k
O(n)
 */
public class KthMissingPositiveNumber {

    public int findKthPositive(int[] arr, int k) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] - mid - 1 < k) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        // newl = r + 1 now
        // kth num between r and newl
        //arr[r] + elems missing after r == arr[r] + (k - elems missing before r)
        //arr[r] + (k - (arr[r] - r - 1)) == k + (r + 1) = k + newl
        return k + l;

    }

    public int findKthPositiveSlow(int[] arr, int k) {
        int prev = 0;
        for (int j : arr) {
            int diff = j - prev - 1;
            if (diff < k) {
                k = k - diff;
            } else {
                return prev + k;
            }
            prev = j;
        }
        return prev + k;
    }
}
