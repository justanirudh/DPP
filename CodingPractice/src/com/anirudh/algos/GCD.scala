package com.anirudh.algos

/**
  * Created by anirudh on 6/11/16.
  */
object GCD extends App{

  //O(n) solution
  def getGCDNaive(num1:Int, num2:Int):Int = { //works with HackerRank
    val max = if(num1 > num2) num1 else num2
    val min = if(num1 < num2) num1 else num2
    if(num1 == num2)
      num1
    else {
      if(max % min == 0) //includes one of them being 1 or 0
        min
      else {
        def getGCD(curr:Int):Int = if(num1%curr ==0 && num2%curr == 0) curr else getGCD(curr - 1)
        getGCD(min/2) //start decreasing from min/2
      }
    }
  }

  //O(log(n)) solution
  def getGCD(x:Int, y:Int):Int = if(x == 0) y else getGCD(y%x, x)

  val num1 = 42
  val num2 = 28
  println(getGCDNaive(num1, num2))

}
