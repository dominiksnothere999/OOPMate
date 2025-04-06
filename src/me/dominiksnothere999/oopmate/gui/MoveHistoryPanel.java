package me.dominiksnothere999.oopmate.gui;

import me.dominiksnothere999.oopmate.utils.Util;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Insets;

// This is the MoveHistoryPanel class, which is used to display the move history of the game.
public class MoveHistoryPanel extends JPanel{
    // The text area that displays the move history.
    private final JTextArea moveHistoryTextArea;
    private int moveCount = 1;

    // Constructor for the MoveHistoryPanel class.
    public MoveHistoryPanel() {
        // Set the layout, background color, border, and preferred size.
        setLayout(new BorderLayout());
        setBackground(Util.LIGHT);
        setBorder(new CompoundBorder(new LineBorder(Util.LIGHT, 2), new EmptyBorder(5, 5, 5, 5)));
        setPreferredSize(Util.setDimension(250, 0));

        // Create the title panel and label.
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(Util.DARK);
        titlePanel.setPreferredSize(Util.setDimension(250, 30));

        // Create the title label and set its properties.
        JLabel titleLabel = new JLabel("Move History");
        titleLabel.setFont(Util.setFont("STANDARD"));
        titleLabel.setForeground(Util.LIGHT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // Create the move history text area and set its properties.
        moveHistoryTextArea = new JTextArea();
        moveHistoryTextArea.setEditable(false);
        moveHistoryTextArea.setFont(Util.setFont("MONO"));
        moveHistoryTextArea.setBackground(Util.TEXTAREA);
        moveHistoryTextArea.setMargin(new Insets(5, 5, 5, 5));
        moveHistoryTextArea.setLineWrap(true);
        moveHistoryTextArea.setWrapStyleWord(true);

        // Set the caret to always update, so the text area scrolls to the bottom when new moves are added.
        DefaultCaret caret = (DefaultCaret) moveHistoryTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        // Create a scroll pane for the text area and set its properties.
        JScrollPane scrollPane = new JScrollPane(moveHistoryTextArea);
        scrollPane.setBorder(new LineBorder(Util.DARK, 1));

        // Set the preferred size of the scroll pane.
        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    // addMove() - Adds a move to the move history text area.
}