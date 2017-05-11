#include<stdio.h>
#include<stdlib.h>
int main(){

void changeArray(int* foo){
	foo[1] = 49; //actually changes array
}
	int i;
	int foo[3] = {1,2,3};
	int* bar = (int*)malloc(3 * sizeof(int));
	bar = foo;
	changeArray(bar);	
	for(i = 0; i<3; i++){
		printf("%d\n", bar[i]);
	}	
	return 0;
}
