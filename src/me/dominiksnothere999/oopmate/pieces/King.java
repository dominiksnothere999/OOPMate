package me.dominiksnothere999.oopmate.pieces;

// This is the King class. It extends the Piece class.
public class King extends Piece {
    // This is the constructor for the King class.
    public King(PieceColor color, int row, int col) {
        super(color, PieceType.KING, row, col);
    }

    // move() - Overrides the move() method in the Piece class.

    // isValidMove() - Overrides the isValidMove() method in the Piece class.

    // canCastleKingside() - Checks if the king can castle kingside.

    // canCastleQueenside() - Checks if the king can castle queenside.

    // isSquareUnderAttack() - Checks if the square is under attack by any opponent pieces.

    // getHasMoved() - Returns the hasMoved variable.

    // setHasMoved() - Sets the hasMoved variable to true if the king has moved, false otherwise.
}