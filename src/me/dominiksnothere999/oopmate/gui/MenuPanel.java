package me.dominiksnothere999.oopmate.gui;

import javax.swing.*;
import java.awt.*;

// Used for the main menu panel of the game.
public class MenuPanel extends JFrame {

    // Constructor for the MenuPanel class.
    public MenuPanel() {
        setTitle("GUI Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        JPanel mainPanel = getMainPanel();
        add(mainPanel);
        setSize(800, 600);        
        setLocationRelativeTo(null);
    }

    // Returns the main panel for the GUI.
    public static JPanel getMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(800, 600));
        panel.setVisible(true);
        return panel;
    }
}