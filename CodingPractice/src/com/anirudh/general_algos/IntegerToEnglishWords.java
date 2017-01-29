package com.anirudh.general_algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by paanir on 1/27/17.
 */
public class IntegerToEnglishWords {


    //I went bottom up: Hence, all the problems. GO TOP DOWN!!!
    //http://www.programcreek.com/2014/05/leetcode-integer-to-english-words-java/
    public static HashMap<Integer, String> populateOnesPlace() {
        HashMap<Integer, String> onesPlace = new HashMap<>();
//        onesPlace.put(0, "Zero");
        onesPlace.put(1, "One");
        onesPlace.put(2, "Two");
        onesPlace.put(3, "Three");
        onesPlace.put(4, "Four");
        onesPlace.put(5, "Five");
        onesPlace.put(6, "Six");
        onesPlace.put(7, "Seven");
        onesPlace.put(8, "Eight");
        onesPlace.put(9, "Nine");
        return onesPlace;
    }

    public static HashMap<Integer, String> populateTensPlace() {
        HashMap<Integer, String> tensPlace = new HashMap<>();
        tensPlace.put(1, "Ten");
        tensPlace.put(2, "Twenty");
        tensPlace.put(3, "Thirty");
        tensPlace.put(4, "Forty");
        tensPlace.put(5, "Fifty");
        tensPlace.put(6, "Sixty");
        tensPlace.put(7, "Seventy");
        tensPlace.put(8, "Eighty");
        tensPlace.put(9, "Ninety");
        return tensPlace;
    }

    public static HashMap<String, String> populateTeens() {
        HashMap<String, String> tensPlace = new HashMap<>();
        tensPlace.put(null, "Ten");
        tensPlace.put("One", "Eleven");
        tensPlace.put("Two", "Twelve");
        tensPlace.put("Three", "Thirteen");
        tensPlace.put("Four", "Fourteen");
        tensPlace.put("Five", "Fifteen");
        tensPlace.put("Six", "Sixteen");
        tensPlace.put("Seven", "Seventeen");
        tensPlace.put("Eight", "Eighteen");
        tensPlace.put("Nine", "Nineteen");
        return tensPlace;
    }

    public static StringBuilder getTheFixForOne(StringBuilder sb, HashMap<String, String> teens, int place) {
        String[] strArr = sb.toString().split(" ");
        String last = strArr[0];
        if (teens.get(last) == null) {
            if (place == 5)
                return sb.insert(0, "Ten ");
        }

        String[] newStrArr = Arrays.copyOfRange(strArr, 1, strArr.length);
        sb = new StringBuilder();
        for (int i = 0; i < newStrArr.length; ++i) {
            sb.append(newStrArr[i]);
            if (i != newStrArr.length - 1)
                sb.append(" ");
        }

        return sb.insert(0, teens.get(last) + " ");
    }


