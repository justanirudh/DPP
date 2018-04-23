package com.anirudh.OOD;

import java.util.Scanner;

/**
 * Created by paanir on 9/16/17.
 */
public class TicTacToe {
    private char[][] board;

    public TicTacToe() {
        board = new char[3][3];
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                board[i][j] = '.';
            }
        }
    }

    private boolean playerWon(char letter) {
        //check 3 rows, check 3 cols, check 2 diags
        return ((board[0][0] == letter && board[0][1] == letter && board[0][2] == letter) ||
                (board[1][0] == letter && board[1][1] == letter && board[1][2] == letter) ||
                (board[2][0] == letter && board[2][1] == letter && board[2][2] == letter) ||
                (board[0][0] == letter && board[1][0] == letter && board[2][0] == letter) ||
                (board[0][1] == letter && board[1][1] == letter && board[2][1] == letter) ||
                (board[0][2] == letter && board[1][2] == letter && board[2][2] == letter) ||
                (board[0][0] == letter && board[1][1] == letter && board[2][2] == letter) ||
                (board[0][2] == letter && board[1][1] == letter && board[2][0] == letter));
    }

    private void printBoard() {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        TicTacToe ttt = new TicTacToe();
        Scanner stdin = new Scanner(System.in);
        ttt.printBoard();
        //start game
        int numMoves = 9;
        int i = 1;
        while (numMoves != 0) {
            System.out.println("Player " + i + " play. Enter row and col separated by space and hit enter");
            String[] input;
            try{
                input = stdin.nextLine().split(" ");
            }
            catch(NumberFormatException nfe){
                System.out.println("Wrong input format. Lets try again");
                continue;
            }
            int row = Integer.parseInt(input[0]);
            int col = Integer.parseInt(input[1]);
            //first check if the location has already been used before
            if(ttt.board[row][col] != '.'){
                System.out.println("The location " + row + ", " + col + " has already been taken. Lets try again");
                continue;
            }
            //player 1 is X and player 2 is O
            if (i == 1) {
                ttt.board[row][col] = 'X';
                if (ttt.playerWon('X')) {
                    ttt.printBoard();
                    System.out.println("Player 1 won!");
                    return;
                }
                i = 2;
            } else {
                ttt.board[row][col] = 'O';
                if (ttt.playerWon('O')) {
                    ttt.printBoard();
                    System.out.println("Player 2 won!");
                    return;
                }
                i = 1;
            }
            ttt.printBoard();
            numMoves--;
        }
        System.out.println("Game is draw!");
    }
}
