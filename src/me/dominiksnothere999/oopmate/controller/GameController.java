package me.dominiksnothere999.oopmate.controller;

import me.dominiksnothere999.oopmate.board.Board;
import me.dominiksnothere999.oopmate.gui.ChessPanel;

// This is the GameController class, which is used to control the game.
public class GameController {
    // The board and view.
    private final Board board;
    private final ChessPanel view;
    protected boolean gameInProgress;

    // Constructor for the GameController class.
    public GameController() {
        this.board = new Board();
        this.view = new ChessPanel(this);
    }

    // Start the game.
    public void startGame() {
        this.gameInProgress = true;
        view.setVisible(true);
    }

    // Get the board.
    public Board getBoard() {
        return board;
    }
}