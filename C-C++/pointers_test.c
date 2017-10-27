#include<stdio.h>
int main(){
	int *i, *j;
	i = &j;
	j = &i;

	return 0;
}
