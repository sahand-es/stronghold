package model.chat;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.App;
import view.GUIController.MainMenuViewController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class ChatMenuGUI extends Application {
    public ScrollPane chatScroll;
    private double width;
    private double height;
    private VBox allChatsVBox;
    private Pane pane;
    private Group joinGroup;
    @Override
    public void start(Stage stage) {
        width = Screen.getPrimary().getBounds().getWidth();
        height = Screen.getPrimary().getBounds().getHeight();


        try {
            pane = FXMLLoader.load(this.getClass().getResource("/fxml/chats-scroll.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        chatScroll = (ScrollPane) ((VBox)pane.getChildren().get(0)).getChildren().get(0);
        joinGroup = (Group) ((VBox)pane.getChildren().get(0)).getChildren().get(1);


        pane.setPrefSize(width, height);
        setBackground();
        set();

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.show();
    }

    private void set() {
        ArrayList<Chat> userChats = getUserChatsFromServer();
        setAllChats(userChats);

        Button refreshBtn = (Button) joinGroup.getChildren().get(2);
        refreshBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                set();
            }
        });
        Button createRoomBtn = (Button) joinGroup.getChildren().get(4);
        createRoomBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                createRoom();
            }
        });

        Button createPvBtn = (Button) joinGroup.getChildren().get(6);
        createPvBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                createPv();
            }
        });
    }

    private void createPv() {
        TextArea username = (TextArea) joinGroup.getChildren().get(5);
        if (username.equals(App.getCurrentUser().getUsername()))
            return;
        Chat chat = new Chat(ChatType.PRIVATE, username.getText());
        chat.addUser(App.getCurrentUser().getUsername());

        HashMap<String,String> data = new HashMap<>();
        data.put("command","createPv");
        data.put("chat",chat.toJson());
        data.put("username",username.getText());
        String dataStr = new Gson().toJson(data);
        try {
            App.writeToServer(dataStr);
            System.out.println(App.readFromServer());
        } catch (Exception e){
            throw new RuntimeException (e);
        }
    }

    private void createRoom() {
        TextArea roomName = (TextArea) joinGroup.getChildren().get(3);
        Chat chat = new Chat(ChatType.ROOM, roomName.getText());
        chat.addUser(App.getCurrentUser().getUsername());

        HashMap<String,String> data = new HashMap<>();
        data.put("command","createRoom");
        data.put("chat",chat.toJson());
        String dataStr = new Gson().toJson(data);
        try {
            App.writeToServer(dataStr);
        } catch (Exception e){
            throw new RuntimeException (e);
        }

    }

    private ArrayList<Chat> getUserChatsFromServer() {
        HashMap<String,String> data = new HashMap<>();
        data.put("command","getUserChats");
        data.put("username",App.getCurrentUser().getUsername());
        String dataStr = new Gson().toJson(data);
        try {
            App.writeToServer(dataStr);
            dataStr = App.readFromServer();
            ArrayList<Chat> output = Chat.fromJsonArrayList(dataStr);
            return output;
        } catch (Exception e){
            throw new RuntimeException (e);
        }
    }

    private void sendSeen(String chatId) {
        HashMap<String,String> data = new HashMap<>();
        data.put("command","seen");
        data.put("chatId", chatId);
        String dataStr = new Gson().toJson(data);
        try {
            App.writeToServer(dataStr);
        } catch (Exception e){
            throw new RuntimeException (e);
        }
    }



    private void setAllChats(ArrayList<Chat> chats) {
        allChatsVBox = (VBox) chatScroll.getContent();
        if (!allChatsVBox.getChildren().isEmpty())
            allChatsVBox.getChildren().clear();
        VBox vBox = (VBox) pane.getChildren().get(0);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefWidth(chatScroll.getPrefWidth());
        vBox.setLayoutX(App.centerX - vBox.getPrefWidth()/2);

        for (Chat chat : chats) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setPrefWidth(allChatsVBox.getPrefWidth());
            hBox.setPrefHeight(50);
            hBox.getStyleClass().add("send-button");
            Label label = new Label(chat.toString());

            hBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ChatNode chatNode = new ChatNode(chat);
                    sendSeen(chat.getId());
                    Scene scene = new Scene(chatNode);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                }
            });

            hBox.getChildren().add(label);
            allChatsVBox.getChildren().add(hBox);
        }

    }

    private void setBackground(){
        Image image = new Image(
                MainMenuViewController.class.getResource("/images/backgrounds/profile-menu-background.jpg").toExternalForm()
        );

        BackgroundImage backgroundFill = new BackgroundImage(
                image, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT
        );

        Background background = new Background(backgroundFill);
        pane.setBackground(background);
    }
}
