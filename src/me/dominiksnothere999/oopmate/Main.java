package me.dominiksnothere999.oopmate;

import me.dominiksnothere999.oopmate.gui.MenuPanel;
import javax.swing.SwingUtilities;

// This is the Main class, which is used to start the game.
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPanel menuPanel = new MenuPanel();
            menuPanel.setVisible(true);
        });
    }
}