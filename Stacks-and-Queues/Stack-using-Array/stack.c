#include<stdio.h>
#define STACKSIZE 6

struct stack {

	int container[STACKSIZE];
	int head;
};

struct stack s;

int pop();
void push(int);
int peek();
void print();

void push(int element){
	if(s.head == STACKSIZE - 1){
		printf("Stack is full.Cant push more\n");
		return;
	}
	else{
		s.head++;
		s.container[s.head] = element;
		return;
	}
};

int pop(){
	if(s.head == -1){
		printf("Nothing to pop. Stack is empty\n");
		return s.head;
	}
	else{
		int element = s.container[s.head];
		s.head--;
		return element;
	}
}

int peek(){
	if(s.head == -1){
		printf("Stack is empty\n");
		return s.head;
	}		
	else
		return s.container[s.head];
}
	
void print(){
	int i;
	for(i=s.head; i>-1; i--){
		printf("%d ->", s.container[i]);
	}
	printf("null\n");
	return ;
}


int main(){

printf("Implementing Stacks\n");

s.head= -1;

push(1);
push(2);
push(3);
push(4);
push(5);
//printf("%d\n", s.head);
//push(6);
pop();
pop();
pop();
//printf("%d\n", peek());
push(51);
push(52);
push(53);
push(54);
print();
}
