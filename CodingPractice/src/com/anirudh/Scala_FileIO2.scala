package com.anirudh

import scala.io.StdIn._

/**
  * Created by anirudh on 18/9/16.
  */
object FileIO2 extends App{

  def isPalindrome(str:String):Boolean = {
    str == str.reverse
  }

  def findSteps(str:String, count:Int):Int = {
    val strLength = str.length
    val strStripped = str.replaceFirst("^0+(?!$)", "")
    val strInc = (strStripped.toInt + 1).toString
//    println(strInc)
    val lengthDiff = strLength - strInc.length
    val (isPal, newStr) =
      if(lengthDiff == 0)
        (isPalindrome(strInc), strInc)
    else{
        val zeroes = (1 to lengthDiff).foldLeft("")((agg, num) => agg + "0")
        val newStr = zeroes + strInc.toString
        (isPalindrome(newStr), newStr)
      }
//    println(isPal)
    if(isPal)
      count
    else
      findSteps(newStr, count + 1)
  }

  var line:String = ""
  def readStuff:Unit =
    while ({ line = readLine() ; line != "0" } ) {
      if(isPalindrome(line))
        println(0)
      else{
        println(findSteps(line, 1))
      }
    }
  readStuff


//  println(findSteps("100000", 1))


}
