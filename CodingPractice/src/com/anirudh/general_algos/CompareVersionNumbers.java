package com.anirudh.general_algos;

import scala.Int;

/**
 * Created by paanir on 12/26/16.
 */
public class CompareVersionNumbers {

    public static int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        for (int i = 0; i < Integer.max(v1.length, v2.length); ++i) {
            String cnum1, cnum2;
            if (i < v1.length)
                cnum1 = v1[i];
            else
                cnum1 = "0";
            if (i < v2.length)
                cnum2 = v2[i];
            else
                cnum2 = "0";
            int num1 = Integer.parseInt(cnum1);
            int num2 = Integer.parseInt(cnum2);
            if (num1 > num2)
                return 1;
            else if (num1 < num2)
                return -1;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(compareVersion("1.0", "1.0.1"));
    }
}
