package competitions_and_interviews.ufpt_tryouts_09182016

import scala.io.StdIn._

/**
  * Created by anirudh on 18/9/16.
  */


/*Description
standard input/output
Statements
Cinderella is given a task by her Stepmother before she is allowed to go to the Ball. There are N (1 ≤ N ≤ 1000) bottles with water in the kitchen. Each bottle contains Li (0 ≤ Li ≤ 106) ounces of water and the maximum capacity of each is 109 ounces. To complete the task Cinderella has to pour the water between the bottles to fill them at equal measure.

Cinderella asks Fairy godmother to help her. At each turn Cinderella points out one of the bottles. This is the source bottle. Then she selects any number of other bottles and for each bottle specifies the amount of water to be poured from the source bottle to it. Then Fairy godmother performs the transfusion instantly.

Please calculate how many turns Cinderella needs to complete the Stepmother's task.

Input
The first line of input contains an integer number N (1 ≤ N ≤ 1000) — the total number of bottles.

On the next line integer numbers Li are contained (0 ≤ Li ≤ 106) — the initial amount of water contained in ith bottle.

Output
Output a single line with an integer S — the minimal number of turns Cinderella needs to complete her task.

Sample Input
Input
3
5 7 7
Output
2
Input
3
21 10 2012
Output
1
Input
1
100
Output
0*/
object Cinderella extends App{

  val bottles = readLine()
  val quants = readLine().split(" ").map(_.toInt).toSeq
  val avg = quants.foldLeft(0)((agg, quant) => agg + quant)/quants.size
  val number = quants.foldLeft(0)((agg, quant) => if(quant > avg) agg + 1 else agg)
  println(number)

}
