#include<stdio.h>
#include<stdlib.h>
#include "towersOfHanoi.h"

Towers move(int discs, struct stack origin, struct stack buffer, struct stack destination){
	if(discs == 0){	
		
		Towers towers;
		printf("If: no. of discs is 0\n");
		towers.origin = origin;
		towers.buffer = buffer;
		towers.destination = destination;
		return towers;
	}
	else if(discs == 1){
		
		printf("Else if: no. of discs is 1\n");
		Towers towers = moveTop(origin, buffer, destination);
		return towers;
	}
	else{	

		Towers tr1, tr2, tr3;

		printf("Else: no. of discs is %d\n", discs);
		print(origin);
		print(buffer);
		print(destination);
		printf("---------------------\n");

		Towers tf1 = move(discs - 1, origin, destination, buffer);
		tr1.origin = tf1.origin;
		tr1.buffer = tf1.destination;
		tr1.destination = tf1.buffer;  //towers adjusted: swapped destination and buffer

		printf("Towers first\n");
		print(tr1.origin);
		print(tr1.buffer);
		print(tr1.destination);
		printf("---------------------\n");
	
		tr2 = moveTop(tr1.origin, tr1.buffer, tr1.destination); //No need to adjust as origin and destination same as those sent		

		printf("Towers second\n");
		print(tr2.origin);
		print(tr2.buffer);
		print(tr2.destination);
		printf("---------------------\n");

		Towers tf2 = move(discs - 1, tr2.buffer, tr2.origin, tr2.destination);
		tr3.origin = tf2.buffer;
		tr3.buffer = tf2.origin;
		tr3.destination = tf2.destination; //towers adjusted: swapped origin and buffer

		printf("Towers final\n");
		print(tr3.origin);
		print(tr3.buffer);
		print(tr3.destination);
		printf("---------------------\n");

		return tr3;
	}
}

Towers moveTop(struct stack origin, struct stack buffer, struct stack destination){
	Towers towers;
	int originTop = peek(origin);
	origin = pop(origin);
	destination = push(destination, originTop);
	towers.origin = origin;
	towers.buffer = buffer;
	towers.destination = destination;	
	return towers;
}
