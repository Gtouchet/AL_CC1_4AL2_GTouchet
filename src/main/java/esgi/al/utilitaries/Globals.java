package esgi.al.utilitaries;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Globals
{
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");

    public static final String JSON_USERS_FILE_PATH = "./res/registeredUsers.json";
    public static final String JSON_PAYMENTS_FILE_PATH = "./res/registeredPayments.json";

    public static String getUsersFileBackupPath()
    {
        return "./res/backups/users/registeredUsers.old." + dateFormatter.format(new Date()) + ".json";
    }

    public static String getPaymentsFileBackupPath()
    {
        return "./res/backups/payments/registeredPayments.old." + dateFormatter.format(new Date()) + ".json";
    }
}
