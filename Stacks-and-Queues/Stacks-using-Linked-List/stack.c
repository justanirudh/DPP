#include "stack.h"
#include<stdio.h>
#include<stdlib.h>

struct stack initializeStack(){
	struct stack s;
	s.head = NULL;
	s.size = 0;
	return s;
}

struct stackNode* createNode(int element){
	struct stackNode* s;
	s = (struct stackNode*) malloc(sizeof(struct stackNode));
	if(s == NULL){
		printf("Memory full");
		exit(0);
	}
	s->element = element;
	s->next = NULL;
	return s;
}

struct stack push(struct stack s, int element){
	struct stackNode* node = createNode(element);
	if(s.size == 0){
		s.head = node;
		s.size++;
		return s;
	}
	else{
		node->next = s.head;
		s.head = node;
		s.size++;
		return s;
	}
}

struct stack pop(struct stack s){
	if(s.size == 0){
		printf("Stack is empty. Nothing to pop");
		exit(0);
	}
	s.head = s.head->next;
	s.size--;
	return s;
}

void print(struct stack s){
	struct stackNode* temp = s.head;
	while(temp != NULL){
		printf("%d ->", temp->element);
		temp = temp->next;
	}
	printf("null\n");
}

int peek(struct stack s){
	if(s.size == 0){
		printf("Stack is empty. Nothing to peek\n");
		exit(0);
	}
	return s.head->element;
}

