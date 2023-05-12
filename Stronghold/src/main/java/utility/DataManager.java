package utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Application;
import model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    public static final String USERS_DATABASE_PATH = "src/main/resources/users.json";

    public static final String LOGGED_IN_DATABASE_PATH = "src/main/resources/loggedInUser.json";

    public static ArrayList<User> loadUsers() {
        try {
            Gson gson = new Gson();
            String text = new String(Files.readAllBytes(Paths.get(USERS_DATABASE_PATH)));

            ArrayList<User> users = gson.fromJson(text, new TypeToken<List<User>>() {
            }.getType());

            if (users == null)
                return (new ArrayList<>());
            return users;
           }
        catch (Exception ignored) {
            return null;
        }
    }

    // call when you want save users database

    // TODO: 4/22/2023  call when program ends
    public static void saveUsers() {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(USERS_DATABASE_PATH);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(Application.getUsers());
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static User loadLoggedInUser() {
        try {
            Gson gson = new Gson();
            String text = new String(Files.readAllBytes(Paths.get(LOGGED_IN_DATABASE_PATH)));

            User user = gson.fromJson(text, new TypeToken<User>() {
            }.getType());

            return user;
        }
        catch (Exception ignored) {
            return null;
        }
    }
    public static void saveLoggedIn(User user) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(LOGGED_IN_DATABASE_PATH);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(user);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saver(){
        saveUsers();
        saveLoggedIn(null);
    }
}
