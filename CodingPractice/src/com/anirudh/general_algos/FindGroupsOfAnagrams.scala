package com.anirudh.general_algos

/**
  * Created by anirudh on 5/9/16.
  */
//Elements of programming interviews
object FindGroupsOfAnagrams extends App{

  val GoodPrimeNumber = 37
  val GoodConstantForHornerRule = 5

  /*
 * Horner's rule
 * y = 0
 * for i==n downto 0
 *    y = ai + x * y
 *    here,y is sum, x is constant and ai is the char
 * */
  def createHashcode(word:String):Int = {
    //Using Horner's rule in the sorted string, a = 41
    val sorted = word.sorted
    sorted.foldLeft(0)((sum,char) => char + (GoodConstantForHornerRule * sum))
  }

  def groupAnagrams(words:String*):Seq[Seq[String]] = {
    //Step 1: Bucket Array
    val bucketArray = Array.fill(100){Seq[String]()} //N = 100, collision resolution by chaining
    words.foldLeft(bucketArray)((arr, word) => {
      val key = createHashcode(word) //Step 2.1: hash code
      println("key: " + key)
      val index = key % GoodPrimeNumber //Step 2.2: compression
      arr.updated(index, arr(index) :+ word)
    }).toSeq.filter(_.nonEmpty)
  }

  def createHashcodeCheat(word:String):String = word.sorted

  def groupAnagramsCheat(words:String*):Map[String, Seq[String]] = { //cheating as using a map
    words.foldLeft(Map[String, Seq[String]]())((map, word) => {
      val key = createHashcodeCheat(word)
      if(map.exists(_._1 == key))
        map + (key -> (map(key) :+ word))
      else
        map + (key -> Seq(word))
    })
  }

  val groupsCheat = groupAnagramsCheat("debitcard", "elvis", "silent", "badcredit", "lives",
    "freedom", "listen", "levis", "cc", "bd").values.toSeq
  val groups = groupAnagrams("debitcard", "elvis", "silent", "badcredit", "lives", "freedom",
    "listen", "levis", "cc", "bd")

  for(group <- groups){
    print("\"")
    for(word <- group){
      print(word + " " )
    }
    print("\" ")
  }
}
