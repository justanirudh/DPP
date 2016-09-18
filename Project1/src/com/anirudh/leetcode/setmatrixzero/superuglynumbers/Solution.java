package com.anirudh.leetcode.setmatrixzero.superuglynumbers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anirudh on 17/9/16.
 */
public class Solution {

    List<Integer> superUglyNums = new ArrayList<Integer>();

    private ArrayList<Integer> getPrimefactors(int n) {
        ArrayList<Integer> factors = new ArrayList<>();
        for (int i = 2; i <= Math.sqrt(n); i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if(n > 1)
            factors.add(n);
        return factors;
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        int i;
        List<Integer> primesNew = new ArrayList<Integer>();
        superUglyNums.add(1);
        for(i = 0; i < primes.length; i++)
            primesNew.add(primes[i]);
        for(i = 2; superUglyNums.size() != n; i++){
            ArrayList<Integer> factors = getPrimefactors(i);
            if(primesNew.containsAll(factors))
                superUglyNums.add(i);
        }
        return superUglyNums.get(n - 1);
    }
}
