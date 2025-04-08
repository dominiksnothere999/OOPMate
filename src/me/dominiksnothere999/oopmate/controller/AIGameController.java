package me.dominiksnothere999.oopmate.controller;

import me.dominiksnothere999.oopmate.board.Board;
import me.dominiksnothere999.oopmate.controller.AIGameController.Difficulty;
import me.dominiksnothere999.oopmate.pieces.Piece;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceColor;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceType;
import me.dominiksnothere999.oopmate.pieces.Queen;
import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.Map;

// This is the AIGameController class, which extends GameController to implement AI opponent functionality.
public class AIGameController extends GameController {
    // The difficulty level of the AI that determines move selection strategy.
    private final Difficulty difficulty;
    private final Random random = new Random();
    
    // Standard piece values used for board evaluation (centipawn values).
    private static final Map<PieceType, Integer> PIECE_VALUES = Map.of(
        PieceType.PAWN, 100,
        PieceType.KNIGHT, 320,
        PieceType.BISHOP, 330,
        PieceType.ROOK, 500,
        PieceType.QUEEN, 900,
        PieceType.KING, 20000
    );

    // A record to represent a chess move with source and destination coordinates.
    private record Move(
        int fromRow,
        int fromCol,
        int toRow,
        int toCol
    ) {}

    // Constructor for the AIGameController class.
    public AIGameController(Difficulty difficulty) {
        super();
        this.difficulty = difficulty;
    }

    // Override the switchTurn() method to handle AI moves when it's the computer's turn.
    @Override
    public void switchTurn() {
        super.switchTurn();

        // If it's Black's turn, make the AI move after a short delay.
        if (getCurrentTurn() == PieceColor.BLACK) {
            SwingUtilities.invokeLater(() -> {
                try {
                    // Add a small delay to make the AI moves visible to the human player.
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // Make the AI move based on the selected difficulty.
                makeAIMove();

                // Switch turn back to the human player.
                super.switchTurn();
            });
        }
    }

    // Select and execute an AI move based on the current difficulty level.
    private void makeAIMove() {
        switch (difficulty) {
            case EASY:
                // Easy difficulty: select completely random valid moves.
                makeRandomMove();
                break;
            case MEDIUM:
                // Medium difficulty: prefer captures over non-captures, but still random.
                makeSmartRandomMove();
                break;
            case HARD:
                // Hard difficulty: evaluate positions and select the best move.
                makeBestMove();
                break;
        }
    }

    // Make a completely random move for the Easy difficulty level.
    private void makeRandomMove() {
        List<Move> validMoves = getAllValidMoves();
        if (!validMoves.isEmpty()) {
            Move move = validMoves.get(random.nextInt(validMoves.size()));
            executeMove(move);
        }
    }

    // Make a "smart random" move for the Medium difficulty level.
    private void makeSmartRandomMove() {
        List<Move> validMoves = getAllValidMoves();
        if (validMoves.isEmpty()) return;

        // Filter to find moves that capture an opponent's piece.
        List<Move> captureMoves = validMoves.stream().filter(move -> getBoard().getPiece(move.toRow, move.toCol) != null).toList();

        // If there are capture moves available, prioritize those.
        if (!captureMoves.isEmpty()) {
            Move move = captureMoves.get(random.nextInt(captureMoves.size()));
            executeMove(move);
            return;
        }

        // Otherwise, make a random move from all valid moves.
        Move move = validMoves.get(random.nextInt(validMoves.size()));
        executeMove(move);
    }

    // Make the best move based on position evaluation for the Hard difficulty level.
    private void makeBestMove() {
        List<Move> validMoves = getAllValidMoves();
        if (validMoves.isEmpty()) return;

        Move bestMove = null;
        int bestScore = Integer.MIN_VALUE;

        // Evaluate each possible move to find the one with the highest score.
        for (Move move : validMoves) {
            Piece piece = getBoard().getPiece(move.fromRow, move.fromCol);
            Piece capturedPiece = getBoard().getPiece(move.toRow, move.toCol);

            // Temporarily make the move to evaluate the resulting position.
            piece.move(getBoard(), move.toRow, move.toCol);

            // Calculate the score for the current board position.
            int score = evaluateBoard(getBoard());

            // Avoid putting yourself in check.
            if (isInCheck(PieceColor.BLACK)) {
                score -= 1000;
            }

            // Bonus for putting opponent in check.
            if (isInCheck(PieceColor.WHITE)) {
                score += 50;
            }

            // Huge bonus for checkmate.
            if (isInCheck(PieceColor.WHITE) && isCheckmate(PieceColor.WHITE)) {
                score += 10000;
            }

            // Undo the temporary move to restore the board state.
            getBoard().getSquare(move.toRow, move.toCol).setPiece(null);
            getBoard().getSquare(move.fromRow, move.fromCol).setPiece(piece);
            piece.setPosition(move.fromRow, move.fromCol);

            // Restore any captured piece.
            if (capturedPiece != null) {
                getBoard().getSquare(move.toRow, move.toCol).setPiece(capturedPiece);
            }

            // Update best move if this move has a higher score.
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        // Execute the best move found, or make a random move if no best move was found.
        if (bestMove != null) {
            executeMove(bestMove);
        } else {
            makeRandomMove();
        }
    }

    // Evaluate the current board position for the AI.
    private int evaluateBoard(Board board) {
        int score = 0;

        // Loop through all squares on the board.
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece != null) {
                    // Get the value of this piece from the piece values map.
                    int pieceValue = PIECE_VALUES.getOrDefault(piece.getType(), 0);
                    
                    // Add value for Black pieces, subtract for White pieces.
                    if (piece.getColor() == PieceColor.BLACK) {
                        score += pieceValue;
                    } else {
                        score -= pieceValue;
                    }
                }
            }
        }

