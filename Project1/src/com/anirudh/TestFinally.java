package com.anirudh;

/**
 * Created by anirudh on 19/7/16.
 */
//In both cases finally is executed before try/catch returns
public class TestFinally {
    public String lem(){
        System.out.println("lem");
        return "return from lem";
    }

    public String foo(){
        int x = 5;
        int y = 10;
        try{
            System.out.println("try started");
            int z = x/y;
            System.out.println(z + " and try ended");
            return "returned from try";
        }
        catch(Exception ex){
            System.out.println("catch started");
            String z = lem();
            System.out.println(z + " and catch ended");
            return "returned from catch";
        }
        finally {
            System.out.println( "in finally");
        }
    }

    public static void main(String[] args) {
        TestFinally t = new TestFinally();
        String foo = t.foo();
        System.out.println(foo);
    }
}
