#include "const.h"
typedef enum { false, true } bool;

struct alnode{
	int vertex;
	bool explored;
	bool discoveryEdge; //if false, back edge
	struct alnode* next;
};

typedef struct alnode* ALnode;

struct graphAL{
	int size;
	ALnode root; //head of the array of linked lists
};

typedef struct graphAL* GraphAL; //also a pointer as easier to pass pointer than copy the whole structure

//int** convertELToAM(int**);

ALnode initializeNode(int);

GraphAL initializeGraphAL(int); //inputting size of graph

GraphAL addNode(GraphAL graph, int vertex);

GraphAL addEdge(GraphAL, int*); //inputting  the graph and an edge (array of 2 vertices)

GraphAL convertELToAL(int(*)[EDGESIZE], int, int); //edge list and number of vertices

void printDFSusingAL(GraphAL, int); // graph pointer and starting vertex

void printDFSusingALHelper(GraphAL, int);
//void printBFSusingAL(GraphAL);

void printPerVertexBasis(GraphAL);

GraphAL dijkstraFromAVertex(GraphAL, int);

