package com.anirudh;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by paanir on 1/22/17.
 */
public class MugColor {


    public static void main(String[] args) {
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("White", false);
        map.put("Black", false);
        map.put("Blue", false);
        map.put("Red", false);
        map.put("Yellow", false);

        Scanner stdin = new Scanner(System.in);

        int numppl = Integer.parseInt(stdin.nextLine());
        while (numppl != 0) {
            String color = stdin.nextLine();
            if (!map.get(color))
                map.put(color, true);
            numppl--;
        }

        String c = "";
        for (String s : map.keySet()) {
            if (!map.get(s)) {
                c = s;
                break;
            }
        }
        System.out.println(c);
    }

}