        return score;
    }

    // Execute a move on the board, handling special cases like pawn promotion.
    private void executeMove(Move move) {
        Piece piece = getBoard().getPiece(move.fromRow, move.fromCol);
        if (piece != null) {
            boolean isCapture = getBoard().getPiece(move.toRow, move.toCol) != null;

            // Handle pawn promotion (AI always promotes to Queen for simplicity).
            if (piece.getType() == PieceType.PAWN && ((piece.getColor() == PieceColor.BLACK && move.toRow == 7) || (piece.getColor() == PieceColor.WHITE && move.toRow == 0))) {
                // Execute the pawn move first.
                piece.move(getBoard(), move.toRow, move.toCol);

                // Create a new Queen to replace the pawn.
                Piece promotedPiece = new Queen(piece.getColor(), move.toRow, move.toCol);

                // Replace the pawn with the queen on the board.
                getBoard().getSquare(move.toRow, move.toCol).setPiece(promotedPiece);

                // Record the promotion move in chess notation.
                String from = convertToChessNotation(move.fromRow, move.fromCol);
                String to = convertToChessNotation(move.toRow, move.toCol);
                String moveText = from + (isCapture ? "x" : "-") + to + "=Q";
                getView().getMoveHistory().addMove(moveText, piece.getColor() == PieceColor.WHITE);
            } else {
                // Regular move execution.
                piece.move(getBoard(), move.toRow, move.toCol);
                recordMove(piece, move.fromRow, move.fromCol, move.toRow, move.toCol, isCapture);
            }

            // Update the UI to reflect the move.
            getView().repaint();
        }
    }

    // Enum defining the available AI difficulty levels.
    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }

    // Get a list of all valid moves for the AI .
    private List<Move> getAllValidMoves() {
        List<Move> moves = new ArrayList<>();
        Board board = getBoard();
        
        // Loop through all squares on the board.
        for (int fromRow = 0; fromRow < 8; fromRow++) {
            for (int fromCol = 0; fromCol < 8; fromCol++) {
                Piece piece = board.getPiece(fromRow, fromCol);
                // Only consider Black pieces.
                if (piece != null && piece.getColor() == PieceColor.BLACK) {
                    // Check all possible destination squares.
                    for (int toRow = 0; toRow < 8; toRow++) {
                        for (int toCol = 0; toCol < 8; toCol++) {
                            // Check if the move is valid according to both piece rules and game rules (doesn't leave king in check).
                            if (piece.isMoveValid(board, toRow, toCol)) {
                                if (isMoveValid(piece, toRow, toCol)) {
                                    moves.add(new Move(fromRow, fromCol, toRow, toCol));
                                }
                            }
                        }
                    }
                }
            }
        }

        return moves;
    }
}