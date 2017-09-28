package com.anirudh.general_algos;

import java.util.Arrays;

/**
 * Created by paanir on 1/24/17.
 */
/*
274. H-Index
DescriptionHintsSubmissionsDiscussSolution
Discuss Pick One
Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.
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
