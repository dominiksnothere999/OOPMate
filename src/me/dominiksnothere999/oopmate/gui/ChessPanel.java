package me.dominiksnothere999.oopmate.gui;

import me.dominiksnothere999.oopmate.controller.GameController;
import javax.swing.JFrame;
import java.awt.BorderLayout;

// This is the ChessPanel class, which is used to represent the game board.
public class ChessPanel extends JFrame {
    // Define additional panels.
    private final StatusPanel statusPanel;

    // Constructor for the ChessPanel class.
    public ChessPanel(GameController gameController) {
        // Set the title, default close operation, layout, and add the board panel.
        setTitle("OOPMate - Game in Progress!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Objects for the panels panel.
        BoardPanel boardPanel = new BoardPanel(gameController.getBoard(), gameController);
        this.statusPanel = new StatusPanel();

        // Pack the GUI together.
        add(boardPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        pack();
        setResizable(false);
        setVisible(true);
    }
}