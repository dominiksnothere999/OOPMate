package me.dominiksnothere999.oopmate.pieces;

// This is the Piece class, which is used to represent a piece on the board.
public class Piece {
    // The properties of the piece.
    protected final PieceColor color;
    protected final PieceType type;
    protected int row;
    protected int col;

    // Constructor for the Piece class.
    public Piece(PieceColor color, PieceType type, int row, int col) {
        this.color = color;
        this.type = type;
        this.row = row;
        this.col = col;
    }

    // Colors of the piece.
    public enum PieceColor {
        WHITE,
        BLACK
    }

    // Types of the piece.
    public enum PieceType {
        PAWN,
        ROOK,
        KNIGHT,
        BISHOP,
        QUEEN,
        KING
    }
}