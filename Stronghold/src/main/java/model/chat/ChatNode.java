package model.chat;

import com.google.gson.Gson;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.App;
import utility.DataManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

public class ChatNode extends Pane{
    private Pane pane;

    private Chat chat;
    private ScrollPane chatScroll;
    private VBox messages;
    private TextArea typedMessaged;
    private Button sendBtn;
    private Label label;
    private VBox usersBox;


    public ChatNode(Chat chat) {
        this.chat = chat;

        try {
            pane = FXMLLoader.load(this.getClass().getResource(DataManager.CHAT_NODE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.getChildren().add(pane);
        chatScroll = (ScrollPane) pane.getChildren().get(0);
        messages = (VBox) chatScroll.getContent();
        typedMessaged = (TextArea) pane.getChildren().get(1);
        sendBtn = (Button) pane.getChildren().get(2);
        label = (Label) pane.getChildren().get(3);
        usersBox = (VBox) ((TitledPane) pane.getChildren().get(4)).getContent();

        set();
    }

    private void set() {
        setMessages();
        setLabel();
        setSendBtn();
        setUsersBox();
    }

    private void setUsersBox() {
        usersBox.setAlignment(Pos.CENTER);
        usersBox.setSpacing(10);
        for (String user : chat.getUsers()) {
            Text text = new Text(user);
            text.setStyle("-fx-font-family: \"Times New Roman\", sans-serif; -fx-font-size: 18;");
            usersBox.getChildren().add(text);
        }
    }

    private void setMessages() {
        messages.setAlignment(Pos.BASELINE_CENTER);
        for (Message message : chat.getMessages()) {
            MessageNode messageNode = new MessageNode(message);
            messages.getChildren().add(messageNode);
        }
    }

    private void setLabel() {
        if (chat.getChatType() != ChatType.PUBLIC)
            label.setText(chat.getChatType().name() + ": " + chat.getName().toUpperCase(Locale.ROOT));
        else {
            label.setText("PUBLIC");
        }
    }

    private void setSendBtn() {
        // TODO: 6/30/2023 new message and fart
    }

    public void update() {
        setUsersBox();
        setMessages();
        for (Node child : messages.getChildren()) {
            if (child instanceof ChatNode) {
                ((ChatNode) child).update();
            }
        }
    }

    public Pane getPane() {
        return pane;
    }

    private void makeNewMessage(String message) {
        //TODO
        HashMap<String, String> data = new HashMap<>();
        data.put("command", "makeMessage");
        data.put("username", App.getCurrentUser().getUsername());
        data.put("message",message);
        data.put("chatId",chat.getId());
        String dataStr = new Gson().toJson(data);
        try {
            App.writeToServer(dataStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void editMessage(String message,String messageId) {
        //TODO
        HashMap<String, String> data = new HashMap<>();
        data.put("command", "editMessage");
        data.put("username", App.getCurrentUser().getUsername());
        data.put("message",message);
        data.put("messageId",messageId); //TODO Id of selected message
        data.put("chatId",chat.getId());
        String dataStr = new Gson().toJson(data);
        try {
            App.writeToServer(dataStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
