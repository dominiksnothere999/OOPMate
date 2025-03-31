package me.dominiksnothere999.oopmate.gui;

import me.dominiksnothere999.oopmate.utils.Util;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;

import java.awt.BorderLayout;
import java.awt.Insets;

public class MoveHistoryPanel extends JPanel{

    private final JTextArea moveHistoryTextArea;

    public MoveHistoryPanel() { 
        setLayout(new BorderLayout());
        setBackground(Util.LIGHT);
        setBorder(new CompoundBorder(new LineBorder(Util.LIGHT, 2), new EmptyBorder(5, 5, 5, 5)));
        setPreferredSize(Util.setDimension(250, 0));

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(Util.DARK);
        titlePanel.setPreferredSize(Util.setDimension(250, 30));

        JLabel titleLabel = new JLabel("Move History");
        titleLabel.setFont(Util.setFont("STANDARD"));
        titleLabel.setForeground(Util.LIGHT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        moveHistoryTextArea = new JTextArea();
        moveHistoryTextArea.setEditable(false);
        moveHistoryTextArea.setFont(Util.setFont("MONO"));
        moveHistoryTextArea.setBackground(Util.TEXTAREA);
        moveHistoryTextArea.setMargin(new Insets(5, 5, 5, 5));
        moveHistoryTextArea.setLineWrap(true);
        moveHistoryTextArea.setWrapStyleWord(true);

        DefaultCaret caret = (DefaultCaret) moveHistoryTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JScrollPane scrollPane = new JScrollPane(moveHistoryTextArea);
        scrollPane.setBorder(new LineBorder(Util.DARK, 1));

        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
}