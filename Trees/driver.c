#include<stdio.h>
#include "tree.h"
int main(){
	
	Tree t = initializeTree();
	
	t = insertNode(t, 5);
	t = insertNode(t, 3);
	t = insertNode(t, 10);
	t = insertNode(t, 2);
	t = insertNode(t, 4);
	t = insertNode(t, 7);
	t = insertNode(t, 9);
	t = insertNode(t, 8);
	t = insertNode(t, 12);
	t = insertNode(t, 15);
	t = insertNode(t, 13);
	t = insertNode(t, 0);
	t = insertNode(t, 11);
	t = insertNode(t, 6);
	
	printf("height=%d\n", getHeight(t));
	
	TreeNode tn = searchNode(t, 10);
	printf("left=%d\n", tn->left->element);
	printf("right=%d\n", (*tn).right->element);
	
	printf("Depth of 15 is %d\n", findDepth(t, 15));
	
	printInOrderRec(t);
	printf("\n");

	printf("Is the tree balanced?: %d\n", isBalancedInitializer(t));
	return 0;
}
