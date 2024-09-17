package graphics.panels;

import graphics.com.DarkToolbarButton;
import scripts.BashAPI;

import javax.swing.*;
import java.awt.*;

public class PauseStopProcessPanel extends JPanel
{
    private final JLabel header;
    private final JTextField pidField;
    private final JButton stopProcessButton;
    private final JButton pauseProcessButton;
    private final JButton resumeProcessButton;

    public PauseStopProcessPanel()
    {
        super();
        setLayout(new GridLayout(3, 1));
        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        header = new JLabel("Manage Processes", JLabel.CENTER);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(header);

        // Form Panel for PID
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(30, 30, 30));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // PID field
        JLabel pidLabel = new JLabel("PID:");
        pidLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(pidLabel, gbc);

        pidField = new JTextField(15); // Adjust field width
        pidField.setBackground(new Color(50, 50, 50));
        pidField.setForeground(Color.WHITE);
        pidField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        pidField.setCaretColor(Color.WHITE);
        gbc.gridx = 1;
        formPanel.add(pidField, gbc);

        add(formPanel);

        // Button Panel for actions
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(30, 30, 30));

        stopProcessButton = new DarkToolbarButton("Stop Process");
        pauseProcessButton = new DarkToolbarButton("Pause Process");
        resumeProcessButton = new DarkToolbarButton("Resume Process");

        stopProcessButton.addActionListener(e -> stopProcessAction());
        pauseProcessButton.addActionListener(e -> pauseProcessAction());
        resumeProcessButton.addActionListener(e -> resumeProcessAction());

        buttonPanel.add(stopProcessButton);
        buttonPanel.add(pauseProcessButton);
        buttonPanel.add(resumeProcessButton);

        add(buttonPanel);
    }

    private void stopProcessAction() {
        String pid = pidField.getText();
        if (pid.isEmpty()) {
            showDarkMessageDialog(this, "Please enter a PID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = showDarkConfirmDialog(this, "Are you sure you want to stop process with PID: " + pid + "?", "Confirm Stop", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (BashAPI.killProcess(pid)) {
                showDarkMessageDialog(this, "Process stopped successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                showDarkMessageDialog(this, "Error stopping process.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void pauseProcessAction() {
        String pid = pidField.getText();
        if (pid.isEmpty()) {
            showDarkMessageDialog(this, "Please enter a PID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = showDarkConfirmDialog(this, "Are you sure you want to pause process with PID: " + pid + "?", "Confirm Pause", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (BashAPI.pauseProcess(pid)) {
                showDarkMessageDialog(this, "Process paused successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                showDarkMessageDialog(this, "Error pausing process.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void resumeProcessAction() {
        String pid = pidField.getText();
        if (pid.isEmpty()) {
            showDarkMessageDialog(this, "Please enter a PID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = showDarkConfirmDialog(this, "Are you sure you want to resume process with PID: " + pid + "?", "Confirm Resume", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (BashAPI.resumeProcess(pid)) {
                showDarkMessageDialog(this, "Process resumed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                showDarkMessageDialog(this, "Error resuming process.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private int showDarkConfirmDialog(Component parent, String message, String title, int optionType) {
        UIManager.put("OptionPane.background", new Color(30, 30, 30));
        UIManager.put("Panel.background", new Color(30, 30, 30));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Button.background", new Color(30, 30, 30));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.borderPainted", false);
        UIManager.put("Button.margin", new Insets(5, 10, 5, 10));

        return JOptionPane.showConfirmDialog(parent, message, title, optionType);
    }

    private void showDarkMessageDialog(Component parent, String message, String title, int messageType) {
        UIManager.put("OptionPane.background", new Color(30, 30, 30));
        UIManager.put("Panel.background", new Color(30, 30, 30));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Button.background", new Color(30, 30, 30));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.borderPainted", false);
        UIManager.put("Button.focusPainted", false);
        UIManager.put("Button.margin", new Insets(5, 10, 5, 10));

        JOptionPane.showMessageDialog(parent, message, title, messageType);
    }
}
