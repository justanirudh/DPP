#include "queue.h"
#include<stdio.h>
#include<stdlib.h>

Queue initializeQueue(){
	Queue q;
	q.front = NULL;
	q.back = NULL;
	q.size=0;	
	return q;
}

QueueNode createQueueNode(char* element){
	QueueNode qn;
	qn = (QueueNode)malloc(sizeof(struct queueNode));
	if(qn == NULL){
		printf("No space on disk\n");
		exit(0);
	}
	qn->element = element;
	qn->next = NULL;
	return qn;	
}

Queue enqueue(Queue q, char* element){
	if(q.size == 0){
		QueueNode qn = createQueueNode(element);
		q.front = qn;
		q.back = qn;
		q.size++;
		return q;
	}
	else {
		QueueNode qn = createQueueNode(element);
		q.back->next = qn;
		q.back = qn;
		q.size++;	
		return q;
	}
}

Queue dequeue(Queue q){
	if(q.size == 0){
		printf("Queue is empty. Nothing to dequeue\n");
		exit(0);
	}
	q.front = q.front->next;
	q.size--;
	return q;
}

char* peek(Queue q){
	return q.front->element;
}

void printQueue(Queue q){
	QueueNode qn = q.front;
	while(qn != NULL){
		printf("%s ->", qn->element);
		qn = qn->next;
	}
	printf("null\n");
	return ;
}
