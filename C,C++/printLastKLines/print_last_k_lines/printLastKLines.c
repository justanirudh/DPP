#define _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>

int
main(void)
{
    FILE *fp;
    char *line = NULL;
    size_t len = 0;
    ssize_t read;
	int klines;
	//char* filename;
	int count = 0;
	int startLine;	
	int start;

	printf("Input the number of last lines you want to print: ");
    scanf("%d", &klines);
    /*printf("Input the name of the file you want to read: ");
    scanf("%s", filename);*/

   fp = fopen("file2", "r");
    if (fp == NULL)
        exit(EXIT_FAILURE);

   while ((read = getline(&line, &len, fp)) != -1) {
		count++;
    }
	
	rewind(fp);
	if(count < klines){
		printf("Not enough lines in the file. Printing all");
		while ((read = getline(&line, &len, fp)) != -1) {
	        printf("%s", line);
    	}		
	}
	else {
		startLine = count - klines;
		start = 0;	
		while ((read = getline(&line, &len, fp)) != -1) {
        	if(start >= startLine)
				printf("%s", line);
			start++;
    	}	
	}	

   free(line);
    exit(EXIT_SUCCESS);
}
