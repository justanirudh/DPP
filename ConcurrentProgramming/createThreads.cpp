#include <thread>
#include <iostream>
using namespace std;
#define NUM_THREADS 10

void print0(){
cout << "Go Gators" << endl;
}

void print1(int id){
cout << "Thread" << id << endl;
}

void simple(){
   thread t0(print0);
   thread t1(print0);
   t0.join(); //required in C++
   t1.join();
}

void simpleLambda(){
	thread t0([]{cout<<"Waddup\n";});
	thread t1([]{cout<<"Buddy\n";});
	t0.join();
	t1.join();
}

void moreThanTwoThreads(){ //does not synchronize properly
   thread threads[8];
   for (int t=0 ;t < NUM_THREADS; t++){
      threads[t] = thread(print1, t );
   }
   for (int t=0 ;t < NUM_THREADS; t++){
       threads[t].join();
   } 
}

//Passing same object to multiple threads
class Factorizer{
		int num;
	public:
		void set_value(int x){
			num = x;
		}
		void printNum(){
			cout<< num << " printed"<<endl;
		}
};

void callMethodOfAnObject(){
	Factorizer f;
	f.set_value(10);
	thread t(&Factorizer::printNum, f);
	t.join();
}

int main(){
	//simple();
	//simpleLambda();
	//print0();
	//moreThanTwoThreads();
	callMethodOfAnObject();
	return 0;
}
