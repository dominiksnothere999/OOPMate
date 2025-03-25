package me.dominiksnothere999.oopmate.board;

import me.dominiksnothere999.oopmate.utils.Util;

// This is the Board class, which is used to represent the game board.
public class Board {
    // The squares on the board.
    private final Square[][] squares;

    // Constructor for the Board class.
    public Board() {
        squares = new Square[Util.BOARD_SIZE][Util.BOARD_SIZE];
        initializeSquares();
    }
    
    // Initialize the squares on the board.
    private void initializeSquares() {
        for (int row = 0; row < Util.BOARD_SIZE; row++) {
            for (int col = 0; col < Util.BOARD_SIZE; col++) {
                squares[row][col] = new Square();
            }
        }
    }
}