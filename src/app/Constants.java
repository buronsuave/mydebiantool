package app;

public final class Constants {
    private static final String CONSTANTS_INSTANCE_ERROR = "Cannot instantiate Constants class";

    private Constants() {
        throw new AssertionError(CONSTANTS_INSTANCE_ERROR);
    }

    public static final String APP_NAME = "Debian Tool App";
    public static final int APP_WINDOW_WIDTH = 800;
    public static final int APP_WINDOW_HEIGHT = 600;

    public static final int DEFAULT_OPT = -1;
    public static final int NETWORK_OPT = 0;
    public static final int INTERFACE_OPT = 1;
    public static final int USER_OPT = 2;
    public static final int ADD_DEL_USER_OPT = 3;
    public static final int PROCESS_OPT = 4;
    public static final int PAUSE_STOP_PROCESS_OPT = 5;
    public static final int PERMISSION_OPT = 6;
    public static final int OWNER_OPT = 7;
    public static final int UPDATE_OPT = 8;

    public static final String BASH_PATH = "/bin/bash";
    public static final String NETWORK_SCRIPT_PATH = "/home/buronsuave/IdeaProjects/MyDebianTool/src/scripts/network.sh";
    public static final String INTERFACE_SCRIPT_PATH = "/home/buronsuave/IdeaProjects/MyDebianTool/src/scripts/interface.sh";
    public static final String USER_SCRIPT_PATH = "/home/buronsuave/IdeaProjects/MyDebianTool/src/scripts/user.sh";
    public static final String ADD_USER_SCRIPT_PATH = "/home/buronsuave/IdeaProjects/MyDebianTool/src/scripts/adduser.sh";
    public static final String DELETE_USER_SCRIPT_PATH = "/home/buronsuave/IdeaProjects/MyDebianTool/src/scripts/deluser.sh";
    public static final String PROCESS_SCRIPT_PATH = "/home/buronsuave/IdeaProjects/MyDebianTool/src/scripts/process.sh";
    public static final String PAUSE_PROCESS_SCRIPT_PATH = "/home/buronsuave/IdeaProjects/MyDebianTool/src/scripts/pauseprocess.sh";
    public static final String RESUME_PROCESS_SCRIPT_PATH = "/home/buronsuave/IdeaProjects/MyDebianTool/src/scripts/resumeprocess.sh";
    public static final String KILL_PROCESS_SCRIPT_PATH = "/home/buronsuave/IdeaProjects/MyDebianTool/src/scripts/killprocess.sh";
    public static final String PERMISSION_SCRIPT_PATH = "/home/buronsuave/IdeaProjects/MyDebianTool/src/scripts/permission.sh";
    public static final String OWNER_SCRIPT_PATH = "/home/buronsuave/IdeaProjects/MyDebianTool/src/scripts/owner.sh";
    public static final String UPDATE_SCRIPT_PATH = "/home/buronsuave/IdeaProjects/MyDebianTool/src/scripts/updatesystem.sh";
}
