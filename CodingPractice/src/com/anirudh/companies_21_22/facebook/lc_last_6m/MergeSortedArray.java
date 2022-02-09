package com.anirudh.companies_21_22.facebook.lc_last_6m;

public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] res = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < m || j < n) {
            int num1 = i < m ? nums1[i] : Integer.MAX_VALUE;
            int num2 = j < n ? nums2[j] : Integer.MAX_VALUE;
            if (num1 < num2) {
                res[k] = num1;
                i++;
            } else {
                res[k] = num2;
                j++;
            }
            k++;
        }
        //copy from res to nums1
        System.arraycopy(res, 0, nums1, 0, m + n);
    }
}
