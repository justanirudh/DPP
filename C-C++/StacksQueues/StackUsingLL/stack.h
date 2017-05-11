struct stackNode{
	int element;
	struct stackNode* next;
};

struct stack{
	struct stackNode* head;
	int size;
};

struct stack initializeStack();
struct stackNode* createNode(int);
struct stack push(struct stack, int );
struct stack pop(struct stack);
int peek(struct stack);
void print(struct stack);
