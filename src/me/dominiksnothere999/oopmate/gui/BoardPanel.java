package me.dominiksnothere999.oopmate.gui;

import me.dominiksnothere999.oopmate.controller.GameController;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceColor;
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

    // drawDraggedPiece() - Draws the piece being dragged by the user.
    private void drawDraggedPiece(Graphics g) {

    }

    // loadPieceImages() - Loads the images for the pieces.
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

    // setupMouseListeners() - Sets up mouse listeners for the board interactions.
    private void setupMouseListeners() {

    }


    // showPawnPromotionDialog() - Displays a dialog for pawn promotion options.

    // isPawnPromotion() - Checks if a pawn is eligible for promotion.

    // canMovePiece() - Determines if a piece can be moved to a specified square.
    
    // makeMove() - Executes the move of a piece on the board.

    // isValidMoveSquare() - Checks if the square is a valid move for the selected piece.
    
    // highlightSelectedAndValidMoves() - Highlights the selected piece and its valid moves.
    private void highlightSelectedAndValidMoves(Graphics g) {

    }

    // calculateValidMoves() - Calculates the valid moves for the selected piece.

    // getString() - Returns a string representation of the board state.
}