#include "queue.h"
#include<stdio.h>

int main(){

	Queue q = initializeQueue();
	q = enqueue(q, "hello");
	q = enqueue(q, "queues0");
	printQueue(q);	
	printf("%s\n", peek(q));
	q = enqueue(q, "queues1");
	q = enqueue(q, "queues2");
	q = enqueue(q, "queues3");
	q = enqueue(q, "queues4");
	printQueue(q);
	printf("%s\n", peek(q));
	q = dequeue(q);
	q = dequeue(q);
	q = dequeue(q);
	q = dequeue(q);
	printQueue(q);
	printf("%s\n", peek(q));
	return 0;
}
