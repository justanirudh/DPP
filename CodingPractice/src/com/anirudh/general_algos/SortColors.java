package com.anirudh.general_algos;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paanir on 9/27/17.
 */
//LC 75 under-construction
public class SortColors {
    public void sortColors(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            Integer count = map.putIfAbsent(i, 1); //is null if was not present previously
            if (count != null)
                map.put(i, map.get(i) + 1);
        }
        int i = 0;
        int countRed = i + map.get(0);
        for (; i < countRed; ++i)
            nums[i] = 0;
        int countWhite = i + map.get(1);
        for (; i < countWhite; ++i)
            nums[i] = 1;
        int countBlue = i + map.get(2);
        for (; i < countBlue; ++i)
            nums[i] = 2;
    }

}
