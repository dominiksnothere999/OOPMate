package me.dominiksnothere999.oopmate.pieces;

import me.dominiksnothere999.oopmate.pieces.Piece.PieceColor;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceType;
import me.dominiksnothere999.oopmate.board.Board;

// This is the Queen class. It extends the Piece class.
public class Queen  extends Piece {
    // This is the constructor for the Queen class.
    public Queen(PieceColor color, int row, int col) {
        super(color, PieceType.QUEEN, row, col);
    }

    // Override the isMoveValid() method in the Piece class.
    @Override
    public boolean isMoveValid(Board board, int targetRow, int targetCol) {
        // Queen can move like a rook or bishop.
        boolean isDiagonal = Math.abs(targetRow - row) == Math.abs(targetCol - col);
        boolean isStraight = row == targetRow || col == targetCol;

        if (!(isDiagonal || isStraight)) {
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