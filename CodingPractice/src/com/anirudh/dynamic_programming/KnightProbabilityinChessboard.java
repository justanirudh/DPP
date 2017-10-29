package com.anirudh.dynamic_programming;

/**
 * Created by paanir on 10/5/17.
 */
/*
688. Knight Probability in Chessboard
DescriptionHintsSubmissionsDiscussSolution
Discuss Pick One
On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves.
The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction,
then one square in an orthogonal direction.


Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would
go off the chessboard) and moves there.

The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability
that the knight remains on the board after it has stopped moving.

Example:
Input: 3, 2, 0, 0
Output: 0.0625
Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.
Note:
N will be between 1 and 25.
K will be between 0 and 100.
The knight always initially starts on the board.
 */
public class KnightProbabilityinChessboard {

    //https://leetcode.com/articles/knight-probability-in-chessboard/
//    Let f[r][c][steps] be the probability of being on square (r, c) after steps steps. Based on how a knight moves,
//    we have the following recursion:
//
//f[r][c][steps] = \sum_{dr, dc} f[r+dr][c+dc][steps-1] / 8.0f[r][c][steps]=∑dr,dc
//​​ f[r+dr][c+dc][steps−1]/8.0
//
//where the sum is taken over the eight (dr, dc)(dr,dc) pairs (2, 1),(2,1), (2, -1),(2,−1), (-2, 1),(−2,1), (-2, -1),
//(−2,−1), (1, 2),(1,2), (1, -2),(1,−2), (-1, 2),(−1,2), (-1, -2)(−1,−2).
//
//Instead of using a three-dimensional array f, we will use two two-dimensional ones dp and dp2, storing the result of
//the two most recent layers we are working on. dp2 will represent f[][][steps], and dp will represent f[][][steps-1].



    double sumAllProbs(double[][] state, int N) {
        double sum = 0;
        for (int row = 0; row < N; ++row) {
            for (int col = 0; col < N; ++col) {
                sum += state[row][col];
            }
        }
        return sum;
    }

    boolean isValid(int x, int y, int N) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public double knightProbability(int N, int K, int r, int c) {

//    a state is a board after every move
//    in every state, we are finding out the probability of the knight being in each position on the board

        double[][] state = new double[N][N];
        int[] dc = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] dr = {1, -1, 2, -2, 2, -2, 1, -1};
        state[r][c] = 1; //prob of it being in this position = 1

        for (int move = 0; move < K; ++move) { //K moves
            double[][] nextState = new double[N][N];
            for (int row = 0; row < N; ++row) {
                for (int col = 0; col < N; ++col) {
                    //now assuming knight is in (row,col), find probs of all places it can go
                    for (int i = 0; i < 8; ++i) {
                        int destR = row + dr[i];
                        int destC = col + dc[i];
                        if (isValid(destR, destC, N))
                            //probabilty of it being in this position is 1/8 of the source as 8 ways to move from source pos
                            nextState[destR][destC] += state[row][col] / 8.0;
                    }
                }
            }
            state = nextState; //this is the old state now
        }

        return sumAllProbs(state, N);
    }

}


