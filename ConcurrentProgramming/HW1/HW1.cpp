/** Main function for HW1
 *
 * Make sure your program runs with this.
 */
#include<iostream>
int test_threadLocal();

int main(){
	int err = test_threadLocal();
	std::cout << "number of errors: " << err << std::endl;
	return err;

}


