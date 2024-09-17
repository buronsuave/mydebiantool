package graphics.com;

import javax.swing.*;
import java.awt.*;

public class DarkToolbarButton extends JButton
{
    public DarkToolbarButton(String text)
    {
        super(text);
        setBackground(new Color(30, 30, 30));
        setForeground(new Color(255, 255, 255));
        setBorderPainted(false);
        setFocusPainted(false);
        setMargin(new Insets(5, 10, 5, 10));
    }
}
