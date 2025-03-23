package me.dominiksnothere999.oopmate.gui;

import me.dominiksnothere999.oopmate.utils.Util;
import javax.swing.*;

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

        button.addActionListener(e -> {
            dispose();
            // Start the game.
        });

        // Pack the GUI together.
        buttonsPanel.add(button);
        mainPanel.add(buttonsPanel);
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private JButton createButton() {

        // Define and style the button.
        JButton button = new JButton("Play vs AI");
        button.setPreferredSize(Util.setDimension(200, 50));
        button.setFont(Util.setFont("STANDARD"));
        button.setBackground(Util.LIGHT);
        button.setForeground(Util.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Util.DARK, 2));
        
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


    // Returns the main panel for the GUI - NOT FINISHED.
    public static JPanel getMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(Util.setDimension(800, 600));
        panel.setVisible(true);
        return panel;
    }
}