package com.anirudh.math;

/**
 * Created by paanir on 10/30/17.
 */
public class AddTwoNumbers {
    //http://www.geeksforgeeks.org/add-two-numbers-without-using-arithmetic-operators/
    /*
    XORing 2 bits gives me the result (without the information of carry)
    0 0 : 0
    0 1 : 1
    1 0 : 1
    1 1 : 0
    ANDing 2 bits gives me the carry
    0 0 : 0
    0 1 : 0
    1 0 : 0
    1 1 : 1
    So, using both to solve our problem
     */
    static int add(int x, int y){
        if(x == 0 && y == 0)
            return 0;
        else if (x == 0)
            return y;
        else if (y == 0)
            return x;
        else{
            int res = x ^ y; //result without any info of carries
            int carry = x & y; //info of carries
            carry = carry << 1; //as carry is added to the next left bit
            return add(res, carry);
        }
    }

    public static void main(String[] args) {
        System.out.println(add(25,6));
    }
}
