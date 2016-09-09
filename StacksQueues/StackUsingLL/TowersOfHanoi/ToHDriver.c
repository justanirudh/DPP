#include "towersOfHanoi.h"
//#include "../stack.h"
#include<stdio.h>
#include <stdlib.h>

int main(){

	int i;
	struct stack so, sb, sd;
	so = initializeStack();
	sb = initializeStack();
	sd = initializeStack();
	
//	so = push(so, 5);
//	so = push(so, 4);
	so = push(so, 3);
	so = push(so, 2);
	so = push(so, 1);
	
	print(so);
	print(sb);
	print(sd);
	printf("--------------------\n");
	Towers newTowers = move(3, so, sb, sd);
	printf("-------------------\n");

	print(newTowers.origin);
	print(newTowers.buffer);
	print(newTowers.destination);
	printf("Sucess\n");
	return 0;
}
