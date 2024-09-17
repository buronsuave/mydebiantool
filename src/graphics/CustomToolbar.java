package graphics;

import app.Constants;
import app.DebianToolApp;
import graphics.com.DarkToolbarButton;

import javax.swing.*;
import java.awt.*;

public class CustomToolbar extends JToolBar
{
    private final DebianToolApp context;
    private final JButton networkButton;
    private final JButton interfaceButton;
    private final JButton userButton;
    private final JButton addDelUserButton;
    private final JButton processButton;
    private final JButton pauseStopProcessButton;
    private final JButton permissionButton;
    private final JButton ownerButton;
    private final JButton updateButton;

    public CustomToolbar(DebianToolApp context)
    {
        super();
        this.context = context;
        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createEmptyBorder());

        networkButton = new DarkToolbarButton("Networks");
        networkButton.addActionListener(actionEvent -> {
            defaultButtons();
            networkButton.setBackground(new Color(60, 60, 60));
            handleNetworkButton();
        });

        interfaceButton = new DarkToolbarButton("Interfaces");
        interfaceButton.addActionListener(actionEvent -> {
            defaultButtons();
            interfaceButton.setBackground(new Color(60, 60, 60));
            handleInterfaceButton();
        });

        userButton = new DarkToolbarButton("Users");
        userButton.addActionListener(actionEvent -> {
            defaultButtons();
            userButton.setBackground(new Color(60, 60, 60));
            handleUserButton();
        });

        addDelUserButton = new DarkToolbarButton("A/D User");
        addDelUserButton.addActionListener(actionEvent -> {
            defaultButtons();
            addDelUserButton.setBackground(new Color(60, 60, 60));
            handleAddDelUserButton();
        });

        processButton = new DarkToolbarButton("Processes");
        processButton.addActionListener(actionEvent -> {
            defaultButtons();
            processButton.setBackground(new Color(60, 60, 60));
            handleProcessButton();
        });

        pauseStopProcessButton = new DarkToolbarButton("P/S Process");
        pauseStopProcessButton.addActionListener(actionEvent -> {
            defaultButtons();
            pauseStopProcessButton.setBackground(new Color(60, 60, 60));
            handlePauseStopProcessButton();
        });

        permissionButton = new DarkToolbarButton("Permissions");
        permissionButton.addActionListener(actionEvent -> {
            defaultButtons();
            permissionButton.setBackground(new Color(60, 60, 60));
            handlePermissionButton();
        });

        ownerButton = new DarkToolbarButton("Ownership");
        ownerButton.addActionListener(actionEvent -> {
            defaultButtons();
            ownerButton.setBackground(new Color(60, 60, 60));
            handleOwnerButton();
        });

        updateButton = new DarkToolbarButton("Update");
        updateButton.addActionListener(actionEvent -> {
            defaultButtons();
            updateButton.setBackground(new Color(60, 60, 60));
            handleUpdateButton();
        });

        add(networkButton);
        add(interfaceButton);
        add(userButton);
        add(addDelUserButton);
        add(processButton);
        add(pauseStopProcessButton);
        add(permissionButton);
        add(ownerButton);
        add(updateButton);
    }

    private void defaultButtons()
    {
        Color DEFAULT_COLOR = new Color(30, 30, 30);
        networkButton.setBackground(DEFAULT_COLOR);
        interfaceButton.setBackground(DEFAULT_COLOR);
        userButton.setBackground(DEFAULT_COLOR);
        addDelUserButton.setBackground(DEFAULT_COLOR);
        processButton.setBackground(DEFAULT_COLOR);
        pauseStopProcessButton.setBackground(DEFAULT_COLOR);
        permissionButton.setBackground(DEFAULT_COLOR);
        ownerButton.setBackground(DEFAULT_COLOR);
        updateButton.setBackground(DEFAULT_COLOR);
    }

    private void handleNetworkButton()
    {
        context.loadContent(Constants.NETWORK_OPT);
    }

    private void handleInterfaceButton()
    {
        context.loadContent(Constants.INTERFACE_OPT);
    }

    private void handleUserButton()
    {
        context.loadContent(Constants.USER_OPT);
    }

    private void handleAddDelUserButton()
    {
        context.loadContent(Constants.ADD_DEL_USER_OPT);
    }

    private void handleProcessButton()
    {
        context.loadContent(Constants.PROCESS_OPT);
    }

    private void handlePauseStopProcessButton()
    {
        context.loadContent(Constants.PAUSE_STOP_PROCESS_OPT);
    }

    private void handlePermissionButton()
    {
        context.loadContent(Constants.PERMISSION_OPT);
    }

    private void handleOwnerButton()
    {
        context.loadContent(Constants.OWNER_OPT);
    }

    private void handleUpdateButton()
    {
        context.loadContent(Constants.UPDATE_OPT);
    }
}