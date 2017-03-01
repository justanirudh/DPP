#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

#define NUM_THREADS 8

void *PrintGoGators(void *ID){
  printf("%d: Go Gators!!\n",
          *(int *)ID);
}

int main(int argc, char *argv[]) {
     pthread_t pt1, pt2;
     int rc1, rc2, t1, t2;

			t1 = 0;
			t2 = 1;
		  rc1 = pthread_create(&pt1, NULL, PrintGoGators, (void*)&t1);
			rc2 = pthread_create(&pt2, NULL, PrintGoGators, (void*)&t2);

     /*for(t=0;t<NUM_THREADS;t++)
     {  printf("Creating thread %d\n", t);
          rc = pthread_create(
             &threads[t],
             NULL, 
             PrintGoGators, 
             (void *)&t);
          if (rc){
               printf("ERROR %d",rc);
               exit(-1);
          }
      }*/
      pthread_exit(NULL);
}
