package model.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import utility.RandomGenerators;
import utility.SHA;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private final String username;
    private String message;
    private final String id;
    private final String sendingTimeString;
    private long sendingTimeMillis;
    private String reactionPath;
    // TODO: 6/30/2023 change it with username
    private String userAvatarPath = "file:src/main/resources/images/avatars/1.png";
    private MessageStatus status;
    private final String chatId;

    public Message(String message, String username, String chatId) {
        this.chatId = chatId;
        sendingTimeString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        sendingTimeMillis = System.currentTimeMillis();
        this.username = username;
        this.message = message;
        this.status = MessageStatus.SENT;
        id = SHA.shaString(message + String.valueOf(RandomGenerators.randomPassword()));
    }

    public void setUserAvatarPath(String userAvatarPath) {
        this.userAvatarPath = userAvatarPath;
    }

    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public static Message fromJson(String json) {
        return new Gson().fromJson(json, new TypeToken<Message>(){}.getType());
    }

    public String getChatId() {
        return chatId;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public long getSendingTimeMillis() {
        return sendingTimeMillis;
    }

    public String getReactionPath() {
        return reactionPath;
    }

    public String getUserAvatarPath() {
        return userAvatarPath;
    }

    public String getSendingTimeString() {
        return sendingTimeString;
    }

    public String getId() {
        return id;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public boolean isThisMessageId(String id) {
        return id.equals(this.id);
    }

    public void editMessage(String message) {
        this.message = message;
    }

    public void setReactionPath(String reactionPath) {
        this.reactionPath = reactionPath;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
