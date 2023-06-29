package model;

import model.map.Map;
import utility.DataManager;
import utility.RandomCaptcha;
import view.enums.AllMenus;

import java.util.ArrayList;


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
