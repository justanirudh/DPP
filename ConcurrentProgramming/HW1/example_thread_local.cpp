#include<iostream>
#include<string>
#include<thread>
#include<mutex>

thread_local int counter = 1; //each thread gets and instance initialized to 1
std::mutex cout_mutex; //used to serialize output

void increment_counter(const std::string& thread_name){
	++counter;//threadlocal, so no lock required
	std::lock_guard<std::mutex> lock(cout_mutex); //guard the output
	std::cout << "counter for " << thread_name << " in increment_counter: "<< counter << std::endl;
}

void scale_counter(const std::string& thread_name, int scale){
	counter = counter * scale;//threadlocal, so no lock required
	std::lock_guard<std::mutex> lock(cout_mutex);
	std::cout << "counter for " << thread_name << " in scale_counter: "<< counter << std::endl;
}

int main(){
	increment_counter("main");
	std::thread a(increment_counter, "a"), b(scale_counter, "b", 5);
	a.join();
	b.join();
	std::lock_guard<std::mutex> lock(cout_mutex);
	std::cout << "counter for main after join: " << counter << '\n';
}
