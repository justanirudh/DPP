package com.anirudh.math;

/**
 * Created by paanir on 10/25/17.
 */

import java.util.*;
/*
Divide two integers. Surround repeating sequence in decimal part with parentheses.
For example,

divide(4, 2) = "2"
divide(2, 4) = "0.5"
divide(1, 3) = "0.(3)"
divide(1, 7) = "0.(142857)"
divide(1, 29) = "0.(0344827586206896551724137931)"
divide(59, 29) = "2.(0344827586206896551724137931)"

*/

public class BlendLabs_PhoneInterview {

    //essentially simulating what we do by hand
    static public String divide(int nume, int denom) {

        int div = nume / denom; //integer part

        if (nume % denom == 0)
            return Integer.toString(div);

        Set<Integer> numes = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        //if numerator repeats or reaches 0, game over
        while (true) {
            nume = (nume % denom) * 10; //new numerator
            if (nume == 0 || numes.contains(nume))
                break;
            else
                numes.add(nume);
            sb.append(nume / denom);
        }
        if (nume != 0) {
            //repeating
            sb.insert(0, "(");
            sb.append(")");
        }

        return div + "." + sb.toString();
    }


    public static void main(String[] args) {

        System.out.println(divide(59, 29));

    }


}
