package controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import model.Database;
import model.User;
import model.network.Connection;

import java.io.IOException;
import java.util.HashMap;

public class ConnectionController {
    Connection connection;

    public ConnectionController(Connection connection) {
        this.connection = connection;
    }

    public void handel(String input) throws IOException{
        try {
            HashMap<String,String> data = new Gson().fromJson(input, HashMap.class);
            String menu = data.get("menu");

            switch (menu){
                case "login":
                    handelLogin(data);
                    break;

                default:
                    System.out.println("invalid menu");
            }
        } catch (JsonSyntaxException e) {
            System.out.println("invalid data");
        }
    }

    private void handelLogin(HashMap<String,String> data) throws IOException {
        String command = data.get("command");
        switch (command){
            case "setUser":
                setUserCommand(data);
                break;

            default:
                System.out.println("invalid command");
        }
    }

    private void setUserCommand(HashMap<String, String> data) {
        String username = data.get("user");
        User user = Database.getUserByUsername(username);
        connection.setUser(user);
    }
}
