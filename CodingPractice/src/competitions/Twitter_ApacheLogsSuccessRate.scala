package competitions

import java.text.SimpleDateFormat
import java.util.{Locale, TimeZone}

import scala.collection.SortedMap

/**
  * Created by anirudh on 6/11/16.
  */
import scala.io.StdIn._
object Twitter_ApacheLogsSuccessRate extends  App{

  //map from date-time to endpint to percentage
  var map:SortedMap[String, SortedMap[String, (Int, Int)]] = SortedMap[String, SortedMap[String, (Int, Int)]]()

  try{
    def getcdt(dt:String) = {
      val format = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH)
      val date =  format.parse(dt)
      val newFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
      newFormat.setTimeZone(TimeZone.getTimeZone("GMT"))
      newFormat.format(date)
    }

    while (true) {
      val line = readLine()
      val lineArr = line.split(" ")
      val dateAndTime = lineArr(3).tail  + " " + lineArr(4).init
      val endpoint = lineArr(6).split("json")(0) + "json"
      val code = lineArr(8).toInt
      val correctDateAndTime = getcdt(dateAndTime)

      if(map.contains(correctDateAndTime)){
        var ep = map(correctDateAndTime)
        if(ep.contains(endpoint)){
          ep = ep + (endpoint -> (if(code == 500) ep(endpoint)._1 else ep(endpoint)._1 + 1, ep(endpoint)._2 + 1))
        }
        else{
          ep = ep + (endpoint -> ( if(code == 500) 0 else 1, 1))
        }
        map = map + (correctDateAndTime -> ep)
      }
      else{
        map = map + (correctDateAndTime -> SortedMap(endpoint -> ( if(code == 500) 0 else 1, 1)))
      }
    }
  }
  catch{
    case e:Exception =>

  }
  finally {
    for(m <- map){
      for(mm <- m._2){
        println(m._1 + " " + mm._1 + " " + BigDecimal((mm._2._1.toDouble/mm._2._2.toDouble) * 100).setScale(2))
      }
    }
  }

  /*def getcdt(dt:String) = {
    val format = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH)
    val date =  format.parse(dt)
    val newFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
    newFormat.setTimeZone(TimeZone.getTimeZone("GMT"))
    newFormat.format(date)
  }

  //map from date-time to endpint to percentage
  var map:SortedMap[String, SortedMap[String, (Int, Int)]] = SortedMap[String, SortedMap[String, (Int, Int)]]()

  val filename = "/home/anirudh/Code/twitter/input001.txt"
  for (line <- Source.fromFile(filename).getLines()) {
    val lineArr = line.split(" ")
    val dateAndTime = lineArr(3).tail  + " " + lineArr(4).init
    val endpoint = lineArr(6).split("json")(0) + "json"
    val code = lineArr(8).toInt
    val correctDateAndTime = getcdt(dateAndTime)

    if(map.contains(correctDateAndTime)){
      var ep = map(correctDateAndTime)
      if(ep.contains(endpoint)){
        ep = ep + (endpoint -> (if(code == 500) ep(endpoint)._1 else ep(endpoint)._1 + 1, ep(endpoint)._2 + 1))
      }
      else{
        ep = ep + (endpoint -> ( if(code == 500) 0 else 1, 1))
      }
      map = map + (correctDateAndTime -> ep)
    }
    else{
      map = map + (correctDateAndTime -> SortedMap(endpoint -> ( if(code == 500) 0 else 1, 1)))
    }
  }


  for(m <- map){
    for(mm <- m._2){
      println(m._1 + " " + mm._1 + " " + BigDecimal((mm._2._1.toDouble/mm._2._2.toDouble) * 100).setScale(2))
    }
  }*/

//  val line = "10.10.10.10 - - [27/Sep/2016:05:22:00 +0000] \"GET /1.1/friendships/list.json?user_id=123 HTTP/1.1\" 500 563 19 \"Twitter-iPhone/6.63 iOS/10.0.2 (Apple;iPhone7,2;;;;;1)\" 177.177.177.177"
  /*val lineArr = line.split(" ")
  val dateAndTime = lineArr(3).tail  + " " + lineArr(4).init
  val endpoint = lineArr(6).split("json")(0) + "json"
  val code = lineArr(8).toInt
  println(dateAndTime, endpoint, code)

  val correctDateAndTime = getcdt(dateAndTime)

  //map from date-time to endpint to percentage
  var map:SortedMap[String, SortedMap[String, (Int, Int)]] = SortedMap[String, SortedMap[String, (Int, Int)]]()
  if(map.contains(correctDateAndTime)){
    var ep = map(correctDateAndTime)
    if(ep.contains(endpoint))
      ep = ep + (endpoint -> (if(code == 500) ep(endpoint)._1 else ep(endpoint)._1 + 1, ep(endpoint)._2 + 1))
  }
  else{
    map = map + (correctDateAndTime -> SortedMap(endpoint -> ( if(code == 500) 0 else 1, 1)))
  }
*/
  /*for(m <- map){
    println(m._1 + " -> ")
    for(mm <- m._2){
      println(mm._1 + " -> " + mm._2._1 + " out of " + mm._2._2)
    }
    println("-------")
  }*/



}
