// Tests for threadLocal.h

#include <iostream>
#include <atomic>
#include <thread>
#include <mutex>
#include <vector>
#include <algorithm>
#include <exception>
#include <list>
#include <chrono>
#include <string>
#include "threadLocal.h"
#include <cstdlib>
#include <ctime> 

int test_threadLocal_0(const int num_threads);
int test_threadLocal_1(const int num_threads);
int test_threadLocal_2(const int num_threads);
int test_threadLocal_3(const int num_threads);
int test_threadLocal_4(const int num_threads);
int test_threadLocal_6(const int num_threads);
int test_threadLocal_7(const int writers, const int readers);
int test_threadLocal_8(const int doubles_num, const int floats_num);
int test_threadLocal_9(const int num_threads);

//Note: Please run the tests more than once on any library on your side.
//Since the actions of threads are non-deterministic, it might take 2-3 runs for errors to show. Thanks! :)
int test_threadLocal(){
	int num_errs = 0;
	num_errs += test_threadLocal_0(15);
	num_errs += test_threadLocal_1(15);
	num_errs += test_threadLocal_2(15);
	num_errs += test_threadLocal_3(15);
	num_errs += test_threadLocal_4(10);
	num_errs += test_threadLocal_6(10);
	num_errs += test_threadLocal_7(5, 5);
	num_errs += test_threadLocal_8(5, 5);
	num_errs += test_threadLocal_9(5);
	return num_errs;
}

//testing these things here:
// 1. ThreadLocal works with ints
// 2. every get is equal to its corresponding set (map works properly)
// 3. if get and set are not synchronized properly, this test will note it as an error
int test_threadLocal_0(const int num_threads) {

	std::vector<std::thread> threads;
	cop5618::threadLocal<int> threadLocalObj;
	std::atomic<int> errors(0);	

	for (int t = 0; t < num_threads; t++) {
		threads.push_back(
				std::thread(
						[ &threadLocalObj, t, &errors ]() mutable ->void {
							try{
								threadLocalObj.set(t);
								for(int i = 0; i<25000; i++);//doing a long computation
								int val = threadLocalObj.get();
								if (val != t){
									errors++;
								}
							}catch(std::exception& e){
								errors++;
								return;
							}
						}));
	}

	std::for_each(threads.begin(), threads.end(),
			std::mem_fn(&std::thread::join));

	return errors;
}

//testing these things here:
// 1. ThreadLocal works with strings
// 2. every get is equal to its corresponding set (map works properly)
// 3. throw if not synchronized
int test_threadLocal_1(const int num_threads) {

	std::vector<std::thread> threads;
	cop5618::threadLocal<std::string> threadLocalObj;
	std::atomic<int> errors(0);	

	for (int t = 0; t < num_threads; t++) {
		threads.push_back(
				std::thread(
						[ &threadLocalObj, t, &errors ]() mutable ->void {
							try{
								threadLocalObj.set(std::to_string(t));
								for(int i = 0; i<25000; i++);
								std::string val = threadLocalObj.get();
								if (val.compare(std::to_string(t)) != 0){
									errors++;
								}
							}catch(std::exception& e){
								errors++;
								return;
							}

						}));
	}
	std::for_each(threads.begin(), threads.end(),
			std::mem_fn(&std::thread::join));
	return errors;
}

//testing these things here:
// 1. ThreadLocal works with arrays
// 2. every get is equal to its corresponding set (map works properly)
int test_threadLocal_2(const int num_threads) {

	std::vector<std::thread> threads;
	cop5618::threadLocal< std::array<int,2> > threadLocalObj;
	std::atomic<int> errors(0);	

	for (int t = 0; t < num_threads; t++) {
		threads.push_back(
				std::thread(
						[ &threadLocalObj, t, &errors ]() mutable ->void {
							try{
								std::array<int, 2>tt = {t, t};
								threadLocalObj.set(tt);
								for(int i = 0; i<25000; i++);
								std::array<int, 2> val = threadLocalObj.get();
								if (val != tt){
									errors++;
								}
							}catch(std::exception& e){
								errors++;
								return;
							}
						}));
	}
	std::for_each(threads.begin(), threads.end(),
			std::mem_fn(&std::thread::join));

	return errors;
}
//testing these things here:
// 1. every get is equal to its corresponding set (map works properly)
// 2. remove works properly
int test_threadLocal_3(const int num_threads) {

	std::vector<std::thread> threads;
	cop5618::threadLocal<std::string> threadLocalObj;
	std::atomic<int> errors(0);	

	for (int t = 0; t < num_threads; t++) {
		threads.push_back(
				std::thread(
						[ &threadLocalObj, t, &errors ]() mutable ->void {
							try{
								threadLocalObj.set(std::to_string(t));
								std::string val = threadLocalObj.get();
								if (val.compare(std::to_string(t)) != 0){
									errors++;
								}
								for(int i = 0; i<25000; i++);
								threadLocalObj.remove();
							}catch (std::exception& e) {
									errors++; 
									return;
							}
						}));
	}
	std::for_each(threads.begin(), threads.end(),
			std::mem_fn(&std::thread::join));
	
	return errors;
}

