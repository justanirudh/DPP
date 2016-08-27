
struct queueNode {
	struct queueNode* next;
	char* element;
};

typedef struct queueNode* QueueNode;

struct queue {
	QueueNode front;
	QueueNode back;
	int size;			
};

typedef struct queue Queue;

Queue initializeQueue();
QueueNode createQueueNode(char*);
Queue enqueue(Queue, char*);
Queue dequeue(Queue);
void printQueue(Queue);
char* peek(Queue);
