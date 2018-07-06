package com.anirudh.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anirudh on 17/9/16.
 */
/*Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.
For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.

Note:
(1) 1 is a super ugly number for any given primes.
(2) The given numbers in primes are in ascending order.
(3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.*/

public class SuperUglyNumbers {

    List<Integer> superUglyNums = new ArrayList<Integer>();

    private ArrayList<Integer> getPrimefactors(int n) {
        ArrayList<Integer> factors = new ArrayList<>();
        for (int i = 2; i <= Math.sqrt(n); i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 1)
            factors.add(n);
        return factors;
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        int i;
        List<Integer> primesNew = new ArrayList<Integer>();
        superUglyNums.add(1);
        for (i = 0; i < primes.length; i++)
            primesNew.add(primes[i]);
        for (i = 2; superUglyNums.size() != n; i++) {
            ArrayList<Integer> factors = getPrimefactors(i);
            if (primesNew.containsAll(factors))
                superUglyNums.add(i);
        }
        return superUglyNums.get(n - 1);
    }

    public static void main(String[] args) {
        SuperUglyNumbers s = new SuperUglyNumbers();
        int[] primes = {2, 7, 13, 19};
        int n = 10;
        int nth = s.nthSuperUglyNumber(n, primes);
        System.out.print(n + "th elem: " + nth);
    }


}
