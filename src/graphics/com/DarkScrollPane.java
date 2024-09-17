package graphics.com;

import javax.swing.*;
import java.awt.*;

public class DarkScrollPane extends JScrollPane
{
    public DarkScrollPane(Component c)
    {
        super(c);
    }

    @Override
    public JScrollBar createVerticalScrollBar()
    {
        return new DarkScrollBar(JScrollBar.VERTICAL);
    }

    @Override
    public JScrollBar createHorizontalScrollBar()
    {
        return new DarkScrollBar(JScrollBar.HORIZONTAL);
    }
}
