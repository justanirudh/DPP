package com.anirudh.dynamic_programming

/**
  * Created by anirudh on 25/9/16.
  */

import scala.collection.mutable
object LongestCommonSubsequenceScala extends App{
//Dynamic Programming: Iteration/recursion + memoization

  def findLCSString(s1:String, s2:String):String = {
    val mapString = new mutable.HashMap[(String, String), String]() //hashmap

    def getOrCalculateLCS(key:(String, String)):String = {
      if(mapString.contains(key))
        mapString(key)
      else{
        val lcs = findLCSString(key._1, key._2)
        mapString.put(key, lcs)
        lcs
      }
    }

    if(s1.isEmpty || s2.isEmpty) //00 or 10 or 01
      ""
    else{
      if(s1.last == s2.last)
        getOrCalculateLCS((s1.init, s2.init)) + s1.last
      else{
        val oneOffs1 = getOrCalculateLCS((s1.init, s2))
        val oneOffs2 = getOrCalculateLCS((s1, s2.init))
        if(oneOffs1.length > oneOffs2.length)
          oneOffs1
        else
          oneOffs2
      }
    }
  }

  def findLCSSeq(s1:Seq[String], s2:Seq[String]):Seq[String] = {
    val mapSeq = new mutable.HashMap[(Seq[String], Seq[String]), Seq[String]]() //hashmap

    def getOrCalculateSeq(key:(Seq[String], Seq[String])):Seq[String] = {
      if(mapSeq.contains(key))
        mapSeq(key)
      else{
        val lcs = findLCSSeq(key._1, key._2)
        mapSeq.put(key, lcs)
        lcs
      }
    }

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

  val s1 = "ABCBDABA"
  val s2 = "BDCABA"

//  val lengths = readLine()
//  val s1 = readLine().split(" ")
//  val s2 = readLine().split(" ")
  val lcs = findLCSString(s1, s2)

  println(lcs.mkString(" "))

}
