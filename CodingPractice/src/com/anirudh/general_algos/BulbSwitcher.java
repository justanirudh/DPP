package com.anirudh.general_algos;

/**
 * Created by paanir on 5/10/17.
 */
/*
319. Bulb Switcher
There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb.
On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the ith round,
 you toggle every i bulb. For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.

Example:

Given n = 3.

At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off].

So you should return 1, because there is only one bulb is on.
 */
public class BulbSwitcher {
    /*
    only those that are toggled odd times will remain open at the end. means, number with odd factors. means perfect
    squares.
    for a number n, number of perfect squares <= the number would be the (floor of the) square root of the number itself
    Eg. if n = 12, 9 is biggest perfect square < 12; 3 < root of 12
     */
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
}
