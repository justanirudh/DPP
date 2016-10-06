//============================================================================
// Name        : HW1.cpp
// Author      : Beverly Sanders
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <atomic>
#include <thread>
#include <mutex>
#include <vector>
#include <algorithm>
#include <exception>
#include <list>
#include "threadsafe_queue.h"

int test_queue_0(int num_threads, int vals_per_thread);
int test_queue_1(int num_threads, int vals_per_thread);
int test_queue_2(int num_adders, int num_removers, int vals_per_adder);

/** This function invokes a list of test cases.
 * Each test case returns 0 if it passed the test, 1 otherwise.
 * It returns the total number of errors.
 *
 * It is crucial that your test cases be automated in some way.
 * This illustrates a simple way of doing it.
 * It would be better to use a testing framework
 */

int test_threadsafe_queue();

int main() {
	int err;
	err = test_threadsafe_queue();
	if (err == 0)
		std::cout << "all tests passed" << std::endl;
	return err;
}

//Verifies that the values in this int container are in ascending order with
//no gaps.  It does not check the upper bound.  This should be done separately
//by checking the size.
template<typename Container>
bool verify_consecutive(Container& container, int first) {
	int i = first;
	for (auto iter = container.begin(); iter != container.end(); ++iter, ++i) {
		if (*iter != i) {
			return false;
		}
	}
	return true;
}

int test_threadsafe_queue() {
	int num_errs = 0;
	num_errs += test_queue_0(8, 4000);
	num_errs += test_queue_1(8, 8000);
	num_errs += test_queue_2(1, 4, 10000);
	return num_errs;
}

/**
 * test_queue_0 tests concurrent adds
 *
 */
int test_queue_0(const int num_threads, const int vals_per_thread) {

	std::vector<std::thread> threads;
	cop5618::threadsafe_queue<int> queue;

	std::atomic<int> sequencer(0);

	for (int t = 0; t < num_threads; t++) {
		threads.push_back(
				std::thread(
						[&sequencer, &queue, vals_per_thread]() mutable ->void {
							{
								//int temp = sequencer.load();
								//std::cout<<"value of tempSeq. is "<<temp<<std::endl;
								for (int i = 0; i < vals_per_thread; i++) {
									queue.push(sequencer++); //increment sequencer, and push old value into queue.
								}
							}
						}));

	}

	//join all the threads
	std::for_each(threads.begin(), threads.end(),
			std::mem_fn(&std::thread::join));

	//Only main thread active here

//	std::cout << "Printing contents of queue" << std::endl << queue << std::endl;

	//check easy things first, like that the size is correct.
	size_t expected_size = num_threads * vals_per_thread;
	if (expected_size != queue.size()) {
		std::cout << "queue size not correct. expected_size=" << expected_size
				<< ", size=" << queue.size() << std::endl;
		return 1;
	}

	//get the underlying deque, sort it and check that all values in range [0..expected_size) are there
	std::deque<int>& backing_queue = queue.get_backing_queue();
	std::sort(backing_queue.begin(), backing_queue.end());
	if( !verify_consecutive<std::deque<int> >(backing_queue, 0)){
		std::cout << "verify_consecutive failed in test_queue_0";
		return 1;
	}
	return 0;
}

/**
 * test_queue_0 has some deficiencies.  Since all the values to add come from the shared sequencer,
 * they are highly likely to be added in order, and we can't tell whether there was actually any
 * interleaving or not.  This test aims to correct that problem.
 *
 * Create some threads that add elements to the queue.  After all the threads have finished,
 * check to see that all the values have been added to the queue.  Note that each thread must
 * do enough work that they will experience concurrency;  otherwise one thread will finish before
 * the next starts, and we aren't testing the concurrent execution.
 *
 * To ensure concurrency call this function with a large enough vals_per_thread count.
 * For each value of t, the thread will add values [t*vals_per_thread, (t+1)*vals-per_thread)
 * to the queue.  If the queue is not sorted on completion, we can conclude that some interleaving
 * probably took place.
 *
 * You should also try a quick test with a version of your class that
 */
