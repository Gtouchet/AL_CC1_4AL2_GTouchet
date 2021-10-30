package esgi.al.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import esgi.al.Globals;
import esgi.al.models.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
            reader.close();
            return Arrays.stream(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Stream.empty();
    }

    public static void rewriteFile(List<User> users)
    {
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
}