package com.anirudh;

import java.util.Scanner;

/**
 * Created by paanir on 1/26/17.
 */
public class FileIOJava {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        //way 1: when first line has number of lines info
        int numPpl = Integer.parseInt(stdin.nextLine());
        for (int i = 0; i < numPpl; i++) {
            int money = Integer.parseInt(stdin.nextLine());
        }

        //Way 2:
        while (stdin.hasNextLine()) {
            int money = Integer.parseInt(stdin.nextLine());
        }
    }
}
