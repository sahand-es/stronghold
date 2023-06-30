package model.chat;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import utility.DataManager;

import java.io.IOException;
import java.util.Locale;

public class ChatNode {
    private Pane pane;

    private Chat chat;
    private ScrollPane chatScroll;
    private VBox messages;
    private TextArea typedMessaged;
    private Button sendBtn;
    private Label label;


    public ChatNode(Chat chat) throws IOException {
        this.chat = chat;

        pane = FXMLLoader.load(this.getClass().getResource(DataManager.CHAT_NODE));
        chatScroll = (ScrollPane) pane.getChildren().get(0);
        messages = (VBox) chatScroll.getContent();
        typedMessaged = (TextArea) pane.getChildren().get(1);
        sendBtn = (Button) pane.getChildren().get(2);
        label = (Label) pane.getChildren().get(3);

        set();
    }

    private void set() {
        setMessages();
        setLabel();
        setSendBtn();
    }

    private void setMessages() {
        messages.setAlignment(Pos.BASELINE_CENTER);
        for (Message message : chat.getMessages()) {
            MessageNode messageNode = new MessageNode(message);
            messages.getChildren().add(messageNode.getPane());
        }
    }

    private void setLabel() {
        if (chat.getChatType() != ChatType.PUBLIC)
            label.setText(chat.getChatType().name() + ":\t" + chat.getName().toUpperCase(Locale.ROOT));
        else {
            label.setText("PUBLIC");
        }
    }

    private void setSendBtn() {
        // TODO: 6/30/2023 new message and fart
    }

    public Pane getPane() {
        return pane;
    }
}
