package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import view.shape.MakeNewTradePanel;
import view.shape.TradeHistoryNode;
import view.shape.TradeListNode;


public class TradeViewController extends Application {

    private BorderPane borderPane;
    private BorderPane innerBorderPane;
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
        makeTop();
        makeBottom();
        makeButtons();

        VBox vBox = new VBox();
        TradeListNode tradeListNode = new TradeListNode(this);

        vBox.getChildren().add(tradeListNode.getStackPane());

        innerBorderPane.setCenter(vBox);





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
                image, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT
        );
        Background background = new Background(backgroundFill);
        borderPane.setBackground(background);
    }

    private void makeTop() {
        Label label = new Label("Trade Menu");
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

            }
        });

        Label l = new Label();
        l.setPrefWidth(0);
        l.setPrefHeight(100);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll( back,l);
        borderPane.setBottom(hBox);
    }

    private void makeButtons(){
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);

        HBox hBox = new HBox();

        Button newTradeButton = new Button("New Trade");
        newTradeButton.setStyle("-fx-font: 15 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        newTradeButton.setPrefSize(200,20);
        newTradeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO
                System.out.println("new Trade");
                setNewTradePanel();
            }
        });

        Button tradeListButton = new Button("Trade List");
        tradeListButton.setStyle("-fx-font: 15 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        tradeListButton.setPrefSize(200,20);
        tradeListButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO
                System.out.println("Trade List");
            }
        });

        Button tradeHistoryButton = new Button("Trade History");
        tradeHistoryButton.setStyle("-fx-font: 15 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        tradeHistoryButton.setPrefSize(200,20);
        tradeHistoryButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO
                System.out.println("Trade History");
            }
        });

        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(newTradeButton,tradeListButton,tradeHistoryButton);
        vBox.getChildren().add(hBox);

        innerBorderPane = new BorderPane();
        innerBorderPane.setPrefSize(600,600);
        vBox.getChildren().add(innerBorderPane);

        borderPane.setCenter(vBox);
    }

    public void setNewTradePanel(){
        innerBorderPane.setCenter(new MakeNewTradePanel().getvBox());
    }
}
