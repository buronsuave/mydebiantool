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

public class ProcessPanel extends JPanel {
    private final DarkTable darkTable;
    private final JLabel header;

    public ProcessPanel() {
        super();
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        header = new JLabel("Running Processes", JLabel.CENTER);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(header, BorderLayout.NORTH);

        String[] columns = {"PID", "User", "CPU%", "Mem%", "Time", "Command"};
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

        loadProcessList();
        DarkScrollPane scrollPane = new DarkScrollPane(darkTable);
        scrollPane.getViewport().setBackground(new Color(30, 30, 30));
        add(scrollPane, BorderLayout.CENTER);
    }

    public void loadProcessList() {
        DefaultTableModel model = (DefaultTableModel) darkTable.getModel();

        // Clear table registers
        int n = model.getRowCount();
        for (int i = 0; i < n; ++i) model.removeRow(0);

        JsonArray processArray;
        try {
            processArray = BashAPI.getProcessList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        for (int i = 0; i < processArray.size(); ++i) {
            if (processArray.get(i).isJsonNull()) continue;
            JsonObject processObject = processArray.get(i).getAsJsonObject();
            String pid = processObject.has("pid") ? processObject.get("pid").getAsString() : "";
            String user = processObject.has("user") ? processObject.get("user").getAsString() : "";
            String cpu = processObject.has("cpu") ? processObject.get("cpu").getAsString() : "";
            String mem = processObject.has("mem") ? processObject.get("mem").getAsString() : "";
            String time = processObject.has("etime") ? processObject.get("etime").getAsString() : "";
            String command = processObject.has("command") ? processObject.get("command").getAsString() : "";

            model.addRow(new Object[]{pid, user, cpu, mem, time, command});
        }
    }
}
