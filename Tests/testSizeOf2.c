#include<stdio.h>
#include<stdlib.h>
int main(){
	int foo[][2] = {{1,2}, {3,4}};
	int* bar;
	bar = foo[0];
	int i;
	for(i=0; i<2; i++){
		//printf("%d\n", bar[i]);
	}
	printf("%lu\n", sizeof(foo));
	printf("%lu\n", (2*sizeof(int)));
	return 0;
}
