package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.resource.ResourcesName;
import view.shape.ResourceNode;

import java.awt.*;
import java.net.URL;



public class MarketViewController extends Application {
    public ScrollPane scrollPane;

    private BorderPane borderPane;

    private int width;
    private int height;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        width = (int) Screen.getPrimary().getBounds().getWidth();
        height = (int) Screen.getPrimary().getBounds().getHeight();

        borderPane = new BorderPane();
        borderPane.setPrefSize(width,height);

        makeLeft();
        makeRight();
        makeTop();
        makeBottom();
        makeScrollPane();

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.show();
    }



    private void makeLeft(){
        Label left = new Label();
        left.setPrefWidth(width / 2 - 250);
        borderPane.setLeft(left);
    }

    private void makeRight(){
        Label right = new Label();
        right.setPrefWidth( width / 2 -265);
        borderPane.setRight(right);
    }

    private void makeTop() {
        Label label = new Label("Market Menu");
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font: 50 sys ; -fx-font-weight: bold");
        label.setPrefHeight(100);

        Label l = new Label();
        l.setPrefWidth(width/2 - 150);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(l,label);
        borderPane.setTop(hBox);
    }

    private void makeBottom(){
        Button back = new Button("Back");
        back.setStyle("-fx-font: 20 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        back.setPrefSize(300,70);
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO
                System.out.println("back");
            }
        });

        Label l1 = new Label();
        l1.setPrefWidth(width/2 - 150);
        l1.setPrefHeight(100);

        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(l1,back);
        borderPane.setBottom(hBox1);
    }

    private void makeScrollPane(){
        scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setPannable(true);


        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().add(new ResourceNode(ResourcesName.APPLE,5,3,100 , 20).getStackPane());
        vBox.getChildren().add(new ResourceNode(ResourcesName.MEAT,4,2,100 , 10).getStackPane());
        vBox.getChildren().add(new ResourceNode(ResourcesName.CHEESE,6,3,100 , 20).getStackPane());
        vBox.getChildren().add(new ResourceNode(ResourcesName.BREAD,5,3,100 , 30).getStackPane());

        scrollPane.setContent(vBox);

        borderPane.setCenter(scrollPane);
    }

}
