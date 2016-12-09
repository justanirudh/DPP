#include<stdio.h>
#include<stdlib.h>
int main(){
	
	char* s = "Hello World\n";
//	printf("%s", s);
//	s[0] = 'h';
	//printf("%s", s); gives seg fault as the memory is read-only
	char* s2 = malloc(20);
	sprintf(s2, "Hello World 2\n");
	printf("%s", s2);
	s2[0] = 'h';
	printf("%s", s2);
	return 0;
}
