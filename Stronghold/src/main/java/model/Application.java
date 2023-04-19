package model;

import utility.DataManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class Application {

    private static ArrayList<User> users;

    static {
        users = DataManager.loadUsers();
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static User getUserByUsername(String username) {
        return null;
    }
}
