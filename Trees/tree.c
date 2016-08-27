#include "tree.h"
#include<stdio.h>
#include<stdlib.h>

TreeNode initializeNode(int element){
	TreeNode tn = (TreeNode)malloc(sizeof(struct treeNode));
	if(tn == NULL){
		printf("Memory allocation failure");
		exit(0);
	}
	tn->element = element;
	tn->left = NULL;
	tn->right = NULL;
	return tn;
}

Tree initializeTree(){
	Tree t;
	t.root = NULL;
	t.nodeCount = 0;
	t.height = 0;
	return t;
}

TNaD insertNodeToTree(TNaD tnad, int element){
	TNaD tnadI, tnadO;
	if(tnad.tn == NULL){
		tnadO.tn = initializeNode(element);
		tnadO.depth = tnad.depth;
		return tnadO;
	}
    else if(element < (tnad.tn)->element){
		tnadI.tn = (tnad.tn)->left;
		tnadI.depth = tnad.depth + 1;
		tnadO = insertNodeToTree(tnadI, element);
		(tnad.tn)->left = tnadO.tn;
		tnad.depth = tnadO.depth;
		return tnad;
	}
	else if(element > (tnad.tn)->element){	
		tnadI.tn = (tnad.tn)->right;
		tnadI.depth = tnad.depth + 1;
		tnadO = insertNodeToTree(tnadI, element);
		(tnad.tn)->right = tnadO.tn;
		tnad.depth = tnadO.depth;
		return tnad;
	}
	else if(element == (tnad.tn)->element){
		printf("Element already exists. Won't add again");
		return tnad;	
	}
	
}

int max(int a, int b){
	if(a >= b)
		return a;
	else
		return b;
}

Tree insertNode(Tree t, int element){
	TNaD tnadInput, tnadOutput;
	tnadInput.tn = t.root;
	tnadInput.depth = 0;
	tnadOutput = insertNodeToTree(tnadInput, element);
	t.root = tnadOutput.tn;
	t.nodeCount++;
	t.height = max(t.height, tnadOutput.depth);
	return t;	
}

int getHeight(Tree t){
	return t.height;
}

TreeNode searchNodeInTree(TreeNode tn, int element){
	if(tn == NULL){
		printf("Element %d does not exist", element);
		TreeNode tnNew = initializeNode(-1);
		return tnNew;
	}
	else if(tn->element == element){
		return tn;
	}
	else if(element < tn->element){
		return searchNodeInTree(tn->left, element);
	}
	else if(element > tn->element){
		return searchNodeInTree(tn->right, element);	
	}
		
}

TreeNode searchNode(Tree t, int element){
	return searchNodeInTree(t.root, element);
}

int findDepthOfElement(TreeNode tn, int element, int depthCurrent){
	if(tn == NULL){
		printf("Element %d does not exist", element);
		return -1;
	}
	else if(tn->element == element){
		return depthCurrent;
	}
	else if(element < tn->element){
		return findDepthOfElement(tn->left, element, depthCurrent + 1);
	}
	else if(element > tn->element){
		return findDepthOfElement(tn->right, element, depthCurrent + 1);	
	}
}

int findDepth(Tree t, int element){
	return findDepthOfElement(t.root, element, 0);
}

void printInOrderRecInTree(TreeNode tn){
	if(tn == NULL){
		return;
	}
	else{
		printInOrderRecInTree(tn->left);
		printf("%d -> ", tn->element);
		printInOrderRecInTree(tn->right);
		return;
	}
}

void printInOrderRec(Tree t){
	printInOrderRecInTree(t.root);
	return;	
}
