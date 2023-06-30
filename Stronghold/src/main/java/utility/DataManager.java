package utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Database;
import model.Game;
import model.User;
import model.map.Map;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    //json:
    public static final String USERS_DATABASE_PATH = "src/main/resources/json/users.json" ;
    public static final String LOGGED_IN_DATABASE_PATH = "src/main/resources/json/loggedInUser.json" ;
    public static final String GAMES_PATH = "src/main/resources/json/Games.json" ;
    //csv:
    public static final String RESOURCES_PATH = "src/main/resources/csv/ResourceAndFood.csv" ;
    public static final String WEAPONS_PATH = "src/main/resources/csv/WeaponAndArmour.csv" ;
    public static final String UNITS_PATH = "src/main/resources/csv/Units.csv" ;
    public static final String BUILDINGS_PATH = "src/main/resources/csv/Buildings.csv" ;
    //fxml:
    public static final String SOLDIER_DETAIL_BOX = "/fxml/soldier-detail-box.fxml" ;
    public static final String BUILDING_DETAIL_BOX = "/fxml/building-detail-box.fxml" ;
    public static final String CHANGE_TEXTURE_BOX = "/fxml/change-texture-box.fxml";
    public static final String BUILDING_SCROLL_BOX = "/fxml/building-scroll.fxml";
    public static final String SOLDIER_CONTROL_BOX = "/fxml/soldier-control-box.fxml";
    public static final String MESSAGE_NODE = "/fxml/message-node.fxml";
    public static final String CHAT_NODE = "/fxml/chat-node.fxml";

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
    public static void saveUsers() {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(USERS_DATABASE_PATH);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(Database.getUsers());
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
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
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
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
        } catch (Exception ignored) {
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

    public static void saver() {
        saveUsers();
        saveLoggedIn(null);
    }
}
