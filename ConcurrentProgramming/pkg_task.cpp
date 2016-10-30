#include<iostream>
#include<future>
//g++ -pthread file
int triple (int x) { return x*3; } 
int main () {
	std::packaged_task<int(int)> tsk (triple); 
	std::future<int> fut = tsk.get_future(); 
	std::thread(std::move(tsk),33).detach(); 
	int value = fut.get(); 
	std::cout << value << std::endl; 
	return 0;
}
