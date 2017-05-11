package com.anirudh.general_algos;

import java.util.*;

/**
 * Created by paanir on 1/23/17.
 */
public class LaundryDay {

    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        Map<String, Integer> map = new TreeMap<>(); //Sorted map
        while (stdin.hasNextLine()) {
            String cloth = stdin.nextLine();
            if (map.containsKey(cloth))
                map.put(cloth, map.get(cloth) + 1);
            else
                map.put(cloth, 1);

        }

        for (String cloth : map.keySet()) {
            String[] words = cloth.split(" ");
            boolean isSock = words[words.length - 1].equals("sock");
            int num = map.get(cloth);
            if (!isSock)
                System.out.println( num + "|" + cloth);
            else {
                if(num %2 == 0){ //even
                    System.out.println(num/2 + "|" + cloth);
                }
                else{ //odd
                    if(num == 1)
                        System.out.println("0|" + cloth);
                    else{
                        System.out.println((num-1)/2 + "|" + cloth);
                        System.out.println("0|" + cloth);
                    }
                }
            }
        }
    }
}