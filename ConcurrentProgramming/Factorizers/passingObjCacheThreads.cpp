#include<vector>
#include<iostream>
#include<thread>
#include<mutex>
#define NUM_THREADS 8
using namespace std;

mutex m;

class Factorizer {
	long last_num;
	vector<long> last_factors;
	//mutex m;
  	public:
  	Factorizer();
  	~Factorizer();
  	void factorize(long val);
};

Factorizer::Factorizer():last_num(0){}
Factorizer::~Factorizer() {}

//If you like you can define same function outside the class using scope resolution operator, ::
void Factorizer::factorize(long curr){
	lock_guard<mutex> guard(m); //RAII style lock: prints all factors sequentially
	if (curr == last_num){
		cout<<"Factors of "<<curr<<": ";
		for(int i = 0; i!=last_factors.size(); i++)
			cout<<last_factors[i]<<" ";
		cout<<"\n";
	}
	else{
		vector<long> factors;
		long original = curr;
   		for (long i = 2; i<=curr; i++){ //only need to go till the root of curr
      		while ( curr%i == 0){
         	factors.push_back(i);
         	curr = curr/i;
      		}
   		}
		last_factors = factors;
		cout<<"Factors of "<<original<<": ";
		for(int i = 0; i!=factors.size(); i++)
			cout<<factors[i]<<" ";
		cout<<"\n";
	}
}

int main(){
	long numbers[NUM_THREADS] = {55, 65, 20, 22, 43, 97, 76, 15};
	thread threads[8];
	Factorizer obj;
   	for (int t=0 ;t < NUM_THREADS; t++){ //passing the same object to all threads
      threads[t] = thread(&Factorizer::factorize, obj, numbers[t]);
   	}
   	for (int t=0 ;t < NUM_THREADS; t++){
       threads[t].join();
   	} 
	return 0;
}

