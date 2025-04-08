package me.dominiksnothere999.oopmate.pieces;

import me.dominiksnothere999.oopmate.board.Board;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceColor;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceType;

// This is the Rook class. It extends the Piece class.
public class Rook extends Piece {
    // This variable indicates whether the rook has moved.
    private boolean hasMoved = false;

    // This is the constructor for the Rook class.
    public Rook(PieceColor color, int row, int col) {
        super(color, PieceType.ROOK, row, col);
    }

    // Override the move method from the Piece class.
    @Override
    public void move(Board board, int targetRow, int targetCol) {
        super.move(board, targetRow, targetCol);
        hasMoved = true;
    }

    // Override the isMoveValid method from the Piece class.
    @Override
    public boolean isMoveValid(Board board, int targetRow, int targetCol) {
        // Must be a different position.
        if (targetRow == row && targetCol == col) {
            return false;
        }
        
        // Rook can only move horizontally or vertically.
        if (targetRow != row && targetCol != col) {
            return false;
        }
        
        // Check if the path is clear.
        if (!isPathClear(board, targetRow, targetCol)) {
            return false;
        }
        
        // Check if destination has a piece of the same color.
        Piece targetPiece = board.getSquare(targetRow, targetCol).getPiece();
        return targetPiece == null || targetPiece.getColor() != this.color;
    }

    // Get the hasMoved variable.
    public boolean getHasMoved() {
        return hasMoved;
    }

    // Set the hasMoved variable.
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}