package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.resource.ResourcesName;
import view.shape.ResourceNode;


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
        borderPane.setPrefSize(width, height);


        setBackground();

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


    private void setBackground(){
        Image image = new Image(
                MarketViewController.class.getResource("/images/backgrounds/background.jpg").toExternalForm()
        );
        BackgroundImage backgroundFill = new BackgroundImage(
                image,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT
        );
        Background background = new Background(backgroundFill);
        borderPane.setBackground(background);
    }

    private void makeLeft() {
        Label left = new Label();

        left.setPrefWidth(width / 2 - 250);
        borderPane.setLeft(left);
    }

    private void makeRight() {
        Label right = new Label();
        right.setPrefWidth(width / 2 - 265);
        borderPane.setRight(right);
    }

    private void makeTop() {
        Label label = new Label("Market Menu");
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font: 50 sys ; -fx-font-weight: bold");
        label.setPrefHeight(100);


        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(label);
        borderPane.setTop(hBox);
    }

    private void makeBottom() {
        Button back = new Button("Back");
        back.setStyle("-fx-font: 20 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        back.setPrefSize(300, 70);
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO
                ResourceNode.clearAllResourceNodes();
            }
        });

        Label l1 = new Label();
        l1.setPrefWidth(0);
        l1.setPrefHeight(100);

        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(l1, back);
        borderPane.setBottom(hBox1);
    }

    private void makeScrollPane() {
        scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setPannable(true);

        VBox vBox = new VBox();
        vBox.setSpacing(10);



        int buyPrice;
        int sellPrice;
        int gold = 100; //TODO get from government
        int amount;

        buyPrice = 5;
        sellPrice = 3;
        for (ResourcesName resource : ResourcesName.foods) {
            amount = 20; //TODO
            vBox.getChildren().add(
                    new ResourceNode(resource, buyPrice, sellPrice, gold, amount).getStackPane()
            );
        }


        buyPrice = 15;
        sellPrice = 10;
        for (ResourcesName resource : ResourcesName.Materials) {
            amount = 20; //TODO
            vBox.getChildren().add(
                    new ResourceNode(resource, buyPrice, sellPrice, gold, amount).getStackPane()
            );
        }

        buyPrice = 20;
        sellPrice = 15;
        for (ResourcesName resource : ResourcesName.weapons) {
            amount = 20; //TODO
            vBox.getChildren().add(
                    new ResourceNode(resource, buyPrice, sellPrice, gold, amount).getStackPane()
            );
        }


        scrollPane.setContent(vBox);

        borderPane.setCenter(scrollPane);
    }

}
