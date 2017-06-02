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

    //Better - under_construction: O(log(root(n)))

    int x;

    public int aux(int start, int end) {
        int mid = (start + end) / 2;
        int midSqrd = mid * mid;
        if (midSqrd == x)
            return mid;
        else if (midSqrd > x) {
            int midMinusOneSqrd = (mid - 1) * (mid - 1);
            if (midMinusOneSqrd <= x)
                return mid - 1;
            else //midMinusOneSqrd > x
                return aux(start, mid - 2);
        } else { //midSqrd < x
            int midPlusOneSqrd = (mid + 1) * (mid + 1);
            if (midPlusOneSqrd > x)
                return mid;
            else if (midPlusOneSqrd == x)
                return mid + 1;
            else //midPlusOneSqrd < x
                return aux(mid + 2, end);
        }
    }

    public int mySqrt(int x) {
        this.x = x;
        return aux(0, x);
    }
}
