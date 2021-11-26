package esgi.al.cc1.infrastructure.services.jsonServices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Array;
import java.util.List;

public class JsonAccessor<T>
{
    private final Class<T> dataType;

    private final JsonPathCreator jsonPathCreator;

    public JsonAccessor(Class<T> dataType, JsonPathCreator jsonPathCreator)
    {
        this.dataType = dataType;

        this.jsonPathCreator = jsonPathCreator;
    }

    public T[] getDataFromFile()
    {
        try {
            JsonReader reader = new JsonReader(new FileReader(this.jsonPathCreator.filePath));
            T[] data = new Gson().fromJson(reader, this.dataType.arrayType());
            return data != null && data.length > 0 ? data : (T[]) Array.newInstance(this.dataType, 0);

        } catch (FileNotFoundException e1) {
            try {
                System.err.println("Error: file [" + this.jsonPathCreator.filePath + "] not found, could not read data.");

                new File(this.jsonPathCreator.filePath).createNewFile();
                System.err.println("Empty file created to write data in.");

                return this.getDataFromFile();

            } catch (IOException e2) {
                System.err.println("Error: could not create file to write data in.\n" + e2.getMessage());
            }
        }
        return null;
    }

    public void writeInFile(List<T> data)
    {
        this.createBackupFile();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(this.jsonPathCreator.filePath);
            gson.toJson(data, writer);
            writer.close();

        } catch (IOException e1) {
            try {
                System.err.println("Error: file [" + this.jsonPathCreator.filePath + "] not found, could not write data.");

                new File(this.jsonPathCreator.filePath).createNewFile();
                System.err.println("Empty file created to write data in.");

                this.writeInFile(data);

            } catch (IOException e2) {
                System.err.println("Error: could not create file to write data in.\n" + e2.getMessage());
            }
        }
    }

    private void createBackupFile()
    {
        try {
            InputStream in = new FileInputStream(this.jsonPathCreator.filePath);
            OutputStream out = new FileOutputStream(this.jsonPathCreator.getBackupFilePath());

            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0)
            {
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();

        } catch (IOException e) {
            System.err.println("Warning: could not create backup file.\n" + e.getMessage());
        }
    }
}