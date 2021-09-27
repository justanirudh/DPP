package com.anirudh.interview_prep_2021.two_sigma;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*
Create a filtering iterator for numbers mod 5 based on an existing iterator.
Only return elements that are a % 5 = 0;
 */
/*
extend iterator<Integer>
Have 3 vars:
1. vanilla iterator
2. hasfilteredNext
3. filteredNext
in hasNext(): call private getNext() -> use vanillaIterator to populate 2. and 3.
 */
public final class CustomIterator implements Iterator<Integer> {
    Iterator<Integer> vanillaIter;

    private boolean hasFilteredNext;
    private int filteredNext;

    public CustomIterator(Iterator<Integer> vanillaIter) {
        this.vanillaIter = vanillaIter;
    }

    private void getNext() {
        hasFilteredNext = false;
        while (vanillaIter.hasNext()) {
            int next = vanillaIter.next();
            if (next % 5 == 0) {
                hasFilteredNext = true;
                filteredNext = next;
                break;
            }
        }
    }

    @Override
    public boolean hasNext() {
        getNext();
        return hasFilteredNext;
    }

    @Override
    public Integer next() {
        if(hasFilteredNext)
            return filteredNext;
        throw new ArrayIndexOutOfBoundsException();
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,5,10, 23, 4,20, 25, 46, 45, 50, 0, -45, -2, -5000, 465, 34, 23, 5000);
        Iterator<Integer> iter = list.iterator();
        CustomIterator cIter = new CustomIterator(iter);
        while(cIter.hasNext()) {
            System.out.print(cIter.next() + " ");
        }
    }
}
