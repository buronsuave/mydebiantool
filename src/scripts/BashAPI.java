package scripts;

import app.Constants;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public final class BashAPI
{
    private static String execute(String... command) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) result.append(line).append("\n");
        process.waitFor();
        return result.toString();
    }

    public static JsonArray getNetworkInformation() throws Exception
    {
        String[] command = {Constants.BASH_PATH, Constants.NETWORK_SCRIPT_PATH};
        return JsonParser
                .parseString
                        (execute(command))
                .getAsJsonArray();
    }

    public static JsonArray getInterfaceConfiguration() throws Exception
    {
        String[] command = {Constants.BASH_PATH, Constants.INTERFACE_SCRIPT_PATH};
        return JsonParser
                .parseString
                        (execute(command))
                .getAsJsonArray();
    }

    public static JsonArray getUserList() throws Exception
    {
        String[] command = {Constants.BASH_PATH, Constants.USER_SCRIPT_PATH};
        return JsonParser
                .parseString
                        (execute(command))
                .getAsJsonArray();
    }

    public static boolean addUser(String username, String fullname, String password)
    {
        String[] command = {Constants.BASH_PATH, Constants.ADD_USER_SCRIPT_PATH, username, fullname, password};
        try
        {
            execute(command);
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean deleteUser(String username)
    {
        String[] command = {Constants.BASH_PATH, Constants.DELETE_USER_SCRIPT_PATH, username};
        try
        {
            execute(command);
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static JsonArray getProcessList() throws Exception
    {
        String[] command = {Constants.BASH_PATH, Constants.PROCESS_SCRIPT_PATH};
        return JsonParser
                .parseString
                        (execute(command))
                .getAsJsonArray();
    }

    public static boolean pauseProcess(String pid)
    {
        String[] command = {Constants.BASH_PATH, Constants.PAUSE_PROCESS_SCRIPT_PATH, pid};
        try
        {
            execute(command);
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean resumeProcess(String pid)
    {
        String[] command = {Constants.BASH_PATH, Constants.RESUME_PROCESS_SCRIPT_PATH, pid};
        try
        {
            execute(command);
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean killProcess(String pid)
    {
        String[] command = {Constants.BASH_PATH, Constants.KILL_PROCESS_SCRIPT_PATH, pid};
        try
        {
            execute(command);
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean changePermission(String path, String permissions)
    {
        String[] command = {Constants.BASH_PATH, Constants.PERMISSION_SCRIPT_PATH, path, permissions};
        try
        {
            execute(command);
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean changeOwner(String path, String owner, String group)
    {
        String[] command = {Constants.BASH_PATH, Constants.OWNER_SCRIPT_PATH, path, owner, group};
        try
        {
            execute(command);
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static String updateSystem()
    {
        String[] command = {Constants.BASH_PATH, Constants.UPDATE_SCRIPT_PATH};
        try
        {
            return execute(command);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
