package me.dominiksnothere999.oopmate.controller;

import static me.dominiksnothere999.oopmate.gui.BoardPanel.getString;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceColor;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceType;
import me.dominiksnothere999.oopmate.gui.ChessPanel;
import me.dominiksnothere999.oopmate.pieces.Pawn;
import me.dominiksnothere999.oopmate.pieces.Piece;
import me.dominiksnothere999.oopmate.board.Board;
import me.dominiksnothere999.oopmate.utils.Util;
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

    // Switch the turn between players.
    public void switchTurn() {
        currentTurn = (currentTurn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
        updateBoardStatus();
        endTurn();
    }

    // End the current player's turn.
    public void endTurn() {
        // Get the previous turn's color.
        PieceColor previousTurn = (currentTurn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
        
        // Reset the justMadeDoubleMove flag for all pawns of the previous turn.
        for (int row = 0; row < Util.BOARD_SIZE; row++) {
            for (int col = 0; col < Util.BOARD_SIZE; col++) {
                Piece piece = board.getSquare(row, col).getPiece();
                if (piece instanceof Pawn && piece.getColor() == previousTurn) {
                    ((Pawn) piece).setJustMadeDoubleMove(false);
                }
            }
        }        
    }

    // hasNoLegalMoves() - Checks if there are no legal moves available.

    // recordMove() - Records a move made by a player.
    public void recordMove(Piece piece, int fromRow, int fromCol, int toRow, int toCol, boolean isCapture) {

    }

    // Handle the movement of a piece.
    public void handlePieceMove(Piece piece, int fromRow, int fromCol, int toRow, int toCol) {
        // Define captured piece and check if the move is a capture.
        Piece capturedPiece = board.getPiece(toRow, toCol);
        boolean isCapture = capturedPiece != null;

        // Add the move to the history and execute the move.
        moveHistory.push(new MoveRecord(piece, fromRow, fromCol, toRow, toCol, capturedPiece, false, piece.getType()));
        piece.move(board, toRow, toCol);
        recordMove(piece, fromRow, fromCol, toRow, toCol, isCapture);
        switchTurn();
    }

    // handlePieceMoveWithPromotion() - Handles the movement of a piece with promotion.

    // createPromotionPiece() - Creates a promotion piece for pawn promotion.

    // isInCheck() - Checks if the current player's king is in check.

    // isInCheckmate() - Checks if the current player's king is in checkmate.

    // isMoveValid() - Checks if a move is valid based on the game rules.
    public boolean isValidMove(Piece piece, int targetRow, int targetCol) {
        return true; // FIX LATER!
    }

    // convertToChessNotation() - Converts a move to standard chess notation.

    // findKing() - Finds the current player's king on the board.

    // getJPanel() - Returns the JPanel associated with the game view.

    // Get the board.
    public Board getBoard() {
        return board;
    }

    // Get the view.
    public ChessPanel getView() {
        return view;
    }

    // Get the current player's turn.
    public PieceColor getCurrentTurn() {
        return currentTurn;
    }

    // Get the next player's turn.
    public String getCurrentTurnText() {
        return currentTurn == PieceColor.WHITE ? "White's turn" : "Black's turn";
    }
    
    // Get the piece notation for a given piece type.
    public String getPieceNotation(PieceType type) {
        return getString(type);
    }
}