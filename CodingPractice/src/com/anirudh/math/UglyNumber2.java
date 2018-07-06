package com.anirudh.math;

/**
 * Created by paanir on 5/14/17.
 */
/*
264. Ugly Number II
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12
is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number, and n does not exceed 1690.
 */
public class UglyNumber2 {

    //Space: O(n), time = O (n^2) kind of
    //--------------------------------------------------------
    public boolean isUgly_Slow(int num) { //sieve of erathonansas
        boolean[] done = new boolean[num + 1];
        for (int factor = 2; factor <= num; ++factor) {
            if (!done[factor] && num % factor == 0) { //is prime factor
                if (factor != 2 && factor != 3 && factor != 5) {
                    System.out.println(factor);
                    return false;
                } else {
                    int multiple = 1;
                    for (int j = factor; j <= num; j = factor * multiple) {
                        done[j] = true;
                        multiple++;
                    }
                }
            }
        }
        return true;
    }

    public int nthUglyNumber_Slow(int n) {
        int count = 0;
        if (n < 1)
            return 0;
        else if (n == 1)
            return 1;
        else
            count++;
        int num = 1;
        while (count != n) {
            num++;
            if (isUgly_Slow(num))
                count++;
        }
        return num;
    }
//-----------------------------------------------------

    //O(1) space, O(n) time
    /*
    O(n) Java solution
The idea of this solution is from this page:http://www.geeksforgeeks.org/ugly-numbers/

The ugly-number sequence is 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, …
because every number can only be divided by 2, 3, 5, one way to look at the sequence is to split the sequence to three groups as below:

(1) 1×2, 2×2, 3×2, 4×2, 5×2, (skipping 7) 6*2, 8*2,
(2) 1×3, 2×3, 3×3, 4×3, 5×3, ..
(3) 1×5, 2×5, 3×5, 4×5, 5×5, ..
We can find that every subsequence is the ugly-sequence itself (1, 2, 3, 4, 5, …) multiplied by 2, 3 and 5 respectively.

Then we use similar merge method as merge sort, to get every ugly number from the three subsequence.

Every step we choose the smallest one, and move one step after,including nums with same value.
     */
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        //handling 1 to be ugly
        if (n < 1)
            return 0;
        else if (n == 1)
            return 1;
        else
            ugly[0] = 1;

        //getting to nth

        int index2 = 0;
        int index3 = 0;
        int index5 = 0;

        //initializing sequence so that previous values can be obtained
        int factor2 = 2;
        int factor3 = 3;
        int factor5 = 5;

        for (int i = 1; i < n; ++i) {
            System.out.println("factor2: " + factor2 +  " factor3: " + factor3 + " factor5 " + factor5 );

            //this gets added AFTER checking the lowest
            ugly[i] = Math.min(Math.min(factor2, factor3), factor5);

            //since equating, reduandant results (2*3 and 3*2) are both incremented, hence no repetition
            if (ugly[i] == factor2) {
                index2++;
                factor2 = 2 * ugly[index2];
            }
            if (ugly[i] == factor3) {
                index3++;
                factor3 = 3 * ugly[index3];
            }
            if (ugly[i] == factor5) {
                index5++;
                factor5 = 5 * ugly[index5];
            }
        }
        return ugly[n - 1];
    }


    public static void main(String[] args) {
        UglyNumber2 ug = new UglyNumber2();
        System.out.println(ug.nthUglyNumber(10));
    }
}
