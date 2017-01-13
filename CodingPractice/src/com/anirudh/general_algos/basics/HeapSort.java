package com.anirudh.general_algos.basics;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by paanir on 1/11/17.
 */
public class HeapSort {

    //parent at floor((j-1)/2)
    //left at 2j + 1
    //right at 2j + 2

    private static int getLeft(int i) {
        return 2 * i + 1;
    }

    private static int getRight(int i) {
        return 2 * i + 2;
    }

    private static int getParent(int i) {
        return (i - 1) / 2;
    }

    private static int[] maxHeapify(int[] nums, int index, int start, int end) {
        int left = getLeft(index);
        int right = getRight(index);
        int numsLen = end - start + 1;
        int largest = index;
        if (left < numsLen && nums[left] > nums[largest])
            largest = left;
        if (right < numsLen && nums[right] > nums[largest])
            largest = right;
        if (largest != index) {
            int temp = nums[index];
            nums[index] = nums[largest];
            nums[largest] = temp;
            maxHeapify(nums, largest, start, end);
        }
        return nums;
    }

    private static int[] buildMaxHeap(int[] nums) { //from mid to start
        for (int i = nums.length / 2; i >= 0; --i)
            nums = maxHeapify(nums, i, 0, nums.length - 1);
        return nums;
    }

    private static int[] heapSort(int[] nums) {
        int[] maxHeapifiedNums = buildMaxHeap(nums);
        for (int i = maxHeapifiedNums.length - 1; i >= 1; --i) {
            int temp = maxHeapifiedNums[0];
            maxHeapifiedNums[0] = maxHeapifiedNums[i];
            maxHeapifiedNums[i] = temp;
            maxHeapifiedNums = maxHeapify(maxHeapifiedNums, 0, 0, i - 1);
        }
        return maxHeapifiedNums;
    }

    public static void main(String[] args) {
        int[] nums = {5, 4, 3, 2, 1};
        //heapsort: build maxheap, swap elems, do Max heapify on the top elem

        int[] sortedNums = heapSort(nums);

        for (int i : sortedNums) {
            System.out.print(i + ",");
        }

    }
}
