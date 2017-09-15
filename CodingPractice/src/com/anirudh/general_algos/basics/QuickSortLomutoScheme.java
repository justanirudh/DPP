package com.anirudh.general_algos.basics;

/**
 * Created by paanir on 2/2/17.
 */
public class QuickSort {

    //Lomuto partition scheme (CLRS)
    public static int partition(int[] arr, int start, int end) {
        int pivotElem = arr[start];
        int large = start;
        for (int small = start + 1; small <= end; small++) {
            if (arr[small] < pivotElem) {
                large++;
                //swap
                int temp = arr[small];
                arr[small] = arr[large];
                arr[large] = temp;
            }
        }
        //put pivot in right location
        int temp = arr[large];
        arr[large] = arr[start];
        arr[start] = temp;
        return large;
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
