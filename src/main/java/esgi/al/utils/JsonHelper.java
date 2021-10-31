package esgi.al.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import esgi.al.models.User;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class JsonHelper
{
    public static Stream<User> getUserDataFromFile()
    {
        try {
            JsonReader reader = new JsonReader(new FileReader(Globals.JSON_USER_FILE_PATH));
            User[] data = new Gson().fromJson(reader, User[].class);
            return Arrays.stream(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Stream.empty();
    }

    public static void rewriteFile(List<User> users)
    {
        createBackupFile();

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try {
            FileWriter writer = new FileWriter(Globals.JSON_USER_FILE_PATH);
            gson.toJson(users, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createBackupFile()
    {
        try {
            InputStream in = new FileInputStream(Globals.JSON_USER_FILE_PATH);
            OutputStream out = new FileOutputStream(Globals.getJsonUserFileBackupPath());

            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0)
            {
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}