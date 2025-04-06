package me.dominiksnothere999.oopmate.controller;

import me.dominiksnothere999.oopmate.pieces.Piece.PieceColor;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceType;
import me.dominiksnothere999.oopmate.gui.ChessPanel;
import me.dominiksnothere999.oopmate.pieces.Piece;
import me.dominiksnothere999.oopmate.board.Board;
import java.util.Stack;

// This is the GameController class, which is used to control the game.
public class GameController {
    // The move record class, which is used to record the moves made in the game.
    private record MoveRecord(
        Piece piece,
        int fromRow,
        int fromCol,
        int toRow,
        int toCol,
        Piece capturedPiece,
        boolean wasPromotion,
        PieceType originalType
    ) {}
    private final Stack<MoveRecord> moveHistory = new Stack<>();

    // The board and view associated with the game.
    private final Board board;
    private final ChessPanel view;
    protected boolean gameInProgress;
    protected PieceColor currentTurn;

    // Constructor for the GameController class.
    public GameController() {
        this.board = new Board();
        this.board.setupInitialPosition();
        this.currentTurn = PieceColor.WHITE;
        this.view = new ChessPanel(this);
        this.gameInProgress = false;
    }

    // Start the game.
    public void startGame() {
        this.gameInProgress = true;
        view.setVisible(true);
        updateBoardStatus();
    }

    // showAIDifficultyDialog() - Shows the AI difficulty dialog.

    // updateBoardStatus() - Updates the status of the board.
    protected void updateBoardStatus() {

    }

    // showGameEndDialog() - Displays the dialog at the end of the game.

    // switchTurn() - Switches the turn between players.

    // endTurn() - Ends the current player's turn.

    // hasNoLegalMoves() - Checks if there are no legal moves available.

    // recordMove() - Records a move made by a player.

    // handlePieceMove() - Handles the movement of a piece.

    // handlePieceMoveWithPromotion() - Handles the movement of a piece with promotion.

    // createPromotionPiece() - Creates a promotion piece for pawn promotion.

    // isInCheck() - Checks if the current player's king is in check.

    // isInCheckmate() - Checks if the current player's king is in checkmate.

    // isMoveValid() - Checks if a move is valid based on the game rules.

    // convertToChessNotation() - Converts a move to standard chess notation.

    // findKing() - Finds the current player's king on the board.

    // getJPanel() - Returns the JPanel associated with the game view.

    // Get the board.
    public Board getBoard() {
        return board;
    }

    // getView() - Returns the view associated with the game controller.

    // getCurrentTurn() - Returns the current player's turn.

    // getCurrentTurnNext() - Returns the next player's turn.
    
    // getPieceNotation() - Returns the notation for the specified piece.
}