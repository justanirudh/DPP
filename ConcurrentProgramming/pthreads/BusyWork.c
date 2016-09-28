#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#define NUM_THREADS 8

typedef struct { //for return values
  int threadID;
  double resultVal;
}RetVals;

void *BusyWork(void *arg) {
	int i;
	double result = 0.0;
	for (i = 0; i < 1000000; i++) {
 		result = result + (double) random();
	}
	printf("%d: Thread result = %e\n",*(int *)arg, result);
	fflush(stdout); 
	//set up return value
	RetVals *ret;
	ret = (RetVals *)malloc(sizeof(RetVals));
	ret->threadID = *(int *)arg;
	ret ->resultVal = result;
	pthread_exit((void *)ret);
}

int main(int argc, char *argv[]) {
  pthread_t thread[NUM_THREADS];
  int *taskid[NUM_THREADS];
  pthread_attr_t attr;
  int rc, t;
  RetVals * status = NULL;
  //create and initialize attribute
  pthread_attr_init(&attr);
  pthread_attr_setdetachstate(
            &attr, 
            PTHREAD_CREATE_JOINABLE);
  for (t = 0; t < NUM_THREADS; t++) {
      taskid[t] = (int *)malloc(sizeof(int));
      *taskid[t] = t;
      //create thread with attribute
      rc = pthread_create(&thread[t], &attr, 
            BusyWork, taskid[t]);
      if (rc) {printf("ERROR %d", rc); exit(-1);}
}
   for (t = 0; t < NUM_THREADS; t++) {
       rc = pthread_join(thread[t], (void *)&status);
       if (rc) {printf("ERROR %d", rc);exit(-1);}
       //get return value
       int threadId = status->threadID;
       double resultVal = status->resultVal;
       printf("Joined with thread %d result=%e\n", 
              threadId, resultVal);
   }
   //deallocate attribute
   pthread_attr_destroy(&attr);
   pthread_exit(NULL);
}
