package competitions.ufpt_tryouts_09182016;

/*
* Description
 Download as PDF
While driving the other day, John looked down at his odometer, and it read 100000. John was pretty excited about that. But, just one mile further, the odometer read 100001, and John was REALLY excited! You see, John loves palindromes - things that read the same way forwards and backwards. So, given any odometer reading, what is the least number of miles John must drive before the odometer reading is a palindrome? For John, every odometer digit counts. If the odometer reading was 000121, he wouldn't consider that a palindrome.
Input
There will be several test cases in the input. Each test case will consist of an odometer reading on its own line. Each odometer reading will be from 2 to 9 digits long. The odometer in question has the number of digits given in the input so, if the input is 00456, the odometer has 5 digits. There will be no spaces in the input, and no blank lines between input sets. The input will end with a line with a single 0.
Output
For each test case, output the minimum number of miles John must drive before the odometer reading is a palindrome. This may be 0 if the number is already a palindrome. Output each integer on its own line, with no extra spaces and no blank lines between outputs.
Sample Input
100000
100001
000121
00456
0
Sample Output
1
0
979
44*/

import java.util.Scanner;
public class Palindrometer {

    public static Scanner s = new Scanner(System.in);
    public static boolean isPalindrome(String str) {
        return (str.equals(new StringBuilder(str).reverse().toString()));
    }

    public static Integer findSteps(String str, Integer count) {
        Boolean isPal;
        String newStr, zeroes;
        Integer i;
        Integer strLength = str.length();
        String strStripped = str.replaceFirst("^0+(?!$)", "");
        String strInc = (new Integer(Integer.parseInt(strStripped) + 1)).toString();
        Integer lengthDiff = strLength - strInc.length();
        if(lengthDiff == 0){
            isPal = isPalindrome(strInc);
            newStr = strInc;
        }
        else{
            zeroes = "";
            for(i = 0; i<lengthDiff; i++){
                zeroes = zeroes + "0";
            }
            newStr = zeroes + strInc;
            isPal = isPalindrome(newStr);
        }
        if(isPal)
            return count;
        else
            return findSteps(newStr, count + 1);
    }

    public static void readStuff() {
        String line = "";
        while ( !line.equals("0") ) {
            line = s.nextLine() ;
            if(line.equals("0"))
                return;
            if(isPalindrome(line))
                System.out.println(0);
            else{
                System.out.println(findSteps(line, 1));
            }
        }}

    public static void main(String[] args)  {
        readStuff();
    }

}
