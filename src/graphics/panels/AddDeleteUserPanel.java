package graphics.panels;

import graphics.com.DarkToolbarButton;
import scripts.BashAPI;

import javax.swing.*;
import java.awt.*;

public class AddDeleteUserPanel extends JPanel {
    private final JLabel header;
    private final JTextField usernameField;
    private final JTextField fullNameField;
    private final JPasswordField passwordField;
    private final JButton addUserButton;
    private final JButton deleteUserButton;

    public AddDeleteUserPanel() {
        super();
        setLayout(new GridLayout(3, 1));

        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        header = new JLabel("Add or Delete Users", JLabel.CENTER);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(header);

        // Form Panel for adding users
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(30, 30, 30));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Username field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);

        usernameField = new JTextField(15); // Adjust field width
        usernameField.setBackground(new Color(50, 50, 50));
        usernameField.setForeground(Color.WHITE);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        usernameField.setCaretColor(Color.WHITE);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        // Full Name field
        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(fullNameLabel, gbc);

        fullNameField = new JTextField(15); // Adjust field width
        fullNameField.setBackground(new Color(50, 50, 50));
        fullNameField.setForeground(Color.WHITE);
        fullNameField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        fullNameField.setCaretColor(Color.WHITE);
        gbc.gridx = 1;
        formPanel.add(fullNameField, gbc);

        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(15); // Adjust field width
        passwordField.setBackground(new Color(50, 50, 50));
        passwordField.setForeground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        passwordField.setCaretColor(Color.WHITE);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        add(formPanel);

        // Button Panel for actions
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(30, 30, 30));

        addUserButton = new DarkToolbarButton("Add User");
        deleteUserButton = new DarkToolbarButton("Delete User");

        addUserButton.addActionListener(e -> addUserAction());
        deleteUserButton.addActionListener(e -> deleteUserAction());

        buttonPanel.add(addUserButton);
        buttonPanel.add(deleteUserButton);

        add(buttonPanel);
    }

    private void addUserAction() {
        String username = usernameField.getText();
        String fullName = fullNameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || fullName.isEmpty() || password.isEmpty()) {
            showDarkMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = showDarkConfirmDialog(this, "Are you sure you want to add user: " + username + "?", "Confirm Add", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Call BashAPI to add user with collected details
            if (BashAPI.addUser(username, fullName, password))
                showDarkMessageDialog(this, "User added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                showDarkMessageDialog(this, "Error adding user.","Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteUserAction() {
        String username = usernameField.getText();
        if (username.isEmpty()) {
            showDarkMessageDialog(this, "Please enter a username", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = showDarkConfirmDialog(this, "Are you sure you want to delete user: " + username + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Call BashAPI to delete user
            if (BashAPI.deleteUser(username))
                showDarkMessageDialog(this, "User deleted successfully! (or never existed, who knows)", "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                showDarkMessageDialog(this, "Error deleting user: ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int showDarkConfirmDialog(Component parent, String message, String title, int optionType) {
        UIManager.put("OptionPane.background", new Color(30, 30, 30));
        UIManager.put("Panel.background", new Color(30, 30, 30));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Button.background", new Color(30, 30, 30));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.borderPainted", false);
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