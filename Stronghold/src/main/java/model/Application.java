package model;

import java.util.ArrayList;

public class Application
{
    private static ArrayList<User> users;

    static
    {
        users = new ArrayList<>();
    }

    public static void addUser(User user)
    {
        users.add(user);
    }

    public static User getUserByUsername(String username)
    {
        return null;
    }
}
