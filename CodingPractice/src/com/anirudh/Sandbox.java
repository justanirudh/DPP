package com.anirudh;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by anirudh on 21/9/16.
 */
public class Sandbox {

    static class DayAndName {
        Integer day;
        String name;

        public DayAndName(int day, String name) {
            this.day = day;
            this.name = name;
        }
    }

    public static enum Kind {
        KW_INTEGER("integer"), KW_BOOLEAN("boolean"), KW_IMAGE("image"), KW_URL("url"), KW_FILE("file"), KW_FRAME("frame"), KW_WHILE("while"), KW_IF("if"), KW_TRUE(
                "true"), KW_FALSE("false"), OP_BLUR("blur"), OP_GRAY("gray"), OP_CONVOLVE(
                "convolve"), KW_SCREENHEIGHT("screenheight"), KW_SCREENWIDTH("screenwidth"), OP_WIDTH("width"), OP_HEIGHT("height"), KW_XLOC("xloc"), KW_YLOC("yloc"), KW_HIDE("hide"), KW_SHOW(
                "show"), KW_MOVE("move"), OP_SLEEP("sleep"), KW_SCALE("scale");

        Kind(String text) {
            this.text = text;
        }

        final String text;

        String getText() {
            return text;
        }
    }

    static class CustomComparator implements Comparator<DayAndName> {
        @Override
        public int compare(DayAndName o1, DayAndName o2) {
            int daydiff = o1.day.compareTo(o2.day);
            if (daydiff == 0) {
                return o1.name.compareTo(o2.name);
            } else
                return daydiff;
        }
    }

    enum TrafficLightColors {RED, YELLOW, GREEN}

    public static void main(String[] args) {
        TrafficLightColors t = TrafficLightColors.RED;
//        String paper = "Smith, M.N., Martin, G., Erdos, P.: Newtonian forms of prime factor matrices";
//        String[] intermed = paper.split("(?<=\\G\\w+,\\w+),");
//        for(String s: intermed){
//            System.out.println(s);
//        }
        String[] s = {"3", "30", "34", "5", "9"};
//        Arrays.sort(s, new Comparator<String>() {
//            public int compare(String o1, String o2) {
//                return (o2 + o1).compareTo(o1 + o2);
//            }
//        });
//        for(String str: s){
//
//            System.out.println(str);
////        }
//        HashMap<Integer, Integer> foo = new HashMap<>();
//        foo.put(1, 1);
//        foo.put(2,2);
//
//        HashMap<Integer, Integer> bar = new HashMap<>();
//        bar.put(1, 1);
//        bar.put(2,3);
//
//        System.out.println(foo.equals(bar));
//        String str = "1";
//        String[] ss = str.split("\\.");
//        System.out.println("ss.length = " + ss.length);
//        String a = "12.34.22.43";
//        System.out.println(a.substring(3));
//        HashMap<String, Integer> hm = new HashMap<>();
//        String[] nums = a.split("\\.");
//        for (String num:
//             nums) {
//            System.out.println(num);
//        }

//        String a = "11|Bob|BUY|100000";
//        String[] foo = a.split("\\|");
//        for (String bar: foo){
//            System.out.println(bar);
//        }
//
//
//
//        ArrayList<DayAndName> dn = new ArrayList<DayAndName>();
//        dn.add(new DayAndName(20, "a"));
//        dn.add(new DayAndName(21, "aa"));
//        dn.add(new DayAndName(22, "aa"));
//        dn.add(new DayAndName(25, "aa"));
//        dn.add(new DayAndName(20, "aa"));
//        dn.add(new DayAndName(21, "b"));
//        dn.add(new DayAndName(26, "a"));
//
//        Collections.sort(dn, new CustomComparator());
//
//        for(DayAndName obj: dn){
//            System.out.println(obj.day + " " + obj.name);
//        }
//
//        String str = "abc\ncde";
//        System.out.println(str.length());
//        for(int i = 0; i < str.length(); ++i){
//            if(str.charAt(i) == '\n')
//                System.out.println("got newline\n");
//        }
        ArrayList<Integer> arr = new ArrayList<>();
        arr.size();
        int[] foo = new int[]{1,2};
        int bar = foo.length;
        String baz = "sss";
        baz.length();

//        assertEquals(0, arr.get(0));

//        Integer.parseInt("99999999999999999999999999999999");
//        System.out.print(Long.MAX_VALUE);

        for (Kind k: Kind.values()) {
            System.err.println("map.put(\"" + k.getText()+"\",Kind." + k + ");");
        }

    }
}

