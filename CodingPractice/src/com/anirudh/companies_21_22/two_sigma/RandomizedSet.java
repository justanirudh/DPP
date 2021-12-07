package com.anirudh.companies_21_22.two_sigma;

import java.util.*;

/**
 * Created by paanir on 9/5/21.
 */
/*
380. Insert Delete GetRandom O(1)
Medium

4042

234

Add to List

Share
Implement the RandomizedSet class:

RandomizedSet() Initializes the RandomizedSet object.
bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present,
false otherwise.
bool remove(int val) Removes an item val from the set if present. Returns true if the item was present,
false otherwise.
int getRandom() Returns a random element from the current set of elements (it's guaranteed that
at least one element exists when this method is called). Each element must have the same probability of being returned.

You must implement the functions of the class such that each function works in average O(1) time complexity.

Example 1:

Input
["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
[[], [1], [2], [2], [], [1], [2], []]
Output
[null, true, false, true, 2, true, false, 2]

Explanation
RandomizedSet randomizedSet = new RandomizedSet();
randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
randomizedSet.insert(2); // 2 was already in the set, so return false.
randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
 */

/*
1. Use a Map and an arraylist
2. Map has {element -> index in arraylist}
3. insert is O(1) in map, curr++. getRandom is O(1) in list
4. to delete elem 'e', put the last elem of the list in index of 'e', change mapping of lastElem to new index
4.1 delete mapping of 'e', delete last elem in list
4.2 decrement curr pointer
 */
class RandomizedSet {
    Map<Integer, Integer> elemToIdx;
    List<Integer> elems;
    Random r;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        elemToIdx = new HashMap<>();
        elems = new ArrayList<>();
        r = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (elemToIdx.containsKey(val))
            return false;
        else {
            elems.add(val);
            elemToIdx.put(val, elems.size() - 1);
            return true;
        }
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!elemToIdx.containsKey(val))
            return false;
        else {
            int lastElem = elems.get(elems.size() - 1); //get last elem
            int idxOfVal = elemToIdx.get(val);  //get index of val to be deleted

            elems.set(idxOfVal, lastElem); //put last elem in index of val to be deleted
            elemToIdx.put(lastElem, idxOfVal); //change mapping of lastElem to point to new index instead of last index

            elemToIdx.remove(val); //remove mapping of val to be deleted
            elems.remove(elems.size() - 1); //remove last elem from list

            return true;

        }

    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int idx = r.nextInt(elems.size());
        return elems.get(idx);
    }
}

/*
["RandomizedSet","insert","insert","getRandom","getRandom","insert","remove","getRandom","getRandom","insert","remove"]
[[],[3],[3],[],[],[1],[3],[],[],[0],[0]]
 */
