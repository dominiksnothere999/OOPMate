package me.dominiksnothere999.oopmate.board;

import me.dominiksnothere999.oopmate.pieces.Piece;

// Used for the board of the game to represent a square.
public class Square {
    private Piece piece;

    // Constructor for the Square class.
    public Square() {
        this.piece = null;
    }

    // Returns the piece on the square.
    public Piece getPiece() {
        return piece;
    }

    // Sets the piece on the square.
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}