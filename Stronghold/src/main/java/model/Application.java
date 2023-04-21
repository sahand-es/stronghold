package model;

import utility.DataManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class Application {

    private static final ArrayList<User> users ;

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
            if (user.getEmail().equals(email))
                return user;
        }
        return null;
    }
}