int test_queue_1(const int num_threads, const int vals_per_thread) {

	std::vector<std::thread> threads;
	cop5618::threadsafe_queue<int> queue;

	for (int t = 0; t < num_threads; t++) {
		int my_t = t;
		threads.push_back(
				std::thread(
						[&queue, my_t, vals_per_thread]() mutable ->void {
							{	int start = my_t * vals_per_thread;
								for (int i = start; i < start + vals_per_thread; i++) {
									queue.push(i);
								}
							}
						}));

	}

	//join all the threads
	std::for_each(threads.begin(), threads.end(),
			std::mem_fn(&std::thread::join));

	//Only the main thread is active now.  Validate the contents of the queue.
	//First check the size
	size_t expected_size = num_threads * vals_per_thread;
	if (expected_size != queue.size()) {
		std::cout << "queue size not correct. expected_size=" << expected_size
				<< ", size=" << queue.size() << std::endl;
		return 1;
	}

	//print contents of queue
//	std::cout << "Printing contents of queue" << std::endl << queue << std::endl;

	//get the internal backing queue and determine if it is sorted
	std::deque<int>& backing_queue = queue.get_backing_queue();
	bool is_sorted = std::is_sorted(backing_queue.begin(), backing_queue.end());
	if (is_sorted) {
		std::cout << "No interleaving, test_queue_1 threads need more work.  num_threads = "
				<< num_threads << ", vals_per_thread= " << vals_per_thread
				<< std::endl;
	} else {
		//Sort the thread.
		//Since this is a test, sort the value in place.
		std::sort(backing_queue.begin(), backing_queue.end());
	}

	//print contents of sorted queue
//	std::cout << "Printing sorted contents of queue" << std::endl << queue << std::endl;
	//sorted queue should contain values from [0,expected_size)
	if( !verify_consecutive<std::deque<int>>(backing_queue, 0)){
		std::cout << "error in test_queue_1" << std::endl;		
		return 1;
	}
	return 0;
}

/** This test is a bit more elaborate
 * 	It creates some threads that add to the queue, and some that remove.
 * 	The removers must be able to handle exceptions and know when to quit.
 * 	We will have a shared atomic counter that counts the number of active
 * 	adder threads.  remover threads stop when the queue is empty, and the
 * 	number of active adder threads is 0.  Remover threads put the values they
 * 	obtain from the queue into their own vector.  The vectors are merged and
 * 	sorted and then checked for correct contents.
 *
 */

int test_queue_2(int num_adders, int num_removers, int vals_per_adder) {

	std::vector<std::thread> adders;
	std::vector<std::thread> removers;
	std::vector<std::list<int>> removed_vals(num_removers); //it is a vector of lists

	cop5618::threadsafe_queue<int> queue;
	std::atomic<int> active_adders(num_adders);

	//create adder threads
	for (int t = 0; t < num_adders; t++) {
		int my_t = t;
		adders.push_back(
				std::thread(
						[&queue, my_t, vals_per_adder, &active_adders]() mutable ->void {
							{	int start = my_t * vals_per_adder;
								for (int i = start; i < start + vals_per_adder; i++) {
									queue.push(i);
								}
								active_adders--;
							}
						}));

	}
	//create removers
	for (int t = 0; t < num_removers; t++) {
		int my_t = t;
		removers.push_back(
				std::thread(
						[&queue, my_t, &removed_vals, &active_adders]() mutable ->void {
							for(;;) {
								try {
									for(;;) {
										int my_val;
										queue.front(my_val);
										removed_vals[my_t].push_back(my_val); //put my_val in the array starting from my_t index of removed_vals
									}
								}
								catch (std::exception& e) {
									if(active_adders == 0) return;
								}
							}
						}));
	}

	std::for_each(adders.begin(), adders.end(),
			std::mem_fn(&std::thread::join));
	std::for_each(removers.begin(), removers.end(),
			std::mem_fn(&std::thread::join));

	//check sizes
	if (queue.size() != 0) {
		std::cout << "queue not empty on termination" << std::endl;
	}
	int expected_size = num_adders * vals_per_adder;
	std::list<int> combined_list;
	int total_size = 0;

	for (auto iter = removed_vals.begin(); iter != removed_vals.end(); ++iter) {
		total_size += iter->size();
//		//print current remover's values
//		for (auto vec_iter = iter->begin(); vec_iter != iter->end();
//				++vec_iter) {
//			if (vec_iter != iter->begin())
//				std::cout << ",";
//			std::cout << *vec_iter;
//		}
//		std::cout << std::endl;
		//sort the list
		iter->sort();
		combined_list.merge(*iter);
	}
	if (expected_size != total_size) {
		std::cout << "total size not as expected" << std::endl;
		return 1;
	}

	bool success = verify_consecutive<std::list<int> >(combined_list, 0);
	if (!success) {
		std::cout << "verify_consecutive failed" << std::endl;
		return 1;
	}
	return 0;

}

