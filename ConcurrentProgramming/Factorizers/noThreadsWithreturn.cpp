#include<vector>
#include<iostream>
#include<thread>
#define NUM_THREADS 8
using namespace std;

class Factorizer {
  public:
  Factorizer();
  ~Factorizer();

  void factorize(long val, vector<long>* factors);

};

Factorizer::Factorizer() {}

Factorizer::~Factorizer() {}

//If you like you can define same function outside the class using scope resolution operator, ::
void Factorizer::factorize(long curr, vector<long>* factors){
	long original = curr;
   	for (long i = 2; i<=curr; i++){ //only need to go till the root of curr
      while ( curr%i == 0){
         factors->push_back(i);
         curr = curr/i;
      }
   	}	
}

int main(){
	Factorizer f;
	vector<long>* factors = new vector<long>;// = (vector<long>*)malloc(10 * sizeof(long));
	long numbers[NUM_THREADS] = {55, 65, 20, 22, 43, 97, 76, 15};
	f.factorize(55, factors);
	vector<long> &vr = *factors; //making a reference to the value inside the pointer
	for(int i = 0; i!=vr.size(); i++)
		cout<<vr[i]<<" ";
	cout<<"\n";

	return 0;
}

