package com.anirudh.algos.shapes;

/**
 * Created by anirudh on 19/7/16.
 */
public class Circle extends Shape{
    private double radius = 10;
    public void printMe() {
        System.out.println("I am a goddamn circle");
    }
    public double calculateArea(){
        return 22*radius*radius/7;
    }
}
