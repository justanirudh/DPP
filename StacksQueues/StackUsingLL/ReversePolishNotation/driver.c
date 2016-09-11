#include<stdio.h>
#include "../stack.h"
#include<stdbool.h>
#include<stdlib.h>
#include<unistd.h>
int main(){

	//char* rpn2[] = {"2", "1", "+", "3", "*"};
	//char* rpn[] = {"4", "13", "5", "/", "+"};	
	//int tokenSize = 5;

	char* rpn[] = {"3","-4","+"};
	int tokenSize = 3; 

	int j;
		bool isSymbol(char* c) {
		char* symbols[] = {"+", "-", "*", "/"};
		int i;
		for(i = 0; i<4; i++){
			if(symbols[i] == c)
				return true;
		}
		return false;
	}
	
	struct stack S = initializeStack();
	
	for(j = 0; j<tokenSize; j++){
		char* curr = rpn[j];
		if(!isSymbol(curr))
			S = push(S, atoi(curr));
		else{
			int num2 = peek(S);
			S = pop(S);
			int num1 = peek(S);
			S = pop(S);
			char tmp = *curr;
			int newVal;
			switch(tmp){
			case '+':
				newVal = (num1 + num2);
				break;
			case '-':
				newVal = (num1 - num2);
				break;
			case '*':
				newVal = (num1 * num2);
				break;
			case '/':
				newVal = (num1 / num2);
				break;
			default:
				printf("Unknown symbol: %c",  tmp);
				exit(1);
			}
			S = push(S, newVal);	
		} 
	}

	int finalVal = peek(S);
	printf("Value of expression:%d\n ", finalVal);	
	return 0;
}
