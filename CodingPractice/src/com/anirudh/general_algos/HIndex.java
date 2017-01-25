package com.anirudh.general_algos;

import java.util.Arrays;

/**
 * Created by paanir on 1/24/17.
 */
public class HIndex {

    public int hIndexAux(int[] arr, int start, int end) {
        if (start > end)
            return 0;
        int mid = (start + end)/2;
        if(arr[mid] >= arr.length - mid){
            if(mid == 0 || (mid != 0 && arr[mid- 1] < arr.length - mid + 1))
                return arr.length - mid;
            else
                return hIndexAux(arr, start, end - 1);
        }
        else
            return hIndexAux(arr, start + 1, end);
    }

    //logn soln
    public int hIndex2(int[] citations) {
        if(citations.length == 0)
            return 0;
        return hIndexAux(citations, 0, citations.length - 1);
    }

    //Order n soln
    public int hIndex(int[] citations) {

        Arrays.sort(citations);
        int count = 0;
        for (int i = citations.length - 1; i >= 0; --i) {
            if (citations[i] >= citations.length - i)
                count++;
            else
                break;
        }
        return count;
    }
}
