package esgi.al.cc1.infrastructure.services.jsonServices;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonPathCreator
{
    public final String filePath;
    public final String backupFilePath;

    private final SimpleDateFormat dateFormatter;

    public JsonPathCreator(String filePath, String backupFilePath)
    {
        this.filePath = filePath;
        this.backupFilePath = backupFilePath;

        this.dateFormatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
    }

    public String getBackupFilePath()
    {
        return this.backupFilePath + dateFormatter.format(new Date()) + ".json";
    }
}
