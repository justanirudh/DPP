package com.anirudh.interview_prep_2021.two_sigma;

import java.util.Random;

/*
Given a RNG that generates values between 1 and 5, create a RNG that
generates values between 1 to 7
 */
/*
    // returns 1 to 7 with equal probability
    public static int my_rand() {
        int i;
        i = 5 * get15RNG() + get15RNG() - 5;
        if (i < 22)
            return i % 7 + 1;
        else
            return my_rand();
    }
 */
public class CustomRandomNumberGenerator2 {

    static Random r = new Random();
    // given method that returns [1,5] == [1,6) with equal probability
    static int get15RNG() {
        // some code here
        return 1 + r.nextInt(6 - 1); // min + rand.nextInt(max - min), The min parameter (the origin) is inclusive, whereas the upper bound max is exclusive.
    }

    // returns 1 to 7 with equal probability
    public static int my_rand() {
        int i;
        i = 5 * get15RNG() + get15RNG() - 5;
        if (i < 22)
            return i % 7 + 1;
        else
            return my_rand();
    }

    public static void main(String[] args) {
        int first, second;
        for (first = 1; first <= 5; ++first) {
            for (second = 1; second <= 5; ++second) {
                System.out.printf("%d \n", 5 * first + second - 5);
            }

        }


        System.out.println(my_rand());
    }
}

