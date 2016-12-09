package competitions

import java.text.SimpleDateFormat
import java.util.{Locale, TimeZone}

import scala.collection.SortedMap

/**
  * Created by anirudh on 6/11/16.
  */
import scala.io.StdIn._

//Problem: Given apache logs, parse them and find success rate (non-500 http codes)
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
}
