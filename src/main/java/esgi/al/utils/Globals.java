package esgi.al.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Globals
{
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");

    public static final String JSON_USER_FILE_PATH = "./res/registeredUsers.json";

    public static String getJsonUserFileBackupPath()
    {
        return "./res/backups/registeredUsers.old." + dateFormatter.format(new Date()) + ".json";
    }
}
