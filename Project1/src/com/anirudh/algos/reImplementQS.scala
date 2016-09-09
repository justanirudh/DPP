import java.util.Random

//Not a QuickSort. Implement again

object QuickSort extends App {

	def quickSort(seq:Seq[Int], rand:Boolean = false):Seq[Int] = {
		
		if(seq.length == 0 || seq.length == 1)
			seq
		else {
			val last = if (rand) {
							val rand = new Random(System.currentTimeMillis())
							val random_index = rand.nextInt(seq.length)
							seq(random_index)
						}
						else seq.last
			val (less, equal, greater) = seq.foldLeft((Seq[Int](), Seq[Int](), Seq[Int]()))(
				(agg, element) => 
					if(element < last)
						(element +: agg._1, agg._2, agg._3)
					else if (element == last)
						(agg._1, element +: agg._2, agg._3)
					else
						(agg._1, agg._2, element +: agg._3)
			)
			quickSort(less, rand) ++ equal ++ quickSort(greater, rand)
		}
	
	}

	val seq = Seq(10,5,3,7,9,6,8,5,2,1,3,1)

	val sortedSeq = quickSort(seq,rand = true)

	sortedSeq foreach println

}
