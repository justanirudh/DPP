#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

#define NUM_THREADS 8

void *PrintGoGators(void *ID){
  printf("%d: Go Gators!!\n",
          *(int *)ID);
}

int main(int argc, char *argv[]) {
     pthread_t threads[NUM_THREADS];
     int rc, t;
     for(t=0;t<NUM_THREADS;t++)
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
      }
      pthread_exit(NULL);
}
