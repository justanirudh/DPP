package com.anirudh.general_algos;

/*
31. Next Permutation

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
1,2,5,3,1 -> 1,3,1,2,5
 */
public class NextPermutation {

    void reverse(int[] nums, int s, int e) {
        while (e > s) {
            int temp = nums[s];
            nums[s] = nums[e];
            nums[e] = temp;
            e--;
            s++;
        }
    }

    public void nextPermutation(int[] nums) {
        if (nums.length == 0 || nums.length == 1)
            return;
        int peak = nums.length - 1;
        for (; peak >= 0; peak--) { //find [..a,peek, ...] such that a < peek
            if (peak == 0 || nums[peak - 1] < nums[peak])
                break;
        }
        if (peak == 0) { //reverse arr
            reverse(nums, 0, nums.length - 1);
        } else { //swap the (peak-1)th elem with lowest of nums[peak to n-1] that is bigger than (peak-1)th.
            // Then sort nums[peak,n-1]

            //find that elem
            int more = nums.length - 1;
            while (nums[more] <= nums[peak - 1])
                more--;
            // swap it with peak - 1
            int temp = nums[peak - 1];
            nums[peak - 1] = nums[more];
            nums[more] = temp;
            //now just sort the already reverse sorted part (as peak to n-1 was reverse sorted)
            reverse(nums, peak, nums.length - 1);

        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,5,3,1};
        NextPermutation np = new NextPermutation();
        np.nextPermutation(nums);
        for(int num : nums){
            System.out.print(num + " ");
        }
    }
}