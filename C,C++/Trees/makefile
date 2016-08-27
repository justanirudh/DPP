CC=gcc
CFLAGS= -I .
DEPS= tree.h

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

rule1: driver.o tree.o
	$(CC) -o output driver.o tree.o
