package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 2/6/17.
 */
public class UniqueBSTs {

    public static long nChooseK(int n, int k) {
        if (k > n - k) //nCk = nC(n-k)
            k = n - k;

        //(n * (n-1) * .....(n - (k - 1))) / 1 * 2 * ... * k
        long num = 1;
        for (int i = 0; i < k; ++i) {
            num *= (n - i); //n to n - (k - 1)
            num /= (i + 1); //1 to k
        }
        return num;
    }

    public static long numTrees(int n) { //catalan nums = 2nCn / (n+1)
        return nChooseK(2 * n, n) / (n + 1);
    }

    public static void main(String[] args) {
        System.out.println(numTrees(19));
    }
}
