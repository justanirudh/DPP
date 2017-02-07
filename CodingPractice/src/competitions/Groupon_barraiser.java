package competitions;

import java.util.HashMap;

/**
 * Created by paanir on 2/6/17.
 */
public class Groupon_barraiser {


//    CAT
//
//            [] [] [] []
//   [S]  [] []   []
//
//            [B] [] [K] []
//
//            [] [] []
//
//
//            "STAB!"
//
//    STAB, CAT => not a valid steal
//    TACK, CAT => valid steal
//
// */

    public static boolean isValid(String stolen, String proposed) {
        //stolen word is a subset of porposed word
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < stolen.length(); ++i) {
            Character curr = stolen.charAt(i);
            if (map.containsKey(curr))
                map.put(curr, map.get(curr) + 1);
            else
                map.put(curr, 1);
        }

        int extra = 0;

        for (int i = 0; i < proposed.length(); ++i) {
            Character curr = proposed.charAt(i);
            if (map.containsKey(curr)) {
                map.put(curr, map.get(curr) - 1);
                if (map.get(curr) == 0)
                    map.remove(curr);
            } else
                extra++;
        }

        return map.isEmpty() && (extra != 0);

    }

}