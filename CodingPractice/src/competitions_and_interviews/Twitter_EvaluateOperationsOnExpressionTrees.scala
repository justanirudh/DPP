package competitions_and_interviews

import scala.collection.mutable.ListBuffer
import scala.io.StdIn._

/**
  * Created by anirudh on 8/11/16.
  */

//Problem: Evaluate an expression tree by he given operations
object Twitter_EvaluateOperationsOnExpressionTrees extends App{

  /*
  * More testcases
  * Input:
  (AB) C ((D E) F) / SSS
  (ABC)D((GH)) /S
  (AB)(CD)E / SS
  A((BC) DEF)((GH)IJ)K /S
  AB((CD)) /R
  A((BC) DEF)((GH)I) /RRS
  (ABCD)EF /S
  AB((CDEF)GH) /SSSS

  Output:
  ABC(DEF)
  ABCD(GH)
  AB(CD)E
  A(BCDEF)(GHIJ)K
  ((DC))BA
  A(BCDEF)(GHI)
  ABCDEF
  AB(CDEFGH)
  *
  * */


  try{

    //This function checks if the next bracket from the given index is closing or not
    def getClosingIndex(exp:String, index: Int):Int = {
      if(index >= exp.length)
        -1
      else{
        val char = exp(index)
        if(char == ')' || char == '('){
          if(char == ')')
            index
          else
            -1
        }
        else
          getClosingIndex(exp, index + 1)
      }
    }

    while(true) {
      val line = readLine()
      val arr = line.replaceAll(" ", "").split("/")
      if(arr.size == 1) //no ops
        println(arr(0))
      else{
        val exp = arr(0) //expression
        val ops = arr(1) //operations
        var opsIndex = 0 //opsIndex of ops arr
        var temp = exp //temp expression
        while(opsIndex < ops.length){
          val op = ops(opsIndex)
          if(op == 'R'){ //if op is an 'R'
            if( opsIndex + 1 < ops.length && ops(opsIndex + 1) == 'R') //next op is also 'R'
              opsIndex = opsIndex + 2 //no need to do anything
            else{ //next op is not 'R'
              temp = temp.reverse
              temp = temp.map(c => if(c == '(') ')' else if(c == ')') '(' else c)
              opsIndex = opsIndex + 1
            }
          }
          else{ //op is 'S'
            while(opsIndex + 1 < ops.length && ops(opsIndex + 1) == 'S') {//consecutive S operations have same effect as one
              opsIndex = opsIndex + 1
            }
            //First looking at the case when the first element is inside parenthesis. Eg. (AB*) C
            if(temp.head == '('){ //starting element
            val parenClosesFirst = getClosingIndex(temp, 1)
              if( parenClosesFirst != -1){ //first element's paren need to be removed
              val ttemp = temp.toList.to[ListBuffer]
                ttemp.remove(0)
                ttemp.remove(parenClosesFirst - 1)
                temp = ttemp.mkString
              }
            }
            //Now looking at the case when there is a subexpression in which first element is of type (AB*)
            var tempIndex = 0
            while(tempIndex < temp.length){
              if(temp(tempIndex) == '(' && tempIndex + 1 < temp.length && temp(tempIndex + 1) == '('){ //start of a subexpression has paren
                tempIndex = tempIndex + 1 //start of the child paren
                val parenClosesFirst = getClosingIndex(temp, tempIndex + 1)
                if(parenClosesFirst != -1){
                  val ttemp = temp.toList.to[ListBuffer]
                  ttemp.remove(tempIndex)
                  ttemp.remove(parenClosesFirst - 1)
                  temp = ttemp.mkString
                  tempIndex = tempIndex + 2
                }
                else
                  tempIndex = tempIndex + 1 //normal case of not finding any valid parenthesis for simplification
              }
              else
                tempIndex = tempIndex + 1 //normal case of not finding any valid parenthesis for simplification
            }
            opsIndex = opsIndex + 1 //go to next operation
          }
        }
        println(temp)
      }
    }
  }
  catch{
    case e:Exception =>
  }
}
