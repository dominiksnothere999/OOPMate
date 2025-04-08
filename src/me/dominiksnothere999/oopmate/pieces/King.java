package me.dominiksnothere999.oopmate.pieces;

// This is the King class. It extends the Piece class.
public class King extends Piece {
    // This variable indicates whether the king has moved.
    private boolean hasMoved = false;

    // This is the constructor for the King class.
    public King(PieceColor color, int row, int col) {
        super(color, PieceType.KING, row, col);
    }

    // move() - Overrides the move() method in the Piece class.

    // isValidMove() - Overrides the isValidMove() method in the Piece class.

    // canCastleKingside() - Checks if the king can castle kingside.

    // canCastleQueenside() - Checks if the king can castle queenside.

    // isSquareUnderAttack() - Checks if the square is under attack by any opponent pieces.

    // Get the hasMoved variable.
    public boolean getHasMoved() {
        return hasMoved;
    }

    // Set the hasMoved variable.
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}