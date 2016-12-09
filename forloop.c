#include<stdio.h>
int main(){
	int i;
	for(i = 0; i<3; i++){
		printf("value of i: %d\n", i);
	}
	for(i = 0; i<3; ++i){
		printf("value of i: %d\n", i);
	}
	return 0;
}
