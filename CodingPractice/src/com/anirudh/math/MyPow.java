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
    //memoization + better algorithm for getting power

    //List as key as I get equals and hashcode methods for free
    HashMap<List<Double>, Double> map = new HashMap<>();

    public double aux(double x, int n) {
        if (x == 0 || x == 1 || n == 1)
            return x;
        if (n == 0)
            return 1;
        if (n % 2 == 0) {
            List<Double> list = new ArrayList<>();
            list.add(x);
            int half = n / 2;
            list.add((double) half);
            if (map.containsKey(list))
                return map.get(list);
            else {
                double temp = aux(x, half);
                double res = temp * temp;
                map.put(list, res);
                return res;
            }
        } else {
            List<Double> list = new ArrayList<>();
            list.add(x);
            int half = (n - 1) / 2;
            list.add((double) half);
            if (map.containsKey(list))
                return map.get(list);
            else {
                double temp = aux(x, half);
                double res = temp * temp * x;
                map.put(list, res);
                return res;
            }
        }
    }

    public double myPow(double x, int exp) {
        return exp > 0 ? aux(x, exp) : aux(1 / x, -exp);
    }

    public static void main(String[] args) {
        MyPow m = new MyPow();
        System.out.println(m.myPow(2.00000, -2147483648));
    }
}
