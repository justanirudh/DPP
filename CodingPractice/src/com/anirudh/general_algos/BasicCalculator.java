package com.anirudh.general_algos;

//import com.anirudh.datastructures.StringStack;

import com.anirudh.general_algos.basics.StringStack;

import java.util.ArrayList;

/**
 * Created by paanir on 12/31/16.
 */
/*
Implement a basic calculator I to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers
and empty spaces.

You may assume that the given expression is always valid.

Some examples:
 */
/*
Implement a basic calculator II to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
 */
public class BasicCalculator {

    private static ArrayList<String> evaluateDM(ArrayList<String> exp, String symbol) {
        ArrayList<String> expNew = new ArrayList<>(); //division done
        for (int i = 0; i < exp.size(); ++i) {
            String curr = exp.get(i);
            if (curr.equals(symbol)) {
                int left; //last element; if it has multiplication signs before it, evaluate that first
                //evaluating left
                Integer removeStart = null;
                if (curr.equals("/")) {
                    int mulIndex = expNew.size() - 2;
                    if (mulIndex < 0 || !expNew.get(mulIndex).equals("*"))
                        left = Integer.parseInt(expNew.get(expNew.size() - 1));
                    else {
                        while (mulIndex >= 0 && expNew.get(mulIndex).equals("*"))
                            mulIndex = mulIndex - 2;
                        left = 1; //first number
                        int numIndex = mulIndex + 1;
                        removeStart = numIndex;
                        while (numIndex != expNew.size() - 1) {
                            left *= Integer.parseInt(expNew.get(numIndex));
                            numIndex = numIndex + 2;
                        }
                        left *= Integer.parseInt(expNew.get(numIndex));
                    }
                } else
                    left = Integer.parseInt(expNew.get(expNew.size() - 1));
                int right = Integer.parseInt(exp.get(++i));
                int newVal;
                if (symbol.equals("/"))
                    newVal = left / right;
                else //*
                    newVal = left * right;
                if (removeStart != null)
                    expNew.subList(removeStart, expNew.size()).clear();
                else
                    expNew.remove(expNew.size() - 1); //removing left before adding newVal; for / and * case, remove all
                expNew.add(Integer.toString(newVal));
            } else
                expNew.add(curr);
        }
        return expNew;
    }

    private static String evaluateExpression(ArrayList<String> exp) {
        //DMAS
        //D
        ArrayList<String> expd = evaluateDM(exp, "/");
        //M
        ArrayList<String> expdm = evaluateDM(expd, "*");
        //AS
        int value = Integer.parseInt(expdm.get(0));
        int i = 1;
        while (i < expdm.size()) {
            String symbol = expdm.get(i++);
            int number = Integer.parseInt(expdm.get(i++));
            if (symbol.equals("+"))
                value += number;
            else //- sign
                value -= number;
        }
        return Integer.toString(value);
    }

    public static int calculate(String s) {
        if (!s.contains("(")) {
            int i = 0;
            ArrayList<String> exp = new ArrayList<>();
            while (i < s.length()) {
                Character c = s.charAt(i);
                String curr = Character.toString(s.charAt(i));
                if (curr.equals(" ")) {//ignoring space
                    ++i;
                } else if (Character.isDigit(c)) { //if number, consolidate the number and then push to stack
                    StringBuilder sb = new StringBuilder();
                    while (i < s.length() && Character.isDigit(s.charAt(i))) {
                        sb.append(s.charAt(i));
                        ++i;
                    }
                    exp.add(sb.toString());
                } else {
                    exp.add(curr);
                    ++i;
                }
            }
            return Integer.parseInt(evaluateExpression(exp));
        } else {
            String str = "(" + s + ")";
            StringStack stack = new StringStack();
            int i = 0;
            while (i < str.length()) {
                Character c = str.charAt(i);
                String curr = Character.toString(c);
                if (curr.equals(" ")) {//ignoring space
                    ++i;
                    continue;
                }
                if (curr.equals(")")) { //if closing bracket
                    ArrayList<String> exp = new ArrayList<>();
                    while (!stack.top().equals("("))
                        exp.add(0, stack.pop()); //way to prepend stuff
                    stack.pop(); //popped opening bracket
                    String value = evaluateExpression(exp);
                    stack.push(value);
                    ++i;
                } else if (Character.isDigit(c)) { //if number, consolidate the number and then push to stack
                    StringBuilder sb = new StringBuilder();
                    while (Character.isDigit(str.charAt(i))) {
                        sb.append(str.charAt(i));
                        ++i;
                    }
                    stack.push(sb.toString());
                } else {
                    stack.push(curr);
                    ++i;
                }
            }
            return Integer.parseInt(stack.top());
        }
    }

    public static void main(String[] args) {
        String str = "42";
        System.out.println(calculate(str));
    }
}
