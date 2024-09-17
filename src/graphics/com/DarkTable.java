package graphics.com;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DarkTable extends JTable
{
    private final DarkTableHeader header;
    private final DarkTableCell cell;

    public DarkTable()
    {
        header = new DarkTableHeader();
        cell = new DarkTableCell();
        getTableHeader().setDefaultRenderer(header);
        getTableHeader().setPreferredSize(new Dimension(0, 35));
        setDefaultRenderer(Object.class, cell);
        setRowHeight(30);
        setBackground(new Color(30, 30, 30));
    }

    private class DarkTableHeader extends DefaultTableCellRenderer
    {
        private final Map<Integer, Integer> alignment = new HashMap<>();
        public void setAlignment(int column, int align)
        {
            alignment.put(column, align);
        }

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1)
        {
            Component com = super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
            com.setBackground(new Color(30, 30, 30));
            com.setForeground(new Color(200, 200, 200));
            com.setFont(com.getFont().deriveFont(Font.BOLD, 12));
            setHorizontalAlignment(alignment.getOrDefault(i1, JLabel.LEFT));
            return com;
        }
    }

    private class DarkTableCell extends DefaultTableCellRenderer
    {
        private final Map<Integer, Integer> alignment = new HashMap<>();
        public void setAlignment(int column, int align)
        {
            alignment.put(column, align);
        }

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int row, int col)
        {
            Component com = super.getTableCellRendererComponent(jtable, o, bln, bln1, row, col);
            if (isCellSelected(row, col))
            {
                if (row % 2 == 0)
                    com.setBackground(new Color(33, 103, 153));
                else
                    com.setBackground(new Color(29, 86, 127));
            }
            else
            {
                if (row % 2 == 0)
                    com.setBackground(new Color(50, 50, 50));
                else
                    com.setBackground(new Color(30, 30, 30));
            }

            com.setForeground(new Color(200, 200, 200));
            setBorder(new EmptyBorder(0, 5, 0, 5));
            setHorizontalAlignment(alignment.getOrDefault(col, JLabel.LEFT));
            return com;
        }
    }
}
