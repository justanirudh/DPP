package com.anirudh.general_algos.basics;

import java.util.Random;

/**
 * Created by paanir on 9/20/17.
 */
public class ShuffleArray {

    public static void swap(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    public static void randomize(int[] arr){
        Random rand = new Random();
        for (int i1 = 1; i1 < arr.length; ++i1) {
            int i2 = rand.nextInt(i1);
            swap(arr, i1, i2);
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 5, 1, 8, 6, 10, 9, 7};
        randomize(arr);

        for(int num : arr)
            System.out.println(num);

    }
}
