package esgi.al.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import esgi.al.models.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

public class JsonHelper
{
    public static Stream<User> getUserDataFromFile(String path)
    {
        try {
            JsonReader reader = new JsonReader(new FileReader(path));
            User[] data = new Gson().fromJson(reader, User[].class);
            return Arrays.stream(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Stream.empty();
    }

    public static void deleteUserFromFile(String path, UUID id)
    {
        // Todo: implements user's deletion in json file
    }
}