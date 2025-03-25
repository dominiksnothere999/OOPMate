package me.dominiksnothere999.oopmate.pieces;

// This is the Pawn class. It extends the Piece class.
public class Pawn extends Piece {
    // This is the constructor for the Pawn class.
    public Pawn(PieceColor color, int row, int col) {
        super(color, PieceType.PAWN, row, col);
    }
}