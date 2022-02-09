package com.anirudh.companies_21_22.google.lc_last_6m;

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
Use Map<Integer, TreeMap<Integer, Integer>> history; // index -> {snapId -> value}
Start with snapId = 0;
If highest snapId for a value == current snapId, replace else add new entry
To get value for a snap_id, use floorValue func of treemap
 */
public class SnapshotArray {
    Map<Integer, TreeMap<Integer, Integer>> history; // index -> {snapId -> value}
    int snapId;

    public SnapshotArray(int length) {
        history = new HashMap<>();
        snapId = 0;
        for (int i = 0; i < length; ++i) {
            history.put(i, new TreeMap<>());
            history.get(i).put(snapId, 0);
        }
    }

    public void set(int index, int val) {
        TreeMap<Integer, Integer> map = history.get(index);
        map.put(snapId, val); //if map.lastKey() same as last (biggest) key, replace; if map.lastKey() < snapId, add new entry
    }

    public int snap() {
        int currSnap = snapId;
        snapId++;
        return currSnap;
    }

    public int get(int index, int snap_id) { //find biggest entry <= snap_id
        return history.get(index).floorEntry(snap_id).getValue();
    }
}

