#include<stdio.h>
#include<stdlib.h>
#include "graph.h"
#include "const.h"

ALnode initializeNode(int vertex, int weight){
	ALnode node = (ALnode)malloc(sizeof(struct alnode));
	if (node == NULL){
		printf("Memory allocation failure");
		exit(0);
	}
	node->vertex = vertex;
	node->weight = weight; // explored, discoveryEdge and proximity dont matter
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
		graph->root[i].discoveryEdge = false; //weights and proximity dont matter
		graph->root[i].next = NULL;
	};
	return graph;
};

GraphAL addEdge(GraphAL graph, int* edge){
	int src = edge[0];
	int dest = edge[1];
	int weight = edge[2];
	ALnode nDest, nSrc;
	//Add dest to src's list
	nDest = initializeNode(dest, weight);
	nDest->next = graph->root[src-1].next; //adding to the start of the list as it is cheaper
	graph->root[src-1].next = nDest;
	//Add src to dest's list
	nSrc = initializeNode(src, weight);
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

void printDFSusingALInitiator(GraphAL graph, int vertex){
	ALnode start = &(graph->root[vertex-1]);
	start->explored = true;
	printf("%d", vertex);
	printDFSusingAL(graph, vertex);
};

VaP findAndExploreMinimum(VaP infoVP, int size){
	int i, j, minIndex, minProx, minVertx;
	VaP min = (VaP)malloc(sizeof(struct vertexandproximity));
	for(i = 0; i<size; i++){ //finding base case to compare to
		if(infoVP[i].explored == false){
			minProx = infoVP[i].proximity;
			break;
		}
	}
	if(i == size) //All graph has been explored
		return NULL;
	minIndex = i;
	for(j = i+1; j<size; j++){ //finding minimum
		if(infoVP[j].explored == false && infoVP[j].proximity < minProx){
			minProx = infoVP[j].proximity;
			minIndex = j; 
		}
	}
	infoVP[minIndex].explored = true;
	min->vertex = infoVP[minIndex].vertex;
	min->proximity = minProx;
	return min;
};

VaP dijkstraFromAVertex(GraphAL graph, VaP infoVP){
	int i, vertex;
	ALnode node;
	EaI ei;
	int verticesSize = graph->size;
	VaP min = findAndExploreMinimum(infoVP, verticesSize);
	if(min == NULL) // All vertices explored
		return infoVP;
	node = &(graph->root[(min->vertex) - 1]);
	while(node->next != NULL){
		node = node->next;
		vertex = node->vertex;
		if( infoVP[vertex-1].explored == false){ //vertex is just index + 1
			if(min->proximity + node->weight < infoVP[vertex-1].proximity)
				infoVP[vertex-1].proximity = min->proximity + node->weight; //relaxation
		}
	}
	return dijkstraFromAVertex(graph, infoVP);
};

VaP dijkstraFromAVertexInitiator(GraphAL graph, int vertex){
	int vertices = graph->size;
	//Using an array. This will take O(n) time . Using Heap will take O(logn) time.
	VaP infoVP = (VaP)malloc(vertices * sizeof(struct vertexandproximity));
	int i;
	for(i = 0; i<vertices; i++){
		infoVP[i].vertex = graph->root[i].vertex;
		infoVP[i].proximity = HUGE_NUMBER;
		infoVP[i].explored = false;
	}
	infoVP[vertex-1].proximity = 0;
	return dijkstraFromAVertex(graph, infoVP);
};

void printDijkstra(GraphAL graph, int vertex){
	VaP infoVP = dijkstraFromAVertexInitiator(graph, vertex);
	int verticesSize = graph->size;
	int i;
	for(i = 0; i<verticesSize; i++){
		printf("Shortest distance of %d from %d is %d\n", infoVP[i].vertex, vertex, infoVP[i].proximity);
	}
};
