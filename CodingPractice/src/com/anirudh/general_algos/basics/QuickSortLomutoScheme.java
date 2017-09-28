package com.anirudh.general_algos.basics;

/**
 * Created by paanir on 2/2/17.
 */

//https://en.wikipedia.org/wiki/Quicksort#Algorithm: //Lomuto partition scheme (same as CLRS)
public class QuickSortLomutoScheme {

    public static void swap(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    public static int partition(int[] arr, int start, int end) {
        int pivotElem = arr[start];
        int left = start;
        for (int right = start + 1; right <= end; right++) {
            if (arr[right] < pivotElem) {
                left++;
                swap(arr, right, left);
            }
        }
        //put pivot in right location
        swap(arr, left, start);
        return left;
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (arr.length == 0)
            return;
        if (start < end) { //arr size > 1
            int pivot = partition(arr, start, end);
            quickSort(arr, start, pivot - 1);
            quickSort(arr, pivot + 1, end);
        }
    }

    public static void main(String[] args) {

        int[] arr = {6, 10, 13, 5, 8, 3, 2, 11, 49, 23, 50, 1, 0};
//        int[] arr = {2, 1, 3, 5, 4};

        /*int[] sorted =*/
        quickSort(arr, 0, arr.length - 1);

        for (int i : arr) {
            System.out.print(i + ",");
        }

    }


}
