object Mergesort extends App{

def merge(left:Seq[Int], right:Seq[Int]):Seq[Int] = {
	(left, right) match {
		case (Nil, Nil) => Nil
		case (x, Nil) => x
		case (Nil, y) => y
		case (x::xx, y::yy) => {
			if(x < y)
				x +: merge(xx, right)
			else
				y +: merge(left, yy)
		}
	}	
}



def mergeSort(array:Seq[Int]):Seq[Int] = {

	if(array.length == 0 || array.length == 1)
		array
	else{
		val half = array.length/2
		val (left, right) = array.splitAt(half)
		val sortedLeft = mergeSort(left)
		val sortedRight = mergeSort(right)
		merge(sortedLeft, sortedRight)
	}
	
}

val array = Seq(10,4,7,3,5,2,6,8,9,1)

val sortedArray = mergeSort(array)

sortedArray foreach println

}
