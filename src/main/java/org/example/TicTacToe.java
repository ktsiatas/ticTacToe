package org.example;

public class TicTacToe {


    private static final int FIRST_PLAYER = 1;
    private static final int SECOND_PLAYER = -1;
    private static Board board;

    public static boolean isGameOver(int x, int y, int player) {
        return (!board.anyMovesLeft()) || board.checkWinner(x, y, player);
    }

    public static void main(String[] args) {

        board = new Board();
        int currentPlayer = FIRST_PLAYER;

        /*TODO
        Pseudocode Plan

        1. Get some initial input for a move
        2. while !isGameOver ->
            flip player ( current * -1)
            get some input
            placePiece

            include some error handling for incorrect input
         */
    }
}