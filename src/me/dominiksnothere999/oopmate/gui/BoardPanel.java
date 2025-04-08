package me.dominiksnothere999.oopmate.gui;

import me.dominiksnothere999.oopmate.controller.GameController;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceColor;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceType;
import me.dominiksnothere999.oopmate.pieces.King;
import me.dominiksnothere999.oopmate.pieces.Piece;
import me.dominiksnothere999.oopmate.board.Board;
import me.dominiksnothere999.oopmate.utils.Util;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.URL;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

// This is the BoardPanel class, which is used to represent the game board.
public class BoardPanel extends JPanel {
    // The board and game controller.
    private final Board board;
    private final GameController gameController;
    final private Map<String, Image> pieceImages;
    private boolean processingMove = false;

    // The selected piece and its position.
    private Piece selectedPiece;
    private int selectedRow;
    private int selectedCol;
    private final List<Point> validMoves;

    // The piece being dragged by the user.
    private Piece draggedPiece;
    private int draggedPieceX;
    private int draggedPieceY;
    private int dragSourceRow;
    private int dragSourceCol;

    // Constructor for the BoardPanel class.
    public BoardPanel(Board board, GameController gameController) {
        this.board = board;
        this.gameController = gameController;
        this.pieceImages = new HashMap<>();
        this.validMoves = new ArrayList<>();
        setPreferredSize(Util.setDimension(Util.SQUARE_SIZE * Util.BOARD_SIZE, Util.SQUARE_SIZE * Util.BOARD_SIZE));
        loadPieceImages();
        setupMouseListeners();
    }

