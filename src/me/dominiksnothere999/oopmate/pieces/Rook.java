package me.dominiksnothere999.oopmate.pieces;

// This is the Rook class. It extends the Piece class.
public class Rook extends Piece {
    // This variable indicates whether the rook has moved.
    private boolean hasMoved = false;

    // This is the constructor for the Rook class.
    public Rook(PieceColor color, int row, int col) {
        super(color, PieceType.ROOK, row, col);
    }

    // move() - Override the move method from the Piece class.

    // isValidMove() - Override the isValidMove method from the Piece class.

    // getHasMoved() - Returns true if the rook has moved, false otherwise.

    // setHasMoved() - Sets the hasMoved variable to true if the rook has moved, false otherwise.
}