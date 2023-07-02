package model.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import utility.SHA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chat {
    private ArrayList<Message> messages;
    private ArrayList<String> users;
    private String id;
    private ChatType chatType;

    private String name;


    public Chat(ChatType chatType, String name) {
        this.chatType = chatType;
        this.name = name;
        users = new ArrayList<>();
        messages = new ArrayList<>();
        this.id = SHA.shaString(String.valueOf(users.getClass().hashCode()));
    }

    public ArrayList<Message> getMessages() {
        ArrayList<Message> sortedMessages = new ArrayList<>(messages);
        Collections.sort(sortedMessages, (first, second) ->
        {
            return Math.toIntExact(first.getSendingTimeMillis() - second.getSendingTimeMillis());
        });
        return sortedMessages;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public ChatType getChatType() {
        return chatType;
    }

    public void addUser(String username) {
        if (chatType == ChatType.PRIVATE && users.size() == 2)
            return;
        users.add(username);
    }

    public void removeUser(String username){
        users.remove(username);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public boolean haveAccess(String username) {
        if (chatType == ChatType.PUBLIC)
            return true;
        for (String user : users) {
            if (user.equals(username))
                return true;
        }
        return false;
    }

    public boolean isThisChatId(String id) {
        return id.equals(this.id);
    }

    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public static Chat fromJson(String json) {
        return new Gson().fromJson(json, new TypeToken<Chat>() {
        }.getType());
    }

    public static String toJson(ArrayList<Chat> chats) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(chats);
    }

    public static ArrayList<Chat> fromJsonArrayList(String json) {
        return new Gson().fromJson(json, new TypeToken<List<Chat>>() {
        }.getType());
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        if (chatType != ChatType.PUBLIC)
            return chatType + ":\t" + name;
        return chatType.name();
    }

    public Message getMessageById(String id){
        for (Message message : messages) {
            if(message.getId().equals(id))
                return message;
        }
        return null;
    }
}
