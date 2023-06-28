package view.shape.trade;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.society.Trade;

public class TradeHistoryNode {
    Trade trade;
    StackPane stackPane;

    public TradeHistoryNode(Trade trade) {
        this.trade = trade;
        Rectangle rectangle = new Rectangle(600, 100);
        rectangle.setFill(Color.DARKGOLDENROD);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(30);

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

        stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle,hBox);
    }



    public StackPane getStackPane() {
        return stackPane;
    }
}
