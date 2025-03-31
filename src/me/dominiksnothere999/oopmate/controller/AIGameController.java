package me.dominiksnothere999.oopmate.controller;

// This is the AIGameController class, which is used to control the game against an AI.
public class AIGameController extends GameController {
    // The difficulty of the AI.
    private final Difficulty difficulty;

    // Constructor for the AIGameController class.
    public AIGameController(Difficulty difficulty) {
        super();
        this.difficulty = difficulty;
    }

    // switchTurn() - Overrides the switchTurn() method in the GameController class.

    // makeAIMove() - Makes a move for the AI based on the difficulty level.

    // makeRandomMove() - Makes a random move for the AI.

    // makeSmartRandomMove() - Makes a smart random move for the AI.

    // makeBestMove() - Makes the best move for the AI based on the difficulty level.

    // evaluateBoard() - Evaluates the board position for the AI.

    // executeMove() - Executes a move on the board.
    
    // Get the difficulty of the AI.
    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }

    // getAllValidMoves() - Returns a list of all valid moves for the AI.

    // Move(...) - Moves a piece on the board.
}