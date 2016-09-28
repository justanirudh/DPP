#include<vector>
#include<iostream>
#include<thread>
#define NUM_THREADS 8
using namespace std;

class Factorizer {
  public:
  Factorizer();
  ~Factorizer();

  void factorize(long val);

};

Factorizer::Factorizer() {}

Factorizer::~Factorizer() {}

//If you like you can define same function outside the class using scope resolution operator, ::
void Factorizer::factorize(long curr){
	std::vector<long> factors;
   	long original = curr;
   	for (long i = 2; i<=curr; i++){ //only need to go till the root of curr
      while ( curr%i == 0){
         factors.push_back(i);
         curr = curr/i;
      }
   	}	
	cout<<"Factors of "<<original<<" are: ";//overlap printing because each using the same cout object without any sync
	for(int i = 0; i!=factors.size(); i++)
		cout<<factors[i]<<" ";
	cout<<"\n";
}

int main(){
	Factorizer f;
	thread threads[NUM_THREADS];
	long numbers[NUM_THREADS] = {55, 65, 20, 22, 43, 97, 76, 15};
   	for (int t=0; t < NUM_THREADS; t++){ //passing the same object to mutiple threads
      threads[t] = thread(&Factorizer::factorize, f, numbers[t]);
   	}
   	for (int t=0; t < NUM_THREADS; t++){
       threads[t].join();
   	}
	return 0;
}

