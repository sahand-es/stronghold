package model.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import utility.RandomGenerators;
import utility.SHA;

public class Message {
    private final String username;
    private String message;
    private final String id;

    public Message(String message, String username) {
        this.username = username;
        this.message = message;
        id = SHA.shaString(message + String.valueOf(RandomGenerators.randomPassword()));
    }
    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(this);
    }

    public static Message fromJson(String json) {
        return new Gson().fromJson(json, new TypeToken<Message>(){}.getType());
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public boolean isThisMessageId(String id) {
        return id.equals(this.id);
    }

    public void editMessage(String message) {
        this.message = message;
    }
}
