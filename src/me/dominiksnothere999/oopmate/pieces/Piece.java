package me.dominiksnothere999.oopmate.pieces;

import me.dominiksnothere999.oopmate.board.Board;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceColor;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceType;

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

    // Perform the move on the board.
    public void move(Board board, int targetRow, int targetCol){
        board.getSquare(row, col).setPiece(null);
        this.row = targetRow;
        this.col = targetCol;
        board.getSquare(targetRow, targetCol).setPiece(this);
    }

    // Check if the move is valid.
    public boolean isValidMove(Board board, int toRow, int toCol) {
        return row != toRow || col != toCol;
    }

    // Check if the path is clear for the piece to move.
    protected boolean isPathClear(Board board, int targetRow, int targetCol) {
        // Check if the target square is within the bounds of the board.
        int rowDirection = Integer.compare(targetRow, row);
        int colDirection = Integer.compare(targetCol, col);

        // Check if the target square is in the same row, column, or diagonal.
        int currentRow = row + rowDirection;
        int currentCol = col + colDirection;

        // Check if the path is clear.
        while (currentRow != targetRow || currentCol != targetCol) {
            // Check if the current square is within the bounds of the board.
            if (board.getSquare(currentRow, currentCol).getPiece() != null) {
                return false;
            }

            // Move to the next square in the direction of the target square.
            currentRow += rowDirection;
            currentCol += colDirection;

            // Check if the current square is within the bounds of the board.
            if ((rowDirection != 0 && colDirection != 0) && (Math.abs(currentRow - targetRow) != Math.abs(currentCol - targetCol))) {
                return false;
            }
        }
        return true;
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

    // Get the color of the piece.
    public PieceColor getColor() {
        return color;
    }

    // Get the type of the piece.
    public PieceType getType() {
        return type;
    }

    // Get the row of the piece.
    public int getRow() {
        return row;
    }

    // Get the column of the piece.
    public int getCol() {
        return col;
    }

    // Set the position of the piece.
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }
}