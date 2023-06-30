package view;

import controller.GameControl;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Database;
import model.Game;
import model.map.Map;
import model.resource.ResourcesName;
import view.shape.trade.ResourceNode;


public class MarketViewController extends Application {
    private ScrollPane scrollPane;

    private BorderPane borderPane;

    private VBox theVBox;


    private int width;
    private int height;


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
                MarketViewController.class.getResource("/images/backgrounds/market-menu-background.jpg").toExternalForm()
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
        Button tradeMenu = new Button("Trade Menu");
        tradeMenu.setStyle("-fx-font: 20 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        tradeMenu.setPrefSize(300,70);
        tradeMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    new TradeViewController().start(Database.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Button back = new Button("Back");
        back.setStyle("-fx-font: 20 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        back.setPrefSize(300, 70);
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ResourceNode.clearAllResourceNodes();
                try {
                    new GameMenuApplication().start(Database.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Label l1 = new Label();
        l1.setPrefWidth(0);
        l1.setPrefHeight(200);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(tradeMenu,back);

        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(l1,vBox);
        borderPane.setBottom(hBox1);
    }

    private void makeScrollPane() {
        initializeTheVBox();

        scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setPannable(true);
        VBox vBox = new VBox();
        vBox.setSpacing(10);



        int buyPrice;
        int sellPrice;
        int gold = Database.getCurrentGame().getCurrentGovernment().getResource().getGold();
        int amount;

        buyPrice = 5;
        sellPrice = 3;
        for (ResourcesName resource : ResourcesName.foods) {
            amount = 20; GameControl.getCurrentGovernment().getResource().getAmount(resource);
            vBox.getChildren().add(
                    new ResourceNode(resource, buyPrice, sellPrice, gold, amount,this).getStackPane()
            );
        }


        buyPrice = 15;
        sellPrice = 10;
        for (ResourcesName resource : ResourcesName.Materials) {
            amount = GameControl.getCurrentGovernment().getResource().getAmount(resource);
            vBox.getChildren().add(
                    new ResourceNode(resource, buyPrice, sellPrice, gold, amount,this).getStackPane()
            );
        }

        buyPrice = 20;
        sellPrice = 15;
        for (ResourcesName resource : ResourcesName.weapons) {
            amount = GameControl.getCurrentGovernment().getResource().getAmount(resource);
            vBox.getChildren().add(
                    new ResourceNode(resource, buyPrice, sellPrice, gold, amount,this).getStackPane()
            );
        }


        scrollPane.setContent(vBox);
        theVBox.getChildren().add(scrollPane);

        borderPane.setCenter(theVBox);
    }

    private void initializeTheVBox(){
        theVBox = new VBox();
        theVBox.setAlignment(Pos.CENTER);
        Label label = new Label();
        label.setPrefSize(0,0);
        theVBox.getChildren().add(label);
    }
    public void makePopUp(String text){
        Rectangle rectangle = new Rectangle(515,50);
        rectangle.setFill(Color.DARKGOLDENROD);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        Label message = new Label(text);
        message.setStyle("-fx-font: 20 sys");

        Button button = new Button("ok");
        button.setPrefSize(90,30);
        button.setStyle("-fx-font: 20 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Label label = new Label();
                label.setPrefSize(0,0);
                theVBox.getChildren().set(0,label);
            }
        });

        hBox.getChildren().addAll(message,button);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle,hBox);
        theVBox.getChildren().set(0,stackPane);
    }

}
