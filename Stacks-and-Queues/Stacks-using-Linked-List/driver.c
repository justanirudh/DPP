#include<stdio.h>
#include "stack.h"
int main (){
	struct stack S = initializeStack();
	S = push(S, 1);
	S = push(S, 2);
	S = push(S, 4);
	S = push(S, 8);
	print(S);
	
	printf("top -> %d\n", peek(S));
	S = pop(S);
	print(S);
	S = pop(S);
	S = pop(S);
	S = pop(S);	
	S = pop(S);
	return 0;
}
