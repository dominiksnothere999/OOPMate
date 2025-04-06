package me.dominiksnothere999.oopmate.board;

import me.dominiksnothere999.oopmate.pieces.Piece.PieceColor;
import me.dominiksnothere999.oopmate.utils.Util;
import me.dominiksnothere999.oopmate.pieces.*;

// This is the Board class, which is used to represent the game board.
public class Board {
    // The squares on the board.
    private final Square[][] squares;

    // Constructor for the Board class.
    public Board() {
        squares = new Square[Util.BOARD_SIZE][Util.BOARD_SIZE];
        initializeBoard();
    }
    
    // Initialize the squares on the board.
    private void initializeBoard() {
        for (int row = 0; row < Util.BOARD_SIZE; row++) {
            for (int col = 0; col < Util.BOARD_SIZE; col++) {
                squares[row][col] = new Square();
            }
        }
    }

    // Setup the initial position of all the pieces on the board.
    public void setupInitialPosition() {
        squares[7][0].setPiece(new Rook(PieceColor.WHITE, 7, 0));
        squares[7][1].setPiece(new Knight(PieceColor.WHITE, 7, 1));
        squares[7][2].setPiece(new Bishop(PieceColor.WHITE, 7, 2));
        squares[7][3].setPiece(new Queen(PieceColor.WHITE, 7, 3));
        squares[7][4].setPiece(new King(PieceColor.WHITE, 7, 4));
        squares[7][5].setPiece(new Bishop(PieceColor.WHITE, 7, 5));
        squares[7][6].setPiece(new Knight(PieceColor.WHITE, 7, 6));
        squares[7][7].setPiece(new Rook(PieceColor.WHITE, 7, 7));

        for (int col = 0; col < Util.BOARD_SIZE; col++) {
            squares[6][col].setPiece(new Pawn(PieceColor.WHITE, 6, col));
        }

        squares[0][0].setPiece(new Rook(PieceColor.BLACK, 0, 0));
        squares[0][1].setPiece(new Knight(PieceColor.BLACK, 0, 1));
        squares[0][2].setPiece(new Bishop(PieceColor.BLACK, 0, 2));
        squares[0][3].setPiece(new Queen(PieceColor.BLACK, 0, 3));
        squares[0][4].setPiece(new King(PieceColor.BLACK, 0, 4));
        squares[0][5].setPiece(new Bishop(PieceColor.BLACK, 0, 5));
        squares[0][6].setPiece(new Knight(PieceColor.BLACK, 0, 6));
        squares[0][7].setPiece(new Rook(PieceColor.BLACK, 0, 7));

        for (int col = 0; col < Util.BOARD_SIZE; col++) {
            squares[1][col].setPiece(new Pawn(PieceColor.BLACK, 1, col));
        }
    }

    // Get a square on the board.
    public Square getSquare(int row, int col) {
        return squares[row][col];
    }

    // Get a piece on the board.
    public Piece getPiece(int row, int col) {
        return squares[row][col].getPiece();
    }
}