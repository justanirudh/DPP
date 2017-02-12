package com.anirudh.datastructures.OOD.shapes;

/**
 * Created by anirudh on 19/7/16.
 */
public class Driver {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[2];
        Circle c = new Circle();
        Square s = new Square();
        shapes[0] = c;
        shapes[1] = s;
        for(Shape sh : shapes) {
            sh.printMe();
            System.out.println(sh.calculateArea());
        }
    }
}
