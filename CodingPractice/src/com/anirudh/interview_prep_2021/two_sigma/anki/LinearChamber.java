package com.anirudh.interview_prep_2021.two_sigma.anki;

/*
A collection of particles is contained in a linear chamber. They all have the same speed, but some are headed toward the right and others are headed toward the left. These particles can pass through each other without disturbing the motion of the particles, so all the particles will leave the chamber relatively quickly.

You will be given the initial conditions by a String init containing at each position an ‘L’ for a leftward moving particle, an ‘R’ for a rightward moving particle, or a ‘.’ for an empty location. init shows all the positions in the chamber. Initially, no location in the chamber contains two particles passing through each other. We would like an animation of the process. At each unit of time, we want a string showing occupied locations with an ‘X’ and unoccupied locations with a ‘.’. Create a funcion animate that is given an int speed and a String init giving the initial conditions. The speed is the number of positions each particle moves in one time unit.

The method will return an array of strings in which each successive element shows the occupied locations at the next time unit. The first element of the return should show the occupied locations at the initial instant (at time = 0) in the ‘X’, ‘.’ format. The last element in the return should show the empty chamber at the first time that it becomes empty followed by a NULL pointer in the following element. After I figured out the solution for the second problem, I found another way to solve it.
 */

/*
Based on currArray and speed, create nextArray
Based on nextArray, create output string replacing L/R with Xs and append to final output
do currArray = nextArray

while replacing L/R with Xs, count the number of times Xs appear. If it is > 0, we chamber is not empty yet
terminate loop on the basis of this
 */
public class LinearChamber {
}
