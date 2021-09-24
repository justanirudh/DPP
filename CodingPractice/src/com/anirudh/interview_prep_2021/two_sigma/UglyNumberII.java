package com.anirudh.interview_prep_2021.two_sigma;

/*
264. Ugly Number II
Medium

3127

179

Add to List

Share
An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

Given an integer n, return the nth ugly number.

Example 1:

Input: n = 10
Output: 12
Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
Example 2:

Input: n = 1
Output: 1
Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 */
public class  UglyNumberII {

    /*
    The n-th smallest multiples of a set of integers. For example, set = {5,7}, n = 4, answer 14, - n = 5, answer 15,
    The sequence shall be: 5,7,10,14,15,20,21,25,.
     */
    //{5,7}: 5,7,10, 14, 15, 20,21
    public int nthUglyNumber(int n) {
        int mult5 = 1;
        int mult7 = 1;
        int num = 0;
        for(int i = 0; i < n; ++i) {
            num = Math.min(5 * mult5, 7 * mult7);
            System.out.println(num);
            if(num == 5 * mult5)
                mult5++;
            if(num == 7 * mult7)
                mult7++;
        }
        return num;
    }

    /*
    Create an array of length n and populate it with all ugly numbers
    Create 3 pointers i2,i3 and i5, all 0 to start with
    arr[0] = 1;
    Use older values to create newer values, if min is equal to 2/3/5 * num[idx/2/3/5], increment idx2/3/5
 */
    public static void main(String[] args) {
        new UglyNumberII().nthUglyNumber(10);
    }

    public int nthUglyNumberLC(int n) {
        int[] ugly = new int[n];
        int i2 = 0; //4
        int i3 = 0; //2
        int i5 = 0; //1
        ugly[0] = 1;
        for(int i = 1; i < n; ++i) {
            ugly[i] = Math.min(2 * ugly[i2], Math.min(3 * ugly[i3], 5 * ugly[i5])); //8,9,10
            if(ugly[i] == 2 * ugly[i2])
                i2++;
            if(ugly[i] == 3 * ugly[i3])
                i3++;
            if(ugly[i] == 5 * ugly[i5])
                i5++;
        }
        return ugly[n-1];
    }
}
