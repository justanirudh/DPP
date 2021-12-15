package com.anirudh.companies_21_22.google.lc_last_6m.anki;

import java.util.*;

/*
1146. Snapshot Array
Medium

1235

208

Add to List

Share
Implement a SnapshotArray that supports the following interface:

SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
void set(index, val) sets the element at the given index to be equal to val.
int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id

 */
/*
Use List<List<Commit>> state OR List<TreeMap<Integer, Integer>>
Each Index in the list contains the history of the element in that index
Used former below, a bit easier with the later
 */
public class SnapshotArray {
    static class Commit {
        int snapId;
        int val;

        Commit(int snapId, int val) {
            this.snapId = snapId;
            this.val = val;
        }
    }

    /*
    first list is the index of the numbers in the snapshot array
    in each such index we have an array of numbers whose index = snapshot_id and value =value
     */
    List<List<Commit>> state; //OR use TreeMap and use map.floorEntry(K key) to get largest key less than or equal to given key: https://leetcode.com/problems/snapshot-array/discuss/350562/JavaPython-Binary-Search
    int currSnapId;

    public SnapshotArray(int length) {
        state = new ArrayList<>();
        currSnapId = 0;
        for (int i = 0; i < length; ++i) {
            List<Commit> list = new ArrayList<>();
            list.add(new Commit(currSnapId, 0));
            state.add(list);
        }

    }

    public void set(int index, int val) {
        List<Commit> snapshots = state.get(index); //all past values at this index
        if (snapshots.get(snapshots.size() - 1).snapId == currSnapId) { //if old value is in the same snapshot as new value, replace it
            snapshots.get(snapshots.size() - 1).val = val;
        } else { // add new snapId
            snapshots.add(new Commit(currSnapId, val));
        }
    }

    public int snap() {
        int curr = currSnapId;
        currSnapId++;
        return curr;
    }

    static class CompareSnaps implements Comparator<Commit> {
        @Override
        public int compare(Commit a, Commit b) {
            return a.snapId - b.snapId;
        }
    }

    //Use TreeMap instead. Way easier
    public int get(int index, int snap_id) { //logn
        List<Commit> snapshots = state.get(index); //all past values at this index
        // find the `snap_id` if present or the largest snap_id less that provided `snap_id` if not present, it is a sorted list, sorted on the basis of snap_id
        int snapIdx = Collections.binarySearch(snapshots, new Commit(snap_id, -1), new CompareSnaps()); //list, key, comparator
        if (snapIdx >= 0) //the snap_id exists in the list
            return snapshots.get(snapIdx).val;
        else { //otherwise, (-(insertion point) - 1). The insertion point is defined as the point at which the key would be inserted into the list: the index of the first element greater than the key, or list.size() if all elements in the list are less than the specified key
            return snapshots.get(-snapIdx - 2).val; //get largest snap_id less than the given snap_id
        }

    }
}


// Option 2: Use Collections.sort instead of TreeMap

// Option 3: Give MLE
//    int snap_id;
//    List<Integer> list;
//    Map<Integer, List<Integer>> snaps;
//    public SnapshotArray(int length) {
//        snap_id = -1;
//        list = new ArrayList<>();
//        for(int i = 0; i < length; ++i){
//            list.add(0);
//        }
//        snaps = new HashMap<>();
//    }
//
//    public void set(int index, int val) {
//        list.set(index, val);
//    }
//
//    public int snap() { //O(n)
//        snap_id++;
//        snaps.put(snap_id, new ArrayList<>(list));
//        return snap_id;
//    }
//
//    public int get(int index, int snap_id) {
//        return snaps.get(snap_id).get(index);
//    }

