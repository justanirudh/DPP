package com.anirudh.leetcode.wordletter

/**
  * Created by anirudh on 16/9/16.
  */

/*Question:
* Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.*/

object Driver extends App{

  val endWord = "cog"

  def findSteps(vocab:Seq[String], queue: Seq[(String, Int)]):Int = {
    var tempVocab = vocab
    val next = queue.head
    if(next._1 == endWord)
      next._2
    else{
      val queueNew = tempVocab.foldLeft(queue.tail){ //queue.tail as deleted 1st element
        case(agg, vocabWord) => {
          //matching 2 strings character by character
          val diffs = (next._1 zip vocabWord).foldLeft(0)((diff, tup) => //zipping current word with each vocab word
            if(tup._1 == tup._2) diff else diff + 1)
          
          if(diffs != 1)
            agg
          else{
            tempVocab = tempVocab.filter(_ != vocabWord) //delete from vocab
            agg :+ (vocabWord, next._2 + 1) //add to queue
          }
        }
      }
      findSteps(tempVocab, queueNew)
    }
  }

  var vocab = Seq("hot","dot","dog","lot","log") :+ endWord
  val beginWord = "hit"
  var queue = Seq((beginWord, 1))
  val steps = findSteps(vocab, queue)

  println("No. of steps: " + steps)

}
