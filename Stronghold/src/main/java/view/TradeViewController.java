package view;

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
import model.society.Trade;
import view.shape.trade.MakeNewTradePanel;
import view.shape.trade.TradeHistoryNode;
import view.shape.trade.TradeListNode;

import java.util.ArrayList;


public class TradeViewController extends Application {

    private BorderPane borderPane;
    private VBox theVBox;
    private BorderPane innerBorderPane;
    private int width;
    private int height;

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

        setNewTradePanel();

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
                try {
                    new MarketViewController().start(MarketViewController.stage);
                } catch (Exception e) {
                    //TODO
                }

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
                setNewTradePanel();
            }
        });

        Button tradeListButton = new Button("Trade List");
        tradeListButton.setStyle("-fx-font: 15 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        tradeListButton.setPrefSize(200,20);
        tradeListButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showTradeList();
            }
        });

        Button tradeHistoryButton = new Button("Trade History");
        tradeHistoryButton.setStyle("-fx-font: 15 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        tradeHistoryButton.setPrefSize(200,20);
        tradeHistoryButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showTradHistory();
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
        initializeTheVBox();
        theVBox.getChildren().add(new MakeNewTradePanel(this).getvBox());
        innerBorderPane.setCenter(theVBox);
    }

    public void showTradeList(){
        initializeTheVBox();

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        ArrayList<Trade> trades = Database.getCurrentGame().getAllTrades();

        if (trades.size() == 0)
            vBox.getChildren().add(showEmptyList());

        for (int i = trades.size() - 1 ; i >= 0 ; i--){
            vBox.getChildren().add(new TradeListNode(trades.get(i),this).getStackPane());
        }

        makeScrollPane(vBox);
        innerBorderPane.setCenter(theVBox);
    }

    private void showTradHistory(){
        initializeTheVBox();


        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        ArrayList<Trade> trades = Database.getCurrentGame().getCurrentGovernment().getTradesHistory();

        if (trades.size() == 0)
            vBox.getChildren().add(showEmptyList());

        for (int i = trades.size() - 1 ; i >= 0 ; i--){
            vBox.getChildren().add(new TradeHistoryNode(trades.get(i)).getStackPane());
        }


        makeScrollPane(vBox);
        innerBorderPane.setCenter(theVBox);
    }

    private void initializeTheVBox(){
        theVBox = new VBox();
        theVBox.setAlignment(Pos.CENTER);
        Label label = new Label();
        label.setPrefSize(0,0);
        theVBox.getChildren().add(label);
    }

    private void makeScrollPane(VBox vBox){
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setPannable(true);
        scrollPane.setPrefWidth(615);
        scrollPane.setMaxWidth(615);
        scrollPane.setContent(vBox);
        theVBox.getChildren().add(scrollPane);
    }

    public void makePopUp(String text){
        Rectangle rectangle = new Rectangle(600,50);
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

    private StackPane showEmptyList(){
        Rectangle rectangle = new Rectangle(600,50);
        rectangle.setFill(Color.DARKGOLDENROD);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        Label message = new Label("There is no trade to show");
        message.setAlignment(Pos.CENTER);
        message.setStyle("-fx-font: 20 sys");

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle,message);

        return stackPane;
    }
}
