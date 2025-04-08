package me.dominiksnothere999.oopmate.pieces;

import me.dominiksnothere999.oopmate.pieces.Piece.PieceColor;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceType;
import me.dominiksnothere999.oopmate.board.Board;

// This is the King class. It extends the Piece class.
public class King extends Piece {
    // This variable indicates whether the king has moved.
    private boolean hasMoved = false;

    // This is the constructor for the King class.
    public King(PieceColor color, int row, int col) {
        super(color, PieceType.KING, row, col);
    }

    // Override the move() method in the Piece class.
    @Override
    public void move(Board board, int targetRow, int targetCol) {
        int colDiff = targetCol - col;
        
        // Check for castling.
        if (!hasMoved && Math.abs(colDiff) == 2) {
            // Kingside castling (to the right).
            if (colDiff > 0) {
                // Move the rook.
                Piece rook = board.getPiece(row, 7);
                board.getSquare(row, 7).setPiece(null);
                board.getSquare(row, col + 1).setPiece(rook);
                if (rook instanceof Rook) {
                    rook.setPosition(row, col + 1);
                    ((Rook) rook).setHasMoved(true);
                }
            } 
            // Queenside castling (to the left).
            else {
                // Move the rook.
                Piece rook = board.getPiece(row, 0);
                board.getSquare(row, 0).setPiece(null);
                board.getSquare(row, col - 1).setPiece(rook);
                if (rook instanceof Rook) {
                    rook.setPosition(row, col - 1);
                    ((Rook) rook).setHasMoved(true);
                }
            }
        }
        
        // Regular move.
        super.move(board, targetRow, targetCol);
        hasMoved = true;
    }

    // Override the isMoveValid() method in the Piece class.
    @Override
    public boolean isMoveValid(Board board, int targetRow, int targetCol) {
        int rowDiff = Math.abs(targetRow - row);
        int colDiff = Math.abs(targetCol - col);

        // Regular king move: one square in any direction.
        if (rowDiff <= 1 && colDiff <= 1) {
            // Check if target square is empty or contains enemy piece.
            Piece targetPiece = board.getSquare(targetRow, targetCol).getPiece();
            return targetPiece == null || targetPiece.getColor() != this.color;
        }
        
        // Check for castling.
        if (!hasMoved && rowDiff == 0 && colDiff == 2) {
            // The king cannot castle if it is in check.
            if (isSquareUnderAttack(board, row, col)) {
                return false;
            }
            
            // Kingside castling (to the right).
            if (targetCol > col) {
                return canCastleKingside(board);
            }
            // Queenside castling (to the left).
            else {
                return canCastleQueenside(board);
            }
        }
        
        return false;
    }

    // Check if the king can castle kingside.
    private boolean canCastleKingside(Board board) {
        // Check if the path is clear.
        if (board.getPiece(row, col + 1) != null || board.getPiece(row, col + 2) != null) {
            return false;
        }
        
        // Check if the rook is in the correct position and hasn't moved.
        Piece rookPiece = board.getPiece(row, 7);
        if (!(rookPiece instanceof Rook) || ((Rook) rookPiece).getHasMoved()) {
            return false;
        }
        
        // Check if the king is in check or if castling through check.
        return !isSquareUnderAttack(board, row, col) && !isSquareUnderAttack(board, row, col + 1) && !isSquareUnderAttack(board, row, col + 2);
    }

    // Check if the king can castle queenside.
    private boolean canCastleQueenside(Board board) {
        // Check if the path is clear.
        if (board.getPiece(row, col - 1) != null || board.getPiece(row, col - 2) != null || board.getPiece(row, col - 3) != null) {
            return false;
        }
        
        // Check if the rook is in the correct position and hasn't moved.
        Piece rookPiece = board.getPiece(row, 0);
        if (!(rookPiece instanceof Rook) || ((Rook) rookPiece).getHasMoved()) {
            return false;
        }
        
        // Check if the king is in check or if castling through check.
        return !isSquareUnderAttack(board, row, col) && !isSquareUnderAttack(board, row, col - 1) && !isSquareUnderAttack(board, row, col - 2);
    }

    // Check if the square is under attack by any opponent pieces.
    private boolean isSquareUnderAttack(Board board, int squareRow, int squareCol) {
        // Check if any opponent piece can move to this square.
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece piece = board.getPiece(r, c);
                if (piece != null && piece.getColor() != this.color) {
                    // For pawns, use special logic since they capture diagonally.
                    if (piece instanceof Pawn) {
                        int direction = piece.getColor() == PieceColor.WHITE ? -1 : 1;
                        int pawnRow = piece.getRow();
                        int pawnCol = piece.getCol();
                        
                        // Check if pawn can capture diagonally to this square.
                        if (pawnRow + direction == squareRow && Math.abs(pawnCol - squareCol) == 1) {
                            // Square is under attack.
                            return true;
                        }
                    }

                    // For enemy king, use direct distance check to avoid recursion.
                    else if (piece instanceof King) {
                        int rowDiff = Math.abs(piece.getRow() - squareRow);
                        int colDiff = Math.abs(piece.getCol() - squareCol);
                        // King can attack any adjacent square (distance of 1 in any direction).
                        if (rowDiff <= 1 && colDiff <= 1) {
                            return true;
                        }
                    } 

                    // For other pieces, verify they can move to this square.
                    else if (piece.isMoveValid(board, squareRow, squareCol)) {
                        // For pieces that need a clear path, verify the path is clear.
                        if (piece instanceof Bishop || piece instanceof Rook || piece instanceof Queen) {
                            if (piece.isPathClear(board, squareRow, squareCol)) {
                                // Square is under attack.
                                return true;
                            }
                        } else {
                            return true; // Other pieces don't need path checking.
                        }
                    }
                }
            }
        }

        // No piece is attacking this square
        return false;
    }

    // Get the hasMoved variable.
    public boolean getHasMoved() {
        return hasMoved;
    }

    // Set the hasMoved variable.
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}