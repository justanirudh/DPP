package com.anirudh;

/**
 * Created by anirudh on 21/9/16.
 */
public class Sandbox {
    public static void main(String[] args){
        String paper = "Smith, M.N., Martin, G., Erdos, P.: Newtonian forms of prime factor matrices";
        String[] intermed = paper.split("(?<=\\G\\w+,\\w+),");
        for(String s: intermed){
            System.out.println(s);
        }

    }
}
