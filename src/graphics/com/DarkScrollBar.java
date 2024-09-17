package graphics.com;

import javax.swing.*;
import java.awt.*;

public class DarkScrollBar extends JScrollBar
{
    public DarkScrollBar(int orientation)
    {
        setUI(new DarkScrollBarUI());
        setOrientation(orientation);
        setPreferredSize(new Dimension(8, 8));
        setForeground(new Color(8, 144, 216));
        setBackground(new Color(30, 30, 30));
    }
}
