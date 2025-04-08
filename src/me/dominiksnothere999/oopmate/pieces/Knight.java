package me.dominiksnothere999.oopmate.pieces;

import me.dominiksnothere999.oopmate.pieces.Piece.PieceColor;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceType;
import me.dominiksnothere999.oopmate.board.Board;

// This is the Knight class. It extends the Piece class.
public class Knight extends Piece {
    // This is the constructor for the Knight class.
    public Knight(PieceColor color, int row, int col) {
        super(color, PieceType.KNIGHT, row, col);
    }

    // Override the isMoveValid() method in the Piece class.
    @Override
    public boolean isMoveValid(Board board, int targetRow, int targetCol) {
        int rowDiff = Math.abs(targetRow - row);
        int colDiff = Math.abs(targetCol - col);

        // Knight moves in L-shape (2 squares in one direction and 1 in the other).
        if (!((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))) {
            return false;
        }

        // Check if target square is empty or contains enemy piece.
        Piece targetPiece = board.getSquare(targetRow, targetCol).getPiece();
        return targetPiece == null || targetPiece.getColor() != this.color;
    }
}