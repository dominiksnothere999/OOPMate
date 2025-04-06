package me.dominiksnothere999.oopmate.controller;

import me.dominiksnothere999.oopmate.controller.AIGameController.Difficulty;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceType;
import java.util.Random;
import java.util.Map;

// This is the AIGameController class, which is used to control the game against an AI.
public class AIGameController extends GameController {
    // The difficulty level of the AI, with map for piece values.
    private final Difficulty difficulty;
    private final Random random = new Random();
    private static final Map<PieceType, Integer> PIECE_VALUES = Map.of(
        PieceType.PAWN, 100,
        PieceType.KNIGHT, 320,
        PieceType.BISHOP, 330,
        PieceType.ROOK, 500,
        PieceType.QUEEN, 900,
        PieceType.KING, 20000
    );

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