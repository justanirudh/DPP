#include<vector>
#include<iostream>
#include<thread>
#include<atomic>
#define NUM_THREADS 8
using namespace std;

class Factorizer {
  	public:
  	Factorizer();
  	~Factorizer();

  	static void factorize(long val);
	static atomic<long> counter;
	long get_counter();

};
atomic<long> Factorizer::counter(0);
Factorizer::Factorizer(){}

Factorizer::~Factorizer() {}

long Factorizer::get_counter(){
	return counter;
}
//If you like you can define same function outside the class using scope resolution operator, ::
void Factorizer::factorize(long curr){
	vector<long> factors;
	long original = curr;
   	for (long i = 2; i<=curr; i++){ //only need to go till the root of curr
      while ( curr%i == 0){
         factors.push_back(i);
         curr = curr/i;
      }
   	}
	counter++;
	cout<<"Factors of "<<original<<"with counter value "<<counter<<": ";
	for(int i = 0; i!=factors.size(); i++)
		cout<<factors[i]<<" ";
	cout<<"\n";
}

int main(){
	//Factorizer f;
	//vector<long>* factors = new vector<long>
	long numbers[NUM_THREADS] = {55, 65, 20, 22, 43, 97, 76, 15};
	thread threads[8];
   for (int t=0 ;t < NUM_THREADS; t++){
      threads[t] = thread(&Factorizer::factorize, numbers[t]);
   }
   for (int t=0 ;t < NUM_THREADS; t++){
       threads[t].join();
   } 
	return 0;
}

