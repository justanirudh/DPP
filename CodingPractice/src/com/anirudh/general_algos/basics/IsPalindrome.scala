package com.anirudh.general_algos.basics

/**
  * Created by anirudh on 20/10/16.
  */
/*
* Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example, "Red rum, sir, is murder" is a palindrome, while "Programcreek is awesome" is not.
* */
object IsPalindrome extends App{

  def isPalindrome(str:String):Boolean =
    if(str.isEmpty)
      true
    else{
      val lc = str.toLowerCase() //ignore cases
      val alphanumeric = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')
      val anlc = lc.filter(c => alphanumeric.contains(c)) //only take alphanumeric symbols
      val rev = anlc.reverse
      rev == anlc
    }

  //less library funcs
  def isPalindromeLessLibs(str:String):Boolean = {
    if (str.isEmpty)
      true
    else{
      val lc = str.toLowerCase() //ignore cases
      val alphanumeric = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')
      val anlc = lc.filter(alphanumeric.contains(_)) //only take alphanumeric symbols

      def isPalin(start:Int, end:Int):Boolean = {
        if(start < end){
          if(anlc(start) == anlc(end))
            isPalin(start + 1, end - 1)
          else
            false
        }
        else
          true
      }
      isPalin(0, anlc.length - 1)
    }
  }

  //even less library funcs
  def isPalindromeEvenLessLibs(str:String):Boolean = {
    if (str.isEmpty)
      true
    else{
      val lc = str.toLowerCase() //ignore cases
      val alphanumeric = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')

      def isPalin(start:Int, end:Int):Boolean = {
        if(start < end){
          if(!alphanumeric.contains(lc(start)) && !alphanumeric.contains(lc(end)))
            isPalin(start + 1, end - 1)
          else if(alphanumeric.contains(lc(start)) && !alphanumeric.contains(lc(end)))
            isPalin(start, end - 1)
          else if (!alphanumeric.contains(lc(start)) && alphanumeric.contains(lc(end)))
            isPalin(start + 1, end)
          else{
            if(lc(start) == lc(end))
              isPalin(start + 1, end - 1)
            else
              false
          }
        }
        else
          true
      }
      isPalin(0, lc.length - 1)
    }
  }

  val str = "Red rum, sir, is murder"
  val str2 = "Programcreek is awesome"

  val is = isPalindromeEvenLessLibs(str)
  println(is)

}
