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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.society.Trade;
import view.TradeViewController;
import view.enums.messages.TradeMessages;

import java.util.ArrayList;

public class TradeListNode {
    private Trade trade;
    private TradeViewController tradeViewController;
    Button acceptButton;
    VBox vBox;

    private StackPane stackPane;

    private ArrayList<TradeListNode> allTrades = new ArrayList<>();

    public TradeListNode(Trade trade,TradeViewController tradeViewController) {
        this.trade = trade;
        this.tradeViewController = tradeViewController;

        Rectangle rectangle = new Rectangle(600, 100);
        rectangle.setFill(Color.DARKGOLDENROD);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);

        Rectangle governmentColor = new Rectangle(70,70);
        governmentColor.setFill(trade.getOwner().getColor().getColor());

        hBox.getChildren().add(governmentColor);

        Rectangle resourceImage = new Rectangle(90, 90);
        resourceImage.setFill(new ImagePattern(
                new Image(ResourceNode.class.getResource(
                        "/images/resources/" + trade.getResource().name().toLowerCase() + ".png").toExternalForm()
                )
        ));

        hBox.getChildren().add(resourceImage);

        Label amountLabel = new Label(String.valueOf(trade.getPrice().get(trade.getResource())));
        amountLabel.setStyle("-fx-font: 20 sys");

        hBox.getChildren().add(amountLabel);

        Rectangle coinImage = new Rectangle(90,90);
        coinImage.setFill(new ImagePattern(
                new Image(ResourceNode.class.getResource("/images/resources/coin.png").toExternalForm())
        ));

        hBox.getChildren().add(coinImage);

        Label priceLabel = new Label(String.valueOf(trade.getGold()));
        priceLabel.setStyle("-fx-font: 20 sys");

        hBox.getChildren().add(priceLabel);

        acceptButton = new Button("accept");
        acceptButton.setPrefSize(100,50);
        acceptButton.setStyle("-fx-font: 15 sys ;-fx-background-color: #e6af29 ; -fx-border-color: #262115");
        acceptButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TradeMessages message = TradeControl.acceptTrade(trade," ");
                switch (message){
                    case SUCCESS:
                        tradeViewController.makePopUp("trade created successfully");
                        tradeViewController.showTradeList();
                        break;

                    case NOT_ENOUGH_RESOURCES:
                        tradeViewController.makePopUp("you dont have enough resources");
                        break;

                    default:
                        tradeViewController.makePopUp("you cant accept this trade");
                }
            }
        });

        hBox.getChildren().add(acceptButton);

        stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle,hBox);

        allTrades.add(this);
    }


    public Button getAcceptButton() {
        return acceptButton;
    }

    public StackPane getStackPane() {
        return stackPane;
    }
}
