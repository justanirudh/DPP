struct treeNode{
	int element;
	struct treeNode* left;
	struct treeNode* right;
};

typedef struct treeNode* TreeNode;

struct tree{
	TreeNode root;
	int nodeCount;
	int height;
};

typedef struct tree Tree;

struct treeNodeAndDepth {
	TreeNode tn;
	int depth;
};

typedef struct treeNodeAndDepth TNaD;

TreeNode initializeNode(int);

Tree initializeTree();

TNaD insertNodeToTree(TNaD, int);

int max(int, int);

Tree insertNode(Tree, int);

int getHeight(Tree);

TreeNode searchNode(Tree, int);

int findDepth(Tree, int);

//void printPreOrder(Tree);

void printInOrderRec(Tree);

//void printPostOrder(Tree);

Tree deleteNode(Tree, int);

void printInOrderIter(Tree);

void printLevelOrder(Tree);
