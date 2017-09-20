package com.anirudh.general_algos.basics;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by paanir on 9/19/17.
 */
//https://www.hackerrank.com/contests/hw1/challenges/merge-sort
public class MergeSort {
    public static void merge(int[] arr, int start, int mid, int end) {

        //first copy to two temp arrays. Then use them to OVERWRITE on the original array

        int lLen = mid - start + 1;
        int rLen = end - (mid + 1) + 1;
        int[] left = new int[lLen];
        int[] right = new int[rLen];

        //make temp arrays
        System.arraycopy(arr, start, left, 0, lLen); //start, startpos, dest, destpos, len
        System.arraycopy(arr, mid + 1, right, 0, rLen);

        //merge into original array with right order
        int i = 0, j = 0, k = start;
        while (i < lLen || j < rLen) {
            if (i >= lLen && j < rLen) {
                arr[k] = right[j];
                j++;
            } else if (i < lLen && j >= rLen) {
                arr[k] = left[i];
                i++;
            } else { //both < length
                if (left[i] < right[j]) {
                    arr[k] = left[i];
                    i++;
                } else if (left[i] > right[j]) {
                    arr[k] = right[j];
                    j++;
                } else { //both equal to each other
                    arr[k] = right[j];
                    k++;
                    arr[k] = left[i];
                    i++;
                    j++;
                }
            }
            k++;
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
