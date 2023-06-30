package model.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import utility.SHA;

import java.util.ArrayList;

public class Chat {
    ArrayList<Message> messages;
    ArrayList<String> users;
    String id;
    ChatType chatType;

    public Chat(ChatType chatType) {
        this.chatType = chatType;
        users = new ArrayList<>();
        messages = new ArrayList<>();
        this.id = SHA.shaString(String.valueOf(users.getClass().hashCode()));
    }

    public void addUser(String username) {
        if (chatType == ChatType.PRIVATE && users.size() == 2)
            return;
        users.add(username);
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
        return new Gson().fromJson(json, new TypeToken<Chat>(){}.getType());
    }
}
