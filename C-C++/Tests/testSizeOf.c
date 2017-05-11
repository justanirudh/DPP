#include<stdio.h>
#include<stdlib.h>
int main(){
	int a[3] = {1,2,3};
	int *p;
	int i;
	p = a;
	/*for(i = 0; i<3; i++){
	printf("%d\n", p[i]);
	}*/
	i = sizeof(p)/sizeof(int);
	printf("%d\n", i);
	return 0;
}
