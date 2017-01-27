package competitions;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by paanir on 1/26/17.
 */
public class Veritas_challenge {

    static int redCost(int[] num) {

        int initLen = num.length;

        if(initLen == 0 || initLen == 1)
            return 0;

        int cost = 0;
        Arrays.sort(num);
        while(initLen > 1){
            System.out.println(cost);
            int first = num[0];
            System.out.println("first: " + first);
            int sec = num[1];
            System.out.println("sec: " + sec);
            int sum = first + sec;
            cost += sum;
            int[] newNum = new int[initLen - 1 ];
            for(int i = 2; i < initLen; ++i){
                newNum[i - 2] = num[i];
            }
            newNum[initLen - 2] = sum;
            Arrays.sort(newNum);
            num = newNum;
            initLen--;
        }
        return cost;
    }


    static String rollingString(String s, String[] operations) {

        char[] ch = s.toCharArray();

        for(int i = 0; i < operations.length; ++i){
            String[] syms = operations[i].split(" ");

            int start = Integer.parseInt(syms[0]);
            int end = Integer.parseInt(syms[1]);
            String dir = syms[2];

            for(int j = start; j <=end; ++j){
                if(dir.equals("R")){
                    if(ch[j] != 'z')
                        ch[j] = ++ch[j];
                    else
                        ch[j] = 'a';
                }
                else{ //L
                    if(ch[j] != 'a')
                        ch[j] = --ch[j];
                    else
                        ch[j] = 'z';
                }
            }
        }

        return String.valueOf(ch);

    }

    public static void main(String[] args) {

        int[] myIntArray = new int[]{1,2,3,4};
        System.out.println(redCost(myIntArray));

    }
}
