package model.chat;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.App;
import model.Database;
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
        allChatsVBox = (VBox) chatScroll.getContent();
        joinGroup = (Group) ((VBox)pane.getChildren().get(0)).getChildren().get(1);


        pane.setPrefSize(width, height);
        setBackground();
        initialize();

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.show();
    }

    private void initialize() {
        ArrayList<Chat> userChats = getUserChatsFromServer();

        //////
        Message message = new Message("Fuck all niggas", "kir");
        Message message2 = new Message("Fuck all iranians", "bokon");

        Chat chat = new Chat(ChatType.PUBLIC,"nigga" );
        Chat chat2 = new Chat(ChatType.PRIVATE,"nigga" );
        chat.addMessage(message);
        chat.addMessage(message2);
        chat.addUser(message.getUsername());
        chat.addUser(message2.getUsername());
        chat.addUser("nigga");
        ArrayList<Chat> chats = new ArrayList<>();
        chats.add(chat);
        chats.add(chat2);
        ///////
        setAllChats(userChats);
    }

    private ArrayList<Chat> getUserChatsFromServer() {
        HashMap<String,String> data = new HashMap<>();
        data.put("command","getUserChats");
        data.put("username",App.getCurrentUser().getUsername());
        String dataStr = new Gson().toJson(data);
        try {
            App.writeToServer(dataStr);
            dataStr = App.readFromServer();
            ArrayList<Chat> output = new ArrayList<>(); //TODO convert chat arr form json
            return output;
        } catch (Exception e){
            throw new RuntimeException (e);
        }
    }



    private void setAllChats(ArrayList<Chat> chats) {
        VBox vBox = (VBox) pane.getChildren().get(0);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefWidth(chatScroll.getPrefWidth());
        vBox.setLayoutX(App.centerX - vBox.getPrefWidth()/2);

        // TODO: 7/1/2023 get chats from user.
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
                    Scene scene = new Scene(chatNode);
                    Stage stage = new Stage();
                    stage.setScene(scene);
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
