#include<stdio.h>
#include<stdlib.h>
int main(){
	int A[5] = {1,3,5,7,9};
	int B[7] = {2,4,8,9,11,25,30};
	//int* C = (int*)malloc(sizeof(A)/sizeof(int));
	int C[12];
	int Atruesize = 5;//sizeof(A)/sizeof(int);
	int Bsize = 7;//sizeof(B)/sizeof(int);
	int mergeRange;
	int i, j, k = 0;
	int sizeDiff;

	/*while(A[i] != 0){
		Atruesize++;
		i++;
	}*/
	printf ("%d\n", Atruesize);
	printf("%d\n", Bsize);
	if(Atruesize < Bsize){
		mergeRange = Atruesize;
	}
	else{
		mergeRange = Bsize;
	}

	i = 0;j = 0; k = 0;
	while(mergeRange != 0){
		if(A[i] < B[j]){
			C[k] = A[i];
			i++;	printf("i = %d\n", i);fflush(stdout);

		}
		else if(B[j] < A[i]){
			C[k] = B[j];
			j++;printf("j = %d\n", j);fflush(stdout);
		}
		else{
			C[k] = A[i];
			k++;
			C[k] = B[j];
			i++;
			j++;
		}
printf("i, j %d %d\n", i, j);fflush(stdout);
		k++;
printf("k = %d\n", k);fflush(stdout);
		mergeRange--;	
	}	

	if(Atruesize < Bsize){
		for(i = Atruesize; i < Bsize; i++){
			C[i] = B[i];
		}	
	}
	else {
		for(i = Bsize; i < Atruesize; i++){
			C[i] = A[i];
		}	
	}

for(i = 0; i<12; i++){
		printf("%d\n", C[i]);
	}
	return(0);

}
