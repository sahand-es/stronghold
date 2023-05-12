package utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Application;
import model.Game;
import model.User;
import model.map.Map;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    public static final String USERS_DATABASE_PATH = "src/main/resources/users.json";
    public static final String LOGGED_IN_DATABASE_PATH = "src/main/resources/loggedInUser.json";
    public static final String RESOURCES_PATH = "src/main/resources/ResourceAndFood.csv";
    public static final String WEAPONS_PATH = "src/main/resources/WeaponAndArmour.csv";
    public static final String UNITS_PATH = "src/main/resources/Units.csv";
    public static final String BUILDINGS_PATH = "src/main/resources/Buildings.csv";
    public static final String GAMES_PATH = "src/main/resources/Games.json";

    public static ArrayList<User> loadUsers() {
        try {
            Gson gson = new Gson();
            String text = new String(Files.readAllBytes(Paths.get(USERS_DATABASE_PATH)));

            ArrayList<User> users = gson.fromJson(text, new TypeToken<List<User>>() {
            }.getType());

            if (users == null)
                return (new ArrayList<>());
            return users;
        } catch (Exception ignored) {
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

    /**
     *
     * @param filePath file path from src
     * @return arraylist of string[] which first element contains attributes
     */
    public static ArrayList<String[]> getArrayListFromCSV(String filePath) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String[]> result = new ArrayList<>();
        String newLine;
        while (true) {
            try {
                if ((newLine = bufferedReader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            result.add(trimAll(newLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)")));
        }
        return result;
    }

    private static String[] trimAll(String[] stringArray) {
        for (int i = 0; i < stringArray.length; i++)
            stringArray[i] = stringArray[i].trim();
        return stringArray;
    }

    public static void saveGames(Map map) {
        try {
            FileWriter fileWriter = new FileWriter(GAMES_PATH);
            Gson gson = new  GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(map);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Game> loadGames() {
        try {
            Gson gson = new Gson();
            String text = new String(Files.readAllBytes(Paths.get(GAMES_PATH)));
            ArrayList<Game> games = gson.fromJson(text, new TypeToken<List<Game>>() {
            }.getType());

            if (games == null)
                return (new ArrayList<>());
            return games;
        } catch (Exception ignored) {
            return null;
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
    public static void saveLoggedIn() {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(LOGGED_IN_DATABASE_PATH);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(Application.getCurrentUser());
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //ToDo delete Logged in user from json file
    //This must be called for when we log out in profile menu

    public static void main(String[] args) {
        Map map = new Map(100, 100);
        Game game = new Game(map);
        System.out.println(Application.getGames());
        saveGames(map);
    }
}
