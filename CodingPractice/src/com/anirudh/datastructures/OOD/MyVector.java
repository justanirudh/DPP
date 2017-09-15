package com.anirudh.datastructures.OOD;

/**
 * Created by paanir on 9/15/17.
 */

/*
ADD
Everytime a new element is added, check if the current array can accomodate it
if not, double the size of array, copy elements to new array and then add it

DELETE
Everytime an element is deleted, check if an array of arr.length/2 can accomodate it
If yes, copy to the lesser size array;

LENGTH
get curr as that is length until last populated elem

GET
only get if less than curr

 */
public class MyVector{

    private int[] arr;

    private int curr; //index of next element; also the length

    public MyVector(){
        curr = 0;
    }

    public void add(int elem){
        if(curr == 0){ //first element
            arr = new int[1];
            arr[0] = elem;
        }
        else{
            if(curr == arr.length){ //if filled curr array, time to double the length
                int[] newArr = new int[2 * curr]; //create new array of double length
                System.arraycopy(arr, 0, newArr, 0, arr.length);//copy all elements
                arr = newArr; //point to new array
                arr[curr] = elem;
            }
            else{ //still space
                arr[curr] = elem;
            }
        }
        curr++;
    }

    public int get(int index){
        if(index >= curr)
            throw new RuntimeException("Index is out of bounds");
        return arr[index];
    }

    public void delete(int index){
        if(index >= curr)
            throw new RuntimeException("Index is out of bounds");
        int newLength = arr.length; //check if populated array size is LE half of the actual arr size
        if(curr - 1 == arr.length/2) //as curr-1 will be new length
            newLength = arr.length/2;
        int[] newArr = new int[newLength]; //copy to new array, except element at index 'index'
        int i = 0, newI = 0;
        while(i < curr){ //we need to go till the end of old array
            if(i != index){
                newArr[newI] = arr[i];
                newI++;
            }
            i++;
        }
        arr = newArr; //point arr to newArr
        curr--;
    }

    public int length() {
        return curr;
    }


    public static void main(String[] args) {
        MyVector vec = new MyVector();
        vec.add(1);
        vec.add(2);
        vec.add(3);
        vec.add(4);
        vec.add(5);
        vec.add(6);
        vec.add(7);

        for(int i = 0; i < vec.length(); ++i)
            System.out.print(vec.get(i) + " ");
        System.out.println();

        vec.delete(2);

        for(int i = 0; i < vec.length(); ++i)
            System.out.print(vec.get(i) + " ");
        System.out.println();

        System.out.println(vec.length());
        System.out.println(vec.arr.length);

        vec.delete(2);

        for(int i = 0; i < vec.length(); ++i)
            System.out.print(vec.get(i) + " ");
        System.out.println();

        System.out.println(vec.length());
        System.out.println(vec.arr.length);

        vec.delete(2);

        for(int i = 0; i < vec.length(); ++i)
            System.out.print(vec.get(i) + " ");
        System.out.println();

        System.out.println(vec.length());
        System.out.println(vec.arr.length);

        vec.delete(2);

        for(int i = 0; i < vec.length(); ++i)
            System.out.print(vec.get(i) + " ");
        System.out.println();

        System.out.println(vec.length());
        System.out.println(vec.arr.length);

        vec.add(8);

        for(int i = 0; i < vec.length(); ++i)
            System.out.print(vec.get(i) + " ");
        System.out.println();

        System.out.println(vec.length());
        System.out.println(vec.arr.length);

        vec.add(9);

        for(int i = 0; i < vec.length(); ++i)
            System.out.print(vec.get(i) + " ");
        System.out.println();

        System.out.println(vec.length());
        System.out.println(vec.arr.length);


    }
}
