package me.dominiksnothere999.oopmate.pieces;

import me.dominiksnothere999.oopmate.board.Board;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceColor;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceType;

// This is the Pawn class. It extends the Piece class.
public class Pawn extends Piece {
    // This variable indicates whether the pawn has moved.
    private boolean hasMoved = false;
    public boolean justMadeDoubleMove = false;

    // This is the constructor for the Pawn class.
    public Pawn(PieceColor color, int row, int col) {
        super(color, PieceType.PAWN, row, col);
    }

    // Override the move() method in the Piece class.
    @Override
    public void move(Board board, int targetRow, int targetCol) {
        justMadeDoubleMove = !hasMoved && Math.abs(targetRow - row) == 2;

        // Handle en passant capture.
        if (Math.abs(targetCol - col) == 1 && board.getSquare(targetRow, targetCol).getPiece() == null) {
            // Remove the captured pawn.
            board.getSquare(row, targetCol).setPiece(null);
        }

        super.move(board, targetRow, targetCol);
        hasMoved = true;
    }

    // Override the isMoveValid() method in the Piece class.
    @Override
    public boolean isMoveValid(Board board, int targetRow, int targetCol) {
        int direction = (color == PieceColor.WHITE) ? -1 : 1;
        int rowDiff = targetRow - row;
        int colDiff = Math.abs(targetCol - col);

        // Basic forward movement.
        if (colDiff == 0) {
            // Single square advance.
            if (rowDiff == direction) {
                return board.getSquare(targetRow, targetCol).getPiece() == null;
            }
            // Initial double square advance.
            if (!hasMoved && rowDiff == 2 * direction) {
                return board.getSquare(targetRow, targetCol).getPiece() == null && board.getSquare(row + direction, col).getPiece() == null;
            }
        }
        // Diagonal capture (including en passant).
        else if (colDiff == 1 && rowDiff == direction) {
            Piece targetPiece = board.getSquare(targetRow, targetCol).getPiece();

            // Normal capture.
            if (targetPiece != null && targetPiece.getColor() != this.color) {
                return true;
            }

            // En passant - check if adjacent pawn just made a double move.
            if (targetPiece == null) {
                Piece adjacentPiece = board.getSquare(row, targetCol).getPiece();
                return adjacentPiece instanceof Pawn && adjacentPiece.getColor() != this.color && ((Pawn) adjacentPiece).justMadeDoubleMove;
            }
        }

        return false;
    }

    // Set the justMadeDoubleMove variable.
        public void setJustMadeDoubleMove(boolean value) {
        justMadeDoubleMove = value;
    }
}