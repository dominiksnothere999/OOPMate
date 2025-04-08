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

    // isMoveValid() - Override the isMoveValid method from the Piece class.

    // Get the hasMoved variable.
    public boolean getHasMoved() {
        return hasMoved;
    }

    // Set the hasMoved variable.
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}