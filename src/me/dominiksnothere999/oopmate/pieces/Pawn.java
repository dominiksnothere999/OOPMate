package me.dominiksnothere999.oopmate.pieces;

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

    //setJustMadeDoubleMove() - Sets the justMadeDoubleMove variable to true.
}