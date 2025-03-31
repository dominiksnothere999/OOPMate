package me.dominiksnothere999.oopmate.gui;

import me.dominiksnothere999.oopmate.board.Board;
import me.dominiksnothere999.oopmate.controller.GameController;
import me.dominiksnothere999.oopmate.utils.Util;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

// This is the BoardPanel class, which is used to represent the game board.
public class BoardPanel extends JPanel {
    // The board and game controller.
    private final Board board;
    private final GameController gameController;

    // Constructor for the BoardPanel class.
    public BoardPanel(Board board, GameController gameController) {
        this.board = board;
        this.gameController = gameController;
        setPreferredSize(Util.setDimension(Util.SQUARE_SIZE * Util.BOARD_SIZE, Util.SQUARE_SIZE * Util.BOARD_SIZE));
    }

    // Override the paintComponent method to draw the board.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < Util.BOARD_SIZE; row++) {
            for (int col = 0; col < Util.BOARD_SIZE; col++) {
                Color squareColor = (row + col) % 2 == 0 ? Util.LIGHT : Util.DARK;
                g.setColor(squareColor);
                g.fillRect(col * Util.SQUARE_SIZE, row * Util.SQUARE_SIZE, 
                           Util.SQUARE_SIZE, Util.SQUARE_SIZE);
            }
        }
    }

    // drawBoard() - Draws the game board on the panel.
    
    // drawPieces() - Draws the pieces on the board.

    // drawPiece() - Draws a single piece on the board.

    // drawDraggedPiece() - Draws the piece being dragged by the user.

    // loadPieceImages() - Loads the images for the pieces.

    // setupMouseListeners() - Sets up mouse listeners for the board interactions.

    // showPawnPromotionDialog() - Displays a dialog for pawn promotion options.

    // isPawnPromotion() - Checks if a pawn is eligible for promotion.

    // canMovePiece() - Determines if a piece can be moved to a specified square.
    
    // makeMove() - Executes the move of a piece on the board.

    // isValidMoveSquare() - Checks if the square is a valid move for the selected piece.
    
    // highlightSelectedAndValidMoves() - Highlights the selected piece and its valid moves.

    // calculateValidMoves() - Calculates the valid moves for the selected piece.

    // getString() - Returns a string representation of the board state.
}