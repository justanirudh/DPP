package com.anirudh.hackerrank

/**
  * Created by anirudh on 25/9/16.
  */

import scala.collection.mutable
import scala.io.StdIn._
object LongestCommonSubsequence extends App{
//Dynamic Programming: Iteration/recursion + memoization

  val mapString = new mutable.HashMap[(String, String), String]() //hashmap
  val mapSeq = new mutable.HashMap[(Seq[String], Seq[String]), Seq[String]]() //hashmap

  def getOrCalculate(key:(String, String)):String = {
    if(mapString.contains(key))
      mapString(key)
    else{
      val lcs = findLCSString(key._1, key._2)
      mapString.put(key, lcs)
      lcs
    }
  }

  def getOrCalculateSeq(key:(Seq[String], Seq[String])):Seq[String] = {
    if(mapSeq.contains(key))
      mapSeq(key)
    else{
      val lcs = findLCSSeq(key._1, key._2)
      mapSeq.put(key, lcs)
      lcs
    }
  }

  def findLCSString(s1:String, s2:String):String = {
    if(s1.isEmpty || s2.isEmpty) //00 or 10 or 01
      ""
    else{
      if(s1.last == s2.last)
        getOrCalculate((s1.init, s2.init)) + s1.last
      else{
        val oneOffs1 = getOrCalculate((s1.init, s2))
        val oneOffs2 = getOrCalculate((s1, s2.init))
        if(oneOffs1.length > oneOffs2.length)
          oneOffs1
        else
          oneOffs2
      }
    }
  }

  def findLCSSeq(s1:Seq[String], s2:Seq[String]):Seq[String] = {
    if(s1.isEmpty || s2.isEmpty) //00 or 10 or 01
      Seq()
    else{
      if(s1.last == s2.last)
        getOrCalculateSeq((s1.init, s2.init)) :+ s1.last
      else{
        val oneOffs1 = getOrCalculateSeq((s1.init, s2))
        val oneOffs2 = getOrCalculateSeq((s1, s2.init))
        if(oneOffs1.length > oneOffs2.length)
          oneOffs1
        else
          oneOffs2
      }
    }
  }

//  val s1 = "ABCBDAB"
//  val s2 = "BDCABA"

  val lengths = readLine()
  val s1 = readLine().split(" ")
  val s2 = readLine().split(" ")
  val lcs = findLCSSeq(s1, s2)

  println(lcs.mkString(" "))

}
