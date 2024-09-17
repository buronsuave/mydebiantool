package app;

import graphics.CustomContainer;
import graphics.CustomToolbar;

import javax.swing.*;
import java.awt.*;

public class DebianToolApp extends JFrame
{
    private final CustomToolbar customToolbar;
    private final CustomContainer customContainer;

    public DebianToolApp()
    {
        setTitle(Constants.APP_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.APP_WINDOW_WIDTH, Constants.APP_WINDOW_HEIGHT);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30));

        customToolbar = new CustomToolbar(this);
        add(customToolbar, BorderLayout.NORTH);

        customContainer = new CustomContainer(this);
        add(customContainer, BorderLayout.CENTER);

        customToolbar.setOpaque(true);
        customContainer.setOpaque(true);
    }

    public void loadContent(int index)
    {
        customContainer.loadContent(index);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new DebianToolApp().setVisible(true));
    }
}
