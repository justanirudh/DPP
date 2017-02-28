package com.anirudh;

import java.util.*;

import static com.anirudh.Sandbox.changeA;
import static org.junit.Assert.assertEquals;

/**
 * Created by anirudh on 21/9/16.
 */
public class Sandbox {

    static void  changeA(int a) {
        a = 10;
    }

    public static void main(String[] args) {
        int a = 5;
        changeA(a);
        System.out.println(a);
    }
}

