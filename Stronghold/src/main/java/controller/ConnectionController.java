package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import model.Database;
import model.User;
import model.chat.Chat;
import model.chat.ChatType;
import model.chat.Message;
import model.network.Connection;
import utility.DataManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConnectionController {
    Connection connection;

    public ConnectionController(Connection connection) {
        this.connection = connection;
    }

    public void handle(String input) throws IOException {
        try {
            HashMap<String, String> data = new Gson().fromJson(input, new TypeToken<HashMap<String, String>>() {
            }.getType());
            String command = data.get("command");

            switch (command) {
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

                case "deleteMessage":
                    deleteMessage(data);
                    break;
                case "seen":
                    setSeen(data);
                    break;
                case "createRoom":
                    createRoom(data);
                    break;
                case "createPv":
                    createPv(data);
                    break;
                case "joinRoom":
                    joinRoom(data);
                    break;


                default:
                    System.out.println("invalid menu");
            }
        } catch (JsonSyntaxException e) {
            System.out.println("invalid data");
        }
    }

    private void joinRoom(HashMap<String, String> data) throws IOException {
        String roomId = data.get("roomId");

        Chat chat = Database.getChatById(roomId);
        if (chat.getChatType() == null)
            connection.write("Invalid chat id!");
        if (chat.getChatType() != ChatType.ROOM)
            connection.write("Can not join any other chat than rooms!");
        else {
            chat.addUser(connection.getUser().getUsername());
            DataManager.saveChats();
            connection.write("Joined room:" + chat.getName());
        }
    }

    private void createPv(HashMap<String, String> data) throws IOException {
        Chat chat = Chat.fromJson(data.get("chat"));
        String username = data.get("username");
        if (Database.getUserByUsername(username) != null) {
            chat.addUser(username);
            Database.addChat(chat);
            connection.write("PV with " + username + " created!");
        } else {
            connection.write("Invalid username!");
        }
    }

    private void createRoom(HashMap<String, String> data) {
        Chat chat = Chat.fromJson(data.get("chat"));
        Database.addChat(chat);
    }

    private void setSeen(HashMap<String, String> data) {
        String chatId = data.get("chatId");
        Chat chat = Database.getChatById(chatId);
        if (chat != null) {
            for (Message message : chat.getMessages()) {
                message.seen();
            }
        }
        DataManager.saveChats();
    }

    private void deleteMessage(HashMap<String, String> data) {
        Message message = Message.fromJson(data.get("message"));
        Chat chat = Database.getChatById(message.getChatId());
        if (chat != null) {
            Message messageS = chat.getMessageById(message.getId());
            messageS.setMessage(message.getMessage());
            messageS.delete();
        }
        DataManager.saveChats();

    }

    private void editMessage(HashMap<String, String> data) {
        Message message = Message.fromJson(data.get("message"));
        Chat chat = Database.getChatById(message.getChatId());
        if (chat != null) {
            Message messageS = chat.getMessageById(message.getId());
            messageS.setMessage(message.getMessage());
        }
        DataManager.saveChats();
    }

    private void makeMessage(HashMap<String, String> data) {
        Message message = Message.fromJson(data.get("message"));
        Chat chat = Database.getChatById(message.getChatId());
        if (chat != null) chat.addMessage(message);
        DataManager.saveChats();
    }

    private void sendUserChats(HashMap<String, String> data) throws IOException {
        String username = data.get("username");
        ArrayList<Chat> output = new ArrayList<>();
        for (Chat chat : Database.getAllChats()) {
            if (chat.haveAccess(username))
                output.add(chat);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String dataStr = gson.toJson(output, new TypeToken<List<Chat>>() {
        }.getType());

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

    private void getUserCommand(HashMap<String, String> data) throws IOException {
        String username = data.get("user");
        User user = Database.getUserByUsername(username);
        String dataStr = new Gson().toJson(user);
        connection.write(dataStr);
    }

    private void getUserByEmailCommand(HashMap<String, String> data) throws IOException {
        String email = data.get("email");
        User user = Database.getUserByEmail(email);
        String dataStr = new Gson().toJson(user);
        connection.write(dataStr);
    }

    private void signup(HashMap<String, String> data) {
        String Username = data.get("username");
        String Password = data.get("password");
        String Nickname = data.get("nickname");
        String Email = data.get("email");
        String Slogan = data.get("slogan");
        int questionNUmber = Integer.parseInt(data.get("questionNumber"));
        String Answer = data.get("answer");

        new User(Username, Password, Nickname, Email, questionNUmber, Answer, Slogan);
    }
}
