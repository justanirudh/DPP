package com.anirudh.OOD.shapes;

/**
 * Created by anirudh on 19/7/16.
 */
public class Square extends Shape{
    private double side = 10;
    public void printMe() {
        System.out.println("I am a goddamn square");
    }
    public double calculateArea(){
        return side * side;
    }
}
