package me.dominiksnothere999.oopmate.gui;

import me.dominiksnothere999.oopmate.board.Board;
import me.dominiksnothere999.oopmate.controller.GameController;
import me.dominiksnothere999.oopmate.pieces.Piece;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceColor;
import me.dominiksnothere999.oopmate.utils.Util;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;

// This is the BoardPanel class, which is used to represent the game board.
public class BoardPanel extends JPanel {
    // The board and game controller.
    private final Board board;
    private final GameController gameController;
    final private Map<String, Image> pieceImages;

    private Piece draggedPiece;

    // Constructor for the BoardPanel class.
    public BoardPanel(Board board, GameController gameController) {
        this.board = board;
        this.gameController = gameController;
        this.pieceImages = new HashMap<>();
        setPreferredSize(Util.setDimension(Util.SQUARE_SIZE * Util.BOARD_SIZE, Util.SQUARE_SIZE * Util.BOARD_SIZE));
        loadPieceImages();
    }

    // Override the paintComponent method to draw the board.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawPieces(g);
    }

    // drawBoard() - Draws the game board on the panel.
    private void drawBoard(Graphics g) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                boolean isLight = (row + col) % 2 == 0;
                g.setColor(isLight ? Util.LIGHT : Util.DARK);
                g.fillRect(col * Util.SQUARE_SIZE, row * Util.SQUARE_SIZE, Util.SQUARE_SIZE, Util.SQUARE_SIZE);
            }
        }
    }
    
    // drawPieces() - Draws the pieces on the board.
    private void drawPieces(Graphics g) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece != null && piece != draggedPiece) {
                    drawPiece(g, piece, row, col);
                }
            }
        }
    }

    // drawPiece() - Draws a single piece on the board.
    private void drawPiece(Graphics g, Piece piece, int row, int col) {
        if (piece == null) {
            return;
        }

        String color = piece.getColor() == PieceColor.WHITE ? "white" : "black";
        String pieceName = piece.getType().toString().toLowerCase();
        String key = color + "_" + pieceName;
        Image img = pieceImages.get(key);
        if (img != null) {
            int x = col * Util.SQUARE_SIZE;
            int y = row * Util.SQUARE_SIZE;
            g.drawImage(img, x, y, Util.SQUARE_SIZE, Util.SQUARE_SIZE, this);
        }
    }

    // drawDraggedPiece() - Draws the piece being dragged by the user.

    // loadPieceImages() - Loads the images for the pieces.
    private void loadPieceImages() {
        String[] pieces = {"pawn", "rook", "knight", "bishop", "queen", "king"};
        String[] colors = {"white", "black"};
        MediaTracker tracker = new MediaTracker(this);
        int imgCount = 0;

        for (String color : colors) {
            for (String piece : pieces) {
                String path = "/resources/" + color + "-" + piece + ".png";
                URL resourceUrl = getClass().getResource(path);
                if (resourceUrl == null) {
                    continue;
                }

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

    // showPawnPromotionDialog() - Displays a dialog for pawn promotion options.

    // isPawnPromotion() - Checks if a pawn is eligible for promotion.

    // canMovePiece() - Determines if a piece can be moved to a specified square.
    
    // makeMove() - Executes the move of a piece on the board.

    // isValidMoveSquare() - Checks if the square is a valid move for the selected piece.
    
    // highlightSelectedAndValidMoves() - Highlights the selected piece and its valid moves.

    // calculateValidMoves() - Calculates the valid moves for the selected piece.

    // getString() - Returns a string representation of the board state.
}