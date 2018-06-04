package com.anirudh.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by paanir on 6/16/17.
 */
/*
50. Pow(x, n)
Implement pow(x, n).
 */
public class MyPow {

    //EPI-4.7: exponentiation by squaring
    //if power%2 == 0, (x^n/2)^2. if power%2=1, x * (x^n/2)^2
    public double myPow(double x, int n) {
        int power = n;
        double curr = x;
        double result = 1.0;

        if (power == 0)
            return 1;

        if (power < 0) {
            power = -power;
            curr = 1 / curr;
        }

        while (power != 0) {
            if ((power & 1) != 0) //power is odd
                result = curr * result;
            curr *= curr;
            power >>>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        MyPow m = new MyPow();
        System.out.println(m.myPow(2.00000, -2147483648));
    }
}
