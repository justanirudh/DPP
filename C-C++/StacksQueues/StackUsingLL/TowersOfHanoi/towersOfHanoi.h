#include "../stack.h"

struct towers {

	struct stack origin;
	struct stack buffer;
	struct stack destination;
};

typedef struct towers Towers;

Towers move(int, struct stack, struct stack, struct stack);

Towers moveTop(struct stack, struct stack, struct stack);