    public static String numberToWords(int num) {

        int place = 1;
        StringBuilder sb = new StringBuilder();

        HashMap<Integer, String> onesPlace = new HashMap<>();
        onesPlace = populateOnesPlace();

        HashMap<Integer, String> tensPlace = new HashMap<>();
        tensPlace = populateTensPlace();

        HashMap<String, String> teens = new HashMap<>();
        teens = populateTeens();

        if (num == 0)
            return "Zero";
        while (num != 0) {
            int dig = num % 10;
            num = num / 10;
            if (place == 1) {
                if (dig != 0)
                    sb.insert(0, onesPlace.get(dig));
            } else if (place == 2) {
                if (dig != 0 && dig != 1) {
                    if (sb.toString().isEmpty())
                        sb.insert(0, tensPlace.get(dig));
                    else
                        sb.insert(0, tensPlace.get(dig) + " ");
                }

                if (dig == 1) {
                    String last = sb.toString();
                    sb = new StringBuilder();
                    sb.insert(0, teens.get(last));
                }
            } else if (place == 3) {
                if (dig != 0) {
                    if (sb.toString().isEmpty())
                        sb.insert(0, onesPlace.get(dig) + " Hundred");
                    else
                        sb.insert(0, onesPlace.get(dig) + " Hundred ");
                }
            } else if (place == 4) {
                if (dig != 0) {
                    if (sb.toString().isEmpty())
                        sb.insert(0, onesPlace.get(dig) + " Thousand");
                    else
                        sb.insert(0, onesPlace.get(dig) + " Thousand ");
                } else {
                    if (sb.toString().isEmpty())
                        sb.insert(0, "Thousand");
                    else
                        sb.insert(0, "Thousand ");
                }
            } else if (place == 5) {
                if (dig != 0 && dig != 1)
                    sb.insert(0, tensPlace.get(dig) + " ");
                if (dig == 1) {
                    sb = getTheFixForOne(sb, teens, place);
                }
            } else if (place == 6) {
                if (dig != 0) {
                    if (sb.toString().isEmpty())
                        sb.insert(0, onesPlace.get(dig) + " Hundred");
                    else
                        sb.insert(0, onesPlace.get(dig) + " Hundred ");
                }
            } else if (place == 7) {
                if (dig != 0) {
                    if (sb.toString().isEmpty() || sb.toString().equals("Thousand")) {
                        sb = new StringBuilder();
                        sb.append(onesPlace.get(dig) + " Million");
                    } else {
                        sb.insert(0, onesPlace.get(dig) + " Million ");
                    }

                }
            } else if (place == 8) {
                if (dig != 0 && dig != 1)
                    sb.insert(0, tensPlace.get(dig) + " ");
                if (dig == 1) {
                    sb = getTheFixForOne(sb, teens, place);
                }
            } else if (place == 9) {
                if (dig != 0) {
                    if (sb.toString().isEmpty())
                        sb.insert(0, onesPlace.get(dig) + " Hundred");
                    else
                        sb.insert(0, onesPlace.get(dig) + " Hundred ");
                }
            } else if (place == 10) {
                if (dig != 0) {
                    if (sb.toString().isEmpty())
                        sb.insert(0, onesPlace.get(dig) + " Billion");
                    else
                        sb.insert(0, onesPlace.get(dig) + " Billion ");
                }
            } else /*if (place == 11)*/ {
//                if (dig != 0 && dig != 1)
//                    sb.insert(0, tensPlace.get(dig) + " ");
//                if (dig == 1) {
//                    sb = getTheFixForOne(sb, teens);
//                }
                return sb.toString();
            }
            place++;
        }

        return sb.toString();
    }

    public class Solution { //ProgramCreek
        HashMap<Integer, String> map = new HashMap<Integer, String>();

        public String numberToWords(int num) {
            fillMap();
            StringBuilder sb = new StringBuilder();

            if (num == 0) {
                return map.get(0);
            }

            if (num >= 1000000000) {
                int extra = num / 1000000000;
                sb.append(convert(extra) + " Billion");
                num = num % 1000000000;
            }

            if (num >= 1000000) {
                int extra = num / 1000000;
                sb.append(convert(extra) + " Million");
                num = num % 1000000;
            }

            if (num >= 1000) {
                int extra = num / 1000;
                sb.append(convert(extra) + " Thousand");
                num = num % 1000;
            }

            if (num > 0) {
                sb.append(convert(num));
            }

            return sb.toString().trim();
        }

        public String convert(int num) {

            StringBuilder sb = new StringBuilder();

            if (num >= 100) {
                int numHundred = num / 100;
                sb.append(" " + map.get(numHundred) + " Hundred");
                num = num % 100;
            }

            if (num > 0) {
                if (num > 0 && num <= 20) {
                    sb.append(" " + map.get(num));
                } else {
                    int numTen = num / 10;
                    sb.append(" " + map.get(numTen * 10));

                    int numOne = num % 10;
                    if (numOne > 0) {
                        sb.append(" " + map.get(numOne));
                    }
                }
            }

            return sb.toString();
        }

        public void fillMap() {
            map.put(0, "Zero");
            map.put(1, "One");
            map.put(2, "Two");
            map.put(3, "Three");
            map.put(4, "Four");
            map.put(5, "Five");
            map.put(6, "Six");
            map.put(7, "Seven");
            map.put(8, "Eight");
            map.put(9, "Nine");
            map.put(10, "Ten");
            map.put(11, "Eleven");
            map.put(12, "Twelve");
            map.put(13, "Thirteen");
            map.put(14, "Fourteen");
            map.put(15, "Fifteen");
            map.put(16, "Sixteen");
            map.put(17, "Seventeen");
            map.put(18, "Eighteen");
            map.put(19, "Nineteen");
            map.put(20, "Twenty");
            map.put(30, "Thirty");
            map.put(40, "Forty");
            map.put(50, "Fifty");
            map.put(60, "Sixty");
            map.put(70, "Seventy");
            map.put(80, "Eighty");
            map.put(90, "Ninety");
        }
    }

    public static void main(String[] args) {

        System.out.println(numberToWords(9000000));
    }
}