//testing these things here:
// 1. if get is called before set, throw
int test_threadLocal_4(const int num_threads) {

	std::vector<std::thread> threads;
	cop5618::threadLocal<std::string> threadLocalObj;
	std::atomic<int> errors(0);	

	for (int t = 0; t < num_threads; t++) {
		threads.push_back(
				std::thread(
						[ &threadLocalObj, t, &errors ]() mutable ->void {
							try{
								if(t%2 == 0){
									threadLocalObj.set(std::to_string(t));
									std::string val = threadLocalObj.get();
								}
								else{//getting without setting here.If a library doesn't throw, increment number of errors
									std::string val = threadLocalObj.get();
									errors++;
								}
							}
							catch (std::exception& e) {
								return;
							}
						}));
	}
	std::for_each(threads.begin(), threads.end(),
			std::mem_fn(&std::thread::join));

	return errors;
}

//testing these things here:
// 1. if remove is called before set, throw
int test_threadLocal_6(const int num_threads) {

	std::vector<std::thread> threads;
	cop5618::threadLocal<std::string> threadLocalObj;
	std::atomic<int> errors(0);	

	for (int t = 0; t < num_threads; t++) {
		threads.push_back(
				std::thread(
						[ &threadLocalObj, t, &errors ]() mutable ->void {
							try{
								if(t%2 == 0){
									threadLocalObj.set(std::to_string(t));
									std::string val = threadLocalObj.get();
								}
								else{//removing without setting here. If a library does not throw, increment errors
									threadLocalObj.remove();
									errors++;		
								}
							}
							catch (std::exception& e) {
								return;
							}
						}));
	}
	std::for_each(threads.begin(), threads.end(),
			std::mem_fn(&std::thread::join));

	return errors;
}

//testing these things here:
// 1. Setting and getting on different threads might or might not throw, depending upon
// whether the new thread has the same id as a previous thread that died.This might cause
// serious issues if ignored.  It is the client's responsibiltiy
// to clean up the threadLocal if an application runs on the same threadpool. This test does exactly this.
// The client calls threadLocal.remove() before instantiating reader threads.
// This makes sure any spurious activity does not happen. if it does, we increment the errors
int test_threadLocal_7(const int writers_num, const int readers_num) {

	std::vector<std::thread> writers;
	std::vector<std::thread> readers;
	cop5618::threadLocal<int> threadLocalObj;
	std::atomic<int> errors(0);	
	
	for (int t = 0; t < writers_num; t++) {
		writers.push_back(
				std::thread(
						[ &threadLocalObj, t, &errors ]() mutable ->void {
							try{
								threadLocalObj.set(t);
								for(int i = 0; i<10000; i++);
								threadLocalObj.remove();
							}catch (std::exception& e) {
								errors++;
								return;
							}
						}));
	}

	std::for_each(writers.begin(), writers.end(),
			std::mem_fn(&std::thread::join));

	for (int t = 0; t < readers_num; t++) {
		readers.push_back(
				std::thread(
						[ &threadLocalObj, t, &errors ]() mutable ->void {
							try{// increment errors if a new thread is able to get a value
								int val = threadLocalObj.get();
								errors++;
							}
							catch (std::exception& e) {
								return;
							}
						}));
	}
	std::for_each(readers.begin(), readers.end(),
			std::mem_fn(&std::thread::join));

	return errors;
}

//testing these things here:
// 1. works with doubles and floats
// 2. creating 2 thread local objects in same scope does not cause issues.
int test_threadLocal_8(const int doubles_num, const int floats_num) {

	std::vector<std::thread> doubles;
	std::vector<std::thread> floats;
	cop5618::threadLocal<double> threadLocalObjDub;
	cop5618::threadLocal<float> threadLocalObjFlo;
	std::atomic<int> errors(0);	

	for (int t = 0; t < doubles_num; t++) {
		doubles.push_back(
				std::thread(
						[ &threadLocalObjDub, t, &errors ]() mutable ->void {
							try{								
								threadLocalObjDub.set(t);
								for(int i = 0; i<8000; i++);
								double val = threadLocalObjDub.get();
							}
							catch (std::exception& e) {
								errors++;
								return;
							}						
						}));
	}

	std::for_each(doubles.begin(), doubles.end(),
			std::mem_fn(&std::thread::join));

	for (int t = 0; t < floats_num; t++) {
		floats.push_back(
				std::thread(
						[ &threadLocalObjFlo, t, &errors ]() mutable ->void {
							try{		
								threadLocalObjFlo.set(t);
								for(int i = 0; i<8000; i++);
								float val = threadLocalObjFlo.get();						
							}
							catch (std::exception& e) {
								errors++;
								return;
							}
						}));
	}
	std::for_each(floats.begin(), floats.end(),
			std::mem_fn(&std::thread::join));

	return errors;
}

//testing these things here:
// 1. set works after a set
int test_threadLocal_9(const int num_threads) {

	std::vector<std::thread> threads;
	cop5618::threadLocal<std::string> threadLocalObj;
	std::atomic<int> errors(0);	

	for (int t = 0; t < num_threads; t++) {
		threads.push_back(
				std::thread(
						[ &threadLocalObj, t, &errors ]() mutable ->void {
							try{
								threadLocalObj.set(std::to_string(t));
								for(int i = 0; i<8000; i++);
								std::string val = threadLocalObj.get();
								int k = t+1;
								threadLocalObj.set(std::to_string(k));
								std::string val2 = threadLocalObj.get();
								if (val.compare(val2) == 0){ //if the value hasn't updated, increment errors
									errors++;
								}
							}catch(std::exception& e){
								errors++;
								return;
							}

						}));
	}
	std::for_each(threads.begin(), threads.end(),
			std::mem_fn(&std::thread::join));

	return errors;
}
