package graphics.panels;

import graphics.com.DarkScrollPane;

import javax.swing.*;
import java.awt.*;

public class DefaultPanel extends JPanel
{
    public DefaultPanel()
    {
        super();
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel header = new JLabel("Welcome to Debian Tool App (Control Panel 2), by buronsuave", JLabel.CENTER);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 22));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(header, BorderLayout.NORTH);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setBackground(new Color(50, 50, 50));
        infoArea.setForeground(Color.WHITE);
        infoArea.setFont(new Font("Arial", Font.PLAIN, 16));
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);

        String text = "This tool provides the following options:\n\n"+
                "- Display the current network connection information.\n"+
                "- Show the content of the network interfaces configuration file.\n"+
                "- Display all existing users on your operating system.\n"+
                "- Add or remove a specific user.\n"+
                "- Show a list of all currently running processes.\n"+
                "- Terminate or stop a specific process.\n"+
                "- Change permissions on a specific file or directory.\n"+
                "- Change the owner or group of a specific file or directory.\n"+
                "- Update installed packages and the operating system.";

        infoArea.setText(text);
        infoArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        DarkScrollPane scrollPane = new DarkScrollPane(infoArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
    }
}
