package com.anirudh.companies_21_22.facebook.lc_last_6m.anki;

/*
348. Design Tic-Tac-Toe
Medium

1517

89

Add to List

Share
Assume the following rules are for the tic-tac-toe game on an n x n board between two players:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves are allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Implement the TicTacToe class:

TicTacToe(int n) Initializes the object the size of the board n.
int move(int row, int col, int player) Indicates that the player with id player plays at the cell (row, col) of the board. The move is guaranteed to be a valid move.


Example 1:

Input
["TicTacToe", "move", "move", "move", "move", "move", "move", "move"]
[[3], [0, 0, 1], [0, 2, 2], [2, 2, 1], [1, 1, 2], [2, 0, 1], [1, 0, 2], [2, 1, 1]]
Output
[null, 0, 0, 0, 0, 0, 0, 1]

Explanation
TicTacToe ticTacToe = new TicTacToe(3);
Assume that player 1 is "X" and player 2 is "O" in the board.
ticTacToe.move(0, 0, 1); // return 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

ticTacToe.move(0, 2, 2); // return 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

ticTacToe.move(2, 2, 1); // return 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

ticTacToe.move(1, 1, 2); // return 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

ticTacToe.move(2, 0, 1); // return 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

ticTacToe.move(1, 0, 2); // return 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

ticTacToe.move(2, 1, 1); // return 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|


Constraints:

2 <= n <= 100
player is 1 or 2.
0 <= row, col < n
(row, col) are unique for each different call to move.
At most n2 calls will be made to move.


Follow-up: Could you do better than O(n2) per move() operation?

Accepted
168,551
Submissions
295,718
 */
/*
Option 1:
For each player
    int[] row[n]: increment row[i] if player plays [i][j]; if row[i] == n, player wins
    int[] col[n]: increment col[j] if player plays [i][j]; if row[j] == n, player wins
    int diag: increment if player plays i+j == n-1
    int antiDiag: increment if player plays i-j == 0

Option 2: O(n^2) for every move. Check row, col, diag and anti-diag in each move
 */
public class TicTacToe {

    int[] p1Row;
    int[] p1Col;
    int p1Diag;
    int p1AntiDiag;
    int[] p2Row;
    int[] p2Col;
    int p2Diag;
    int p2AntiDiag;

    int n;

    public TicTacToe(int n) {
        this.n = n;
        p1Row = new int[n];
        p1Col = new int[n];
        p1Diag = 0;
        p1AntiDiag = 0;
        p2Row = new int[n];
        p2Col = new int[n];
        p2Diag = 0;
        p2AntiDiag = 0;
    }

    public int move(int row, int col, int player) {
        if (player == 1) {
            p1Row[row]++;
            if (p1Row[row] == n)
                return 1;

            p1Col[col]++;
            if (p1Col[col] == n)
                return 1;

            if (row + col == n - 1) {
                p1Diag++;
                if (p1Diag == n)
                    return 1;
            }

            if (row - col == 0) {
                p1AntiDiag++;
                if (p1AntiDiag == n)
                    return 1;
            }
        } else {
            p2Row[row]++;
            if (p2Row[row] == n)
                return 2;

            p2Col[col]++;
            if (p2Col[col] == n)
                return 2;

            if (row + col == n - 1) {
                p2Diag++;
                if (p2Diag == n)
                    return 2;
            }

            if (row - col == 0) {
                p2AntiDiag++;
                if (p2AntiDiag == n)
                    return 2;
            }
        }
        return 0;
    }

//-------------------------------SLOW
    public class DesignTicTacToeSlow {
        int[][] matrix;
        int n;

        public DesignTicTacToeSlow(int n) {
            matrix = new int[n][n];
            this.n = n;
        }

        private boolean checkRow(int row, int player) {
            for (int i = 0; i < n; ++i) {
                if (matrix[row][i] != player)
                    return false;
            }
            return true;
        }

        private boolean checkCol(int col, int player) {
            for (int i = 0; i < n; ++i) {
                if (matrix[i][col] != player)
                    return false;
            }
            return true;
        }

        boolean checkSlashDiag(int player) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (i + j == n - 1 && matrix[i][j] != player)
                        return false;
                }
            }
            return true;
        }

        boolean checkBackwardSlashDiag(int player) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (i - j == 0 && matrix[i][j] != player)
                        return false;
                }
            }
            return true;
        }

        private boolean checkDiag(int row, int col, int player) {
            if (row + col != n - 1 && row - col != 0) //not in diag
                return false;
            else if (row + col == n - 1 && row - col == 0) { //center elem
                return checkSlashDiag(player) || checkBackwardSlashDiag(player);
            } else if (row + col == n - 1) {
                return checkSlashDiag(player);
            } else {
                return checkBackwardSlashDiag(player);
            }
        }


        public int move(int row, int col, int player) {
            matrix[row][col] = player;
            if (checkRow(row, player) || checkCol(col, player) || checkDiag(row, col, player))
                return player;
            else
                return 0;
        }
    }
}

