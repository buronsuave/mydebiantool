package graphics.panels;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import graphics.com.DarkScrollPane;
import graphics.com.DarkTable;
import scripts.BashAPI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InterfacePanel extends JPanel
{
    private final DarkTable darkTable;
    private final JLabel header;

    public InterfacePanel()
    {
        super();
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        header = new JLabel("Network Interfaces Configuration (/etc/network/interfaces)", JLabel.CENTER);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(header, BorderLayout.NORTH);

        String[] columns = {"Parameter", "Value"};
        darkTable = new DarkTable();
        darkTable.setModel(new DefaultTableModel(
                new Object[][]{},
                columns
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        loadInterfaceConfig();
        DarkScrollPane scrollPane = new DarkScrollPane(darkTable);
        scrollPane.getViewport().setBackground(new Color(30, 30, 30));
        add(scrollPane, BorderLayout.CENTER);
    }

    public void loadInterfaceConfig()
    {
        int n = darkTable.getModel().getRowCount();
        for (int i = 0; i < n; ++i) ((DefaultTableModel) darkTable.getModel()).removeRow(0);

        JsonArray configArray;
        try {
            configArray = BashAPI.getInterfaceConfiguration();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        DefaultTableModel model = (DefaultTableModel) darkTable.getModel();
        for (int i = 0; i < configArray.size(); ++i)
        {
            if (configArray.get(i).isJsonNull()) continue;
            JsonObject configEntry = configArray.get(i).getAsJsonObject();
            String parameter = configEntry.has("parameter") ? configEntry.get("parameter").getAsString() : "";
            String value = configEntry.has("value") ? configEntry.get("value").getAsString() : "";
            model.addRow(new Object[]{parameter, value});
        }
    }
}
