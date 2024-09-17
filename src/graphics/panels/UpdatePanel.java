package graphics.panels;

import graphics.com.DarkScrollPane;
import graphics.com.DarkToolbarButton;
import scripts.BashAPI;

import javax.swing.*;
import java.awt.*;

public class UpdatePanel extends JPanel {
    private final JTextArea outputArea;
    private final DarkToolbarButton updateButton;

    public UpdatePanel() {
        super();
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JLabel header = new JLabel("Update Packages and OS", JLabel.CENTER);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(header, BorderLayout.NORTH);

        // Update Button
        updateButton = new DarkToolbarButton("Update System");
        updateButton.addActionListener(e -> performUpdate());

        // Output Area
        outputArea = new JTextArea();
        outputArea.setBackground(new Color(50, 50, 50));
        outputArea.setForeground(Color.WHITE);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 14));
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(30, 30, 30));
        buttonPanel.add(updateButton);

        DarkScrollPane scrollPane = new DarkScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(buttonPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void performUpdate() {
        String output;
        outputArea.setText("Starting process...\n");
        repaint();
        try { Thread.sleep(150); }
        catch (Exception e) { System.out.println(e.getMessage()); }
        try {
            output = BashAPI.updateSystem();
        } catch (Exception e) {
            output = "Error: " + e.getMessage();
        }
        outputArea.append(output);
    }
}