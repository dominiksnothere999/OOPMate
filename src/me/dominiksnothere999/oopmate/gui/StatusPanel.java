package me.dominiksnothere999.oopmate.gui;

import me.dominiksnothere999.oopmate.utils.Util;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;

// This is the StatusPanel class, which is used to display the status of the game.
public class StatusPanel extends JPanel{
    // Define the status label.
    private final JLabel statusLabel;

    // Constructor for the StatusPanel class.
    public StatusPanel() {
        // Set the layout, create the label, and set the background color.
        setLayout(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("White's turn");
        statusLabel.setFont(Util.setFont("STANDARD"));
        add(statusLabel);
        setBackground(Util.LIGHT);
        statusLabel.setForeground(Util.BLACK);
    }
    
}