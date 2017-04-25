package com.anirudh.matrix

/**
  * Created by paanir on 4/24/17.
  */
object FindElemInMatrix extends App {

	def findElement(matrix:Seq[Seq[Int]], elem:Int, r:Int, c:Int):(Int, Int) = {
		if(r < 0 || c >= matrix(0).length )
			throw new Exception(elem + " does not exist")
		if(elem == matrix(r)(c))
			(r,c)
		else if(elem < matrix(r)(c))
			findElement(matrix, elem, r-1, c)
		else
			findElement(matrix, elem, r, c+1)
	}

	val matrix = Seq(Seq(15,20,40,85), Seq(20,35,85,95), Seq(30,55,95,105), Seq(40,80,100,120))

	//start from bottom-left or top-right corner as decision making is better at those points
	val indices = findElement(matrix, 95, matrix.length - 1, 0)
	println("row no: " + indices._1 + " col no: " + indices._2)

}
