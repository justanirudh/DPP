#include<stdio.h>
#include "graph.h"
#include<stdlib.h>

int main(){

 	int el[][EDGESIZE] = {{1, 2,1},
	{1, 5,1},
	 {1, 6,1},	
	 {2, 3,1},
	 {2, 6,1},
	 {3, 4,1},
	 {3, 7,1},
	 {4, 7,1},
	 {4, 8,1},
	 {5, 6,1},
	 {5, 9,1},
	 {7, 10,1},
	 {7, 12,1},
	{8, 12,1},
	{6, 9,1},
	 {9, 10,1},
	 {9, 13,1},
	 {10, 11,1},
	 {7, 11,1},
	 {9, 14,1},
	 {11, 14,1},
	 {12, 16,1},
	 {13, 14,1},
	 {15, 16,1},
	 {11, 15,1}};

	long unsigned int noRows = sizeof(el)/sizeof(el[0]);
	GraphAL graphAL = convertELToAL(el, 16, noRows); //Need to pass number of rows as an argument as it cannot be calculated in compile time (http://stackoverflow.com/questions/6934776/c-getting-the-row-size-of-a-multidimensional-array-passed-to-a-function)  	
	printPerVertexBasis(graphAL);
	printDFSusingALHelper(graphAL, 1);
	printf("\n");

	int elWeighted[][EDGESIZE] = {
		{1,8,2704},	
{1,2,867},
{1,4,187},
{1,6,1258},
{2,3,849},
{2,4,740},
{2,5,621},
{2,7,802},
{2,8,1846},
{3,4,144},
{4,5,184},
{4,6,1090},
{4,7,1391},
{5,6,946},
{6,7,1121},
{6,9,2342},
{7,8,1464},
{7,9,1235},
{8,9,337}};

	return 0;
}
