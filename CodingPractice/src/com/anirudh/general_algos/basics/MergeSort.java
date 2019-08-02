package com.anirudh.general_algos.basics;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by paanir on 9/19/17.
 */
//https://www.hackerrank.com/contests/hw1/challenges/merge-sort

/**
 * Space complexity: O(n)
 * Time complexity = O(nlogn)
 */
public class MergeSort {

    public static void merge(int[] arr, int start, int mid, int end) {

        //first copy to two temp arrays. Then use them to OVERWRITE on the original array

        // indices like this [start, mid] [mid + 1, end]
        int lLen = mid - start + 1;
        int rLen = end - (mid + 1) + 1;
        int[] leftArr = new int[lLen];
        int[] rightArr = new int[rLen];

        //make temp arrays
        System.arraycopy(arr, start, leftArr, 0, lLen); //src, srcPos, dest, destPos, len
//        leftArr = Arrays.copyOfRange(arr, start, mid + 1);

        System.arraycopy(arr, mid + 1, rightArr, 0, rLen);
//        rightArr = Arrays.copyOfRange(arr, mid, end + 1);

        //merge into original array with rightArr order
        int i = 0, j = 0, k = start;
        while (i < lLen || j < rLen) {
            if (i >= lLen && j < rLen) { //left arr reached end first
                arr[k] = rightArr[j];
                j++;
            } else if (i < lLen && j >= rLen) { //right arr reached end first
                arr[k] = leftArr[i];
                i++;
            } else { //both < length
                if (leftArr[i] < rightArr[j]) {
                    arr[k] = leftArr[i];
                    i++;
                } else if (leftArr[i] > rightArr[j]) {
                    arr[k] = rightArr[j];
                    j++;
                } else { //both equal to each other
                    arr[k] = rightArr[j];
                    k++;
                    arr[k] = leftArr[i];
                    i++;
                    j++;
                }
            }
            k++; //increment result arrs position
        }
    }


    public static void mergeSort(int[] arr, int start, int end) {
        if (arr == null || arr.length == 0)
            return;
        if (start < end) {
            int mid = start + (end - start) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int size = Integer.parseInt(stdin.nextLine());
        int[] arr = new int[size];
        for (int i = 0; i < size; ++i) {
            arr[i] = Integer.parseInt(stdin.nextLine());
        }
        mergeSort(arr, 0, size - 1);
        System.out.print("[" + Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(",")) + "]");
    }
}
