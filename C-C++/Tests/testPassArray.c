#include<stdio.h>
#include<stdlib.h>
int getArraySize(int);

int main(){

unsigned long int getArraySize(int bar[][2]){
	unsigned long int size = sizeof(bar)/sizeof(bar[0]);
	return size;
};
	int foo[][2] = {{1,2},{2,3},{3,4}};
	unsigned long int size = getArraySize(foo);
	printf("Number of elements: %lu\n", size);	
	return 0;
}


