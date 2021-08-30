package com.anirudh.interview_prep_2021.facebook.lc_last_6m;

/**
 * Created by paanir on 8/27/21.
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
 A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.

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

/**
 * Put first array in hashmap of indices to value, for non-zero values only
 * Then iterate through the other array. Update the map if index from 2nd array exists as key in HashMap
 */
public class DotProductofTwoSparseVectors {
    //TODO: Implement

}
