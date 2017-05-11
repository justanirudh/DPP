#include<stdio.h>
#include<stdlib.h>
int main(){
	int a[3] = {4,67,3};
	int* p;
	p = a;
	printf("%d\n", *(p+1));
	printf("%d\n", p[2]);
	return 0;
}
