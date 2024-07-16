import org.example.Board;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    private static final int[][] drawBoard = {{1, 1, -1, -1},
            {-1, -1, 1, 1},
            {1, 1, -1, -1},
            {-1, -1, 1, 1}};


    @Test
    public void testAnyMovesFalse() {
        Board board = new Board(drawBoard);
        assertFalse(board.anyMovesLeft());
        assertFalse(board.checkWinner(0,0,1));
    }

    @Test
    public void testAnyMovesTrue() {
        Board board = new Board();
        assertTrue(board.anyMovesLeft());
    }

    @Test
    public void checkEmptyBoard() {
        Board board = new Board();
        assertTrue(board.anyMovesLeft());
        assertFalse(board.checkWinner(1,1, 1));
    }

    @Test
    public void checkColBoard() {
        Board board = new Board();
        board.placePiece(0,0, -1);
        board.placePiece(1,0, -1);
        board.placePiece(2,0, -1);
        board.placePiece(3,0, -1);
        assertTrue(board.checkWinner(0,0,-1));
    }

    @Test
    public void checkRowBoard() {
        Board board = new Board();
        board.placePiece(0,0, 1);
        board.placePiece(0,1, 1);
        board.placePiece(0,2, 1);
        board.placePiece(0,3, 1);
        assertTrue(board.checkWinner(0,0,1));
    }

    @Test
    public void checkLeftDiagonalBoard() {
        Board board = new Board();
        board.placePiece(0,0, 1);
        board.placePiece(1,1, 1);
        board.placePiece(2,2, 1);
        board.placePiece(3,3, 1);
        assertTrue(board.checkWinner(0,0,1));
    }

    @Test
    public void checkRightDiagonalBoard() {
        Board board = new Board();
        board.placePiece(3,0, 1);
        board.placePiece(2,1, 1);
        board.placePiece(1,2, 1);
        board.placePiece(0,3, 1);
        assertTrue(board.checkWinner(0,3,1));
    }

    @Test
    public void checkCornerBoard() {
        Board board = new Board();
        board.placePiece(0,0, -1);
        board.placePiece(0,3, -1);
        board.placePiece(3,0, -1);
        board.placePiece(3,3, -1);
        assertTrue(board.checkWinner(0,3,-1));
    }

    @Test
    public void checkBoxBoard() {
        Board board = new Board();
        board.placePiece(0,0, -1);
        board.placePiece(0,1, -1);
        board.placePiece(1,0, -1);
        board.placePiece(1,1, -1);
        assertTrue(board.checkWinner(0,1,-1));
    }
}
