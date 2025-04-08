package me.dominiksnothere999.oopmate.pieces;

import me.dominiksnothere999.oopmate.board.Board;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceColor;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceType;

// This is the Bishop class. It extends the Piece class.
public class Bishop extends Piece {
    // This is the constructor for the Bishop class.
    public Bishop(PieceColor color, int row, int col) {
        super(color, PieceType.BISHOP, row, col);
    }

    // Override the isMoveValid method from the Piece class.
    @Override
    public boolean isMoveValid(Board board, int targetRow, int targetCol) {
        // Bishop moves diagonally.
        if (Math.abs(targetRow - row) != Math.abs(targetCol - col)) {
            return false;
        }

        // Check if path is clear.
        if (!isPathClear(board, targetRow, targetCol)) {
            return false;
        }

        // Check if target square is empty or contains enemy piece.
        Piece targetPiece = board.getSquare(targetRow, targetCol).getPiece();
        return targetPiece == null || targetPiece.getColor() != this.color;
    }
}