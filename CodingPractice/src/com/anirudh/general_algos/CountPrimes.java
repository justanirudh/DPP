package com.anirudh.general_algos;

/**
 * Created by paanir on 4/8/17.
 */
/*
204. Count Primes
Description:

Count the number of prime numbers less than a non-negative number, n.
 */
public class CountPrimes {

    //------------------------------------------
    public boolean isPrime(int n) {
        double nn = Math.sqrt(n);
        for (int i = 2; i <= nn; ++i) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    //O(1) space but slower, O(n^1.5) time
    public int countPrimesSlow(int n) {
        if (n == 0 || n == 1)
            return 0;
        int count = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime(i))
                count++;
        }
        return count;
    }
//----------------------------------------------------

    //O(n) space but faster
    //Sieve of Eratosthenes
    public int countPrimesFast(int n) {
        if (n == 0 || n == 1 || n == 2) // ques says less than
            return 0;
        boolean[] done = new boolean[n]; //got all numbers from 0 until n in just a declaration!
        int count = 0;
        for (int i = 2; i < n; ++i) {
            if (!done[i]) {
                count++;
                int factor = 1;
                for (int j = i; j < n; j = i * factor) {
                    done[j] = true;
                    ++factor;
                }
            }
        }
        return count;
    }

    //-------------------------------------------------

    //Even better + fastest
    //Explanation: Hints for the question: https://leetcode.com/problems/count-primes/#/description
    //The Sieve of Eratosthenes uses an extra O(n) memory and its runtime complexity is O(n log log n).
    public int countPrimesFastest(int n) {

        boolean[] isPrime = new boolean[n];

        for (int i = 2; i < n; i++) { //this loop can be avoided by having isNotPrime[], lol
            isPrime[i] = true;
        }

        // Loop's ending condition is i * i < n instead of i < sqrt(n)
        // to avoid repeatedly calling an expensive function sqrt().
        int nn = (int)Math.sqrt(n);
        /*
        Yes, the terminating loop condition can be p < √n, as all non-primes ≥ √n must have already been marked off.
        When the loop terminates, all the numbers in the table that are non-marked are prime.
         */
        for (int i = 2; i <= nn; i++) {
            //taking from i*i as before that have been covered by computations of numbers before this i
            //this also means a separate loop for counting is required as the numbers closer to n might be prime but not yet visited (eg. 17 near 20)
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i)
                    isPrime[j] = false;
            }
        }

        int count = 0;
        //if counted in the previous loop, can give off by
        // 1 errors for small values such as 3 + will be miss many others non-visited
        for (int i = 2; i < n; i++) {
            if (isPrime[i])
                count++;
        }

        return count;
    }
}
