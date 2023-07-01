package model;

import java.util.ArrayList;

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
}
