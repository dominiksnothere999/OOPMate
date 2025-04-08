package me.dominiksnothere999.oopmate.pieces;

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

    // move() - Overrides the move() method in the Piece class.

    // isValidMove() - Overrides the isValidMove() method in the Piece class.

    // Set the justMadeDoubleMove variable.
        public void setJustMadeDoubleMove(boolean value) {
        justMadeDoubleMove = value;
    }
}