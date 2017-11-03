package competitions_and_interviews;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by paanir on 1/28/17.
 */
public class TwoSigma_LongestChain {

    static int longestChain(String[] words) {

        HashMap<String, Integer> map = new HashMap<>();

        class comp implements Comparator<String> {
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        }

        Arrays.sort(words, new comp());

        for (int i = 0; i < words.length; ++i) { /*****\n*****/
            String curr = words[i];
            if (curr.equals("")) {
                map.put(curr, 1);
            } else {
                for (int j = 0; j < curr.length(); ++j) {
                    StringBuilder sb = new StringBuilder(curr);
                    String oneLessChar = sb.deleteCharAt(j).toString();
                    if (map.containsKey(oneLessChar)) {
                        if (!map.containsKey(curr)) //map doesnt have curr
                            map.put(curr, map.get(oneLessChar) + 1);
                        else { //map contains curr
                            if (map.get(oneLessChar) + 1 > map.get(curr))
                                map.put(curr, map.get(oneLessChar) + 1);
                        }
                    }
                }
                if (!map.containsKey(curr)) {
                    map.put(curr, 1);
                }
            }
        }

        int max = 0;
        for (Integer i : map.values()) {
            if (i > max)
                max = i;
        }
        return max;
    }


    public static void main(String[] args) {

        String[] arr = new String[]{"bda", "bca", "a", "b", "ba", "bdca"};

        System.out.println(longestChain(arr));

        ;

    }
}
