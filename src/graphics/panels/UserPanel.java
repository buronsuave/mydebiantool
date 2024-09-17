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

public class UserPanel extends JPanel {
    private final DarkTable darkTable;
    private final JLabel header;

    public UserPanel() {
        super();
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        header = new JLabel("System Users", JLabel.CENTER);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(header, BorderLayout.NORTH);

        String[] columns = {"Username", "UID", "GID", "Home Directory"};
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

        loadUserList();
        DarkScrollPane scrollPane = new DarkScrollPane(darkTable);
        scrollPane.getViewport().setBackground(new Color(30, 30, 30));
        add(scrollPane, BorderLayout.CENTER);
    }

    public void loadUserList() {
        DefaultTableModel model = (DefaultTableModel) darkTable.getModel();

        // Clear table registers
        int n = model.getRowCount();
        for (int i = 0; i < n; ++i ) model.removeRow(0);

        JsonArray userArray;
        try {
            userArray = BashAPI.getUserList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        for (int i = 0; i < userArray.size(); ++i) {
            if (userArray.get(i).isJsonNull()) continue;
            JsonObject userObject = userArray.get(i).getAsJsonObject();
            String username = userObject.has("username") ? userObject.get("username").getAsString() : "";
            String uid = userObject.has("uid") ? userObject.get("uid").getAsString() : "";
            String gid = userObject.has("gid") ? userObject.get("gid").getAsString() : "";
            String home = userObject.has("home") ? userObject.get("home").getAsString() : "";

            model.addRow(new Object[]{username, uid, gid, home});
        }
    }
}

