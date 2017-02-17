package com.anirudh.datastructures.OOD;

/**
 * Created by anirudh on 14/11/16.
 */
public class SingletonExmaple {

    private static SingletonExmaple exp;
    public int foo;
    private SingletonExmaple(){
        this.foo = 100;
    }

    public static SingletonExmaple getInstance(){
        if(exp == null)
            exp =  new SingletonExmaple();
        return exp;
    }
}

