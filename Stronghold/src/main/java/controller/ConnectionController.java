package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import model.Database;
import model.User;
import model.chat.Chat;
import model.chat.Message;
import model.network.Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConnectionController {
    Connection connection;

    public ConnectionController(Connection connection) {
        this.connection = connection;
    }

    public void handle(String input) throws IOException{
        try {
            HashMap<String,String> data = new Gson().fromJson(input, new TypeToken<HashMap<String,String>>(){}.getType());
            String command = data.get("command");

            switch (command){
                case "setUser":
                    setUserCommand(data);
                    break;

                case "getUser":
                    getUserCommand(data);
                    break;

                case "getUserByEmail":
                    getUserByEmailCommand(data);
                    break;

                case "signup":
                    signup(data);
                    break;

                case "setAvatarPath":
                    setAvatarPath(data);
                    break;

                case "setPassword":
                    setPassword(data);
                    break;

                case "setSlogan":
                    setSlogan(data);
                    break;

                case "getAllUsers":
                    sendAllUsers();
                    break;


                case "getUserChats":
                    sendUserChats(data);
                    break;

                case "makeMessage":
                    makeMessage(data);
                    break;

                case "editMessage":
                    editMessage(data);
                    break;


                default:
                    System.out.println("invalid menu");
            }
        } catch (JsonSyntaxException e) {
            System.out.println("invalid data");
        }
    }

    private void editMessage(HashMap<String, String> data) {
        //TODO
        Message message = Message.fromJson(data.get("message"));
        Chat chat = Database.getChatById(message.getChatId());
        if (chat != null) {
            Message messageS = chat.getMessageById(message.getId());
            messageS.setMessage(message.getMessage());
        }
    }

    private void makeMessage(HashMap<String, String> data) {
        //TODO
        Message message = Message.fromJson(data.get("message"));
        Chat chat = Database.getChatById(message.getChatId());
        if (chat != null) chat.addMessage(message);
    }

    private void sendUserChats(HashMap<String, String> data) throws IOException {
        String username = data.get("username");
        ArrayList<Chat> output = new ArrayList<>();
        for (Chat chat : Database.getAllChats()) {
            if (chat.haveAccess(username))
                output.add(chat);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String dataStr = gson.toJson(output, new TypeToken<List<Chat>>(){}.getType());

        connection.write(dataStr);
    }

    private void sendAllUsers() throws IOException {
        for (User user : Database.getUsers()) {
            connection.write(new Gson().toJson(user));
        }
        connection.write("EOF");
    }

    private void setSlogan(HashMap<String, String> data) {
        String slogan = data.get("slogan");
        connection.getUser().setSlogan(slogan);
    }

    private void setPassword(HashMap<String, String> data) {
        String password = data.get("password");
        connection.getUser().setPassword(password);
    }

    private void setAvatarPath(HashMap<String, String> data) {
        String imagePath = data.get("imagePath");
        System.out.println("image path : " + imagePath);
        connection.getUser().setAvatarPath(imagePath);
    }


    private void setUserCommand(HashMap<String, String> data) {
        String username = data.get("user");
        User user = Database.getUserByUsername(username);
        connection.setUser(user);
    }

    private void getUserCommand(HashMap<String,String> data) throws IOException {
        String username = data.get("user");
        User user = Database.getUserByUsername(username);
        String dataStr = new Gson().toJson(user);
        connection.write(dataStr);
    }

    private void getUserByEmailCommand (HashMap<String,String> data) throws IOException {
        String email = data.get("email");
        User user = Database.getUserByEmail(email);
        String dataStr = new Gson().toJson(user);
        connection.write(dataStr);
    }

    private void signup(HashMap<String,String> data){
        String Username = data.get("username");
        String Password = data.get("password");
        String Nickname = data.get("nickname");
        String Email = data.get("email");
        String Slogan = data.get("slogan");
        int questionNUmber = Integer.parseInt(data.get("questionNumber"));
        String Answer = data.get("answer");

        new User(Username,Password,Nickname,Email,questionNUmber,Answer,Slogan);
    }
}
