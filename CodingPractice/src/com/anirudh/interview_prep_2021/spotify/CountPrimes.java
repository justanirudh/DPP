package com.anirudh.interview_prep_2021.spotify;

/**
 * Created by paanir on 4/8/17.
 */
/*
204. Count Primes
Description:

Count the number of prime numbers less than a non-negative number, n.
 */

/*
    //Sieve of Eratosthenes
        for (int i = 2; i <= sqrtN; i++) { //until sqrt N as numbers after that have already been covered by primes before i
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) // from i*i as primes before this would already be covered
                    isPrime[j] = false;
            }
        }

*/
public class CountPrimes {

    public int countPrimesFastest(int n) {

        boolean[] isPrime = new boolean[n];

        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }
        int sqrtN = (int) Math.sqrt(n);

        for (int i = 2; i <= sqrtN; i++) { //until sqrt N as numbers after that have already been covered by primes before i
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) // from i*i as primes before this would already be covered
                    isPrime[j] = false;
            }
        }

        int count = 0; //now just count
        for (int i = 2; i < n; i++) {
            if (isPrime[i])
                count++;
        }

        return count;
    }
}
