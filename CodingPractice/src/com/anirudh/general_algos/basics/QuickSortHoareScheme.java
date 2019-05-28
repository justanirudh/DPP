package com.anirudh.general_algos.basics;

/**
 * Created by paanir on 9/13/17.
 */

import java.lang.*;
import java.util.Scanner;

//http://www.geeksforgeeks.org/quick-sort/

//Hoare partition
//MORE INTUITIVE btu a lot of edge cases
//Mind the < and <=.
class QuickSortHoareScheme {

    public static void swap(Integer[] nums, int i1, int i2) {
        int temp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = temp;
    }


    public static int partition(Integer[] nums, int start, int end) {
        //always picking first elem to be pivot element.
        int pivotElem = nums[start];
        int left = start; //large elems
        int right = end; //small elems
        while (true) {
            while (nums[left] < pivotElem) //IMP: this should be < and NOT <=.
                left++; //As it ALWAYS stops at least at the pivot, not need to check if reached end of array
            while (nums[right] > pivotElem)
                right--;
            if (left >= right)
                return right; //because right goes with the left range of next recursion (quickSort(nums, start, "pivot");)
            swap(nums, left, right);
            left++;
            right--;
        }

    }

    public static void quickSort(Integer[] nums, int start, int end) {
        if (nums == null || nums.length == 0)
            return;
        if (start < end) {
            int pivot = partition(nums, start, end);
            quickSort(nums, start, pivot); //IMP: pivot, not pivot - 1
            quickSort(nums, pivot + 1, end);
        }
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int num_tests = Integer.parseInt(stdin.nextLine());
        while (num_tests != 0) {
            String[] strings = stdin.nextLine().split(" ");
            Integer[] nums = new Integer[strings.length];
            for (int i = 0; i < strings.length; ++i) {
                nums[i] = Integer.parseInt(strings[i]);
            }
            quickSort(nums, 0, nums.length - 1);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < nums.length; ++i) {
                builder.append(nums[i]);
                if (i != nums.length - 1)
                    builder.append(" ");
            }
            System.out.println(builder.toString());
            num_tests--;
        }


    }
}
