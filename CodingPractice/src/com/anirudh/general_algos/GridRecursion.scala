object GridRecursion extends App{
	
	def printRow(cols:Int):Unit = {
		if(cols > 0){
			print("$")
			printRow(cols - 1)
		}
	}
	
	def printGrid(rows:Int, cols:Int):Unit = {
		
		if(rows > 0){
			printRow(cols)
			println()
			printGrid(rows - 1, cols)
		}
	}

	val rows = 6
	val cols = 5
	printGrid(rows, cols)
} 
