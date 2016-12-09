#include<stdio.h>
#include<string.h>
#include<stdlib.h>

// reverse a given char* string. O(n) space, O(n) time
int main(){
	char* s;
	char temp;
	int i, size;
	s = "apples";
	size = strlen(s);
	char* mutableStr = (char*)malloc(sizeof(char) * size); 
	strcpy(mutableStr, s);
	//s[0] = 'n'; This would throw seg fault. char* strings are immutable as they directly become a read-only part of the executable post-compilation. you need to maintain data on stack or heap.Either use char[] or copy to a dynamically built char*
	for(i = 0; i < size/2; i++){
		temp = mutableStr[i];
		mutableStr[i] = mutableStr[size - 1 - i];
		mutableStr[size - 1 - i] = temp;
	}
	printf("%s\n", mutableStr);
	return 0;
}
