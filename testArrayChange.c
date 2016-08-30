#include<stdio.h>
struct pair{
	int vertex;
	int weight;
};
typedef struct pair Pair;
int main(){
	Pair foo[3] = {23, 45, 78};
	foo[2] = 100;
	return 0;
}
