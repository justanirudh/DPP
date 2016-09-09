#include<stdio.h>
#include<stdlib.h>

//in-place quicksort
void quicksort(int);

int main(){

	int partition(int array[], int start, int end){
		int pivot = array[start]; //first elem pivot
		int small = start;
		int large, temp;
		for(large = start + 1; large <= end; large++){
			if(array[large] < pivot){
				small = small+1;
				temp = array[large];
				array[large] = array[small];
				array[small] = temp;
			}
		}
		temp = array[small];
		array[small] = pivot;
		array[start] = temp;
		return small;
	}

	void quicksort(int array[], int start, int end){
		//printf("%d %d", start, end);
		int part;
		if (start < end){
			part = partition(array, start, end);
			quicksort(array, start, part-1);
			quicksort(array, part + 1, end);
		}
	}

	int array[10] = {10, 5, 6, 34, 54, 32, 66, 100, 22, 1};
	int start = 0;
	int size_array  = sizeof(array)/sizeof(int);
	int i;
	quicksort(array, start, size_array - 1);
	for(i = 0; i<size_array; i++ ){
		printf("%d ", array[i]);
	}	
	return 0;
}
