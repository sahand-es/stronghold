package view.shape.trade;


import controller.TradeControl;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.resource.ResourcesName;
import view.TradeViewController;
import view.enums.messages.TradeMessages;

import java.util.ArrayList;


public class MakeNewTradePanel {

    private ResourcesName resource;
    private int amount;

    private int price;

    private TradeViewController tradeViewController;

    VBox vBox;

    public MakeNewTradePanel(TradeViewController tradeViewController) {
        this.tradeViewController = tradeViewController;
        resource = ResourcesName.STONE;
        amount = 0;
        price = 0;

        Rectangle rectangle = new Rectangle(600, 100);
        rectangle.setFill(Color.DARKGOLDENROD);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);

        Rectangle resourceImage = new Rectangle(90, 90);
        resourceImage.setFill(new ImagePattern(
                new Image(ResourceNode.class.getResource(
                        "/images/resources/" + resource.name().toLowerCase() + ".png").toExternalForm()
                )
        ));

        hBox.getChildren().add(resourceImage);

        Label amountLabel = new Label(String.valueOf(amount));
        amountLabel.setStyle("-fx-font: 20 sys");

        hBox.getChildren().add(amountLabel);

        VBox amountButtons = new VBox();
        amountButtons.setAlignment(Pos.CENTER);
        amountButtons.setSpacing(5);

        Button addAmountButton = new Button("+");
        addAmountButton.setPrefSize(30,30);
        addAmountButton.setStyle("-fx-font: 15 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        addAmountButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                amount += (amount<100) ? 1 : 0 ;
                amountLabel.setText(String.valueOf(amount));
            }
        });

        Button reduceAmountButton = new Button("-");
        reduceAmountButton.setPrefSize(30,30);
        reduceAmountButton.setStyle("-fx-font: 15 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        reduceAmountButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                amount -= (amount > 0) ? 1 : 0 ;
                amountLabel.setText(String.valueOf(amount));
            }
        });

        amountButtons.getChildren().addAll(addAmountButton,reduceAmountButton);

        hBox.getChildren().add(amountButtons);


        Rectangle coinImage = new Rectangle(90,90);
        coinImage.setFill(new ImagePattern(
                new Image(ResourceNode.class.getResource("/images/resources/coin.png").toExternalForm())
        ));

        hBox.getChildren().add(coinImage);

        Label priceLabel = new Label(String.valueOf(price));
        priceLabel.setStyle("-fx-font: 20 sys");

        hBox.getChildren().add(priceLabel);


        VBox priceButtons = new VBox();
        priceButtons.setSpacing(5);
        priceButtons.setAlignment(Pos.CENTER);

        Button addPriceButton = new Button("+");
        addPriceButton.setPrefSize(30,30);
        addPriceButton.setStyle("-fx-font: 15 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        addPriceButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                price += (price<100) ? 1 : 0 ;
                priceLabel.setText(String.valueOf(price));
            }
        });

        Button reducePriceButton = new Button("-");
        reducePriceButton.setPrefSize(30,30);
        reducePriceButton.setStyle("-fx-font: 15 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        reducePriceButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                price -= (price > 0) ? 1 : 0 ;
                priceLabel.setText(String.valueOf(price));
            }
        });

        priceButtons.getChildren().addAll(addPriceButton,reducePriceButton);

        hBox.getChildren().add(priceButtons);


        Button acceptButton = new Button("make new trade");
        acceptButton.setPrefSize(100,50);
        acceptButton.setStyle("-fx-font: 15 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        acceptButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TradeMessages message = TradeControl.makeTrade(resource.name(),amount,price," ");
                switch (message){
                    case SUCCESS:
                        tradeViewController.makePopUp("trade created successfully");
                        break;

                    case NOT_ENOUGH_RESOURCES:
                        tradeViewController.makePopUp("you dont have enough resources");
                        break;

                    default:
                        tradeViewController.makePopUp("you cant make this trade");
                }


            }
        });

        hBox.getChildren().add(acceptButton);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle,hBox);


        TilePane tilePane = new TilePane();
        tilePane.setAlignment(Pos.CENTER);
        tilePane.setPrefRows(3);
        tilePane.setMaxWidth(600);

        ArrayList<ResourcesName> resources = new ArrayList<>();
        resources.addAll(ResourcesName.foods);
        resources.addAll(ResourcesName.Materials);
        resources.addAll(ResourcesName.weapons);

        for (ResourcesName resource1 : resources) {
            Rectangle image = new Rectangle(85,85);
            image.setFill(new ImagePattern(
                    new Image(ResourceNode.class.getResource(
                            "/images/resources/" + resource1.name().toLowerCase() + ".png").toExternalForm()
                    )
            ));
            image.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    resource = resource1;
                    resourceImage.setFill(new ImagePattern(
                            new Image(ResourceNode.class.getResource(
                                    "/images/resources/" + resource.name().toLowerCase() + ".png").toExternalForm()
                            )
                    ));
                }
            });
            tilePane.getChildren().add(image);
        }

        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(stackPane,tilePane);

    }

    public VBox getvBox() {
        return vBox;
    }
}
