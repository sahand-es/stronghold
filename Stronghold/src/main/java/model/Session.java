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

    public Session(String sessionId, int numberOfPlayers, String adminUsername) {
        this.sessionId = sessionId;
        this.users = new ArrayList<>();
        users.add(adminUsername);
        this.numberOfPlayers = numberOfPlayers;
        this.isStarted = false;
    }

    public String getSessionId() {
        return sessionId;
    }

    public ArrayList<String> getUsers() {
        return users;
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

    @Override
    public String toString() {
        return sessionId + " " + users.size() +"/" + numberOfPlayers + " " + isStarted;
    }
}
