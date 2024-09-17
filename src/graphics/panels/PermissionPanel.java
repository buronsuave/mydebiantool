package graphics.panels;

import graphics.com.DarkToolbarButton;
import scripts.BashAPI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PermissionPanel extends JPanel
{
    private final JCheckBox ownerReadCheckBox;
    private final JCheckBox ownerWriteCheckBox;
    private final JCheckBox ownerExecuteCheckBox;
    private final JCheckBox groupReadCheckBox;
    private final JCheckBox groupWriteCheckBox;
    private final JCheckBox groupExecuteCheckBox;
    private final JCheckBox otherReadCheckBox;
    private final JCheckBox otherWriteCheckBox;
    private final JCheckBox otherExecuteCheckBox;
    private final JTextField pathField;
    private final JButton applyButton;

    public PermissionPanel()
    {
        super();
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JLabel header = new JLabel("Change File/Directory Permissions", JLabel.CENTER);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(header, BorderLayout.NORTH);

        // Permissions Panel
        JPanel permissionsPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        permissionsPanel.setBackground(new Color(30, 30, 30));
        permissionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 40, 10));

        // Owner Permissions
        JPanel ownerPanel = new JPanel(new GridLayout(1, 3));
        ownerPanel.setBackground(new Color(50, 50, 50));
        ownerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel ownerPanelHeader = new JLabel("Owner", JLabel.CENTER);
        ownerPanelHeader.setFont(new Font("Arial", Font.BOLD, 16));
        ownerPanelHeader.setForeground(Color.WHITE);
        ownerPanel.add(ownerPanelHeader);
        ownerReadCheckBox = new JCheckBox("Read");
        ownerWriteCheckBox = new JCheckBox("Write");
        ownerExecuteCheckBox = new JCheckBox("Execute");
        ownerReadCheckBox.setBackground(new Color(50, 50, 50));
        ownerWriteCheckBox.setBackground(new Color(50, 50, 50));
        ownerExecuteCheckBox.setBackground(new Color(50, 50, 50));
        ownerReadCheckBox.setForeground(Color.WHITE);
        ownerWriteCheckBox.setForeground(Color.WHITE);
        ownerExecuteCheckBox.setForeground(Color.WHITE);
        ownerPanel.add(ownerReadCheckBox);
        ownerPanel.add(ownerWriteCheckBox);
        ownerPanel.add(ownerExecuteCheckBox);
        permissionsPanel.add(ownerPanel);

        // Group Permissions
        JPanel groupPanel = new JPanel(new GridLayout(1, 3));
        groupPanel.setBackground(new Color(50, 50, 50));
        groupPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel groupPanelHeader = new JLabel("Group", JLabel.CENTER);
        groupPanelHeader.setFont(new Font("Arial", Font.BOLD, 16));
        groupPanelHeader.setForeground(Color.WHITE);
        groupPanel.add(groupPanelHeader);
        groupReadCheckBox = new JCheckBox("Read");
        groupWriteCheckBox = new JCheckBox("Write");
        groupExecuteCheckBox = new JCheckBox("Execute");
        groupReadCheckBox.setBackground(new Color(50, 50, 50));
        groupWriteCheckBox.setBackground(new Color(50, 50, 50));
        groupExecuteCheckBox.setBackground(new Color(50, 50, 50));
        groupReadCheckBox.setForeground(Color.WHITE);
        groupWriteCheckBox.setForeground(Color.WHITE);
        groupExecuteCheckBox.setForeground(Color.WHITE);
        groupPanel.add(groupReadCheckBox);
        groupPanel.add(groupWriteCheckBox);
        groupPanel.add(groupExecuteCheckBox);
        permissionsPanel.add(groupPanel);

        // Other Permissions
        JPanel otherPanel = new JPanel(new GridLayout(1, 3));
        otherPanel.setBackground(new Color(50, 50, 50));
        otherPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel otherPanelHeader = new JLabel("Other", JLabel.CENTER);
        otherPanelHeader.setFont(new Font("Arial", Font.BOLD, 16));
        otherPanelHeader.setForeground(Color.WHITE);
        otherPanel.add(otherPanelHeader);
        otherReadCheckBox = new JCheckBox("Read");
        otherWriteCheckBox = new JCheckBox("Write");
        otherExecuteCheckBox = new JCheckBox("Execute");
        otherReadCheckBox.setBackground(new Color(50, 50, 50));
        otherWriteCheckBox.setBackground(new Color(50, 50, 50));
        otherExecuteCheckBox.setBackground(new Color(50, 50, 50));
        otherReadCheckBox.setForeground(Color.WHITE);
        otherWriteCheckBox.setForeground(Color.WHITE);
        otherExecuteCheckBox.setForeground(Color.WHITE);
        otherPanel.add(otherReadCheckBox);
        otherPanel.add(otherWriteCheckBox);
        otherPanel.add(otherExecuteCheckBox);
        permissionsPanel.add(otherPanel);

        add(permissionsPanel, BorderLayout.CENTER);

        // Path and Apply Button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(30, 30, 30));

        pathField = new JTextField();
        pathField.setBackground(new Color(50, 50, 50));
        pathField.setForeground(Color.WHITE);
        pathField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        pathField.setCaretColor(Color.WHITE);
        JLabel bottomPanelHeader = new JLabel("Path: ", JLabel.RIGHT);
        bottomPanelHeader.setForeground(Color.WHITE);
        bottomPanel.add(bottomPanelHeader, BorderLayout.WEST);
        bottomPanel.add(pathField, BorderLayout.CENTER);

        applyButton = new DarkToolbarButton("Apply Permissions");
        applyButton.addActionListener(e -> applyPermissionsAction());
        bottomPanel.add(applyButton, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void applyPermissionsAction() {
        String path = pathField.getText();
        if (path.isEmpty()) {
            showDarkMessageDialog(this, "Please enter a path", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int ownerPermissions = (ownerReadCheckBox.isSelected() ? 4 : 0) |
                (ownerWriteCheckBox.isSelected() ? 2 : 0) |
                (ownerExecuteCheckBox.isSelected() ? 1 : 0);
        int groupPermissions = (groupReadCheckBox.isSelected() ? 4 : 0) |
                (groupWriteCheckBox.isSelected() ? 2 : 0) |
                (groupExecuteCheckBox.isSelected() ? 1 : 0);
        int otherPermissions = (otherReadCheckBox.isSelected() ? 4 : 0) |
                (otherWriteCheckBox.isSelected() ? 2 : 0) |
                (otherExecuteCheckBox.isSelected() ? 1 : 0);

        String permissions = String.format("%o%o%o", ownerPermissions, groupPermissions, otherPermissions);

        int confirm = showDarkConfirmDialog(this, "Are you sure you want to change permissions to: " + permissions + " for path: " + path + "?", "Confirm Change", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Call BashAPI to change permissions with collected details
            if (BashAPI.changePermission(path, permissions))
                showDarkMessageDialog(this, "Permissions changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                showDarkMessageDialog(this, "Error changing permissions.", "Error", JOptionPane.ERROR_MESSAGE);
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
