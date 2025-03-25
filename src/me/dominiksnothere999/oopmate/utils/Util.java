package me.dominiksnothere999.oopmate.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

// Utility class for the game.
public class Util {
    // Define the colors.
    public static final Color BLACK = new Color(20, 20, 20);
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color DARK = new Color(50, 70, 120);
    public static final Color LIGHT = new Color(220, 220, 220);

    // Define the board sizes.
    public static final int SQUARE_SIZE = 60;
    public static final int BOARD_SIZE = 8;

    // Set the font.
    public static Font setFont(String type) {
        if (type.equals("STANDARD")) {
            return new Font("SF Pro Display", Font.BOLD, 16);
        } else if (type.equals("SMALL")) {
            return new Font("SF Pro Display", Font.BOLD, 14);
        } else if (type.equals("MONO")) {
            return new Font("Monospaced", Font.BOLD, 14);
        } else {
            return new Font("Arial", Font.BOLD, 16);
        }
    }

    // Set the dimension.
    public static Dimension setDimension(int width, int height) {
        return new Dimension(width, height);
    }

    // Set the grid layout.
    public static GridLayout setGridLayout(int rows, int cols, int hgap, int vgap) {
        return new GridLayout(rows, cols, hgap, vgap);
    }
}