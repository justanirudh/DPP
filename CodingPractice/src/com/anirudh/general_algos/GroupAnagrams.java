package com.anirudh.general_algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by paanir on 1/2/17.
 */
public class GroupAnagrams {


    private static String sortString(String s) {
        char[] chararr = s.toCharArray();
        Arrays.sort(chararr);
        return new String(chararr);
    }

    public static List<List<String>> groupAnagrams(String[] args) {
        Map<String, List<String>> groups = Arrays.stream(args).collect(Collectors.groupingBy(str -> sortString(str)));
        return new ArrayList<>(groups.values());
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        for (List<String> l : groupAnagrams(strs)) {
            for (String str : l) {
                System.out.print(str + ", ");
            }
            System.out.println();
        }
    }
}
