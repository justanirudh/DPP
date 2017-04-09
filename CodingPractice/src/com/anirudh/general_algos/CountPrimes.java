package com.anirudh.general_algos;

/**
 * Created by paanir on 4/8/17.
 */
//getting TLE
public class CountPrimes {
    public boolean isPrime(int n) {
        double nn = Math.sqrt(n);
        for (int i = 2; i <= nn; ++i) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public int countPrimes(int n) {
        if (n == 0 || n == 1)
            return 0;
        int count = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime(i))
                count++;
        }
        return count;
    }
}
