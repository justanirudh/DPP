package com.anirudh.general_algos.basics;

/**
 * Created by paanir on 9/21/17.
 */
public class BinarySearch {
    static int search(int[] arr, int start, int end, int elem) { //logn
        if (start <= end) {
            int mid = start + (end - start) / 2; //to avoid overflow
            if (arr[mid] == elem)
                return mid;
            else if (arr[mid] < elem)
                return search(arr, mid + 1, end, elem);
            else
                return search(arr, start, mid - 1, elem);
        }
        return -1;
    }

    public static void main(String[] args) {
        //precondition: arr is sorted
        int[] arr = {5,7,10,12,15, 16, 18, 22, 25, 28};
//        int[] arr = {1, 2};
        int elem = 16;
        System.out.println(search(arr, 0, arr.length - 1, elem));
    }
}
