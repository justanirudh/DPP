package com.anirudh.general_algos

object ModifiedBinarySearch extends App {
	
	def findElement(seq:Seq[String], elem:String, start:Int, end:Int):Int = {
		def getRealMid(mid:Int):Int = {
			def spread (left:Int, right:Int):Int = {
				if(left < start && right > end)
					throw new Exception("no such element")
				else if (left >= start && seq(left) != "")
					left
				else if (right <= end && seq(right) != "")
					right
				else spread (left-1, right+1)

			}
			if(seq(mid) != "")
				mid
			else
				spread(mid-1, mid+1)
		}

		if(start > end)
			throw new Exception("not found")
		
		val mid = (start + end)/2
		val realMid = getRealMid(mid)
		if(elem == seq(realMid))
			realMid
		else if (elem < seq(realMid))
			findElement(seq, elem, start, realMid - 1)
		else if (elem > seq(realMid))
			findElement(seq, elem, realMid + 1, end)
		else
			throw new Exception(elem + " does not exist")		
	}

	val seq = Seq("at", "", "", "", "ball", "", "", "car", "", "", "dad", "", "")
	val seq2 = Seq("at", "ball", "bbgun", "car", "cast", "cat", "dad", "effort")
	val seq3 = Seq("at", "ball", "car", "dad", "effort")
	val index = findElement(seq, "ball", 0, seq.length - 1)
	println(index)
}
