package concurrent_programming.expandablearray;

import java.util.NoSuchElementException;

/**
 * Created by anirudh on 27/9/16.
 */
public class ExpandableArray {
    protected Object[] data; // the elements
    protected int size = 0;   // the number of array slots used

    public ExpandableArray(int cap) {
        data = new Object[cap];
    }

    public synchronized int size() {
        return size;
    }

    public synchronized Object get(int i) throws NoSuchElementException {
        if (i < 0 || i >= size )
            throw new NoSuchElementException();
        return data[i]; //return its copy to be extra safe
    }

    public synchronized void add(Object x) { // add at end
        if (size == data.length){ // need a bigger array
            Object[] olddata = data;
            data = new Object[3 * (size + 1) / 2];
            System.arraycopy(olddata, 0, data, 0, olddata.length);
        }
        data[size++] = x;
    }

    public synchronized void removeLast() throws NoSuchElementException {
        if (size == 0)
            throw new NoSuchElementException();
        data[--size] = null;
    }

}
