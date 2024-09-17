package graphics;

import app.Constants;
import app.DebianToolApp;
import graphics.panels.*;

import javax.swing.*;
import java.awt.*;

public class CustomContainer extends JPanel
{
    private final DebianToolApp context;

    // Panel instances
    private DefaultPanel defaultPanel = null;
    private NetworkPanel networkPanel = null;
    private InterfacePanel interfacePanel = null;
    private UserPanel userPanel = null;
    private AddDeleteUserPanel addDeleteUserPanel = null;
    private ProcessPanel processPanel = null;
    private PauseStopProcessPanel pauseStopProcessPanel = null;
    private PermissionPanel permissionPanel = null;
    private OwnerPanel ownerPanel = null;
    private UpdatePanel updatePanel = null;

    public CustomContainer(DebianToolApp context)
    {
        super();
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        this.context = context;
        loadContent(Constants.DEFAULT_OPT);
    }

    public void loadContent(int index)
    {
        switch (index)
        {
            case Constants.NETWORK_OPT -> loadNetworkContent();
            case Constants.INTERFACE_OPT -> loadInterfacesContent();
            case Constants.USER_OPT -> loadUserContent();
            case Constants.ADD_DEL_USER_OPT -> loadAddDeleteUserContent();
            case Constants.PROCESS_OPT -> loadProcessContent();
            case Constants.PAUSE_STOP_PROCESS_OPT -> loadPauseStopProcessContent();
            case Constants.PERMISSION_OPT -> loadPermissionContent();
            case Constants.OWNER_OPT -> loadOwnerContent();
            case Constants.UPDATE_OPT -> loadUpdateContent();
            default -> loadDefaultFrame();
        }
    }

    private void loadDefaultFrame()
    {
        removeAll();
        if (defaultPanel == null) defaultPanel = new DefaultPanel();
        add(defaultPanel);
        revalidate();
        repaint();
    }

    private void loadNetworkContent()
    {
        removeAll();
        if (networkPanel == null) networkPanel = new NetworkPanel();
        else networkPanel.loadNetworkInformation();
        add(networkPanel);
        revalidate();
        repaint();
    }

    private void loadInterfacesContent()
    {
        removeAll();
        if (interfacePanel == null) interfacePanel = new InterfacePanel();
        else interfacePanel.loadInterfaceConfig();
        add(interfacePanel);
        revalidate();
        repaint();
    }

    private void loadUserContent()
    {
        removeAll();
        if (userPanel == null) userPanel = new UserPanel();
        else userPanel.loadUserList();
        add(userPanel);
        revalidate();
        repaint();
    }

    private void loadAddDeleteUserContent()
    {
        removeAll();
        if (addDeleteUserPanel == null) addDeleteUserPanel = new AddDeleteUserPanel();
        add(addDeleteUserPanel);
        revalidate();
        repaint();
    }

    private void loadProcessContent()
    {
        removeAll();
        if (processPanel == null) processPanel = new ProcessPanel();
        else processPanel.loadProcessList();
        add(processPanel);
        revalidate();
        repaint();
    }

    private void loadPauseStopProcessContent()
    {
        removeAll();
        if (pauseStopProcessPanel == null) pauseStopProcessPanel = new PauseStopProcessPanel();
        add(pauseStopProcessPanel);
        revalidate();
        repaint();
    }

    private void loadPermissionContent()
    {
        removeAll();
        if (permissionPanel == null) permissionPanel = new PermissionPanel();
        add(permissionPanel);
        revalidate();
        repaint();
    }

    private void loadOwnerContent()
    {
        removeAll();
        if (ownerPanel == null) ownerPanel = new OwnerPanel();
        add(ownerPanel);
        revalidate();
        repaint();
    }

    private void loadUpdateContent()
    {
        removeAll();
        if (updatePanel == null) updatePanel = new UpdatePanel();
        add(updatePanel);
        revalidate();
        repaint();
    }
}
