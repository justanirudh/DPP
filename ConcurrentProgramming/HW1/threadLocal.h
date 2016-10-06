/*
 * threadLocal.h
 *  Start with this and add what is necessary
 */

#ifndef THREADLOCAL_H_
#define THREADLOCAL_H_

#include <iostream>
#include <map>
#include <exception>  //for std::exception
#include <memory>     //for std::shared_ptr
#include <deque>      //for std::queue
#include <mutex>      //for std::mutex and std::lock_guard
#include <iostream>   //for std::ostream
#include <stdexcept>
namespace cop5618 {

template <typename T>
class threadLocal {
public:
	threadLocal();
	~threadLocal();

	//disable copy, assign, move, and move assign constructors
	 threadLocal(const threadLocal&)=delete;
	 threadLocal& operator=(const threadLocal&)=delete;
	 threadLocal(threadLocal&&)=delete;
	 threadLocal& operator=(const threadLocal&&)=delete;

	 /**
	 * Returns the current thread's value.
	 * If no value has been previously set by this
	 * thread, an out_of_range exception is thrown.
	 */
	const T& get() const;


	/**
	 * Sets the value of the threadLocal for the current thread
	 * to val.
	 */
	void set(T val);

	/**
	 * Removes the current thread's value for the threadLocal
	 */
	void remove();

	/**
	 * Friend function.  Useful for debugging only
	 */
	template <typename U>
	friend std::ostream& operator<< (std::ostream& os, const threadLocal<U>& obj);
	
private:
	//ADD PRIVATE MEMBERS 
	std::map<std::thread::id,T> threadLocalsMap;
	mutable std::mutex m;	
};

//ADD DEFINITIONS
template<typename T>
threadLocal<T>::threadLocal(){}
template<typename T>
threadLocal<T>::~threadLocal() {}

template<typename T>
const T& threadLocal<T>::get() const{
	std::lock_guard<std::mutex> lock(m);
	std::thread::id this_id = std::this_thread::get_id();
	if(threadLocalsMap.find(this_id) == threadLocalsMap.end()){
		throw std::out_of_range ("attempt to get or remove a threadLocal variable before setting it");
	}
	return threadLocalsMap.find(this_id)->second;	
}

template<typename T>
void threadLocal<T>::set(T val) {//overwrites if already set
	std::lock_guard<std::mutex> lock(m);
	std::thread::id this_id = std::this_thread::get_id();
	threadLocalsMap[this_id] = val;
}

template<typename T>
void threadLocal<T>::remove() {
	std::lock_guard<std::mutex> lock(m);
	std::thread::id this_id = std::this_thread::get_id();
	if(threadLocalsMap.find(this_id) == threadLocalsMap.end()){
		throw std::out_of_range ("attempt to get or remove a threadLocal variable before setting it");
	}
	threadLocalsMap.erase(this_id);
}

template <typename T>
std::ostream& operator<< (std::ostream& os, const threadLocal<T>& obj){
	std::lock_guard<std::mutex> lock(obj.m);
	for (auto iter = obj.threadLocalsMap.begin(); iter != obj.threadLocalsMap.end(); ++iter){
		if (iter != obj.threadLocalsMap.begin()){os << "\n";}
			os << "(" << iter->first << ", " << iter->second << ")";
	}
	return os;
}

} /* namespace cop5618 */

#endif /* THREADLOCAL_H_ */
