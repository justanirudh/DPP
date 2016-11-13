package com.anirudh.algos

/**
  * Created by anirudh on 6/11/16.
  */
/*
* Let c = gcd(a,b)
Then a = c*x for some x, and b = c*y for some y.
x and y are coprime by defintion of gcd.

By definition, lcm(a,b) is divisible by a=c*x and b=c*y, therefore
lcm(a,b) = c*x*y  (since x and y are coprime)

lcm(a,b)*gcd(a,b) = (c^2)*x*y = (c*x)*(c*y) = a*b
*
* */

object LCM extends App{

  def getLCMNaive(x:Int, y:Int):Int = {
    //first check if max(x,y) % min(x,y) == 0
    //else
    //do max * i; increase i from 2 to min; return the first max*i, where max*i is divisible by min as well
    //return that minimum element
    -1
  }


  //O(log(n)) solution
  def getLCM(x:Int, y:Int) = x*y/GCD.getGCD(x, y)

  val x = 28
  val y = 42
  val lcm = getLCM(x, y)
  println(lcm)
}
