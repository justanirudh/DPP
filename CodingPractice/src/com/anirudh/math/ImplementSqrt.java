package com.anirudh.math;

/**
 * Created by paanir on 6/1/17.
 */
/*
69. Sqrt(x)

Implement int sqrt(int x).

Compute and return the square root of x.
 */
public class ImplementSqrt {


    //binary search O(logn) (might look weird but it is exactly what you think it is)
    public int mySqrt(int x) {
        if (x == 0)
            return 0;
        int left = 1;
        int right = x;
        while (true) {
            int sqrt = left + (right - left) / 2;
            if (sqrt > x / sqrt) //sqrt * sqrt > x
                right = sqrt - 1;
            else { //sqrt * sqrt <= x
                if (sqrt + 1 > x / (sqrt + 1)) //(sqrt^2 <= num && (sqrt+1)^2 > num)
                    return sqrt;
                left = sqrt + 1;
            }
        }
    }

    //Naive: O(root(n))
    public int mySqrtNaive(int x) {
        int num = 0;
        while (num * num < x)
            num++;
        if (num * num == x)
            return num;
        else
            return --num;
    }


}
