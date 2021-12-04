package com.anirudh.interview_prep_2021_2022.two_sigma;

import java.util.Random;

/*
Given a RNG that generates values between 0 and 5 (rng(6)), create a RNG that
generates values between 0 and 11 (rng(12))
 */

/*
    res = rng(6) + (6 *( rng(6) % 2))
 */
public class CustomRandomNumberGenerator {

    Random r = new Random();
    private int get05RNG() { //[0,5]
        return r.nextInt(6);
    }

    private int get01RNG() { //[0,1]
        return get05RNG() % 2; //[0,6): 0, 1, 2, 3, 4,5: equal number of odd and even nums
    }
    int get011RNG() { //[0, 11]
        return get05RNG() + (6 * get01RNG()); // [0,5] + 6 * [0, 1]; EQUAL PROBABILITY OF GETTING SHIFTED TO [6-11] RANGE
    }

//    int get011RNG2() { //WRONG! PROB OF GETTING 0 IS LESS THAN PROB OF GETTING 1
//        int zeroTo15 = get05RNG() + get05RNG() + get05RNG(); //[0 to 15]; DONT DO 3 * get05RNG() as it will yield only multiples of 3
//        if(zeroTo15 < 12)
//            return zeroTo15;
//        else
//            return get011RNG2();
//    }

    public static void main(String[] args) {
        for(int i = 0; i < 20; ++i) {
            System.out.print(new CustomRandomNumberGenerator().get011RNG() + " ");
        }
    }
}
