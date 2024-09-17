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

public class NetworkPanel extends JPanel
{
    private final DarkTable darkTable;
    private final JLabel header;

    public NetworkPanel()
    {
        super();
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        header = new JLabel("Current Network Information", JLabel.CENTER);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(header, BorderLayout.NORTH);

        String[] columns = {"Interface", "MAC Address", "IP Address", "Broadcast", "Family", "Scope"};
        darkTable = new DarkTable();
        darkTable.setModel(new DefaultTableModel(
                new Object [][]{},
                columns
        ){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        });
        loadNetworkInformation();
        DarkScrollPane scrollPane = new DarkScrollPane(darkTable);
        scrollPane.getViewport().setBackground(new Color(30, 30, 30));
        add(scrollPane, BorderLayout.CENTER);
    }

    public void loadNetworkInformation()
    {
        int n = darkTable.getModel().getRowCount();
        for (int i = 0; i < n; ++i) ((DefaultTableModel) darkTable.getModel()).removeRow(0);

        JsonArray networkArray;
        try { networkArray = BashAPI.getNetworkInformation();}
        catch (Exception e) { System.out.println(e.getMessage()); return; }

        for (int i = 0; i < networkArray.size(); ++i)
        {
            if (networkArray.get(i).isJsonNull()) continue;
            JsonObject networkObject = networkArray.get(i).getAsJsonObject();
            String interfaceName = "";
            String mac = "";

            if (networkObject.has("ifname"))
                interfaceName = networkObject.get("ifname").getAsString();

            if (networkObject.has("address"))
                mac = networkObject.get("address").getAsString();

            if (!networkObject.has("addr_info"))
            {
                ((DefaultTableModel) darkTable.getModel()).addRow(new Object[]{interfaceName, mac, "", "", "", ""});
                continue;
            }

            JsonArray addressArray = networkObject.get("addr_info").getAsJsonArray();
            for (int j = 0; j < addressArray.size(); ++j)
            {
                if (addressArray.get(j).isJsonNull()) continue;
                JsonObject addrInfo = addressArray.get(j).getAsJsonObject();
                String ip = "";
                String broadcast = "";
                String family = "";
                String scope = "";

                if (addrInfo.has("local"))
                    ip = addrInfo.get("local").getAsString();

                if (addrInfo.has("broadcast"))
                    broadcast = addrInfo.get("broadcast").getAsString();

                if (addrInfo.has("family"))
                    family = addrInfo.get("family").getAsString();

                if (addrInfo.has("scope"))
                    scope = addrInfo.get("scope").getAsString();

                ((DefaultTableModel) darkTable.getModel()).addRow(new Object[]{interfaceName, mac, ip, broadcast, family, scope});
            }
        }
    }
}
