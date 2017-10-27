#include<stdio.h>
#include<stdlib.h>

int* assign();
int* assign() {
		int foo = 5;
		int* p = &foo;
		return p;
}
int main(){

	int* p = assign();
	printf("%d", *p);

	return 0;
}
