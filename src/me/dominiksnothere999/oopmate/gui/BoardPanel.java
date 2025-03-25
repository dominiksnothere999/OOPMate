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
    
}