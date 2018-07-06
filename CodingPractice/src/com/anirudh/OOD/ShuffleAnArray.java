package com.anirudh.OOD;

/**
 * Created by paanir on 4/17/17.
 */
/*
384. Shuffle an Array
Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
SuperUglyNumbers solution = new SuperUglyNumbers(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();
 */
import java.util.Random;

public class ShuffleAnArray {

    int[] nums;
    Random r;

    private void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public ShuffleAnArray(int[] nums) {
        this.nums = nums; //dont touch original array
        r = new Random();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return this.nums;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() { //can also directly use Collections.shuffle. This one's smarter
        int[] newArr = this.nums.clone();
        for (int right = 1; right < newArr.length; ++right) {// right is RHS border, left can be b/w 0 and right-inclusive
            int left = r.nextInt(right + 1); //get random number between [0, right+1) = [0, right]
            swap(newArr, left, right);
        }
        return newArr;
    }

}
