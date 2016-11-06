package com.anirudh.algos

/**
  * Created by anirudh on 4/11/16.
  */
object ReverseString extends App{

  //go through each char and prepend to a LL
  //O(n) space, O(n) time
  def revStr(str:String):String = {
    var rev:Seq[Char] = Seq()
    for(s <- str)
      rev = s +: rev //Seq impl as List which is a Linked List. prepend to it is O(1)
    rev.mkString
  }

  //change str to char array and exchange elements inplace
  //O(1) space, O(n) time
  def revStrBetter(str:String):String = {
    val size = str.length
    val arr = str.toCharArray
    for(i <- 0 until size/2){
      val temp = arr(i)
      arr(i) = arr(size - 1 - i)
      arr(size - 1 - i) = temp
    }
    arr.mkString
  }

  val str = "apples"
  println(revStrBetter(str))
}
