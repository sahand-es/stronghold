package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private String sessionId;
    private ArrayList<String> users;
    private int numberOfPlayers;
    private boolean isStarted;

    private int size;

    private String chatId;

    public Session(String sessionId, int numberOfPlayers, String adminUsername) {
        this.sessionId = sessionId;
        this.users = new ArrayList<>();
        users.add(adminUsername);
        this.numberOfPlayers = numberOfPlayers;
        this.isStarted = false;
        size = 1;
    }

    public String getSessionId() {
        return sessionId;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void addUser(String username){
        users.add(username);
        size ++;
    }

    public void removeUser(String username){
        if (users.remove(username))
            size --;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void decrementSize(){
        size --;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public static ArrayList<Session> fromJsonArrayList(String json) {
        return new Gson().fromJson(json, new TypeToken<List<Session>>() {
        }.getType());
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return sessionId + " " + users.size() +"/" + numberOfPlayers + " " + isStarted;
    }
}
