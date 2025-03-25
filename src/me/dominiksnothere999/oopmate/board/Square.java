package me.dominiksnothere999.oopmate.board;

import me.dominiksnothere999.oopmate.pieces.Piece;

// This is the Square class, which is used to represent a square on the board.
public class Square {
    // The piece on the square.
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