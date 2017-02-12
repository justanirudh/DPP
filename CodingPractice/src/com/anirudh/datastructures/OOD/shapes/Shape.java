package com.anirudh.datastructures.OOD.shapes;

/**
 * Created by anirudh on 19/7/16.
 */
public abstract class Shape {
    public void printMe(){
        System.out.println("Imma be a shape");
    }
    public abstract double calculateArea();
}
