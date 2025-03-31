package me.dominiksnothere999.oopmate.pieces;

// This is the Queen class. It extends the Piece class.
public class Queen  extends Piece {
    // This is the constructor for the Queen class.
    public Queen(PieceColor color, int row, int col) {
        super(color, PieceType.QUEEN, row, col);
    }

    // isValidMove() - Overrides the isValidMove() method in the Piece class.
}