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

    // move() - Moves the piece to the specified row and column.

    // isValidMove() - Checks if the move is valid for the piece.

    // isClearPath() - Checks if the path is clear for the piece to move.

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

    // getColor() - Returns the color of the piece.
    public PieceColor getColor() {
        return color;
    }

    // getType() - Returns the type of the piece.
    public PieceType getType() {
        return type;
    }

    // getRow() - Returns the row of the piece.
    public int getRow() {
        return row;
    }

    // getCol() - Returns the column of the piece.
    public int getCol() {
        return col;
    }

    // setPosition() - Sets the position of the piece to the specified row and column.
}