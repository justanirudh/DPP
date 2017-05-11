#include<stdio.h>
#include<string.h>
#include<stdlib.h>

//reverse a given char* string. O(n) space, O(n) time
int main(){
	char* s;
	int i, size;
	s = "apple";
	size = strlen(s);
	char* newStr = (char*)malloc(sizeof(char) * size); 
	for(i = size - 1; i >= 0; --i){
		newStr[(size - 1) - i] = s[i];
	}
	printf("%s\n", newStr);
	return 0;
}
