package competitions;

/**
 * Created by paanir on 10/25/17.
 */

import java.io.*;
import java.util.*;
/*
Divide two integers. Surround repeating sequence in decimal part with parentheses.
For example,

divide(4, 2) = "2"
divide(2, 4) = "0.5"
divide(1, 3) = "0.(3)"
divide(1, 7) = "0.(142857)"
divide(1, 29) = "0.(0344827586206896551724137931)"

*/

public class BlendLabsPhoneInterview {

    static public String divide(int n1, int n2) {
        StringBuilder sb = new StringBuilder();

        int div = n1 / n2; //integer part
        sb.append(div);
        sb.append(".");
        sb.append("(");

        Set<Integer> nums = new HashSet<>();

        while (true) {
            int num = (n1 - div) * 10;
            if (nums.contains(num))
                break;
            else
                nums.add(num);
            int nextNum = num / n2;
            n1 = num;
            div = (n2 * nextNum);
            sb.append(nextNum);
        }

        sb.append(")");
        return sb.toString();

    }


    public static void main(String[] args) {

        System.out.println(divide(1, 29));

    }


}
