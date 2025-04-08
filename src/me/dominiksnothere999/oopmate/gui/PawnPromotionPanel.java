package me.dominiksnothere999.oopmate.gui;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import me.dominiksnothere999.oopmate.pieces.Piece.PieceColor;
import me.dominiksnothere999.oopmate.pieces.Piece.PieceType;

public class PawnPromotionPanel {
    // The default piece type for promotion.
    private PieceType selectedPieceType = PieceType.QUEEN;

    // Constructor for the PawnPromotionPanel class.
    public PawnPromotionPanel(Frame parent, PieceColor color) {
        super(parent, "Promote Pawn", true);

        // Create a panel with a grid layout for the buttons.
        JPanel panel = new JPanel(new GridLayout(1, 4));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create buttons for each promotion piece with image.
        for (PieceType type : new PieceType[] {
                PieceType.QUEEN, PieceType.ROOK, PieceType.BISHOP, PieceType.KNIGHT
        }) {
            JButton button = createPromotionButton(color, type);
            panel.add(button);
        }

        // Set the panel as the content pane of the dialog.
        setContentPane(panel);
        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    // Create a button for the promotion.
    private JButton createPromotionButton(PieceColor color, PieceType type) {
        String colorStr = color == PieceColor.WHITE ? "white" : "black";
        String pieceStr = type.toString().toLowerCase();

        // Get image from resources.
        String path = "/resources/" + colorStr + "-" + pieceStr + ".png";
        URL url = getClass().getResource(path);
        assert url != null;
        ImageIcon icon = new ImageIcon(url);

        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(80, 80));
        button.setToolTipText(type.toString().charAt(0) + type.toString().substring(1).toLowerCase());

        // Set action to store user choice and close dialog.
        button.addActionListener(e -> {
            selectedPieceType = type;
            dispose();
        });

        return button;
    }

    // Get the selected piece type.
    public PieceType getSelectedPieceType() {
        return selectedPieceType;
    }
}