    // Override the paintComponent method to draw the board.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        highlightSelectedAndValidMoves(g);
        drawPieces(g);
        if (draggedPiece != null) {
            drawDraggedPiece(g);
        }
    }

    // Draw the chessboard, alternating colors for squares.
    private void drawBoard(Graphics g) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                boolean isLight = (row + col) % 2 == 0;
                g.setColor(isLight ? Util.LIGHT : Util.DARK);
                g.fillRect(col * Util.SQUARE_SIZE, row * Util.SQUARE_SIZE, Util.SQUARE_SIZE, Util.SQUARE_SIZE);
            }
        }
    }
    
    // Draw the pieces on the board.
    private void drawPieces(Graphics g) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(row, col);

                // Check if the piece is the one being dragged.
                if (piece != null && piece != draggedPiece) {
                    drawPiece(g, piece, row, col);
                }
            }
        }
    }

    // Draw a specific piece at the given row and column.
    private void drawPiece(Graphics g, Piece piece, int row, int col) {
        if (piece == null) {
            return;
        }

        // Determine the image to draw based on the piece's color and type.
        String color = piece.getColor() == PieceColor.WHITE ? "white" : "black";
        String pieceName = piece.getType().toString().toLowerCase();
        String key = color + "_" + pieceName;
        Image img = pieceImages.get(key);

        // Draw the image at the specified location.
        if (img != null) {
            int x = col * Util.SQUARE_SIZE;
            int y = row * Util.SQUARE_SIZE;
            g.drawImage(img, x, y, Util.SQUARE_SIZE, Util.SQUARE_SIZE, this);
        }
    }

    // Draw the piece being dragged by the user.
    private void drawDraggedPiece(Graphics g) {
        // Determine the image to draw based on the dragged piece's color and type.
        String color = draggedPiece.getColor() == PieceColor.WHITE ? "white" : "black";
        String pieceName = draggedPiece.getType().toString().toLowerCase();
        String key = color + "_" + pieceName;
        Image img = pieceImages.get(key);
        
        // Draw the image at the current mouse position.
        if (img != null) {
            // Calculate the position to draw the image based on the mouse coordinates.
            int x = draggedPieceX - Util.SQUARE_SIZE / 2;
            int y = draggedPieceY - Util.SQUARE_SIZE / 2;
            
            // Set the transparency for the dragged piece.
            Graphics2D g2d = (Graphics2D) g;
            Composite originalComposite = g2d.getComposite();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
            
            // Draw the image at the calculated position.
            g2d.drawImage(img, x, y, Util.SQUARE_SIZE, Util.SQUARE_SIZE, this);
            g2d.setComposite(originalComposite);
        }
    }

    // Load the images for the pieces.
    private void loadPieceImages() {
        String[] pieces = {"pawn", "rook", "knight", "bishop", "queen", "king"};
        String[] colors = {"white", "black"};

        // Tracker to ensure all images are loaded before proceeding.
        MediaTracker tracker = new MediaTracker(this);
        int imgCount = 0;

        for (String color : colors) {
            for (String piece : pieces) {
                // Construct the path to the image resource.
                String path = "/resources/" + color + "-" + piece + ".png";
                URL resourceUrl = getClass().getResource(path);
                if (resourceUrl == null) {
                    continue;
                }

                // Load the image and add it to the tracker.
                ImageIcon icon = new ImageIcon(resourceUrl);
                Image img = icon.getImage();
                tracker.addImage(img, imgCount++);
                String key = color + "_" + piece;
                pieceImages.put(key, img);
            }
        }

        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {
            System.err.println("[ERROR] Image loading was interrupted: " + e.getMessage());
        }
    }

    // Setup mouse listeners for the board.
    private void setupMouseListeners() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            // Override the mousePressed method to handle piece selection and movement.
            @Override
            public void mousePressed(MouseEvent e) {
                if (processingMove) return;

                // Get the row and column of the clicked square.
                int col = e.getX() / Util.SQUARE_SIZE;
                int row = e.getY() / Util.SQUARE_SIZE;

                // Check if the clicked square is within the board boundaries.
                if (row >= 0 && row < 8 && col >= 0 && col < 8) {
                    Piece clickedPiece = board.getSquare(row, col).getPiece();

                    // Check if a piece is already selected and we're clicking elsewhere.
                    if (selectedPiece != null) {
                        // Check if we can move to this square.
                        if (isMoveValidSquare(row, col)) {
                            makeMove(selectedPiece, selectedRow, selectedCol, row, col);
                            selectedPiece = null;
                            validMoves.clear();
                            repaint();
                            return;
                        }
                        // If not valid move, deselect current piece.
                        selectedPiece = null;
                        validMoves.clear();
                        repaint();
                    }

                    // Check if we can move the clicked piece and select it.
                    boolean canMove = canMovePiece(clickedPiece);
                    if (canMove) {
                        selectedPiece = clickedPiece;
                        selectedRow = row;
                        selectedCol = col;
                        calculateValidMoves(clickedPiece);
                        draggedPiece = clickedPiece;
                        dragSourceRow = row;
                        dragSourceCol = col;
                        draggedPieceX = e.getX();
                        draggedPieceY = e.getY();
                        repaint();
                    }
                }
            }

            // Override the mouseReleased method to handle piece dropping.
            @Override
            public void mouseReleased(MouseEvent e) {
                if (draggedPiece != null) {
                    // Get the target row and column based on the mouse release position.
                    int targetCol = e.getX() / Util.SQUARE_SIZE;
                    int targetRow = e.getY() / Util.SQUARE_SIZE;

                    // Check if the target square is within the board boundaries, then make the move.
                    if (targetRow >= 0 && targetRow < 8 && targetCol >= 0 && targetCol < 8) {
                        if (isMoveValidSquare(targetRow, targetCol)) {
                            makeMove(draggedPiece, dragSourceRow, dragSourceCol, targetRow, targetCol);
                            selectedPiece = null;
                            validMoves.clear();
                        }
                    }

                    // Reset the dragged piece and repaint the board.
                    draggedPiece = null;
                    repaint();
                }
            }
        };

        // Mouse motion listener to handle dragging of pieces.
        MouseMotionAdapter mouseMotionAdapter = new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Check if a piece is being dragged.
                if (draggedPiece != null) {
                    draggedPieceX = e.getX();
                    draggedPieceY = e.getY();
                    repaint();
                }
            }
        };

        addMouseListener(mouseAdapter);  
        addMouseMotionListener(mouseMotionAdapter);
    }

    // ##### | showPawnPromotionDialog() - Displays a dialog for pawn promotion options.

    // ##### | isPawnPromotion() - Checks if a pawn is eligible for promotion.

    // Check if the piece can be moved.
    private boolean canMovePiece(Piece piece) {
        if (piece == null) {
            return false;
        }
        return piece.getColor() == gameController.getCurrentTurn();
    }
    
    // Execute the move of a piece on the board.
    private void makeMove(Piece piece, int fromRow, int fromCol, int toRow, int toCol) {
        // Set a flag to indicate that a move is being processed.
        processingMove = true;

        // Handle the move using the game controller.
        try {
            gameController.handlePieceMove(piece, fromRow, fromCol, toRow, toCol);
            repaint();
        } catch (Exception e) {
            System.err.println("[ERROR] Error processing move: " + e.getMessage());
        } finally {
            processingMove = false;
        }
    }

    // Check if the move is valid for the selected piece.
    private boolean isMoveValidSquare(int row, int col) {
        // Check if the selected piece exists.
        if (selectedPiece == null) {
            return false;
        }

        // Check if the target square is within the valid moves.
        if (!selectedPiece.isMoveValid(board, row, col)) {
            return false;
        }

        return gameController.isMoveValid(selectedPiece, row, col);
    }

    // Highlight the selected piece and its valid moves.
    private void highlightSelectedAndValidMoves(Graphics g) {
        // Set the color for highlighting.
        if (draggedPiece != null) {
            g.setColor(new Color(180, 180, 40, 100));
            g.fillRect(dragSourceCol * Util.SQUARE_SIZE, dragSourceRow * Util.SQUARE_SIZE, Util.SQUARE_SIZE, Util.SQUARE_SIZE);
        }
        
        // Highlight the selected piece.
        if (selectedPiece != null && draggedPiece == null) {
            g.setColor(new Color(100, 150, 200, 180));
            g.fillRect(selectedCol * Util.SQUARE_SIZE, selectedRow * Util.SQUARE_SIZE, Util.SQUARE_SIZE, Util.SQUARE_SIZE);
        }
        
        // Highlight the valid moves.
        for (Point move : validMoves) {
            // Get the row and column of the valid move.
            int col = (int) move.getX();
            int row = (int) move.getY();
            
            // Check if the square is occupied by a piece.
            boolean isCapture = board.getPiece(row, col) != null;
            
            // Define the composite for the graphics context.
            Graphics2D g2d = (Graphics2D) g;
            Composite originalComposite = g2d.getComposite();
            
            // Set the color and transparency based on whether it's a capture or not.
            if (isCapture) {
                g2d.setColor(new Color(70, 90, 140, 180));
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                int ringSize = Util.SQUARE_SIZE - 10;
                int offset = (Util.SQUARE_SIZE - ringSize) / 2;
                int strokeWidth = 4;
                
                g2d.setStroke(new BasicStroke(strokeWidth));
                g2d.drawOval(
                    col * Util.SQUARE_SIZE + offset,
                    row * Util.SQUARE_SIZE + offset,
                    ringSize, 
                    ringSize
                );
            } else {
                g2d.setColor(new Color(70, 90, 140, 150));
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
                int dotSize = Util.SQUARE_SIZE / 4;
                int dotOffset = (Util.SQUARE_SIZE - dotSize) / 2;
                
                g2d.fillOval(
                    col * Util.SQUARE_SIZE + dotOffset,
                    row * Util.SQUARE_SIZE + dotOffset,
                    dotSize, 
                    dotSize
                );
            }
            
            // Reset the composite to the original state.
            g2d.setComposite(originalComposite);
        }
    }

    // Calculate the valid moves for the selected piece.
    private void calculateValidMoves(Piece piece) {
        validMoves.clear();
        if (piece == null) return;
        
        // Check if the piece is a King and has not moved yet.
        if (piece instanceof King king && !king.getHasMoved()) {
            int kingRow = king.getRow();
            
            if (king.isMoveValid(board, kingRow, king.getCol() + 2)) {
                // Check if the king can castle to the right.
                validMoves.add(new Point(king.getCol() + 2, kingRow));
            }
            
            if (king.isMoveValid(board, kingRow, king.getCol() - 2)) {
                // Check if the king can castle to the left.
                validMoves.add(new Point(king.getCol() - 2, kingRow));
            }
        }
        
        // Check all squares on the board for valid moves.
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (piece.isMoveValid(board, row, col) &&
                        gameController.isMoveValid(piece, row, col)) {
                    validMoves.add(new Point(col, row));
                }
            }
        }
    }

    // Get the string representation of the piece type.
    public static String getString(PieceType type) {
        return switch (type) {
            case PAWN -> "";
            case KNIGHT -> "N";
            case BISHOP -> "B";
            case ROOK -> "R";
            case QUEEN -> "Q";
            case KING -> "K";
        };
    }
}