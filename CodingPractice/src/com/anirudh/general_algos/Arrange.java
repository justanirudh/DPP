package com.anirudh.general_algos;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by paanir on 2/3/17.
 */


/*
InterviewBit
Rearrange ArrayBookmark Suggest Edit
Rearrange a given array so that Arr[i] becomes Arr[Arr[i]] with O(1) extra space.

Example:

Input : [1, 0]
Return : [0, 1]
 Lets say N = size of the array. Then, following holds true :
* All elements in the array are in the range [0, N-1]
* N * N does not overflow for a signed integer


 */
public class Arrange {

    public static void arrange(ArrayList<Integer> a) {

        boolean isOrdered = false;

        int i = 0;

        while (!isOrdered) {

            int temp1 = a.get(i);

            if (a.get(temp1) == 0)
                a.set(i, -999);
            else
                a.set(i, -1 * a.get(temp1));

            while (true) { //do arr[i] = -1* arr[arr[i]] so that it doesnt disturb further traversals
                int j = 0;
                while (j < a.size()) {
                    if (a.get(j) == i)
                        break;
                    j++;
                }
                if (j == a.size())
                    break;
                int temp2 = a.get(j);

                if (temp1 != 0)
                    a.set(j, -1 * temp1);
                else
                    a.set(j, -999);
                temp1 = temp2;
                i = j;
            }

            isOrdered = true;

            for (int m = 0; m < a.size(); ++m) { //check if all negative OR if positive, then ondition laready satisfied. If not, doa gain
                if (a.get(m) > 0 && (a.get(m).intValue() != a.get(a.get(m)))) {
                    System.out.println(m);
                    isOrdered = false;
                    i = m;
                    break;
                }
            }
        }


        for (int l = 0; l < a.size(); ++l) { //convert all -ve to +ve
            if (a.get(l) == -999)
                a.set(l, 0);
            else if (a.get(l) < 0)
                a.set(l, -1 * a.get(l));
        }

        for (int k = 0; k < a.size(); ++k) {
            System.out.print(a.get(k) + " ");
        }

    }

    public static void main(String[] args) {
        ArrayList<Integer> al = new ArrayList<>();
        al.add(1);
        al.add(2);
        al.add(7);
        al.add(0);
        al.add(9);
        al.add(3);
        al.add(6);
        al.add(8);
        al.add(5);
        al.add(4);
        arrange(al);
    }
}
