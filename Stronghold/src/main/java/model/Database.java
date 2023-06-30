package model;

import model.map.Map;
import utility.DataManager;
import utility.RandomCaptcha;
import view.enums.AllMenus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


public class Database {

    private static ArrayList<User> users ;
    private static User currentUser;
    private static AllMenus currentMenu;
    private static Map currentMap;
    private static Game currentGame;
    private static ArrayList<Game> games;

    static {
        users = DataManager.loadUsers();
        if (users == null){
            users = new ArrayList<>();
        }
        games = DataManager.loadGames();
        currentUser = DataManager.loadLoggedInUser();
    }
    private static ArrayList<Map> allMaps;

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static ArrayList<Game> getGames() {
        return games;
    }

    public static void addGame(Game game) {
        games.add(game);
    }

    public static void addUser(User user) {
        users.add(user);
        DataManager.saveUsers();
    }

    public static User getUserByUsername(String username) {
        if (users == null)
            return null;
        for (User user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }
    public static User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email))
                return user;
        }
        return null;
    }
    public static String top5FamousSlogan() {
        String output = new String();

        HashMap<String, Integer> stringCount = new HashMap<>();

        ArrayList<String> slogans = new ArrayList<>();
        for (User user:users) {
            if (user.getSlogan() != null){
                slogans.add(user.getSlogan());
            }
        }


        // Count the occurrences of each lowercase string
        for (String str : slogans) {
            String lowercaseStr = str.toLowerCase();
            stringCount.put(lowercaseStr, stringCount.getOrDefault(lowercaseStr, 0) + 1);
        }

        // Sort the strings based on their count in descending order
        List<HashMap.Entry<String, Integer>> sortedStrings = new ArrayList<>(stringCount.entrySet());
        sortedStrings.sort(HashMap.Entry.comparingByValue(Comparator.reverseOrder()));

        // Print the top 5 most used strings
        int count = 0;
        for (HashMap.Entry<String, Integer> entry : sortedStrings) {
            if (count >= 5) {
                break;
            }

            if(entry.getKey() != ""){
                output += count + " :" + entry.getKey() + "    count: " + entry.getValue() + "\n";
                count++;
            }

        }

        return output;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Database.currentUser = currentUser;
    }

    public static AllMenus getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(AllMenus currentMenu) {
        Database.currentMenu = currentMenu;
    }

    public static Map getCurrentMap() {
        return currentMap;
    }

    public static void setCurrentMap(Map currentMap) {
        Database.currentMap = currentMap;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        Database.currentGame = currentGame;
    }

}
