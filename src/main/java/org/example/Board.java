package org.example;

public class Board {

    private final int[][] CORNERS = {{0, 0}, {0, 3}, {3, 0}, {3, 3}};
    private final int[][] LEFT_DIAGONAL = {{0, 0}, {1, 1}, {2, 2}, {3, 3}};
    private final int[][] RIGHT_DIAGONAL = {{0, 3}, {1, 2}, {2, 1}, {3, 0}};
    private final int LEFT_D_POS = 0;
    private final int RIGHT_D_POS = 1;

    private int[] colSum = {0 ,0 ,0 ,0};
    private int[] rowSum = {0, 0, 0 ,0};
    private int[] diagonalSum = {0, 0}; // top left, top right
    private int cornerSum = 0;
    private int[][] board;

    private static final int EMPTY = 0;

    public Board() {
        this.board = new int[][]{
                {EMPTY, EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY, EMPTY}};
    }

    public Board(int[][] board) {
        this.board = board;
    }
    public void placePiece(int x, int y, int player) {
        // Should have a check here for valid placement
        // Error handling

        board[y][x] = player; // update the board

        // update the winner values
        colSum[y]+= player;
        rowSum[x]+= player;

        for (int[] point : CORNERS) {
            if (point[0] == y && point[1] == x) {
                cornerSum+= player;
            }
        }

        for (int i = 0; i < LEFT_DIAGONAL.length; i++) {
            if (LEFT_DIAGONAL[i][0] == y && LEFT_DIAGONAL[i][1] == x) {
                diagonalSum[0]+= player;
            }
            if (RIGHT_DIAGONAL[i][0] == y && RIGHT_DIAGONAL[i][1] == x) {
                diagonalSum[1]+= player;
            }
        }

    }

    private boolean checkRow(int y, int player) {
        return rowSum[y] == player * 4;
    }

    private boolean checkCol(int x, int player) {
        return colSum[x] == player * 4;
    }

    // x, y could be the point in a box for 4 different spaces
    // [y,x], [y,x+1], [y+1,x], [y+1, x+1] top left
    // [y,x], [y, x+1], [y-1,x], [y-1,x+1] bottom left
    // [y,x], [y, x-1], [y+1,x], [y+1,x-1] top right
    // [y,x], [y, x-1], [y-1,x], [y-1,x-1] bottom right
    private boolean checkBox(int x, int y, int player) {
        if (x + 1 < board.length && board[y][x+1] == player) { // left
            if (y + 1 < board.length && board[y+1][x] == player && board[y+1][x+1] == player) { // top
                return true;
            }
            if (y - 1 > -1 && board[y-1][x] == player && board[y-1][x+1] == player) { // bottom
                return true;
            }
        }
        if (x - 1 > -1 && board[y][x-1] == player) { // right
            if (y + 1 < board.length && board[y+1][x] == player && board[y+1][x-1] == player) { // top
                return true;
            }
            if (y - 1 > -1 && board[y][x-1] == player && board[y-1][x-1] == player) { // bottom
                return true;
            }
        }
        return false;
    }

    // 2 possibilities
    // [0,0], [1,1], [2,2], [3,3]
    // [0,3], [1,2], [2,1], [3,0]
    private boolean checkDiagonal(int position, int player) {
        return diagonalSum[position] == player * 4;
    }

    // corners are [0,0], [0,3], [3,0], [3,3]
    private boolean checkCorners(int player) {
        return cornerSum == player * 4;
    }

    public boolean checkWinner(int x, int y, int player) {
        // only need to check diagonals if placed there
        if (x == y) {
            if (checkDiagonal(LEFT_D_POS, player)) {
                return true;
            }
        }
        if (x + y == 3) {
            if (checkDiagonal(RIGHT_D_POS, player)) {
                return true;
            }
        }

        // only need to check corners if placed there
        for (int[] point : CORNERS) {
            if (point[0] == y && point[1] == x) {
                if (checkCorners(player)) {
                    return true;
                }
            }
        }

        // always check row or column or box
        if (checkCol(x, player)) {
            return true;
        }
        if (checkRow(y, player)) {
            return true;
        }
        if (checkBox(x, y, player)) {
            return true;
        }

        return false;
    }

    public boolean anyMovesLeft() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }
}
