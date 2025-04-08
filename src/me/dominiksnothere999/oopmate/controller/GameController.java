package me.dominiksnothere999.oopmate.controller;

import static me.dominiksnothere999.oopmate.gui.BoardPanel.getString;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceColor;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceType;
import me.dominiksnothere999.oopmate.pieces.Rook;
import me.dominiksnothere999.oopmate.gui.ChessPanel;
import me.dominiksnothere999.oopmate.pieces.King;
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

    // Check if there are no legal moves available.
    public boolean hasNoLegalMoves(PieceColor color) {
        for (int fromRow = 0; fromRow < Util.BOARD_SIZE; fromRow++) {
            for (int fromCol = 0; fromCol < Util.BOARD_SIZE; fromCol++) {
                // Get the piece at the current position.
                Piece piece = board.getPiece(fromRow, fromCol);
                // Check if the piece is not null and belongs to the current player.
                if (piece != null && piece.getColor() == color) {
                    for (int toRow = 0; toRow < Util.BOARD_SIZE; toRow++) {
                        for (int toCol = 0; toCol < Util.BOARD_SIZE; toCol++) {
                            // Check if the move is not valid.
                            if (piece.isMoveValid(board, toRow, toCol) && isMoveValid(piece, toRow, toCol)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

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

    // Check if the current player's king is in check.
    public boolean isInCheck(PieceColor kingColor) {
        // Find the king of the current player.
        King king = findKing(kingColor);
        if (king == null) return false;
        
        // Get the opponent's color.
        PieceColor opponentColor = (kingColor == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
        
        // Check if any opponent piece can attack the king.
        for (int row = 0; row < Util.BOARD_SIZE; row++) {
            for (int col = 0; col < Util.BOARD_SIZE; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece != null && piece.getColor() == opponentColor) {
                    if (piece.isMoveValid(board, king.getRow(), king.getCol())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Check if the current player's king is in checkmate.
    public boolean isCheckmate(PieceColor kingColor) {
        // Check if the king is in check.
        if (!isInCheck(kingColor)) {
            return false;
        }
        
        // Check if there are any valid moves available for the current player.
        for (int fromRow = 0; fromRow < Util.BOARD_SIZE; fromRow++) {
            for (int fromCol = 0; fromCol < Util.BOARD_SIZE; fromCol++) {
                // Get the piece at the current position.
                Piece piece = board.getPiece(fromRow, fromCol);
                if (piece != null && piece.getColor() == kingColor) {
                    for (int toRow = 0; toRow < Util.BOARD_SIZE; toRow++) {
                        for (int toCol = 0; toCol < Util.BOARD_SIZE; toCol++) {
                            // Check if the move is valid.
                            if (piece.isMoveValid(board, toRow, toCol)) {
                                // Define captured piece and check if the move is a capture.
                                Piece capturedPiece = board.getPiece(toRow, toCol);
                                int originalRow = piece.getRow();
                                int originalCol = piece.getCol();
                                boolean wasKingMoved = false;
                                boolean wasRookMoved = false;
                                
                                // Check if the piece is a king or rook and store its moved status.
                                if (piece instanceof King) {
                                    wasKingMoved = ((King) piece).getHasMoved();
                                } else if (piece instanceof Rook) {
                                    wasRookMoved = ((Rook) piece).getHasMoved();
                                }
                                
                                // Define rook and its original position.
                                Piece rook = null;
                                int rookRow = -1, rookCol = -1;
                                boolean wasRookHasMoved = false;
                                
                                // Check if the move is a castling move.
                                boolean castleCheck = piece instanceof King && Math.abs(toCol - originalCol) == 2;
                                if (castleCheck) {
                                    int rookOrigCol = (toCol > originalCol) ? 7 : 0;
                                    rook = board.getPiece(originalRow, rookOrigCol);
                                    
                                    if (rook instanceof Rook) {
                                        rookRow = rook.getRow();
                                        rookCol = rook.getCol();
                                        wasRookHasMoved = ((Rook) rook).getHasMoved();
                                    }
                                }
                                
                                // Move the piece and check if the king is still in check.
                                piece.move(board, toRow, toCol);
                                boolean stillInCheck = isInCheck(kingColor);
                                
                                // Undo the move.
                                if (castleCheck) {
                                    int rookDestCol = (toCol > originalCol) ? originalCol + 1 : originalCol - 1;
                                    Piece movedRook = board.getPiece(originalRow, rookDestCol);
                                    
                                    if (movedRook instanceof Rook && rook != null) {
                                        board.getSquare(originalRow, rookDestCol).setPiece(null);
                                        board.getSquare(rookRow, rookCol).setPiece(rook);
                                        rook.setPosition(rookRow, rookCol);
                                        assert rook instanceof Rook;
                                        ((Rook) rook).setHasMoved(wasRookHasMoved);
                                    }
                                }
                                
                                // Undo the move of the piece.
                                board.getSquare(toRow, toCol).setPiece(null);
                                board.getSquare(originalRow, originalCol).setPiece(piece);
                                piece.setPosition(originalRow, originalCol);
                                
                                // Restore the captured piece if it was captured.
                                if (capturedPiece != null) {
                                    board.getSquare(toRow, toCol).setPiece(capturedPiece);
                                }
                                
                                // Restore the moved status of the king or rook.
                                if (piece instanceof King) {
                                    ((King) piece).setHasMoved(wasKingMoved);
                                } else if (piece instanceof Rook) {
                                    ((Rook) piece).setHasMoved(wasRookMoved);
                                }
                                
                                // Check if the king is still in check after the move.
                                if (!stillInCheck) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        // If no valid moves are available and the king is in check, it's checkmate.
        return true;
    }

    // Check if a move is valid based on the game rules.
    public boolean isMoveValid(Piece piece, int targetRow, int targetCol) {
        if (piece == null) return false;
        
        // Store original position and potential captured piece.
        int originalRow = piece.getRow();
        int originalCol = piece.getCol();
        Piece capturedPiece = board.getPiece(targetRow, targetCol);
        
        // Track piece movement states to restore after validation.
        boolean wasKingMoved = false;
        boolean wasRookMoved = false;
        Piece rook = null;
        int rookRow = -1, rookCol = -1;
        boolean wasRookHasMoved = false;
        Piece enPassantPawn = null;
        boolean wasJustMadeDoubleMove = false;
        
        // Check for en passant capture conditions.
        if (piece instanceof Pawn && 
            Math.abs(targetCol - originalCol) == 1 && 
            capturedPiece == null) {
            
            // Check for an opponent pawn that just made a double move.
            enPassantPawn = board.getPiece(originalRow, targetCol);
            if (enPassantPawn instanceof Pawn) {
                wasJustMadeDoubleMove = ((Pawn) enPassantPawn).justMadeDoubleMove;
            }
        }
        
        // Store original movement state for kings and rooks.
        if (piece instanceof King) {
            wasKingMoved = ((King) piece).getHasMoved();
            
            // Detect potential castling move.
            if (Math.abs(targetCol - originalCol) == 2) {
                int rookOrigCol = (targetCol > originalCol) ? 7 : 0;
                rook = board.getPiece(originalRow, rookOrigCol);
                
                if (rook instanceof Rook) {
                    rookRow = rook.getRow();
                    rookCol = rook.getCol();
                    wasRookHasMoved = ((Rook) rook).getHasMoved();
                }
            }
        } else if (piece instanceof Rook) {
            wasRookMoved = ((Rook) piece).getHasMoved();
        }
        
        // Variable to store the validation result.
        boolean result;

        // Identify if this move involves castling.
        boolean rookCheck = piece instanceof King && Math.abs(targetCol - originalCol) == 2 && rook instanceof Rook;
        try {
            // Temporarily make the move on the board.
            board.getSquare(originalRow, originalCol).setPiece(null);
            board.getSquare(targetRow, targetCol).setPiece(piece);
            piece.setPosition(targetRow, targetCol);

            // Handle en passant pawn capture.
            if (enPassantPawn instanceof Pawn &&
                    enPassantPawn.getColor() != piece.getColor() &&
                    ((Pawn) enPassantPawn).justMadeDoubleMove) {
                board.getSquare(originalRow, targetCol).setPiece(null);
            }
            
            // Handle rook movement for castling.
            if (rookCheck) {
                int rookDestCol = (targetCol > originalCol) ? originalCol + 1 : originalCol - 1;
                board.getSquare(rookRow, rookCol).setPiece(null);
                board.getSquare(originalRow, rookDestCol).setPiece(rook);
                rook.setPosition(originalRow, rookDestCol);
                ((Rook) rook).setHasMoved(true);
            }
            
            // Update moved flags temporarily.
            if (piece instanceof King) {
                ((King) piece).setHasMoved(true);
            } else if (piece instanceof Rook) {
                ((Rook) piece).setHasMoved(true);
            }
            
            // A move is valid only if it doesn't leave the king in check.
            result = !isInCheck(piece.getColor());
            
        } finally {
            // Restore the board to its original state.
            board.getSquare(targetRow, targetCol).setPiece(capturedPiece);
            board.getSquare(originalRow, originalCol).setPiece(piece);
            piece.setPosition(originalRow, originalCol);
            
            // Restore rook position if castling was attempted.
            if (rookCheck) {
                int rookDestCol = (targetCol > originalCol) ? originalCol + 1 : originalCol - 1;
                
                board.getSquare(originalRow, rookDestCol).setPiece(null);
                board.getSquare(rookRow, rookCol).setPiece(rook);
                rook.setPosition(rookRow, rookCol);
                ((Rook) rook).setHasMoved(wasRookHasMoved);
            }
            
            // Restore en passant pawn state.
            if (enPassantPawn instanceof Pawn) {
                board.getSquare(originalRow, targetCol).setPiece(enPassantPawn);
                ((Pawn) enPassantPawn).justMadeDoubleMove = wasJustMadeDoubleMove;
            }
            
            // Restore original moved flags.
            if (piece instanceof King) {
                ((King) piece).setHasMoved(wasKingMoved);
            } else if (piece instanceof Rook) {
                ((Rook) piece).setHasMoved(wasRookMoved);
            }
        }
        
        return result;
    }

    // convertToChessNotation() - Converts a move to standard chess notation.

    // Find the current player's king on the board.
    private King findKing(PieceColor color) {
        for (int row = 0; row < Util.BOARD_SIZE; row++) {
            for (int col = 0; col < Util.BOARD_SIZE; col++) {
                // Get the piece at the current position.
                Piece piece = board.getPiece(row, col);
                if (piece != null && piece.getType() == PieceType.KING && piece.getColor() == color) {
                    return (King) piece;
                }
            }
        }
        return null;
    }

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