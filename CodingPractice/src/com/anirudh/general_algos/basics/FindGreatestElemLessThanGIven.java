package com.anirudh.general_algos.basics;

import java.util.*;

/**
 * Created by paanir on 1/17/17.
 */
//Given a sorted array, find the index of the greatest element less than the given element
public class FindGreatestElemLessThanGIven {

    public static int greatestElemLessThanGivenElem(int[] arr, int elem, int start, int end){
        if(start < end){
            int mid = (start + end)/2;
            if(arr[mid] > elem)
                return greatestElemLessThanGivenElem(arr, elem, start, mid - 1);
            else
                return greatestElemLessThanGivenElem(arr, elem, mid + 1, end);
        }
        else{
            if(elem >= arr[start])
                return start;
            else
                return start - 1;
        }
    }

    public static int greatestElemLessThanGivenElem(List<Integer> arr, int elem, int start, int end){
        if(start < end){
            int mid = (start + end)/2;
            if(arr.get(mid) > elem)
                return greatestElemLessThanGivenElem(arr, elem, start, mid - 1);
            else
                return greatestElemLessThanGivenElem(arr, elem, mid + 1, end);
        }
        else{
            if(elem >= arr.get(start))
                return start;
            else
                return start - 1;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{10,20, 30, 40, 50};
        ArrayList<Integer> arr2 = new ArrayList<>();
        arr2.add(10);
        arr2.add(20);
        arr2.add(30);
        arr2.add(40);
        arr2.add(50);
        int elem = 55;
//        System.out.println(greatestElemLessThanGivenElem(arr, elem, 0, arr.length - 1));
//        System.out.println(greatestElemLessThanGivenElem(arr2, elem, 0, arr.length - 1));
        System.out.println(Collections.binarySearch(arr2, 11));
    }
}
