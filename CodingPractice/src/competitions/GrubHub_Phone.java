package competitions;

/**
 * Created by paanir on 10/26/17.
 */

/*
credit card
54302976
right to left
every 2nd digit double
if >= 10, minus 9
add all
if div by 10, true
 */
public class GrubHub_Phone {

    static public boolean isValid(String num) {
        int sum = 0;
        int numLen = num.length();
        for (int i = numLen - 1; i >= 0; --i) {
            char ch = num.charAt(i);
            int chInt = ch - '0';
            if ((numLen - i) % 2 == 0) {
                int newInt = 2 * chInt;
                if (newInt >= 10)
                    newInt -= 9;
                sum += newInt;
            } else {
                sum += chInt;
            }
        }
        return (sum % 10) == 0;
    }
}