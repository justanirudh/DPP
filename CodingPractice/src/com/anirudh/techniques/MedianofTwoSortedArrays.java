package com.anirudh.techniques;

/**
 * Created by paanir on 10/23/17.
 */
/*
4. Median of Two Sorted Arrays
There are two sorted arrays A and B of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
A = [1, 3]
B = [2]

The median is 2.0
Example 2:
A = [1, 2]
B = [3, 4]

The median is (2 + 3)/2 = 2.5
 */
//https://leetcode.com/articles/median-of-two-sorted-arrays/
public class MedianofTwoSortedArrays {


    public double findMedianSortedArrays(int[] A, int[] B) {
        //make sure A is the smaller array
        if (A.length > B.length) {
            int[] temp = A;
            A = B;
            B = temp;
        }
        int m = A.length;
        int n = B.length;

        /*Let i be the index of A and j be the index of B
         A: 1,2,....i-1 i,....m-1
         B: 1,2,....j-1 j,.....n-1
         Two conditions:
         1. i + j = (m-i) + (n-j) + 1, as both lengths needs to be equal, property of median (not sure how +1 came)
          => j = (m + n + 1)/2 - i
         2. A[i-1] <= B[j] && B[j-1] <= A[i]
        */

        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        //traversing over smaller array
        while (iMin <= iMax) {
            //fixing condition 1
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;

            //checking condition 2
            if (i < iMax && B[j - 1] > A[i]) {//i is small, increase i
                iMin = iMin + 1;
            } else if (i > iMin && A[i - 1] > B[j]) { //i is big, decrease it
                iMax = iMax - 1;
            } else { //i is perfect
                //result will be maxLeft if odd number of digits
                //else will be (maxLeft + minRight)/2
                int maxLeft;
                if (i == 0) // => A[i-1] does not exist, hence only consider j-1
                    maxLeft = B[j - 1];
                else if (j == 0)
                    maxLeft = A[i - 1];
                else
                    maxLeft = Math.max(A[i - 1], B[j - 1]);

                //if odd number of elems, return maxLeft
                if ((m + n) % 2 != 0)
                    return maxLeft;

                //get minRight
                int minRight;
                if (i == m) // => A[i] does not exist, only consider B[j    ]
                    minRight = B[j];
                else if (j == n)
                    minRight = A[i];
                else
                    minRight = Math.min(A[i], B[j]);


                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0;
    }

}
