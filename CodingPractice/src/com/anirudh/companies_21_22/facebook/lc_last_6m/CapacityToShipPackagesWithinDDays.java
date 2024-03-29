package com.anirudh.companies_21_22.facebook.lc_last_6m;

/*
1011. Capacity To Ship Packages Within D Days
Medium

3624

81

Add to List

Share
A conveyor belt has packages that must be shipped from one port to another within days days.

The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights).
We may not load more weight than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.



Example 1:

Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15
Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
Example 2:

Input: weights = [3,2,2,4,1,4], days = 3
Output: 6
Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4
Example 3:

Input: weights = [1,2,3,1,1], days = 4
Output: 3
Explanation:
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1


Constraints:

1 <= days <= weights.length <= 5 * 104
1 <= weights[i] <= 500
Accepted
123,460
Submissions
196,289
 */

/*
Same as 410. Split Array Largest Sum

minimum largest sum = largest element E
maximum largest sum = sum of all elements S
Do binary search between lo = E and hi = S
To start with, ans = S as it is sum of 1 giant subarray
mid = (E+S)/2
make groups with sum <= mid
if num_groups > m => less groups required => each group's sum will increase => lo = mid+1, hi = S
if num_groups <= m => more groups required => each group's sum will decrease => lo = E, hi = mid - 1, record result as pot soln
Keep doing this until lo > hi
 */
public class CapacityToShipPackagesWithinDDays {
    int res;

    void binarySearch(int[] weights, int min, int max, int days) {

        while (min <= max) {
            int mid = min + (max - min) / 2; //weight per day
            int currDays = 0;
            int currSum = 0;
            for (int weight : weights) {
                currSum += weight;
                if (currSum > mid) {
                    currDays++;
                    currSum = weight;
                }
            }
            currDays++; //to close last subarray
            if (currDays > days) { //more days needed than supplied; need to decrease days, hence increase weight per day, increase min
                min = mid + 1;
            } else { //currDays <= days, potential solution
                res = Math.min(res, mid);
                max = mid - 1;
            }
        }
    }

    public int shipWithinDays(int[] weights, int days) {
        int min = Integer.MIN_VALUE;
        int max = 0;
        for (int weight : weights) {
            if (weight > min)
                min = weight;
            max += weight;
        }
        res = max;
        binarySearch(weights, min, max, days);
        return res;
    }
}
