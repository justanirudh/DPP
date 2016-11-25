package com.anirudh.leetcode

/**
  * Created by anirudh on 21/10/16.
  */

/*
* The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display
* this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the a method convert("PAYPALISHIRING", 3) which returns "PAHNAPLSIIGYIR".
* */
object ZigZag extends App{
  //Note: can also be done  by analysing the gaps between letters in each row: O(n) space and O(n) time, I think
  def getZigZagged(str:String, numRows:Int):String = { //O(nk) time and O(nk) space
    val strLen = str.length
    val twoDArray = Array.ofDim[Char](numRows, strLen)
    var goingDown = true
    var i = 0
    var j = 0
    var strptr = 0
    while(strptr != strLen){
      if(goingDown){
        while(strptr != strLen && i!= numRows - 1){ //go down until you hit the last row
          twoDArray(i)(j) = str(strptr)
          strptr = strptr + 1
          i = i + 1
        }
        if(strptr != strLen){ //last row
          twoDArray(i)(j) = str(strptr)
          goingDown = false
          strptr = strptr + 1
        }
      }
      else{
        while(strptr != strLen && i != 0){ // go up until you hit the first row
          j = j + 1
          i = i - 1
          twoDArray(i)(j) = str(strptr)
          strptr = strptr + 1
        }
        if(strptr != strLen){
          i = i + 1
          goingDown = true
        }
      }
    }
    //now traverse the entire matrix and create the string
    var result = ""
    for(row <- twoDArray.indices){
      for(col <- twoDArray.head.indices){
        result = result + twoDArray(row)(col)
      }
    }
    result
  }

  val str = "PAYPALISHIRING"
  val numRows = 3
  val zigzagged = getZigZagged(str, numRows)
  println(zigzagged)
}
