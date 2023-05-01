package com.anirudh.a_prep_2023.bloomberg;

import java.util.*;

/*
835. Image Overlap
Medium
1.2K
455
company
Bloomberg
company
Microsoft
company
Yahoo
You are given two images, img1 and img2, represented as binary, square matrices of size n x n. A binary matrix has only 0s and 1s as values.

We translate one image however we choose by sliding all the 1 bits left, right, up, and/or down any number of units. We then place it on top of the other image. We can then calculate the overlap by counting the number of positions that have a 1 in both images.

Note also that a translation does not include any kind of rotation. Any 1 bits that are translated outside of the matrix borders are erased.

Return the largest possible overlap.



Example 1:


Input: img1 = [[1,1,0],[0,1,0],[0,1,0]], img2 = [[0,0,0],[0,1,1],[0,0,1]]
Output: 3
Explanation: We translate img1 to right by 1 unit and down by 1 unit.

The number of positions that have a 1 in both images is 3 (shown in red).

Example 2:

Input: img1 = [[1]], img2 = [[1]]
Output: 1
Example 3:

Input: img1 = [[0]], img2 = [[0]]
Output: 0
 */
/*
Approach 2: Linear Transformation
Intuition

One drawback of the above algorithm is that we would scan through those zones that are filled with zeros over and over, even though these zones are not of our interests.

Because for those cells filled with zero, no matter how we shift, they would not add up to the final solutions. As a follow-up question, we could ask ourselves that, can we focus on those cells with ones?

The answer is yes. The idea is that we filter out those cells with ones in both matrices, and then we apply the linear transformation to align the cells.

First of all, we define a 2-dimension coordinate, via which we could assign a unique coordinate to each cell in the matrix, e.g. a cell can be indexed as I=(Xi,Yi)I = (X_i, Y_i)I=(X
i
​
 ,Y
i
​
 ).

Then to shift a cell, we can obtain the new position of the cell by applying a linear transformation. For example, to shift the cell to the right by one and to the up side by one is to apply the linear transformation vector of V=(1,1)V = (1, 1)V=(1,1). The new index of the cell can be obtained by I+V=(Xi+1,Yi+1)I + V = (X_i + 1, Y_i + 1)I+V=(X
i
​
 +1,Y
i
​
 +1).

linear transformation

Furthermore, given two matrices, we have two non-zero cells respectively in the matrices as Pa=(Xa,Ya)P_a =(X_a, Y_a)P
a
​
 =(X
a
​
 ,Y
a
​
 ) and Pb=(Xb,Yb)P_b = (X_b, Y_b)P
b
​
 =(X
b
​
 ,Y
b
​
 ). To align these cells together, we would need a transformation vector as Vab=(Xb−Xa,Yb−Ya)V_{ab} = (X_b - X_a, Y_b - Y_a)V
ab
​
 =(X
b
​
 −X
a
​
 ,Y
b
​
 −Y
a
​
 ), so that Pa+Vab=PbP_a + V_{ab} = P_bP
a
​
 +V
ab
​
 =P
b
​
 .

Now, the key insight is that all the cells in the same overlapping zone would share the same linear transformation vector.

Based on the above insight, we can then use the transformation vector VabV_{ab}V
ab
​
  as a key to group all the non-zero cells alignments between two matrices. Each group represents an overlapping zone. Naturally, the size of the overlapping zone would be the size of the group as well.

Algorithm

The algorithm can be implemented in two overall steps.

First, we filter out those non-zero cells in each matrix respectively.

Second, we do a cartesian product on the non-zero cells. For each pair of the products, we calculate the corresponding linear transformation vector as Vab=(Xb−Xa,Yb−Ya)V_{ab} = (X_b - X_a, Y_b - Y_a)V
ab
​
 =(X
b
​
 −X
a
​
 ,Y
b
​
 −Y
a
​
 ). Then, we count the number of the pairs that have the same transformation vector, which is also the number of ones in the overlapping zone
 */

/*
put all 1s of mat A in a list A
put all 1s of mat B in a list B
For each pair of list A and listB, find vector: (Xa - Xb), (Ya-Yb)
Put in a map {vector -> freq}
largest freq is the answer

Now, the key insight is that all the cells in the
same overlapping zone would share the same linear transformation vector.

Meaning the way to transform pa to pb, w, that can be taken by MOST number
of point pairs (pa ->pb) is the transformation that will be used
 */
public class ImageOverlap {
    public int largestOverlap(int[][] img1, int[][] img2) {
        List<int[]> a = new ArrayList<>();
        for (int i = 0; i < img1.length; ++i) {
            for (int j = 0; j < img1[0].length; ++j) {
                if (img1[i][j] == 1)
                    a.add(new int[]{i, j});
            }
        }
        List<int[]> b = new ArrayList<>();
        for (int i = 0; i < img2.length; ++i) {
            for (int j = 0; j < img2[0].length; ++j) {
                if (img2[i][j] == 1)
                    b.add(new int[]{i, j});
            }
        }
        Map<String, Integer> vecFreq = new HashMap<>();
        for (int[] ae : a) {
            for (int[] be : b) {
                String vec = (ae[0] - be[0]) + "," + (ae[1] - be[1]);
                vecFreq.put(vec, vecFreq.getOrDefault(vec, 0) + 1);
            }
        }
        int max = 0;
        for (int f : vecFreq.values()) {
            max = Math.max(max, f);
        }
        return max;
    }
}
