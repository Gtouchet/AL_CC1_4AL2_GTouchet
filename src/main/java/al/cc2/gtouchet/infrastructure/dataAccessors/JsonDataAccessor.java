package al.cc2.gtouchet.infrastructure.dataAccessors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsonDataAccessor<T> implements DataAccessor<T>
{
    private final Class<T> dataType;
    private final DateFormat dateFormatter;

    private final String filePath;
    private final String backupFilePath;

    public JsonDataAccessor(Class<T> dataType)
    {
        this.dataType = dataType;
        this.dateFormatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");

        this.filePath = "./res/" + dataType.getSimpleName().toLowerCase() + "s.json";
        this.backupFilePath = "./res/backups/" + dataType.getSimpleName().toLowerCase() + "s/";
    }

    @Override
    public List<T> getData()
    {
        try {
            List<T> data = new Gson().fromJson(
                    new JsonReader(new FileReader(this.filePath)),
                    TypeToken.getParameterized(List.class, this.dataType).getType()
            );
            return data != null ? data : new ArrayList<>();

        } catch (FileNotFoundException e1) {
            try {
                System.err.println("Error: file [" + this.filePath + "] not found, could not read data.");

                new File(this.filePath).createNewFile();
                System.err.println("Empty file created to write data in.");

                return this.getData();

            } catch (IOException e2) {
                System.err.println("Error: could not create file to write data in.\n" + e2.getMessage());
            }
        }
        return null;
    }

    @Override
    public void write(List<T> data)
    {
        this.createBackupFile();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(this.filePath);
            gson.toJson(data, writer);
            writer.close();

        } catch (IOException e1) {
            try {
                System.err.println("Error: file [" + this.filePath + "] not found, could not write data.");

                new File(this.filePath).createNewFile();
                System.err.println("Empty file created to write data in.");

                this.write(data);

            } catch (IOException e2) {
                System.err.println("Error: could not create file to write data in.\n" + e2.getMessage());
            }
        }
    }

    private void createBackupFile()
    {
        try {
            InputStream in = new FileInputStream(this.filePath);
            OutputStream out = new FileOutputStream(this.backupFilePath + this.dateFormatter.format(new Date()) + ".json");

            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();

        } catch (IOException e) {
            System.err.println("Warning: could not create backup file.\n" + e.getMessage());
        }
    }
}