package me.dominiksnothere999.oopmate;

import javax.swing.*;

import me.dominiksnothere999.oopmate.gui.MenuPanel;

// Main class for the program.
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPanel menuPanel = new MenuPanel();
            menuPanel.setVisible(true);
        });
    }
}