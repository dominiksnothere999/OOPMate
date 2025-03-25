package me.dominiksnothere999.oopmate.gui;

import me.dominiksnothere999.oopmate.controller.GameController;
import javax.swing.JFrame;
import java.awt.BorderLayout;

// This is the ChessPanel class, which is used to represent the game board.
public class ChessPanel extends JFrame {
    // Constructor for the ChessPanel class.
    public ChessPanel(GameController gameController) {
        // Set the title, default close operation, layout, and add the board panel.
        setTitle("OOPMate - Game in Progress!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Object for the board panel.
        BoardPanel boardPanel = new BoardPanel(gameController.getBoard(), gameController);
        
        // Pack the GUI together.
        add(boardPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        pack();
        setResizable(false);
        setVisible(true);
    }
}