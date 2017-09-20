package com.anirudh.general_algos.basics;

import java.util.Scanner;

/**
 * Created by paanir on 9/19/17.
 */
//Under-construction
//https://www.hackerrank.com/contests/hw1/challenges/merge-sort
public class MergeSort {

    public static int[] merge(int[] left, int[] right, int start, int mid, int end) {
        int[] res = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length || j < right.length) {
            if (i >= left.length && j < right.length) {
                res[k] = right[j];
                j++;
            } else if (i < left.length && j >= right.length) {
                res[k] = left[i];
                i++;
            } else { //both < length
                if (left[i] < right[j]) {
                    res[k] = left[i];
                    i++;
                } else if (left[i] > right[j]) {
                    res[k] = right[j];
                    j++;
                } else {
                    res[k] = right[j];
                    k++;
                    res[k] = left[i];
                    i++;
                    j++;
                }
            }
            k++;
        }
    }

    public static int[] mergeSort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            int[] left = mergeSort(arr, start, mid);
            int[] right = mergeSort(arr, mid + 1, end);
            return merge(left, right, start, mid, end);
        }
        return arr;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner stdin = new Scanner(System.in);
        int size = Integer.parseInt(stdin.nextLine());
        String[] arrTemp = stdin.nextLine().split(" ");
        int[] arr = new int[size];
        for (int i = 0; i < size; ++i) {
            arr[i] = Integer.parseInt(arrTemp[i]);
        }
        int[] sorted = mergeSort(arr, 0, size - 1);
    }
}
