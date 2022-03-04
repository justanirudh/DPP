package com.anirudh.companies_21_22.facebook.lc_last_6m;

/*
273. Integer to English Words
Hard

1948

4748

Add to List

Share
Convert a non-negative integer num to its English words representation.



Example 1:

Input: num = 123
Output: "One Hundred Twenty Three"
Example 2:

Input: num = 12345
Output: "Twelve Thousand Three Hundred Forty Five"
Example 3:

Input: num = 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"


Constraints:

0 <= num <= 231 - 1
Accepted
287,964
Submissions
978,709
 */
public class IntegertoEnglishWords {

    String[] below10 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] below20 = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] below100 = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    int TEN = 10;
    int TWENTY = 20;
    int HUNDRED = 100;
    int THOUSAND = 1000;
    int MILLION = 1000000;
    int BILLION = 1000000000;

    String getPhrase(int num) {
        String res;
        if (num < TEN) { //5
            res = below10[num];
        } else if (num < TWENTY) { //19
            res = below20[num - TEN];
        } else if (num < HUNDRED) { //9 5
            res = below100[num / TEN] + " " + getPhrase(num % TEN);
        } else if (num < THOUSAND) { //9 95
            res = getPhrase(num / HUNDRED) + " Hundred " + getPhrase(num % HUNDRED);
        } else if (num < MILLION) { //995 001
            res = getPhrase(num / THOUSAND) + " Thousand " + getPhrase(num % THOUSAND);
        } else if (num < BILLION) { //99 995 003
            res = getPhrase(num / MILLION) + " Million " + getPhrase(num % MILLION);
        } else {
            res = getPhrase(num / BILLION) + " Billion " + getPhrase(num % BILLION);
        }
        return res.trim();
    }

    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";
        return getPhrase(num).trim();
    }

}
