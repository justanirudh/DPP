#include<stdio.h>
#include<stdlib.h>
#include "graph.h"
#include "const.h"

ALnode initializeNode(int vertex){
	ALnode node = (ALnode)malloc(sizeof(struct alnode));
	if (node == NULL){
		printf("Memory allocation failure");
		exit(0);
	}
	node->vertex = vertex;
	node->next = NULL;
	return node;
};

GraphAL initializeGraphAL(int vertices){ //size is number of vertices
	int i;
	GraphAL graph = (GraphAL)malloc(sizeof(struct graphAL));
	graph->size = vertices;
	graph->root = (ALnode)malloc(vertices * sizeof(struct alnode));
	for(i = 0; i < vertices; i++){
		graph->root[i].vertex = i + 1; //As edge list has vertices starting with 1, . and not -> because root[i] is the elem of the array and not a mere pointer
		graph->root[i].explored  = false;
		graph->root[i].discoveryEdge = false;
		graph->root[i].next = NULL;
	};
	return graph;
};

GraphAL addNode(GraphAL graph, int vertex){
	ALnode n = initializeNode(vertex);
	n->next = graph->root[vertex-1].next; //adding to the start of the list as it is cheaper
	graph->root[vertex-1].next = n;
	return graph;
};

GraphAL addEdge(GraphAL graph, int* edge){
	int src = edge[0];
	int dest = edge[1];
	ALnode nDest, nSrc;
	//Add dest to src's list
	nDest = initializeNode(dest);
	nDest->next = graph->root[src-1].next; //adding to the start of the list as it is cheaper
	graph->root[src-1].next = nDest;
	//Add src to dest's list
	nSrc = initializeNode(src);
	nSrc->next = graph->root[dest-1].next; //adding to the start of the list as it is cheaper
	graph->root[dest-1].next = nSrc;
	return graph;
};

GraphAL convertELToAL(int el[][EDGESIZE], int vertices, int noRows) {
	int i;
	int* edge;
	GraphAL graph = initializeGraphAL(vertices);
	for(i = 0; i < noRows; i++){
		edge = el[i];
		graph = addEdge(graph, edge);
	}
	return graph;
};

void printPerVertexBasis(GraphAL graph){
	int i;
	ALnode n;
	for(i = 0; i < graph->size; i++){
		n = &(graph->root[i]);
		printf("|%d| -> ", n->vertex);
		n = n->next;
		while(n != NULL){
			printf("%d -> ", n->vertex);
			n = n->next;	
		}
		printf("NULL\n");
	}
};

void printDFSusingAL(GraphAL graph, int vertex){
	int newVertex;
	ALnode reverseEdge;
	ALnode edgeEnd  = graph->root[vertex-1].next;
	while(edgeEnd != NULL){
		if(edgeEnd->explored == false){
			edgeEnd->explored = true;
			//Also make the reverse edge explored			
			newVertex = edgeEnd->vertex;
			reverseEdge = graph->root[newVertex - 1].next;
			while(reverseEdge->vertex != vertex)
				reverseEdge = reverseEdge->next;
			reverseEdge->explored = true;
			if(graph->root[newVertex - 1].explored == false){
				printf(" -> %d", newVertex);
				edgeEnd->discoveryEdge = true;
				reverseEdge->discoveryEdge = true;
				graph->root[newVertex - 1].explored = true;
				printDFSusingAL(graph, newVertex);
			}
			else{
				edgeEnd->discoveryEdge = false; //back edge
				reverseEdge->discoveryEdge = false;
				edgeEnd = edgeEnd->next;
			}
		}
		else
			edgeEnd = edgeEnd->next;		
	}
};

void printDFSusingALHelper(GraphAL graph, int vertex){
	ALnode start = &(graph->root[vertex-1]);
	start->explored = true;
	printf("%d", vertex);
	printDFSusingAL(graph, vertex);
};

GraphAL dijkstraFromAVertex(GraphAL graph, int vertex){
	
};
