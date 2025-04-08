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
 
    // Update the status label with the given text.
    public void updateStatus(String message) {
        statusLabel.setText(message);
        
        // Determine which player's turn it is by looking at the message.
        boolean isBlackTurn = message.toLowerCase().startsWith("black");
        
        // Set appropriate colors based on the turn.
        if (isBlackTurn) {
            setBackground(Util.DARK);
            statusLabel.setForeground(Util.LIGHT);
        } else {
            setBackground(Util.LIGHT);
            statusLabel.setForeground(Util.DARK);
        }
    }
}