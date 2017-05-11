package com.anirudh;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 4/7/17.
 */
//#39
//Under-construction..
public class CombinationSum {

    int[] candidates;
    int target;
    int[] maxCounts;

    public List<List<Integer>> restSum(int sum, int lastNum, int index, List<Integer> combo) {
        if (index == candidates.length) { //done with all elems
            if (sum == target) {
                System.out.println(sum);
                for (Integer i : combo) {
                    System.out.print(i + ",");
                }
                System.out.println();
                List<List<Integer>> combos = new ArrayList<>();
                combos.add(combo);
                return combos;
            } else
                return null;
        } else {
            List<List<Integer>> combos = new ArrayList<>();
            int currCandidate = candidates[index];
            int currMaxCount = maxCounts[index];
            for (int i = 0; i < currMaxCount; ++i) {
                if (lastNum != 0)
                    combo.add(lastNum);
                int newSum = sum + currCandidate * i;
                if(newSum > target)
                    break;
                List<List<Integer>> list = restSum(newSum, currCandidate * i, index + 1, combo);
                if (list != null)
                    combos.addAll(list);
            }
            return combos;
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.candidates = candidates;
        this.target = target;

        int len = candidates.length;
        if (len == 0)
            return null;
        int[] maxCounts = new int[len];
        for (int i = 0; i < len; i++) {
            int sum = 0;
            int currElem = candidates[i];
            int maxCount = 0;
            while (sum <= target) {
                sum += currElem;
                maxCount++;
            }
            maxCounts[i] = maxCount;
        }
        this.maxCounts = maxCounts;
         for(int i = 0; i< len; ++i)
             System.out.println(maxCounts[i] + ",");

        List<List<Integer>> combos = new ArrayList<>();

        int currMaxCount = maxCounts[0];
        int currCandidate = candidates[0];
        for (int i = 0; i < currMaxCount; ++i) {
            List<List<Integer>> list = restSum(currCandidate * i, currCandidate * i, 1, new ArrayList<>());
            if (list != null)
                combos.addAll(list);
        }
        return combos;
    }

    public static void main(String[] args) {
        int target = 7;
        int[] arr = {2, 3, 6, 7};
        CombinationSum cs = new CombinationSum();
        cs.combinationSum(arr, target);
    }
}
