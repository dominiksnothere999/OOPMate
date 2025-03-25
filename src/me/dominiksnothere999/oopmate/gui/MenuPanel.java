package me.dominiksnothere999.oopmate.gui;

import me.dominiksnothere999.oopmate.controller.AIGameController;
import me.dominiksnothere999.oopmate.utils.Util;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import java.awt.Graphics;
import java.awt.Color;

// Used for the main menu panel of the game.
public class MenuPanel extends JFrame {
    // Constructor for the MenuPanel class.
    public MenuPanel() {
        // Set the title, default close operation, and block resizing.
        setTitle("OOPMate - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Object for the main panel.
        JPanel mainPanel = getMainPanel();

        // Object for the buttons.
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(Util.setGridLayout(1, 1, 10, 10));
        buttonsPanel.setOpaque(false);

        // Create the button.
        JButton button = createButton();

        button.addActionListener(_ -> {
            dispose();
            selectDifficulty();
        });

        // Pack the GUI together.
        buttonsPanel.add(button);
        mainPanel.add(buttonsPanel);
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    // Select the difficulty for the AI.
    private void selectDifficulty() {
        // Create a dialog box to select the difficulty.
        String[] options = {"Easy", "Medium", "Hard"};
        int response = JOptionPane.showOptionDialog(
            this,
            "Select AI Difficulty",
            "AI Difficulty",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]
        );

        // Select a difficulty.
        AIGameController.Difficulty difficulty = switch (response) {
            case 1 -> AIGameController.Difficulty.MEDIUM;
            case 2 -> AIGameController.Difficulty.HARD;
            default -> AIGameController.Difficulty.EASY;
        };

        // Start the game with the selected difficulty.
        new AIGameController(difficulty).startGame();
    }

    // Create a button for the GUI.
    private JButton createButton() {
        // Define and style the button.
        JButton button = new JButton("Play vs AI");
        button.setPreferredSize(Util.setDimension(200, 50));
        button.setFont(Util.setFont("STANDARD"));
        button.setBackground(Util.LIGHT);
        button.setForeground(Util.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Util.BLACK, 2));
        
        // Add hover effects.
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Util.DARK);
                button.setForeground(Util.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Util.LIGHT);
                button.setForeground(Util.BLACK);
            }
        });

        return button;
    }

    // Create the main panel for the GUI.
    public static JPanel getMainPanel() {
        // Override the paintComponent method to draw the board.
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int size = Util.SQUARE_SIZE;
                for (int row = 0; row < 8; row++) {
                    for (int col = 0; col < 8; col++) {
                        Color squareColor = (row + col) % 2 == 0 ? Util.LIGHT : Util.DARK;
                        g.setColor(squareColor);
                        g.fillRect(col * size, row * size, size, size);
                    }
                }
            }
        };

        // Set the layout and preferred size.
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(Util.setDimension(480, 480));
        return panel;
    }
}