package com.anirudh.companies_21_22.facebook.lc_last_6m;

/**
 * Created by paanir on 8/27/21.
 * <p>
 * 1570. Dot Product of Two Sparse Vectors
 * Medium
 * <p>
 * 372
 * <p>
 * 52
 * <p>
 * Add to List
 * <p>
 * Share
 * Given two sparse vectors, compute their dot product.
 * <p>
 * Implement class SparseVector:
 * <p>
 * SparseVector(nums) Initializes the object with the vector nums
 * dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
 * A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and
 * compute the dot product between two SparseVector.
 * <p>
 * Follow up: What if only one of the vectors is sparse?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
 * Output: 8
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
 * Example 2:
 * <p>
 * Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
 * Output: 0
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
 * Example 3:
 * <p>
 * Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
 * Output: 6
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 0 <= nums1[i], nums2[i] <= 100
 * <p>
 * 1570. Dot Product of Two Sparse Vectors
 * Medium
 * <p>
 * 372
 * <p>
 * 52
 * <p>
 * Add to List
 * <p>
 * Share
 * Given two sparse vectors, compute their dot product.
 * <p>
 * Implement class SparseVector:
 * <p>
 * SparseVector(nums) Initializes the object with the vector nums
 * dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
 * A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and
 * compute the dot product between two SparseVector.
 * <p>
 * Follow up: What if only one of the vectors is sparse?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
 * Output: 8
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
 * Example 2:
 * <p>
 * Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
 * Output: 0
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
 * Example 3:
 * <p>
 * Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
 * Output: 6
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 0 <= nums1[i], nums2[i] <= 100
 */

/**
 * 1570. Dot Product of Two Sparse Vectors
 Medium

 372

 52

 Add to List

 Share
 Given two sparse vectors, compute their dot product.

 Implement class SparseVector:

 SparseVector(nums) Initializes the object with the vector nums
 dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
 A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and
 compute the dot product between two SparseVector.

 Follow up: What if only one of the vectors is sparse?



 Example 1:

 Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
 Output: 8
 Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
 Example 2:

 Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
 Output: 0
 Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
 Example 3:

 Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
 Output: 6


 Constraints:

 n == nums1.length == nums2.length
 1 <= n <= 10^5
 0 <= nums1[i], nums2[i] <= 100
 */

import java.util.HashMap;
import java.util.Map;

/*
Create Map{index -> value} for both
Find smaller of the 2 maps, iterate through that and check if the otehr map has the same idnex or not
 */
public class SparseVector {

    Map<Integer, Integer> vector;

    SparseVector(int[] nums) {
        vector = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                vector.put(i, nums[i]);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        Map<Integer, Integer> other = vec.vector;
        Map<Integer, Integer> smaller;
        Map<Integer, Integer> larger;
        if (vector.size() < other.size()) {
            smaller = vector;
            larger = other;
        } else {
            smaller = other;
            larger = vector;
        }
        int sum = 0;
        for (int index : smaller.keySet()) {
            if (larger.containsKey(index)) {
                sum += smaller.get(index) * larger.get(index);
            }
        }
        return sum;
    }

}
