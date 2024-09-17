package graphics.panels;

import graphics.com.DarkToolbarButton;
import scripts.BashAPI;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;

public class OwnerPanel extends JPanel
{
    private final JTextField pathField;
    private final JTextField ownerField;
    private final JTextField groupField;
    private final JButton changeOwnerGroupButton;

    public OwnerPanel()
    {
        super();
        setLayout(new GridLayout(4, 1));

        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JLabel header = new JLabel("Change Owner and/or Group", JLabel.CENTER);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(header);

        // Form Panel for changing owner and group
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(30, 30, 30));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Path field
        JLabel pathLabel = new JLabel("Path:");
        pathLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(pathLabel, gbc);

        pathField = new JTextField(30); // Adjust field width
        pathField.setBackground(new Color(50, 50, 50));
        pathField.setForeground(Color.WHITE);
        pathField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        pathField.setCaretColor(Color.WHITE);
        gbc.gridx = 1;
        formPanel.add(pathField, gbc);

        // Owner field
        JLabel ownerLabel = new JLabel("Owner:");
        ownerLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(ownerLabel, gbc);

        ownerField = new JTextField(15); // Adjust field width
        ownerField.setBackground(new Color(50, 50, 50));
        ownerField.setForeground(Color.WHITE);
        ownerField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        ownerField.setCaretColor(Color.WHITE);
        gbc.gridx = 1;
        formPanel.add(ownerField, gbc);

        // Group field
        JLabel groupLabel = new JLabel("Group:");
        groupLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(groupLabel, gbc);

        groupField = new JTextField(15); // Adjust field width
        groupField.setBackground(new Color(50, 50, 50));
        groupField.setForeground(Color.WHITE);
        groupField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        groupField.setCaretColor(Color.WHITE);
        gbc.gridx = 1;
        formPanel.add(groupField, gbc);

        add(formPanel);

        // Button Panel for actions
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(30, 30, 30));

        changeOwnerGroupButton = new DarkToolbarButton("Change Owner/Group");
        changeOwnerGroupButton.addActionListener(e -> changeOwnerGroupAction());

        buttonPanel.add(changeOwnerGroupButton);
        add(buttonPanel);
    }

    private void changeOwnerGroupAction() {
        String path = pathField.getText();
        String owner = ownerField.getText();
        String group = groupField.getText();

        if (path.isEmpty()) {
            showDarkMessageDialog(this, "Please enter a path", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = showDarkConfirmDialog(this, "Are you sure you want to change the owner and/or group of: " + path + "?", "Confirm Change", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Call BashAPI to change owner and/or group with collected details
            if (BashAPI.changeOwner(path, owner, group))
                showDarkMessageDialog(this, "Owner and/or group changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                showDarkMessageDialog(this, "Error changing owner and/or group.", "Error", JOptionPane.ERROR_MESSAGE);
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
